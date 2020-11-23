package com.sist.data;

import org.springframework.stereotype.Repository;
import java.util.*;
import java.sql.*;


public class FoodDAO {

	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@211.238.142.195:1521:XE";
	public FoodDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
	
	// food_category7 테이블에 데이터 넣는 메소드 =======================================================================================
	public void foodCategoryInsert(FoodCategoryVO vo){
		try {
			getConnection();
			String sql = "INSERT INTO food_category7 VALUES(?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getPoster());
			ps.setString(4, vo.getSubject());
			ps.setString(5, vo.getLink());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			disConnection();
		}
	}
	
	
	
	// food_detail7 테이블에 데이터 넣는 메소드 ==========================================================================================
	public void foodDetailInsert(FoodVO vo){
		try {
			getConnection();
			String sql = "INSERT INTO food_detail7 VALUES((SELECT NVL(MAX(no)+1, 1) FROM food_detail7), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getCateno());
			ps.setString(2, vo.getPoster());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getScore());
			ps.setString(5, vo.getAddr());
			ps.setString(6, vo.getTel());
			ps.setString(7, vo.getType());
			ps.setString(8, vo.getPrice());
			ps.setString(9, vo.getMenu());
			ps.setInt(10, vo.getGood());
			ps.setInt(11, vo.getSoso());
			ps.setInt(12, vo.getBad());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			disConnection();
		}
	}
	
	
	// 망고플레이트의 카테고리 목록이 실시간 바뀌기 때문에, 카테고리와 맞는 맛집을 디비에서 가져옴 =================================================================================================
	public List<FoodCategoryVO> foodCategoryListData(){
		List<FoodCategoryVO> list = new ArrayList<FoodCategoryVO>();
		try {
			getConnection();
			String sql = "SELECT no, link FROM food_category7";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				FoodCategoryVO vo = new FoodCategoryVO();
				vo.setNo(rs.getInt(1));
				vo.setLink(rs.getString(2));
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			disConnection();
		}
		return list;
	}
}
