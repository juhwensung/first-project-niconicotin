<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%

	//JSONArray result = (JSONArray)request.getAttribute("gongjiLists");
	//out.println(result);
	
	JSONObject result = (JSONObject)request.getAttribute("obj2");
	out.println(result);
%>