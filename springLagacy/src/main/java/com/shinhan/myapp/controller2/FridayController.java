package com.shinhan.myapp.controller2;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shinhan.myapp.vo.ParamVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/friday")
@Slf4j
public class FridayController {

	@RequestMapping(value="/one.do", method = RequestMethod.GET)
	public void f1() {
		
	}
	
	@GetMapping("/two.do")
	public String f2(
			@RequestParam Map<String, String> map,
			ParamVO param,
			String username,
			@RequestParam(value="userid", required=false, defaultValue = "0") Integer userid2) {
		
		log.info("userid >> " + userid2);
		log.info("paramVO >> " + param.toString());
		log.info("map >> " + map);
		
		return "redirect:/dept/list.do";
	}

	
}
