<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%

	JSONObject result = (JSONObject)request.getAttribute("gongjiViewObj");
	out.println(result);
	
	JSONArray result2 = (JSONArray)request.getAttribute("gongjiCommentLists");
	out.println();
	out.println();
	out.println(result2);
%>
