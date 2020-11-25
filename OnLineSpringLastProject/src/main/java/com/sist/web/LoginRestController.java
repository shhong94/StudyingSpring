package com.sist.web;

import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.ReplyDAO;

@RestController
public class LoginRestController {

	@Autowired
	private ReplyDAO dao;
	
	
	// 로그인 처리하는 메소드 ===============================================================================================
	@RequestMapping("main/login.do")
	public String main_login(String id, String pwd, HttpSession session){
		
		System.out.println("로그인 레스트 컨트롤러");
		System.out.println("id : " + id);
		System.out.println("pwd : " + pwd);
		
		String result = "";
		try {
			String s= dao.isLogin(id, pwd);
			if(s.equals("NOID")){
				result = "1";
			}
			else if(s.equals("NOPWD")){
				result = "2";
				System.out.println("비번틀림");
			}
			else{
				result = "3";
				StringTokenizer st = new StringTokenizer(s, "|");									// id와 name 잘라서 세션에 할당
				session.setAttribute("id", st.nextToken());
				session.setAttribute("name", st.nextToken());
				System.out.println("둘다맞음");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		return result;
	}
}
