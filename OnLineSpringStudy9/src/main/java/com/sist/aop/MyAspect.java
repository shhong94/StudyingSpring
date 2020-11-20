package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Aspect		// 공통모듈
@Component	// 메모리 할당
public class MyAspect {
	
	@Autowired
	private MyDAO dao;
					// = 리턴형 (별모양은 어떤 것이든 관계 없다는 의미)
	@Before("execution(* com.sist.aop.MyDAO.db_*(..))")	// com.sist.aop.MyDAO.db_*()  :  com.sist.aop.MyDAO에 있는 db_로 시작하는 매개변수 없는 메소드가 실행되기 전에 실행
	public void before(){																// db_*(..) : 매개변수 있거나 없거나
		dao.getConnection();															// db_*()  : 매개변수 없는 메소드만 
	}																					// db_*(int), db_*(String, String)
	
	@After("execution(* com.sist.aop.MyDAO.db_*(..))")	// com.sist.aop.MyDAO.db_*()  :  com.sist.aop.MyDAO에 있는 db_로 시작하는 메소드가 실행된 후에 실행
	public void after(){
		dao.disConnection();
	}
	
	
}
