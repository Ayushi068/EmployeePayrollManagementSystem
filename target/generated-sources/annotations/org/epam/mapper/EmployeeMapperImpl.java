package org.epam.mapper;

import javax.annotation.processing.Generated;
import org.epam.dto.EmployeeRequestDTO;
import org.epam.dto.EmployeeResponseDTO;
import org.epam.model.Department;
import org.epam.model.Designation;
import org.epam.model.Employee;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-20T17:47:38+0530",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeResponseDTO toDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();

        employeeResponseDTO.setName( employee.getName() );
        employeeResponseDTO.setId( employee.getId() );
        employeeResponseDTO.setDepartmentName( employeeDepartmentName( employee ) );
        employeeResponseDTO.setDesignationName( employeeDesignationName( employee ) );
        employeeResponseDTO.setEmail( employee.getEmail() );
        if ( employee.getSalary() != null ) {
            employeeResponseDTO.setSalary( employee.getSalary() );
        }

        return employeeResponseDTO;
    }

    @Override
    public Employee toEntity(EmployeeRequestDTO employeeRequestDTO) {
        if ( employeeRequestDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setName( employeeRequestDTO.getName() );
        employee.setEmail( employeeRequestDTO.getEmail() );
        employee.setSalary( employeeRequestDTO.getSalary() );

        return employee;
    }

    private String employeeDepartmentName(Employee employee) {
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        return department.getName();
    }

    private String employeeDesignationName(Employee employee) {
        Designation designation = employee.getDesignation();
        if ( designation == null ) {
            return null;
        }
        return designation.getName();
    }
}
