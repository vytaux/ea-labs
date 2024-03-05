package app;

import domain.Book;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.BookRepository;
import repository.CustomerRepository;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Application implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
		customerRepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
		customerRepository.save(new Customer("Kim", "Bauer", "kim@acme.com"));
		customerRepository.save(new Customer("David", "Palmer", "dpalmer@gmail.com"));
		customerRepository.save(new Customer("Michelle", "Dessler", "mich@hotmail.com"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer by ID
		Optional<Customer> custOpt = customerRepository.findById(1L);
		Customer customer = custOpt.get();
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(customer);
		System.out.println();

		System.out.println("###### Create 3 books, and save them in the database #######");
		Book theCatcherInTheRye = new Book("The Catcher in the Rye", "123", "J.D. Salinger", 10.0);
		Book toKillAMockingBird = new Book("To Kill a Mockingbird", "4321", "some author", 15.0);
		bookRepository.save(theCatcherInTheRye);
		bookRepository.save(toKillAMockingBird);
		bookRepository.save(new Book("1984", "5678", "George Orwell", 20.0));
		System.out.println("###### Retrieve all books from the database and display them in the console ######");
		for (Book book : bookRepository.findAll()) {
			System.out.println(book);
		}
		System.out.println("###### Update a book - The Catcher in the Rye ######");
		theCatcherInTheRye.setTitle("The Catcher in the Rye - updated");
		bookRepository.save(theCatcherInTheRye);
		System.out.println("###### Delete a book - To Kill a Mockingbird ######");
		bookRepository.delete(toKillAMockingBird);
		System.out.println("###### Retrieve all books from the database again and display them in the console ######");
		for (Book book : bookRepository.findAll()) {
			System.out.println(book);
		}
	}

}
