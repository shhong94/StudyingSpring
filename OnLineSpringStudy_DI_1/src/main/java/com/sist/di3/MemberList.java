package com.sist.di3;


// 순서 : Member클래스 -> MemberList -> Main클래스


public class MemberList {
	
	
	private Member mem;			// Member클래스형의 변수

	
	
	public Member getMem() {		// 게터세터
		return mem;
	}

/*
 * <bean id="ml" class="com.sist.di3.MemberList"
		p:mem-ref="mem"			<============================================ setter메소드 찾아서 주소값 대입
	/>
 */	
	public void setMem(Member mem) {				
		this.mem = mem;
	}
	
	
	
	public void print(){			// Member클래스의 변수들을 출력하는 메소드
		
		System.out.println("회원번호 : " + mem.getNo());
		System.out.println("이름 : "  + mem.getName());
		System.out.println("성별 : "  + mem.getSex());
		System.out.println("주소 : "  + mem.getAddr());
		System.out.println("전화 : "  + mem.getTel());
	}
}
