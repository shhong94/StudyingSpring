package com.sist.di4;

import javax.annotation.Resource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.di2.*;
import com.sist.di3.*;

@Component("mc")
public class MainClass {
	
	
	@Resource(name="mem")
	private Member mem;
	
	@Resource(name="sa")
	private Sawon sa;
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
		
		MainClass mc = app.getBean("mc", MainClass.class);
		
		System.out.println(mc.mem.getName());
		System.out.println(mc.sa.getName());
	}

}
