package com.sist.di2;

import com.sist.di.Sawon;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ApplicationContext("C:\\springDev\\springStudy\\OnLineSpringStudy_DI_1\\src\\main\\java\\com\\sist\\di2\\app.xml");

		Sawon sa = (Sawon)app.getBean("sa");
		System.out.println(sa);
		sa.init();
		sa.print();
		
		Sawon sa1 = (Sawon)app.getBean("sa");
		System.out.println(sa1);
		sa1.init();
		sa1.print();
	}

}
