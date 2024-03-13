package bank.integration.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSSenderImpl implements JMSSender{

	private JmsTemplate jmsTemplate;

	public JMSSenderImpl(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendJMSMessage (String text){
		jmsTemplate.send("testQueue", session -> session.createTextMessage(text));
	}
}
