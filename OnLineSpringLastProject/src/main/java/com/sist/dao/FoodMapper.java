package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;

public interface FoodMapper {

	// 음식 카테고리 출력 =======================================================================================
	@Select("SELECT no, title, poster, subject FROM food_category7")
	public List<FoodCategoryVO> foodCategoryAllData();

}
