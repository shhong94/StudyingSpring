package com.sist.di;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class MusicModel {
	
	@Autowired								//  �ڵ�����		(getBean ��� �ȵ�)
	private MusicDAO dao;
	
	@RequestMapping("music/music.do")
	public String music(HttpServletRequest request){
		
		List<MusicVO> list = dao.musicAllData();
		
		return "music/music";
	}
}
