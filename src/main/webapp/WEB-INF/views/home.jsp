<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OverBump</title>
<link href="<c:url value='/static/css/main.css' />" rel="stylesheet"></link>
</head>	

<body>

<header>

     <a href="home" id="logo" title="На главную">overbump</a>
     <div class="menu">
         <a href="home">HOME</a>
         <a href="upload">UPLOAD</a>
    	 <!-- <a href="rules">RULES</a> -->
         <a href="feedback">FEEDBACK</a>
     </div>
     
</header>


<main>
    <div class="selector">
        <a href="home">NEW / ALL</a>
        <span>&bull;</span>
        <a href="home-top">TOP</a>
        <span>&bull;</span>
        <a href="home-pin">PINED</a>
    </div>
    
     <div class="pageCounter">
     <div>Posts on page -> 4</div>
     </div>
    
    <div class="container">
    <c:forEach items="${posts}" var="post">
        <article>
        	<div class="btn">
            	<div class="info">Description: ${post.description}.</div>
            </div>>
            <a href="<c:url value='/post-id-${post.id}' />"><img alt="" src="static/dat/${post.name}"></a>
            <div class="btns">
                <a href="<c:url value='/post-id-${post.id}' />"><div class="info"><img alt="" src="static/img/bump2.png"> ${post.bumps}</div></a>
                <a href="<c:url value='/post-id-${post.id}' />"><div class="info"><img alt="" src="static/img/eye.png"> ${post.views}</div></a>
                <a href="<c:url value='/post-id-${post.id}' />"><div class="info"><img alt="" src="static/img/bubbles.png">${post.comments}</div></a>
                <a class="open" href="<c:url value='/post-id-${post.id}'/>">OPEN</a>
              <!--  <div class="info">INFO ${post.description} dateTime</div> -->
            </div>
            <div class="btn">
            	<div class="info">Date: <fmt:formatDate value="${post.joiningDate}" pattern="HH:mm:ss dd/MM/yyyy"/> Location: ${post.country}, ${post.city}.</div>
            </div>>
        </article>
        </c:forEach>
    </div>
    
    <a class="more" href="home-showMore-${amount}">SHOW MORE</a>
</main>

        <footer>&copy; Overbump, 2016&ensp;&bull;&ensp;Rosipov Development&ensp;&bull;&ensp;<a href="#">UP</a></footer>
    </body>
</html>