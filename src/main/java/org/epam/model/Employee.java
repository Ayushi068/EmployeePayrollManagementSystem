package org.epam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    @Email(message = "Email is not correct")
    private String email;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "designation_id")
    private Designation designation;

    private Double salary;

    public Employee(String email, String name, Department department, Designation designation, Double salary) {
        this.email = email;
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
    }
}