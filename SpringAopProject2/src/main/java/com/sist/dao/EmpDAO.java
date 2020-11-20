package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAO {
	
	@Autowired
	private DBConnection dbconn;
	
	private PreparedStatement ps;
	private Connection conn;
	
	
	
	public EmpDAO(){
		
	}
	
	
	
	
	
	public List<EmpVO> empListData(){
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		// @Before
		
		try {
			// @Around
			// 실행 전 setAutoCommit(false)
			// DML
			// 실행 후 commit
			
			String sql = "SELECT empno, ename, job, hiredate, sal, (SELECT dname FROM dept d WHERE d.deptno = e.deptno) as dname, (SELECT loc FROM dept d WHERE d.deptno = e.deptno) as loc FROM emp e";
			ps = dbconn.getConn().prepareStatement(sql);
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
		
		return list;	// @After-Returning
	}
	
	
	
	public EmpVO empDetailData(int empno){
		EmpVO vo = new EmpVO();
		
		try {
			
			String sql = "SELECT empno, ename, job, hiredate, sal, (SELECT dname FROM dept d WHERE d.deptno = e.deptno) as dname, (SELECT loc FROM dept d WHERE d.deptno = e.deptno) as loc FROM emp e WHERE empno = ?";
			ps = dbconn.getConn().prepareStatement(sql);
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
		
		return vo;
	}
	
	
	
	
	// 트랜잭션 관련 
	public void deptInsert(int deptno, String dname, String loc){
		try {
			dbconn.getConnection();
			conn = dbconn.getConn();
			conn.setAutoCommit(false);								// 오토커밋 false
			
			String sql = "INSERT INTO dept3 VALUES(?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, deptno);
			ps.setString(2, dname);
			ps.setString(3, loc);
			ps.executeUpdate();
			
			sql = "INSERT INTO dept3 VALUES(?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, deptno);
			ps.setString(2, dname);
			ps.setString(3, loc);
			ps.executeUpdate();
			
			conn.commit();											// sql문 전부 성공했을 때 커밋
			
		} catch (Exception e) {
				try {
					conn.rollback();								// 위 sql문 하나라도 오류발생시 롤백
				} catch (Exception e2) {
					System.out.println(e.getMessage());
				}
			System.out.println(e.getMessage());
		}
		finally {
				try {												// sql문이 전부 성공했으면 오토커밋 true
					conn.setAutoCommit(true);
				} catch (Exception e2) {
					
				}
			dbconn.disConnection();
		}
	}
	
	
}
