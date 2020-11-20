package com.sist.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration						// 메모리에 클래스 할당
public class Config1 {
	
	@Bean("sa")						// 메모리에 메소드 할당
	public Sawon sawonInfo(){
		Sawon s = new Sawon();		// 객체 생성
		
		s.setName("박박박");
		s.setDept("스프링5부");
		s.setJob("사원");
		
		return s;
	}
}
