package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;

@Controller
public class MovieController {

	@Autowired
	private MovieDAO dao;
	
	@RequestMapping("movie/list.do")
	public String movie_list(Model model, HttpServletRequest request){
		
		List<MovieVO> list = dao.movieListData();
		model.addAttribute("list", list);
		
		
		// 쿠키 리스트 ------------------------------------------------------
		List<MovieVO> cList = new ArrayList<MovieVO>();
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i = cookies.length-1; i >= 0 ; i--){
				if(cookies[i].getName().startsWith("m")){						// m으로 시작하는 쿠키는 영화관련 쿠키
					MovieVO vo = dao.movieDetailData(Integer.parseInt(cookies[i].getValue()));
					cList.add(vo);
				}
			}
		}
		model.addAttribute("cList", cList);
		
		return "movie/list";
	}
	
	
	// 쿠키 생성 =================================================================================================================================================================
	@RequestMapping("movie/detail_before.do")
	public String movie_detail_before(int no, RedirectAttributes ra, HttpServletResponse response){
		
		// 쿠키 -----------------------------------------------------------
		Cookie cookie = new Cookie("m" + no, String.valueOf(no));		// 쿠키는 문자열만 저장 가능
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);					// 클라이언트에 쿠키 저장
		
		//ra.addFlashAttribute("no", no);				// redirect:detail.do?no= + no 를 POST로 전송하는 방식   (application-context.xml에서 <mvc:annotation-driven/> 태그 추가해야 함)
		
		return "redirect:detail.do?no=" + no;
	}
	
	
	// 상세보기 =================================================================================================================================================================
	@RequestMapping("movie/detail.do")
	public String movie_detail(int no, Model model){
		
		MovieVO vo = dao.movieDetailData(no);
		model.addAttribute("vo", vo);
		
		return "movie/detail";
	}
	
}
