package com.sist.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sawon {
	
	@Autowired					// �Ҵ�� �ּҸ� ���⿡ �ڵ����� �־����    (Saram)app.getBean("saram"); ����
	private Saram saram;		// Saram ��ü
	private String dept="���ߺ�";
	private String job="�븮";
	
	
	
	
	public Saram getSaram() {
		return saram;
	}
	public void setSaram(Saram saram) {
		this.saram = saram;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
}
