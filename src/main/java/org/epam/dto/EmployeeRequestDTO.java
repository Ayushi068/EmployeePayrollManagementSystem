package org.epam.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class EmployeeRequestDTO {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z]+(?:\\s[A-Za-z]+)*$", message = "Name must contain only letters and spaces")
    private String name;

    @NotNull(message = "Department ID cannot be null")
    @Positive(message = "Department ID must be greater than 0")
    private int departmentId;

    @NotNull(message = "Designation ID cannot be null")
    @Positive(message = "Designation ID must be greater than 0")
    private int designationId;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be greater than 0")
    private double salary;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email is not correct")
    private String email;
}
