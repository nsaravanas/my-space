package org.myapp.repository;

import org.myapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeCalcRepository extends JpaRepository<Student, Integer> {

}
