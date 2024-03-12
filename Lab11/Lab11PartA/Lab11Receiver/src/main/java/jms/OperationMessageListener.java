package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OperationMessageListener {

    private final JmsCalculator jmsCalculator;

    public OperationMessageListener(JmsCalculator jmsCalculator) {
        this.jmsCalculator = jmsCalculator;
    }

    @JmsListener(destination = "testQueue")
    public void receiveMessage(final String operationAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Operation operation = objectMapper.readValue(operationAsString, Operation.class);
            jmsCalculator.applyOperation(operation);
            System.out.println("JMS receiver received message:" + operation.getOperator()+" "+operation.getValue());
            System.out.println("Current value of sum is: "+jmsCalculator.getSum());
        } catch (IOException e) {
            System.out.println("JMS receiver: Cannot convert : " + operationAsString+" to a Person object");
        }
     }

}

