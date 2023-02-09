<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    System.out.println("session : " + session.getAttribute("emailss"));
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<form action="login_ok.do" method="post">
Email <input type="text" name="email"> <br>
Password <input type="password" name="password"><br>
<input type="submit" value="로그인">
<br>
</form>
<button type="button" onclick="location.href = 'http://localhost:8080/member.do' ">회원 가입</button><br>
<button type="button" onclick="location.href = 'http://localhost:8080/id_search.do' ">id 찾기</button>
<button type="button" onclick="location.href = 'http://localhost:8080/mail.do' ">password 찾기</button>
</body>
</html>