package com.sist.di2;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MusicDAO extends SqlSessionDaoSupport{
	
	
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {		// p:sqlSessionFactory-ref="ssf"
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public List<MusicVO> musicListData(){
		return getSqlSession().selectList("musicListData");
	}
}
