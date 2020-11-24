package com.sist.data;
/*
 * 
 * 믿고보는 맛집 리스트
 * <div class="top_list_slide">
        <ul class="list-toplist-slider" style="width: 531px;">
            <li>
              <img class="center-croping" alt="부산 광안리 맛집 베스트 25곳 사진"
                   data-lazy="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/la_vlgj7bh5oqani.png?fit=around|600:400&amp;crop=600:400;*,*&amp;output-format=jpg&amp;output-quality=80"/>

              <a href="/top_lists/2720_gwanganri"
                 onclick="trackEvent('CLICK_TOPLIST', {&quot;section_position&quot;:0,&quot;section_title&quot;:&quot;믿고 보는 맛집 리스트&quot;,&quot;position&quot;:0,&quot;link_key&quot;:&quot;FSCPVRH&quot;});">
                <figure class="ls-item">
                  <figcaption class="info">
                    <div class="info_inner_wrap">
                      <span class="title">부산 광안리 맛집 베스트 25곳</span>
                      <p class="desc">"광안리에 이거 먹을 갈 사람?"</p>
                      <p class="hash">
                          <span>#부산시 수영구 </span>
                          <span>#부산 수영구 </span>
                          <span>#수영구 </span>
                          <span>#부산 남구 </span>
                          <span>#부산시 남구 </span>
                          <span>#수영구청 </span>
                          <span>#광안대교 </span>
                          <span>#광안리바닷가 </span>
                          <span>#광안리해변 </span>
                          <span>#광안리 </span>
                          <span>#광안리역 </span>
                          <span>#금련산역 </span>
                          <span>#수영역 </span>
                      </p>
                    </div>
                  </figcaption>
                </figure>
              </a>
            </li>
            
            
            
     
     지역별 인기 맛집
     <div class="top_list_slide">
        <ul class="list-toplist-slider" style="width: 531px;">
            <li>
              <img class="center-croping" alt="2020 제주 인기 맛집 TOP 60 사진"
                   data-lazy="https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/7zsdxmpu4kauzpk7.jpg?fit=around|600:400&amp;crop=600:400;*,*&amp;output-format=jpg&amp;output-quality=80"/>

              <a href="/top_lists/2264_jeju2020"
                 onclick="trackEvent('CLICK_TOPLIST', {&quot;section_position&quot;:6,&quot;section_title&quot;:&quot;지역별 인기 맛집&quot;,&quot;position&quot;:0,&quot;link_key&quot;:&quot;PQQTL_E&quot;});">
                <figure class="ls-item">
                  <figcaption class="info">
                    <div class="info_inner_wrap">
                      <span class="title">2020 제주 인기 맛집 TOP 60</span>
                      <p class="desc">"제주의 인기 맛집만 쏙쏙 골라 모았다!"</p>
                      <p class="hash">
                          <span>#제주도 </span>
                          <span>#제주 </span>
                          <span>#서귀포시 </span>
                          <span>#제주시 </span>
                          <span>#제주여행 </span>
                          <span>#제주맛집 </span>
                          <span>#고기국수 </span>
                          <span>#돔베고기 </span>
                          <span>#흑돼지 </span>
                          <span>#고등어회 </span>
                          <span>#갈치회 </span>
                          <span>#몸국 </span>
                          <span>#오겹살 </span>
                          <span>#2020망고플레이트인기맛집 </span>
                      </p>
                    </div>
                  </figcaption>
                </figure>
              </a>
            </li>
 */

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FoodManager {

	// 음식 카테고리 데이터 가져오는 메소드 ============================================================================================
	public void foodCategoryAllData(){
		
		FoodDAO dao = new FoodDAO();
		
		try {
			Document doc = Jsoup.connect("https://www.mangoplate.com/").get();
			Elements title = doc.select("div.info_inner_wrap span.title");
			Elements poster = doc.select("ul.list-toplist-slider img.center-croping");
			Elements subject = doc.select("div.info_inner_wrap p.desc");
			Elements link = doc.select("ul.list-toplist-slider a");
			for(int i = 0; i < title.size(); i++){
				System.out.println((i+1) + "번");
				System.out.println("제목 : " + title.get(i).text());
				System.out.println("부제목 : " + subject.get(i).text());
				System.out.println("링크 : " + link.get(i).attr("href"));
				System.out.println("이미지 : " + poster.get(i).attr("data-lazy"));
				
				FoodCategoryVO vo = new FoodCategoryVO();
				vo.setNo(i + 1);
				vo.setTitle(title.get(i).text());
				
				String p = poster.get(i).attr("data-lazy");			// 포스터 내의 &를 ^로 치환
				p = p.replace("&", "^");
				vo.setPoster(p);
				
				String s = subject.get(i).text();					// 부제목 내의 역슬래시를 공백으로 치환
				s = s.replace("\"", "");
				vo.setSubject(s);
				
				vo.setLink(link.get(i).attr("href"));
				dao.foodCategoryInsert(vo);
				
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	// https://www.mangoplate.com
	// 맛집 상세보기 가져오기 (디비로부터) ==================================================================================================================================
	public FoodVO foodDetailData(){
		FoodVO vo = new FoodVO();
		FoodDAO dao = new FoodDAO();
		
		try {
			List<FoodCategoryVO> list = dao.foodCategoryListData();		// 망고플레이트의 카테고리 목록이 실시간 바뀌기 때문에, 카테고리를 디비에서 가져옴 
			
			// 1차 반복 -------------------------------------------------------------------------------------------------------------------
			for(FoodCategoryVO fvo : list){
				
				String url = "https://www.mangoplate.com" + fvo.getLink();
				int cateno = fvo.getNo();
				Document doc = Jsoup.connect(url).get();
				Elements link = doc.select("figure.restaurant-item span.title a");							
				System.out.println("카테고리번호 : " + cateno);
				
				// 2차 반복 -----------------------------------------------------------------------------
				for(int i = 0; i < link.size(); i++){
					
					System.out.println("사이트 : " + link.get(i).attr("href"));
					System.out.println("===========================================");
					
					String url2 = "https://www.mangoplate.com" + link.get(i).attr("href");		// 상세페이지 링크
					Document doc2 = Jsoup.connect(url2).get();
					
			FoodVO vo2 = new FoodVO();
			vo2.setCateno(cateno);
					
					/*String strPoster = "";														// 포스터 없을 때 예외처리
					try {
						Elements poster = doc2.select("img.center-croping");
						
						for(int j = 0; j < poster.size(); j++){
							String s = poster.get(j).attr("src");
							strPoster = s + ",";
						}
						strPoster = strPoster.substring(0, strPoster.lastIndexOf(","));
					} catch (Exception e) {
						strPoster = "no";
						System.out.println(e.getMessage());
					}*/
					
					String strPoster="";
                 try
                 {
                    Elements poster=doc2.select("img.center-croping");
                    
                    for(int j=0;j<poster.size();j++)
                    {
                       String s=poster.get(j).attr("src");
                       strPoster+=s+"^";
                    }
                    strPoster=strPoster.substring(0,strPoster.lastIndexOf("^"));
                 }catch(Exception ex){
                    strPoster="no";
                 }
                 vo2.setPoster(strPoster);
					
					
			vo2.setPoster(strPoster);
					
					
					Element title = doc2.selectFirst("span.title h1.restaurant_name");
					Element score = doc2.selectFirst("span.title strong.rate-point span");
					Element addr = doc2.select("tr.only-desktop td").get(0);					// 0번째 only-desktop 안의 td
//					Element tel = doc2.select("tr.only-desktop td").get(1);						// 1번째 only-desktop 안의 td
					Element type = doc2.select("tr td span").get(2);
					
			vo2.setTitle(title.text());
			vo2.setScore(score.text());
			vo2.setAddr(addr.text());
//			vo2.setTel(tel.text());
			vo2.setType(type.text());
			
					String strTel = "";														// 가격 없을 때 예외처리
					try {
						Element tel = doc2.select("tr.only-desktop td").get(1);
						strTel = tel.text();
					} catch (Exception e) {
						strTel = "no";
					}
			vo2.setTel(strTel);
					
					String strPrice = "";														// 가격 없을 때 예외처리
					try {
						Element price = doc2.select("tr td").get(3);
						strPrice = price.text();
					} catch (Exception e) {
						strPrice = "no";
					}
			vo2.setPrice(strPrice);
					
					String strMenu = "";														// 메뉴 종류 없을 때 예외처리
					try {
						Elements menu = doc2.select("td.menu_td li.Restaurant_MenuItem");
						
						for(int j = 0; j < menu.size(); j++){
							
							strMenu += menu.get(j).text() + "^";
						}
						strMenu = strMenu.substring(0, strMenu.lastIndexOf("^"));
					} catch (Exception e) {
						strMenu = "no";
					}
			vo2.setMenu(strMenu);
					
					// <script id="reviewCountInfo" type="application/json">[{"action_value":1,"count":6},{"action_value":2,"count":14},{"action_value":3,"count":50}]</script>
					
					String temp = doc2.selectFirst("script#reviewCountInfo").data();			// 맛있다, 별로 등은 JSON형식이기 때문에 파싱해야 함 ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
					
					try {
						JSONParser jp = new JSONParser();
						JSONArray arr = (JSONArray)jp.parse(temp);
						for(int a = 0; a < arr.size(); a++){
							JSONObject obj = (JSONObject)arr.get(a);
							if(a == 2){															// JSON의 "action_value"가 2면 좋아요, 2이면 괜찮다, 그게아니면 별로
								System.out.println("좋아요 : " + obj.get("count"));				// JSON의 "count"의 값 가져오기
//			vo2.setGood((int) obj.get("count"));
								vo2.setGood(Integer.parseInt(String.valueOf(obj.get("count"))));
							}
							else if(a == 1){
								System.out.println("괜찮다 : " + obj.get("count"));
//			vo2.setSoso((int)obj.get("count"));
								vo2.setSoso(Integer.parseInt(String.valueOf(obj.get("count"))));
							}
							else{
								System.out.println("별로 : " + obj.get("count"));
//			vo2.setBad((int)obj.get("count"));
								vo2.setBad(Integer.parseInt(String.valueOf(obj.get("count"))));
							}
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					System.out.println("제목 : " + title.text());
					System.out.println("주소 : " + addr.text());
					System.out.println("평점 : " + score.text());
					System.out.println("전화 : " + strTel);
					System.out.println("종류 : " + type.text());
					System.out.println("가격 : " + strPrice);
					System.out.println("메뉴 : " + strMenu);
					System.out.println("리뷰 : " + temp);
					System.out.println("포스터 : " + strPoster);
					
					dao.foodDetailInsert(vo2);
					
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
}
