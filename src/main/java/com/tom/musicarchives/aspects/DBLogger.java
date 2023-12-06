package com.tom.musicarchives.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DBLogger {
    @Before("execution(* com.tom.musicarchives.model.*DAOImpl.*(..))")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

        Object[] args = theJoinPoint.getArgs();

        System.out.print("DAO: " + methodSig + ", args: ");
        for (var a : args) {
            System.out.print(a + " ");
        }

        System.out.println();
    }
}
