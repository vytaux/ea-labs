package customers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");

		Logger logger = new LoggerImpl();
		CustomerService customerService = context.getBean("customerService", CustomerService.class);
		customerService.setCustomerRepository(new CustomerRepositoryImpl(logger));
		customerService.setEmailSender(new EmailSenderImpl(logger));

		customerService.addCustomer(
			"Frank Brown", "fbrown@acme.com",
			"mainstreet 5", "Chicago", "60613"
		);

	}
}

