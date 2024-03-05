package app;

import domain.Course;
import domain.Department;
import domain.Grade;
import domain.Student;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.StudentRepository;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	private final StudentRepository studentRepository;

	public Application(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		prepareData();

		System.out.println("Get all students from a certain department");
		List<Student> csDeptStudents = studentRepository.findByDepartmentName("Computer Science");
		System.out.println("Students from Computer Science department: " + csDeptStudents);

		System.out.println("Get all students who took a course with a certain name.");
		List<Student> mathStudents = studentRepository.findByGradesCourseName("Math");
		System.out.println("Students who took Math course: " + mathStudents);
	}

	private void prepareData() {
		Department department = new Department();
		department.setName("Computer Science");

		Department department1 = new Department();
		department1.setName("Physics");

		Course course = new Course();
		course.setName("Math");

		Course course1 = new Course();
		course1.setName("Physics");

		Grade grade = new Grade();
		grade.setCourse(course);
		grade.setGrade(5);

		Grade grade1 = new Grade();
		grade1.setCourse(course1);
		grade1.setGrade(4.1);

		Student student = new Student();
		student.setName("John Doe");
		student.setStudentNumber("123456");
		student.addGrade(grade);
		student.addGrade(grade1);
		student.setDepartment(department);

		Grade grade2 = new Grade();
		grade2.setCourse(course);
		grade2.setGrade(5);

		Student student1 = new Student();
		student1.setName("Frank Martin");
		student1.setStudentNumber("654321");
		student1.addGrade(grade2);
		student1.setDepartment(department1);

		studentRepository.save(student);
		studentRepository.save(student1);
	}
}
