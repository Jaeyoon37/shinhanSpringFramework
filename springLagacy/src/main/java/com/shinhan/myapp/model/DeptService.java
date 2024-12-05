package com.shinhan.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.DeptDTO;

@Service
public class DeptService {
	
	//@Autowired ´Â Å¸ÀÔÀÌ °°À¸¸é ÀÚµ¿À¸·Î injection ÇØÁÜ.
	// ¡æ °°Àº Å¸ÀÔÀÌ ¿©·¯ °³ ÀÖÀ¸¸é ¿À·ù
	// ¡æ °°Àº Å¸ÀÔÀÌ ¿©·¯ °³ ÀÖÀ¸¸é ÀÌ¸§À¸·Î injection ÇØ¾ß ÇÔ.
	@Autowired 
	@Qualifier("deptMybatis")
	DeptDAOInterface deptDAO;

	public List<DeptDTO> selectAllService() {
		return deptDAO.selectAll();
	}

	public int insertService(DeptDTO dept) {
		// ?…? ¥?„œë¹„ìŠ¤
		return deptDAO.insert(dept); //DAO ë¶ˆëŸ¬?„œ ë¦¬í„´ ?‚´ê°? ë°›ì?ê±? ë¦¬í„´
	}

	public int deleteService(int dept_id) {
		// ?‚­? œ?„œë¹„ìŠ¤
		return deptDAO.delete(dept_id);
	}

	public DeptDTO selectByIdService(int dept_id) {
		// ?ƒ?„¸ë³´ê¸° ?„œë¹„ìŠ¤
		return deptDAO.selectById(dept_id); //?‚´ê°?ë°›ì?ê±? DAO?•œ?…Œ ?„˜ê²¨ì£¼ê¸? .ë¦¬í„´
	}
	public int updateService(DeptDTO dept) {
		// ?ˆ˜? •?„œë¹„ìŠ¤
		return deptDAO.update(dept); 
	}


	
}
