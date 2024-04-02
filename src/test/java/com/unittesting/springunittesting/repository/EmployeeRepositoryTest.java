package com.unittesting.springunittesting.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.unittesting.springunittesting.entity.Employee;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EmployeeRepositoryTest {

  @Autowired
  private EmployeeRepository employeeRepository;
  private Employee employee;

  @BeforeEach
  void setUp() {
    // given: setup object or precondition
    employee = Employee.builder()
        .firstName("My")
        .lastName("Nguyen")
        .email("nguyentramy.se@gmail.com")
        .departmentCode("G55")
        .build();
  }

  /**
   * JUnit Test for save employee operation
   */
  @Test
  @DisplayName("JUnit Test for save employee operation")
  void givenEmployeeObject_whenSave_thenReturnSaveEmployee() {
    // when: action of behavior that we are going to test
    Employee saveEmployee = employeeRepository.save(employee);

    // then: verify the output
    assertThat(saveEmployee).isNotNull();
    assertThat(saveEmployee.getId()).isPositive();
  }

  /**
   * JUnit Test for get Employee List operation
   */
  @Test
  void givenListEmployee_whenFindAll_thenReturnListEmployee() {
    // given: setup project or precondition
    Employee employee1 = Employee.builder()
        .firstName("My")
        .lastName("Nguyen")
        .email("nguyentramy.se@gmail.com")
        .departmentCode("G55")
        .build();

    Employee employee2 = Employee.builder()
        .firstName("Hung")
        .lastName("Hoang")
        .email("hoangvuhung.se@gmail.com")
        .departmentCode("G55")
        .build();

    employeeRepository.save(employee1);
    employeeRepository.save(employee2);

    // when
    List<Employee> ls = employeeRepository.findAll();

    // then
    assertThat(ls).hasSize(2);
  }

}