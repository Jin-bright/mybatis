package com.sh.mybatis.student.model.service;

import java.util.List;
import java.util.Map;

import com.sh.mybatis.student.model.dto.Student;

public interface StudentService {

	int insertStudent(Student student);

	int insertStudent(Map<String, Object> studentMap);

	int getTotalCount();

	//1명 조회할거야  - dql 
	Student selectOneStudent(int no);

	//1명 업데이트할거야 - dml 
	int updateStudent(Student student);

	//1명 조회할거야 - map - 비동기 
	Map<String, Object> selectOneStudentMap(int no);

	//1명 delete update 할거야 - 비동기 
	int deleteStudent(Student student);

	// 여러명 조회 할거야 
	List<Student> selectStudentList();

	//m
	List<Map<String, Object>> selectStudentMapList();
}
