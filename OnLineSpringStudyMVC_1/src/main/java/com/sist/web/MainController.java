package com.sist.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// MVC 패턴의 Model클래스
@Controller
public class MainController {
	
	// 아이디ㅡ, 패스워드 받기
	@RequestMapping("main/output.do")
	public String main_output(HttpServletRequest request){
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		request.setAttribute("id", id);
		request.setAttribute("pwd", pwd);
		
		return "main/output";
	}
	
	@RequestMapping("main/input.do")
	public String main_input(HttpServletRequest request){
		
		
		
		return "main/input";
	}
	
	
	// 2.5버전 이후에서는 request가 아니라 매개변수로 받음
	@RequestMapping("main/output2.do")
	public String main_output2(String id, String pwd, Model model){		// Model : 해당 jsp로 데이터 전송
		
		model.addAttribute("id", id);		// == request.setAttribute( , );
		model.addAttribute("pwd", pwd);
		
		return "main/output2";
	}
}
