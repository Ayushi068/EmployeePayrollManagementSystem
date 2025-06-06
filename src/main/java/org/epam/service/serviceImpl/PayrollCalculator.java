package org.epam.service.serviceImpl;

import org.epam.dto.EmployeeResponseDTO;
import org.epam.exception.DepartmentNotFoundException;
import org.epam.exception.EmployeeNotFoundException;
import org.epam.model.Employee;
import org.epam.repository.DepartmentRepository;
import org.epam.service.EmployeeService;
import org.epam.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PayrollCalculator {
    private static final double TAX_RATE = 0.20;
    private static final double BONUS_RATE = 0.10;
    private static final double FOOD_ALLOWANCE = 300.0;
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;


    @Autowired
    public PayrollCalculator(EmployeeService employeeService, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public double calculate(EmployeeResponseDTO employeeResponsedto) {
        Employee employee = employeeRepository.findById(employeeResponsedto.getId()).orElseThrow(()->new EmployeeNotFoundException("Invalid Employee ID"));

        double baseSalary = employee.getSalary();
        double bonus = baseSalary * BONUS_RATE;
        double tax = baseSalary * TAX_RATE;
        return baseSalary + bonus - tax + FOOD_ALLOWANCE;
    }

    public double calculateDepartmentPayroll(int departmentID)
    {
        if(!(departmentRepository.existsById(departmentID))) throw new DepartmentNotFoundException("Department Id is invalid");
        List<EmployeeResponseDTO> employeeList = employeeService.getAllByDepartment(departmentID);
        return employeeList.stream()
                .mapToDouble(this::calculate)
                .sum();
    }
}
