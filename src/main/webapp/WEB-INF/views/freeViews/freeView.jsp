<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject" %>
	<%
	JSONObject result = (JSONObject)request.getAttribute("freeViewObj");
	out.println(request.getAttribute("freeViewObj"));
	//out.println(result);
	%>