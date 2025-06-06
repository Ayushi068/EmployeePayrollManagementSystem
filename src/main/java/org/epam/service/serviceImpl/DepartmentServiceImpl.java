package org.epam.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.epam.dto.DepartmentDTO;
import org.epam.exception.DepartmentNotFoundException;
import org.epam.exception.DesignationNotFoundException;
import org.epam.mapper.DepartmentMapper;
import org.epam.model.Department;
import org.epam.repository.DepartmentRepository;
import org.epam.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;


    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
        initializeHardcodedValues();
    }

    private void initializeHardcodedValues() {
        if(departmentRepository.count()==0)
        {
            add(new DepartmentDTO("Human Resources"));
            add(new DepartmentDTO("IT"));
            add(new DepartmentDTO("Finance"));
        }
    }

    @Override
    @Transactional
    public DepartmentDTO add(DepartmentDTO departmentDto){
        if(departmentRepository.findByName(departmentDto.getName()).isPresent()) throw new IllegalArgumentException("Department already exists");
        Department department = convertToEntity(departmentDto);
        departmentRepository.save(department);
        return convertToDto(department);
    }

    @Override
    @Transactional
    public DepartmentDTO getById(int id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DesignationNotFoundException("Department with this id doesn't exist"));
        return convertToDto(department);
    }


    @Override
    @Transactional
    public List<DepartmentDTO> getAll() {
        List<Department> departments = (List<Department>) departmentRepository.findAll();

        return departments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DepartmentDTO convertToDto(Department department) {
        return departmentMapper.toDTO(department);
    }
    private Department convertToEntity(DepartmentDTO departmentDTO) { return departmentMapper.toEntity(departmentDTO);}

    @Override
    @Transactional
    public DepartmentDTO deleteById(int id) {
        Department deleteDepartment = departmentRepository.findById(id).orElseThrow(()-> new DepartmentNotFoundException("Department with this id doesn't exist"));
        departmentRepository.deleteById(id);
        return convertToDto(deleteDepartment);
    }

    @Override
    public DepartmentDTO update(int id, DepartmentDTO departmentdto) {

        departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException("Department with this id doesn't exist"));
        Department department = convertToEntity(departmentdto);
        departmentRepository.save(department);
        return convertToDto(department);
    }



}