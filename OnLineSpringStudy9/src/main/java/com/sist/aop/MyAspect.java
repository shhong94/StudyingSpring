package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Aspect		// ������
@Component	// �޸� �Ҵ�
public class MyAspect {
	
	@Autowired
	private MyDAO dao;
					// = ������ (������� � ���̵� ���� ���ٴ� �ǹ�)
	@Before("execution(* com.sist.aop.MyDAO.db_*(..))")	// com.sist.aop.MyDAO.db_*()  :  com.sist.aop.MyDAO�� �ִ� db_�� �����ϴ� �Ű����� ���� �޼ҵ尡 ����Ǳ� ���� ����
	public void before(){																// db_*(..) : �Ű����� �ְų� ���ų�
		dao.getConnection();															// db_*()  : �Ű����� ���� �޼ҵ常 
	}																					// db_*(int), db_*(String, String)
	
	@After("execution(* com.sist.aop.MyDAO.db_*(..))")	// com.sist.aop.MyDAO.db_*()  :  com.sist.aop.MyDAO�� �ִ� db_�� �����ϴ� �޼ҵ尡 ����� �Ŀ� ����
	public void after(){
		dao.disConnection();
	}
	
	
}
