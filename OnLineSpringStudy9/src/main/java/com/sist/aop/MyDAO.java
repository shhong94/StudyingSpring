package com.sist.aop;

import org.springframework.stereotype.Repository;

@Repository
public class MyDAO {
	public void getConnection(){
		System.out.println("오라클연결");
	}
	
	public void disConnection(){
		System.out.println("오라클연결종료");
	}
	
	public void db_select(String name){
		//getConnection();								// 공통모듈
		System.out.println("SELECT문장 실행");								// 핵심모듈
		//disConnection();								// 공통모듈
	}
	
	public void db_insert(){
		//getConnection();
		System.out.println("INSERT문장 실행");
		//disConnection();
	}
	
	public void db_update(){					// AOP가 붙어서 실행될 메소드
		//getConnection();
		System.out.println("UPDATE문장 실행");
		//disConnection();
	}
	
	public void print(){						// 그냥 메소드
		
	}
	
}
