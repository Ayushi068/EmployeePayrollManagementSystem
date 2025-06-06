package org.epam.repository;

import org.epam.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department,Integer> {
    Optional<Department> findByName(String name);
}