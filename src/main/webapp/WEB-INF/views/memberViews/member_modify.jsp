
<%@page import="java.util.Date"%>
<%@page import="com.project.member.to.MemberTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	MemberTO to = (MemberTO)request.getAttribute("to");
    
      	String email = to.getEmail();
    	String name = to.getName();
    	String nickname = to.getNickname();
    	String address = to.getAddress();
    	String phoneNum = to.getPhone();
    	Date smoke_years = to.getSmoke_years();
    	String prefer_cigar = to.getPrefer_cigar();
    	Date sign_date = to.getSign_date();
    	Date birthday = to.getBirthday();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>내 정보</h3>
<form action="member_modify_ok.do" method="post">
아이디: <input type="text" name="email" value="<%= email %>" disabled> <br>
이름: <input type="text" name="name" value="<%= name %>" > <br>
닉네임: <input type="text" name="nickname" value="<%= nickname %>" > <br>
주소: <input type="text" name="address" value="<%= address %>" > <br>
선호 담배 : <input type="text" name="prefer_cigar" value="<%= prefer_cigar %>" > <br>
흡연 시작일: <input type="text" name="smoke_years" value="<%= smoke_years %>"> <br>
폰 번호 : <input type="text" name="phoneNum" value="<%= phoneNum %>"> <br>
가입날짜: <input type="text" name="email" value="<%= sign_date %>" disabled> <br>
생일: <input type="text" name="birthday" value="<%= birthday %>"> <br>
<input type="submit" value="수정 완료">
</form>

</body>
</html>