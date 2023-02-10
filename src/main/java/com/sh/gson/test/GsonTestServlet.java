package com.sh.gson.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class GsonTestServlet
 */
@WebServlet("/gson/test")
public class GsonTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 응답데이터 생성
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "안녕하세요");
		map.put("num", 1234567890);
		
		//2. json으로 넘기기 - jar 다운대신 pom에등록하기 <-- 일단 인터넷에서 복붙할소스(dependency)찾아서 pom에 붙임 -> 그럼 다운 및 프로젝트연결은 maven이 해줌 
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter()); 
	}

}
