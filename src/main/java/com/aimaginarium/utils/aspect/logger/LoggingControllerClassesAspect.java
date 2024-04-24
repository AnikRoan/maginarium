package com.aimaginarium.utils.aspect.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import static com.aimaginarium.utils.aspect.logger.LogColourConstant.*;

@Slf4j
@Aspect
@Component
public record LoggingControllerClassesAspect() {

    @Pointcut("within(com.aimaginarium.controller.*))")
    public void controllersPointcut() {
    }

    @Before("controllersPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        callLoggingMessage(joinPoint, " - started");
    }

    @AfterReturning(value = "controllersPointcut()", returning = "returningValue")
    public void logAfterReturning(JoinPoint joinPoint, Object returningValue) {
        String postfix = "ended";
        if (returningValue != null) {
            postfix = returningValue.getClass().getSimpleName()+ " returned";
        }
        callLoggingMessage(joinPoint, " - " + postfix);
    }

    private void callLoggingMessage(JoinPoint joinPoint, String postfix) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();
        log.info(ANSI_MAGENTA + "Controller: {}.{}({}){}" + ANSI_RESET, className, methodName, argsToString(parameterNames, args), postfix);
    }

    private String argsToString(String[] parameterNames, Object[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        if (parameterNames != null && args != null && parameterNames.length > 0 && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                stringBuilder.append(parameterNames[i]).append("=").append(args[i]).append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        return stringBuilder.toString();
    }
}
