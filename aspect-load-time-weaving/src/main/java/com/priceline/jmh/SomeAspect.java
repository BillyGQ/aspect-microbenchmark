package com.priceline.jmh;

import java.util.Random;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.openjdk.jmh.infra.Blackhole;

import com.priceline.jmh.JMHApplication.BenchmarkState;

@Aspect
public class SomeAspect {

    @Pointcut("@annotation(someAnnotation) && execution(* *(..))")
    public void callAt(SomeAnnotation someAnnotation) {
    }

    @Around(value = "callAt(someAnnotation)", argNames = "joinPoint,someAnnotation")
    public Object aroundAspectFunction(ProceedingJoinPoint joinPoint, SomeAnnotation someAnnotation) throws Throwable {

        Object[] args = joinPoint.getArgs();

        Blackhole blackhole = (Blackhole) args[0];
        Random random = ((BenchmarkState)args[1]).random;

        blackhole.consume(random.nextInt());

        Object proceed = joinPoint.proceed();

        blackhole.consume(random.nextInt());

        return proceed;
    }

}
