package com.sist.di;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component("mc")
public class MainClass {

	@Resource(name="sa")
	private Sawon sa;
	
	@Resource(name="mem")
	private Member mem;
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MainClass mc = (MainClass)app.getBean("mc");
		System.out.println(mc.sa.getName() + mc.sa.getSex());
		System.out.println(mc.mem.getName() + mc.mem.getAddr());
		
	}

}
