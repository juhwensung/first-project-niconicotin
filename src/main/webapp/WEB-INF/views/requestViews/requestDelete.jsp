<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
	JSONObject requestDeleteObj = (JSONObject)request.getAttribute("requestDeleteObj");
Object request_seq = requestDeleteObj.get("request_seq");
Object request_writer_seq = requestDeleteObj.get("request_writer_seq");
Object request_suject = requestDeleteObj.get("request_suject");
Object request_writer = requestDeleteObj.get("request_writer");
Object request_reg_date = requestDeleteObj.get("request_reg_date");
Object request_content = requestDeleteObj.get("request_content");
Object request_hit = requestDeleteObj.get("request_hit");
Object request_cmt_count = requestDeleteObj.get("request_cmt_count");
Object request_cigar_brand = requestDeleteObj.get("request_cigar_brand");
Object request_cigar_name = requestDeleteObj.get("request_cigar_name");
Object request_tar = requestDeleteObj.get("request_tar");
Object request_nicotine = requestDeleteObj.get("request_nicotine");
Object request_file_name = requestDeleteObj.get("request_file_name");
Object request_file_size = requestDeleteObj.get("request_file_size");
Object request_smoke_years = requestDeleteObj.get("request_smoke_years");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/requestCigar/delete_ok.do" method="get">
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
		사진이름 <input type="text" name="request_file_name" value="<%=request_file_name %>" /> <br/>
		사진크기 <input type="text" name="request_file_size" value="<%=request_file_size %>" /> <br/>
		흡연 연차 <input type="text" name="request_smoke_years" value="<%=request_smoke_years %>" readonly /> <br/>
		<input type="submit" value="전송">
	</form>
</body>
</html>