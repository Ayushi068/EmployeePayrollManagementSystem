package org.epam.controller;

import jakarta.validation.Valid;
import org.epam.dto.DepartmentDTO;
import org.epam.service.DepartmentService;
import org.epam.service.serviceImpl.PayrollCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final PayrollCalculator payrollCalculator;

    @Autowired
    public DepartmentController(DepartmentService departmentService, PayrollCalculator payrollCalculator)
    {
        this.departmentService = departmentService;
        this.payrollCalculator = payrollCalculator;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody @Valid DepartmentDTO departmentDTO)
    {

            return ResponseEntity.ok(departmentService.add(departmentDTO));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> viewDepartmentDetails(@PathVariable("id")  int id)
    {

            return ResponseEntity.ok(departmentService.getById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDTO> deleteDepartment(@PathVariable("id") int id)
    {

            return ResponseEntity.ok(departmentService.deleteById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable("id")  int id,@RequestBody @Valid DepartmentDTO departmentDTO)
    {

            return ResponseEntity.ok(departmentService.update(id,departmentDTO));

    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments()
    {
            return ResponseEntity.ok(departmentService.getAll());

    }

    @GetMapping("/payrolls/{id}")

    public ResponseEntity<Double> getDepartmentPayroll(@PathVariable("id") int departmentID)
    {
        return ResponseEntity.ok(payrollCalculator.calculateDepartmentPayroll(departmentID));
    }

}
