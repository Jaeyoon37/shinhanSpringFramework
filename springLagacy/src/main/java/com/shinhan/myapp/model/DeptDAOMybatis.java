package com.shinhan.myapp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.DeptDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("deptMybatis")
public class DeptDAOMybatis implements DeptDAOInterface{
	
	//DB정보 → DataSource → sqlSessionFactory → sqlSession
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.shinhan.dept.";
	
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = sqlSession.selectList(namespace + "selectAll");
		log.info("dept 조회 건 수: " + deptlist.size());
		return deptlist;
	}


	public DeptDTO selectById(int deptid) {
		DeptDTO dept = sqlSession.selectOne(namespace + "selectById", deptid);
		log.info("dept 1건 : " + dept);
		return dept;
	}
	

	public int insert(DeptDTO dept) {
		int result = sqlSession.insert(namespace + "insert", dept);
		log.info("입력 건 수 : " + result);
		return result;
	}


	public int update(DeptDTO dept) {
		int result = sqlSession.update(namespace + "update", dept);
		log.info("수정 건 수 : " + result);
		return result;
	}


	public int delete(int dept_id) { 
		int result = sqlSession.delete(namespace + "delete", dept_id);
		log.info("삭제 건 수 : " + result);
		return result;
		
	}


	
}
