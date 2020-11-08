package ru.akalavan.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebServiceLogger {

    private static Logger LOG = LoggerFactory.getLogger(WebServiceLogger.class);

    @Pointcut("execution(public * ru.akalavan.service.CarService.*(..))")
    public void serviceMethod() {}

    @Pointcut("@annotation(ru.akalavan.annotation.Loggable)")
    public void loggableMethod() {}

    @Before(value = "serviceMethod() && loggableMethod()")
    public void logWebServiceBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        LOG.info("Method name: " + methodName + "; args: " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "serviceMethod() && loggableMethod()", returning = "object")
    public void logWebServiceAfter(Object object) {
        LOG.info("result: " + object + ";");
    }


}
