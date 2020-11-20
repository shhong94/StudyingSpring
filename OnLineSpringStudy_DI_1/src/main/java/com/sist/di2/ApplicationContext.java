package com.sist.di2;

import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.*;


// 파싱한 것들을 저장하는 역할 ===================================================================================


public class ApplicationContext {
	private Map map = new HashMap();
	
	public ApplicationContext(String path){
		try {
			
			// XML 파서기 생성
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLParser xp = new XMLParser();
			sp.parse(new File(path), xp);		// xp로 파싱한 것들을 sp에 저장
			
			map = xp.map;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
// DL 키를 통해서 객체 주소를 찾아주는 역할
	public Object getBean(String key){			// key에 해당하는 bean 가져오기
		return map.get(key);
	}
	
}
