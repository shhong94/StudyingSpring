package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;

@RestController
@CrossOrigin("http://211.238.142.195:3000")
public class ReactController {
	
	@Autowired
	private RecipeDAO dao;
	
	@RequestMapping("recipe/list.do")
	public String recipe_list(String page){
		if(page == null){
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		int rowSize = 20;
		int start = (rowSize * curpage) - (rowSize - 1);
		int end = (rowSize * curpage);
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list = dao.recipeListData(map);
		
		// JSON ============================================================
		String json = "";
		JSONArray arr = new JSONArray();
		for(RecipeVO vo : list){
			JSONObject obj = new JSONObject();
			obj.put("no", vo.getNo());
			obj.put("title", vo.getTitle());
			obj.put("poster", vo.getPoster());
			obj.put("chef", vo.getChef());
			arr.add(obj);
		}
		json = arr.toJSONString();
		System.out.println(json);
		
		
		return json;
	}
}
