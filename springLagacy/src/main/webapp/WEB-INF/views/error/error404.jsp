<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>그런 페이지 없습니다. 안돼. 돌아가.</h1>
	<h2>알림: 주소확인 필요함.</h2>
	<p><%=request.getRequestURL().toString() %></p>
	<p id="here"></p>
	<script>
		document.querySelector("#here").innerHTML = location.href;
	</script>
</body>
</html>