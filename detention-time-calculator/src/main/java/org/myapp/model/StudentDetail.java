package org.myapp.model;

import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STUDENT_DETAIL")
public class StudentDetail {

	@Id
	@GeneratedValue
	@Column(name = "STUDENT_DETAIL_ID")
	private int studentDetailId;

	@Column(name = "OFFENSES")
	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = OffenseType.class)
	private List<OffenseType> offenses;

	@Column(name = "DETENTION_TIME")
	private double detentionTime;

	@NotNull
	@Column(name = "DETENTION_TYPE")
	@Enumerated(EnumType.STRING)
	private DetentionType detentionType;

	@NotNull
	@Column(name = "TIME")
	@Enumerated(EnumType.STRING)
	private Time time;

	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name = "name")
	@Column(name = "UNDEF_OFFENSE")
	@CollectionTable(name = "UNDEF_OFFENSE", joinColumns = @JoinColumn(name = "UNDEF_OFFENSE_ID") )
	private Map<String, Double> undefOffense;

	public List<OffenseType> getOffenses() {
		return offenses;
	}

	public void setOffenses(List<OffenseType> offenses) {
		this.offenses = offenses;
	}

	public double getDetentionTime() {
		return detentionTime;
	}

	public void setDetentionTime(double detentionTime) {
		this.detentionTime = detentionTime;
	}

	public DetentionType getDetentionType() {
		return detentionType;
	}

	public void setDetentionType(DetentionType detentionType) {
		this.detentionType = detentionType;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getStudentDetailId() {
		return studentDetailId;
	}

	public void setStudentDetailId(int studentDetailId) {
		this.studentDetailId = studentDetailId;
	}

	public Map<String, Double> getUndefOffense() {
		return undefOffense;
	}

	public void setUndefOffense(Map<String, Double> undefOffense) {
		this.undefOffense = undefOffense;
	}

}
