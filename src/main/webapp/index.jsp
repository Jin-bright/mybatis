<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>Hello maven</title>
	<link rel="icon" href="data:;base64,iVBORw0KGgo=">
	<script src="${pageContext.request.contextPath}/js/jquery-3.6.1.js"></script>
</head>
<body>
	<h1>Hello maven $ mybatis </h1>
	<h2>Hello World!</h2>
	<button id="btn"> ajax 테스트 </button>
	
	<script>
	// 비동기만들어보기 
	document.querySelector("#btn").addEventListener('click', () => {
		$.ajax({
			url : '${pageContext.request.contextPath}/gson/test',
			success(data){
				console.log(data);
			},
			error : console.log
		});
	});
	</script><br /><br />	<br />
	<hr />
	<button onclick="location.href='${pageContext.request.contextPath}/student/selectList.do'">/student/selectList.do</button>
	<button onclick="location.href='${pageContext.request.contextPath}/student/redirect.do'">/student/redirect.do</button>
	<br /><br /><hr />
	
	<h2> mybatis </h2>
	<h3> - student </h3>
	<ul>
		<li><a href="${pageContext.request.contextPath}/student/studentEnroll.do">학생등록</a></li>
		<li><a href="${pageContext.request.contextPath}/student/selectOne.do">한건 조회</a></li>
		<li><a href="${pageContext.request.contextPath}/student/selectList.do"> 여러 건 조회(0210)</a></li>		
	</ul> <br /><br />
	
	<h3>-emp</h3>
	<ul>
		<li><a href="${pageContext.request.contextPath}/emp/search1.do"> 동적쿼리</a></li>
		<li><a href="${pageContext.request.contextPath}/emp/search2.do"> if </a></li>
	</ul>
	
</body>
</html>
