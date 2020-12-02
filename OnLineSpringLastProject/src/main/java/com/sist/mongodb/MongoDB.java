package com.sist.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.util.*;

public class MongoDB {
	private MongoClient mc;				// 오라클의 Connection
	
	private DB db;						// 오라클의 XE
	
	private DBCollection dbc;			// 테이블
	
	public MongoDB(){
		try {
			mc = new MongoClient("localhost", 27017);
			db = mc.getDB("mydb");
			dbc = db.getCollection("tour");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/*
	 * NO            NUMBER         
		NAME          VARCHAR2(1000) 
		IMG           VARCHAR2(1000) 
		CONTENT       CLOB           
		ZONE          VARCHAR2(100)  
		ADDR          VARCHAR2(1000) 
		VISIT_ROAD    CLOB           
		LATITUDE      VARCHAR2(100)  
		LONGITUDE     VARCHAR2(100)  
		STAR          VARCHAR2(10)   
		TIME          VARCHAR2(20)   
		REGDATE       DATE           
		HIT           NUMBER         
		LOVE          NUMBER         
		TAG1          VARCHAR2(10)   
		TAG2          VARCHAR2(10)   
		TAG3          VARCHAR2(10)   
		EWSN          VARCHAR2(20) 
	 */
	// 몽고디비에 park 데이터 넣기
	public void InsertIntoMongoDB(){
		Oracle oracle = new Oracle();
		
		List<DogVO> oracle_list = oracle.getPark();
		
		for(DogVO vo : oracle_list){
			BasicDBObject obj = new BasicDBObject();
			obj.put("no", vo.getNo());
			obj.put("name", vo.getName());
			obj.put("img", vo.getImg());
			obj.put("content", vo.getContent());
			obj.put("zone", vo.getZone());
			obj.put("addr", vo.getAddr());
			obj.put("visit_road", vo.getVisit_road());
			obj.put("latitude", vo.getLatitude());
			obj.put("longitude", vo.getLongitude());
			obj.put("star", vo.getStar());
			obj.put("time", vo.getTime());
			obj.put("regdate", vo.getRegdate());
			obj.put("hit", vo.getHit());
			obj.put("love", vo.getLove());
			obj.put("tag1", vo.getTag1());
			obj.put("tag2", vo.getTag2());
			obj.put("tag3", vo.getTag3());
			obj.put("ewsn", vo.getEswn());
			
			System.out.println(vo.getNo());
			System.out.println(vo.getName());
			System.out.println("\n ============================ \n");
			dbc.insert(obj);
		}
	}
	
	
	
	
	/*
	 * TNO	NUMBER						// 여행지게시물 고유 번호
		CATENO	NUMBER					// 여행 주제별 번호  1.명소, 2.자연&관광, 3.엔터테인먼트, 4.쇼핑
		TITLE	VARCHAR2(1000 BYTE)		// 여행지명
		ADDRESS	VARCHAR2(1000 BYTE)		// 여행지 주소
		TEL	VARCHAR2(100 BYTE)			// 연락가능번호
		BHOUR	VARCHAR2(1000 BYTE)		// 영업시간
		BDAY	VARCHAR2(1000 BYTE)		// 영업일
		NOTICE	VARCHAR2(1000 BYTE)		// 이것만은 꼭! 공지사항
		TRANS	VARCHAR2(1000 BYTE)		// 교통수단, 오시는길
		PHOTO	VARCHAR2(1000 BYTE)		// 사진
		CONTENT	CLOB					// 여행지 상세 내용 설명
		POST	CLOB					// 후기
		BUDGET	NUMBER					// 여행 경비
		AGE	NUMBER						// 방문 연령대
		SITE	VARCHAR2(200 BYTE)		// 여행사이트 평점
		SEASON	VARCHAR2(34 BYTE)		// 방문 시기 (월, 계절)
		PEOPLE	VARCHAR2(34 BYTE)		// 동반인 분류
		THEMA	VARCHAR2(34 BYTE)		// 여행지 분류 ( 지역별, 테마별.....)
		TAG	VARCHAR2(1000 BYTE)			// 관련 태그
	 */
	// 몽고디비에 tour 넣기
	public void InsertTour(){
		Oracle oracle = new Oracle();
		List<TourVO> list = oracle.getTour();
		
		for(TourVO vo : list){
			BasicDBObject obj = new BasicDBObject();
			obj.put("tno", vo.getTno());
			obj.put("cateno", vo.getCateno());
			obj.put("title", vo.getTitle());
			obj.put("address", vo.getAddress());
			obj.put("tel", vo.getTel());
			obj.put("bhour", vo.getBhour());
			obj.put("bday", vo.getBday());
			obj.put("notice", vo.getNotice());
			obj.put("trans", vo.getTrans());
			obj.put("photo", vo.getPhoto());
			obj.put("content", vo.getContent());
			obj.put("post", vo.getPost());
			obj.put("budget", vo.getBudget());
			obj.put("age", vo.getAge());
			obj.put("site", vo.getSite());
			obj.put("season", vo.getSeason());
			obj.put("people", vo.getPeople());
			obj.put("thema", vo.getThema());
			obj.put("tag", vo.getTag());
			
			System.out.println(vo.getTno());
			System.out.println(vo.getTitle());
			System.out.println("\n ================ \n");
			
			dbc.insert(obj);
			
		}
	}
}
