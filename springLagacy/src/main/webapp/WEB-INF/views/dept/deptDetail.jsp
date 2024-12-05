<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>부서 정보 수정</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
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
		<h2>부서 정보 수정</h2>

		<form action="${path }/dept/detail.do" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">부서번호</span>
				<input type="number" required="required" readonly="readonly"
				class="form-control" value="${deptInfo.department_id }" name="department_id">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">부서이름</span>
				<input type="text" required="required" 
					value="${deptInfo.department_name }"
					class="form-control" name="department_name">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">부서장</span>
				<input type="number" required="required" class="form-control" value="${deptInfo.manager_id }" name="manager_id">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">지역번호</span>
				<input type="number" required="required" class="form-control" value="${deptInfo.location_id }" name="location_id">
			</div>

			
			
			<button type="submit" class="btn btn-primary">수정 완료</button>
		</form>
	</div>
	

</body>
</html>