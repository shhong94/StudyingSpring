package com.sist.di3;

import org.springframework.stereotype.Component;

@Component("sa")
public class Sawon {
	private String name="���";
	private String dept="���ߺ�";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
	public void print(){
		System.out.println("�̸� : " + name + "�μ� : " + dept);
	}
	
}
