package com.sist.dao;


import org.apache.ibatis.annotations.Select;
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
	
	
	// 몽고디비 게시판 상세페이지 우측에서 지도 클릭시 해당 지역의 음식점 출력 ===========================================================
	public List<FoodVO> foodLocationFindData(String gu){
		return mapper.foodLocationFindData(gu);
	}
	
	
	
	// 좋아요 탑5 음식점 가져오기 ======================================================================================================================
	public List<FoodVO> foodTop5(){
		return mapper.foodTop5();
	}
	
	
	// 좋아요 탑5 레시피 가져오기 ======================================================================================================================
	public List<RecipeVO> recipeTop5(){
		return mapper.recipeTop5();
	}
	
	
	// 추천 레시피 출력 ===============================================================================================================================
	public List<String> recipeTitleData(){
		return mapper.recipeTitleData();
	}
	
	
	
	// 추천 레시피의 상세보기 ==========================================================================================================================
	public List<FoodVO> recommandFindData(String title){
		return mapper.recommandFindData(title);
	}
	
}
