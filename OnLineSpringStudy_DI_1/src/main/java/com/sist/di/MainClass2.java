package com.sist.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass2 {

		public static void main(String[] args) {
			// xml �Ľ�
			ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
			
			Sawon sa = (Sawon)app.getBean("sa");
			sa.init();
			sa.print();
		}
}
