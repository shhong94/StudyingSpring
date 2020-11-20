package com.sist.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sawon {
	
	@Autowired					// 할당된 주소를 여기에 자동으로 넣어줘라    (Saram)app.getBean("saram"); 수행
	private Saram saram;		// Saram 객체
	private String dept="개발부";
	private String job="대리";
	
	
	
	
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
