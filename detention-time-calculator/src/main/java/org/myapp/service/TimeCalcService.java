package org.myapp.service;

import java.util.List;

import org.myapp.model.Student;

public interface TimeCalcService {

	double calculate(Student student);

	List<Student> getAll();

	Student getStudent(int id);

}
