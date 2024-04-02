package com.unittesting.springunittesting.service;

import com.unittesting.springunittesting.entity.Employee;
import com.unittesting.springunittesting.repository.EmployeeRepository;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

  private final EmployeeRepository employeeRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Employee saveEmployee(Employee employee) {
    Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
    if (savedEmployee.isPresent()) {
      throw new IllegalStateException("Employee already exist with given email: " + employee.getEmail());
    }
    return employeeRepository.save(employee);
  }

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public Optional<Employee> getEmployeeById(long id) {
    return employeeRepository.findById(id);
  }

  @Override
  public Employee updateEmployee(Employee updatedEmployee) {
    return employeeRepository.save(updatedEmployee);
  }

  @Override
  public void deleteEmployee(long id) {
    employeeRepository.deleteById(id);
  }
}
