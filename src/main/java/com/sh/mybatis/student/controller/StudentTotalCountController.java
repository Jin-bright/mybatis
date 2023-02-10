package com.sh.mybatis.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentTotalCountController extends AbstractController {
	
	 private StudentService studentService;  // 인터페이스 만들어서 

	 public StudentTotalCountController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	 
	 @Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int totalCount = studentService.getTotalCount();
		System.out.println("totalcount : " +   totalCount   );

// 쌤방식  Map<String, Object> map = new HashMap<>();
//		 map.put("totalCount", map);
		
		  response.setContentType("application/json; charset=utf-8");
		 new Gson().toJson(totalCount, response.getWriter()); 
// 쌤방식   new Gson().toJson(map, response.getWriter()); 
		 
		return null;
//		return null -> // 비동기 혹은 {} 아님 [] 여야됨 
	}


}
