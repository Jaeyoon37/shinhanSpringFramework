package com.shinhan.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.DeptDTO;

@Service
public class DeptService {
	
	//@Autowired 는 타입이 같으면 자동으로 injection 해줌.
	// → 같은 타입이 여러 개 있으면 오류
	// → 같은 타입이 여러 개 있으면 이름으로 injection 해야 함.
	@Autowired 
	@Qualifier("deptMybatis")
	DeptDAOInterface deptDAO;

	public List<DeptDTO> selectAllService() {
		return deptDAO.selectAll();
	}

	public int insertService(DeptDTO dept) {
		// ?엯?젰?꽌鍮꾩뒪
		return deptDAO.insert(dept); //DAO 遺덈윭?꽌 由ы꽩 ?궡媛? 諛쏆?嫄? 由ы꽩
	}

	public int deleteService(int dept_id) {
		// ?궘?젣?꽌鍮꾩뒪
		return deptDAO.delete(dept_id);
	}

	public DeptDTO selectByIdService(int dept_id) {
		// ?긽?꽭蹂닿린 ?꽌鍮꾩뒪
		return deptDAO.selectById(dept_id); //?궡媛?諛쏆?嫄? DAO?븳?뀒 ?꽆寃⑥＜湲? .由ы꽩
	}
	public int updateService(DeptDTO dept) {
		// ?닔?젙?꽌鍮꾩뒪
		return deptDAO.update(dept); 
	}


	
}
