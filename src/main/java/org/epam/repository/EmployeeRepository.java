package org.epam.repository;


import org.epam.model.Department;
import org.epam.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
//    @Query("select e from Employee e where e.")
    List<Employee> findByDepartment(Department department);
    Employee findByEmail(String email);
}
