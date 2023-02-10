package com.sh.mybatis.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.common.AbstractController;
import com.sh.mybatis.student.model.dto.Student;
import com.sh.mybatis.student.model.service.StudentService;

public class StudentSelectListController extends AbstractController {// abstract 상속했음 ! 
	 private StudentService studentService;  // 인터페이스 만들어서 
	 
	
	 /**
	  * 의존객체 주입,,,,,, 뭘까 무슨말일까 컨트롤러가 작동할려면 service객체가 필요해서 얘가 의존객체인가 그리고 생성자에 넣어버려서 주입 ?
	  * @param studentService
	  */
	 public StudentSelectListController(StudentService studentService ) {
		this.studentService = studentService;
	}

	 
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1 동기 - 업무로직 - dtod로 조회 
		
		List<Student> studentList = studentService.selectStudentList();
		System.out.println( "studentList = " + studentList);
		
		//2 맵으로 조회 
		List<Map<String, Object>> studentMapList = studentService.selectStudentMapList();
		System.out.println( studentMapList );
		//2. jsp에 데이터 전달 
		request.setAttribute("studentList", studentList);
		request.setAttribute("studentMapList", studentMapList);
		
		//3 forwarding하는코드 
		return "student/selectList";
	}

}
