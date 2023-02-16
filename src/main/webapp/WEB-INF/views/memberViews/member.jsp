<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="member_id_check.do" method="post">
Email <input type="text" name="email">
<input type="submit" value="중복 확인"> <br>
</form>
<form action="member_ok.do" method="post"><br>
Password <input type="password" name="password"> <br>
이름 <input type="text" name="name"> <br>
주소 <input type="text" name="address"> <br>
연락처 <input type="text" name="phoneNum1"> - <input type="text" name="phoneNum2"> - <input type="text" name="phoneNum3"> <br>
닉네임 <input type="text" name="nickname"> <br>
담배 연차 <input type="text" name="smoke_years"> <br>
선호 담배<input type="text" name="prefer_cigar"> <br>
주민등록 <input type="text" name="jumin_front"> - <input type="password" name="jumin_back"><br>
생년월일 <input type="text" name="birthday"> <br>
<input type="submit" value="확인"> <br>
</form>
</body>
</html>