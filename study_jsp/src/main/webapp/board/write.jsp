<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<body>
	<div id="container">
		<form id="section" class="border-gray text-center" action="/brd/write_s2" method="post">
			<input class="border-gray input-box" type="text" name="title" placeholder="제목"> <br>
			<input class="border-gray bg-gray input-box" type="text" name="writer" placeholder="작성자" value="${ses.id }" readonly="readonly"> <br>
			<textarea class="border-gray textarea" name="content" placeholder="내용을 입력해주세요." rows="20"></textarea>
			<input class="border-gray button-blue" type="submit" value="작성완료">
			<a href="javascript:history.back()">뒤로가기</a>
		</form>
	</div>
	
	<script type="text/javascript">
		const msg = `<c:out value="${msg }"></c:out>`;
		
		if (msg != '') {
			alert(msg);
		}
	</script>
</body>
</html>