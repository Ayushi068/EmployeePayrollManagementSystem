package org.epam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponseDTO {

    private int id;
    private String name;
    private String departmentName;
    private String designationName;
    private double salary;
    private String email;
}
