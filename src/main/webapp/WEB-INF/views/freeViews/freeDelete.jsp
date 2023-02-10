<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
	JSONObject freeDeleteObj = (JSONObject)request.getAttribute("freeDeleteObj");
	Object free_seq = freeDeleteObj.get("free_seq");
	
	Object free_writer_seq = session.getAttribute("member_seq");
	Object free_suject = freeDeleteObj.get("free_subject");
	Object free_writer = freeDeleteObj.get("free_writer");
	Object free_reg_date = freeDeleteObj.get("free_reg_date");
	Object free_content = freeDeleteObj.get("free_content");
	Object free_hit = freeDeleteObj.get("free_hit");
	Object free_cmt_count = freeDeleteObj.get("free_cmt_count");
	Object free_file_name = freeDeleteObj.get("free_file_name");
	Object free_file_size = freeDeleteObj.get("free_file_size");
	Object free_smoke_years = freeDeleteObj.get("free_smoke_years");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/free/delete_ok.do" method="post">
		글 번호 <input type="text" name="free_seq" value="<%=free_seq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="free_writer_seq" value="<%=free_writer_seq %>" readonly /> <br/>
		자유 제목 <input type="text" name="free_subject" value="<%=free_suject %>" /> <br/>
		글쓴이 <input type="text" name="free_writer" value="<%=free_writer %>" readonly /> <br/>
		글쓴날 <input type="text" name="free_reg_date" value="<%=free_reg_date %>" readonly /> <br/>
		리뷰 내용 <input type="text" name="free_content" value="<%=free_content %>" /> <br/>
		조회수 <input type="text" name="free_hit" value="<%=free_hit %>" readonly /> <br/>
		댓글 수 <input type="text" name="free_cmt_count" value="<%=free_cmt_count %>" readonly /> <br/>
		사진이름 <input type="text" name="free_file_name" value="<%=free_file_name %>" /> <br/>
		사진크기 <input type="text" name="free_file_size" value="<%=free_file_size %>" /> <br/>
		흡연 연차 <input type="text" name="free_smoke_years" value="<%=free_smoke_years %>" readonly /> <br/>
		<input type="submit" value="전송">
	</form>
</body>
</html>