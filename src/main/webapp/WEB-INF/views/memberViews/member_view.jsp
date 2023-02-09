
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.Date"%>
<%@page import="com.project.member.to.MemberTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	MemberTO to = (MemberTO)request.getAttribute("to");
    
		JSONObject result = (JSONObject)request.getAttribute("memberInfo");
		
		JSONObject array1 = (JSONObject)request.getAttribute("reviewList");
		JSONObject array2 = (JSONObject)request.getAttribute("freeList");
		JSONObject array3 = (JSONObject)request.getAttribute("gongjiList");
		JSONObject array4 = (JSONObject)request.getAttribute("requestList");
		//out.println(result);
		
    	String email = to.getEmail();
    	String name = to.getName();
    	String nickname = to.getNickname();
    	String address = to.getAddress();
    	String phoneNum = to.getPhone();
    	Object smoke_years = request.getAttribute("smoke_years");
    	String prefer_cigar = to.getPrefer_cigar();
    	Date sign_date = to.getSign_date();
    	Date birthday = to.getBirthday();
    	
		//System.out.println("jsp : "+phoneNum);
		
	if( session.getAttribute( "emailss" ) == null){
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
<h3>내 정보</h3>
이메일: <%= email %> <br>
이름: <%= name %> <br>
닉네임: <%= nickname %> <br>
주소: <%= address %> <br>
선호 담배: <%= prefer_cigar %> <br>
흡연 연차: <%= smoke_years %>년 <br>
폰 번호 : <%= phoneNum %> <br>
가입날짜: <%= sign_date %> <br>
생일: <%= birthday %> <br>
<button onclick="location.href='/member_modify.do'">정보 수정</button> <br>
<button onclick="location.href='/member_modify_password.do'">비번 재설정</button> <br>
<button onclick="location.href='/member_delete.do'">회원 탈퇴</button> <br>
<hr>
<%out.println(result); %><br>
<% out.println(array1); %><br>
<% out.println(array2); %><br>
<% out.println(array3); %><br>
<% out.println(array4); %><br>
</body>
</html>
<%
   }
%>