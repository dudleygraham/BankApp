package com.learning.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
	
	@Pointcut("execution(* com.learning.banking.controller.*.*(..)) ")
	public void logRestController() {}
	
	@Before("logRestController()")
	public void beforeLogRestController(JoinPoint joinPoint) {
		LOGGER.info("Method called: " + joinPoint.getSignature().toShortString());
	}
	
	@AfterThrowing(pointcut = "logRestController()", throwing = "ex")
	public void afterExceptionThrowController(Exception ex) {
		LOGGER.error("Exception: " + ex);
		ex.printStackTrace(); 
	}
}
