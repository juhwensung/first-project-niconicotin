<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>

<%
	JSONObject reviewCmtWriteObj = (JSONObject)request.getAttribute("reviewCmtWriteObj");
	Object review_cmt_seq =  reviewCmtWriteObj.get("review_cmt_seq");
	Object review_pseq =  reviewCmtWriteObj.get("review_pseq");
	Object review_grp =  reviewCmtWriteObj.get("review_grp");
	Object review_grps =  reviewCmtWriteObj.get("review_grps");
	Object review_grpl =  reviewCmtWriteObj.get("review_grpl");
	Object review_cmt_writer =  reviewCmtWriteObj.get("review_cmt_writer");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/review/cmt_write_ok.do" method="get">
		글번호 <input type="text" name="review_cmt_seq" value="<%=review_cmt_seq %>" readonly /> <br/>
		모글번호 <input type="text" name="review_pseq" value="<%=review_pseq %>" readonly /> <br/>
		모글그룹번호 <input type="text" name="review_grp" value="<%=review_grp %>" readonly /> <br/>
		자글순서 <input type="text" name="review_grps" value="<%=review_grps %>" readonly /> <br/>
		들여쓰기칸수 <input type="text" name="review_grpl" value="<%=review_grpl %>" readonly /> <br/>
		글쓴이 <input type="text" value="<%=review_cmt_writer %>" readonly /> <br/>
		글내용 <input type="text" name="review_cmt_content" /> <br/>
		<input type="submit" value="댓글쓰기"/>
	</form>
</body>
</html>