package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MusicDAO {
	
	@Autowired
	private MusicMapper mapper;
	
	
	
	// 목록 출력
	public List<MusicVO> musicListData(Map map){
		return mapper.musicListData(map);
	}
	
	
	
	// 상세보기
	public MusicVO musicDetailData(int mno){
		return mapper.musicDetailData(mno);
	}
	
	
	// 총페이지
	public int musicTotalPage(){
		return mapper.musicTotalPage();
	}
}
