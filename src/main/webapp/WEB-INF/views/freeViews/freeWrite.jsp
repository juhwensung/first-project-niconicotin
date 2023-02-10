<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   Object member_seq = session.getAttribute( "member_seq" ); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/free/write_ok.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="free_seq" value="2"> <br/>
		제목 <input type="text" name="free_subject"> <br/>
		내용 <input type="text" name="free_content"> <br/>
		파일 <input type="file" name="upload">
		공개 <input type="radio" name="free_public" value="public" checked="checked">
		비공개 <input type="radio" name="free_public" value="private">
		<input type="submit" value="전송">
		<input type='button' onclick='location.href="/login.do/"'>테스트 내용
	</form>
	
	<form action="/free/modify.do" method="get">
		<input type="hidden" name="free_seq" value="2">
		<input type="submit" value="수정">
	</form>
	
	<form action="/free/delete.do" method="get">
		<input type="hidden" name="free_seq" value="2">
		<input type="submit" value="삭제">
	</form>
</body>
</html>