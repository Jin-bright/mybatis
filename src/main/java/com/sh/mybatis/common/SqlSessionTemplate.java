package com.sh.mybatis.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
	

	/** Sqlsession 
	 * 1. 공장지을거야 factory builder 한테 공장지어달라고하 
	 * 2. factory (설정파일필요)
	 * 3. sqlSession 
	 * @return
	 */
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "/mybatis-config.xml";
		
		//1
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

		try {
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactory factory = builder.build(is);
			session = factory.openSession(false); //false ? 오토커밋 false ,,
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}

}
