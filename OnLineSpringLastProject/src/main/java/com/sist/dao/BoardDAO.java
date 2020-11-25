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
			
			// DBCursor : ResultSet
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
	
	// 상세보기
	public BoardVO boardDetailData(int no){
		BoardVO vo = new BoardVO();
		try {
			// 조건
			BasicDBObject where = new BasicDBObject();
			where.put("no", no);
			
			BasicDBObject obj = (BasicDBObject)dbc.findOne(where);		// SELECT * FROM board WHERE no = #{no}	 ==  findOne({no : 1})
			
			// 조회수 증가
			int hit = obj.getInt("hit");
			BasicDBObject up = new BasicDBObject("$set", new BasicDBObject("hit", hit + 1));
			dbc.update(where, up);
			
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
	
	// 수정하기
	
	// 삭제하기
	
	// 찾기 ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★ 어려움
}
