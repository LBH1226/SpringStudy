<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 수정을 최소화 하기위한 변수정의 -->
<!-- == /controller -->
<c:set var="cpath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- 자동완성 기능을 위해 가져온 jquery-ui library -->
<script
  src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>	
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.1/themes/smoothness/jquery-ui.css" >
	
	
	
</head>
<body>
	<div class="jumbotron">
		<h1>빅데이너 23차 게시판</h1>
		<p>bootstrap 사용하여 간단하게 게시판을 만들어보자</p>
	</div>

	<div class="container">
		<div class="card">
			<div class="card-header">게시판 연습</div>
			<div class="card-body">


				<form id="searchForm" onsubmit="return false;" class="form-inline">
					<div align="right" class="form-group">
						<select name="type" class="form-control btn-warning" id="sel1">
							<option class="btn-light">작성자</option>
							<option class="btn-light">제목</option>
						</select>
					</div>
					<input id="auto_complete" name="text" type="text" class="form-control"> <input
						id="searchBtn" type="submit" class="btn btn-warning btn-sm"
						value="검색">
				</form>


				<br> <input type="text" class="form-control">
				<table id="myTable" class="table table-hover">
					<tr id = "myTr">
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
					</tr>

					<!-- JSTL/EL 사용하여 request 영역안에 저장돼있는 게시글 정보 전부 화면에 출력 -->
					<c:forEach var="list" items="${list}">
						<tr>
							<td>${list.idx}</td>
							<!--  1. QueryString으로 데이터 보내기 '${cpath}/boardContent?value=${list.idx}'
								2. 경로상에 그냥 바로 데이터 포함해서 보내기 -->
							<td><a href='${cpath}/boardContent/${list.idx}'>${list.title}</a></td>
							<td>${list.writer}</td>
							<td>${list.indate}</td>
						</tr>
					</c:forEach>
				</table>
				<!-- 경로는 /controller/register거나 register로  -->
				<button onclick="location.href='${cpath}/register'"
					class="btn btn-primary">글쓰기</button>
			</div>
		</div>
	</div>

	<!-- 자바스크립트와 자바 세션 영역은 아예 다른 영역이다
	js에서도 cpath를 쓸수있게 해주기 -->
	<script type="text/javascript">
		var cpath = "${cpath}";
	</script>
	<script src="resources/JS/myfirstjs.js"></script>
	

</body>
</html>