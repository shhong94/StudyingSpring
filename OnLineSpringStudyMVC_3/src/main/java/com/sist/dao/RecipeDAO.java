package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class RecipeDAO extends SqlSessionDaoSupport{

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public List<RecipeVO> recipeListData(Map map){
		return getSqlSession().selectList("recipeListData"
				+ "", map);
	}
	
	public int recipeTotalPage(){
		return getSqlSession().selectOne("recipeTotalPage");
	}
	
	
	public List<RecipeVO> recipeChefData(Map map){
		return getSqlSession().selectList("recipeChefData", map);
	}
	
	public int recipeChefTotalPage(String chef){
		return getSqlSession().selectOne("recipeChefTotalPage", chef);
	}
}
