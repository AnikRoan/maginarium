package com.aimaginarium.utils.aspect.logger;

import org.aspectj.lang.JoinPoint;

@FunctionalInterface
public interface Logger {

    void callLoggingMessage(JoinPoint joinPoint, String prefix, String postfix, String colour);
}
