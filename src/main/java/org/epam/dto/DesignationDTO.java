package org.epam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class DesignationDTO {
    private Integer id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z]+(?:\\s[A-Za-z]+)*$", message = "Name must contain only letters and spaces")
    private String name;

    @NotNull(message = "Minimum Salary cannot be null")
    @Positive(message = "Minimum Salary must be greater than 0")
    private Double minSalary;

    @NotNull(message = "Maximum Salary cannot be null")
    @Positive(message = "Maximum Salary must be greater than 0")
    private Double maxSalary;

    public DesignationDTO(String name, Double minSalary, Double maxSalary) {
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }
}
