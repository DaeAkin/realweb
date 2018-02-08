<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>

<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- menu -->

<div class="nav1">
    <ul>
        <li><a href="<%=request.getContextPath()%>/member">회원가입</a></li>
        <li><a href="<%=request.getContextPath()%>/member/login">로그인</a></li>
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
        <li>게시판
            <ul class="dropbox">
                <li>자유게시판
                </li>
                <li>하단메뉴 2</li>
            </ul></li>
        <li>메뉴 3</li>
        <li>메뉴 4</li>
    </ul>
</div>

<!-- content -->
<decorator:body></decorator:body>

<div class="footer">
    <hr>
        <ul>
        <li>개인정보수집</li>
        <li>이용 약관</li>
        <li>게시물 정지</li>
        <li>제휴 문의</li>
    </ul>
    <p>Email : kei890@naver.com</p>
    <p>Copyright@Donghyeon Min</p>
    
</div>


</body>
</html>