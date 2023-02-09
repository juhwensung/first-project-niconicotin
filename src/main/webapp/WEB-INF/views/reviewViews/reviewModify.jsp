<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
	JSONObject reviewModifyObj = (JSONObject)request.getAttribute("reviewModifyObj");
	Object review_seq = reviewModifyObj.get("review_seq");
	Object review_writer_seq = reviewModifyObj.get("review_writer_seq");
	Object review_cigar_seq = reviewModifyObj.get("review_cigar_seq");
	Object review_suject = reviewModifyObj.get("review_suject");
	Object review_writer = reviewModifyObj.get("review_writer");
	Object review_reg_date = reviewModifyObj.get("review_reg_date");
	Object review_content = reviewModifyObj.get("review_content");
	Object review_hit = reviewModifyObj.get("review_hit");
	Object review_cmt_count = reviewModifyObj.get("review_cmt_count");
	Object review_grade = reviewModifyObj.get("review_grade");
	Object review_like = reviewModifyObj.get("review_like");
	Object review_dislike = reviewModifyObj.get("review_dislike");
	Object review_file_name = reviewModifyObj.get("review_file_name");
	Object review_file_size = reviewModifyObj.get("review_file_size");
	Object review_smoke_years = reviewModifyObj.get("review_smoke_years");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/review/modify_ok.do" method="get">
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
</body>
</html>