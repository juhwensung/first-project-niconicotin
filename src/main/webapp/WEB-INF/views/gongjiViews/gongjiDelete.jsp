<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
	JSONObject gongjiDeleteObj = (JSONObject)request.getAttribute("gongjiDeleteObj");
	Object gongji_seq = gongjiDeleteObj.get("gongji_seq");
	Object gongji_writer_seq = gongjiDeleteObj.get("gongji_writer_seq");
	Object gongji_suject = gongjiDeleteObj.get("gongji_suject");
	Object gongji_writer = gongjiDeleteObj.get("gongji_writer");
	Object gongji_reg_date = gongjiDeleteObj.get("gongji_reg_date");
	Object gongji_content = gongjiDeleteObj.get("gongji_content");
	Object gongji_hit = gongjiDeleteObj.get("gongji_hit");
	Object gongji_cmt_count = gongjiDeleteObj.get("gongji_cmt_count");
	Object gongji_file_name = gongjiDeleteObj.get("gongji_file_name");
	Object gongji_file_size = gongjiDeleteObj.get("gongji_file_size");
	Object gongji_smoke_years = gongjiDeleteObj.get("gongji_smoke_years");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/gongji/delete_ok.do" method="get">
		글 번호 <input type="text" name="gongji_seq" value="<%=gongji_seq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="gongji_writer_seq" value="<%=gongji_writer_seq %>" readonly /> <br/>
		리뷰 제목 <input type="text" name="gongji_subject" value="<%=gongji_suject %>" /> <br/>
		글쓴이 <input type="text" name="gongji_writer" value="<%=gongji_writer %>" readonly /> <br/>
		글쓴날 <input type="text" name="gongji_reg_date" value="<%=gongji_reg_date %>" readonly /> <br/>
		리뷰 내용 <input type="text" name="gongji_content" value="<%=gongji_content %>" /> <br/>
		조회수 <input type="text" name="gongji_hit" value="<%=gongji_hit %>" readonly /> <br/>
		댓글 수 <input type="text" name="gongji_cmt_count" value="<%=gongji_cmt_count %>" readonly /> <br/>
		사진이름 <input type="text" name="gongji_file_name" value="<%=gongji_file_name %>" /> <br/>
		사진크기 <input type="text" name="gongji_file_size" value="<%=gongji_file_size %>" /> <br/>
		흡연 연차 <input type="text" name="gongji_smoke_years" value="<%=gongji_smoke_years %>" readonly /> <br/>
		<input type="submit" value="전송">
	</form>
</body>
</html>