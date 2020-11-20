package com.sist.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainClass {
		
	@Autowired
	private Sawon sa;			// 지금은  null

	public static void main(String[] args) {

		ApplicationContext app = new ClassPathXmlApplicationContext("app2.xml");
		
		MainClass mc = (MainClass)app.getBean("mainClass");
		
		System.out.println(mc.sa.getSaram().getName() + " " + mc.sa.getSaram().getSex() + " " + mc.sa.getSaram().getAge());
		System.out.println(mc.sa.getDept() + " " + mc.sa.getJob());
		
		
		// but private 변수에 setter, getter를 이용하지 않고 자동으로 할당하다보니 OOP가 깨짐

	}

}
