<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%
	JSONObject requestViewObj = (JSONObject)request.getAttribute("requestViewObj");
	Object request_seq = requestViewObj.get("request_seq");
	Object request_writer_seq = requestViewObj.get("request_writer_seq");
	Object request_suject = requestViewObj.get("request_suject");
	Object request_writer = requestViewObj.get("request_writer");
	Object request_reg_date = requestViewObj.get("request_reg_date");
	Object request_content = requestViewObj.get("request_content");
	Object request_hit = requestViewObj.get("request_hit");
	Object request_cmt_count = requestViewObj.get("request_cmt_count");
	Object request_cigar_brand = requestViewObj.get("request_cigar_brand");
	Object request_cigar_name = requestViewObj.get("request_cigar_name");
	Object request_tar = requestViewObj.get("request_tar");
	Object request_nicotine = requestViewObj.get("request_nicotine");
	Object request_file_name = requestViewObj.get("request_file_name");
	Object request_file_size = requestViewObj.get("request_file_size");
	Object request_smoke_years = requestViewObj.get("request_smoke_years");
		
	JSONArray requestCommentLists = (JSONArray)request.getAttribute("requestCommentLists");
	StringBuilder sbHtml = new StringBuilder();
	for(int i = 0; i < requestCommentLists.size(); i++){
		JSONObject obj = (JSONObject)requestCommentLists.get(i);
		int grpl = (int)obj.get("request_grpl");
		String sgrpl = "";
		for(int j = 0; j < grpl; j++){
			sgrpl += "<td></td>";
		}
		Object request_cmt_seq = obj.get("request_cmt_seq");
		Object request_pseq = obj.get("request_pseq");
		Object request_cmt_writer_seq = obj.get("request_cmt_writer_seq");
		Object request_grp = obj.get("request_grp");
		Object request_grps = obj.get("request_grps");
		Object request_cmt_writer = obj.get("request_cmt_writer");
		Object request_cmt_content = obj.get("request_cmt_content");
		Object request_cmt_reg_date = obj.get("request_cmt_reg_date");
		
		sbHtml.append("<tr>");
		sbHtml.append(sgrpl);
		sbHtml.append("<td>"+ request_cmt_seq +"</td>");
		sbHtml.append("<td>"+ request_pseq +"</td>");
		sbHtml.append("<td>"+ request_cmt_writer_seq +"</td>");
		sbHtml.append("<td>"+ request_grp +"</td>");
		sbHtml.append("<td>"+ request_grps +"</td>");
		sbHtml.append("<td>"+ request_cmt_writer +"</td>");
		sbHtml.append("<td>"+ request_cmt_content +"</td>");
		sbHtml.append("<td>"+ request_cmt_reg_date +"</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='쓰기' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_write.do?request_cmt_seq=" + request_cmt_seq + "\"'/>");
		sbHtml.append("</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='수정' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_modify.do?request_cmt_seq=" + request_cmt_seq + "\"'/>");
		sbHtml.append("</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='삭제' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_delete.do?request_cmt_seq=" + request_cmt_seq + "\"'/>");
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
	<form action="/requestCigar/view.do" method="get">
		글 번호 <input type="text" name="request_seq" value="<%=request_seq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="request_writer_seq" value="<%=request_writer_seq %>" readonly /> <br/>
		리뷰 제목 <input type="text" name="request_subject" value="<%=request_suject %>" /> <br/>
		글쓴이 <input type="text" name="request_writer" value="<%=request_writer %>" readonly /> <br/>
		글쓴날 <input type="text" name="request_reg_date" value="<%=request_reg_date %>" readonly /> <br/>
		리뷰 내용 <input type="text" name="request_content" value="<%=request_content %>" /> <br/>
		조회수 <input type="text" name="request_hit" value="<%=request_hit %>" readonly /> <br/>
		댓글 수 <input type="text" name="request_cmt_count" value="<%=request_cmt_count %>" readonly /> <br/>
		사진이름 <input type="text" name="request_file_name" value="<%=request_file_name %>" /> <br/>
		사진크기 <input type="text" name="request_file_size" value="<%=request_file_size %>" /> <br/>
		흡연 연차 <input type="text" name="request_smoke_years" value="<%=request_smoke_years %>" readonly /> <br/>
		<input type="submit" value="전송">
	</form>
	<form action="/requestCigar/parent_cmt_write.do" method="get">
		<input type="hidden" name="request_pseq" value="<%=request_seq %>">
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