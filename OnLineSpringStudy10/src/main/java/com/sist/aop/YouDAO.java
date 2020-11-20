package com.sist.aop;

import org.springframework.stereotype.Repository;

@Repository
public class YouDAO {
	
	public void boardAllData(){
		System.out.println("boardAllData() Call.....");
	}
}
