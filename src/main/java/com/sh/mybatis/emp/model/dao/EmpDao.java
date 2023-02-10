package com.sh.mybatis.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface EmpDao {

	List<Map<String, Object>> selectEmpList(SqlSession session);

	//2. 검색 -
	List<Map<String, Object>> search1(SqlSession session, Map<String, Object> param);

	//3. 검색여러개 
	List<Map<String, Object>> search2(SqlSession session, Map<String, Object> param);

}
