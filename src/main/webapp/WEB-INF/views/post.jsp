<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<a name="up"></a>
   <img class="image" src="/OverBump/imageController/imageDisplay?id=${post.id}"/>

    <div class="btns" style="text-align: center;">
    	<div class="description">${post.description}.</div>
    	<div class="info" style="margin: 0 16px 0 0;"><img alt="" src="static/img/bump2.png"> ${post.bumps}</div>
        <!--  <div class="info" style="margin: 0 16px 0 0;"><img alt="" src="static/img/eye.png"> ${post.views}</div> -->
        <div class="info" style="margin: 0 16px 0 0;"><img alt="" src="static/img/bubbles.png"> ${post.comments}</div>
        <a class="open" href="postup-id-${post.id}">Bump</a>
        <a class="ban" href="postsage-id-${post.id}">Sage</a>
        <a class="ban" href="report-id-${post.id}">Report</a>
    </div>
    <div class="comments">

         <c:forEach items="${comments}" var="comment">
         <div id="10052" class="comment">
            <div class="info"><fmt:formatDate value="${comment.commDate}" pattern="HH:mm:ss dd/MM/yyyy"/> </div>
            <div class="text">${comment.comm}</div>
        </div>
        </c:forEach>
        <form:form method="POST" modelAttribute="comment" class="comm">

						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="comm">Add your comment here.</label>
							<div class="col-md-7">
								<form:textarea rows="6" cols="85" path="comm" id="comm" />
							</div>
							
						</div>				

			
						<div class="form-actions floatRight">
							<input type="submit" value="Comment" class="open">
						</div>

		</form:form>
		<a name="bottom"></a>
        <a class="more" href="">показать еще</a>
    	</div>
    	
</main>


<footer>&copy; Overbump, 2016&ensp;&bull;&ensp;Rosipov Development&ensp;&bull;&ensp;<a href="#">UP</a></footer>

</body>
</html>
