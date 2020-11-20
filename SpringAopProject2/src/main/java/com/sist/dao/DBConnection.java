package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnection {
	private Connection conn;
	private PreparedStatement ps;
	private String driver, url, username, password;
	
	
	// 생성자 ==========================================================================================
	public DBConnection(String driver, String url, String username, String password){
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
		
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 연결 ==========================================================================================
	public void getConnection(){
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 연결 해제 ==========================================================================================
	public void disConnection(){
		try {
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 게터 세터  ==========================================================================================
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public PreparedStatement getPs() {
		return ps;
	}
	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}
	
}
