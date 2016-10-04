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

		<article>
            <img alt="" src="static/dat/${existPost.name}">
        </article>
		<div class="existPost">
            This  image was alredy uploaded by enather user. Please do not copy-paste existing posts.
        </div>
        <a class="more" href="post-id-${existPost.id}">To existing post</a>
        <a class="more" href="upload">Back to upload page</a>
</main>

        <footer>&copy; Overbump, 2016&ensp;&bull;&ensp;Rosipov Development&ensp;&bull;&ensp;<a href="#">UP</a></footer>
    </body>
</html>