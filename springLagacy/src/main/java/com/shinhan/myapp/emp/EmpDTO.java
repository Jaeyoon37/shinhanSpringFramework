package com.shinhan.myapp.emp;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//DTO : Data Transfer Object 
//VO : Value Object 
@Getter@Setter@ToString@Builder
@NoArgsConstructor@AllArgsConstructor
public class EmpDTO {
	//기본형 datatype은 null setting불가
	// → 기본형을 전부 바꿔주기 wrapper class로
	Integer employee_id;    
	String first_name ;    
	String last_name;      
	String email ;         
	String phone_number ;  
	Date hire_date ;     
	String job_id ;        
	Double salary;
	Double commission_pct ;
	Integer manager_id;     
	Integer department_id  ;
	
}