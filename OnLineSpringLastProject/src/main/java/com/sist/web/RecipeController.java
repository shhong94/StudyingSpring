package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;


@Controller
public class RecipeController {

	@Autowired
	private RecipeDAO dao;
	
	
	// 레시피 리스트 ==================================================================================================================
	@RequestMapping("recipe/list.do")
	public String recipe_list(String page, Model model){
		
		if(page == null){
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		List<RecipeVO> list = dao.recipeListData(curpage);
		int count = dao.recipeCount();
		int totalpage =(int)(Math.ceil(count / 12.0));
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		
		return "recipe/list";
	}
	
	
	// 레시피 상세보기 ==================================================================================================================
	@RequestMapping("recipe/recipe_detail.do")
	public String recipe_detail(int no, Model model){
		RecipeDetailVO vo = dao.recipeDetailData(no);
		StringTokenizer st = new StringTokenizer(vo.getFoodmake(), "\n");
		while(st.hasMoreTokens()){
			vo.getmList().add(st.nextToken());
		}
		model.addAttribute("vo", vo);
		return "recipe/detail";
	}
	
	
	// 셰프 리스트 =====================================================================================================================================
	@RequestMapping("recipe/chef_list.do")
	public String recipe_chef_list(String page, Model model){
		if(page == null){
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		
		List<ChefVO> list = dao.chefListData(curpage);
		
		model.addAttribute("list", list);
		
		return "recipe/chef_list";
	}
	
	
	
	// 셰프 레시피 ============================================================================================================================
	@RequestMapping("recipe/chef_product.do")
	public String recipe_chef_product(String chef, Model model, String page, String fd){
		
		if(page == null){
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		if(fd == null){														// 검색하지 않으면
			list = dao.chefProductData(chef, curpage);
		}
		else{																// 검색을 하면
			list = dao.chefProductFindData(chef, fd);
		}
		
		
		model.addAttribute("chef", chef);
		model.addAttribute("list", list);
		
		return "recipe/chef_product";
	}
	
	
	
	
}
