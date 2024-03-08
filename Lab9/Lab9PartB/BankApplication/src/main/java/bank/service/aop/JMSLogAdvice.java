package bank.service.aop;

import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class JMSLogAdvice {

    private final Logger logger;

    public JMSLogAdvice(Logger logger) {
        this.logger = logger;
    }

    @Pointcut("execution(* bank.integration.jms.JMSSender.sendJMSMessage(..))")
    private void jmsSend() {}

    @AfterReturning("jmsSend() && args(message)")
    public void logJmsSend(String message) {
        String logMsg = String.format(
                "[%s] JMS message sent: %s",
                JMSLogAdvice.class.getSimpleName(),
                message
        );
        logger.log(logMsg);
    }
}
