package domain;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;


@Entity
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(cascade = CascadeType.PERSIST)
	@MapKey(name = "studentId")
	private Map<String, Student> students = new HashMap<>();

	public School() {
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

	public Map<String, Student> getStudents() {
		return students;
	}

	public void setStudents(Map<String, Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		students.put(student.getStudentId(), student);
	}

	@Override
	public String toString() {
		return "School{" +
				"id=" + id +
				", name='" + name + '\'' +
				", students=" + students +
				'}';
	}
}
