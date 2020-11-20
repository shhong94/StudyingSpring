package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;

@Controller
public class MainController {
	
	@Autowired
	private StudentDAO dao;
	
	
	// 목록 출력
	@RequestMapping("main/list.do")
	public String main_list(Model model){
		
		List<StudentVO> list = dao.studentListData();
		model.addAttribute("list", list);
		
		return "list";
	}
	
	
	// 삽입
	@RequestMapping("main/insert.do")
	public String main_insert(){
		return "insert";
	}
	
	// 삽입 ok
	@RequestMapping("main/insert_ok.do")
	public String main_insert_ok(StudentVO vo){
		
		dao.studentInsert(vo);
		
		return "redirect:list.do";
	}
	
	
	
	// 삭제
	@RequestMapping("main/delete.do")
	public String main_delete(int hakbun){
		
		dao.studentDelete(hakbun);
		
		return "redirect:list.do";
	}
	
	
	
	
	// 수정하기 전에 내용 가져오기
	@RequestMapping("main/update.do")
	public String main_update(int hakbun, Model model){
		StudentVO vo = dao.studentDetailData(hakbun);
		model.addAttribute("vo", vo);
		return "update";
	}
	
	// 수정 ok
	@RequestMapping("main/update_ok.do")
	public String main_update_ok(StudentVO vo){
		dao.studentUpdate(vo);
		return "redirect:list.do";
	}
}
