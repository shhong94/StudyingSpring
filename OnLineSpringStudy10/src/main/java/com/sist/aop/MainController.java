package com.sist.aop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller															// �޸� �Ҵ�
public class MainController {
	
	@RequestMapping("main/main.do")
	public String main_main(MemberVO vo, Model model){				// �Ķ����
		
		model.addAttribute("vo", vo);								// vo�ȿ� ���� �ڵ����� �����
		
		
		return "../main/main";									// Controller�� jsp���ϸ��� �ѱ�
	}
}
