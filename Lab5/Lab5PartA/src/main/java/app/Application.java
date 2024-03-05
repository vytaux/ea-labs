package app;

import domain.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.OrderRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	private final OrderRepository orderRepository;

	public Application(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
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

		CD p2 = new CD();
		p2.setName("David Bowie");
		p2.setPrice(17.5);
		p2.setArtist("David Bowie");

		DVD p3 = new DVD();
		p3.setName("The Godfather");
		p3.setPrice(19.99);
		p3.setGenre("Drama");

		Order o1 = new Order("234743", "12/10/06", "open");
		o1.addOrderLine(new OrderLine(1, p1));
		o1.addOrderLine(new OrderLine(2, p2));
		o1.addOrderLine(new OrderLine(1, p3));

		Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
				"New york", "43221");
		c1.addOrder(o1);
		o1.setCustomer(c1);

		orderRepository.save(o1);

		Order o = orderRepository.findByOrderNumber("234743");
		printOrder(o);

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
