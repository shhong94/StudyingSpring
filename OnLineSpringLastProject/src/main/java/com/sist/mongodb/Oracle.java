package com.sist.mongodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.sist.data.FoodCategoryVO;

public class Oracle {

	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@211.238.142.195:1521:XE";
	
	public Oracle(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getConnection(){
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

	public void disConnection(){
		try {
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
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
	// park 테이블에서 데이터 리스트 가져오기
	public List<DogVO> getPark(){
		List<DogVO> list = new ArrayList<DogVO>();
		try {
			getConnection();
			String sql = "SELECT no, name, img, content, zone, addr, visit_road, latitude, longitude, star, time, regdate, hit, love, tag1, tag2, tag3, ewsn FROM park";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				DogVO vo = new DogVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setImg(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setZone(rs.getString(5));
				vo.setAddr(rs.getString(6));
				vo.setVisit_road(rs.getString(7));
				vo.setLatitude(rs.getString(8));
				vo.setLongitude(rs.getString(9));
				vo.setStar(rs.getString(10));
				vo.setTime(rs.getString(11));
				vo.setRegdate(rs.getDate(12));
				vo.setHit(rs.getInt(13));
				vo.setLove(rs.getInt(14));
				vo.setTag1(rs.getString(15));
				vo.setTag2(rs.getString(16));
				vo.setTag3(rs.getString(17));
				vo.setEswn(rs.getString(18));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return list;
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
	// tort 테이블에서 데이터 가져오기
	public List<TourVO> getTour(){
		List<TourVO> list = new ArrayList<TourVO>();
		
		try {
			getConnection();
			String sql = "SELECT tno, cateno, title, address, tel, bhour, bday, notice, trans, photo, content, post, budget, age, site, season, people, thema, tag from tour";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				TourVO vo = new TourVO();
				vo.setTno(rs.getInt(1));
				vo.setCateno(rs.getInt(2));
				vo.setTitle(rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setTel(rs.getString(5));
				vo.setBhour(rs.getString(6));
				vo.setBday(rs.getString(7));
				vo.setNotice(rs.getString(8));
				vo.setTrans(rs.getString(9));
				vo.setPhoto(rs.getString(10));
				vo.setContent(rs.getString(11));
				vo.setPost(rs.getString(12));
				vo.setBudget(rs.getInt(13));
				vo.setAge(rs.getInt(14));
				vo.setSite(rs.getString(15));
				vo.setSeason(rs.getString(16));
				vo.setPeople(rs.getString(17));
				vo.setThema(rs.getString(18));
				vo.setTag(rs.getString(19));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return list;
	}
}
