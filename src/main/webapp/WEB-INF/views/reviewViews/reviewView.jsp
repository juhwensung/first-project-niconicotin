<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%

	JSONObject result = (JSONObject)request.getAttribute("reviewViewObj");
	out.println(result);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
<button onclick="location.href='/review/like.do'">좋아요</button> <br>
<button onclick="location.href='/review/dislike.do'">싫어요</button> 
</body>
</html>