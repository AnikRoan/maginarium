package com.aimaginarium.utils.aspect.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import static com.aimaginarium.utils.aspect.logger.LogColourConstant.ANSI_RESET;

@Slf4j
@Component
public class LoggerImpl implements Logger {

    @Override
    public void callLoggingMessage(JoinPoint joinPoint, String prefix, String postfix, String colour) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();
        log.debug("{}{}" + ": {}.{}({}){}" + ANSI_RESET,
                colour, prefix, className, methodName, argsToString(parameterNames, args), postfix);
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
