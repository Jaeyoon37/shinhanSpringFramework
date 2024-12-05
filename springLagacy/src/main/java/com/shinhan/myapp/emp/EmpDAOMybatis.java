package com.shinhan.myapp.emp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

// DataSource(DB연결) → sqlSessionFactory(DB, Mapper) → sqlSession 생성
//  → DAO → Service → Controller

@Slf4j
@Repository("empMybatis") //interface 구현한 클래스가 여러 개라서 이름을 다르게 줌. 이름을 안주면 empDAOMybatis가 됨.
public class EmpDAOMybatis implements EmpDAOInterface{
	
	@Autowired
	SqlSession sqlSession;
    
	String namespace = "com.shinhan.emp.";
	
	
	// return값 = sqlSession.selectOne("namesapce와 mapper 의 id", 파라메터)
	// return값 = sqlSession.selectList("")
	// return값 = sqlSession.selectMap("")
	
	public List<EmpDTO> selectByArray(List<Integer> deptArr) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByArray", deptArr);
		log.info("파라메터(list, array) >> " + emplist.size());
		return emplist;
	}
	
    public Map<String, Object> selectJoin2(String jobid) {
    	Map<String, Object> emplist = sqlSession.selectMap(namespace + "selectJoin2", jobid);
    	log.info("mybatis 이용 (MAP) >> " + emplist.size() + "건");
    	return emplist;
    }
    

    public List<JobDTO> selectAllJob() {
    	List<JobDTO> joblist = sqlSession.selectList(namespace + "selectAllJob");
    	log.info("mybatis 이용 >> " + joblist.size() + "건");
    	return joblist;
        
    }
    
    //Employees, Departments, Locations, Countries랑 조인한것 ++ Jobs도 조인
    public List<EmpJoinDTO> selectJoin(String jobid) {
    	List<EmpJoinDTO> emplist = sqlSession.selectList(namespace + "selectJoin", jobid);
    	log.info("mybatis 이용 >> " + emplist.size() + "건");
    	return emplist;
        
    }
    

    public List<EmpDTO> selectByDept(int dept_id) {
    	List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByDept", dept_id);
    	log.info("mybatis 이용 >> " + emplist.size() + "건");
    	return emplist;
    }
    

    public List<EmpDTO> selectByJob(String job_id) {
    	List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByJob", job_id);
    	log.info("mybatis 이용 >> " + emplist.size() + "건");
    	return emplist;
    }
    

    public List<EmpDTO> selectBySalary(double salary) {
    	List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectBySalary", salary);
    	log.info("mybatis 이용 >> " + emplist.size() + "건");
    	return emplist;
    }
    

    public List<EmpDTO> selectByCondition(Map<String,Object> map) {
    	List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByCondition2", map);
    	log.info("mybatis 이용 >> " + emplist.size() + "건");
    	return emplist;
        
    }   
    
    public List<EmpDTO> selectAll() {
    	List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectAll");
    	log.info("mybatis 이용 >> " + emplist.size() + "건");
    	return emplist;
    }
    public EmpDTO selectById(int empid) {
    	EmpDTO emp = sqlSession.selectOne(namespace + "selectById", empid);
    	log.info("mybatis 이용 (상세보기)>> " + emp);
    	return emp;
    }

    public int insert(EmpDTO emp) {
    	int result = sqlSession.insert(namespace + "insert", emp);
    	log.info("mybatis 이용 >> " + result + "건 입력");
    	return result;
    }

    public int update(EmpDTO emp) {
    	int result = sqlSession.update(namespace + "update", emp);
    	log.info("mybatis 이용 >> " + result + "건 수정");
    	return result;
    }

    public int delete(int empid) {
    	int result = sqlSession.delete(namespace + "delete", empid);
    	log.info("mybatis 이용 >> " + result + "건 삭제");
    	return result;
    }
}