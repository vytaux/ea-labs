package customers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeRepositoryCallAspect {

    @Pointcut("execution(* customers.*Repository.*(..)) ")
    private void repositoryMethodCalls() {}

    @Around("repositoryMethodCalls()")
    public Object aroundAdvice(ProceedingJoinPoint call) {
        try {
            String method = call.getSignature().getName();

            StopWatch sw = new StopWatch();
            sw.start(call.getSignature().getName());

            Object retVal = call.proceed();

            sw.stop();

            long totaltime = sw.getLastTaskTimeMillis();
            System.out.println("Time to execute " + method + " = " + totaltime + "ms");

            return retVal;
        } catch (Throwable e) {
            System.out.println("Exception in repository call aspect " + e);
        }

        return null;
    }
}