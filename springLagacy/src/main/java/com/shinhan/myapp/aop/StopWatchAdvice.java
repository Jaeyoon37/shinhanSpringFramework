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
		StopWatch watch = new StopWatch("½ΓΐΫ");
		watch.start();
		System.out.println("======== BEFORE ========");
		Object obj = jp.proceed();

		// λ³΄μ‘°?λ¬?
		System.out.println("======== AFTER ========");
		System.out.println("*****" + jp.getSignature().getName());
		watch.stop();
		System.out.println("°ΙΈ° ½Γ°£:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		
		
		return obj;
	}

	@Pointcut("execution(* d*(int,int))")
	public void targetMethod2() {

	}

	@Around("targetMethod2()")
	public Object dddddd(ProceedingJoinPoint jp) throws Throwable {
		// λ³΄μ‘°?λ¬?
		System.out.println("******" + jp.getSignature().getName() + "λ©μ? ?ΈμΆ? ? ");
		StopWatch watch = new StopWatch("κ³μ°?κ°?");
		watch.start();
		// μ£Όμλ¬΄λ?? ????€.
		Object object = jp.proceed();
		// λ³΄μ‘°?λ¬?
		System.out.println("*****" + jp.getSignature().getName() + "λ©μ? ?ΈμΆ? ?");
		watch.stop();
		System.out.println("μ£Όμλ¬? ?? ?κ°?:" + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());
		// μ£Όμλ¬? λ¦¬ν΄
		return object;
	}
}
