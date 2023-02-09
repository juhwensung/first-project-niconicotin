<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// System.out.println(session.getAttribute("emailss"));
	if(session.getAttribute("emailss") != null){
    	out.println("<script type='text/javascript'>");
    	out.println("alert('로그인 성공.');");
    	out.println("location.href='login_complete.do'");
    	out.println("</script>");
	} else {
    	out.println("<script type='text/javascript'>");
    	out.println("alert('로그인 실패');");
    	out.println("location.href='login.do'");
    	out.println("</script>");
	}
%>