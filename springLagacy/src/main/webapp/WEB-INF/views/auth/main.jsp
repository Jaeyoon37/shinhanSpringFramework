<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.servletContext.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>업무선택</h1>
	<ul>
		<li><a href="${path }/auth/login.do">로그인</a></li>
		<li><a href="${path }/emp/list.do">직원조회</a></li>
		<li><a href="${path }/emp/insert.do">직원입력</a></li>
		<li><a href="${path }/dept/list.do">부서조회</a></li>
		<li><a href="${path }/dept/insert.do">부서입력</a></li>
		<li><a href="${path }/board/insert.do">Board 입력</a></li>
		<li><a href="${path }/board/list.do">Board 조회</a></li>
	</ul>
</body>
</html>