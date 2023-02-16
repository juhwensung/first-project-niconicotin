<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/cigar/write_ok.do" method="post" enctype="multipart/form-data">
		글쓴이 번호 <input type="text" name="cigar_writer_seq" value="1"> <br/>
		브랜드 <input type="text" name="cigar_brand"> <br/>
		담배이름 <input type="text" name="cigar_name"> <br/>
		타르량 <input type="text" name="cigar_tar"> <br/>
		니코틴양 <input type="text" name="cigar_nicotine"> <br/>
		해시 태그 <input type="text" name="cigar_hash_tag"> <br/>
		설명 <input type="text" name="cigar_content"> <br/>
		평점 <input type="text" name="cigar_total_grade" value="4.2"> <br/>
		파일 <input type="file" name="upload">
		<input type="submit" value="전송">
	</form>
	
	<form action="/cigar/modify.do" method="get">
		<input type="hidden" name="cigar_seq" value="1">
		<input type="submit" value="수정">
	</form>
	
	<form action="/cigar/delete.do" method="get">
		<input type="hidden" name="cigar_seq" value="1">
		<input type="submit" value="삭제">
	</form>
</body>
</html>