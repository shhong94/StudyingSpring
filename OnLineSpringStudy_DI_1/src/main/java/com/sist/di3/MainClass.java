package com.sist.di3;

//���� : MemberŬ���� -> MemberList -> MainŬ����

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app1.xml");
									//========>     C:\springDev\springStudy\OnLineSpringStudy_DI_1\src\main\java
		
		MemberList ml = (MemberList)app.getBean("ml");
		
		ml.print();

	}

}
