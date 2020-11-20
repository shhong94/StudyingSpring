package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import java.util.*;

public interface EmpMapper {
	
	// 스칼라
	@Select("SELECT "
				+ "empno, "
				+ "ename, "
				+ "job, "
				+ "hiredate, "
				+ "sal, "
				+ "deptno, "
				+ "(SELECT dname FROM dept d WHERE e.deptno = d.deptno) as dname, "
				+ "(SELECT loc FROM dept d WHERE e.deptno = d.deptno) as loc "
			+ "FROM emp e")
	public List<EmpVO> empListData();
	
	// 단일행
	@Select("SELECT empno, ename, job, hiredate, sal, deptno, "
			+ "(SELECT dname FROM dept d WHERE e.deptno = d.deptno) as dname, "
			+ "(SELECT loc FROM dept d WHERE e.deptno = d.deptno) as loc "
			+ "FROM emp e "
			+ "WHERE deptno = (SELECT deptno FROM emp WHERE ename = #{ename})")
	public List<EmpVO> empGroupData(String ename);
}
