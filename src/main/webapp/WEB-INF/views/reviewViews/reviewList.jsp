<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="org.json.simple.JSONArray" %>
<%

	JSONArray result = (JSONArray)request.getAttribute("reviewLists");
	out.println(result);
%>