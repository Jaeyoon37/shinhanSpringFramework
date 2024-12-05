<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>신규 직원 등록 </title>
<meta charset="utf-8">
<style>
	[required="required"]{
		border: 2px dotted blue;
	}
</style>
</head>
<body>

	<div class="container mt-3">
<!-- include 디렉티브태그는 jsp 를 합쳐서 컴파일한다. -->
<%@ include file="../common/header.jsp" %>
		<h2>신규 직원 등록</h2>

		<form action="${path}/emp/insert.do" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">직원번호</span>
				<input type="number" required="required" class="form-control" placeholder="직원번호 입력" name="employee_id">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">이름</span>
				<input type="text" class="form-control" name="first_name">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">성</span>
				<input type="text" required="required" class="form-control" name="last_name">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">이메일</span>
				<input type="text" required="required" class="form-control" name="email">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">핸드폰번호</span>
				<input type="text" class="form-control" placeholder="010-0000-0000" name="phone">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">직책</span>
				<select required="required" class="form-control" name="job_id">
					<c:forEach items="${joblist}" var="job">
						<option ${job.job_id=='IT_PROG'?'selected':''} value="${job.job_id }">
							${job.job_id} / ${job.job_title}
						</option>
					</c:forEach>
				</select>
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">입사일</span>
				<input type="date" required="required" class="form-control" placeholder="0000-00-00" name="hire_date">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">급여</span>
				<input type="text" class="form-control" name="salary">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">커미션</span>
				<input type="text" pattern="[0]\.[0-9]{1,2}" class="form-control" name="commission_pct">
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">매니저</span>
				<select class="form-control" name="manager_id">
					<option value="-1" >매니저 없음</option>
					<c:forEach items="${managerlist}" var="mng">
						<option value="${mng.manager_id}">
							${mng.first_name} / ${mng.last_name}
						</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="input-group mb-3">
				<span class="input-group-text">부서</span>
				<select class="form-control" name="department_id">
				<!-- <option value="" selected disabled hidden>카테고리를 선택해주세요.</option> -->
					<option value="-1" >부서 없음</option>
					<c:forEach items="${deptlist}" var="dept">
						<option value="${dept.department_id}">
							${dept.department_name}
						</option>
					</c:forEach>
				</select>
			</div>

			
			<button type="submit" class="btn btn-primary">신규 직원 등록</button>
		</form>
	</div>
	<script type="text/javascript">
		var today = new Date().toISOString().split("T")[0];
		document.querySelector("input[name='hire_date']").value = today;
	</script>

</body>
</html>