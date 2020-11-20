package com.sist.di2;

import java.lang.reflect.Method;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


// XML �Ľ� ====================================================================================================


public class XMLParser extends DefaultHandler{
	Map map = new HashMap();
	Object obj;
	Class clsName;
	
	
	@Override													// �±��̸� 				�Ӽ�
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try {
			if(qName.equals("bean")){							// �±װ� bean�̸�
				String id = attributes.getValue("id");			// id�Ӽ��� �� ��������
				String cls = attributes.getValue("class");		// class�Ӽ��� �� ��������
				clsName = Class.forName(cls);					// class�Ӽ��� ������ �޸𸮿� Ŭ���� �Ҵ�
				obj = clsName.newInstance();					// �Ҵ�� Ŭ������ �ν��Ͻ� ����
				map.put(id, obj);								// �ν��Ͻ��� map�� ����
			}
			
			if(qName.equals("property")){						// �±װ� property�̸� 
				String name = attributes.getValue("name");			// property�� name�Ӽ� ��������
				String value = attributes.getValue("value");			// property�� value�Ӽ� ��������
				Method[] methods = clsName.getDeclaredMethods();			// �Ҵ�� Ŭ������ �޼ҵ带 �迭�� ����
				
				for(Method m : methods){
					if(m.getName().equalsIgnoreCase("set" + name)){			// �޼ҵ��̸��� setter�̸� �޼ҵ� �ҷ�����
						m.invoke(obj, value);
						if(name.equals("age")){								// name�Ӽ���  age�� ��������ȯ �� �޼ҵ� ����
							m.invoke(obj, Integer.parseInt(value));
						}
						else{												// �޼ҵ� ����
							m.invoke(obj, value);
						}
					}
						
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
