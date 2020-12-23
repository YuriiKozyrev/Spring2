package com.myshop.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MeasureAspect {

    final Map<Date, String> hashMap = new HashMap<Date, String>();

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
