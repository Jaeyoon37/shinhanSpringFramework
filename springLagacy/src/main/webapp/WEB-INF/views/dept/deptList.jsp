<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	var result = "${result}";
	if(result!=""){
	    alert(result);
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<%@ include file="../common/header.jsp"%>
		<a class="btn btn-success" href="insert.do">신규등록(이동)</a>

		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#myModal">신규등록(modal)</button>
		<hr>

		<h1>부서목록</h1>
		<table class="table table-bordered border-secondary table-striped align-middle">
			<tr>
				<th>부서번호</th>
				<th>부서이름</th>
				<th>매니저번호</th>
				<th>지역변호</th>
				<th></th>
			</tr>

			<c:forEach items="${deptlist}" var="dept">
				<tr class="table">
					<td><a href="detail.do?deptid=${dept.department_id }">${dept.department_id}</a></td>
					<td>${dept.department_name}</td>
					<td>${dept.manager_id}</td>
					<td>${dept.location_id}</td>
					<td><button
							onclick="location.href='delete.do?deptid=${dept.department_id}'">삭제</button></td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<!-- The Modal -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">부서 등록</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="dept/insert.do" method="post">
						<div class="input-group mb-3">
							<span class="input-group-text">부서번호</span> <input type="number"
								required="required" class="form-control" placeholder="부서번호 입력"
								name="department_id">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">부서이름</span> <input type="text"
								required="required" class="form-control" placeholder="부서이름 입력"
								name="department_name">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">부서장</span> <input type="number"
								required="required" class="form-control" value="50"
								name="manager_id">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">지역번호</span> <input type="number"
								required="required" class="form-control" value="1700"
								name="location_id">
						</div>

						<input type="hidden" name="phone" value="010-1234-5678">

						<button type="submit" class="btn btn-primary">신규부서 등록</button>
					</form>

				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>