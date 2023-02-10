package com.sh.jdk.api.relection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws Exception {
		//sample클래스 실행할거야 
	Main main = new Main();
	//main.test1();
	//main.test2(); //메서드 호출 
	main.test3(); // vlfemwpdj 
	

	}
	
	private void test3() throws Exception {
		
		Class<?> clz = Class.forName("com.sh.jdk.api.relection.Sample");
		//1. sample 객체만들기 - 클래스객체에서 생성자가져와서 바로 new instance 
		Sample sample = (Sample)clz.getDeclaredConstructor(int.class, String.class).newInstance(123, "여보세요");
		
		Field num = clz.getDeclaredField("num");
		num.setAccessible(true);
		
		Object value = num.get(sample);
		
		num.set(sample, 789);
		value = num.get(sample);
		
		System.out.println( value );
	}
	/**
	 * 메서드호출 
	 * @throws Exception 
	 */
	private void test2() throws Exception {
		//setNum 만들기 (호출 ) 
		//0. 클래스 객체만들기 
		Class<?> clz = Class.forName("com.sh.jdk.api.relection.Sample");
		//1. sample 객체만들기 - 클래스객체에서 생성자가져와서 바로 new instance 
		Sample sample = (Sample)clz.getDeclaredConstructor(null).newInstance(null);
		//2. 메서드만들기 - 클래스에서 메서드 가져와서 int매개변수로 넣고 
		Method setNum = (Method) clz.getDeclaredMethod("setNum", int.class); //메소드이름(문자열), 매개변수타입
		//  setnum메서드에 100을 대입하는데,, sample 은 뭐지  ? 저객체가 있는 메서드에 100을 넣고 호출해라 ? 
		Object returnValue =  setNum.invoke(sample, 100); //sample객체에 etnum을 메서드를 호출해라 
		System.out.println( sample ); 
		System.out.println( "**setNum메서드 : " + setNum ); 
		System.out.println( returnValue );
		// 출력값 : public void com.sh.jdk.api.relection.Sample.setNum(int)
		
		////////////////////////////////////////////////////////////
		// getNum호출
		Method getNum = (Method) clz.getDeclaredMethod("getNum");
		returnValue =  getNum.invoke(sample);
		System.out.println( returnValue );
		
		
		
	}
	/**
	 * Reflection API
	 * - 클래스객체를 통해서 클래스 정보 또는 객체 정보 열람 또는 객체 생성, 필드값 주입, 메서드 호출 등을 제어하는 api 
	 * - 문자열만 주면 내가 클래스 객체 만들어준다 
	 */

	private void test1() throws Exception {
		/// 0. 클래스 객체는 하나만 만들어서 재사용한다 ? 
		Class<?> clz = Class.forName("com.sh.jdk.api.relection.Sample"); // 클래스객체를만든다 (괄호안에가 변수명같은거.. ) - 내가 어떤 타입의 클래스만들지몰라서 이걸ㄹ ㅗ쓴다 
		Class<?> clz2 = Sample.class; //클래스객체만드는방법2 (클래스 이름 알떄만 )
		Class<?> clz3 = new Sample().getClass();  
		/// 0. 클래스 객체는 하나만 만들어서 재사용한다 ? 
		System.out.println("clz : " + clz );
//		System.out.println("clz2 : " + clz2 );
//		System.out.println("clz3 : " + clz2 );
		System.out.println( clz == clz2 );
		
		//1. 기본생성자 이용해서 객체생성 ( 괄호안에 null이어야됨)  
		Constructor<Sample> constructor =  (Constructor)clz.getDeclaredConstructor(null);  // 1.생성자만듬 
		Sample sample =  constructor.newInstance(null); // 2. 객체를 하나만들었다 생성자를 이용해서 
		System.out.println("**sample : " +  sample );	
		
		//2. 파라미터 생성자 이용해서 객체생성 
		Constructor<Sample> constructor2 =  (Constructor)clz.getDeclaredConstructor(int.class, String.class); 
		Sample sample2 =  constructor2.newInstance(10,"홍길동"); // 2. 객체를 하나만들었다 생성자를 이용해서 
		System.out.println("**sample2 : " +  sample2 );	
	}

}
