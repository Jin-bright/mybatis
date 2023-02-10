package com.sh.mybatis.common;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mybatis.emp.model.dao.EmpDaoImpl;
import com.sh.mybatis.emp.model.service.EmpService;
import com.sh.mybatis.emp.model.service.EmpServiceImpl;
import com.sh.mybatis.student.model.dao.StudentDaoImpl;
import com.sh.mybatis.student.model.service.StudentService;
import com.sh.mybatis.student.model.service.StudentServiceImpl;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String, AbstractController> commandMap = new HashMap<>();
    //ㄴ 요청 url과 이를 처리할 controller객체를 매핑
    // ex) /student/selectList.do  --->   studentSelectListController <-- abstractcontroller의 자식이다
    // 그래서 매 요청 마다 컨트롤러를 만들거임 근데 이게 간단해질거다 .. ? 그리고 이걸 일관적으로 제어하기 위해만든게 map에 만든 AbstractController이거임 
    // ★모든 컨트롤러는 AbstractController의 자식클래스로, AbstractController타입으로 제어될 것 
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
    	//처음만들어질때 property 파일 읽어오게 
    	//1. command-map.properies -> prop객체로 옮겨오기 
    	Properties prop = new Properties();
    	String filepath = DispatcherServlet.class.getResource("/command-map.properties").getPath();
    	
    	try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    	//2. prop -> commandMap에 들어가게 타입변경해야되는데 
    	StudentService studentService =  new StudentServiceImpl(new StudentDaoImpl());
    	EmpService empService = new EmpServiceImpl( new EmpDaoImpl()); // 0210 추가 
    	
    	Set<String> propNames = prop.stringPropertyNames(); //key set 같은거 
    	// ★★propNames = properties에서 = 전에꺼까지 --- map의 key들이 될거임 ( /student/redirect.do)
    	

    	try { // reflection API 사용해서 할거임 
	    	for(String url : propNames) {
	    		String className = prop.getProperty(url); 
	        	// ★★className = map의 value들이될거임 ( com.sh.mybatis.student.controller.StudentSelectListController) 

	    		Class<?> clz = Class.forName(className); //1.클래스만듬 
	    		AbstractController controller = null;
	    		
	    		if(url.startsWith("/student")) {
 		    		 Constructor<?> constructor =  clz.getDeclaredConstructor(StudentService.class); //2. interface 만들고 null -> service 바꿧음 
	    			 controller = (AbstractController) constructor.newInstance(studentService); //객체를 만들어서 ab타입으로 변환 - new XXXcontroller이런느낌 //interface 만들고 매개변수?추가  	    		
	
	    		}
	    		else if(url.startsWith("/emp")) {
		    		Constructor<?> constructor =  clz.getDeclaredConstructor(EmpService.class); //2. interface 만들고 null -> service 바꿧음 
		    		controller = (AbstractController) constructor.newInstance(empService); //객체를 만들어서 ab타입으로 변환 - new XXXcontroller이런느낌 //interface 만들고 매개변수?추가  	    		
	    			
	    		}

	    		commandMap.put(url, controller );
	    	}
	    	
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	System.out.println( "**commandMap : " + commandMap);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 요청주소 가져오기 
		String url = request.getRequestURI(); // mybatis/student/selectList.do 
		url = url.replace(request.getContextPath(), ""); // /student/selectList.do 
		
		//2. commandmap에서 해당 controller 가져오기 
		AbstractController controller = commandMap.get(url); //AbstractController 이 객체가 나온다 
		// 맵의 value => com.sh.mybatis.student.controller.StudentSelectListController@51fc83b3
	
		if( controller == null ) { 
			//주소가 이상하면 null  -> 404 에러처리  ( 뒤에는 메시지 ) 
			response.sendError(HttpServletResponse.SC_NOT_FOUND, url );
			return;
		}
		
		//3. 전송방식에 따라 doget 또는 dopost 호출 
		String method = request.getMethod(); //메서드를 불러오는 ,, 문법같은거라고 생각해 GET POST이런거나옴 
		String viewName = null; //doget 메소드가 string으로 리턴되는데 
		
		
		switch(method) {
		case "GET" : 
			viewName = controller.doGet(request, response);
			break;
		case "POST" : 
			viewName = controller.doPost(request, response);
			break; 
		default : 
			throw new RuntimeException("허용되지 않은 전송방식 - " + method );
		}
		
		
		
		
		//4.  응답처리 - forward || redirect  ||    bypass (conroller에서 응답직접출력 / 비동기 ? )  
		if( viewName != null) {
			//ridirect - index로 가게   ====> rediect 시킬거는 이렇게 생긴 url이 들어오는거임 <  redirect:/ ~~  >  
			if(viewName.startsWith("redirect:")) {
				System.out.println(  viewName  );
				response.sendRedirect(request.getContextPath() + viewName.replace("redirect:", ""));
				
			}
			//forward ======> forward 응답은 이렇게 들어올거야 <  student/selectList  >   ( 원래는 /WEB-INF/views/student/selectList 엿음 )
			else {
				String prefix = "/WEB-INF/views/";
				String suffix = ".jsp";
				
				viewName = prefix+viewName+suffix;
				request.getRequestDispatcher(viewName).forward(request, response);
				
			}
		}
		
		
	}


	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// do get으로 가게 코드 지우지 않는다 
		doGet(request, response);
	}

}
