package com.sist.di3;

import org.springframework.stereotype.Repository;

@Repository
public class Oracle implements MyDAO {

	@Override
	public void getConnection() {
		System.out.println("오라클연결");

	}

	@Override
	public void disConnection() {
		System.out.println("오라클해제");

	}

}
