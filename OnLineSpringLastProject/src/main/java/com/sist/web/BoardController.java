package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;
import java.util.*;

@Controller
@RequestMapping("board/")
public class BoardController {

	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private FoodDAO fdao;
	
	// 게시글 목록 출력 =======================================================================================================================
	@RequestMapping("list.do")
	public String board_list(String page, Model model){
		
		if(page == null){
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		int totalpage = bdao.boardTotalPage();
		
		List<BoardVO> bList = bdao.boardListData(curpage);
		
		commonData(model);
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("bList", bList);
		
		return "board/list";
	}
	
	
	
	// 게시글 작성 페이지 =======================================================================================================================
	@RequestMapping("insert.do")
	public String insert(Model model){
		
		commonData(model);
		
		return "board/insert";
	}
	
	// 게시글 작성 수행 =======================================================================================================================
	@RequestMapping("insert_ok.do")
	public String insert_ok(BoardVO vo){
		
		bdao.boardInsert(vo);
		
		return "redirect:../board/list.do";
	}
	
	
	// 상세보기 ==============================================================================================================================
	@RequestMapping("detail.do")
	public String board_detail(int no, Model model){
		
		BoardVO vo = bdao.boardDetailData(no, 1);							// 타입이 1이면 그냥 상세보기 (조회수 증가)
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	
	
	
	// 게시글 수정 ============================================================================================================================
	@RequestMapping("update.do")
	public String board_update(int no, Model model){
		BoardVO vo = bdao.boardDetailData(no, 2);							// 타입이 1이 아니면 조건문 건너띄고 수정 전 상세보기 등 (조회수 증가 안됨)
		
		commonData(model);
		
		model.addAttribute("vo", vo);
		return "board/update";
	}
	
	
	
	// 게시글 삭제 ============================================================================================================================
	@RequestMapping("delete.do")
	public String board_delete_ok(int no, Model model){
		commonData(model);
		model.addAttribute("no", no);
		return "board/delete";
	}
	
	
	/*
	 * ◆ 2차 프로젝트에서 html을 통째로 ajax를 통해 보내는 방법 두 가지
	 * 
	 * 1. 타일즈의 name에 해당되지 않는 것을 RequestMapping으로 설정하면 타일즈가 아니라 ViewResolver에서 jsp파일을 찾음 
	 * 		(근데 ViewResolver에서 찾으려면 jsp파일의 경로가 WEB-INF가 아니라 webapp 아래에 있어햐 하므로 2번째 방법이 더 용이할 수 있음)
	 * 
	 * 2. 또는 타일즈에 이 메소드의 리턴값을 등록하여 해당 jsp파일만 출력하도록 설정해야 함 (이 프로젝트에서는 2번 방법 적용 중)
	 * 
	 */
	// 몽고디비 게시글 상세보기 화면의 우측 지도 클릭시 지도에 해당하는 맛집 출력 ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★ AJAX
	@RequestMapping("food_find.do")						
	public String food_find(String no, Model model){		
		System.out.println("지도클릭시 음식점 출력 : " + no);
		String[] gu = { "전체", "강서구", "양천구", "구로구", "마포구", "영등포구", "금천구",
				"은평구", "서대문구", "동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구",
				"성북구", "도봉구", "동대문구", "성동구", "강남구", "노원구", "중랑구", "광진구", "송파구",
				"강동구" };
		
		List<FoodVO> list = new ArrayList<FoodVO>();
		
		if(no != null){															// no가 널이 아닐 때 (지도를 클릭했을 때) 배열의 인덱스에 해당하는 지역의 음식점 리스트 가져오기
			list = fdao.foodLocationFindData(gu[Integer.parseInt(no)]);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("count", list.size());								// 지역에 해당되는 음식점이 디비에 없을 때 검색결과가 없음을 알려주는 용도

		
		return "food_find";
	}
	
	
	
	
	// 음식점과 레시피 탑5 출력 ================================================================================================================================================
	public void commonData(Model model){
		List<FoodVO> fList = fdao.foodTop5();					// 모든 메소드에서 공통으로 출력할 부분은 메소드화해서 사용
		List<RecipeVO> rList = fdao.recipeTop5();
		
		model.addAttribute("fList", fList);
		model.addAttribute("rList", rList);
	}
}
