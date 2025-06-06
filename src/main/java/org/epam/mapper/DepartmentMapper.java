package org.epam.mapper;

import org.epam.dto.DepartmentDTO;
import org.epam.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    DepartmentDTO toDTO(Department department);

    @Mapping(target = "name", source = "name")
    Department toEntity(DepartmentDTO departmentDTO);
}