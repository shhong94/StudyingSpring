package com.sist.di2;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;
public class EmpDAO extends SqlSessionDaoSupport{
	
   public List<EmpVO> empListData(){
	   
      return getSqlSession().selectList("empListData");					// 스프링에서 마이바티스 쓰는 코드
   }
}


