package com.sist.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		String[] xml = {"app1.xml", "app2.xml"};									// ���� xml���ϵ��� �迭�� �ְ�
		
		ApplicationContext app = new ClassPathXmlApplicationContext(xml);			// �迭 ������ �Ķ���ͷ� ������ �������� ���ÿ� �Ľ�����
		
		Sawon sa = (Sawon)app.getBean("sa");
		System.out.println("�̸� : " + sa.getName());
		System.out.println("�μ� : " + sa.getDept());
		System.out.println("���� : " + sa.getJob());
		
		Member mem = (Member)app.getBean("mem");
		System.out.println("�̸� : " + mem.getName());
		System.out.println("�ּ� : " + mem.getAddr());
		System.out.println("��ȭ : " + mem.getTel());

	}

}
