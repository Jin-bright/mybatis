package com.sh.mybatis.student.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.student.model.dto.Student;

public class StudentDaoImpl implements StudentDao {

	//오버라이드해줘야된다
	@Override
	public int insertStudent(SqlSession session, Student student) {
		//session.insert(statement:String, parameter:Object )
		//statement = mapper파일의 namespace.id값 
		//parameter - 0 / 1개
		return session.insert("student.insertStudent", student);
	}

	@Override
	public int insertStudent(SqlSession session, Map<String, Object> studentMap) {
		return session.insert("student.insertStudent", studentMap);
	}

	
	@Override
	public int getTotalCount(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectOne("student.getTotalCount");
	}

	//1명 조회할거야 - 1행 
	@Override
	public Student selectOneStudent(SqlSession session, int no) {
		// TODO Auto-generated method stub
		return session.selectOne("student.selectOneStudent", no);
	}

	//1명 업데이트 할거야 - 1행 
	@Override
	public int updateStudent(SqlSession session, Student student) {
		// TODO Auto-generated method stub
		return session.update("student.updateStudent", student);
	}

	@Override
	public Map<String, Object> selectOneStudentMap(SqlSession session, int no) {
		// TODO Auto-generated method stub
		return session.selectOne("student.selectOneStudentMap", no);
	}

	
	
	//1명 delete 할거야 
	@Override
	public int deleteStudent(SqlSession session, Student student) {
		// TODO Auto-generated method stub
		return session.update("student.deleteStudent", student);
	}

	
	//여러명 select 할거야 
	@Override
	public List<Student> selectStudentList(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectList("student.selectStudent");
	}

	@Override
	public List<Map<String, Object>> selectStudentMapList(SqlSession session) {
		return session.selectList("student.selectStudentMapList");
	}
	
	

}
