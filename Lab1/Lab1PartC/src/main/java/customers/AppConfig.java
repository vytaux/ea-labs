package customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Logger logger() {
        return new LoggerImpl(); // replace with your actual Logger implementation
    }

    @Bean
    public CustomerRepository customerRepository(Logger logger) {
        return new CustomerRepositoryImpl(logger);
    }

    @Bean
    public EmailSender emailSender() {
        return new EmailSenderImpl(logger());
    }

    @Bean
    public CustomerService customerService() {
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.setCustomerRepository(customerRepository(logger()));
        customerService.setEmailSender(emailSender());
        return customerService;
    }
}