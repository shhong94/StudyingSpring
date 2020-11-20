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
				if(clsName.isAnnotationPresent(Component.class)==false){						// �Ҵ�� Ŭ������ Component ������̼��� ������ �ѱ��
					continue;
				}
				Object obj = clsName.newInstance();												// �Ҵ�� Ŭ���� �� �� ���ǿ� �������� �ʴ°͸� ��ü�� ����
				System.out.println(obj);
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
