<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
JSONObject reviewCmtDeleteObj = (JSONObject)request.getAttribute("reviewCmtDeleteObj");
Object review_cmt_seq =  reviewCmtDeleteObj.get("review_cmt_seq");
Object review_pseq =  reviewCmtDeleteObj.get("review_pseq");
Object review_cmt_writer_seq =  reviewCmtDeleteObj.get("review_cmt_writer_seq");
Object review_grp =  reviewCmtDeleteObj.get("review_grp");
Object review_grps =  reviewCmtDeleteObj.get("review_grps");
Object review_grpl =  reviewCmtDeleteObj.get("review_grpl");
Object review_cmt_writer =  reviewCmtDeleteObj.get("review_cmt_writer");
Object review_cmt_content =  reviewCmtDeleteObj.get("review_cmt_content");
Object review_cmt_reg_date =  reviewCmtDeleteObj.get("review_cmt_reg_date");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/review/cmt_delete_ok.do" method="get">
		글번호 <input type="text" name="review_cmt_seq" value="<%=review_cmt_seq %>" readonly /> <br/>
		모글번호 <input type="text" name="review_pseq" value="<%=review_pseq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="review_cmt_writer_seq" value="<%=review_cmt_writer_seq %>" readonly/> <br/>
		모글그룹번호 <input type="text" name="review_grp" value="<%=review_grp %>" readonly /> <br/>
		자글순서 <input type="text" name="review_grps" value="<%=review_grps %>" readonly /> <br/>
		들여쓰기칸수 <input type="text" name="review_grpl" value="<%=review_grpl %>" readonly /> <br/>
		글쓴이 <input type="text" name="review_cmt_writer" value="<%=review_cmt_writer %>" readonly /> <br/>
		글내용 <input type="text" name="review_cmt_content" value="<%=review_cmt_writer %>"/> <br/>
		작성날짜 <input type="text" name="review_cmt_reg_date" value="<%=review_cmt_reg_date %>" readonly/> <br/>
		<input type="submit" value="댓글쓰기"/>
	</form>
</body>
</html>