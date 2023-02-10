<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%
	JSONObject reviewViewObj = (JSONObject)request.getAttribute("reviewViewObj");
	Object review_seq = reviewViewObj.get("review_seq");
	Object review_writer_seq = reviewViewObj.get("review_writer_seq");
	Object review_cigar_seq = reviewViewObj.get("review_cigar_seq");
	Object review_suject = reviewViewObj.get("review_suject");
	Object review_writer = reviewViewObj.get("review_writer");
	Object review_reg_date = reviewViewObj.get("review_reg_date");
	Object review_content = reviewViewObj.get("review_content");
	Object review_hit = reviewViewObj.get("review_hit");
	Object review_cmt_count = reviewViewObj.get("review_cmt_count");
	Object review_grade = reviewViewObj.get("review_grade");
	Object review_like = reviewViewObj.get("review_like");
	Object review_dislike = reviewViewObj.get("review_dislike");
	Object review_file_name = reviewViewObj.get("review_file_name");
	Object review_file_size = reviewViewObj.get("review_file_size");
	Object review_smoke_years = reviewViewObj.get("review_smoke_years");

	JSONArray reviewCommentLists = (JSONArray)request.getAttribute("reviewCommentLists");
	StringBuilder sbHtml = new StringBuilder();
	for(int i = 0; i < reviewCommentLists.size(); i++){
		JSONObject obj = (JSONObject)reviewCommentLists.get(i);
		int grpl = (int)obj.get("review_grpl");
		String sgrpl = "";
		for(int j = 0; j < grpl; j++){
			sgrpl += "<td></td>";
		}
		Object review_cmt_seq = obj.get("review_cmt_seq");
		Object review_pseq = obj.get("review_pseq");
		Object review_cmt_writer_seq = obj.get("review_cmt_writer_seq");
		Object review_grp = obj.get("review_grp");
		Object review_grps = obj.get("review_grps");
		Object review_cmt_writer = obj.get("review_cmt_writer");
		Object review_cmt_content = obj.get("review_cmt_content");
		Object review_cmt_reg_date = obj.get("review_cmt_reg_date");
		
		sbHtml.append("<tr>");
		sbHtml.append(sgrpl);
		sbHtml.append("<td>"+ review_cmt_seq +"</td>");
		sbHtml.append("<td>"+ review_pseq +"</td>");
		sbHtml.append("<td>"+ review_cmt_writer_seq +"</td>");
		sbHtml.append("<td>"+ review_grp +"</td>");
		sbHtml.append("<td>"+ review_grps +"</td>");
		sbHtml.append("<td>"+ review_cmt_writer +"</td>");
		sbHtml.append("<td>"+ review_cmt_content +"</td>");
		sbHtml.append("<td>"+ review_cmt_reg_date +"</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='쓰기' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_write.do?review_cmt_seq=" + review_cmt_seq + "\"'/>");
		sbHtml.append("</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='수정' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_modify.do?review_cmt_seq=" + review_cmt_seq + "\"'/>");
		sbHtml.append("</td>");
		sbHtml.append("<td>");
		sbHtml.append("<input type='button' value='삭제' class='btn_write btn_txt01' style='cursor: pointer;' onclick='location.href=\"cmt_delete.do?review_cmt_seq=" + review_cmt_seq + "\"'/>");
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
	<form action="/review/view.do" method="get">
		글 번호 <input type="text" name="review_seq" value="<%=review_seq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="review_writer_seq" value="<%=review_writer_seq %>" readonly /> <br/>
		담배 번호 <input type="text" name="review_cigar_seq" value="<%=review_cigar_seq %>" readonly /> <br/>
		리뷰 제목 <input type="text" name="review_subject" value="<%=review_suject %>" /> <br/>
		글쓴이 <input type="text" name="review_writer" value="<%=review_writer %>" readonly /> <br/>
		글쓴날 <input type="text" name="review_reg_date" value="<%=review_reg_date %>" readonly /> <br/>
		리뷰 내용 <input type="text" name="review_content" value="<%=review_content %>" /> <br/>
		조회수 <input type="text" name="review_hit" value="<%=review_hit %>" readonly /> <br/>
		댓글 수 <input type="text" name="review_cmt_count" value="<%=review_cmt_count %>" readonly /> <br/>
		평점 <input type="text" name="review_grade"  value="<%=review_grade %>" /> <br/>
		좋아요 <input type="text" name="review_like"  value="<%=review_like %>" readonly /> <br/>
		싫어요 <input type="text" name="review_dislike"  value="<%=review_dislike %>" readonly /> <br/>
		사진이름 <input type="text" name="review_file_name" value="<%=review_file_name %>" /> <br/>
		사진크기 <input type="text" name="review_file_size" value="<%=review_file_size %>" /> <br/>
		흡연 연차 <input type="text" name="review_smoke_years" value="<%=review_smoke_years %>" readonly /> <br/>
		<input type="submit" value="전송">
	</form>
	<form action="/review/parent_cmt_write.do" method="get">
		<input type="hidden" name="review_pseq" value="<%=review_seq %>">
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