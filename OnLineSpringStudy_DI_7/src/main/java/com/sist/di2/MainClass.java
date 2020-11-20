package com.sist.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class MainClass {
	
	@Autowired
	private MusicDAO dao;
	
	@Autowired
	private MovieDAO mDAO;

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app2.xml");
		
		MainClass mc = (MainClass)app.getBean("mainClass");
		
		List<MusicVO> list = mc.dao.musicListData();
		for(MusicVO vo : list){
			System.out.println(vo.getMno() + vo.getTitle() + vo.getSinger() + vo.getAlbum());
		}
		
	List<MovieVO> mList = mc.mDAO.movieListData();
	for(MovieVO mvo : mList){
		System.out.println(mvo.getTitle() + mvo.getActor());
	}
		
	

	}

}
