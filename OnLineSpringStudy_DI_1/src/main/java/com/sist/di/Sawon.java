package com.sist.di;

public class Sawon {
	private String name;
	private String sex;
	private int age;
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
	
	
	
	
	
	public void init(){
		System.out.println("============= ������� =============");
	}
	
	public void print(){
		System.out.println("�̸� : " + name);
		System.out.println("���� : " + sex);
		System.out.println("���� : " + age);
	}
}
