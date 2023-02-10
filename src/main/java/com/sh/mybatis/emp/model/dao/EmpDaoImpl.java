package com.sh.mybatis.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class EmpDaoImpl implements EmpDao {

	
	//1.emp 전체 리스트를 조회할겨야
	@Override
	public List<Map<String, Object>> selectEmpList(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectList( "emp.selectempMapList");
	}

	//2. 검색 
	@Override
	public List<Map<String, Object>> search1(SqlSession session, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return session.selectList("emp.search1", param);
	}

	
	@Override
	public List<Map<String, Object>> search2(SqlSession session, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return  session.selectList("emp.search2", param);
	}

}
