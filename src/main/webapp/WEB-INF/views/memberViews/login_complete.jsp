<%@page import="com.project.member.to.MemberTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    if( session.getAttribute( "emailss" ) == null){
    	out.println("<script type='text/javascript'>");
    	out.println("alert('로그인해야 됩니다.');");
    	out.println("location.href='login.do'");
    	out.println("</script>");
    } else {
       String user = (String)session.getAttribute("emailss");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button onclick="location.href='member_view.do'">내 정보 조회</button>
<%= user %><br>
<a href='logout_ok.do'>로그아웃</a> 
<div id="map" style="width:800px;height:600px;"></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1631125a0ba9902e938f432c1d7071f5"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
	var container = document.getElementById('map');
	var options = {
		center: new kakao.maps.LatLng(33.450701, 126.570667),
		level: 5
	};
		var map = new kakao.maps.Map(container, options);
</script>
</body>
</html>
<%
    }
%>