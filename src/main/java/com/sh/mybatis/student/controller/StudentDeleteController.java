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

public class StudentDeleteController extends AbstractController {

	private StudentService studentService;

	public StudentDeleteController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		//1
		int no = Integer.parseInt( request.getParameter("no"));
	//	String name = request.getParameter("name");      쌤은 no만 보냇음
	//	String tel = request.getParameter("tel"); 쌤은 no만 보냇음
		
		Student student = new Student();
		student.setNo(no);
	//	student.setName(name);  쌤은 no만 보냇음
	//	student.setTel(tel); 쌤은 no만 보냇음
		
		System.out.println( student );
		//2
		int result = studentService.deleteStudent(student);
		
		//3
		HttpSession session = request.getSession();
		session.setAttribute("msg", "삭제 성공 - 이게보이나  ");

		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(student, response.getWriter());
		
		return null;

	}
}
