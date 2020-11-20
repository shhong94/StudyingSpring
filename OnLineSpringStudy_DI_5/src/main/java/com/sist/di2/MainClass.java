package com.sist.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainClass {
		
	@Autowired
	private Sawon sa;			// ������  null

	public static void main(String[] args) {

		ApplicationContext app = new ClassPathXmlApplicationContext("app2.xml");
		
		MainClass mc = (MainClass)app.getBean("mainClass");
		
		System.out.println(mc.sa.getSaram().getName() + " " + mc.sa.getSaram().getSex() + " " + mc.sa.getSaram().getAge());
		System.out.println(mc.sa.getDept() + " " + mc.sa.getJob());
		
		
		// but private ������ setter, getter�� �̿����� �ʰ� �ڵ����� �Ҵ��ϴٺ��� OOP�� ����

	}

}
