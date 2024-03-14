package bank.integration.kafka;

import bank.integration.kafka.message.CreateAccountMessage;
import bank.integration.kafka.message.DepositMessage;
import bank.integration.kafka.message.KafkaMessage;
import bank.integration.kafka.message.WithdrawMessage;
import bank.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class KafkaReceiver {

    private final Logger logger = Logger.getLogger(KafkaReceiver.class.getName());

    private final AccountService accountService;

    public KafkaReceiver(AccountService accountService) {
        this.accountService = accountService;
    }

    @KafkaListener(topics = {"BankApplication"})
    public void receive(@Payload String message) {
        logger.info("Receiver received KafkaMessage message: " + message);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            KafkaMessage kafkaMessage = objectMapper.readValue(message, KafkaMessage.class);
            switch (kafkaMessage.getOperation()) {
                case "createAccount":
                    handleCreateAccountMessage(kafkaMessage);
                    break;
                case "deposit":
                    handleDepositMessage(kafkaMessage);
                    break;
                case "withdraw":
                    handleWithdrawMessage(kafkaMessage);
                    break;
                default:
                    logger.warning("Receiver received unknown message: " + kafkaMessage.getData());
            }
        } catch (IOException e) {
            logger.warning("Error while deserializing message: " + message);
        }
    }

    private void handleWithdrawMessage(KafkaMessage kafkaMessage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        WithdrawMessage withdrawMessage = objectMapper.readValue(
                kafkaMessage.getData(),
                WithdrawMessage.class
        );

        accountService.withdraw(
                withdrawMessage.getAccountNumber(),
                withdrawMessage.getAmount()
        );
    }

    private void handleCreateAccountMessage(KafkaMessage kafkaMessage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateAccountMessage createAccountMessage = objectMapper.readValue(
                kafkaMessage.getData(),
                CreateAccountMessage.class
        );

        accountService.createAccount(
                createAccountMessage.getAccountNumber(),
                createAccountMessage.getCustomerName()
        );
    }

    private void handleDepositMessage(KafkaMessage kafkaMessage) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DepositMessage depositMessage = objectMapper.readValue(
                kafkaMessage.getData(),
                DepositMessage.class
        );

        accountService.deposit(
                depositMessage.getAccountNumber(),
                depositMessage.getAmount()
        );
    }
}