package com.nagarro.javaadvance.assignment5rest.repository;

import com.nagarro.javaadvance.assignment5rest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
