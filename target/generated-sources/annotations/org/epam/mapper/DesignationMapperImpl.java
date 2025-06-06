package org.epam.mapper;

import javax.annotation.processing.Generated;
import org.epam.dto.DesignationDTO;
import org.epam.model.Designation;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-20T17:47:38+0530",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class DesignationMapperImpl implements DesignationMapper {

    @Override
    public DesignationDTO toDTO(Designation designation) {
        if ( designation == null ) {
            return null;
        }

        String name = null;
        Double minSalary = null;
        Double maxSalary = null;

        name = designation.getName();
        minSalary = designation.getMinSalary();
        maxSalary = designation.getMaxSalary();

        DesignationDTO designationDTO = new DesignationDTO( name, minSalary, maxSalary );

        designationDTO.setId( designation.getId() );

        return designationDTO;
    }

    @Override
    public Designation toEntity(DesignationDTO designationDTO) {
        if ( designationDTO == null ) {
            return null;
        }

        Designation designation = new Designation();

        designation.setName( designationDTO.getName() );
        designation.setMinSalary( designationDTO.getMinSalary() );
        designation.setMaxSalary( designationDTO.getMaxSalary() );
        if ( designationDTO.getId() != null ) {
            designation.setId( designationDTO.getId() );
        }

        return designation;
    }
}
