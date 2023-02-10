<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%
	JSONObject freeViewObj = (JSONObject)request.getAttribute("freeViewObj");
	Object free_seq = freeViewObj.get("free_seq");
	Object free_writer_seq = freeViewObj.get("free_writer_seq");
	Object free_suject = freeViewObj.get("free_suject");
	Object free_writer = freeViewObj.get("free_writer");
	Object free_reg_date = freeViewObj.get("free_reg_date");
	Object free_content = freeViewObj.get("free_content");
	Object free_hit = freeViewObj.get("free_hit");
	Object free_cmt_count = freeViewObj.get("free_cmt_count");
	Object free_file_name = freeViewObj.get("free_file_name");
	Object free_file_size = freeViewObj.get("free_file_size");
	Object free_smoke_years = freeViewObj.get("free_smoke_years");
	
	JSONArray freeCommentLists = (JSONArray)request.getAttribute("freeCommentLists");
	StringBuilder sbHtml = new StringBuilder();
	for(int i = 0; i < freeCommentLists.size(); i++){
		JSONObject obj = (JSONObject)freeCommentLists.get(i);
		int grpl = (int)obj.get("free_grpl");
		String sgrpl = "";
		for(int j = 0; j < grpl; j++){
			sgrpl += "<td></td>";
		}
		Object free_cmt_seq = obj.get("free_cmt_seq");
		Object free_pseq = obj.get("free_pseq");
		Object free_cmt_writer_seq = obj.get("free_cmt_writer_seq");
		Object free_grp = obj.get("free_grp");
		Object free_grps = obj.get("free_grps");
		Object free_cmt_writer = obj.get("free_cmt_writer");
		Object free_cmt_content = obj.get("free_cmt_content");
		Object free_cmt_reg_date = obj.get("free_cmt_reg_date");
		
		sbHtml.append("<tr>");
		sbHtml.append(sgrpl);
		sbHtml.append("<td>"+ free_cmt_seq +"</td>");
		sbHtml.append("<td>"+ free_pseq +"</td>");
		sbHtml.append("<td>"+ free_cmt_writer_seq +"</td>");
		sbHtml.append("<td>"+ free_grp +"</td>");
		sbHtml.append("<td>"+ free_grps +"</td>");
		sbHtml.append("<td>"+ free_cmt_writer +"</td>");
		sbHtml.append("<td>"+ free_cmt_content +"</td>");
		sbHtml.append("<td>"+ free_cmt_reg_date +"</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='쓰기' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_write.do?free_cmt_seq=" + free_cmt_seq + "\"'/>");
		sbHtml.append("</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='수정' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_modify.do?free_cmt_seq=" + free_cmt_seq + "\"'/>");
		sbHtml.append("</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='삭제' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_delete.do?free_cmt_seq=" + free_cmt_seq + "\"'/>");
		sbHtml.append("</td>");
		sbHtml.append("</tr>");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/free/view.do" method="get">
		글 번호 <input type="text" name="free_seq" value="<%=free_seq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="free_writer_seq" value="<%=free_writer_seq %>" readonly /> <br/>
		리뷰 제목 <input type="text" name="free_subject" value="<%=free_suject %>" /> <br/>
		글쓴이 <input type="text" name="free_writer" value="<%=free_writer %>" readonly /> <br/>
		글쓴날 <input type="text" name="free_reg_date" value="<%=free_reg_date %>" readonly /> <br/>
		리뷰 내용 <input type="text" name="free_content" value="<%=free_content %>" /> <br/>
		조회수 <input type="text" name="free_hit" value="<%=free_hit %>" readonly /> <br/>
		댓글 수 <input type="text" name="free_cmt_count" value="<%=free_cmt_count %>" readonly /> <br/>
		사진이름 <input type="text" name="free_file_name" value="<%=free_file_name %>" /> <br/>
		사진크기 <input type="text" name="free_file_size" value="<%=free_file_size %>" /> <br/>
		흡연 연차 <input type="text" name="free_smoke_years" value="<%=free_smoke_years %>" readonly /> <br/>
		<input type="submit" value="전송">
	</form>
	<form action="/free/parent_cmt_write.do" method="get">
		<input type="hidden" name="free_pseq" value="<%=free_seq %>">
		<input type="submit" value="댓글작성">
	</form>
	<br/><br/><br/>
	<div>
		<table width="800" border="1">
			<%= sbHtml.toString() %>
		</table>
	</div>
</body>
</html>