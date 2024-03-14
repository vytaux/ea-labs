package bank.integration.jms;

import bank.integration.kafka.KafkaReceiver;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class JMSSenderImpl implements JMSSender {

    private final Logger logger = Logger.getLogger(JMSSenderImpl.class.getName());

    private final JmsTemplate jmsTemplate;

    public JMSSenderImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendJMSMessage(String message) {
        try {
            jmsTemplate.send("testQueue", session -> session.createTextMessage(message));
        } catch (Throwable e) {
            logger.warning("Exception in JMSSenderImpl " + e);
        }
    }
}
