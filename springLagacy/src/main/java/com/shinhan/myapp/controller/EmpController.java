package com.shinhan.myapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.myapp.emp.EmpDTO;
import com.shinhan.myapp.emp.EmpService;
import com.shinhan.myapp.model.AccountService;
import com.shinhan.myapp.model.DeptService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmpController {
	
	
	final EmpService eService;
	final DeptService dService;
	final AccountService accService;
	
	@ResponseBody //응답 문서를 만들어서 data를 보낼 때 사용(즉, page 없음) response.getWriter().append("")
	@GetMapping(value="/transfer.do", produces = "text/plain;charset=utf-8")
	public String transfer() {
		accService.transferService();
		return "이체서비스 완료";
	}
	
	@GetMapping("/listByArray.do")
	public String listByArray(@RequestParam(value = "deptArr[]") Integer[] arr, Model model) {
		log.info(Arrays.toString(arr));
		//배열을 list로 변경하기
		model.addAttribute("empDatas", eService.selectByArray(Arrays.asList(arr)));
		return "emp/empListTable";
	}
	
	@GetMapping("/listByJobJoin2.do")
	public String listByJobJoin2(String job, Model model) {
		log.info("parameter : " + job);
		
		model.addAttribute("empDatas", eService.selectByJobJoin2(job));
		return "emp/empListTable2";
	}
	
	@GetMapping("/listByJobJoin.do")
	public String listByJobJoin(String job, Model model) {
		model.addAttribute("empDatas", eService.selectByJobJoin(job));
		return "emp/empListTable2";
	}
	@GetMapping("/listByDept.do")
	public String listByDept(int deptid, Model model) {
		model.addAttribute("empDatas", eService.selectByDept(deptid));
		return "emp/empListTable";
	}
	
	@GetMapping("/listByJob.do")
	public String listByJob(String job, Model model) {
		model.addAttribute("empDatas", eService.selectByJob(job));
		return "emp/empListTable";
	}

	@GetMapping("/listBySalary.do")
	public String listBySalary(double salary, Model model) {
		model.addAttribute("empDatas", eService.selectBySalary(salary));
		return "emp/empListTable";
		
	}
	
	@GetMapping("/list.do")
	public String selectAll(Model model, HttpServletRequest request) {
		
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map != null) {
			model.addAttribute("resultMessage", map.get("resultMessage") );
		}
		
		model.addAttribute("joblist", eService.selectAllJobService());
		model.addAttribute("deptlist", dService.selectAllService());
		return "emp/empList";
	}
	@GetMapping("/emplist2.do")
	public String selectCondition(Model model, @RequestParam Map<String, Object> map) {
		log.info(map.toString());
		String chk = (String)map.get("chk");
		if(chk.equals("true"))map.put("hdate", "1900-01-01");
		List<EmpDTO> emplist = eService.selectByCondition(map);
		model.addAttribute("empDatas", emplist);
		
		//empService.selectByCondition()
		return "emp/empListTable";
	}
	@GetMapping("insert.do")
	public String insertGet(Model model) {
		model.addAttribute("joblist", eService.selectAllJobService());
		model.addAttribute("deptlist", dService.selectAllService());
		model.addAttribute("managerlist", eService.selectAllService());
		
		return "emp/empInsert";
	}
	@PostMapping("/insert.do")
	public String insertPost(EmpDTO emp, RedirectAttributes attr) {
		int result = eService.insertService(emp);
		attr.addFlashAttribute("resultMessage", result>0?"삭제성공":"삭제실패");
		return "redirect:list.do";
	}

	@GetMapping("/detail.do")
	public String detailGet(int empid, Model model) {
		model.addAttribute("empInfo", eService.selectByIdService(empid));
		model.addAttribute("joblist", eService.selectAllJobService());
		model.addAttribute("deptlist", dService.selectAllService());
		model.addAttribute("managerlist", eService.selectAllService());
		return "emp/empDetail";
	}
	
	@PostMapping("/detail.do")
	public String detailPost(EmpDTO emp) {
		int result = eService.updateService(emp);
		return "redirect:list.do";
		
	}
	@RequestMapping(value = "/delete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(int empid, RedirectAttributes attr) {
		int result = eService.deleteService(empid);
		attr.addFlashAttribute("resultMessage", result>0?"삭제성공":"삭제실패");
		return "redirect:list.do";
	}
	
}
