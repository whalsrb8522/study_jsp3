<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<style type="text/css">
	
</style>
</head>
<body>
	<div id="container">
		<div id="section" class="border-gray text-center">
			<table class="table-gray-vertical">
				<tr>
					<th>제목</th>
					<td>${bvo.title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${bvo.writer }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${bvo.regdate }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${bvo.content }</td>
				</tr>
			</table>
			
			<a href="/brd/modify_s1?bno=${bvo.bno }"><input type="button" class="border-gray button-blue" value="글수정"></a>
			<a href="javascript:history.back()">뒤로가기</a>
		</div>
	</div>
</body>
</html>