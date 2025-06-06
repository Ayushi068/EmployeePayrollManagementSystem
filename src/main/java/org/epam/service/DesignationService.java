package org.epam.service;

import org.epam.dto.DesignationDTO;

import java.util.List;

public interface DesignationService {
    DesignationDTO add(DesignationDTO designationDto);
    DesignationDTO getById(int id);
    List<DesignationDTO> getAll();
    DesignationDTO deleteById(int id);
    DesignationDTO update(int id, DesignationDTO designationDto);
}
