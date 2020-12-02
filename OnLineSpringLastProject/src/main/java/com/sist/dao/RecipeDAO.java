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
	
	
	// 셰프 레시피 출력 ==============================================================================================================
	public List<RecipeVO> chefProductData(String chef, int page){
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		
		// query에 조건 설정
		BasicQuery query = new BasicQuery("{chef:'"+chef+"'}"); 
		int rowSize = 20;
		int skip = (page * rowSize) - rowSize;
		query.skip(skip).limit(rowSize);
		list = mt.find(query,  RecipeVO.class, "recipe7");
		
		return list;
	}
	
	
	
	// 셰프 레시피 검색 ==================================================================================================================
	public List<RecipeVO> chefProductFindData(String chef, String fd){
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		
		BasicQuery query = new BasicQuery("{$and:[{chef:'"+chef+"'}, {title:{$regex:'.*"+fd+"'}}]}");				// 셰프는 chef && 타이틀은 fd (WHERE chef = "aaa" AND title LIKE "%bbb%")
		
		list = mt.find(query, RecipeVO.class, "recipe7");
		
		return list;
	}
	
	
	
	// 셰프 리스트의 총 페이지 구하기 ==================================================================================================================
	public int chefTotalPage(){
		BasicQuery query = new BasicQuery("{}");							// SELECT WHERE절 : 전부 가져오기
		List<ChefVO> list = mt.find(query, ChefVO.class, "chef7");			// 조건에 맞는 셰프 리스트 가져오기
		int count = list.size();											// 리스트 개수
		int total = (int)(Math.ceil(count / 20.0));
		return total;
	}
}
