<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
JSONObject requestCmtDeleteObj = (JSONObject)request.getAttribute("requestCmtDeleteObj");
Object request_cmt_seq =  requestCmtDeleteObj.get("request_cmt_seq");
Object request_pseq =  requestCmtDeleteObj.get("request_pseq");
Object request_cmt_writer_seq =  requestCmtDeleteObj.get("request_cmt_writer_seq");
Object request_grp =  requestCmtDeleteObj.get("request_grp");
Object request_grps =  requestCmtDeleteObj.get("request_grps");
Object request_grpl =  requestCmtDeleteObj.get("request_grpl");
Object request_cmt_writer =  requestCmtDeleteObj.get("request_cmt_writer");
Object request_cmt_content =  requestCmtDeleteObj.get("request_cmt_content");
Object request_cmt_reg_date =  requestCmtDeleteObj.get("request_cmt_reg_date");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/requestCigar/cmt_delete_ok.do" method="get">
		글번호 <input type="text" name="request_cmt_seq" value="<%=request_cmt_seq %>" readonly /> <br/>
		모글번호 <input type="text" name="request_pseq" value="<%=request_pseq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="request_cmt_writer_seq" value="<%=request_cmt_writer_seq %>" readonly/> <br/>
		모글그룹번호 <input type="text" name="request_grp" value="<%=request_grp %>" readonly /> <br/>
		자글순서 <input type="text" name="request_grps" value="<%=request_grps %>" readonly /> <br/>
		들여쓰기칸수 <input type="text" name="request_grpl" value="<%=request_grpl %>" readonly /> <br/>
		글쓴이 <input type="text" name="request_cmt_writer" value="<%=request_cmt_writer %>" readonly /> <br/>
		글내용 <input type="text" name="request_cmt_content" value="<%=request_cmt_writer %>"/> <br/>
		작성날짜 <input type="text" name="request_cmt_reg_date" value="<%=request_cmt_reg_date %>" readonly/> <br/>
		<input type="submit" value="댓글쓰기"/>
	</form>
</body>
</html>