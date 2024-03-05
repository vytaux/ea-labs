package customers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmailSenderAspect {

    private final Logger logger;

    public EmailSenderAspect(Logger logger) {
        this.logger = logger;
    }

    @Pointcut("execution(* customers.EmailSender.sendEmail(..)) ")
    private void sendEmail() { }

    @After("sendEmail() && args(email, message)")
    public void afterAdvice(JoinPoint joinPoint, String email, String message)
    {
        String method = joinPoint.getSignature().getName();
        EmailSender emailSender = (EmailSender) joinPoint.getTarget();

        String logMsg = String.format(
            "method=%s address=%s message=%s outgoing mail server=%s",
            method, email, message, emailSender.getOutgoingMailServer()
        );

        logger.log(logMsg);
    }
}