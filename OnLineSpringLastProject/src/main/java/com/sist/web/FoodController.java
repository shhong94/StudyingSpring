package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.FoodDAO;
import com.sist.dao.*;
import java.util.*;

@Controller
public class FoodController {

	@Autowired
	private FoodDAO fdao;
	
	@RequestMapping("food/list.do")
	public String food_list(Model model){
		
		List<FoodCategoryVO> list = fdao.foodCategoryAllData();
		model.addAttribute("list", list);
		
		return "food/list";
	}
}
