package com.unittesting.springunittesting.service;

import com.unittesting.springunittesting.entity.Employee;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
  Employee saveEmployee(Employee employee) throws UserPrincipalNotFoundException;
  List<Employee> getAllEmployees();
  Optional<Employee> getEmployeeById(long id);
  Employee updateEmployee(Employee updatedEmployee);
  void deleteEmployee(long id);
}
