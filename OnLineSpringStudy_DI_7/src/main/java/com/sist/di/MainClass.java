package com.sist.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class MainClass {

	public static void main(String[] args) {
			ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
			MusicDAO dao = app.getBean("musicDAO", MusicDAO.class);
			
			List<MusicVO> list = dao.musicListData();
			for(MusicVO vo : list){
				System.out.println(vo.getMno() + vo.getTitle() + vo.getSinger() + vo.getAlbum());
			}
	}

}
