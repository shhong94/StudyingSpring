package com.sist.music;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller									// 어노테이션으로 메모리 할당
public class MainController {
	
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request){
		request.setAttribute("msg", "Hello");
		
		return "main/main";					// 뒤에 .jsp 붙이면 안됨
	}
}
