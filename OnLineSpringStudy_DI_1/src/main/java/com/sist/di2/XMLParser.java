package com.sist.di2;

import java.lang.reflect.Method;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


// XML 파싱 ====================================================================================================


public class XMLParser extends DefaultHandler{
	Map map = new HashMap();
	Object obj;
	Class clsName;
	
	
	@Override													// 태그이름 				속성
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try {
			if(qName.equals("bean")){							// 태그가 bean이면
				String id = attributes.getValue("id");			// id속성의 값 가져오기
				String cls = attributes.getValue("class");		// class속성의 값 가져오기
				clsName = Class.forName(cls);					// class속성의 값으로 메모리에 클래스 할당
				obj = clsName.newInstance();					// 할당된 클래스의 인스턴스 생성
				map.put(id, obj);								// 인스턴스를 map에 저장
			}
			
			if(qName.equals("property")){						// 태그가 property이면 
				String name = attributes.getValue("name");			// property의 name속성 가져오기
				String value = attributes.getValue("value");			// property의 value속성 가져오기
				Method[] methods = clsName.getDeclaredMethods();			// 할당된 클래스의 메소드를 배열에 저장
				
				for(Method m : methods){
					if(m.getName().equalsIgnoreCase("set" + name)){			// 메소드이름이 setter이면 메소드 불러오기
						m.invoke(obj, value);
						if(name.equals("age")){								// name속성이  age면 정수형변환 후 메소드 실행
							m.invoke(obj, Integer.parseInt(value));
						}
						else{												// 메소드 실행
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
