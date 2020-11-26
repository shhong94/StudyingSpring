package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import java.util.*;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

import java.text.*;


@Repository
public class BoardDAO {

	private MongoClient mc;				// 오라클의 Connection
	
	private DB db;						// 오라클의 XE
	
	private DBCollection dbc;			// 테이블
	
	
	@Autowired
	private FoodDAO fdao;
	
	public BoardDAO(){
		try {
			mc = new MongoClient("localhost", 27017);
			db = mc.getDB("mydb");
			dbc = db.getCollection("board");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// 데이터 추가 =======================================================================================================================
	public void boardInsert(BoardVO vo){
		try {
			int max = 0;
			
			// DBCursor : ResultSet (여러 DBObject 묶음)
			// dbc.find() : ps.rxrcuteQuery("SELECT * FROM ~~~~");
			DBCursor cursor = dbc.find();
			
			// 자동 증가번호(시퀀스) (MAX(no)+1)
			while(cursor.hasNext()){
				BasicDBObject obj = (BasicDBObject)cursor.next();				// cursor.next() : rs.next()
				int no = obj.getInt("no");
				if(max < no){
					max = no;
				}
			}
			cursor.close();
			
			// INSERT INTO 문장
			BasicDBObject obj = new BasicDBObject();
			obj.put("no", max+1);
			obj.put("name", vo.getName());
			obj.put("subject", vo.getSubject());
			obj.put("content", vo.getContent());
			obj.put("pwd", vo.getPwd());
			obj.put("regdate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));				// regdate는 오늘 날짜로 디폴트
			obj.put("hit", 0);
			dbc.insert(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	// 데이터 읽기 =======================================================================================================================
	public List<BoardVO> boardListData(int page){
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			int rowSize = 10;
			int skip = (rowSize * page) - rowSize;					// 1페이지면 게시글 0개 스킵하고(버리고) 2페이지면 10개 스킵하고, 3페이지면 20개 스킵하고 목록 출력
			
			DBCursor cursor = dbc.find().skip(skip).limit(rowSize).sort(new BasicDBObject("no", -1));				// 1:ASC -1:DESC
			while(cursor.hasNext()){
				BasicDBObject obj = (BasicDBObject)cursor.next();
				BoardVO vo = new BoardVO();
				vo.setNo(obj.getInt("no"));
				vo.setSubject(obj.getString("subject"));
				vo.setName(obj.getString("name"));
				vo.setRegdate(obj.getString("regdate"));
				vo.setHit(obj.getInt("hit"));
				list.add(vo);
			}
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
		
		
	}
	
	// 상세보기 ============================================================================================================================
	public BoardVO boardDetailData(int no, int type){					
		BoardVO vo = new BoardVO();
		try {
			// 조건
			BasicDBObject where = new BasicDBObject();
			where.put("no", no);
			
			BasicDBObject obj = (BasicDBObject)dbc.findOne(where);		// SELECT * FROM board WHERE no = #{no}	 ==  findOne({no : 1})
			
			if(type == 1){																						// 타입이 1이면 그냥 상세보기 (조회수 증가), 1이 아니면 조건문 건너띄고 수정 전 상세보기 등 (조회수 증가 안됨)
				int hit = obj.getInt("hit");
				BasicDBObject up = new BasicDBObject("$set", new BasicDBObject("hit", hit + 1));
				dbc.update(where, up);
			}
			
			// 조회수 증가
			
			
			obj = (BasicDBObject)dbc.findOne(where);
			vo.setNo(obj.getInt("no"));
			vo.setName(obj.getString("name"));
			vo.setSubject(obj.getString("subject"));
			vo.setContent(obj.getString("content"));
			vo.setRegdate(obj.getString("regdate"));
			vo.setHit(obj.getInt("hit"));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return vo;
	}
	
	// 수정하기 ===============================================================================================================================
	public boolean boardUpdate(BoardVO vo){
		boolean bCheck = false;
		try {
			// 몽고디비에 저장된 비밀번호 읽기
			
			// 조건문
			BasicDBObject where = new BasicDBObject();
			where.put("no", vo.getNo());
			
			// 조건에 해당하는 {} 가져오기
			BasicDBObject obj = (BasicDBObject)dbc.findOne(where);				// DBCursor : {}묶음       BasicDBObject : {} 한개
			if(vo.getPwd().equals(obj.getString("pwd"))){
				bCheck = true;
				BasicDBObject updateObj = new BasicDBObject();					// 수정내용을 임시로 저장할 BasicDBObject
				updateObj.put("name", vo.getName());
				updateObj.put("subject", vo.getSubject());
				updateObj.put("content", vo.getContent());
				BasicDBObject update = new BasicDBObject("$set", updateObj);	// 
				
				dbc.update(where, update);										// where에 해당하는 오브젝트를 update 오브젝트처럼 바꿔라
			}
			else{
				bCheck = false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return bCheck;
	}
	
	// 삭제하기 ===============================================================================================================================
	public boolean board_delete(int no, String pwd){
		boolean bCheck = false;
		try {
			// 조건
			BasicDBObject where = new BasicDBObject();
			where.put("no", no);
			
			// 비밀번호 가져오기
			BasicDBObject obj = (BasicDBObject)dbc.findOne(where);
			
			if(pwd.equals(obj.getString("pwd"))){
				bCheck = true;
				dbc.remove(where);						// where에 해당하는 오브젝트를 삭제
			}
			else{
				bCheck = false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return bCheck;
	}
	
	
	
	// 총 페이지 ===============================================================================================================================
	public int boardTotalPage(){
		int total = 0;
		try {
			DBCursor cursor = dbc.find();
			total = cursor.count();					// SELECT COUNT(*) FROM ~~~~
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return (int)Math.ceil(total / 10.0);
	}
	
	// 찾기 ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★ 어려움
}
