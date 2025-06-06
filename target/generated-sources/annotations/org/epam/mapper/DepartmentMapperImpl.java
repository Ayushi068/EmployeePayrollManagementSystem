package org.epam.mapper;

import javax.annotation.processing.Generated;
import org.epam.dto.DepartmentDTO;
import org.epam.model.Department;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-20T17:47:38+0530",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDTO toDTO(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setName( department.getName() );
        departmentDTO.setId( department.getId() );

        return departmentDTO;
    }

    @Override
    public Department toEntity(DepartmentDTO departmentDTO) {
        if ( departmentDTO == null ) {
            return null;
        }

        Department department = new Department();

        department.setName( departmentDTO.getName() );
        if ( departmentDTO.getId() != null ) {
            department.setId( departmentDTO.getId() );
        }

        return department;
    }
}
