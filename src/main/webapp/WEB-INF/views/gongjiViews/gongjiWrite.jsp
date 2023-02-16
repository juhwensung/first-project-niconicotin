<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/gongji/write_ok.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="gongji_writer_seq" value="1"> <br/>
		공지 제목 <input type="text" name="gongji_subject"> <br/>
		<input type="hidden" name="gongji_writer" value="이름"> <br/>
		공지 내용 <input type="text" name="gongji_content"> <br/>
		파일 <input type="file" name="upload">
		공개 <input type="radio" value="public" name="gongji_public" checked="checked"> 
		비공개 <input type="radio" value="private" name="gongji_public"><br/>
		<input type="submit" value="전송">
	</form>
	
	<form action="/gongji/modify.do" method="get">
		<input type="hidden" name="gongji_seq" value="1">
		<input type="submit" value="수정">
	</form>
	
	<form action="/gongji/delete.do" method="get">
		<input type="hidden" name="gongji_seq" value="1">
		<input type="submit" value="삭제">
	</form>
</body>
</html>