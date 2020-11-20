package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import com.sist.dao.*;

@Controller
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	
	// 게시판 목록 출력 ================================================================
	@GetMapping("board/list.do")
	public String board_list(String page, Model model){
		
		if(page == null){
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		int rowSize = 15;
		int start = (rowSize * curpage) - (rowSize - 1);
		int end = (rowSize * curpage);
		
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list = dao.boardListData(map);
		model.addAttribute("list", list);
		
		return "board/list";
	}
	
	// insert 페이지 이동 ============================================================
	@GetMapping("board/insert.do")
	public String board_insert(){
		return "board/insert";
	}
	
	// insert 수행 ==================================================================
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo){
		
		dao.boardInsert(vo);
		
		return "redirect:../board/list.do";
	}
	
	
	// 상세보기 ======================================================================
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model){
		
		BoardVO vo = dao.boardDetailData(no);
		
		model.addAttribute("vo", vo);
		
		return "board/detail";
	}
	
	
	
	// 답변 페이지로 이동 ==============================================================================
	@GetMapping("board/reply.do")
	public String board_reply(int no, Model model){
		model.addAttribute("no", no);
		
		return "board/reply";
	}
	
	// 답변 수행 ====================================================================================
	@PostMapping("board/reply_ok.do")
	public String board_reply_ok(int pno, BoardVO vo){
		
		dao.boardReplyInsert(pno, vo);
		
		return "redirect:../board/list.do";
	}
}
