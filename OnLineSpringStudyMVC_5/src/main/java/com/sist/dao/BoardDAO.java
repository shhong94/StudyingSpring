package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class BoardDAO {

	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	
	
	public int boardTotalPage(){
		return mapper.boardTotalPage();
	}
	
	
	public BoardVO boarddetailData(int no){
		mapper.boardHitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	public void boardInsert(BoardVO vo){
		mapper.insertBoard(vo);
	}
}
