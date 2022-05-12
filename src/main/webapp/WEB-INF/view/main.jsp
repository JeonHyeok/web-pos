<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POS-System</title>
<script>
	
</script>
</head>
<body>
	<h1>POS Machine System</h1>

	<div id="Time">실시간 보여주기 부분...</div>

	<form action="login" method="POST" name="login">
		<div class="login">
			아이디 : <input type="text" value="" required /><br>
			비밀번호 : <input type="password" value="" required /><br>
			<input type="submit" value="로그인" name="login"/>
		</div>
	</form>
	<form action="membership" method="POST" name="membership">
		<input type="submit" value="회원가입" name="membership" />
	</form>
</body>
</html>