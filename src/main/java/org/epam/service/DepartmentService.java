package org.epam.service;

import org.epam.dto.DepartmentDTO;


import java.util.List;



public interface DepartmentService {
    DepartmentDTO add(DepartmentDTO departmentdto);
    DepartmentDTO getById(int id);
    List<DepartmentDTO> getAll();
    DepartmentDTO deleteById(int id);
    DepartmentDTO update(int id,DepartmentDTO departmentDto);
}

