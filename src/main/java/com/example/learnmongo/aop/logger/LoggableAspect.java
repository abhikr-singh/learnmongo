package com.example.learnmongo.aop.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LoggableAspect {

    @Around("@annotation(com.example.learnmongo.annotations.Loggable)")
    public Object logger(ProceedingJoinPoint pjp) throws Throwable {
        final Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String[] argsName = signature.getParameterNames();
        Object[] argsValue = pjp.getArgs();
        StringBuffer argsBuffer = new StringBuffer(method.getName());
        int argsCount = argsName.length;
        if (argsCount != 0) {
            for (int i = 0; i < argsCount; i++) {
                argsBuffer.append(" with args [").append(argsName[i])
                        .append(": ").append(argsValue[i]).append(", ");
            }
            argsBuffer.setCharAt(argsBuffer.length() - 2, ']');
        }
        log.debug("Entering the method {}", argsBuffer.toString().trim());
        Object result = pjp.proceed();
        log.debug("Exiting the method {}", method.getName());
        return result;
    }

    @AfterThrowing(pointcut = "execution(* com.example.learnmongo..*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        final Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String arguments = Arrays.toString(joinPoint.getArgs());
        log.error("Caught exception in method: "
                + methodName + " with arguments "
                + arguments + "\nthe exception is: "
                + ex.getMessage());
    }
}
