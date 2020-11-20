package com.sist.di2;

import org.springframework.stereotype.Component;

@Component("mem")
public class Member {
	private String name="¸â¹ö2";
	private String tel="010-2222-2222";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
