package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired
	private MemberMapper mapper;
	
	// 아이디와 비밀번호 일치여부 확인
	public String isLogin(String id, String pwd){
		String result = "";
		
		int count = mapper.idCheck(id);
		if(count == 0){
			result = "NOID";
		}
		else{
			String db_pwd = mapper.memberGetPassword(id);
			if(db_pwd.equals(pwd)){
				result = "OK";
			}
			else{
				result = "NOID";
			}
		}
		
		return result;
	}
}
