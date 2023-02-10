<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>

<%
	JSONObject requestCmtWriteObj = (JSONObject)request.getAttribute("requestCmtWriteObj");
	Object request_cmt_seq =  requestCmtWriteObj.get("request_cmt_seq");
	Object request_pseq =  requestCmtWriteObj.get("request_pseq");
	Object request_grp =  requestCmtWriteObj.get("request_grp");
	Object request_grps =  requestCmtWriteObj.get("request_grps");
	Object request_grpl =  requestCmtWriteObj.get("request_grpl");
	Object request_cmt_writer =  requestCmtWriteObj.get("request_cmt_writer");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/requestCigar/cmt_write_ok.do" method="get">
		글번호 <input type="text" name="request_cmt_seq" value="<%=request_cmt_seq %>" readonly /> <br/>
		모글번호 <input type="text" name="request_pseq" value="<%=request_pseq %>" readonly /> <br/>
		모글그룹번호 <input type="text" name="request_grp" value="<%=request_grp %>" readonly /> <br/>
		자글순서 <input type="text" name="request_grps" value="<%=request_grps %>" readonly /> <br/>
		들여쓰기칸수 <input type="text" name="request_grpl" value="<%=request_grpl %>" readonly /> <br/>
		글쓴이 <input type="text" value="<%=request_cmt_writer %>" readonly /> <br/>
		글내용 <input type="text" name="request_cmt_content" /> <br/>
		<input type="submit" value="댓글쓰기"/>
	</form>
</body>
</html>