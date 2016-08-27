package org.example.spring.hibernate.repository;

import java.util.List;

import org.example.spring.hibernate.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByHobbiesIn(List<String> hobbies);

	// @Query("select e from employee e where e.hobbies in ?1")
	// List<Employee> getEmployees(List<String> hobbies);

}
