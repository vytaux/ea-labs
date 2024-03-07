package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private StudentRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

        // create student
		Student student = new Student(101,"John doe", "0622341678");
		Address address = new Address("Some strreet", "Fairfield", "11243");
		student.setAddress(address);
		customerRepository.save(student);

		student = new Student(109,"John Jones", "0624321234");
		address = new Address("4th st", "Fairfield", "11243");
		student.setAddress(address);
		customerRepository.save(student);

		student = new Student(66,"James Johnson", "068633452");
		address = new Address("4th st", "Soem city", "34522");
		student.setAddress(address);
		customerRepository.save(student);

		System.out.println("######## Find the students with a certain name = John doe ########");
		List<Student> studentsName = customerRepository.findByName("John doe");
		studentsName.forEach(System.out::println);

		System.out.println("######## Find the students with a certain phone = 0622341678 ########");
		List<Student> studentsPhone = customerRepository.findByPhone("0622341678");
		studentsPhone.forEach(System.out::println);

		System.out.println("######## Find the Students from a certain city = Fairfield using @query ########");
		List<Student> studentsCity = customerRepository.findByAddressCity("Fairfield");
		studentsCity.forEach(System.out::println);

		Grade grade = new Grade("Math", "A");
		student = new Student(773,"Some Johnson", "06412452");
		address = new Address("55th st", "sajdas city", "34544");
		student.setAddress(address);
		student.addGrade(grade);
		customerRepository.save(student);

		grade = new Grade("Computer Science", "A+");
		student = new Student(124, "Not James Johnson", "912490839084");
		address = new Address("42nd st", "Another city", "37711");
		student.setAddress(address);
		student.addGrade(grade);
		customerRepository.save(student);

		System.out.println("######## Find the Students that took a certain course with a given name = Math ########");
		List<Student> studentsCourse = customerRepository.findByGradesCourseName("Math");
		studentsCourse.forEach(System.out::println);

		System.out.println("######## Find the Students with an A+ for a certain course name = Computer Science ########");
		List<Student> studentsGrade = customerRepository.findByGradesCourseNameAndGrade("Computer Science", "A+");
		studentsGrade.forEach(System.out::println);
	}
}
