package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class FoodDAO {

	@Autowired
	private FoodMapper mapper;
	
	public List<FoodCategoryVO> foodCategoryAllData(){
		return mapper.foodCategoryAllData();
	}
}
