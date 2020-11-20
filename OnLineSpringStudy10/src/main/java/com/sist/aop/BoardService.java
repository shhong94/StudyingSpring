package com.sist.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service												// BI(Business Integration, 여러 기능 통합)
public class BoardService {
	
	@Autowired
	private MyDAO mdao;
	
	@Autowired
	private YouDAO ydao;
}
