package com.sist.web;

import java.util.*;
import com.sist.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MusicController {
	
	@Autowired
	private MusicDAO dao;
	
	@GetMapping("music/list.do")
	public String music_list(String page, Model model){
		
		if(page == null){
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		int rowSize = 10;
		int start = (rowSize * curpage) - (rowSize - 1);
		int end = rowSize * curpage;	
		int totalpage = dao.musicTotalPage();
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<MusicVO> list = dao.musicListData(map);
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		
		
		return "music/list";
	}
	
	@RequestMapping("movie2/main.do")
	public String movie2_main(){
		
		
		
		return "movie2/main";
	}
	
}
