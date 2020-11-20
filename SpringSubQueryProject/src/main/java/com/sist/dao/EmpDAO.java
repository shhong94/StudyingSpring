package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class EmpDAO{
	
	@Autowired
	private EmpMapper mapper;
	
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
	
	public List<EmpVO> empGroupData(String ename){
		return mapper.empGroupData(ename);
	}
}
