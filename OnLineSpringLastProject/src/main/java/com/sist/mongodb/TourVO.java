package com.sist.mongodb;
/*
 	TNO	NUMBER						// 여행지게시물 고유 번호
	CATENO	NUMBER					// 여행 주제별 번호  1.명소, 2.자연&관광, 3.엔터테인먼트, 4.쇼핑
	TITLE	VARCHAR2(1000 BYTE)		// 여행지명
	ADDRESS	VARCHAR2(1000 BYTE)		// 여행지 주소
	TEL	VARCHAR2(100 BYTE)			// 연락가능번호
	BHOUR	VARCHAR2(1000 BYTE)		// 영업시간
	BDAY	VARCHAR2(1000 BYTE)		// 영업일
	NOTICE	VARCHAR2(1000 BYTE)		// 이것만은 꼭! 공지사항
	TRANS	VARCHAR2(1000 BYTE)		// 교통수단, 오시는길
	PHOTO	VARCHAR2(1000 BYTE)		// 사진
	CONTENT	CLOB					// 여행지 상세 내용 설명
	POST	CLOB					// 후기
	BUDGET	NUMBER					// 여행 경비
	AGE	NUMBER						// 방문 연령대
	SITE	VARCHAR2(200 BYTE)		// 여행사이트 평점
	SEASON	VARCHAR2(34 BYTE)		// 방문 시기 (월, 계절)
	PEOPLE	VARCHAR2(34 BYTE)		// 동반인 분류
	THEMA	VARCHAR2(34 BYTE)		// 여행지 분류 ( 지역별, 테마별.....)
	TAG	VARCHAR2(1000 BYTE)			// 관련 태그
 */
public class TourVO {

	private String title,address,tel,bhour,
	bday,notice,trans,photo,content,
	post,site,season,people,thema,tag;
	private int tno,cateno,budget,age;
	
	
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public int getCateno() {
		return cateno;
	}
	public void setCateno(int cateno) {
		this.cateno = cateno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBhour() {
		return bhour;
	}
	public void setBhour(String bhour) {
		this.bhour = bhour;
	}
	public String getBday() {
		return bday;
	}
	public void setBday(String bday) {
		this.bday = bday;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getThema() {
		return thema;
	}
	public void setThema(String thema) {
		this.thema = thema;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
