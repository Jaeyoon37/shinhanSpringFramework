<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-3">

		<%@ include file="../common/header.jsp" %>
		<h1>게시목록</h1>
		<img 
			alt="우산 이미지" width="150" height="100"
			src="${path }/resources/images/umbrella.jpg">
		<table id="table_here" class="table table-striped table-hover">
			<tr>
				<td>번호</td>
				<td>bno</td>
				<td>title</td>
				<td>content</td>
				<td>writer</td>
				<td>등록일</td>
				<td>이미지</td>
				<td></td>
			</tr>
			<c:forEach items="${boardlist }" var="board" varStatus="status">
			<tr>
				<td>${status.count}</td>		
				<td><a href="${path}/board/detail.do?bno=${board.board_no}">${board.board_no}</a></td>			
				<td>${board.title}</td>			
				<td>${board.content}</td>			
				<td>${board.writer}</td>			
				<td>${board.regdate}</td>
				<td>${board.pic}</td>
				<td>
					<c:if test="${loginMember.member_id == board.writer}">
						<button onclick="location.href='${path}/board/delete.do?bno=${board.board_no}'">
							삭제
						</button>
					</c:if>
				</td>		
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>