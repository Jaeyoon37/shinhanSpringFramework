package com.shinhan.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shinhan.myapp.model.DeptService;

@Controller
// - Controller 라고 선언
// - 요청이 오면 페이지를 return함.
// - 또는 다른 요청으로 재요청
public class SampleController {
	
	@Autowired
	DeptService dService;
	
	// 1. 요청주소와 페이지의 위치가 같으면 페이지이름은 setting 하지 않아도 됨
	@RequestMapping(value="/jspTest/test2.do", method=RequestMethod.GET)
	public void f4(Model model) {
		model.addAttribute("dept", dService.selectByIdService(327));
	}
	
	// 2. ModelAndView 리턴(Model + View)
	// @GetMapping : method가 get인 mapping
	@GetMapping(value="/test3.do")
	public ModelAndView f3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("dept", dService.selectByIdService(326));
		mv.setViewName("jspTest/test2");
		return mv;
	}
	
	// 3. view 이름을 return
	@RequestMapping(value="/test2.do", method=RequestMethod.GET)
	public String f2(Model model) {
		model.addAttribute("dept", dService.selectByIdService(325));
		return "jspTest/test2";
	}
	
	
	@RequestMapping("/test1.do")
	public String f1(Model dataStore) {
		dataStore.addAttribute("myName", "재윤이");
		dataStore.addAttribute("favorite", "서창빈");
		return "jspTest/test1"; //text1.jsp로 연결
	}
}
