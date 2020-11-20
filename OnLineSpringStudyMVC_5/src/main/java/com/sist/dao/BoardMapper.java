package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.*;

public interface BoardMapper {
	
	// 게시판 목록 출력---------------------------------------------------------------------------------
	@Select("SELECT no, subject, name, regdate, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM spring_board3 ORDER BY 1 DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	@Select("SELECT CEIL(COUNT(*) / 10.0) FROM spring_board3")
	public int boardTotalPage();
	
	
	// 게시글 쓰기----------------------------------------------------------------------------------------------------
	@SelectKey(keyProperty="no", resultType=int.class, before=true,
			statement="SELECT NVL(MAX(no)+1, 1) as no FROM spring_board3")
	
	@Insert("INSERT INTO spring_board3(no, name, subject, content, pwd) VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd})")
	public void insertBoard(BoardVO vo);
	
	// 상세보기----------------------------------------------------------------------------------------------------
	@Update("UPDATE spring_board3 SET hit = hit+1 WHERE no = #{no}")
	public void boardHitIncrement(int no);
	@Select("SELECT no, name, subject, content, regdate, hit FROM spring_board3 WHERE no = #{no}")
	public BoardVO boardDetailData(int no);
	
	// 수정
	
	// 삭제-----------------------------------------------------------------------------------------------------------
	@Delete("DELETE FROM spring_board3 WHERE no = #{no}")
	public void boardDelete(int no);
}
