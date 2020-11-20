package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
public interface BoardMapper {

	@Select("SELECT no, subject, name, regdate, hit, gt, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, gt, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit, gt "
			+ "FROM srboard3 ORDER BY gi DESC, gs ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	
	
	// 글쓰기 ===============================================================================================================================================================================
	@SelectKey(keyProperty="no", resultType=int.class, before=true, statement="SELECT NVL(MAX(no)+1, 1) as no FROM srboard3")		// 자동증가번호
	@Insert("INSERT INTO srboard3(no, name, subject, content, pwd, gi) "
			+ "VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd}, (SELECT NVL(MAX(gi)+1, 1) FROM srboard3))")
	public void boardInsert(BoardVO vo);
	
	
	// 상세보기 ===============================================================================================================================================================================
	@Update("UPDATE srboard3 SET hit = hit + 1 WHERE no = #{no}")
	public void boardHitIncrement(int no);
	@Select("SELECT no, name, subject, content, regdate, hit FROM srboard3 WHERE no = #{no}")
	public BoardVO boardDetailData(int no);
	
	
	// 답변 올리기 ===============================================================================================================================================================================
	@Select("SELECT gi, gs, gt FROM srboard3 WHERE no = #{no}")				// 상위 글의 gi, gs, gt 가져오기 (DAO에서 처리할 예정)
	public BoardVO boardParentData(int no);
	
	@Update("UPDATE srboard3 SET gs = gs + 1 WHERE gi = #{gi} AND gs > #{gs}")			// 상위글과 그룹이 같고 그룹스텝이 큰 게시글들의 gs 증가
	public void boardGsIncrement(BoardVO vo);
	
	@SelectKey(keyProperty="no", resultType=int.class, before=true, statement="SELECT NVL(MAX(no)+1, 1) as no FROM srboard3")
	@Insert("INSERT INTO srboard3(no, name, subject, content, pwd, gi, gs, gt, root) VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd}, #{gi}, #{gs}, #{gt}, #{root})")			// 답변달기
	public void boardReplyInsert(BoardVO vo);
	
	@Update("UPDATE srboard3 SET depth = depth + 1 WHERE no = #{no}")			// depth 증가
	public void boardDepthIncrement(int no);
	
}
