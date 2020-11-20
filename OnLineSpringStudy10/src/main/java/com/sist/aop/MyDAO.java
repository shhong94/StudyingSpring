package com.sist.aop;

import org.springframework.stereotype.Repository;

@Repository
public class MyDAO {
	
	public void replyAllData(){
		System.out.println("replyAllData() Call.....");
	}
}
