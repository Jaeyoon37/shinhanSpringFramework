<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>직원 정보 수정</title>
<meta charset="utf-8">
<style>
	
	h2 {
		background-color: pink;
	}
	[readonly]{
		background-color: lightyellow;
	}
</style>
</head>
<body>

	<div class="container mt-3">
<!-- include 디렉티브태그는 jsp 를 합쳐서 컴파일한다. -->
<%@ include file="../common/header.jsp" %>
		<h2>직원 정보 수정</h2>

		<form action="${path}/emp/detail.do" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">직원번호</span>
				<input type="number" readonly="readonly" required="required"
						value="${empInfo.employee_id }"
						class="form-control" name="employee_id">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">이름</span>
				<input type="text" class="form-control" name="first_name" value="${empInfo.first_name }">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">성</span>
				<input type="text" required="required" class="form-control" name="last_name" value="${empInfo.last_name }">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">이메일</span>
				<input type="text" required="required" class="form-control" name="email" value="${empInfo.email }">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">핸드폰번호</span>
				<input type="text" class="form-control" placeholder="010-0000-0000" name="phone" value="${empInfo.phone_number }">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">직책</span>
				<select required="required" class="form-control" name="job_id">
					<c:forEach items="${joblist}" var="job">
						<option ${job.job_id==empInfo.job_id?'selected':''} value="${job.job_id }">
							${job.job_id} / ${job.job_title}
						</option>
					</c:forEach>
				</select>
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">입사일</span>
				<input type="date" required="required" class="form-control" placeholder="0000-00-00" name="hire_date" value="${empInfo.hire_date }">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">급여</span>
				<input type="text" class="form-control" name="salary" value="${empInfo.salary }">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">커미션</span>
				<input type="text" pattern="[0]\.[0-9]{1,2}" class="form-control" name="commission_pct" value="${empInfo.commission_pct }">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">매니저</span>
				<select class="form-control" name="manager_id">
					<option value="-1" >매니저 없음</option>
					<c:forEach items="${managerlist}" var="emp">
						<option ${empInfo.manager_id == emp.employee_id?'selected':''} value="${emp.employee_id}">
							${emp.first_name} / ${emp.last_name}
						</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">부서</span>
				<select class="form-control" name="department_id">
					<option value="-1" >부서 없음</option>
					<!-- <option value="" selected disabled hidden>카테고리를 선택해주세요.</option> -->
					<c:forEach items="${deptlist}" var="dept">
						<option ${empInfo.department_id == dept.department_id?'selected':''}
								value="${dept.department_id}">
							${dept.department_name}
						</option>
					</c:forEach>
				</select>
			</div>

			
			<button type="submit" class="btn btn-primary">수정 완료</button>
		</form>
	</div>
	

</body>
</html>