package app;

import domain.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.BookRepository;
import repositories.DepartmentRepository;
import repositories.PassengerRepository;
import repositories.SchoolRepository;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	private final DepartmentRepository departmentRepository;
	private final BookRepository bookRepository;
	private final PassengerRepository passengerRepository;
	private final SchoolRepository schoolRepository;

	public Application(
            DepartmentRepository departmentRepository,
            BookRepository bookRepository,
            PassengerRepository passengerRepository,
			SchoolRepository schoolRepository
    ) {
		this.departmentRepository = departmentRepository;
		this.bookRepository = bookRepository;
        this.passengerRepository = passengerRepository;
        this.schoolRepository = schoolRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// ################# A ###########################
		// a) Create a Bidirectional OneToMany association
		// between Department and Employee.
		Department department = createDepartment();
		// Retrieve the Department from the database and check if the Employee is also
		// automatically retrieved
		Optional<Department> departmentById = departmentRepository.findById(department.getId());
		departmentById.ifPresent(System.out::println);

		// ################# B ###########################
		// b) Create an Optional Unidirectional ManyToOne association between Book and
		// Publisher without using NULL fields in the database
		Book book = createBook();
		Optional<Book> bookById = bookRepository.findById(book.getId());
		bookById.ifPresent(System.out::println);

		// ################# C ###########################
		// c) Create a Unidirectional OneToMany association between Passenger and Flight
		// using a List
		Passenger passenger = createPassenger();
		Optional<Passenger> passengerById = passengerRepository.findById(passenger.getId());
		passengerById.ifPresent(System.out::println);

		// ################# D ###########################
		// d) Create a Unidirectional OneToMany association between School and Student using
		// a Map, where studentId is used as the key for the map.
		School school = createSchool();
		Optional<School> schoolById = schoolRepository.findById(school.getId());
		schoolById.ifPresent(System.out::println);
	}

	private Department createDepartment() {
		Employee employee = new Employee();
		employee.setName("John");
		employee.setEmployeeNumber("123");

		Department department = new Department();
		department.setName("Sales");
		department.addEmployee(employee);

		departmentRepository.save(department);

		return department;
	}

	private Book createBook() {
		Publisher publisher = new Publisher();
		publisher.setName("John Doe");

		Book book = new Book();
		book.setTitle("Java");
		book.setAuthor("John Doe");
		book.setPublisher(publisher);

		bookRepository.save(book);

		return book;
	}

	private Passenger createPassenger() {
		Flight flight1 = new Flight();
		flight1.setFlightNumber("BA123");
		flight1.setFrom("LHR");
		flight1.setTo("JFK");
		flight1.setDate(LocalDate.now());

		Flight flight2 = new Flight();
		flight2.setFlightNumber("BA456");
		flight2.setFrom("JFK");
		flight2.setTo("LHR");
		flight2.setDate(LocalDate.now());

		Passenger passenger = new Passenger();
		passenger.setName("John Doe");
		passenger.addFlight(flight1);
		passenger.addFlight(flight2);

		passengerRepository.save(passenger);

		return passenger;
	}

	private School createSchool() {
		Student student1 = new Student();
		student1.setStudentId("123");
		student1.setFirstName("John");
		student1.setLastName("Doe");

		Student student2 = new Student();
		student2.setStudentId("456");
		student2.setFirstName("Jane");
		student2.setLastName("Doe");

		School school = new School();
		school.setName("School of Computing");
		school.addStudent(student1);
		school.addStudent(student2);

		schoolRepository.save(school);

		return school;
	}
}
