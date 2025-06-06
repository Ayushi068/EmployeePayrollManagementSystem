package org.epam.controller;

import jakarta.validation.Valid;
import org.epam.dto.DesignationDTO;
import org.epam.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designations")
public class DesignationController {

    private final DesignationService designationService;

    @Autowired

    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }

    @PostMapping
    public ResponseEntity<DesignationDTO> addDesignation(@RequestBody @Valid DesignationDTO designationDTO)
    {
            return ResponseEntity.ok(designationService.add(designationDTO));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DesignationDTO> viewDesignationDetails(@PathVariable("id")  int id)
    {
            return ResponseEntity.ok(designationService.getById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DesignationDTO> deleteDesignation(@PathVariable("id")  int id)
    {

            return ResponseEntity.ok(designationService.deleteById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<DesignationDTO> updateDesignation(@PathVariable("id")  int id,@RequestBody @Valid DesignationDTO designationDTO)
    {

            return ResponseEntity.ok(designationService.update(id,designationDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DesignationDTO>> getAllDesignation()
    {
            return ResponseEntity.ok(designationService.getAll());
    }

}
