package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;

@Controller
public class MemberController {

	@Autowired
	private MemberDAO dao;
	
	@RequestMapping("member/login.do")
	public String member_login(){
		return "member/login";
	}
	
	@RequestMapping("member/login_ok.do")
	public String member_login_ok(String id, String pwd, HttpSession session, Model model){
		
		String result = dao.isLogin(id, pwd);
		
		if(result == "OK"){							// 결과가 OK면 세션에 등록
			session.setAttribute("id", id);
		}
		model.addAttribute("result", result);
		
		return "member/login_ok";
	}
}
