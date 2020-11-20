package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.RecipeDAO;
import com.sist.dao.RecipeVO;

import java.util.*;

@Controller
public class RecipeController {

	@Autowired
	private RecipeDAO dao;

	// 사용자 요청 처리
	@RequestMapping("recipe/list.do")
	public String reciepe_list(String page, Model model){
		
		if(page == null){
			page="1";
		}
		
		int curpage = Integer.parseInt(page);
		int totalpage = dao.recipeTotalPage();
		int rowSize = 20;
		int start = (rowSize * curpage) - (rowSize - 1);
		int end = rowSize * curpage;
		
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list = dao.recipeListData(map);
		for(RecipeVO vo: list){
			String title = vo.getTitle();
			if(title.length() > 18){
				title = title.substring(0, 18);
				title = title + "...";
			}
			vo.setTitle(title);
		}
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		
		return "recipe/list";
	}

}
