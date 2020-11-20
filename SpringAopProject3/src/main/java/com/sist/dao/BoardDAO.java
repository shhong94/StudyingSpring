package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class BoardDAO {

	@Autowired
	private BoardMapper mapper;

	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	
	public void boardInsert(BoardVO vo){
		mapper.boardInsert(vo);
	}
	
	
	// 상세보기
	public BoardVO boardDetailData(int no){
		
		mapper.boardHitIncrement(no);
		
		return mapper.boardDetailData(no);
	}
	
	
	// 답변달기 ========================================================================================================================
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void boardReplyInsert(int root, BoardVO vo){
		BoardVO pvo = mapper.boardParentData(root);				// no가 root인 상위글의 gi, gs, gt 가져와서 pvo에 담기 (parent VO)
		mapper.boardGsIncrement(pvo); 							// gs 증가
		vo.setGi(pvo.getGi());									// 답변(vo)의 gi gs gt 수정한 뒤에 답변을 삽입해야 함
		vo.setGs(pvo.getGs()+1);
		vo.setGt(pvo.getGt()+1);
		mapper.boardReplyInsert(vo);							// 답변(vo) 달기
		mapper.boardDepthIncrement(root);
	}
}
