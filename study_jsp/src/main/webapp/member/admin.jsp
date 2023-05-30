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
		<div id="section" class="border-gray text-center">
			<table class="table-gray">
				<thead>
					<tr>
						<th>아이디</th>
						<th>이름</th>
						<th>가입일</th>
						<th>관리자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="mvo" items="${listMvo }">
						<tr onclick="location.href='/mem/modify_s1?id=${mvo.id}'" style="cursor: pointer;">
							<td>${mvo.id }</td>
							<td>${mvo.name }</td>
							<td>${mvo.regdate }</td>
							<td>${mvo.auth ? 'O' : 'X' }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="javascript:history.back()">뒤로가기</a>
		</div>
	</div>
</body>
</html>