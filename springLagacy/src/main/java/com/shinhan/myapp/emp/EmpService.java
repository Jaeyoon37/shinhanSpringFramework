package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmpService {

	//@Autowired : type이 같으면 injection
	//@Qualifier : 이름으로 injection
	@Qualifier("empMybatis") //EmpDAOMybatis에서 가져오기
	
	@Autowired
	EmpDAOMybatis empDAO;
	
	public List<EmpDTO> selectByArray(List<Integer> deptArr){
		return empDAO.selectByArray(deptArr);
	}
	public Map<String, Object> selectByJobJoin2(String job_id){
		return empDAO.selectJoin2(job_id);
	}
	
	//join(직원employees,부서departments,지역 locations, 나라 countries)
	public List<EmpJoinDTO> selectByJobJoin(String job_id){
		return empDAO.selectJoin(job_id);
	}
	public List<JobDTO> selectAllJobService() {
		return empDAO.selectAllJob();
	}

	public List<EmpDTO> selectByDept(int dept_id) {
		return empDAO.selectByDept(dept_id);
	}

	public List<EmpDTO> selectByJob(String job_id) {
		return empDAO.selectByJob(job_id);
	}

	public List<EmpDTO> selectBySalary(double salary) {
		return empDAO.selectBySalary(salary);
	}

	public List<EmpDTO> selectByCondition(Map<String, Object> map) {
		return empDAO.selectByCondition(map);
	}


	public List<EmpDTO> selectAllService() {
		return empDAO.selectAll();
	}

	public EmpDTO selectByIdService(int empid) {
		return empDAO.selectById(empid);
	}

	public int insertService(EmpDTO emp) {
		return empDAO.insert(emp);

	}

	public int updateService(EmpDTO emp) {
		return empDAO.update(emp);
	}

	public int deleteService(int empid) {
		return empDAO.delete(empid);
	}
}