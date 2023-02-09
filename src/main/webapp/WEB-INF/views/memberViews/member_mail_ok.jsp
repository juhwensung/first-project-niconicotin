<%@page import="com.project.member.to.MemberTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% MemberTO to = (MemberTO)request.getAttribute("to"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
try{
	if(to.getEmail() != null){
		out.println("이메일 발송 성공");
	}
} catch(NullPointerException e) {
	out.println("이메일 발송 실패");
}
	%>
</body>
</html>