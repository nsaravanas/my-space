package org.myapp.controller;

import java.util.List;

import org.myapp.model.Student;
import org.springframework.web.servlet.ModelAndView;

public interface TimeCalcController {
	
	double calculate(Student student);

	ModelAndView home();

	List<Student> getAll();

	Student getStudent(int id);

	ModelAndView globalExceptionHandler(Exception e);
}
