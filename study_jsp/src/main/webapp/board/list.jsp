<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<style type="text/css">
	#section {
		width: 800px;
	}
</style>
</head>
<body>
	<div id="container">
		<div id="section" class="border-gray text-center">
			<table class="table-gray">
				<thead>
					<tr>
						<th>번호</th>					
						<th>제목</th>					
						<th>작성자</th>					
						<th>작성일</th>					
						<th>조회수</th>					
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty listBvo }">
						<tr>
							<td colspan="5">게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:if test="${listBvo ne null }">
						<c:forEach var="bvo" items="${listBvo }">
							<tr onclick="location.href='/brd/detail?bno=${bvo.bno}'" style="cursor: pointer;">
								<td>${bvo.bno }</td>
								<td>${bvo.title }</td>
								<td>${bvo.writer }</td>
								<td>${bvo.regdate }</td>
								<td>${bvo.readcount }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			
			<a href="/brd/write_s1"><input type="button" class="border-gray button-blue" value="글작성"></a>
			<a href="/index.jsp">메인으로</a>
		</div>
	</div>
</body>
</html>