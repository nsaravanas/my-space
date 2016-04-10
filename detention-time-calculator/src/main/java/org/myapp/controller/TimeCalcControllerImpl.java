package org.myapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.myapp.model.DetentionType;
import org.myapp.model.OffenseType;
import org.myapp.model.Student;
import org.myapp.model.StudentDetail;
import org.myapp.model.Time;
import org.myapp.service.TimeCalcService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TimeCalcControllerImpl implements TimeCalcController {

	@Inject
	private TimeCalcService timeCalcService;

	@Override
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public double calculate(@RequestBody @Valid Student student) {
		return this.timeCalcService.calculate(student);
	}

	@Override
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		final Map<String, Object> model = new HashMap<>();
		return new ModelAndView("index", model);
	}

	@Override
	@RequestMapping(path = "/students", method = RequestMethod.GET)
	public List<Student> getAll() {
		return this.timeCalcService.getAll();
	}

	@Override
	@RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
	public Student getStudent(@PathVariable int id) {
		return this.timeCalcService.getStudent(id);
	}

	@RequestMapping(path = "/sample", method = RequestMethod.GET)
	public Student sampleJSON() {
		int studentId = 100;
		String name = "Saravanakumar";
		List<OffenseType> offenses = new ArrayList<>();
		offenses.add(OffenseType.FIGHTING);
		offenses.add(OffenseType.HOMEWORK_NOT_DONE);
		offenses.add(OffenseType.LYING);
		DetentionType detentionType = DetentionType.CONCURRENT;
		Time time = Time.GOOD_TIME;
		StudentDetail studentDetail = new StudentDetail();
		studentDetail.setOffenses(offenses);
		studentDetail.setDetentionType(detentionType);
		studentDetail.setTime(time);
		Map<String, Double> undefOffense = new HashMap<>();
		undefOffense.put("HARASEMENT", 5.0);
		undefOffense.put("TEASING", 4.0);
		undefOffense.put("SLEEPING", 2.0);
		studentDetail.setUndefOffense(undefOffense);
		Student student = new Student();
		student.setStudentId(studentId);
		student.setName(name);
		student.setStudentDetail(studentDetail);
		return student;
	}

	@Override
	@RequestMapping(path = "/error", method = RequestMethod.GET)
	@ExceptionHandler(value = Exception.class)
	public ModelAndView globalExceptionHandler(Exception e) {
		final Map<String, Object> model = new HashMap<>();
		model.put("exception", e);
		e.printStackTrace();
		return new ModelAndView("error", model);
	}
}
