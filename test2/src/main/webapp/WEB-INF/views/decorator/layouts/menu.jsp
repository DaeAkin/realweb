<%@page import="com.min.www.dto.member.MemberDto"%>
<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


</head>
<body>
<%	MemberDto memberInfo = (MemberDto)session.getAttribute("memberInfo");
	String requestId = (String)request.getAttribute("requestId");
	String id = (String)session.getAttribute("loginuser");
	String nickname = (String)session.getAttribute("nickname");
	

	
%>


  	<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">하이하이!${IMAGEURL}${id }${nickname }asd${check }${hi} </a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="<%=request.getContextPath()%>/board/list">Home</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Page 1-1</a></li>
            <li><a href="#">Page 1-2</a></li>
            <li><a href="#">Page 1-3</a></li>
          </ul>
        </li>
        <li><a href="<%=request.getContextPath()%>/board/list">게시판</a></li>
        <li><a href="#">Page 3</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
       <%
    	if(id == null) {
    %>
        <li><a href="<%=request.getContextPath()%>/member"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="<%=request.getContextPath()%>/member/loginform"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      	<%
    	} else {
        %>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <span class="glyphicon glyphicon-user"></span> <%=(String)session.getAttribute("nickname") %>님<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<%=request.getContextPath()%>/member/edit">회원정보변경</a></li>
            <li><a href="<%=request.getContextPath()%>/member/logout">Logout </a></li>
            <li><a href="#">Page 1-3</a></li>
          </ul>
        </li>
        
       
        <%} %>
      </ul>
    </div>
  </div>
</nav>
  

    
    



<!--  
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
-->


</body>
</html>