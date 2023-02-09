
<%@page import="java.util.Date"%>
<%@page import="com.project.member.to.MemberTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>내 정보</h3>
<form action="mail_ok.do" method="post">
이메일 : <input type="text" name="email">
<input type="submit" value="비밀번호 찾기">
</form>

</body>
</html>