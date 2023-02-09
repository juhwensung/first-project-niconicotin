<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.json.simple.JSONObject" %>
<%
	JSONObject cigarModifyObj = (JSONObject)request.getAttribute("cigarModifyObj");
	Object cigar_seq = cigarModifyObj.get("cigar_seq");
	Object cigar_writer_seq = cigarModifyObj.get("cigar_writer_seq");
	Object cigar_brand = cigarModifyObj.get("cigar_brand");
	Object cigar_name = cigarModifyObj.get("cigar_name");
	Object cigar_tar = cigarModifyObj.get("cigar_tar");
	Object cigar_nicotine = cigarModifyObj.get("cigar_nicotine");
	Object cigar_file_name = cigarModifyObj.get("cigar_file_name");
	Object cigar_file_size = cigarModifyObj.get("cigar_file_size");
	Object cigar_hash_tag = cigarModifyObj.get("cigar_hash_tag");
	Object cigar_content = cigarModifyObj.get("cigar_content");
	Object cigar_total_grade = cigarModifyObj.get("cigar_total_grade");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/cigar/modify_ok.do" method="get">
		글 번호 <input type="text" name="cigar_seq" value="<%=cigar_seq %>" readonly /> <br/>
		글쓴이 번호 <input type="text" name="cigar_writer_seq" value="<%=cigar_writer_seq %>" readonly /> <br/>
		브랜드 <input type="text" name="cigar_brand" value="<%=cigar_brand %>" /> <br/>
		담배이름 <input type="text" name="cigar_name" value="<%=cigar_name %>" /> <br/>
		타르량 <input type="text" name="cigar_tar" value="<%=cigar_tar %>" /> <br/>
		니코틴양 <input type="text" name="cigar_nicotine" value="<%=cigar_nicotine %>" /> <br/>
		사진이름 <input type="text" name="cigar_file_name" value="<%=cigar_file_name %>" /> <br/>
		사진크기 <input type="text" name="cigar_file_size" value="<%=cigar_file_size %>" /> <br/>
		해시 태그 <input type="text" name="cigar_hash_tag" value="<%=cigar_hash_tag %>" /> <br/>
		설명 <input type="text" name="cigar_content" value="<%=cigar_content %>" /> <br/>
		평점 <input type="text" name="cigar_total_grade"  value="<%=cigar_total_grade %>" readonly /> <br/>
		<input type="submit" value="전송">
	</form>
</body>
</html>