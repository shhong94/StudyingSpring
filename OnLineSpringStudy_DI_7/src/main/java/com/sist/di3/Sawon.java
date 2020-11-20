package com.sist.di3;

import org.springframework.stereotype.Component;

@Component("sa")
public class Sawon {
	private String name="사원";
	private String dept="개발부";
	
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
		System.out.println("이름 : " + name + "부서 : " + dept);
	}
	
}
