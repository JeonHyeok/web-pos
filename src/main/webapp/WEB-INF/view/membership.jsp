<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POS-System membership</title>
</head>
<body>
	<h1>POS Machine System</h1>

	<div id=nav>시간을 실시간으로 보여주기 부분...</div>

	<form action="/memberprocess" method="POST">
		<div id=member>
			아이디 : <input type="text" value="" required /><br>
			비밀번호 : <input type="text" value="" required /><br>
		</div>
		<input type="submit" value="가입 신청" />
	</form>
</body>
</html>