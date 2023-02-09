<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.project.member.to.MemberTO"%>
    <%
    MemberTO to = (MemberTO)request.getAttribute("to");
    String email = "";
	//System.out.println(to.getPhone());
    if(to.getEmail() != "" && to.getEmail() != null){
    	email = to.getEmail();
    } else {
    	email = "입력 정보와 일치하는 계정을 찾을 수 없습니다.";
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%= email %>
<br>
</body>
</html>