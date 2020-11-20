package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAO {
	
	@Autowired
	private MyDataSource ds;
	
	private Connection conn;
	private PreparedStatement ps;
	
	
	public EmpDAO(){
		try {
			Class.forName(ds.getDriverClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
// 반복구간 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 반복구간
	public void getConnection(){
		try {
			conn = DriverManager.getConnection(ds.getUrl(), ds.getUsername(), ds.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
// 반복구간 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 반복구간
	
	
	
	public List<EmpVO> empListData(){
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		// @Before
		
		try {
			// @Around
			// 실행 전 setAutoCommit(false)
			// DML
			// 실행 후 commit
			getConnection();
			String sql = "SELECT empno, ename, job, hiredate, sal, (SELECT dname FROM dept d WHERE d.deptno = e.deptno) as dname, (SELECT loc FROM dept d WHERE d.deptno = e.deptno) as loc FROM emp e";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				vo.setDname(rs.getString(6));
				vo.setLoc(rs.getString(7));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// @Atfer-Throwing (rollback)
			e.printStackTrace();
		}
		finally {
			// @After
			// setAutoCommit(true)
			disConnection();
		}
		
		return list;	// @After-Returning
	}
	
	
	
	public EmpVO empDetailData(int empno){
		EmpVO vo = new EmpVO();
		
		try {
			getConnection();
			String sql = "SELECT empno, ename, job, hiredate, sal, (SELECT dname FROM dept d WHERE d.deptno = e.deptno) as dname, (SELECT loc FROM dept d WHERE d.deptno = e.deptno) as loc FROM emp e WHERE empno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setEmpno(rs.getInt(1));
			vo.setEname(rs.getString(2));
			vo.setJob(rs.getString(3));
			vo.setHiredate(rs.getDate(4));
			vo.setSal(rs.getInt(5));
			vo.setDname(rs.getString(6));
			vo.setLoc(rs.getString(7));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return vo;
	}
	
	
}
