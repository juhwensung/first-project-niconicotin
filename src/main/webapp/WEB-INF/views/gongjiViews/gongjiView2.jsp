<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%
	JSONObject gongjiViewObj = (JSONObject)request.getAttribute("gongjiViewObj");
	Object gongji_seq = gongjiViewObj.get("gongji_seq");
	Object gongji_writer_seq = gongjiViewObj.get("gongji_writer_seq");
	Object gongji_suject = gongjiViewObj.get("gongji_suject");
	Object gongji_writer = gongjiViewObj.get("gongji_writer");
	Object gongji_reg_date = gongjiViewObj.get("gongji_reg_date");
	Object gongji_content = gongjiViewObj.get("gongji_content");
	Object gongji_hit = gongjiViewObj.get("gongji_hit");
	Object gongji_cmt_count = gongjiViewObj.get("gongji_cmt_count");
	Object gongji_file_name = gongjiViewObj.get("gongji_file_name");
	Object gongji_file_size = gongjiViewObj.get("gongji_file_size");
	Object gongji_smoke_years = gongjiViewObj.get("gongji_smoke_years");
	
	JSONArray gongjiCommentLists = (JSONArray)request.getAttribute("gongjiCommentLists");
	StringBuilder sbHtml = new StringBuilder();
	for(int i = 0; i < gongjiCommentLists.size(); i++){
		JSONObject obj = (JSONObject)gongjiCommentLists.get(i);
		int grpl = (int)obj.get("gongji_grpl");
		String sgrpl = "";
		for(int j = 0; j < grpl; j++){
			sgrpl += "<td></td>";
		}
		Object gongji_cmt_seq = obj.get("gongji_cmt_seq");
		Object gongji_pseq = obj.get("gongji_pseq");
		Object gongji_cmt_writer_seq = obj.get("gongji_cmt_writer_seq");
		Object gongji_grp = obj.get("gongji_grp");
		Object gongji_grps = obj.get("gongji_grps");
		Object gongji_cmt_writer = obj.get("gongji_cmt_writer");
		Object gongji_cmt_content = obj.get("gongji_cmt_content");
		Object gongji_cmt_reg_date = obj.get("gongji_cmt_reg_date");
		
		sbHtml.append("<tr>");
		sbHtml.append(sgrpl);
		sbHtml.append("<td>"+ gongji_cmt_seq +"</td>");
		sbHtml.append("<td>"+ gongji_pseq +"</td>");
		sbHtml.append("<td>"+ gongji_cmt_writer_seq +"</td>");
		sbHtml.append("<td>"+ gongji_grp +"</td>");
		sbHtml.append("<td>"+ gongji_grps +"</td>");
		sbHtml.append("<td>"+ gongji_cmt_writer +"</td>");
		sbHtml.append("<td>"+ gongji_cmt_content +"</td>");
		sbHtml.append("<td>"+ gongji_cmt_reg_date +"</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='쓰기' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_write.do?gongji_cmt_seq=" + gongji_cmt_seq + "\"'/>");
		sbHtml.append("</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='수정' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_modify.do?gongji_cmt_seq=" + gongji_cmt_seq + "\"'/>");
		sbHtml.append("</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='삭제' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_delete.do?gongji_cmt_seq=" + gongji_cmt_seq + "\"'/>");
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
	<form action="/gongji/view.do" method="get">
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
	<form action="/gongji/parent_cmt_write.do" method="get">
		<input type="hidden" name="gongji_pseq" value="<%=gongji_seq %>">
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