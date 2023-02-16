
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
<form action="member_modify_password_ok.do" method="post">
현재 비밀 번호 <input type="password" name="oldPassword"> <br>
변경할 비밀 번호<input type="password" name="newPassword"> <br>
<input type="submit" value="수정 완료">
</form>

</body>
</html>