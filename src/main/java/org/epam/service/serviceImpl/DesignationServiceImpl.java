package org.epam.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.epam.dto.DesignationDTO;
import org.epam.exception.DesignationNotFoundException;
import org.epam.mapper.DesignationMapper;
import org.epam.model.Designation;
import org.epam.repository.DesignationRepository;
import org.epam.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignationServiceImpl implements DesignationService {
    private final DesignationRepository designationRepository;
    private final ObjectMapper objectMapper;
    private final DesignationMapper designationMapper;

    @Autowired
    public DesignationServiceImpl(DesignationRepository designationRepository, ObjectMapper objectMapper, DesignationMapper designationMapper) {
        this.designationRepository = designationRepository;
        this.objectMapper = objectMapper;
        this.designationMapper = designationMapper;
        initializeDesignations();
    }

    private void initializeDesignations(){
        if(designationRepository.count()==0)
        {
            add(new DesignationDTO("Manager", 600000.0, 800000.0));
            add(new DesignationDTO("Intern", 300000.0, 400000.0));
            add(new DesignationDTO("Developer", 900000.0, 1000000.0));
        }
    }

    @Override
    @Transactional
    public DesignationDTO add(DesignationDTO designationDto) {
        if(designationDto.getMaxSalary()<designationDto.getMinSalary()) throw new IllegalArgumentException("Maximum salary cannot be less than Minimum Salary");
        boolean IsDesignation = designationRepository.findByName(designationDto.getName()).isPresent();
        if(IsDesignation) throw  new IllegalArgumentException("department already exists");
        Designation saveDesignation = designationMapper.toEntity(designationDto);
        designationRepository.save(saveDesignation);
        return designationMapper.toDTO(saveDesignation);
    }

    @Override
    @Transactional
    public DesignationDTO getById(int id) {
        Designation designation = designationRepository.findById(id).orElseThrow(()-> new DesignationNotFoundException("Invalid Designation ID"));
        return designationMapper.toDTO(designation);
    }

    @Override
    @Transactional
    public List<DesignationDTO> getAll() {
        List<Designation> designations = (List<Designation>) designationRepository.findAll();
        return designations.stream().map(designationMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public DesignationDTO deleteById(int id) {

        Designation designation = designationRepository.findById(id).orElseThrow(()-> new DesignationNotFoundException("Invalid Designation ID"));
        designationRepository.deleteById(id);
        return designationMapper.toDTO(designation);
    }

    @Override
    @Transactional
    public DesignationDTO update(int id, DesignationDTO designationDto) {
        if(!(designationRepository.existsById(id))) throw new IllegalArgumentException("Department already Exists");
        Designation designation = designationMapper.toEntity(designationDto);
        designationRepository.save(designation);
        return designationMapper.toDTO(designation);

    }
}