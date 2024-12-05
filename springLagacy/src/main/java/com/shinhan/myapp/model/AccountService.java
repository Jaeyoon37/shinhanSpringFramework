package com.shinhan.myapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AccountService {
	
	@Autowired
	AccountDAOMybatis dao;
	
	public void transferService() {
		int ret1 = dao.deposit();
		int ret2 = dao.withdraw();
		log.debug("transferService 결과 >> deposit = " + ret1 + ", withdraw = " + ret2 );
		log.info("transferService 결과 >> deposit = " + ret1 + ", withdraw = " + ret2 );
		log.warn("transferService 결과 >> deposit = " + ret1 + ", withdraw = " + ret2 );
		log.error("transferService 결과 >> deposit = " + ret1 + ", withdraw = " + ret2 );
		
	}
}
