package com.shinhan.myapp.controller2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.myapp.emp.EmpDTO;
import com.shinhan.myapp.emp.EmpService;

// RestAPI규칙을 따른다.
//Spring 4버전  @Controller + @ResponseBody
@RestController
@RequestMapping("/rest")
public class EmpRestController {
	
	@Autowired
	EmpService eService;
	
	// 7. 삭제
	@DeleteMapping(value = "/empdelete.do/{empid}",
			produces = "text/plain;charset=utf-8")
	public String delete(@PathVariable int empid) {
		int result = eService.deleteService(empid);
		return result>0?"delete성공":"delete실패";
	}
	
	
	// 6. 수정(put), 
	@PutMapping(value = "/empupdate.do",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = "text/plain;charset=utf-8")
	public String update(@RequestBody EmpDTO emp) {
		int result = eService.updateService(emp);
		return result>0?"update성공":"update실패";
	}
	
	
	// 5. 입력(Post), 들어오는 데이터가 있음.
	// 		- 요청 문서의 body로 데이터가 들어옴.(requestParam 아님)
	@PostMapping(value = "/empinsert.do",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = "text/plain;charset=utf-8")
	public String insert(@RequestBody EmpDTO emp) {
		int result = eService.insertService(emp);
		return result>0?"insert성공":"insert실패";
	}
	
	// 4. 모든 직원 조회, return 은 Map<직원번호, 직원DTO>
	//{100:{}, 101:{}, 102:{} }
	@GetMapping(value = "/empmap.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<Integer, EmpDTO> f4() {
		Map<Integer, EmpDTO> map = new HashMap<Integer, EmpDTO>();
		List<EmpDTO> emplist = eService.selectAllService();
		emplist.forEach(emp->{
			map.put(emp.getEmployee_id(), emp);
		});
		return map;
	}
	
	
	// 3. 특정직원조회
	// 		- url을 통해서 들어온 data는 있음.
	//		- HTTP URI를 통해 resource 를 표현한다.
	@GetMapping(value = "/empdetail.do/{empid}", produces = "application/json")
	public EmpDTO f3(@PathVariable int empid) {
		return eService.selectByIdService(empid);
	}
	
	
	//{"emplist" : [{}, {}, {}]}
	// 2. Jackson 라이브러리가 data를 json으로 변경해서 return 한다.
	@GetMapping(value = "/emplist.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmpDTO> f2() {
		return eService.selectAllService();
	}
	
	// 1. 들어온 data는 없음. return data는 단순 문자
	@GetMapping(value="/test2.do", produces = "text/plain;charset=utf-8")
	public String f1() {
		EmpDTO emp = eService.selectByIdService(50);
		return "rest방식 연습2(4버전 @RestController) >> " + emp.getFirst_name();
	}
	
}
