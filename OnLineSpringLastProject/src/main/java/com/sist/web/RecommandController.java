package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;
import com.sist.recommand.NaverBlogFind;
import com.sist.recommand.RecommandManager;
import com.sist.recommand.RecommandVO;

import java.util.*;


/*
 * 	지니뮤직 태그
 * 	상황
	휴식 드라이브 산책 집 출/퇴근길 휴가/여행 운동 시상식 거리 하우스파티 클럽 고백 해변 공연 라운지
	감성
	외로움 기분전환 슬픔 힘찬 이별 지침/힘듦 설렘 오후 밤 새벽 저녁 아침 사랑 스트레스/짜증 그리움 추억 우울 행복 불안 분노 기쁨 축하
	스타일
	밝은 신나는 따뜻한 편안한 그루브한 부드러운 로맨틱한 매혹적인 영화음악 잔잔한 댄서블한 달콤한 몽환적인 시원한 애절한 어두운 연주음악 발렌타인데이 화이트데이
	날씨/계절
	가을 맑은날 추운날 봄 여름 겨울 흐린날 비오는날 더운날 안개낀날 눈오는날
 */
@Controller
@RequestMapping("recommand/")
public class RecommandController {
	
	@Autowired
	private RecommandManager mgr;
	
	@Autowired
	private NaverBlogFind nb;
	
	@Autowired
	private FoodDAO dao;
	
	
	
	
	
	@RequestMapping("list.do")
	public String recommand_list(){
		return "recommand/list";
	}

	// 추천 태그에 따른 리스트 출력 ======================================================================================================
	@RequestMapping("recomm_list.do")
	public String recommand_list_sub(int no, Model model){
		
		String s1 = "휴식,드라이브,산책,퇴근길,휴가,운동,거리,고백,해변";
		String s2 = "외로움,기분전환,슬픔,힘찬,이별,지침,설렘,오후,밤,새벽,저녁,아침,사랑,짜증,그리움,추억,우울,행복,불안,분노,기쁨,축하";
		String s3 = "밝은,신나는,따뜻한,편안한,그루브한,부드러운,로맨틱한,매혹적인,영화음악,잔잔한,달콤한,몽환적인,시원한,애절한,어두운";
		String s4 = "가을,맑은날,추운날,봄,여름,겨울,흐린날,비오는날,더운날,안개낀날,눈오는날";
		
		String data = "";		// 태그별 값을 담을 변수
		int index = 0;			// 태그 내 값의 개수
		
		if(no == 1){			// 파라미터 no가 1이면 data는 s1, 개수는 9개
			data = s1;
			index = 9;
		}
		else if(no == 2){
			data = s2;
			index = 22;
		}
		else if(no == 3){
			data = s3;
			index = 15;
		}
		else if(no == 4){
			data = s4;
			index = 11;
		}
		
		String[] ss = new String[index];							// index만큼 길이의 배열
		StringTokenizer st = new StringTokenizer(data, ",");		// 태그 값들을 담은 data를 콤마 기준으로 나누기
		int i = 0;
		while(st.hasMoreTokens()){									// 위 ss 배열에 나눈것들을 담기
			ss[i] = st.nextToken();
			i++;
		}
		
		model.addAttribute("ss", ss);								// ss배열 넘기기
		return "recomm_list";
	}
	
	
	// 추천 레시피를 출력 ========================================================================================================
	@RequestMapping("find.do")
	public String recommand_find(String fd, Model model){
		nb.naverFindData(fd);  										// XML 제작
		List<RecommandVO> list = mgr.recommandData();
		List<FoodVO> fList = new ArrayList<FoodVO>();
		for(RecommandVO vo : list){
			List<FoodVO> dList = dao.recommandFindData(vo.getTitle());
			fList.add(dList.get(0));
		}
		
		model.addAttribute("fList", fList);
		
		return "recomm_find";
	}
	
	
	
}
