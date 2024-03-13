package kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.message.CreateAccountMessage;
import kafka.message.DepositMessage;
import kafka.message.KafkaMessage;
import kafka.message.WithdrawMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Sender {

    private final Logger logger = Logger.getLogger(Sender.class.getName());

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Sender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCreateAccountMessage(long accountNumber, String customerName) {
        ObjectMapper objectMapper = new ObjectMapper();

        CreateAccountMessage createAccountRequest = new CreateAccountMessage();
        createAccountRequest.setAccountNumber(accountNumber);
        createAccountRequest.setCustomerName(customerName);

        try {
            String createAccountMessageAsString = objectMapper.writeValueAsString(createAccountRequest);

            KafkaMessage kafkaMessage = new KafkaMessage();
            kafkaMessage.setOperation("createAccount");
            kafkaMessage.setData(createAccountMessageAsString);

            String kafkaMessageAsString = objectMapper.writeValueAsString(kafkaMessage);

            kafkaTemplate.send("BankApplication", kafkaMessageAsString);

            logger.info("Create account message sent: " + createAccountMessageAsString);
        } catch (JsonProcessingException e) {
            logger.info("Error while serializing create account message");
        }
    }

    public void sendDepositMessage(long accountNumber, double amount) {
        ObjectMapper objectMapper = new ObjectMapper();

        DepositMessage depositMessage = new DepositMessage();
        depositMessage.setAccountNumber(accountNumber);
        depositMessage.setAmount(amount);

        try {
            String depositMessageAsString = objectMapper.writeValueAsString(depositMessage);

            KafkaMessage kafkaMessage = new KafkaMessage();
            kafkaMessage.setOperation("deposit");
            kafkaMessage.setData(depositMessageAsString);

            String kafkaMessageAsString = objectMapper.writeValueAsString(kafkaMessage);

            kafkaTemplate.send("BankApplication", kafkaMessageAsString);

            logger.info("Deposit message sent: " + depositMessageAsString);
        } catch (JsonProcessingException e) {
            logger.info("Error while serializing deposit message");
        }
    }

    public void sendWithdrawMessage(long accountNumber, double amount) {
        ObjectMapper objectMapper = new ObjectMapper();

        WithdrawMessage withdrawMessage = new WithdrawMessage();
        withdrawMessage.setAccountNumber(accountNumber);
        withdrawMessage.setAmount(amount);

        try {
            String withdrawMessageAsString = objectMapper.writeValueAsString(withdrawMessage);

            KafkaMessage kafkaMessage = new KafkaMessage();
            kafkaMessage.setOperation("withdraw");
            kafkaMessage.setData(withdrawMessageAsString);

            String kafkaMessageAsString = objectMapper.writeValueAsString(kafkaMessage);

            kafkaTemplate.send("BankApplication", kafkaMessageAsString);

            logger.info("Withdraw message sent: " + withdrawMessageAsString);
        } catch (JsonProcessingException e) {
            logger.info("Error while serializing withdraw message");
        }
    }
}
