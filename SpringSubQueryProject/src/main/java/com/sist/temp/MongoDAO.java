package com.sist.temp;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.sist.dao.*;
import java.util.*;

@Repository
public class MongoDAO {
	
	private MongoClient mc; 																				// 오라클의 Connection
	private DB db;																							// 데이터베이스 (오라클의 XE)	
	private DBCollection dbc;																				// 테이블
	
	public MongoDAO(){
		try {
			mc = new MongoClient("localhost", 27017);														// 주소와 포트번호
			db = mc.getDB("mydb");
			dbc = db.getCollection("emp");																	// emp테이블 생성 (CREATE TABLE emp)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 몽고디비는 INSERT INTO...가 아니라 키:값을 바로 삽입함
	public void empInsert(EmpVO vo){
		BasicDBObject obj = new BasicDBObject();															// BasicDBObject : { } 오브젝트 하나를 만들어줌
		obj.put("empno", vo.getEmpno());
		obj.put("ename", vo.getEname());
		obj.put("job", vo.getJob());
		obj.put("hiredate", vo.getHiredate().toString());
		obj.put("dname", vo.getDname());
		obj.put("loc", vo.getLoc());
		
		dbc.insert(obj);
	}
	
	
	public List<EmpVO> empListData(int page){
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		int rowSize = 10;
		int skip = (rowSize * page) - rowSize;												// 몽고디비는 rownum 존재하지 않고, 0번부터 시작하기 때문에 이렇게 코딩해야함  (1페이지일 땐 버리는거 없이 출력, 2페이지일 땐 앞에 10개 버리고 출력, 3페이지는 앞에 20개 버리고 출력)
		DBCursor c = dbc.find().skip(skip).limit(rowSize);		
		while(c.hasNext()){
			BasicDBObject obj = (BasicDBObject)c.next();
			EmpVO vo = new EmpVO();
			vo.setEmpno(obj.getInt("empno"));
			vo.setEname(obj.getString("ename"));
			vo.setJob(obj.getString("job"));
			vo.setDname(obj.getString("dname"));
			vo.setLoc(obj.getString("loc"));
			list.add(vo);
		}
		c.close();
		
		return list;
	}
}
