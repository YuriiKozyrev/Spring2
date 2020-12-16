package com.myshop.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class MeasureAspect {

    Map<Date, String> hashMap = new HashMap<Date, String>();

    @Pointcut("execution (* com.myshop.demo.controller..*.*(..))")
    public void getLogInfo(){
    }

    @After("getLogInfo()")
    public void logAround(JoinPoint jp) throws Throwable{
        Date date = new Date();
        hashMap.put(date, jp.getSignature().toShortString());
        System.out.println(hashMap);
    }
}
