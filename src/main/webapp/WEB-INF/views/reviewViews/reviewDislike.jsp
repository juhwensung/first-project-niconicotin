<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int flag = (Integer)request.getAttribute("flag");	

// redirection
out.println("<script type='text/javascript'>");
if(flag == 0){
	out.println("alert('싫어요 성공');");
	out.println("history.back()");
} else if (flag == 1){
	out.println("alert('싫어요 취소');");
	out.println("history.back();");
} else if (flag == 3) {
	out.println("alert('좋아요 누른 게시글에 싫어요를 할 수 없습니다.');");
	out.println("history.back();");
} else if(flag == 4){
	out.println("alert('로그인 하셔야합니다.');");
	out.println("history.back();");
} else {
	out.println("alert('에러');");
	out.println("history.back();");
}
out.println("</script>");
%>