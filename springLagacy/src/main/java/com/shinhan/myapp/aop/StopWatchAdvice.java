package com.shinhan.myapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
//@Aspect // @pointcut + advice
public class StopWatchAdvice {

	
	//@Pointcut("execution(* select*(..))")
	@Pointcut("within(com.shinhan.myapp.controller.EmpController)")
	public void deptTimer() {
	}

	@Around("deptTimer()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {

		System.out.println("******" + jp.getSignature().getName());
		StopWatch watch = new StopWatch("½ÃÀÛ");
		watch.start();
		System.out.println("======== BEFORE ========");
		Object obj = jp.proceed();

		// ë³´ì¡°?—…ë¬?
		System.out.println("======== AFTER ========");
		System.out.println("*****" + jp.getSignature().getName());
		watch.stop();
		System.out.println("°É¸° ½Ã°£:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		
		
		return obj;
	}

	@Pointcut("execution(* d*(int,int))")
	public void targetMethod2() {

	}

	@Around("targetMethod2()")
	public Object dddddd(ProceedingJoinPoint jp) throws Throwable {
		// ë³´ì¡°?—…ë¬?
		System.out.println("******" + jp.getSignature().getName() + "ë©”ì„œ?“œ ?˜¸ì¶? ? „");
		StopWatch watch = new StopWatch("ê³„ì‚°?‹œê°?");
		watch.start();
		// ì£¼ì—…ë¬´ë?? ?ˆ˜?–‰?•œ?‹¤.
		Object object = jp.proceed();
		// ë³´ì¡°?—…ë¬?
		System.out.println("*****" + jp.getSignature().getName() + "ë©”ì„œ?“œ ?˜¸ì¶? ?›„");
		watch.stop();
		System.out.println("ì£¼ì—…ë¬? ?ˆ˜?–‰ ?‹œê°?:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		// ì£¼ì—…ë¬? ë¦¬í„´
		return object;
	}
}
