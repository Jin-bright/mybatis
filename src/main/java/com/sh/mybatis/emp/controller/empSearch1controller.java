package com.sh.mybatis.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.emp.model.service.EmpService;

public class empSearch1controller extends AbstractController {
	private EmpService empService;

	
	public empSearch1controller(EmpService empService) {
		super();
		this.empService = empService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		Map<String, Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		
		
		//2. 업무로직 
		List<Map<String,Object>> empList = null;
		if( searchType!= null && searchKeyword != null ) { //검색요청인경우 
			empList = empService.search1( param );	
		}
		else { //일반요청 
			empList = empService.selectEmpList();	
		}
		request.setAttribute("empList", empList);
		System.out.println( empList );
		
		
		
		
		
		//3. jsp데이터설정 
		
		return "emp/search1";
	}
	
	
	

}
