<%@page import="com.project.member.to.MemberTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    if(session.getAttribute( "emailss" ) == null){
    	out.println("<script type='text/javascript'>");
    	out.println("alert('로그인해야 됩니다.');");
    	out.println("location.href='login.do'");
    	out.println("</script>");
    } else {
       String user = (String)session.getAttribute("emailss");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button onclick="location.href='member_view.do'">내 정보 조회</button>
<%= user %><br>
<a href='logout_ok.do'>로그아웃</a>
</body>
</html>
<%
    }
%>