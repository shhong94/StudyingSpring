package com.sist.naver;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONExample {
	;
	
	public static void main(String[] args) {
		
		// 키는 무조건 큰따옴표로 감싸야됨 --------------------------------------
		String json = "{\"sawon\" : ["
				+ "{\"name\" :\"홍길동\", \"sex\" : \"남자\", \"age\" : 30},"
				+ "{\"name\" :\"김김김\", \"sex\" : \"여자\", \"age\" : 30},"
				+ "{\"name\" :\"박박박\", \"sex\" : \"남자\", \"age\" : 30}"
				+ "]}";
		
		
		// 3번. 오브젝트 안에 배열이 있는 JSON 파싱
		try {
			JSONParser jp = new JSONParser();
			JSONObject root = (JSONObject)jp.parse(json);						// 처음엔 오브젝트로 받기
			JSONArray arr = (JSONArray)root.get("sawon");						// 오브젝트 안의 배열을 받기
			for(int i = 0; i < arr.size(); i++){
				JSONObject obj = (JSONObject)arr.get(i);						// 오브젝트 안의 배열 안의 오브젝트를 받기
				System.out.println(obj.get("name"));
				System.out.println(obj.get("sex"));
				System.out.println(obj.get("age"));
				System.out.println("=========================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// 2번. JSON array를 가져오기 ========================================
//		try {
//			JSONParser jp = new JSONParser();
//			JSONArray arr = (JSONArray)jp.parse(json);
//			for(int i = 0; i < arr.size(); i++){
//				JSONObject obj = (JSONObject)arr.get(i);
//				System.out.println(obj.get("name"));
//				System.out.println(obj.get("sex"));
//				System.out.println(obj.get("age"));
//				System.out.println("=========================");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		// 1번. JSON 오브젝트 하나만 가져오기=====================================
//		try {
//			JSONParser jp = new JSONParser();
//			JSONObject obj = (JSONObject)jp.parse(json);
//			System.out.println("성별 : " + obj.get("sex"));
//			System.out.println("이름 : " + obj.get("name"));
//			System.out.println("아니 : " + obj.get("age"));
//		} catch (Exception e) {
//			// TODO: J
//		}
	}
}
