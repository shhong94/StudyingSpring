package com.sist.naver;


/*
 * jaxb 방식
 * <rss> <channel> <item> <title>,<description>
 */
// 네이버 개발가이드의 xml 응답 참고 https://developers.naver.com/docs/search/blog/

public class Item {
	
	
	
	private String title;
	private String description;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
