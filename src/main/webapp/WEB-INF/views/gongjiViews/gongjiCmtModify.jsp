<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
JSONObject gongjiCmtModifyObj = (JSONObject)request.getAttribute("gongjiCmtModifyObj");
Object gongji_cmt_seq =  gongjiCmtModifyObj.get("gongji_cmt_seq");
Object gongji_pseq =  gongjiCmtModifyObj.get("gongji_pseq");
Object gongji_cmt_writer_seq =  gongjiCmtModifyObj.get("gongji_cmt_writer_seq");
Object gongji_grp =  gongjiCmtModifyObj.get("gongji_grp");
Object gongji_grps =  gongjiCmtModifyObj.get("gongji_grps");
Object gongji_grpl =  gongjiCmtModifyObj.get("gongji_grpl");
Object gongji_cmt_writer =  gongjiCmtModifyObj.get("gongji_cmt_writer");
Object gongji_cmt_content =  gongjiCmtModifyObj.get("gongji_cmt_content");
Object gongji_cmt_reg_date =  gongjiCmtModifyObj.get("gongji_cmt_reg_date");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/gongji/cmt_modify_ok.do" method="get">
		글번호 <input type="text" name="gongji_cmt_seq" value="<%=gongji_cmt_seq %>" readonly /> <br/>
		모글번호 <input type="text" name="gongji_pseq" value="<%=gongji_pseq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="gongji_cmt_writer_seq" value="<%=gongji_cmt_writer_seq %>" readonly/> <br/>
		모글그룹번호 <input type="text" name="gongji_grp" value="<%=gongji_grp %>" readonly /> <br/>
		자글순서 <input type="text" name="gongji_grps" value="<%=gongji_grps %>" readonly /> <br/>
		들여쓰기칸수 <input type="text" name="gongji_grpl" value="<%=gongji_grpl %>" readonly /> <br/>
		글쓴이 <input type="text" name="gongji_cmt_writer" value="<%=gongji_cmt_writer %>" readonly /> <br/>
		글내용 <input type="text" name="gongji_cmt_content" value="<%=gongji_cmt_writer %>"/> <br/>
		작성날짜 <input type="text" name="gongji_cmt_reg_date" value="<%=gongji_cmt_reg_date %>" readonly/> <br/>
		<input type="submit" value="댓글쓰기"/>
	</form>
</body>
</html>