package com.example.Aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Aspect
@Component
public class Log {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);
    @Before("execution(* com.example.contorller.IndexController.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        StringBuilder sb = new StringBuilder();
        for(Object object : joinPoint.getArgs()){
             sb.append("arg = " + object.toString());
        }
        logger.info("beforeMethod = "+ sb);
        logger.info("beforeTime = "+ new Date());

    }
    @After("execution(* com.example.contorller.IndexController.*(..))")
    public void afterMethod(JoinPoint joinPoint){
        StringBuilder sb = new StringBuilder();
        for(Object arg : joinPoint.getArgs()){
            sb.append("arg = "+ arg );
        }

        logger.info("afterMethod = "+ sb);
        logger.info("afterTime = "+ new Date());
    }
}
