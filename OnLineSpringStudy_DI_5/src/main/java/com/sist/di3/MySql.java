package com.sist.di3;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MySql implements MyDAO {

	@Override
	public void getConnection() {
		System.out.println("mySql에 연결");

	}

	@Override
	public void disConnection() {
		System.out.println("mySql 연결해제");

	}

}
