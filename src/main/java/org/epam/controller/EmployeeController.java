package org.epam.controller;

import jakarta.validation.Valid;
import org.epam.dto.EmployeeRequestDTO;
import org.epam.dto.EmployeeResponseDTO;
import org.epam.service.*;
import org.epam.service.serviceImpl.PayrollCalculator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    private final PayrollCalculator payrollCalculator;
    private final EmployeeService employeeService;


    public EmployeeController(PayrollCalculator payrollCalculator,
                              EmployeeService employeeService) {
        this.payrollCalculator = payrollCalculator;
        this.employeeService = employeeService;

    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> addEmployee(@RequestBody @Valid EmployeeRequestDTO employeeDTO) {
            return ResponseEntity.ok(employeeService.add(employeeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO>viewEmployeeDetails(@PathVariable("id")  int employeeId) {

            return ResponseEntity.ok(employeeService.findById(employeeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable("id")  int employeeId, @RequestBody @Valid EmployeeRequestDTO employeeDto) {

            return ResponseEntity.ok(employeeService.update(employeeId, employeeDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> deleteEmployee(@PathVariable("id")  int employeeId) {
            return ResponseEntity.ok( employeeService.deleteById(employeeId));


    }

    @GetMapping("/departments/{departmentId}")
    public ResponseEntity<List<EmployeeResponseDTO>> getByDepartment(@PathVariable("departmentId")  int departmentId) {

            return ResponseEntity.ok(employeeService.getAllByDepartment(departmentId));

    }

    @GetMapping("/payrolls/{id}")
    public ResponseEntity<String> calculatePayroll(@PathVariable("id") int employeeId) {

            EmployeeResponseDTO employeeDto = employeeService.findById(employeeId);
            double payrollAmount = payrollCalculator.calculate(employeeDto);
            String payrollDetails = generatePayrollDetails(employeeDto, payrollAmount);
            return ResponseEntity.ok(payrollDetails);

    }

    private String generatePayrollDetails(EmployeeResponseDTO employee, double payrollAmount) {
        return "Payroll Details for " + employee.getName() + ":" +
                "\nBase Salary:      $" + employee.getSalary() +
                "\nBonus (10%):      $" + employee.getSalary() * 0.10 +
                "\nTax (20%):        -$" + employee.getSalary() * 0.20 +
                "\nFood Allowance:   $300.0" +
                "\n------------------------" +
                "\nNet Salary:       $" + payrollAmount;
    }

}