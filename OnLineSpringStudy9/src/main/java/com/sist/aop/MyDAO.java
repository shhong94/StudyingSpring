package com.sist.aop;

import org.springframework.stereotype.Repository;

@Repository
public class MyDAO {
	public void getConnection(){
		System.out.println("����Ŭ����");
	}
	
	public void disConnection(){
		System.out.println("����Ŭ��������");
	}
	
	public void db_select(String name){
		//getConnection();								// ������
		System.out.println("SELECT���� ����");								// �ٽɸ��
		//disConnection();								// ������
	}
	
	public void db_insert(){
		//getConnection();
		System.out.println("INSERT���� ����");
		//disConnection();
	}
	
	public void db_update(){					// AOP�� �پ ����� �޼ҵ�
		//getConnection();
		System.out.println("UPDATE���� ����");
		//disConnection();
	}
	
	public void print(){						// �׳� �޼ҵ�
		
	}
	
}
