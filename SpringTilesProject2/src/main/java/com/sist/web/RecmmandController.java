package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecmmandController {
	@RequestMapping("recommand/list.do")
	public String recommand_list(){
		return "recommand/list";
	}
}
