package com.shinhan.myapp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DeptDTO {

	int department_id ;
	String department_name ;
	int manager_id ;   
	int location_id ;
}
