<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
JSONObject freeCmtModifyObj = (JSONObject)request.getAttribute("freeCmtModifyObj");
Object free_cmt_seq =  freeCmtModifyObj.get("free_cmt_seq");
Object free_pseq =  freeCmtModifyObj.get("free_pseq");
Object free_cmt_writer_seq =  freeCmtModifyObj.get("free_cmt_writer_seq");
Object free_grp =  freeCmtModifyObj.get("free_grp");
Object free_grps =  freeCmtModifyObj.get("free_grps");
Object free_grpl =  freeCmtModifyObj.get("free_grpl");
Object free_cmt_writer =  freeCmtModifyObj.get("free_cmt_writer");
Object free_cmt_content =  freeCmtModifyObj.get("free_cmt_content");
Object free_cmt_reg_date =  freeCmtModifyObj.get("free_cmt_reg_date");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/free/cmt_modify_ok.do" method="get">
		글번호 <input type="text" name="free_cmt_seq" value="<%=free_cmt_seq %>" readonly /> <br/>
		모글번호 <input type="text" name="free_pseq" value="<%=free_pseq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="free_cmt_writer_seq" value="<%=free_cmt_writer_seq %>" readonly/> <br/>
		모글그룹번호 <input type="text" name="free_grp" value="<%=free_grp %>" readonly /> <br/>
		자글순서 <input type="text" name="free_grps" value="<%=free_grps %>" readonly /> <br/>
		들여쓰기칸수 <input type="text" name="free_grpl" value="<%=free_grpl %>" readonly /> <br/>
		글쓴이 <input type="text" name="free_cmt_writer" value="<%=free_cmt_writer %>" readonly /> <br/>
		글내용 <input type="text" name="free_cmt_content" value="<%=free_cmt_writer %>"/> <br/>
		작성날짜 <input type="text" name="free_cmt_reg_date" value="<%=free_cmt_reg_date %>" readonly/> <br/>
		<input type="submit" value="댓글쓰기"/>
	</form>
</body>
</html>