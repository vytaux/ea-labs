package app;

import domain.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.AddressRepository;
import repositories.CDRepository;
import repositories.CustomerRepository;
import repositories.OrderRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final CDRepository cdRepository;
	private final AddressRepository addressRepository;

	public Application(
			OrderRepository orderRepository,
			CustomerRepository customerRepository,
			CDRepository cdRepository,
			AddressRepository addressRepository
	) {
		this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.cdRepository = cdRepository;
		this.addressRepository = addressRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Book p1 = new Book();
		p1.setName("The Hitchhiker's Guide to the Galaxy");
		p1.setPrice(12.5);
		p1.setIsbn("1234-5678-910");
		p1.setProductNumber("1234");

		CD p2 = new CD();
		p2.setName("U2");
		p2.setPrice(9.99);
		p2.setArtist("U2");
		p2.setProductNumber("5678");

		CD cd = new CD();
		cd.setName("Eternail Hails......");
		cd.setPrice(35);
		cd.setArtist("Darkthrone");
		cd.setProductNumber("9123");
		cdRepository.save(cd);

		DVD p3 = new DVD();
		p3.setName("The Godfather");
		p3.setPrice(19.99);
		p3.setGenre("Drama");

		Order o1 = new Order("234743", "12/10/06", "closed");
		o1.addOrderLine(new OrderLine(1, p1));
		o1.addOrderLine(new OrderLine(2, p2));
		o1.addOrderLine(new OrderLine(1, p3));

		Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
				"Amsterdam", "43221", "Canada");
		c1.addOrder(o1);
		o1.setCustomer(c1);

		orderRepository.save(o1);

		Customer c2 = new Customer("John", "Something", "Not mainstreet 8",
				"some state", "12432", "USA");

		customerRepository.save(c2);

//		Order o = orderRepository.findByOrderNumber("234743");
//		printOrder(o);


		// Queries

		System.out.println("########################## ALL CUSTOMERS ##########################");
		List<Customer> customers = customerRepository.findAll();
		System.out.println("Customers: " + customers);

		System.out.println("Give all CD’s from U2 with a price smaller than 10 euro");
		List<CD> cds = cdRepository.findByArtistAndPriceLessThan("U2", 10);
		System.out.println("CDs: " + cds);

		// Named query

		System.out.println("Give all customers from the USA using a named query");
		List<Customer> customersFromUSA = customerRepository.findAllCustomersFromUSA();
		System.out.println("Customers from USA: " + customersFromUSA);

		System.out.println("Give all CD’s from a certain artist");
		List<CD> cdsByArtist = cdRepository.findByArtist("Darkthrone");
		System.out.println("CDs by Darkthrone: " + cdsByArtist);

		// JPQL query

		System.out.println("Give all order numbers with status closed");
		List<String> orderNumbers = orderRepository.findByOrderNumbersOfOrdersWithStatusClosed();
		System.out.println("Order numbers with status closed: " + orderNumbers);

		System.out.println("Give the first and lastnames of all customers who live in Amsterdam");
		List<String[]> customerNames = customerRepository.findFirstAndLastNamesOfCustomersFromAmsterdam();
		System.out.println("Customer first and lastNames from Amsterdam");
		for (String[] names : customerNames) {
			System.out.println(Arrays.toString(names));
		}

		System.out.println("Give the ordernumbers of all orders from customers who live in a" +
				" certain city (city is a parameter)."
		);
		List<String> orderNumbersByCity = orderRepository.findOrderNumbersByCity("Amsterdam");
		System.out.println("Order numbers by city: " + orderNumbersByCity);

		System.out.println("Give all CD’s from a certain artist with a price bigger than " +
				"a certain amount (artist and amount are parameter2).");
		List<CD> cdsByArtistAndPrice = cdRepository.findByArtistAndPriceGreaterThan("Darkthrone", 20);
		System.out.println("CDs by artist darkthrone with price bigger than 20: " + cdsByArtistAndPrice);

		// Native query

		System.out.println("Give all addresses in Amsterdam.");
		List<Address> addresses = addressRepository.findAllAddressesInAmsterdam();
		System.out.println("Addresses in Amsterdam: " + addresses);

		System.out.println("Give all CD’s from U2");
		List<CD> cdsByU2 = cdRepository.findByArtistU2();
		System.out.println("CDs by U2: " + cdsByU2);
	}


	public static void printOrder(Order order) {
		System.out.println("Order with orderNumber: " + order.getOrderNumber());
		System.out.println("Order date: " + order.getDate());
		System.out.println("Order status: " + order.getStatus());
		Customer cust = order.getCustomer();
		System.out.println("Customer: " + cust.getFirstName() + " "
				+ cust.getLastName());
		for (OrderLine orderline : order.getOrderLines()) {
			System.out.println("Order line: quantity= "
					+ orderline.getQuantity());
			Product product = orderline.getProduct();
			System.out.println("Product: " + product.getName() + " "
					+ " " + product.getPrice());
		}
	}
}
