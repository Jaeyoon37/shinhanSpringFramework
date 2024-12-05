<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	// 작성1 : JSP문법이며, WAS에서 해석된다. ${aa}
	// \${aa} : JSP문법 아님, WAS 해석 안함
	// 작성2 : 백틱 문법이며, Browser 의 JavaScript 해석기에서 해석된다. `\${aa}`
	
	var testData = "\${deptlist}";
</script>
</head>
<body>
	<div class="container">

	<%@ include file="../common/header.jsp" %>
	
	<hr>
	<button id="btnSalary" class="btn btn-primary">조회(only급여)</button>
	<button id="btnJob" class="btn btn-primary">조회(only직책)</button>
	<button id="btnDept" class="btn btn-primary">조회(only부서)</button>
	<button id="btnJobJoin" class="btn btn-primary">조회(only직책join)</button>
	<button id="btnJobJoin2" class="btn btn-primary">조회(only직책join-map)</button>
	<button id="btnArray" class="btn btn-primary">조회(부서배열)</button>
	<button id="btnTransfer" class="btn btn-primary">Transaction 연습</button>
	<hr>
	<h3>Restful API 사용하기</h3>
	<button id = "btnSelect" class="btn btn-secondary">전체조회</button>
	<input type="number" id="empid" value="100">
	<button id = "btnDetail" class="btn btn-secondary">상세보기</button>
	<button id = "btnInsert" class="btn btn-secondary">입력</button>
	<button id = "btnUpdate" class="btn btn-secondary">수정</button>
	<button id = "btnDelete" class="btn btn-secondary">삭제</button>
	
	<script>
		$(function(){
			$("#btnSelect").click(f_select);
			$("#btnDetail").click(f_detail);
			$("#btnInsert").click(f_insert);
			$("#btnUpdate").click(f_update);
			$("#btnDelete").click(f_delete);
		});
		
		
		function f_select(){
			$.ajax({
				url:"${path}/rest/emplist.do",
				//가져갈 게 없어서 데이터 필요없음.
				success:function(responseData){
					console.log(responseData);
					var output = print(responseData);
					$("#table_here").html(output);
				},
				error:function(err){
					console.log(err);
				}
			});
		}
		
		function print(responseData){
			var dynamicRows = "";
			$.each(responseData, function(index,emp){
				dynamicRows += `
					<tr>
					<td>\${index+1}</td>
					<td>
						<a href="${path}/emp/detail.do?empid=\${emp.employee_id}">
							\${emp.employee_id}
						</a>
					</td>
					<td>\${emp.first_name}</td>
					<td>\${emp.last_name}</td>
					<td>\${emp.email}</td>
					<td>\${emp.phone_number}</td>
					<td>\${emp.job_id}</td>
					<td>\${emp.hire_date}</td>
					<td>\${emp.commission_pct}</td>
					<td>\${emp.salary}</td>
					<td>\${emp.manager_id}</td>
					<td>\${emp.department_id}</td>
					
				</tr>
				`;
			});
			var output = `
				<table class="table table-striped table-hover">
					<tr>
						<td>번호</td>
						<td>직원번호</td>
						<td>fname</td>
						<td>lname</td>
						<td>email</td>
						<td>phone</td>
						<td>job</td>
						<td>hiredate</td>
						<td>commission</td>
						<td>salary</td>
						<td>manager</td>
						<td>department</td>
					</tr>
					\${dynamicRows}
				</table>
			`;
			return output;
		}
		
		function f_detail(){
			var empid = $("#empid").val();
			$.ajax({
				url:`${path}/rest/empdetail.do/\${empid}`,
				success:function(responseData){
					console.log(responseData);
					var dynamicOutput = "";
					for(let prop in responseData){
						dynamicOutput += `<li>\${prop} >>> \${responseData[prop]}</li>`;
					}
					var output = `
						<ul>
							\${dynamicOutput}
						</ul>
					`;
					$("#table_here").html(output);
				},
				error:function(err){
					console.log(err);
				}
			});
		}
		
		function getData(){
			var obj = {
					"employee_id": 2,
					"first_name": "정뱅이",
					"last_name": "조",
					"email": "gyuram@inast",
					"phone_number": "010-4425-1106",
					"hire_date": 1055775600000,
					"job_id": "AD_PRES",
					"salary": 24000.0,
					"commission_pct": 0.5,
					"manager_id": 100,
					"department_id": 90
			};
			return obj;
		}
		function f_insert(){
			var emp = getData();
			$.ajax({
				url:"${path}/rest/empinsert.do",
				type:"post",
				contentType : "application/json",
				data:JSON.stringify(emp),
				success: function(responseData){
					alert(responseData);
				},
				error: function(err){
					console.log(err);
				}
			});
		
		}
		function f_update(){
			var emp = getData();
			$.ajax({
				url:"${path}/rest/empupdate.do",
				type:"put",
				contentType : "application/json",
				data:JSON.stringify(emp),
				success: function(responseData){
					alert(responseData);
				},
				error: function(err){
					console.log(err);
				}
			});
		}
		function f_delete(){
			var empid = $("#empid").val();
			$.ajax({
				url:`${path}/rest/empdelete.do/\${empid}`,
				type:"delete",
				success:function(responseData){
					alert(responseData);
				},
				error:function(err){
					console.log(err);
				}
			});
		}
	</script>
	<hr>
	
	<fieldset>
		<legend>조건선택</legend>
		<div class="input-group mb-3">
				<span class="input-group-text">부서</span>
				<select class="form-control" name="department_id">
					<option value="-1" >선택 안 함</option>
					<c:forEach items="${deptlist}" var="dept">
						<option value="${dept.department_id}">
							${dept.department_name}
						</option>
					</c:forEach>
				</select>
				
				<span class="input-group-text">직책</span>
				<select required="required" class="form-control" name="job_id">
					<option value="-1" >선택 안 함</option>
					<c:forEach items="${joblist}" var="job">
						<option ${job.job_id=='IT_PROG'?'selected':''} value="${job.job_id }">
							${job.job_id} / ${job.job_title}
						</option>
					</c:forEach>
				</select>
				
				<span class="input-group-text">급여(이상)</span>
				<input type="number" class="form-control" name="salary" value="0">
				
				<span class="input-group-text">입사일(이후)</span>
				<input type="date" required="required" class="form-control" placeholder="0000-00-00" name="hire_date">
				
				<span class="input-group-text">
					<input type="checkbox" name="chkDate" value="1">모든 일자
				</span>
				
				<button id="btn_condition" class="btn btn-danger">조건 조회</button>
				
			</div>
	</fieldset>
	<hr>
	<h1>직원List</h1>
	<div id="table_here">
	
	<table class="table table-striped table-hover">
		<tr>
			<td>직원번호</td>
			<td>fname</td>
			<td>lname</td>
			<td>email</td>
			<td>phone</td>
			<td>job</td>
			<td>hiredate</td>
			<td>commission</td>
			<td>salary</td>
			<td>manager</td>
			<td>department</td>
			<td>get 요청</td>
			<td>post 요청</td>
		</tr>
		<c:forEach items="${empDatas}" var="emp">
			<tr>
				<td>
					<a href="${path}/emp/detail.do?empid=${emp.employee_id}">
						${emp.employee_id}
					</a>
				</td>
				<td>${emp.first_name}</td>
				<td>${emp.last_name}</td>
				<td>${emp.email}</td>
				<td>${emp.phone_number}</td>
				<td>${emp.job_id}</td>
				<td>${emp.hire_date}</td>
				<td>${emp.commission_pct}</td>
				<td>${emp.salary}</td>
				<td>${emp.manager_id}</td>
				<td>${emp.department_id}</td>
				<td>
					<button class="btn btn-success"
							onclick="location.href='${path}/emp/delete.do?empid=${emp.employee_id}'">삭제(get)
					</button>
				</td>
				<td>
					<form action="${path}/emp/delete.do" method="post">
					<input type="hidden" name="empid" value="${emp.employee_id }">
						<button class="btn btn-success">삭제(post)</button>
					</form>
				</td>
			</tr>
		</c:forEach> <!-- c:은 jsp 태그 -->
	</table>
	</div>
	</div>
<script>
	$(function(){
		
		var result = "${resultMessage}";
		if(result!=""){
			alert(result);
		}
		
		var d = new Date();
		d.setFullYear(d.getFullYear() - 20);
		$('[name="hire_date"]').val(d.toISOString().split("T")[0]);
		$("#btn_condition").on("click", f_ajax);
		$("#btn_condition").trigger("click"); //이벤트 호출
	});

	$("#btnSalary").on("click", f_salary);
	$("#btnJob").on("click", f_job);
	$("#btnDept").on("click", f_dept);
	$("#btnJobJoin").on("click", f_jobjoin);
	$("#btnJobJoin2").on("click", f_jobjoin2);
	$("#btnArray").on("click", f_deptArray);
	$("#btnTransfer").on("click", f_transfer);
	
	function f_transfer(){
		$.ajax({
			url: "${path}/emp/transfer.do",
			success: function(responseData){
				alert("responseData");
			},
			error:function(){}
		});
	}
	function f_deptArray(){
		$.ajax({
			url:"${path}/emp/listByArray.do",
			data:{deptArr : [10, 60, 90]},
			success:function(responseData){
				$("#table_here").html(responseData);
			},
			error:function(){}
		});
	}
	function f_salary(){
		$.ajax({
			url: "${path}/emp/listBySalary.do",
			data:{salary: $("input[name='salary']").val()},
			success:function(responseData){
				$("#table_here").html(responseData);
			},
			error:function(){}
		});
	}
	function f_job(){
		$.ajax({
			url: "${path}/emp/listByJob.do",
			data:{job: $("select[name='job_id']").val()},
			success:function(responseData){
				$("#table_here").html(responseData);
			},
			error:function(){}
		});
	}
	function f_dept(){
		$.ajax({
			url: "${path}/emp/listByDept.do",
			data:{deptid: $("select[name='department_id']").val()},
			success:function(responseData){
				$("#table_here").html(responseData);
			},
			error:function(){}
		});
	}
	function f_jobjoin(){
		$.ajax({
			url: "${path}/emp/listByJobJoin.do",
			data:{job: $("select[name='job_id']").val()},
			success:function(responseData){
				$("#table_here").html(responseData);
			},
			error:function(){}
		});
	
	}
	
	function f_jobjoin2(){
		$.ajax({
			url: "${path}/emp/listByJobJoin2.do",
			data:{job: $("select[name='job_id']").val()},
			success:function(responseData){
				$("#table_here").html(responseData);
			},
			error:function(){}
		});
	
	}
	function f_ajax(){
		$.ajax({
			url:"${path}/emp/emplist2.do",
			type:"get",
			data:{"deptid" : $('[name="department_id"]').val(),
					"jobid" : $('[name="job_id"]').val(),
					"salary" : $('[name="salary"]').val(),
					"hdate" : $('[name="hire_date"]').val(),
					"chk" : $('[name="chkDate"]').prop("checked")
				},
			success:function(responseData){
				//emplistServlet2
				// 2. data를 받아서 HTML 만들까? no
				// 3. jSP 를 받아서 현재 화면에 대치(replace)? ok
				$("#table_here").html(responseData);
			},
			error:function(err){
				alert(err);
			}
		});
	}
</script>
</body>
</html>