package com.shinhan.myapp;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 여러 가지 형태의 요청 학습

@Controller
public class SampleController3 {

	org.slf4j.Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
	//요청의 주소가 같고 넘어오는 파라메터도 확인
	// - userid 라는 파라메터의 값은 꼭 같아야함
	// - userpw 는 존재만 하면 됨.
	// - email 은 존재 안 함.
	@RequestMapping(value="/second4.do", params = {"userid=jyoon", "userpw", "!email"})
	public String f3(String userid, String userpw) {
		logger.info("아이디 >> " + userid);
		logger.info("비밀번호 >> " + userpw);
		return "jspTest/second3";
	}
	
	@RequestMapping(value = {"/second3.do"}, method=RequestMethod.POST)
	public String f2(String userid, int userpw) {
		logger.info("아이디 >> " + userid);
		logger.info("비밀번호 >> " + userpw);
		return "jspTest/second3";
	}
	
	@RequestMapping(value = {"/jspTest/second1.do", "/jspTest/second2.do"}, method=RequestMethod.GET)
	public String f1() {
		return "jspTest/first1";
	}
}
