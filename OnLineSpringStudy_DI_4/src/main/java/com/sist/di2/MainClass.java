package com.sist.di2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;

public class MainClass {

	public static void main(String[] args) {

		ApplicationContext app = new ClassPathXmlApplicationContext("app3.xml");
		
		EmpDAO dao = new EmpDAO();
		
		List<EmpVO> list = dao.empListData();
		for(EmpVO vo : list){
			System.out.println(vo.getEmpno());
			System.out.println(vo.getEname());
			System.out.println(vo.getJob());
			System.out.println(vo.getHiredate().toString());
			System.out.println(vo.getSal());
		}
		

	}

}


