package com.sist.di;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

// �����ͺ��̽� ������ �����ϴ� Ŭ����		====================== �������� BasicDataSource ���
@Component
public class MyBasicDataSource extends BasicDataSource{
	public MyBasicDataSource(){
		setDriverClassName("oracle.jdbc.driver.OracleDriver");
		setUrl("jdbc:oracle:thin:@211.238.142.195:1521:XE");
		setUsername("hr");
		setPassword("happy");
		
		
	}
}
