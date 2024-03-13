package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Application implements CommandLineRunner {

    private final Sender sender;

    public Application(Sender sender) {
        this.sender = sender;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sender.sendCreateAccountMessage(123L, "John Doe");
        sender.sendDepositMessage(123L, 345);
        sender.sendWithdrawMessage(123L, 100);
    }
}
