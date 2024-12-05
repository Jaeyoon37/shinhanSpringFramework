<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>coffee.jsp 파일입니당 ㄴ^0^ㄱ</h1>
	<form action="${path}/jspTest2/coffee3.do">
		부서id >> <input type="number" name="department_id"><br>
		부서명 >> <input type="text" name="dapartment_name"><br>
		매니저 >> <input type="number" name="manager_id"><br>
		지역 >> <input type="number" name="location_id"><br>
		<input type="submit" value="서버전송">
	</form>
</body>
</html>