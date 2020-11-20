package com.sist.manager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class MovieManager {
	
//	public static void main(String[] args) {
//		MovieManager m = new MovieManager();
//		String json = m.jsonAllData(1);
//		json = json.substring(json.indexOf("["), json.lastIndexOf("]")+1);
//		
//		try {
//			JSONParser jp = new JSONParser();							// json 파서기
//			JSONArray arr = (JSONArray)jp.parse(json);					// [] : 배열 (JSONArray로 파싱해야 함),  {} : 오브젝트 (JSONObject로 파싱해야 함)
//			System.out.println("JSONArray");
//			System.out.println(arr.toJSONString());
//			
//			for(int i = 0; i < arr.size(); i++){
//				JSONObject obj = (JSONObject)arr.get(i);				// 배열 안의 오브젝트를 하나씩 가져오기
//				System.out.println(i+1 + "=" + obj);
//				System.out.println("영화명 : " + obj.get("movieNm"));		// 오브젝트 내의 키를 통해 데이터 가져오기
//				System.out.println("감독 : " + obj.get("director"));
//				System.out.println("장르 : " + obj.get("genre"));
//				System.out.println("등급 : " + obj.get("watchGradeNm"));
//				System.out.println("개봉일 : " + obj.get("openDt"));
//				System.out.println("===================================================================");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(json);
//	}
	
	
	
	
	
	// JSON을 파싱하여 데이터를 가져오는 메소드 ========================================================================================================================
	public List<MovieVO> jsonAllData(int type){
		
		List<MovieVO> list = new ArrayList<MovieVO>();
		
		
		String url = "http://www.kobis.or.kr/kobis/business/main/";											// 공통 주소
		
		switch(type){
		case 1:
			url += "searchMainDailyBoxOffice.do";															// 일일 박스오피스
			break;
		case 2:
			url += "searchMainRealTicket.do";																// 실시간 예매율
			break;
		case 3:
			url += "searchMainDailySeatTicket.do";															// 좌석점유율
			break;
		case 4:
			url += "searchMainOnlineDailyBoxOffice.do";														// 온라인 박스오피스 일일
			break;
		}
		
		try {
			Document doc = Jsoup.connect(url).get();
			String json = doc.toString();
			json = json.substring(json.indexOf("["), json.lastIndexOf("]")+1);
			JSONParser jp = new JSONParser();																// json 파서기
			JSONArray arr = (JSONArray)jp.parse(json);														// [] : 배열 (JSONArray로 파싱해야 함),  {} : 오브젝트 (JSONObject로 파싱해야 함)
			
			for(int i = 0; i < arr.size(); i++){
				JSONObject obj = (JSONObject)arr.get(i);													// 배열 안의 오브젝트를 하나씩 가져오기
				
				MovieVO vo = new MovieVO();
				vo.setMno(i+1);
				vo.setRank((int)obj.get("rank"));
				vo.setTitle_ko((String)obj.get("movieNm"));
				vo.setTitle_en((String)obj.get("movieNmEn"));
				vo.setDirector((String)obj.get("director"));
				vo.setGenre((String)obj.get("genre"));
				vo.setPoster("http://www.kobis.or.kr" + (String)obj.get("thumbUrl"));						// 앞에 서버주소를 넣어줘야 함
				vo.setGrade((String)obj.get("watchGradeNm"));
				vo.setRegdate((String)obj.get("openDt"));
				vo.setStory((String)obj.get("synop"));
				vo.setTime((String)obj.get("showTm"));
				vo.setRank_id((int)obj.get("rankInten"));
				vo.setNation((String)obj.get("repNationCd"));
				list.add(vo);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
