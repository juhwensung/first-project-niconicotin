    
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%    
	int flag = (Integer)request.getAttribute("flag");
    out.println("<script type='text/javascript'>");
    if(flag == 0){
		out.println("alert('비밀 번호 수정완료.')");
		out.println("location.href='./member_view.do'"); //수정할 글 번호 seq 꼭 가져오기
	} else {
		out.println("alert('비밀 번호 수정 실패.');");
		out.println("history.back();");
	}
    
	out.println("</script>");
%>