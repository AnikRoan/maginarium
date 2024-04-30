package com.aimaginarium.utils.aspect;

import com.aimaginarium.utils.aspect.logger.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.aimaginarium.utils.aspect.logger.LogColourConstant.ANSI_CYAN;
import static com.aimaginarium.utils.aspect.logger.LogColourConstant.ANSI_RED;

@Aspect
@Component
public record LoggingServiceClassesAspect(Logger logger) {

    private static final String PREFIX = "Service";

    @Pointcut("within(com.aimaginarium.service..*))")
    public void servicesPointcut() {
    }

    @Before("servicesPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.callLoggingMessage(joinPoint, PREFIX, " - started", ANSI_CYAN);
    }

    @AfterReturning(value = "servicesPointcut()", returning = "returningValue")
    public void logAfterReturning(JoinPoint joinPoint, Object returningValue) {
        String postfix = Objects.nonNull(returningValue)
                ? returningValue.getClass().getSimpleName() + " returned"
                : "ended";
        logger.callLoggingMessage(joinPoint, PREFIX, " - " + postfix, ANSI_CYAN);
    }

    @AfterThrowing(value = "servicesPointcut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        logger.callLoggingMessage(joinPoint, PREFIX, " - " + ex.getMessage(), ANSI_RED);
    }

}
