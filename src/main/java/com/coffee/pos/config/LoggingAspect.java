package com.coffee.pos.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper;

    public LoggingAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerPointCut() {}

    @Around("restControllerPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // 紀錄輸入
        logger.info(
                "Enter: {}.{}() with argument[s] = {}",
                className,
                methodName,
                objectMapper.writeValueAsString(args));

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;

            // 紀錄輸出
            logger.info(
                    "Exit: {}.{}() with result {} in {}ms",
                    className,
                    methodName,
                    objectMapper.writeValueAsString(result),
                    executionTime);

            return result;
        } catch (Exception e) {
            logger.error(
                    "Exception in {}.{}() with cause = {}", className, methodName, e.getMessage());
            throw e;
        }
    }
}
