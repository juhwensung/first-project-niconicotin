<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
JSONObject requestCmtModifyObj = (JSONObject)request.getAttribute("requestCmtModifyObj");
Object request_cmt_seq =  requestCmtModifyObj.get("request_cmt_seq");
Object request_pseq =  requestCmtModifyObj.get("request_pseq");
Object request_cmt_writer_seq =  requestCmtModifyObj.get("request_cmt_writer_seq");
Object request_grp =  requestCmtModifyObj.get("request_grp");
Object request_grps =  requestCmtModifyObj.get("request_grps");
Object request_grpl =  requestCmtModifyObj.get("request_grpl");
Object request_cmt_writer =  requestCmtModifyObj.get("request_cmt_writer");
Object request_cmt_content =  requestCmtModifyObj.get("request_cmt_content");
Object request_cmt_reg_date =  requestCmtModifyObj.get("request_cmt_reg_date");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/requestCigar/cmt_modify_ok.do" method="get">
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