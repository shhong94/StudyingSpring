package com.sist.di2;

import org.springframework.stereotype.Component;

@Component
public class Saram {
	private String name="ȫȫȫ";
	private String sex="����";
	private int age=20;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
