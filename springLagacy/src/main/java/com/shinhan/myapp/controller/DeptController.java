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

// 부서의 CRUD 작업 (이미 DAO에 되어있고, Service가 DAO를 부름. Controller는 service 를 부름)
// 사용자 요청 → DispatcherServlet → Controller 찾기
// context:component-scan 에 의해서 Bean 이 생성됨.

//@RestController = @Controller + @ResponseBody
//					→ 요청을 받아서 응답 데이터를 return
//@Controller → 요청을 받아서 응답페이지를 return

@Controller   //Controller 라고 선언
//@RestController
public class DeptController {
	
	@Autowired
	DeptService dService; // 자동으로 서비스 부름
	
	Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	
	
	@GetMapping("/dept/detail.do")
	public String detail(int deptid, Model model) {
		model.addAttribute("deptInfo",dService.selectByIdService(deptid));
		return "dept/deptDetail";
	}
	
	/* 상세보기 후에 결과를 보여주고, 1초 후 list로 가기
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
		String message = "수정 건 수 >> " + result;
		attr.addFlashAttribute("resultMessage", attr);
		return "redirect:/dept/list.do"; //재요청하기(response.sendRedirect()
		
	}
	
	@GetMapping("/dept/insert.do")
	public String insertGet() {
		return "dept/deptInsert";
	}
	
	@PostMapping("/dept/insert.do")
	public String insertPost(DeptDTO dept, RedirectAttributes attr) {
		int result = dService.insertService(dept);
		String message = "입력 건 수 >> " + result;
		logger.info(message);
		attr.addFlashAttribute("resultMessage", message);
		//dService.insertService(dept);
		return "redirect:/dept/list.do"; //재요청
	}
	
	//@ResponseBody   -  request.getWriter().append("aa");
	@RequestMapping("/dept/list.do")
	public String f1(Model model, HttpServletRequest request) {
		
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map != null) {
			String message = (String)map.get("resultMessage");
			logger.info("받은 메시지" + message);
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
		String message = "삭제 건 수 : " + result;
		attr.addFlashAttribute("resultMessage", message);
		return "redirect:/dept/list.do";
	}
	
	
	
}
