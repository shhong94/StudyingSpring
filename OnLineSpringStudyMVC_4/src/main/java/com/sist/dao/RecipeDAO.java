package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class RecipeDAO {
	
	@Autowired
	private RecipeMapper mapper;						// 작성된 mapper 인터페이스
	
	public List<RecipeVO> recipeListData(Map map){		// 파라미터는 sql구현 메소드와 동일
		return mapper.recipeListData(map);				// mapper의 sql구현 메소드 실행
	}
	
	public int recipeTotalPage(){
		return mapper.recipeTotalPage();
	}
}
