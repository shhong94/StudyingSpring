package com.sist.dao;

import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import java.util.*;
import java.sql.*;

@Repository
public class StudentDAO {
	
	// PreparedStatement : 일반 SQL 전송시 사용
	// CallableStatement : 프로시저 호출시 사용
	
	
	private Connection conn;
	private CallableStatement cs;
	private final String URL = "jdbc:oracle:thin:@211.238.142.195:1521:XE";
	
	// 생성자
	public StudentDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 연결
	public void getConnection(){
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 해제
	public void disConnection(){
		try {
			if(cs != null){
				cs.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
/*
 * -- Total
CREATE OR REPLACE PROCEDURE studentListData(
    pResult OUT SYS_REFCURSOR  -- 이게 뭔지 모르겠다
)
IS
BEGIN
    OPEN pResult FOR 
        SELECT * FROM pl_student3;
END;
/
 */
	// 목록 출력
	public List<StudentVO> studentListData(){
		List<StudentVO> list = new ArrayList<StudentVO>();
		
		try {
			getConnection();
			
			String sql = "{CALL studentListData(?)}";
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);  						// registerOutParameter : OUT 파라미터를 받을 수 있는 임시공간 생성
																					/*
																					 * int >>> OracleTypes.INTEGER
																					 * String >>> OracleTypes.VARCHAR
																					 * double >>> dOracleTypes.DOUBLE
																					 * Date >>> OracleTypes.DATE
																					 * ResultSet >>> OracleTypes.CURSOR
																					 */
			cs.executeQuery();
			ResultSet rs = (ResultSet)cs.getObject(1);								// CURSOR일 경우에는 ResultSet으로 받아야 함
			while(rs.next()){
				StudentVO vo = new StudentVO();
				vo.setHakbun(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setKor(rs.getInt(3));
				vo.setEng(rs.getInt(4));
				vo.setMath(rs.getInt(5));
				vo.setAvg(rs.getDouble(7));
				vo.setTotal(rs.getInt(6));
				list.add(vo);
			}
			rs.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		
		return list;
	}
	
	
	
	
	// 삽입
	public void studentInsert(StudentVO vo){
		try {
			getConnection();
			String sql = "{CALL studentInsert(?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, vo.getName());
			cs.setInt(2, vo.getKor());
			cs.setInt(3, vo.getEng());
			cs.setInt(4, vo.getMath());
			cs.executeQuery(); 										// 프로시저는 SELECT문에서 호출하기 때문에 executeUpdate()가 아니라, executeQuery()임    (커밋은 프로시저 구현부에 작성되어 있음)
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
	
	
	
	
	// 삭제
	public void studentDelete(int hakbun){
		try {
			getConnection();
			String sql = "{CALL studentDelete(?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, hakbun);
			cs.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
	
	
	
	// 수정할 때 내용 가져오기
	public StudentVO studentDetailData(int hakbun){
		StudentVO vo = new StudentVO();
		try {
			getConnection();
			String sql = "{CALL studentDetailData(?, ?, ?, ?, ?}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, hakbun);
			cs.registerOutParameter(2, OracleTypes.VARCHAR);
			cs.registerOutParameter(3, OracleTypes.INTEGER);
			cs.registerOutParameter(4, OracleTypes.INTEGER);
			cs.registerOutParameter(5, OracleTypes.INTEGER);
			cs.executeQuery();
			vo.setName(cs.getString(2));
			vo.setKor(cs.getInt(3));
			vo.setEng(cs.getInt(4));
			vo.setMath(cs.getInt(5));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
		return vo; 
	}
	
	
	// 수정
	public void studentUpdate(StudentVO vo){
		try {
			getConnection();
			String sql = "{CALL studentUpdate(?, ?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, vo.getHakbun());
			cs.setString(2, vo.getName());
			cs.setInt(3, vo.getKor());
			cs.setInt(4, vo.getEng());
			cs.setInt(5, vo.getMath());
			cs.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnection();
		}
	}
	
	
	
	
	
}
