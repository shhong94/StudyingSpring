package com.sist.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.dao.*;

@Aspect			// 공통모듈 (DAO에서 공통적으로 사용되는 모듈을 모으나, 메모리 할당을 하지 않음)
@Component		// 여기서 메모리 할당 수행
public class DBAspect {

	@Autowired
	private DBConnection dbConn;
	
	// Before ~ AfterThrowing 정리 ==============================================================================================
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void before(){
		dbConn.getConnection();
	}
	
	
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void after(){
		dbConn.disConnection();
	}
	
	
	// 로그파일 만들기
	@Around("execution(* com.sist.web.EmpController.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Object obj = null;
		System.out.println("사용자 호출 전 : " + jp.getSignature().getName());
		obj = jp.proceed();
		System.out.println("사용자 호출 후 : " + jp.getSignature().getName());
		return obj;
	}
	
	
	// 리턴값
	@AfterReturning(value = "execution(* com.sist.web.EmpController.*(..))", returning = "val")	// 매개변수랑 동일해야 함
	public void afterReturning(JoinPoint jp, Object val){
		System.out.println("리턴값 : " + val);
	}
	
	
	// 예외처리
	@AfterThrowing(value = "execution(* com.sist.web.EmpController.*(..))", throwing = "ex")	// 매개변수랑 동일해야 함
	public void afterThrowing(Throwable ex){
		System.out.println(ex.getMessage());
	}
	
}
