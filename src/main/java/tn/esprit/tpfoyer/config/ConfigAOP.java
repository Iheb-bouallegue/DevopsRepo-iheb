package tn.esprit.tpfoyer.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ConfigAOP {




    @After("execution(* tn.esprit.tpfoyer.service.*.add*(..))")
    public void logMethodOut(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Execution Réussie ! ");
    }

    @Around("execution(* tn.esprit.tpfoyer.service.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable
    {
        long start= System.currentTimeMillis();

        Object obj= pjp.proceed();

        long elapsedTime= System.currentTimeMillis() -start;

        log.info("Methodexecutiontime: " + elapsedTime+ " milliseconds.");
        return obj;
    }


}
