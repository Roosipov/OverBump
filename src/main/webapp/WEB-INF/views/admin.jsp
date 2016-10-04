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
    <div class="comments">

         <c:forEach items="${posts}" var="post">
         <div id="10052" class="comment">
            <div class="info"><fmt:formatDate value="${post.joiningDate}" pattern="HH:mm:ss dd/MM/yyyy"/> </div>
            <div class="text">${comment.comm}</div>
        </div>
        </c:forEach>
        <form:form method="POST" modelAttribute="comment" class="comm">
				<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-3 control-lable" for="comm">Comment</label>
							<div class="col-md-7">
								<form:textarea rows="6" cols="85" path="comm" id="comm" />
							</div>
							
						</div>
					</div>
			
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Comment" class="open">
						</div>
					</div>
		</form:form>
		<a name="bottom"></a>
        <a class="more" href="">показать еще</a>
    	</div>
    	
</main>


<footer>&copy; Overbump, 2016&ensp;&bull;&ensp;Rosipov Development&ensp;&bull;&ensp;<a href="#">UP</a></footer>

</body>
</html>
