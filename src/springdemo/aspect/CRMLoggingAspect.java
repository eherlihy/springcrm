package springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	// set up logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// set up pointcut declarations
	@Pointcut("execution(* springdemo.controller.*.*(..))")
	private void controllerPackage() {	}
	
	@Pointcut("execution(* springdemo.dao.*.*(..))")
	private void DAOPackage() {	}
	
	@Pointcut("execution(* springdemo.service.*.*(..))")
	private void servicePackage() {	}
	
	@Pointcut("controllerPackage() || DAOPackage() || servicePackage()")
	private void appFlow() {}
	
	// add @Before advice
	@Before("appFlow()")
	public void before(JoinPoint joinPoint) {
		// display method
		logger.info("> in @Before: method: " + joinPoint.getSignature().toShortString());
		// display arguments
		Object[] args = joinPoint.getArgs();
		for (Object t : args) {
			logger.info("> arg: " + t);
		}
	}
	
	// add @AfterReturning advice
	@AfterReturning(pointcut="appFlow()", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		// log result
		logger.info("> in @AfterReturning: method: " + joinPoint.getSignature().toShortString());
		logger.info("> result: " + result);
	}
}
