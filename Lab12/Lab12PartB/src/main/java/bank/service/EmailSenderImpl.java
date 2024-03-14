package bank.service;

import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    public void sendEmail(String email, String message) {
        System.out.println("Sending email to " + email + " with message: " + message);
    }
}
