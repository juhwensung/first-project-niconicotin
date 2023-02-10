<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
	JSONObject freeModifyObj = (JSONObject)request.getAttribute("freeModifyObj");
	Object free_seq = freeModifyObj.get("free_seq");
	Object free_writer_seq = session.getAttribute("member_seq");
	Object free_suject = freeModifyObj.get("free_subject");
	Object free_writer = freeModifyObj.get("free_writer");
	Object free_reg_date = freeModifyObj.get("free_reg_date");
	Object free_content = freeModifyObj.get("free_content");
	Object free_hit = freeModifyObj.get("free_hit");
	Object free_cmt_count = freeModifyObj.get("free_cmt_count");
	Object free_file_name = freeModifyObj.get("free_file_name");
	Object free_file_size = freeModifyObj.get("free_file_size");
	Object free_smoke_years = freeModifyObj.get("free_smoke_years");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/free/modify_ok.do" method="post" enctype="multipart/form-data">
		글 번호 <input type="text" name="free_seq" value="<%=free_seq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="free_writer_seq" value="<%=free_writer_seq %>" readonly /> <br/>
		글 제목 <input type="text" name="free_subject" value="<%=free_suject %>" /> <br/>
		글쓴이 <input type="text" name="free_writer" value="<%=free_writer %>" readonly /> <br/>
		글쓴날 <input type="text" name="free_reg_date" value="<%=free_reg_date %>" readonly /> <br/>
		글 내용 <input type="text" name="free_content" value="<%=free_content %>" /> <br/>
		조회수 <input type="text" name="free_hit" value="<%=free_hit %>" readonly /> <br/>
		댓글 수 <input type="text" name="free_cmt_count" value="<%=free_cmt_count %>" readonly /> <br/>
		흡연 연차 <input type="text" name="free_smoke_years" value="<%=free_smoke_years %>" readonly /> <br/>
		파일이름 : <input type="text" name="free_file_name" value=" <%= free_file_name %>" readonly/><br>
		파일 사이즈 :<%= free_file_size %><br>
		파일 <input type="file" name="upload">
		공개 <input type="checkbox" name="free_public" value=true checked="checked">
		비공개 <input type="checkbox" name="free_public" value=flase>
		<input type="submit" value="전송">
	</form>
</body>
</html>