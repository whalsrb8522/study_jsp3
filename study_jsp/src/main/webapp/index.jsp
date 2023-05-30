<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
	<div id="container">
		<c:if test="${ses eq null }">
			<form id="section" class="border-gray text-center" action="/mem/signIn" method="post">
				<input class="border-gray input-box" type="text" name="id" placeholder="아이디"> <br>
				<input class="border-gray input-box" type="password" name="password" placeholder="비밀번호"> <br>
				<input class="border-gray button-blue" type="submit" value="로그인"> <br>
				<a href="/mem/signUp_s1">회원가입</a>
			</form>
		</c:if>
		<c:if test="${ses ne null }">
			<div id="section" class="border-gray text-center">
				${ses.name }(${ses.id })님 안녕하세요. <br><br>
				<a href="/brd/list"><input class="border-gray button-blue" type="button" value="게시판"></a>
				<a href="/mem/modify_s1">회원수정</a>
				<!-- 권한이 있을 경우만 관리자 페이지가 보이게 -->
				<c:if test="${ses.auth eq true}">
					<a href="/mem/admin">관리자 페이지</a>
				</c:if>
				<a href="/mem/signOut">로그아웃</a>
			</div>
		</c:if>
	</div>
	
	<script type="text/javascript">
		const msg = `<c:out value="${msg }"></c:out>`;
		
		if (msg != '') {
			alert(msg);
		}
	</script>
</body>
</html>
