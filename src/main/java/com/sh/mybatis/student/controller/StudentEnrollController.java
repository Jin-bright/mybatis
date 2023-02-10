package com.sh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.dto.Student;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentEnrollController extends AbstractController {
	private StudentService studentService;

	//service 넣은 생성자를 만들기 
	public StudentEnrollController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "student/studentEnroll"; //forwardign 하고싶은 주소를 여기다 쓰는데 접두사 접미어 빼도도된 ~~videw
	}
	
	
	//enroll할거니까 
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		//dto한테 담을고야 
		
		Student student = new Student();
		student.setName(name);
		student.setTel(tel);
		System.out.println(student );
		
		
		// 2. 업무로직 
		int result = studentService.insertStudent(student);
		
		// 3. 사용자 피드백전달 
		HttpSession session = request.getSession();
		session.setAttribute("msg", "등록성공");
		// 4. 리다렉
		return "redirect:/student/studentEnroll.do";
	}
	
}
