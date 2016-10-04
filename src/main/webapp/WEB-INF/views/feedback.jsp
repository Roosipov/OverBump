<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    
    
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>OverBump</title>
		<link href="<c:url value='/static/css/main.css' />" rel="stylesheet"></link>
		<link href="<c:url value='/static/css/lay.css' />" rel="stylesheet"></link>
		<link href="<c:url value='/static/css/contact.css' />" rel="stylesheet"></link>
       <!-- <script type="text/javascript" src="assets/js/jquery.js"></script>
        <script type="text/javascript" src="assets/js/js.js"></script> -->
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
    	<div class="masscontainer">
	        <div id="contact">
	            <div id="center">
	        <div id="contact_form">
	        	<form:form method="POST" id="contactForm" modelAttribute="message">
	                <div class="error" id="error">An error occurer and the message can't be sent!</div>
	                <div class="success" id="success">Email sent successfully!<br />Thank you for contacting us.</div>
	
	                    <span class="input">
						<label for="name"><b>Name:</b> </label>
						<form:input type="text" path="name" id="name" name="name"/>
								<div class="has-error">
									<form:errors path="name" class="help-inline"/>
								</div>
						</span>
						
						<span class="input">
						<label for="email"><b>Email:</b> </label>
						
						<form:input type="text" path="email" id="email" name="email"/>
								<div class="has-error">
									<form:errors path="email" class="help-inline"/>
								</div>
						</span>
						
					<!--  <span class="input">
						<label for="phone"><b>Phone:</b> </label>
						<input  type="text" path="phone" id="phone" name="phone" />
						<div class="warning" id="phoneError">Only digits are accepted!</div>
						</span> -->
						
						
						<span class="input">
						<label for="text"><b>Message:</b> </label>
						<textarea id="text" path="text" name="text">Hello,</textarea>
							<div class="has-error">
								<form:errors path="text" class="help-inline"/>
							</div>
						</span>
						
						<!--  <span class="input">
						<label for="security_code"><b>Security Code:</b> </label>
						<input class="noicon" type="text" id="security_code" name="security_code" style="width:100px" />
						<img src="assets/php/security/1/sec.php" style="vertical-align:middle;" />
						<div class="warning" id="security_codeError">The security code is wrong!</div>
						</span> -->
						
	                    <span id="submit" class="input">
	                    <label for="submit"></label>
	                    <!--  <p id="ajax_loader" style="text-align:center;"><img src="assets/img/contact/ajax-loader.gif" /></p> -->
	                    <input id="send" type="submit" value="SEND" />
	                    </span> 
	
	                </form:form>
	                </div>
	            </div>
	            <div id="bot"><!--bottom--></div>
	  
	        </div>
        </div>
        </main>
        <div align="center"><br>
          <footer>&copy; Overbump, 2016&ensp;&bull;&ensp;Rosipov Development&ensp;&bull;&ensp;<a href="#">UP</a></footer>
</body>
</html>

