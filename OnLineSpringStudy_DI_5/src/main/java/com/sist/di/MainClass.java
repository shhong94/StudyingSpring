package com.sist.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;

public class MainClass {

	public static void main(String[] args) {
		
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		
		MusicDAO dao = app.getBean("dao", MusicDAO.class);
		List<MusicVO> list = dao.musicAllData();
		for(MusicVO vo : list){
			System.out.println(vo.getTitle() + vo.getSinger() + vo.getAlbum());
		}

	}

}
