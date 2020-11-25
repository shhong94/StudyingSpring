package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;
import java.util.*;

@Controller
public class BoardController {

	@Autowired
	private BoardDAO bdao;
	
	// 게시글 출력 =======================================================================================================================
	@RequestMapping("board/list.do")
	public String board_list(String page, Model model){
		
		if(page == null){
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		List<BoardVO> bList = bdao.boardListData(curpage);
		
		model.addAttribute("bList", bList);
		
		return "board/list";
	}
	
	
	
	// 게시글 작성 페이지 =======================================================================================================================
	@RequestMapping("board/insert.do")
	public String insert(){
		
		
		
		return "board/insert";
	}
	
	// 게시글 작성 수행 =======================================================================================================================
	@RequestMapping("board/insert_ok.do")
	public String insert_ok(BoardVO vo){
		
		bdao.boardInsert(vo);
		
		return "redirect:../board/list.do";
	}
	
	
	// 상세보기 ==============================================================================================================================
	@RequestMapping("board/detail.do")
	public String board_detail(int no, Model model){
		
		BoardVO vo = bdao.boardDetailData(no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
}
