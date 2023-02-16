<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
	JSONObject requestModifyObj = (JSONObject)request.getAttribute("requestModifyObj");
	Object request_seq = requestModifyObj.get("request_seq");
	Object request_writer_seq = requestModifyObj.get("request_writer_seq");
	Object request_suject = requestModifyObj.get("request_suject");
	Object request_writer = requestModifyObj.get("request_writer");
	Object request_reg_date = requestModifyObj.get("request_reg_date");
	Object request_content = requestModifyObj.get("request_content");
	Object request_hit = requestModifyObj.get("request_hit");
	Object request_cmt_count = requestModifyObj.get("request_cmt_count");
	Object request_cigar_brand = requestModifyObj.get("request_cigar_brand");
	Object request_cigar_name = requestModifyObj.get("request_cigar_name");
	Object request_tar = requestModifyObj.get("request_tar");
	Object request_nicotine = requestModifyObj.get("request_nicotine");
	Object request_file_name = requestModifyObj.get("request_file_name");
	Object request_file_size = requestModifyObj.get("request_file_size");
	Object request_smoke_years = requestModifyObj.get("request_smoke_years");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/requestCigar/modify_ok.do" method="post" enctype="multipart/form-data">
		글 번호 <input type="text" name="request_seq" value="<%=request_seq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="request_writer_seq" value="<%=request_writer_seq %>" readonly /> <br/>
		리뷰 제목 <input type="text" name="request_subject" value="<%=request_suject %>" /> <br/>
		글쓴이 <input type="text" name="request_writer" value="<%=request_writer %>" readonly /> <br/>
		글쓴날 <input type="text" name="request_reg_date" value="<%=request_reg_date %>" readonly /> <br/>
		리뷰 내용 <input type="text" name="request_content" value="<%=request_content %>" /> <br/>
		조회수 <input type="text" name="request_hit" value="<%=request_hit %>" readonly /> <br/>
		댓글 수 <input type="text" name="request_cmt_count" value="<%=request_cmt_count %>" readonly /> <br/>
		브랜드 <input type="text" name="request_cigar_brand" value="<%=request_cigar_brand %>" /> <br/>
		담배명 <input type="text" name="request_cigar_name" value="<%=request_cigar_name %>" /> <br/>
		타르 <input type="text" name="request_tar" value="<%=request_tar %>" /> <br/>
		니코틴 <input type="text" name="request_nicotine" value="<%=request_nicotine %>" /> <br/>
		파일 <input type="file" name="upload">
		사진이름 <input type="text" name="request_file_name" value="<%=request_file_name %>" readonly="readonly" /> <br/>
		사진크기 <input type="text" name="request_file_size" value="<%=request_file_size %>" readonly="readonly"/> <br/>
		흡연 연차 <input type="text" name="request_smoke_years" value="<%=request_smoke_years %>" readonly /> <br/>
		공개 <input type="radio" name="free_public" value="public" checked="checked">
		비공개 <input type="radio" name="free_public" value="private">
		<input type="submit" value="전송">
	</form>
</body>
</html>