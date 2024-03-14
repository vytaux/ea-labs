package bank.service.aop;

import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RepositoryLogAdvice {

    private final Logger logger;

    public RepositoryLogAdvice(Logger logger) {
        this.logger = logger;
    }

    @Pointcut("execution(* bank.repository.*.*(..))")
    private void repositoryCalls() {}

    @Before("repositoryCalls()")
    public void logRepositoryCall(JoinPoint joinPoint) {
        String logMsg = String.format(
            "[%s] Repository method called: %s",
            RepositoryLogAdvice.class.getSimpleName(),
            joinPoint.getSignature().getName()
        );
        logger.log(logMsg);
    }
}
