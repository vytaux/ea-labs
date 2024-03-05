package customers;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab2PartAApplication {

	private final CustomerService customerService;
	private final ProductService productService;

	public Lab2PartAApplication(CustomerService customerService, ProductService productService) {
		this.customerService = customerService;
		this.productService = productService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Lab2PartAApplication.class, args);
	}

	@PostConstruct
	public void init() {
		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");

		productService.addProduct("Notebook");
	}
}
