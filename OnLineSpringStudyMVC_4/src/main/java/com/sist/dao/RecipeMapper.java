package com.sist.dao;
/*
 * <!-- 리스트 -->
	<select id="recipeListData" resultType="RecipeVO" parameterType="hashmap">
		SELECT no, title, chef, poster, num
		FROM (SELECT no, title, chef, poster, rownum as num
		FROM (SELECT no, title, chef, poster
		FROM recipe))
		WHERE num BETWEEN #{start} AND #{end}
	</select>
	
	<select id="recipeTotalPage" resultType="int">
		SELECT CEIL(COUNT(*) / 20.0) FROM recipe
	</select>
	
	<!-- 셰프 -->
	<select id="recipeChefData" resultType="RecipeVO" parameterType="hashmap">
		SELECT no, title, chef, poster, num
		FROM (SELECT no, title, chef, poster, rownum as num
		FROM (SELECT no, title, chef, poster
		FROM recipe WHERE chef = #{chef}))
		WHERE num BETWEEN #{start} AND #{end}
	</select>
	
	<select id="recipeChefTotalPage" resultType="int" parameterType="String">
		SELECT CEIL(COUNT(*) / 20.0) FROM recipe WHERE chef = #{chef}
	</select>
 */

import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface RecipeMapper {
	
	@Select("SELECT no, title, chef, poster, num "
		+"FROM (SELECT no, title, chef, poster, rownum as num "
		+"FROM (SELECT no, title, chef, poster "
		+"FROM recipe)) "
		+"WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map);							// sql문을 구현하는 메소드
	
	
	@Select("SELECT CEIL(COUNT(*) / 20.0) FROM recipe")
	public int recipeTotalPage();
	
}
