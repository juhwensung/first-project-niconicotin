<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/review/write_ok.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="review_writer_seq" value="1"> <br/>
		<input type="hidden" name="review_cigar_seq" value="2"> <br/>
		리뷰 제목 <input type="text" name="review_subject"> <br/>
		<input type="hidden" name="review_writer" value="이름"> <br/>
		리뷰 내용 <input type="text" name="review_content"> <br/>
		평점 <input type="text" name="review_grade"> <br/>
		파일 <input type="file" name="upload">
		<input type="submit" value="전송">
	</form>
	
	<form action="/review/modify.do" method="get">
		<input type="hidden" name="review_seq" value="1">
		<input type="submit" value="수정">
	</form>
	
	<form action="/review/delete.do" method="get">
		<input type="hidden" name="review_seq" value="1">
		<input type="submit" value="삭제">
	</form>
</body>
</html>