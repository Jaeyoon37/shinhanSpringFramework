package com.shinhan.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

//예외발생 시 전역적으로 처리하는 controller
//500 : 서버오류
//404 : 존재하지않는페이지
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public String f1(Exception ex, Model model) {
		log.info("예외 발생 class >> " + ex.getClass().getName());
		log.info("예외 발생 메시지 >> " + ex.getMessage());
		ex.printStackTrace();
		model.addAttribute("message","공사중입니다 !!");
		return "error/error500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String f2(HttpServletRequest request) {
		log.info("요청 주소 >> " + request.getRequestURI());
		return "error/error404";
	}
	
}
