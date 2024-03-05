package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SupplierRepository supplierRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerRepository.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		System.out.println(customerRepository.getCustomer(101));
		System.out.println(customerRepository.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.getAllCustomers());

		System.out.println();
		System.out.println("################# PRODUCTS ####################");
		// save product
		Product product = new Product(1001, "Laptop", 1000);
		Supplier supplier = new Supplier(1001, "Acme", "0622341678");
		product.setSupplier(supplier);
		productRepository.save(product);
		// findByProductNumber
		Product byProductNumber = productRepository.findByProductNumber(1001);
		System.out.println("Product by product number 1001: " + byProductNumber);
		// getAllProducts
		List<Product> allProducts = productRepository.getAllProducts();
		System.out.println("-----------All products ----------------");
		System.out.println(allProducts);
		// findByProductName
		Product byProductName = productRepository.findByProductName(byProductNumber.getName());
		System.out.println("Product by product name " + byProductNumber.getName() + ": " + byProductName);
		// removeProduct
		productRepository.removeProduct(byProductNumber.getProductNumber());
		System.out.println("-----------All products ----------------");
		System.out.println(productRepository.getAllProducts());
	}

}
