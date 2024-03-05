package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "customers")
public class AppConfig {

    @Bean
    public CustomerService customerService(
            CustomerRepository customerRepository,
            EmailSender emailSender
    ) {
        CustomerService customerService = new CustomerServiceImpl();
        customerService.setCustomerRepository(customerRepository);
        customerService.setEmailSender(emailSender);
        return customerService;
    }

    @Bean
    public ProductService productService(
            EmailSender emailSender,
            ProductRepository productRepository
    ) {
        return new ProductServiceImpl(emailSender, productRepository);
    }
}