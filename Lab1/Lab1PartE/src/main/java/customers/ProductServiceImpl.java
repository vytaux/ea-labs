package customers;

import org.springframework.stereotype.Service;

public class ProductServiceImpl implements ProductService {

	private EmailSender emailSender;
	private ProductRepository productRepository;

	public ProductServiceImpl(EmailSender emailSender, ProductRepository productRepository) {
		this.emailSender = emailSender;
        this.productRepository = productRepository;
    }

	@Override
	public void addProduct(String name) {
		Product product = new Product(name);
		productRepository.save(product);
		// Send an email
		emailSender.sendEmail("some@email", "Product added: " + name);
	}
}
