package com.nagarro.javaadvance.assignment5rest.controller;

import com.nagarro.javaadvance.assignment5rest.exception.ResourceNotFoundException;
import com.nagarro.javaadvance.assignment5rest.model.Employee;
import com.nagarro.javaadvance.assignment5rest.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeCode)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeCode)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeCode));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeCode,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeCode)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeCode));

        employee.setFullName(employeeDetails.getFullName());
        employee.setLocation(employeeDetails.getLocation());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDateOfBirth(employeeDetails.getDateOfBirth());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeCode)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeCode)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeCode));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
