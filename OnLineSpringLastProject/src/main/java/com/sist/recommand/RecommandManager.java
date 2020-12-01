package com.sist.recommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import com.sist.dao.*;


@Component
public class RecommandManager {
	
	
	@Autowired
	private FoodDAO dao;
	

	public List<RecommandVO> recommandData(){
		List<RecommandVO> list = new ArrayList<RecommandVO>();
		try {
			JAXBContext jb = JAXBContext.newInstance(Rss.class);
			
			Unmarshaller un = jb.createUnmarshaller();
			Rss rss = (Rss)un.unmarshal(new File("c:\\upload\\recommand.xml"));			// NaverBlogFind 메소드를 통해 만들어진 xml파일을 자바로 변환
			
			List<Item> iList = rss.getChannel().getItem();								// description을 한 줄씩 읽기
			List<String> fList = dao.recipeTitleData();									// 레시피의 타이틀만 리스트로 받기
			
			
			// 매처 클래스는 패턴 인스턴스를 갖고 매치를 수행 (패턴은 매처의 수단)
/*
 * 패턴 : 데이터 사전 (영화제목, 맛집, 노래제목)
 * 		맛있고, 맛있게... : 긍정(맛있.*) / 부정
 */
			Pattern[] p = new Pattern[fList.size()];									// 패턴 배열 초기화
			Matcher[] m = new Matcher[fList.size()];									// 매처 배열 초기화
			
			for(int i = 0; i < p.length; i++){
				p[i] = Pattern.compile(fList.get(i));									// 패턴 배열의 인스턴스 생성
			}
			
			int[] count = new int[fList.size()];										// 출력된 레시피 길이만큼의 count 배열 초기화 (레시피가 몇번 언급됐는지 확인하는 용도)
			
			for(Item item : iList){														// 검색 결과의 길이만큼 반복문 실행
				
				for(int i = 0; i < m.length; i++){											// 매처 배열의 길이만큼 반복문 실행
					m[i] = p[i].matcher(item.getDescription());									// 패턴 배열(레시피 리스트)과 검색결과의 description이 같은 것을 매처 배열에 담기 (일반 자바의 contains와 같음)
					while(m[i].find()){																// 패턴에 일치하는 문자가 발견되면 true
						String title = m[i].group();												// 패턴에 일치하는 문자열을 title 변수에 넣기
						count[i]++;																	// 언급된 개수 증가
					}
				}
			}
			
			// 출력
			int j = 0;
			for(String title : fList){
				if(count[j] != 0){														// 언급된 갯수가 0이 아니면 출력
					RecommandVO vo = new RecommandVO();
					vo.setTitle(title);
					vo.setCount(count[j]);
					list.add(vo);
					System.out.println(title + " : " + count[j]);
				}
				
				j++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
