package com.sh.mybatis.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController {
	/**
	 * 자식클래스에서 오버라이드 하지않고 호출 시 예외 발생 
	 *  이떄는 리턴절 따로 안적어도됨 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	//추상클래스 
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 	{
		throw new RuntimeException("나는추상메서드! 오버라이드하지않고 Get 요청은 허락되지 않습니다.");
		//자식객체에서 얘를 오버라이드 하지않고 쓸때 오류나게할려고(쓰지마!!) 
	}
	
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 	{
		throw new RuntimeException("오버라이드하지 않은 post 요청은 허락되지 않습니다.");
		//자식객체에서 얘를 오버라이드 하지않고 쓸때 오류나게할려고(쓰지마!!) 
	}

}
