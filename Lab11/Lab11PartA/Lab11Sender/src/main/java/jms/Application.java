package jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;


@SpringBootApplication
@EnableJms
public class Application implements CommandLineRunner {

	@Autowired
	JmsTemplate jmsTemplate;

	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		sendJmsMessageAndSout("+", 7);
		sendJmsMessageAndSout("+", 8);
	}

	private void sendJmsMessageAndSout(String operator, Integer value) throws JsonProcessingException {
		Operation operation = new Operation(operator, value);
		//convert person to JSON string
		ObjectMapper objectMapper = new ObjectMapper();
		String operationAsString = objectMapper.writeValueAsString(operation);

		System.out.println("Sending a JMS message:" + operationAsString);
		jmsTemplate.convertAndSend("testQueue",operationAsString);
	}

}
