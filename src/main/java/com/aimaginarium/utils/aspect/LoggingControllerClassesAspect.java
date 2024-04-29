package com.aimaginarium.utils.aspect;

import com.aimaginarium.utils.aspect.logger.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.aimaginarium.utils.aspect.logger.LogColourConstant.ANSI_MAGENTA;

@Aspect
@Component
public record LoggingControllerClassesAspect(Logger logger) {

    private static final String PREFIX = "Controller";

    @Pointcut("within(com.aimaginarium.controller.*))")
    public void controllersPointcut() {
    }

    @Before("controllersPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.callLoggingMessage(joinPoint, PREFIX, " - started", ANSI_MAGENTA);
    }

    @AfterReturning(value = "controllersPointcut()", returning = "returningValue")
    public void logAfterReturning(JoinPoint joinPoint, Object returningValue) {
        String postfix = Objects.nonNull(returningValue)
                ? returningValue.getClass().getSimpleName() + " returned"
                : "ended";
        logger.callLoggingMessage(joinPoint, PREFIX, " - " + postfix, ANSI_MAGENTA);
    }

}
