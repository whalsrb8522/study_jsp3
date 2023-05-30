<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
	<div id="container">
		<form id="section" class="border-gray text-center" action="/mem/signUp_s2">
			<input class="border-gray input-box" type="text" name="id" placeholder="아이디"> <br>
			<input class="border-gray input-box" type="password" name="password" placeholder="비밀번호"> <br>
			<input class="border-gray input-box" type="text" name="name" placeholder="이름"> <br>
			<input class="border-gray input-box" type="email" name="email" placeholder="이메일"> <br>
			<input class="border-gray input-box" type="text" name="phone" placeholder="전화번호(숫자만)"> <br>
			<input class="border-gray button-blue" type="submit" value="회원가입"> <br>
			<a href="javascript:history.back()">뒤로가기</a>
		</form>
	</div>
</body>
</html>