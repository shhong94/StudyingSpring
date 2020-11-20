package com.sist.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		String[] xml = {"app1.xml", "app2.xml"};									// 여러 xml파일들을 배열에 넣고
		
		ApplicationContext app = new ClassPathXmlApplicationContext(xml);			// 배열 변수를 파라미터로 넣으면 여러개를 동시에 파싱해줌
		
		Sawon sa = (Sawon)app.getBean("sa");
		System.out.println("이름 : " + sa.getName());
		System.out.println("부서 : " + sa.getDept());
		System.out.println("직위 : " + sa.getJob());
		
		Member mem = (Member)app.getBean("mem");
		System.out.println("이름 : " + mem.getName());
		System.out.println("주소 : " + mem.getAddr());
		System.out.println("전화 : " + mem.getTel());

	}

}
