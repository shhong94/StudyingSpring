package com.sist.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost")
public class MusicRestController {
	@RequestMapping("movie2/movie_data.do")
	public String movie2_main(String no){
		
		String result = "{datas:";																			// JSON 배열을 하나로 묶는 object가 필요함
		
		String url = "http://www.kobis.or.kr/kobis/business/main/";											// 공통 주소
		if(no == null){
			no = "1";
		}
		int type = Integer.parseInt(no);
		
		switch(type){
		case 1:
			url += "searchMainDailyBoxOffice.do";															// 일일 박스오피스
			break;
		case 2:
			url += "searchMainRealTicket.do";																// 실시간 예매율
			break;
		case 3:
			url += "searchMainDailySeatTicket.do";															// 좌석점유율
			break;
		case 4:
			url += "searchMainOnlineDailyBoxOffice.do";														// 온라인 박스오피스 일일
			break;
		}	
		
		try {
				Document doc=Jsoup.connect(url).get();
			   //System.out.println(doc.toString());
			   String temp=doc.toString();													// html의 body태그 등도 같이 가져옴
			   temp=temp.substring(temp.indexOf("["),temp.lastIndexOf("]")+1);				// html태그는 자르고 JSON만 가져옴
			   
			   result+=temp;
			   System.out.println(result);
			   result=result.replace("<", "");
			   result=result.replace(">", "");
			   result=result.replace("\r", "");
			   result=result.replace("\n", "");
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
