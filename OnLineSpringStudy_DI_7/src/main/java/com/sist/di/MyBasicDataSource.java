package com.sist.di;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

// 데이터베이스 정보를 저장하는 클래스		====================== 스프링의 BasicDataSource 상속
@Component
public class MyBasicDataSource extends BasicDataSource{
	public MyBasicDataSource(){
		setDriverClassName("oracle.jdbc.driver.OracleDriver");
		setUrl("jdbc:oracle:thin:@211.238.142.195:1521:XE");
		setUsername("hr");
		setPassword("happy");
		
		
	}
}
