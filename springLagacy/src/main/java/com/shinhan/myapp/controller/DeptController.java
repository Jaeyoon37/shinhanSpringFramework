package com.shinhan.myapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.myapp.model.DeptService;
import com.shinhan.myapp.vo.DeptDTO;

// �μ��� CRUD �۾� (�̹� DAO�� �Ǿ��ְ�, Service�� DAO�� �θ�. Controller�� service �� �θ�)
// ����� ��û �� DispatcherServlet �� Controller ã��
// context:component-scan �� ���ؼ� Bean �� ������.

//@RestController = @Controller + @ResponseBody
//					�� ��û�� �޾Ƽ� ���� �����͸� return
//@Controller �� ��û�� �޾Ƽ� ������������ return

@Controller   //Controller ��� ����
//@RestController
public class DeptController {
	
	@Autowired
	DeptService dService; // �ڵ����� ���� �θ�
	
	Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	
	
	@GetMapping("/dept/detail.do")
	public String detail(int deptid, Model model) {
		model.addAttribute("deptInfo",dService.selectByIdService(deptid));
		return "dept/deptDetail";
	}
	
	/* �󼼺��� �Ŀ� ����� �����ְ�, 1�� �� list�� ����
	@PostMapping("/dept/detail.do")
	public String detailPost(@ModelAttribute("dept") DeptDTO dept) {
		logger.info(dept.toString());
		dService.updateService(dept);
		return "dept/result";
	}
	*/
	
	@PostMapping("/dept/detail.do")
	public String detailPost(DeptDTO dept, RedirectAttributes attr) {
		logger.info(dept.toString());
		int result = dService.updateService(dept);
		String message = "���� �� �� >> " + result;
		attr.addFlashAttribute("resultMessage", attr);
		return "redirect:/dept/list.do"; //���û�ϱ�(response.sendRedirect()
		
	}
	
	@GetMapping("/dept/insert.do")
	public String insertGet() {
		return "dept/deptInsert";
	}
	
	@PostMapping("/dept/insert.do")
	public String insertPost(DeptDTO dept, RedirectAttributes attr) {
		int result = dService.insertService(dept);
		String message = "�Է� �� �� >> " + result;
		logger.info(message);
		attr.addFlashAttribute("resultMessage", message);
		//dService.insertService(dept);
		return "redirect:/dept/list.do"; //���û
	}
	
	//@ResponseBody   -  request.getWriter().append("aa");
	@RequestMapping("/dept/list.do")
	public String f1(Model model, HttpServletRequest request) {
		
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map != null) {
			String message = (String)map.get("resultMessage");
			logger.info("���� �޽���" + message);
			model.addAttribute("result", message);
		}
		
		List<DeptDTO> deptlist = dService.selectAllService();
		model.addAttribute("deptlist", deptlist);
		//return "dept/deptList zzzzzzzz"; //forward, include
		// /WEB-INF/views/dept/deptlist.jsp
		return "dept/deptList";
	}
	
	
	@RequestMapping(value="/dept/delete.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(int deptid, RedirectAttributes attr) {
		int result = dService.deleteService(deptid);
		String message = "���� �� �� : " + result;
		attr.addFlashAttribute("resultMessage", message);
		return "redirect:/dept/list.do";
	}
	
	
	
}
