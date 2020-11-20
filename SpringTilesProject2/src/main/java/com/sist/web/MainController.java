package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@Controller
public class MainController {

	@Autowired
	private MovieDAO dao;

	
	
	@RequestMapping("main/main.do")
	public String main_main(String page, Model model){
		
		if(page == null){
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		int rowSize = 12;
		int start = (rowSize * curpage) - (rowSize - 1);
		int end = (rowSize * curpage);
		int totalpage = dao.movieTotalPage();
				
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<MovieVO> mList = dao.movieListData(map);
		
		for(MovieVO vo : mList){
			String s = vo.getTitle();
			if(s.length() > 20){
				s = s.substring(0, 17) + "...";
				vo.setTitle(s);
			}
		}
		
		List<MovieVO> tList = dao.movieTop5Hit();
		
		model.addAttribute("mList", mList);
		model.addAttribute("tList", tList);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		return "main";
	}
}
