package com.sist.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration						// �޸𸮿� Ŭ���� �Ҵ�
public class Config1 {
	
	@Bean("sa")						// �޸𸮿� �޼ҵ� �Ҵ�
	public Sawon sawonInfo(){
		Sawon s = new Sawon();		// ��ü ����
		
		s.setName("�ڹڹ�");
		s.setDept("������5��");
		s.setJob("���");
		
		return s;
	}
}
