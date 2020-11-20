package com.sist.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainClass {

	public static void main(String[] args) {
		
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		
		MyDAO dao = (MyDAO)app.getBean("myDAO");
		
		dao.db_select("ȫȫȫ");
		System.out.println("=============================");
		dao.db_insert();
		System.out.println("=============================");
		dao.db_update();
		System.out.println("=============================");
		dao.print();
		
		
		
	}

}
