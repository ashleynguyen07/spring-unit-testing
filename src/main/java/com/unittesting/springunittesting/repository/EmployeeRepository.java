package com.unittesting.springunittesting.repository;

import com.unittesting.springunittesting.entity.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Optional<Employee> findByEmail(String email);
  @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
  Employee findByJPQL(String firstName, String lastName);
  @Query(value= "select * from employees e where e.first_name = ?1 and e.last_name = ?2", nativeQuery = true )
  Employee findByNativeSQL(String firstName, String lastName);
}
