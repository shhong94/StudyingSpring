package com.sist.aop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller															// 메모리 할당
public class MainController {
	
	@RequestMapping("main/main.do")
	public String main_main(MemberVO vo, Model model){				// 파라미터
		
		model.addAttribute("vo", vo);								// vo안에 값이 자동으로 담겨짐
		
		
		return "../main/main";									// Controller는 jsp파일명을 넘김
	}
}
