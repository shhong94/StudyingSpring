package com.sist.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config2 {
	
	@Bean("m")
	public Member memberInfo(){
		Member m = new Member();
		
		m.setName("������");
		m.setAddr("������5��");
		m.setTel("010-5555-5555");
		
		return m;
	}
}
