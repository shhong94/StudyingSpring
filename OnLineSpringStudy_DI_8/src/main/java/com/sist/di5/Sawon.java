package com.sist.di5;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Sawon implements InitializingBean,DisposableBean {
	// ���� ------------------------------------------------------
	private String name;
	private String dept;
	
	// ������ ----------------------------------------------------
	public Sawon(String name, String dept){
		this.name = name;
		this.dept = dept;
		System.out.println("������ ����");
	}
	
	// ���� ���� ----------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("���͸޼ҵ�");
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	// �޼ҵ� ------------------------------------------------------
	public void print(){
		System.out.println(name);
		System.out.println(dept);
		System.out.println("print �޼ҵ�");
	}
	
	// ���� / �Ҹ� �޼ҵ� --------------------------------------------------
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("��ü����");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("��ü�Ҹ�");
	}
	
	/*
	 * ����
	 * 1. ������ �����Ͽ� �ʱ�ȭ 
	 * 2. afterPropertiesSet() �޼ҵ带 ���� ���͸޼ҵ带 �����Ͽ� �� ����
	 * 3. print() �޼ҵ� ����
	 * 4. destroy() �޼ҵ� �����Ͽ� �Ҹ�
	*/
	
}
