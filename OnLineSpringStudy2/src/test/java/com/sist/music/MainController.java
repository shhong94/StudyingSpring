package com.sist.music;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller									// ������̼����� �޸� �Ҵ�
public class MainController {
	
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request){
		request.setAttribute("msg", "Hello");
		
		return "main/main";					// �ڿ� .jsp ���̸� �ȵ�
	}
}
