package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.sist.dao.*;

@Repository
public class RecipeDAO {
	
	@Autowired
	private MongoTemplate mt;
	
	
	// 레시피 리스트 출력 ======================================================================================================
	public List<RecipeVO> recipeListData(int page){
		
		Query query = new Query();								// query에 조건 설정
		int rowSize = 12;
		int skip = (page * rowSize) - rowSize;
		query.skip(skip).limit(rowSize);
		query.with(new Sort(Sort.Direction.ASC, "no"));
		
		return mt.find(query, RecipeVO.class, "recipe7");
		
		
	}
	
	
	// 레시피 총 갯수 구하기 ==========================================================================================================
	 public int recipeCount(){
		 
		 Query query = new Query();
		 int count = (int)mt.count(query, "recipe7");
		 return count;
	 }	 
	 
	 
	 // 레시피 상세보기
	 public RecipeDetailVO recipeDetailData(int no){
		 RecipeDetailVO vo = new RecipeDetailVO();
		 BasicQuery query = new BasicQuery("{no : "+ no +" }");
		 vo = mt.findOne(query, RecipeDetailVO.class, "recipe_detail7");
		 return vo;
	 }
	 
	 
	 
	// 셰프 리스트 출력 ======================================================================================================
	public List<ChefVO> chefListData(int page){
		
		Query query = new Query();								// query에 조건 설정
		int rowSize = 20;
		int skip = (page * rowSize) - rowSize;
		query.skip(skip).limit(rowSize);
		query.with(new Sort(Sort.Direction.ASC, "no"));
		
		return mt.find(query, ChefVO.class, "chef7");
		
		
	}
}
