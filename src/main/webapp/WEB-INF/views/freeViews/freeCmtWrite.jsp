<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>

<%
	JSONObject freeCmtWriteObj = (JSONObject)request.getAttribute("freeCmtWriteObj");
	Object free_cmt_seq =  freeCmtWriteObj.get("free_cmt_seq");
	Object free_pseq =  freeCmtWriteObj.get("free_pseq");
	Object free_grp =  freeCmtWriteObj.get("free_grp");
	Object free_grps =  freeCmtWriteObj.get("free_grps");
	Object free_grpl =  freeCmtWriteObj.get("free_grpl");
	Object free_cmt_writer =  freeCmtWriteObj.get("free_cmt_writer");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/free/cmt_write_ok.do" method="get">
		글번호 <input type="text" name="free_cmt_seq" value="<%=free_cmt_seq %>" readonly /> <br/>
		모글번호 <input type="text" name="free_pseq" value="<%=free_pseq %>" readonly /> <br/>
		모글그룹번호 <input type="text" name="free_grp" value="<%=free_grp %>" readonly /> <br/>
		자글순서 <input type="text" name="free_grps" value="<%=free_grps %>" readonly /> <br/>
		들여쓰기칸수 <input type="text" name="free_grpl" value="<%=free_grpl %>" readonly /> <br/>
		글쓴이 <input type="text" value="<%=free_cmt_writer %>" readonly /> <br/>
		글내용 <input type="text" name="free_cmt_content" /> <br/>
		<input type="submit" value="댓글쓰기"/>
	</form>
</body>
</html>