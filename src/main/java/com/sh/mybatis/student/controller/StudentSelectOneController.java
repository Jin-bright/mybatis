package com.sh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.service.StudentService;
import com.sh.mybatis.student.model.service.StudentServiceImpl;

public class StudentSelectOneController extends AbstractController { //  상속했음 ! 
	 private StudentService studentService;  // 인터페이스 만들어서 

	 /**
	  * 의존객체 주입,,,,,, 뭘까 무슨말일까 컨트롤러가 작동할려면 service객체가 필요해서 얘가 의존객체인가 그리고 생성자에 넣어버려서 주입 ?
	  * @param studentService
	  */
	 public StudentSelectOneController(StudentService studentService ) {
		this.studentService = studentService;
	}
	 

	 @Override
	 public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 return "student/selectOne";
	 }
}
