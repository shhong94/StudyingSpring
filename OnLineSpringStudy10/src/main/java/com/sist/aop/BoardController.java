package com.sist.aop;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController										// ¸Þ¸ð¸® ÇÒ´ç
public class BoardController {
	
	@RequestMapping("board/update.do")
	public String board_update(){
		JSONObject obj = new JSONObject();
		obj.put("name", "È«È«È«");
		obj.put("sex", "³²ÀÚ");
		
		return obj.toJSONString();					// RestController´Â JSON ¶Ç´Â XMLÀ» ³Ñ±è
	}

}
