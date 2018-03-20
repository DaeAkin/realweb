<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


</head>
<body>
<%
	String requestId = (String)request.getAttribute("requestId");
	String id = (String)session.getAttribute("loginuser");
	String nickname = (String)session.getAttribute("nickname");
%>
<%=requestId %>
<%=id %>
<%=nickname %>
${nickname}
${hi} 
<div class="nav1">
    <ul>
    <%
    	if(id == null) {
    %>
    
        <li><a href="<%=request.getContextPath()%>/member">회원가입</a></li>
        <li><a href="<%=request.getContextPath()%>/member/loginform">로그인</a></li>
        <%
    	} else {
        %>
        <li><a href="<%=request.getContextPath()%>/member/logout">로그아웃</a></li>
        <li><a href="<%=request.getContextPath()%>/member/edit"><%=(String)session.getAttribute("nickname") %>님</a></li>
        <%} %>
    </ul>
</div>

<div class="nav2">
    <ul>
        <li class="home"><a href="#"><img src="%EC%A7%91.png"></a></li>
        <li>메뉴 1
            <ul class="dropbox">
                <li>하단메뉴 1</li>
                <li>하단메뉴 2</li>
                <li>아아아아아</li>
            </ul>
        </li>
        <li>게시판11ㄴ
            <ul class="dropbox">
                <li>자유게시판</li>
                <li>하단메뉴 2</li>
            </ul></li>
        <li>메뉴 3</li>
        <li>메뉴 4</li>
    </ul>
</div>


</body>
</html>