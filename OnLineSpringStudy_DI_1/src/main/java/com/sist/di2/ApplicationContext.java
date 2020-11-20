package com.sist.di2;

import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.*;


// �Ľ��� �͵��� �����ϴ� ���� ===================================================================================


public class ApplicationContext {
	private Map map = new HashMap();
	
	public ApplicationContext(String path){
		try {
			
			// XML �ļ��� ����
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLParser xp = new XMLParser();
			sp.parse(new File(path), xp);		// xp�� �Ľ��� �͵��� sp�� ����
			
			map = xp.map;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
// DL Ű�� ���ؼ� ��ü �ּҸ� ã���ִ� ����
	public Object getBean(String key){			// key�� �ش��ϴ� bean ��������
		return map.get(key);
	}
	
}
