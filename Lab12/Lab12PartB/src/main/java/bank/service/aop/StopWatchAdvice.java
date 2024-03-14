package bank.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class StopWatchAdvice {

    @Pointcut("execution(* bank.service.*.*(..))")
    private void serviceLevelMethods() {}

    @Around("serviceLevelMethods()")
    public Object aroundAdvice(ProceedingJoinPoint call) {
        try {
            String method = call.getSignature().getName();

            StopWatch sw = new StopWatch();
            sw.start(call.getSignature().getName());

            Object retVal = call.proceed();

            sw.stop();

            String printMsg = String.format(
                "[%s] Time to execute %s = %dms",
                StopWatchAdvice.class.getSimpleName(),
                method,
                sw.getLastTaskTimeMillis()
            );
            System.out.println(printMsg);

            return retVal;
        } catch (Throwable e) {
            System.out.println("Exception in repository call aspect " + e);
        }

        return null;
    }
}
