package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("main/main.do")
	public String main_main(){
		return "main";				// 리턴값을 UrlBasedViewResolver로 넘겨서 tile.xml의 main 타일즈를 출력
	}
	
	@RequestMapping("board/list.do")
	public String board_list(){
		return "board/list";
	}
	
	@RequestMapping("notice/list.do")
	public String notice_list(){
		return "notice/list";
	}
}
