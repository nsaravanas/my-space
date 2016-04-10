package org.myapp.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.myapp.exception.CallParentException;
import org.myapp.model.DetentionType;
import org.myapp.model.OffenseType;
import org.myapp.model.Student;
import org.myapp.model.Time;
import org.myapp.repository.TimeCalcRepository;
import org.springframework.stereotype.Service;

@Service
public class TimeCalcServiceImpl implements TimeCalcService {

	@Inject
	private TimeCalcRepository timeCalcRepository;

	@Override
	@Transactional
	public double calculate(Student student) {
		return calculateDetentionTime(student);
	}

	@Transactional
	private double calculateDetentionTime(Student student) {
		double detentionTime = 0;
		double def = 0.0;
		double undef = 0.0;

		Comparator<OffenseType> defOffenseType = (e1, e2) -> {
			if (e1.getPeriod() > e2.getPeriod()) {
				return 1;
			} else if (e1.getPeriod() < e2.getPeriod()) {
				return -1;
			}
			return 0;
		};

		Comparator<Double> undefOffenseType = (e1, e2) -> {
			if (e1 > e2) {
				return 1;
			} else if (e1 < e2) {
				return -1;
			}
			return 0;
		};

		if (student.getStudentDetail().getDetentionType() == DetentionType.CONCURRENT) {
			Optional<OffenseType> op1 = student.getStudentDetail().getOffenses().stream().max(defOffenseType);
			if (op1.isPresent())
				def = op1.get().getPeriod();
			Optional<Double> op2 = student.getStudentDetail().getUndefOffense().values().stream().max(undefOffenseType);
			if (op2.isPresent())
				undef = op2.get().doubleValue();
			detentionTime = def >= undef ? def : undef;
		} else if (student.getStudentDetail().getDetentionType() == DetentionType.CONSECUTIVE) {
			def = student.getStudentDetail().getOffenses().stream().mapToDouble(e -> e.getPeriod()).sum();
			undef = student.getStudentDetail().getUndefOffense().values().stream().mapToDouble(e -> e).sum();
			detentionTime = def + undef;
		} else {
			throw new IllegalArgumentException(student.getStudentDetail().getDetentionType() + " not supported");
		}

		if (student.getStudentDetail().getTime() == Time.GOOD_TIME) {
			detentionTime -= (detentionTime / 10);
		} else if (student.getStudentDetail().getTime() == Time.BAD_TIME) {
			detentionTime += (detentionTime / 10);
		} else {
			throw new IllegalArgumentException(student.getStudentDetail().getTime() + " not supported");
		}

		if (detentionTime >= 8.0) {
			throw new CallParentException("DETENTION_TIME [" + detentionTime + "] exceeds 8 hours, Call parent.");
		}

		student.getStudentDetail().setDetentionTime(detentionTime);
		this.timeCalcRepository.save(student);
		return student.getStudentDetail().getDetentionTime();
	}

	@Override
	@Transactional
	public List<Student> getAll() {
		return this.timeCalcRepository.findAll();
	}

	@Override
	@Transactional
	public Student getStudent(int id) {
		return this.timeCalcRepository.findOne(id);
	}

}
