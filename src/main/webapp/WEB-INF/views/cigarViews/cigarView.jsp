<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="com.project.cigar.to.CigarTO" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>
<%

	JSONObject result = (JSONObject)request.getAttribute("cigarViewObj");
	out.println(result);
%>
