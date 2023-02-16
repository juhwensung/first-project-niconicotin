<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
System.out.println(request.getParameter("phoneNum"));
int flag = (Integer)request.getAttribute("flag");

// redirectiopn
out.println("<script type='text/javascript'>");
if(flag == 0){
	out.println("alert('회원가입 성공.')");
	out.println("location.href='./login.do';");
} else if(flag == 1) {
	out.println("alert('회원가입 실패.');");
	out.println("history.back();");
} else if(flag == 2) {
	out.println("alert('주민 번호 형식 틀림.');");
	out.println("history.back();");
} else if(flag == 3) {
	out.println("alert('19세 미만은 가입이 불가 합니다.');");
	out.println("history.back();");
}
out.println("</script>");
%>