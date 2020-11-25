package com.sist.dao;
import java.util.*;
import java.sql.*;

import org.apache.ibatis.annotations.Results;
import org.springframework.stereotype.Repository;

import oracle.jdbc.driver.OracleTypes;

@Repository
public class ReplyDAO {
	
	private Connection conn;
	private CallableStatement cs;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@211.238.142.195:1521:XE";
	
	public ReplyDAO(){
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
			e.printStackTrace();
		}
	}
	
	
	
	public void disConnection(){
		try {
			if(cs != null){
				cs.close();
			}
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
	
	
	// 로그인 메소드 (LoginRestController에서 처리) =========================================================================================================================
	public String isLogin(String id, String pwd){
		String result = "";
		
		try {
			getConnection();
			String sql = "SELECT COUNT(*) FROM member3 WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			if(count == 0){
				result = "NOID";
				System.out.println("아이디없음");
			}
			else{
				sql = "SELECT pwd, name FROM member3 WHERE id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				rs =  ps.executeQuery();
				rs.next();
				String db_pwd = rs.getString(1);
				String name = rs.getString(2);
				rs.close();
				
				if(db_pwd == pwd){
					result = id + "|" + name;											// id와 name 담기
				}
				else{
					result = "NOPWD";
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			disConnection();
		}
		
		return result;
	}
	
	/*
	 * create or replace PROCEDURE replyInsert3(
		    pType project_reply3.type%TYPE,
		    pCno project_reply3.cno%TYPE,	
		    pId project_reply3.id%TYPE,
		    pName project_reply3.name%TYPE,
		    pMsg project_reply3.msg%TYPE
		)
		IS
		    vNo project_reply3.no%TYPE;
		BEGIN
		    SELECT NVL(MAX(no)+1, 1) INTO vNo FROM project_reply3;
		    INSERT INTO project_reply3(no, type, cno, id, name, msg) VALUES(vNo, pType, pCno, pId, pName, pMsg);
		    COMMIT;
		END;

	 */
	
	// 댓글 작성 ===================================================================================================================================
	public void replyInsert(ReplyVO vo){
		try {
			getConnection();
			String sql = "{CALL replyInsert3(?, ?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, 1);
			cs.setInt(2, vo.getCno());
			cs.setString(3, vo.getId());
			cs.setString(4, vo.getName());
			cs.setString(5, vo.getMsg());
			cs.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			disConnection();
		}
	}
	
	
	
	
	/*
	 * create or replace PROCEDURE replyListData3(
		    pType project_reply3.type%TYPE,
		    pCno project_reply3.cno%TYPE,
		    pStart project_reply3.no%TYPE,
		    pEnd NUMBER,
		    pResult OUT SYS_REFCURSOR
		)
		IS
		BEGIN
		    OPEN pResult FOR
		        SELECT no, type, cno, id, name, msg, TO_CHAR(regdate, 'yyyy-MM-dd HH24:MI:SS'), num 
		        FROM (SELECT no, type, cno, id, name, msg, regdate, rownum as num 
		        FROM (SELECT no, type, cno, id, name, msg, regdate 
		        FROM project_reply3 WHERE type = pType AND cno = pCno ORDER BY no DESC))
		        WHERE num BETWEEN pStart AND pEnd;
		END;

	 */
	// 댓글 목록 출력 ===================================================================================================================================
	public List<ReplyVO> replyListData(int cno, int page){
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		try {
			getConnection();
			String sql = "{CALL replyListData3(?, ?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, 1);
			cs.setInt(2, cno);
			int rowSize = 10;
			int start = (rowSize * page) - (rowSize - 1);
			int end = (rowSize * page);
			cs.setInt(3, start);
			cs.setInt(4, end);
			cs.registerOutParameter(5, OracleTypes.CURSOR);
			cs.executeQuery();
			ResultSet rs = (ResultSet)cs.getObject(5);				// 프로시저의 OUT변수 받기
			
			while(rs.next()){
				ReplyVO vo = new ReplyVO();
				vo.setNo(rs.getInt(1));
				vo.setType(rs.getInt(2));
				vo.setCno(rs.getInt(3));
				vo.setId(rs.getString(4));
				vo.setName(rs.getString(5));
				vo.setMsg(rs.getString(6));
				vo.setDbday(rs.getString(7));
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
	
	
	
	/*
	 * create or replace PROCEDURE replyDelete3(
		    pNo project_reply3.no%TYPE
		)
		IS
		BEGIN
		    DELETE FROM project_reply3 WHERE no = pNo;
		    COMMIT;
		END;

	 */
	
	// 댓글 삭제 ===================================================================================================================================================
	public void reply_delete(int no){
		try {
			getConnection();
			String sql = "{CALL replyDelete3(?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.executeQuery();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			disConnection();
		}
	}
	
	
	
	/*
	 * create or replace PROCEDURE replyUpdate3(
		    pNo project_reply3.no%TYPE,
		    pMsg project_reply3.msg%TYPE
		)
		IS
		BEGIN
		    UPDATE project_reply3 SET msg = pMsg WHERE no = pNo;
		    COMMIT;
		END;

	 */
	// 댓글 수정 ===================================================================================================================================================
	public void reply_update(int no, String msg){
		try {
			getConnection();
			String sql = "{CALL replyUpdate3(?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.setString(2, msg);
			cs.executeQuery();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			disConnection();
		}
	}
	
}
