package org.epam.repository;


import org.epam.model.Designation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesignationRepository extends CrudRepository<Designation,Integer> {
    Optional<Designation> findByName(String departmentName);
}

