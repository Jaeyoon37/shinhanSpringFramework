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
// - Controller ��� ����
// - ��û�� ���� �������� return��.
// - �Ǵ� �ٸ� ��û���� ���û
public class SampleController {
	
	@Autowired
	DeptService dService;
	
	// 1. ��û�ּҿ� �������� ��ġ�� ������ �������̸��� setting ���� �ʾƵ� ��
	@RequestMapping(value="/jspTest/test2.do", method=RequestMethod.GET)
	public void f4(Model model) {
		model.addAttribute("dept", dService.selectByIdService(327));
	}
	
	// 2. ModelAndView ����(Model + View)
	// @GetMapping : method�� get�� mapping
	@GetMapping(value="/test3.do")
	public ModelAndView f3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("dept", dService.selectByIdService(326));
		mv.setViewName("jspTest/test2");
		return mv;
	}
	
	// 3. view �̸��� return
	@RequestMapping(value="/test2.do", method=RequestMethod.GET)
	public String f2(Model model) {
		model.addAttribute("dept", dService.selectByIdService(325));
		return "jspTest/test2";
	}
	
	
	@RequestMapping("/test1.do")
	public String f1(Model dataStore) {
		dataStore.addAttribute("myName", "������");
		dataStore.addAttribute("favorite", "��â��");
		return "jspTest/test1"; //text1.jsp�� ����
	}
}
