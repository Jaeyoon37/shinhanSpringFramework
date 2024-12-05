package com.shinhan.myapp;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// ���� ���� ������ ��û �н�

@Controller
public class SampleController3 {

	org.slf4j.Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
	//��û�� �ּҰ� ���� �Ѿ���� �Ķ���͵� Ȯ��
	// - userid ��� �Ķ������ ���� �� ���ƾ���
	// - userpw �� ���縸 �ϸ� ��.
	// - email �� ���� �� ��.
	@RequestMapping(value="/second4.do", params = {"userid=jyoon", "userpw", "!email"})
	public String f3(String userid, String userpw) {
		logger.info("���̵� >> " + userid);
		logger.info("��й�ȣ >> " + userpw);
		return "jspTest/second3";
	}
	
	@RequestMapping(value = {"/second3.do"}, method=RequestMethod.POST)
	public String f2(String userid, int userpw) {
		logger.info("���̵� >> " + userid);
		logger.info("��й�ȣ >> " + userpw);
		return "jspTest/second3";
	}
	
	@RequestMapping(value = {"/jspTest/second1.do", "/jspTest/second2.do"}, method=RequestMethod.GET)
	public String f1() {
		return "jspTest/first1";
	}
}
