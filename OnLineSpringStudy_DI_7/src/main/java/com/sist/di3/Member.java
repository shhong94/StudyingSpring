package com.sist.di3;

import org.springframework.stereotype.Component;

@Component("mem")
public class Member {
	private String name="���";
	private String addr="����";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public void print(){
		System.out.println("�̸� : " + name + "�ּ� : " + addr);
	}
}
