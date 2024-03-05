package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String studentNumber;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Department department;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "student_grade",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "grade_id"))
	private List<Grade> grades = new ArrayList<>();

	public Student() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public void addGrade(Grade grade) {
		this.grades.add(grade);
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", studentNumber='" + studentNumber + '\'' +
				", department=" + department +
				", grades=" + grades +
				'}';
	}
}
