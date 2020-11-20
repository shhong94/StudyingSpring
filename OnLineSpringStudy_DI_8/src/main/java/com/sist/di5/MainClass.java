package com.sist.di5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	
	
	
	public static void main(String[] args) {
		
		GenericXmlApplicationContext app = new GenericXmlApplicationContext("app.xml");		// GenericXmlApplicationContext�� destroy(�Ҹ�)�޼ҵ� ����
//		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");				ApplicationContext�� destroy(�Ҹ�) �޼ҵ� ����
		
		Sawon sa1 = (Sawon)app.getBean("sa");
		Sawon sa2 = (Sawon)app.getBean("sa");
		
		sa1.print();
		
		System.out.println(sa1);
		System.out.println(sa2);
		
		app.close();
		
	}
}
