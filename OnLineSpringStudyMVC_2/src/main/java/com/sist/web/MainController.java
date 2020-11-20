package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("main/")
public class MainController {
	
	@GetMapping("emp.do")
	public String main_emp(){
		return "emp";
	}
	
	@PostMapping("emp_ok.do")
	public String main_emp_ok(EmpVO vo, Model model){
		
		model.addAttribute("vo", vo);
		
		return "emp_ok";
	}
}
