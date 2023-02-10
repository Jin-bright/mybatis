package com.sh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.dto.Student;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentOneController extends AbstractController {
	private StudentService studentService;

	public StudentOneController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한명가져올건데 
		
		//사용자입력값 조회 
		int no = Integer.parseInt( request.getParameter("no"));
		System.out.println("no : " + no );
		
		//업무로직 
		Student student = studentService.selectOneStudent(no);
		System.out.println( student );
		
		//json
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson( student, response.getWriter() );
		
		return null; //비동기 
	}

}
