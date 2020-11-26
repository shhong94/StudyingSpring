package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;

public interface FoodMapper {

	// 음식 카테고리 출력 =======================================================================================
	@Select("SELECT no, title, poster, subject FROM food_category7")
	public List<FoodCategoryVO> foodCategoryAllData();
	
	// 카테고리별 음식 리스트 출력 ==================================================================================
	@Select("SELECT no, title, score, poster, addr, tel FROM food_detail7 WHERE cateno = #{cateno}")
	public List<FoodVO> foodCategoryListData(int cateno);
	
	// 카테고리별 음식 리스트 출력할 때 카테고리 타이틀도 출력 ============================================================
	@Select("SELECT title, subject FROM food_category7 WHERE no = #{no}")
	public FoodCategoryVO foodCategoryInfoData(int no);

	// 음식 상세보기 ==========================================================================================
	@Select("SELECT * FROM food_detail7 WHERE no = #{no}")
	public FoodVO FoodDetailData(int no);
	
	
	// 음식종류(food_detail7 type컬럼)를 finddata에 넣고 이와 관련된 레시피 정보 가져오기 =====================================
	@Select("SELECT title, poster, chef, rownum FROM recipe WHERE rownum < 5 AND REGEXP_LIKE(title, #{finddata})")
	public List<RecipeVO> foodLikeRecipeData(String finddata);
	
	
	// 몽고디비 게시판 상세페이지 우측에서 지도 클릭시 해당 지역의 음식점 출력 ===========================================================
	@Select("SELECT no, poster, title, rownum FROM food_detail7 WHERE rownum <= 12 AND addr LIKE '%'||#{gu}||'%'")
	public List<FoodVO> foodLocationFindData(String gu);
	
	
	// 좋아요 탑5 음식점 가져오기 ======================================================================================================================
	@Select("SELECT no, title, poster, rownum FROM (SELECT no, title, poster FROM food_detail7 ORDER BY good DESC) WHERE rownum <= 5")
	public List<FoodVO> foodTop5();
	
	
	// 좋아요 탑5 레시피 가져오기 ======================================================================================================================
	@Select("SELECT no, title, poster, rownum FROM recipe WHERE rownum <= 5")
	public List<RecipeVO> recipeTop5();
	
}
