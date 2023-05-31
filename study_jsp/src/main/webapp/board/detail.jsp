<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<style type="text/css">
	#section {
		width: 800px;
	}
	#commentWriter {
		width: 15% !important;
	}
	#commentContent {
		width: 68% !important;
	}
	#commentBtn {
		width: 15% !important;	
	}
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
					<td>
						<c:if test="${bvo.image ne '' && bvo.image ne null }">
							<img alt="" src="/_fileUpload/${bvo.image }">
						</c:if>
						${bvo.content }
					</td>
				</tr>
			</table>
			
			<a href="/brd/modify_s1?bno=${bvo.bno }"><input type="button" class="border-gray button-blue" value="글수정"></a>
			<a href="javascript:location.href = document.referrer">뒤로가기</a>
			
			<div style="margin-top: 10px;">
				<input id="commentWriter" class="border-gray bg-gray input-box" type="text" name="writer" placeholder="작성자" value="${ses.id }" readonly="readonly">
				<input id="commentContent" class="border-gray input-box" type="text" name="title" placeholder="댓글을 입력해주세요">
				<input id="commentBtn" class="border-gray button-blue" type="submit" value="작성완료">
			</div>
			
			
			
			<div class="accordion" id="accordionExample">
				<div class="accordion-item">
					<h2 class="accordion-header" id="headingOne">
						<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
							cno, writer
						</button>
					</h2>
					<div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" >
						<div class="accordion-body">
							content, regdate					
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
    <script type="text/javascript">
    	const bnoVal = `<c:out value="${bvo.bno }" />`;
    </script>
    <script type="text/javascript" src="/resources/board_detail.js"></script>
    <script type="text/javascript">
    	printCommentLlist(bnoVal);
    </script>
</body>
</html>