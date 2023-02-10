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

public class empSearch2controller extends AbstractController {
	private EmpService empService;

	
	public empSearch2controller(EmpService empService) {
		super();
		this.empService = empService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1 사용자입력값 
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		String gender = request.getParameter("gender");
		Integer salary = null;
		
		try {
			salary = Integer.parseInt( request.getParameter("salary"));						
		} catch ( NumberFormatException e) {
			e.printStackTrace();
		}
		
		String salaryCompare = request.getParameter("salaryCompare");
		
		
		Map<String,Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		param.put("gender", gender);
		param.put("salary", salary);
		param.put("salaryCompare", salaryCompare);
		
		System.out.println("맵확인 param : " +  param  );
		
		//2 업무로직
		List<Map<String,Object>> empList = empService.search2(param);
		System.out.println( "**empList : " + empList );
		
		
		request.setAttribute("empList", empList);
		
		//3 jsp데이터 전달 
		
		return "emp/search2";
	}
	
	
	

}
