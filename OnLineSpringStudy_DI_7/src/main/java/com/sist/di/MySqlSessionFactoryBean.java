package com.sist.di;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean{

	@Autowired				//===================== Autowired�� ���� �Ű������� ���� ä������	(p:datasource-ref="ds")
	public void setDataSource(DataSource dataSource) {		// DataSource ��ü ����
		
		super.setDataSource(dataSource);
	}
	
	public MySqlSessionFactoryBean(){
		try {
			Resource res = new ClassPathResource("Config.xml");	// p:configlocation="classpath:Config.xml"
			setConfigLocation(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
