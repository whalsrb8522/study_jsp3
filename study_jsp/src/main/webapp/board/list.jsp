<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<style type="text/css">
	#section {
		width: 800px;
	}
	#section input {
		margin-top: 10px
	}
	#searchText {
		width:70% !important;
	}
</style>
</head>
<body>
	<div id="container">
		<div id="section" class="border-gray text-center">
			<form action="/brd/list">
				<div>
					<c:set value="${pgh.pgvo.type }" var="typed"></c:set>
					<select name="type">
						<option value="t" ${typed eq 't' ? 'selected':''}>title</option>
						<option value="c" ${typed eq 'c' ? 'selected':''}>content</option>
						<option value="w" ${typed eq 'w' ? 'selected':''}>writer</option>
						<option value="tc" ${typed eq 'tc' ? 'selected':''}>title + content</option>
						<option value="tw" ${typed eq 'tw' ? 'selected':''}>title + writer</option>
						<option value="cw" ${typed eq 'cw' ? 'selected':''}>content + writer</option> 
					</select>
					<input id="searchText" type="text" name="keyword" placeholder="Search...">
					<input type="hidden" name=pageNo value="${pgh.pgvo.pageNo}">
					<input type="hidden" name=qty value="${pgh.pgvo.qty}">
		 			<button type="submit" class="btn btn-sm btn-primary position-relative">
		 				SEARCH
		 					<c:if test="${pgh.pgvo.keyword != null && pgh.pgvo.keyword != '' }">
				 				<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
				 					${pgh.totalCount }
				    				<span class="visually-hidden">unread messages</span>
				  				</span>
			  				</c:if>
		 			</button>
				</div>
			</form>	
		
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
								<td>
									<c:if test="${bvo.image ne null && bvo.image ne '' }">
										<img alt="thumbnail" src="/_fileUpload/th_${bvo.image }">
									</c:if>
									${bvo.title }
								</td>
								<td>${bvo.writer }</td>
								<td>${bvo.regdate }</td>
								<td>${bvo.readcount }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			
			<c:if test="${pgh.prev }">
				<a href="/brd/list?pageNo=${pgh.startPage - 1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"> ◁ </a>
			</c:if>
			<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
				<a href="/brd/list?pageNo=${i }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">${i } | </a>
			</c:forEach>
			<c:if test="${pgh.next }">
				<a href="/brd/list?pageNo=${pgh.endPage + 1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"> ▷ </a>
			</c:if>
			
			<a href="/brd/write_s1"><input type="button" class="border-gray button-blue" value="글작성"></a>
			<a href="/index.jsp">메인으로</a>
		</div>
	</div>
</body>
</html>