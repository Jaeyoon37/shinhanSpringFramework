package com.shinhan.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/jspTest") //class(type) level 에서 요청주소를 작성(공통적인 부분)
public class SampleController2 {

	@GetMapping("/first1.do") // function(method) level 에서 요청주소 작성(각각)
	public void f1() {
		
	}
	@RequestMapping("/first2.do")
	public void f2() {
		
	}
	@RequestMapping(value="/first3.do", method=RequestMethod.GET)
	public void f3() {
		
	}
}
