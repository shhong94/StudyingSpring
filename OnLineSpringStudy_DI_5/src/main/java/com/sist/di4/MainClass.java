package com.sist.di4;

import java.util.*;
public class MainClass {

	public static void main(String[] args) {

		try {
			List<String> list = new ArrayList<String>();
			list.add("com.sist.di4.A");
			list.add("com.sist.di4.B");
			list.add("com.sist.di4.C");
			
			
			
			for(String s : list){
				Class clsName = Class.forName(s);
				if(clsName.isAnnotationPresent(Component.class)==false){						// 할당된 클래스에 Component 어노테이션이 없으면 넘기기
					continue;
				}
				Object obj = clsName.newInstance();												// 할당된 클래스 중 위 조건에 부합하지 않는것만 객체로 생성
				System.out.println(obj);
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
