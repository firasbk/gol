package com.life.lifegame.monitor;

import com.life.lifegame.processor.Structure;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StatusMonitor {
	private final static Logger log = LoggerFactory.getLogger(StatusMonitor.class);

	@AfterReturning(pointcut = "execution(* com.firas..*LifeGameProcessor.*(..))", returning = "structure")
	public void stopTimer(JoinPoint joinPoint, Structure structure) {
		log.info("STRUCTURE PROCESSED: " + structure.getElements().size() + " elements.");
	}
}
