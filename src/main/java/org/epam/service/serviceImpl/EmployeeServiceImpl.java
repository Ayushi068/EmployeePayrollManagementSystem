package org.epam.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.epam.dto.EmployeeRequestDTO;
import org.epam.dto.EmployeeResponseDTO;
import org.epam.exception.DepartmentNotFoundException;
import org.epam.exception.DesignationNotFoundException;
import org.epam.exception.EmployeeNotFoundException;
import org.epam.mapper.EmployeeMapper;
import org.epam.model.Department;
import org.epam.model.Designation;
import org.epam.model.Employee;
import org.epam.repository.EmployeeRepository;
import org.epam.service.EmployeeService;
import org.epam.repository.DepartmentRepository;
import org.epam.repository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, DesignationRepository designationRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.designationRepository = designationRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    @Transactional
    public EmployeeResponseDTO add(EmployeeRequestDTO employeeDto) {
//
        Employee employee = employeeRepository.findByEmail(employeeDto.getEmail());
        if(employee!=null) throw new IllegalArgumentException("Employee already exist");

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(()->new DepartmentNotFoundException("Invalid department ID"));
        Designation designation = designationRepository.findById(employeeDto.getDesignationId())
                .orElseThrow(()->new DesignationNotFoundException("Invalid Designation ID"));

        if(employeeDto.getSalary()<designation.getMinSalary()||employeeDto.getSalary()>designation.getMaxSalary())
        {
            throw new IllegalArgumentException("Salary is invalid, Salary must be in range"+designation.getMinSalary()+" "+designation.getMaxSalary());
        }

        Employee saveEmployee = employeeMapper.toEntity(employeeDto);
        saveEmployee.setDepartment(department);
        saveEmployee.setDesignation(designation);
        employeeRepository.save(saveEmployee);
        return convertToDto(saveEmployee);

    }

    @Override
    @Transactional
    public EmployeeResponseDTO findById(int id) {
        if(employeeRepository.existsById(id))
        {
            return convertToDto(employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee Id is invalid")));
        }

        else{
            throw new EmployeeNotFoundException("Invalid Employee id");
        }
    }

    @Override
    @Transactional
    public EmployeeResponseDTO deleteById(int id) {

        Employee employee=employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Invalid Employee ID"));
        employeeRepository.deleteById(id);
        return convertToDto(employee);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO update(int id, EmployeeRequestDTO employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
            Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException("Invalid department ID"));


            Designation designation = designationRepository.findById(employeeDto.getDesignationId())
                    .orElseThrow(() -> new DesignationNotFoundException("Invalid designation ID"));


            employeeMapper.toEntity(employeeDto);
        employee.setDepartment(department);
        employee.setDesignation(designation);

        employeeRepository.save(employee);
        return convertToDto(employee);
    }


    @Override
    @Transactional
    public List<EmployeeResponseDTO> getAllByDepartment(int departmentId) {
        Department department =  departmentRepository.findById(departmentId).orElseThrow(()-> new DepartmentNotFoundException("Invalid Department Id"));

        List<Employee> employeeList = employeeRepository.findByDepartment(department);
        if (employeeList==null||employeeList.isEmpty()) {
            throw new IllegalArgumentException("No employees found for this department.");
        }

        return employeeList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private EmployeeResponseDTO convertToDto(Employee employee) {
        return employeeMapper.toDTO(employee);
    }
}