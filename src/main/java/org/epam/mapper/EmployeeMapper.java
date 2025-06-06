package org.epam.mapper;

import org.epam.dto.EmployeeRequestDTO;
import org.epam.dto.EmployeeResponseDTO;
import org.epam.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "departmentName", source = "department.name")
    @Mapping(target = "designationName", source = "designation.name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "salary", source = "salary")
    EmployeeResponseDTO toDTO(Employee employee);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "salary", source = "salary")
    Employee toEntity(EmployeeRequestDTO employeeRequestDTO);

}




