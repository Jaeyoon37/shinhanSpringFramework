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
		StopWatch watch = new StopWatch("����");
		watch.start();
		System.out.println("======== BEFORE ========");
		Object obj = jp.proceed();

		// 보조?���?
		System.out.println("======== AFTER ========");
		System.out.println("*****" + jp.getSignature().getName());
		watch.stop();
		System.out.println("�ɸ� �ð�:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		
		
		return obj;
	}

	@Pointcut("execution(* d*(int,int))")
	public void targetMethod2() {

	}

	@Around("targetMethod2()")
	public Object dddddd(ProceedingJoinPoint jp) throws Throwable {
		// 보조?���?
		System.out.println("******" + jp.getSignature().getName() + "메서?�� ?���? ?��");
		StopWatch watch = new StopWatch("계산?���?");
		watch.start();
		// 주업무�?? ?��?��?��?��.
		Object object = jp.proceed();
		// 보조?���?
		System.out.println("*****" + jp.getSignature().getName() + "메서?�� ?���? ?��");
		watch.stop();
		System.out.println("주업�? ?��?�� ?���?:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		// 주업�? 리턴
		return object;
	}
}
