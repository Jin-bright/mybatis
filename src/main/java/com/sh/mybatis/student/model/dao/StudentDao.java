package com.sh.mybatis.student.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.sh.mybatis.student.model.dto.Student;

public interface StudentDao {

	int insertStudent(SqlSession session, Student student);

	int insertStudent(SqlSession session, Map<String, Object> studentMap);

	int getTotalCount(SqlSession session);

	//1명 학생 조회할거야 - dql 
	Student selectOneStudent(SqlSession session, int no);

	//1명 업데이트 할거야 - dml 
	int updateStudent(SqlSession session, Student student);

	Map<String, Object> selectOneStudentMap(SqlSession session, int no);

	//1명 delete 할거야 
	int deleteStudent(SqlSession session, Student student);

	//여러명 select 할거야 
	List<Student> selectStudentList(SqlSession session);

	List<Map<String, Object>> selectStudentMapList(SqlSession session);
	
	
	
}
