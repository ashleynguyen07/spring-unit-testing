package com.unittesting.springunittesting.service;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.unittesting.springunittesting.entity.Employee;
import com.unittesting.springunittesting.repository.EmployeeRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  private Employee employee;

  @BeforeEach
  void setUp() {
    employee = Employee.builder()
        .id(1L)
        .firstName("My")
        .lastName("Nguyen")
        .email("nguyentramy.se@gmail.com")
        .departmentCode("G55")
        .build();
  }

  /**
   * JUnit Test saveEmployee method
   */
  @Test
  void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
    // given
    given(employeeRepository.findByEmail(employee.getEmail()))
        .willReturn(Optional.empty());

    given(employeeRepository.save(employee))
        .willReturn(employee);

    // when
    Employee savedEmployee = employeeService.saveEmployee(employee);

    // then
    assertThat(savedEmployee).isNotNull();
    assertThat(savedEmployee.getId()).isPositive();
  }
  /**
   * JUnit test saveEmployee method which throws Exception
   * */
  @Test
  void givenExistEmail_whenSaveEmployee_thenThrowsException() {
    // given
    given(employeeRepository.findByEmail(employee.getEmail()))
        .willReturn(Optional.of(employee));

    // when
    assertThrows(IllegalStateException.class, () -> {
      employeeService.saveEmployee(employee);
    });

    // then
    verify(employeeRepository, never()).save(any(Employee.class));
  }

  /**
   * JUnit Test getAllEmployee method
   * */
  @Test
  void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeeList() {
    // given
    Employee employee1 = Employee.builder()
        .id(2L)
        .firstName("Hung")
        .lastName("Hoang")
        .email("hoanghung@gmail.com")
        .departmentCode("FI")
        .build();

    given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

    // when
    List<Employee> ls = employeeService.getAllEmployees();

    // then
    assertThat(ls).hasSize(2);
  }

  /**
   * JUnit test getAllEmployee which return empty list
   * */
  @Test
  void givenEmployeeList_whenGetAllEmployees_thenReturnEmptyList() {
    // given
    given(employeeRepository.findAll()).willReturn(Collections.emptyList());

    // when
    List<Employee> ls = employeeService.getAllEmployees();

    // then
    assertThat(ls).isEmpty();
  }

  /**
   * JUnit test getEmployeeId method
   * */
  @Test
  void givenEmployeeId_whenGetEmployeeById_thenReturnEmployee() {
    // given
    given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));

    // when
    Employee optionalEmployee = employeeService.getEmployeeById(employee.getId()).get();

    // then
    assertThat(optionalEmployee).isNotNull();
  }

  /**
   * JUnit test updateEmployee method
   * */
  @Test
  void updateEmployee() {
    // given
    given(employeeRepository.save(employee)).willReturn(employee);
    employee.setEmail("belu@gmail.com");
    employee.setFirstName("My");

    // when
    Employee updatedEmployee = employeeService.updateEmployee(employee);

    // then
    assertThat(updatedEmployee.getEmail()).isEqualTo("belu@gmail.com");
    assertThat(updatedEmployee.getFirstName()).isEqualTo("My");
  }

  /**
   * JUnit test deleteEmployee method
   * */
  @Test
  void deleteEmployee() {
    // given
    willDoNothing().given(employeeRepository).deleteById(employee.getId());

    // when
    employeeService.deleteEmployee(employee.getId());

    // then
    verify(employeeRepository, times(1)).deleteById(employee.getId());
  }
}