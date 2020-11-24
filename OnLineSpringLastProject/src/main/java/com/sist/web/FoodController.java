package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.FoodDAO;
import com.sist.naver.NaverManager;
import com.sist.dao.*;
import java.util.*;

@Controller
@RequestMapping("food/")
// lox5F6f7KmqWjUI1iezM
// XoqBgcOuW0
public class FoodController {

	@Autowired
	private FoodDAO fdao;
	
	@Autowired
	private NaverManager nm;																								// 네이버 검색 기능
	
	@Autowired
	private RManager rm;
	
	// 메인페이지에 카테고리 출력 =========================================================================================
	@RequestMapping("list.do")
	public String food_list(Model model){
		
		List<FoodCategoryVO> list = fdao.foodCategoryAllData();
		model.addAttribute("list", list);
		
		return "food/list";
	}
	
	
	// 카테고리별로 음식 출력 ============================================================================================
	@RequestMapping("food_category.do")
	public String food_category(int no, Model model){
		List<FoodVO> list = fdao.foodCategoryListData(no);
		for(FoodVO vo : list){										// 포스터 하나만 가져오기
			String s = vo.getPoster();				
			s = s.substring(0, s.indexOf("^"));
			vo.setPosterOne(s);
			
			String ss = vo.getAddr();
			StringTokenizer st = new StringTokenizer(ss, "지");
			vo.setAddr1(st.nextToken());
			
			String sss = st.nextToken();
			sss = sss.substring(2);
			vo.setAddr2(sss);
		}
		FoodCategoryVO vo = fdao.foodCategoryInfoData(no);			// 해당 카테고리의 주제목, 부제목
		
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		return "food/category";
	}
	
	
	
	// 상세보기 =========================================================================================================
	@RequestMapping("food_detail.do")
	public String food_detail(int no, Model model){
		
		FoodVO vo = fdao.FoodDetailData(no);
		
		String s = vo.getAddr();								// 도로명과 지번으로 주소 나누기
		StringTokenizer st = new StringTokenizer(s, "지");
		vo.setAddr1(st.nextToken());
		vo.setAddr2("지" + st.nextToken());
		
		
		String type = vo.getType();								// 음식 종류
		st = new StringTokenizer(type, "/");
		String result = "";
		while(st.hasMoreTokens()){
			result += st.nextToken().trim() + "|";
		}
		result = result.substring(0, result.lastIndexOf("|"));
		List<RecipeVO> list = fdao.foodLikeRecipeData(result);
		
		
		nm.naverData(vo.getTitle());							// 상세보기하면 네이버검색결과(제목) txt파일로 저장하는 메소드
		
		rm.graph(no); 											// 워드클라우드를 생성하는 RManager의 메소드
		
		
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		
		return "food/detail";
	}
}
