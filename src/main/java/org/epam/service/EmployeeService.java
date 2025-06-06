package org.epam.service;

import org.epam.dto.EmployeeRequestDTO;
import org.epam.dto.EmployeeResponseDTO;
import java.util.List;


public interface EmployeeService {
    EmployeeResponseDTO add(EmployeeRequestDTO employeeDto);
    EmployeeResponseDTO findById(int id);
    EmployeeResponseDTO deleteById(int id);
    EmployeeResponseDTO update(int id, EmployeeRequestDTO employeeDto);
    List<EmployeeResponseDTO> getAllByDepartment(int departmentId);
}
