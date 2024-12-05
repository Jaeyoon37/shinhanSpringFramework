package com.shinhan.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.DeptDTO;

@Service
public class DeptService {
	
	//@Autowired �� Ÿ���� ������ �ڵ����� injection ����.
	// �� ���� Ÿ���� ���� �� ������ ����
	// �� ���� Ÿ���� ���� �� ������ �̸����� injection �ؾ� ��.
	@Autowired 
	@Qualifier("deptMybatis")
	DeptDAOInterface deptDAO;

	public List<DeptDTO> selectAllService() {
		return deptDAO.selectAll();
	}

	public int insertService(DeptDTO dept) {
		// ?��?��?��비스
		return deptDAO.insert(dept); //DAO 불러?�� 리턴 ?���? 받�?�? 리턴
	}

	public int deleteService(int dept_id) {
		// ?��?��?��비스
		return deptDAO.delete(dept_id);
	}

	public DeptDTO selectByIdService(int dept_id) {
		// ?��?��보기 ?��비스
		return deptDAO.selectById(dept_id); //?���?받�?�? DAO?��?�� ?��겨주�? .리턴
	}
	public int updateService(DeptDTO dept) {
		// ?��?��?��비스
		return deptDAO.update(dept); 
	}


	
}
