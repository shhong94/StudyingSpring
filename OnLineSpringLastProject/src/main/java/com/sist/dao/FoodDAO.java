package com.sist.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class FoodDAO {

	@Autowired
	private FoodMapper mapper;
	
	// 카테고리 가져오는 메소드 =====================================================================================
	public List<FoodCategoryVO> foodCategoryAllData(){
		return mapper.foodCategoryAllData();
	}
	
	// 카테고리별 음식 리스트 출력 ==================================================================================
	public List<FoodVO> foodCategoryListData(int cateno){
		return mapper.foodCategoryListData(cateno);
	}
	
	// 카테고리별 음식 리스트 출력할 때 카테고리 타이틀도 출력 ============================================================
	public FoodCategoryVO foodCategoryInfoData(int no){
		return mapper.foodCategoryInfoData(no);
	}
	
	
	// 음식 상세보기 =============================================================================================
	public FoodVO FoodDetailData(int no){
		return mapper.FoodDetailData(no);
	}
	
	
	// 
	public List<RecipeVO> foodLikeRecipeData(String finddata){
		return mapper.foodLikeRecipeData(finddata);
	}
	
}
