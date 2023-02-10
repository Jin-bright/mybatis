package com.sh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.dto.Student;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentUpdateController extends AbstractController {
	private StudentService studentService;

	
	public StudentUpdateController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//1. 사용자입력값 
		int no = Integer.parseInt( request.getParameter("no"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
	
		Student student = new Student();
		student.setNo(no);
		student.setTel(tel);
		student.setName(name);
		
		//업무로직 
		int result = studentService.updateStudent(student);
		System.out.println( "업데이트성공여부  : " + result );
		HttpSession session = request.getSession();
		session.setAttribute("msg", "수정 성공");
		//gson 
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson( student, response.getWriter() );
		
		return null;
	//	return  "redirect:/student/selectOne.do";
	}
	

}
