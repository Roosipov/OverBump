<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OverBump</title>
<link href="<c:url value='/static/css/report.css' />" rel="stylesheet"></link>
</head>
    <body>
        <div class="container">
			
			<section class="main">
				<div class="wrapper-demo">
					<div id="dd" class="wrapper-dropdown-4">Report
						<ul class="dropdown">
							<li><input type="checkbox" id="el-1" name="el-1" value="donut"><label for="el-1">Съесть пирожок</label></li>
							<li><input type="checkbox" id="el-2" name="el-2" value="neighbour"><label for="el-2">Следить за соседями</label></li>
							<li><input type="checkbox" id="el-3" name="el-3" value="T-rex"><label for="el-3">Покормить кота</label></li>
						</ul>
					</div>
				</div>
			</section>
			
		</div>
		<!-- jQuery if needed -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript">
			
			function DropDown(el) {
				this.dd = el;
				this.opts = this.dd.find('ul.dropdown > li');
				this.val = [];
				this.index = [];
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;

					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						event.stopPropagation();
					});

					obj.opts.children('label').on('click',function(event){
						var opt = $(this).parent(),
							chbox = opt.children('input'),
							val = chbox.val(),
							idx = opt.index();

						($.inArray(val, obj.val) !== -1) ? obj.val.splice( $.inArray(val, obj.val), 1 ) : obj.val.push( val );
						($.inArray(idx, obj.index) !== -1) ? obj.index.splice( $.inArray(idx, obj.index), 1 ) : obj.index.push( idx );
					});
				},
				getValue : function() {
					return this.val;
				},
				getIndex : function() {
					return this.index;
				}
			}

			$(function() {

				var dd = new DropDown( $('#dd') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-4').removeClass('active');
				});

			});

		</script>
	</body>
</html>