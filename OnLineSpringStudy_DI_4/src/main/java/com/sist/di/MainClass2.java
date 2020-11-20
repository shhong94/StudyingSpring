package com.sist.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass2 {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext app = 
				new AnnotationConfigApplicationContext(Config1.class, Config2.class);
		
		Sawon s = app.getBean("sa", Sawon.class);
		System.out.println("이름 : " + s.getName());
		System.out.println("직위 : " + s.getJob());
		System.out.println("부서 : " + s.getDept());
		
		
		Member m = app.getBean("m", Member.class);
		System.out.println("이름 : " + m.getName());
		System.out.println("주소 : " + m.getAddr());
		System.out.println("전화 : " + m.getTel());
		
	}
}
