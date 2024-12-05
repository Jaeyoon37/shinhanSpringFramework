<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-3">
		<%@ include file="../common/header.jsp" %>
		<h1>게시글 상세보기</h1>

		<form action="update.do" method="post">
			<div class="input-group mb-3">
				<span class="input-group-text">번호</span>
				<input type="number" readonly="readonly" class="form-control" name="board_no" value="${board.board_no}">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">제목</span>
				<input type="text" class="form-control" name="title" value="${board.title }">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">등록일</span>
				<input type="text" readonly="readonly" class="form-control" name="regdate" value="${board.regdate }">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">작성자</span>
				<input type="text" readonly="readonly" class="form-control" name="writer" value="${board.writer }">
			</div>
			<div class="input-group mb-3">
				<span class="input-group-text">내용</span>
				<input type="text" class="form-control" name="content" value="${board.content}">
			</div>
			<div class="input-group mb-3">
				<img alt="${board.title}이미지" src="${path}/resources/upload/${board.pic}"
					width="260" height="210">
			</div>
			<c:if test="${loginMember.member_id == board.writer }">
				<div class="input-group mb-3">
					<input class="btn btn-primary" type="submit" value="수정">
				</div>
			</c:if>
			
		</form>
		
	</div>
</body>
</html>