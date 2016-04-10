package org.myapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STUDENT")
public class Student {

	@Id
	@Column(name = "STUDENT_ID")
	private int studentId;

	@NotNull
	@Column(name = "STUDENT_NAME", length = 50)
	private String name;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private StudentDetail studentDetail;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StudentDetail getStudentDetail() {
		return studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}

}
