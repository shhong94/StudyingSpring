package com.sist.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass2 {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext app = 
				new AnnotationConfigApplicationContext(Config1.class, Config2.class);
		
		Sawon s = app.getBean("sa", Sawon.class);
		System.out.println("�̸� : " + s.getName());
		System.out.println("���� : " + s.getJob());
		System.out.println("�μ� : " + s.getDept());
		
		
		Member m = app.getBean("m", Member.class);
		System.out.println("�̸� : " + m.getName());
		System.out.println("�ּ� : " + m.getAddr());
		System.out.println("��ȭ : " + m.getTel());
		
	}
}
