package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;

// 리액트와 스프링을 연동하는 서버 (리액트는 ArrayList를 못 받기 때문에 JSON으로 전송해야 함) ====================================
@RestController
@CrossOrigin("http://localhost:3000")															// 리액트의 포트번호인 3000번은 이 스프링 서버에 접근이 가능하게 설정
public class ReactController {

	@Autowired
	private RecipeDAO dao;
	
	
	
	// 리액트로 셰프 리스트 전송 ===========================================================================================================================================
	@RequestMapping(value="react_chef/chef_list.do", produces="text/plain;charset=UTF-8")		// 스프링에서 리액트로 전송시 한글 깨짐 방지
	public String chef_list(String page){														// JSON으로 넘기기 때문에 Model이 필요 없음
		if(page == null){
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		List<ChefVO> list = dao.chefListData(curpage);
		
		String json = "";
		try {
			JSONArray arr = new JSONArray();						// [ {}, {}, {}... ]
			for(ChefVO vo : list){
				JSONObject obj = new JSONObject();					// {}
				obj.put("poster", vo.getPoster());
				obj.put("chef", vo.getChef());
				obj.put("mc1", vo.getMc1());
				obj.put("mc3", vo.getMc3());
				obj.put("mc7", vo.getMc7());
				obj.put("mc2", vo.getMc2());
				arr.add(obj);
			}
			
			json = arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	// 리액트로 셰프 리스트의 총 페이지 전송 ===========================================================================================================================================
	@RequestMapping("react_chef/totalpage.do")
	public String chef_total(){
		
		int total = 0;
		try {
			total = dao.chefTotalPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return String.valueOf(total);
	}
	
	
	
	// 리액트로 셰프 상세페이지 전송 ===========================================================================================================================================
	@RequestMapping(value="react_chef/chef_detail.do", produces="text/plain;charset=UTF-8")
	public String chef_detail(String chef, String page){
		String result = "";
		if(page == null){
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		
		List<RecipeVO> list = dao.chefProductData(chef, curpage);
		
		try {
			JSONArray arr = new JSONArray();
			for(RecipeVO vo : list){
				JSONObject obj = new JSONObject();
				obj.put("poster", vo.getPoster());
				obj.put("title", vo.getTitle());
				obj.put("chef", vo.getChef());
				arr.add(obj);
			}
			
			result = arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	// 리액트로 셰프 리스트 전송 ===========================================================================================================================================
	@RequestMapping(value="react_chef/chef_find.do", produces="text/plain;charset=UTF-8")		// 스프링에서 리액트로 전송시 한글 깨짐 방지
	public String chef_find(String chef, String fd){														// JSON으로 넘기기 때문에 Model이 필요 없음
		
		List<RecipeVO> list = dao.chefProductFindData(chef, fd);
		
		String json = "";
		try {
			JSONArray arr = new JSONArray();						// [ {}, {}, {}... ]
			for(RecipeVO vo : list){
				JSONObject obj = new JSONObject();
				obj.put("poster", vo.getPoster());
				obj.put("title", vo.getTitle());
				obj.put("chef", vo.getChef());
				arr.add(obj);
			}
			
			json = arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	
	
	// 리액트 hooks ==============================================================================================================================================================
	
	// 레시피 목록 출력 =====================================================================================================================
	@RequestMapping(value="react_recipe/recipe_list.do", produces="text/plain;charset=UTF-8")		// 스프링에서 리액트로 전송시 한글 깨짐 방지
	public String recipe_list(String page){														// JSON으로 넘기기 때문에 Model이 필요 없음
		if(page == null){
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		List<RecipeVO> list = dao.recipeListData(curpage);
		
		String json = "";
		try {
			JSONArray arr = new JSONArray();						// [ {}, {}, {}... ]
			for(RecipeVO vo : list){
				JSONObject obj = new JSONObject();					// {}
				obj.put("poster", vo.getPoster());
				obj.put("title", vo.getTitle());
				obj.put("chef", vo.getChef());
				arr.add(obj);
			}
			
			json = arr.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	// 리액트로 셰프 리스트의 총 페이지 전송 ===========================================================================================================================================
	@RequestMapping("recipe/totalpage.do")
	public String recipe_total(){
		
		int total = 0;
		try {
			int count = dao.recipeCount();
			total = (int)(Math.ceil(count / 12.0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return String.valueOf(total);
	}
}
