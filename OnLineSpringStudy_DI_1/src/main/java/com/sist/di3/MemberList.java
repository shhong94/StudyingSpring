package com.sist.di3;


// ���� : MemberŬ���� -> MemberList -> MainŬ����


public class MemberList {
	
	
	private Member mem;			// MemberŬ�������� ����

	
	
	public Member getMem() {		// ���ͼ���
		return mem;
	}

/*
 * <bean id="ml" class="com.sist.di3.MemberList"
		p:mem-ref="mem"			<============================================ setter�޼ҵ� ã�Ƽ� �ּҰ� ����
	/>
 */	
	public void setMem(Member mem) {				
		this.mem = mem;
	}
	
	
	
	public void print(){			// MemberŬ������ �������� ����ϴ� �޼ҵ�
		
		System.out.println("ȸ����ȣ : " + mem.getNo());
		System.out.println("�̸� : "  + mem.getName());
		System.out.println("���� : "  + mem.getSex());
		System.out.println("�ּ� : "  + mem.getAddr());
		System.out.println("��ȭ : "  + mem.getTel());
	}
}
