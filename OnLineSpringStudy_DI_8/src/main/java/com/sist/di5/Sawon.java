package com.sist.di5;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Sawon implements InitializingBean,DisposableBean {
	// 변수 ------------------------------------------------------
	private String name;
	private String dept;
	
	// 생성자 ----------------------------------------------------
	public Sawon(String name, String dept){
		this.name = name;
		this.dept = dept;
		System.out.println("생성자 실행");
	}
	
	// 게터 세터 ----------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("세터메소드");
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	// 메소드 ------------------------------------------------------
	public void print(){
		System.out.println(name);
		System.out.println(dept);
		System.out.println("print 메소드");
	}
	
	// 생성 / 소멸 메소드 --------------------------------------------------
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("객체생성");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("객체소멸");
	}
	
	/*
	 * 순서
	 * 1. 생성자 실행하여 초기화 
	 * 2. afterPropertiesSet() 메소드를 통해 세터메소드를 실행하여 값 설정
	 * 3. print() 메소드 실행
	 * 4. destroy() 메소드 실행하여 소멸
	*/
	
}
