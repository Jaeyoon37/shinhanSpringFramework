<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
#loginInfo {
	background-color: beige;
	border: 1px dotted gray;
}
</style>

<div id="loginInfo">
	<p>
		<c:if test="${loginMember.member_name == null }">네 손님 어서오십시오</c:if>
		<c:if test="${loginMember.member_name != null }">${loginMember.member_name}님 환영합니다!</c:if>

		<a href="${path}/auth/logout.do">로그아웃</a>
	</p>
</div>
