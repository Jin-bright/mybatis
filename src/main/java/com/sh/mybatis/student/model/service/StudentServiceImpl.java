package com.sh.mybatis.student.model.service;

import org.apache.ibatis.session.SqlSession;

import static com.sh.mybatis.common.SqlSessionTemplate.getSqlSession;

import java.util.List;
import java.util.Map;

import com.sh.mybatis.common.SqlSessionTemplate;
import com.sh.mybatis.student.model.dao.StudentDao;
import com.sh.mybatis.student.model.dto.Student;

public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	
	public StudentServiceImpl(StudentDao studentDao) {
		this.studentDao = studentDao;
		
		// TODO Auto-generated constructor stub
	}

	
	// interface 에서 먼저 만든걸 오버라이딩하는거임!자식이니까,,
	@Override
	public int insertStudent(Student student) {
		// 커넥션대용
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudent(session, student);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close(); //dbcp 라고 디비커넥션풀에 반환 = 메모리 해제아님 
		}
		return result;
	}


	//2.
	@Override
	public int insertStudent(Map<String, Object> studentMap) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudent(session, studentMap);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		
		return result;
	}


	//조회 - dql - 하지만 리턴값 int 
	@Override
	public int getTotalCount() {
		SqlSession session = getSqlSession();
		int totalCount = studentDao.getTotalCount(session);
		session.close();
		return totalCount;
	}

//1명 조회할거야 - dql 
	@Override
	public Student selectOneStudent(int no) {
		SqlSession session = getSqlSession();
		Student student = studentDao.selectOneStudent(session, no);
		session.close();	
		return student;
	}


	//1명 업데이트 할거야 
	@Override
	public int updateStudent(Student student) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.updateStudent(session, student);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		}finally {
			session.close();
		}
		return result;
	}


	@Override
	public Map<String, Object> selectOneStudentMap(int no) {
		SqlSession session = getSqlSession();
		Map<String, Object>  student = studentDao.selectOneStudentMap(session, no);
		session.close();	
		return student;
	}

	//1명 delete 할거야 
	@Override
	public int deleteStudent(Student student) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentDao.deleteStudent( session, student);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		}finally {
			session.close();
		}
		return result;
	}


	//여러명 조회할거야 - dql 
	@Override
	public List<Student> selectStudentList() {
		SqlSession session = getSqlSession();
		List<Student> students = studentDao.selectStudentList(session);
		session.close();
		return students;
	}


	@Override
	public List<Map<String, Object>> selectStudentMapList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> studentMapList = studentDao.selectStudentMapList(session);
		session.close();
		return studentMapList;
	}
	
	
}
