<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP 로그인 웹 사이트</title>
</head>
<body>
	<%
		session.invalidate();  //session의 어트리뷰트를 제거함으로써 접속 해제 상태임을 저장.
	%>
	<script>
		location.href = 'main.jsp'; //메인으로 돌아감./
	</script>
</body>
</html>