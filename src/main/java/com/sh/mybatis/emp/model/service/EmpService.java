package com.sh.mybatis.emp.model.service;

import java.util.List;
import java.util.Map;

public interface EmpService {

	//1. 걍 리스트 
	List<Map<String, Object>> selectEmpList();

	//2. 검색 
	List<Map<String, Object>> search1(Map<String, Object> param);

	//3. 검색 여러개 
	List<Map<String, Object>> search2(Map<String, Object> param);

}
