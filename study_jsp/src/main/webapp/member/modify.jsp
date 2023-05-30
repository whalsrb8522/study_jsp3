<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
	<div id="container">
		<form id="section" class="border-gray text-center" action="/mem/modify_s2" method="post">
			<input class="border-gray bg-gray input-box" type="text" name="id" placeholder="아이디" value="${mvo.id }" readonly="readonly"> <br>
			<input class="border-gray input-box" type="password" name="password" placeholder="비밀번호(필수)"> <br>
			<input class="border-gray input-box" type="text" name="name" placeholder="이름" value="${mvo.name }"> <br>
			<input class="border-gray input-box" type="email" name="email" placeholder="이메일" value="${mvo.email }"> <br>
			<input class="border-gray input-box" type="text" name="phone" placeholder="전화번호(숫자만)" value="${mvo.phone }"> <br>
			<input class="border-gray button-blue" type="submit" value="회원수정"> <br>
			<a href="/mem/withdrawal?id=${mvo.id }"><input type="button" type="button" class="border-gray button-blue" value="회원탈퇴"></a>
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