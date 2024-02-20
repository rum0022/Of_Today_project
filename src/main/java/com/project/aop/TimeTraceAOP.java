package com.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 이 객체는 singleton으로 만들어짐 (컴포넌트로 만들어진것은 싱글턴임)
@Aspect // 부가기능 정의하겠다는 용어(advice) + 어디에 적용할 것인가(pointcut) 
public class TimeTraceAOP {

	// 1) 수행할 패키지 지정 (적용경로지정), 지금은 모든 스프링 빈 하겠다고 설정한것임
	//@Around("execution(* com.project..*(..))")
	
	//2) 어노테이션 지정
	@Around("@annotation(TimeTrace)") // 어느 어노테이션이 붙어있을 때 수행되는가 (지금은 TimeTrace)
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		// 타겟이 적용하는 메소드 - joinPoint 
		
		// 시간측정을 하겠음.
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// 본래 할 일 수행 (예: 회원가입, 로그인, 글쓰기 등등)
		Object proceedObj = joinPoint.proceed();
		
		stopWatch.stop();
		
		log.info("########### 실행시간(ms): {}", stopWatch.getTotalTimeMillis());
		log.info("$$$$$$ 실행시간:" + stopWatch.prettyPrint());
		
		return proceedObj;
	}
}
