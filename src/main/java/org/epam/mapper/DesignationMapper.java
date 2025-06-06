package org.epam.mapper;

import org.epam.dto.DesignationDTO;
import org.epam.model.Designation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DesignationMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id",source = "id")
    @Mapping(target = "minSalary", source = "minSalary")
    @Mapping(target = "maxSalary",source = "maxSalary")
    DesignationDTO toDTO(Designation designation);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "minSalary", source = "minSalary")
    @Mapping(target = "maxSalary",source = "maxSalary")
    Designation toEntity(DesignationDTO designationDTO);

}
