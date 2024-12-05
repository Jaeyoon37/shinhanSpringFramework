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

// RestAPI��Ģ�� ������.
//Spring 4����  @Controller + @ResponseBody
@RestController
@RequestMapping("/rest")
public class EmpRestController {
	
	@Autowired
	EmpService eService;
	
	// 7. ����
	@DeleteMapping(value = "/empdelete.do/{empid}",
			produces = "text/plain;charset=utf-8")
	public String delete(@PathVariable int empid) {
		int result = eService.deleteService(empid);
		return result>0?"delete����":"delete����";
	}
	
	
	// 6. ����(put), 
	@PutMapping(value = "/empupdate.do",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = "text/plain;charset=utf-8")
	public String update(@RequestBody EmpDTO emp) {
		int result = eService.updateService(emp);
		return result>0?"update����":"update����";
	}
	
	
	// 5. �Է�(Post), ������ �����Ͱ� ����.
	// 		- ��û ������ body�� �����Ͱ� ����.(requestParam �ƴ�)
	@PostMapping(value = "/empinsert.do",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = "text/plain;charset=utf-8")
	public String insert(@RequestBody EmpDTO emp) {
		int result = eService.insertService(emp);
		return result>0?"insert����":"insert����";
	}
	
	// 4. ��� ���� ��ȸ, return �� Map<������ȣ, ����DTO>
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
	
	
	// 3. Ư��������ȸ
	// 		- url�� ���ؼ� ���� data�� ����.
	//		- HTTP URI�� ���� resource �� ǥ���Ѵ�.
	@GetMapping(value = "/empdetail.do/{empid}", produces = "application/json")
	public EmpDTO f3(@PathVariable int empid) {
		return eService.selectByIdService(empid);
	}
	
	
	//{"emplist" : [{}, {}, {}]}
	// 2. Jackson ���̺귯���� data�� json���� �����ؼ� return �Ѵ�.
	@GetMapping(value = "/emplist.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmpDTO> f2() {
		return eService.selectAllService();
	}
	
	// 1. ���� data�� ����. return data�� �ܼ� ����
	@GetMapping(value="/test2.do", produces = "text/plain;charset=utf-8")
	public String f1() {
		EmpDTO emp = eService.selectByIdService(50);
		return "rest��� ����2(4���� @RestController) >> " + emp.getFirst_name();
	}
	
}
