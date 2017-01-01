<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>主界面</title>
		<script src="js/jquery-1.12.3.js"></script>
		<script src="js/cookie.js"></script>
		<link href="css/main/reset.css" type="text/css" rel="stylesheet"/>
		
		<link href="css/main/index.css" type="text/css" rel="stylesheet"/>
		<link href="css/barTable/styles.css" rel="stylesheet" type="text/css"
			media="all" />
		<link href="css/barTable/demo.css" rel="stylesheet" type="text/css"
			media="all" />
		<style type="text/css">

			
			.background{position:fixed;top:0%;left:0px;height:100%;width:100%;z-index:-1}
			button{
				position:fixed;top:20%;left:0px;height:10%;width:10%;
			}

			.head{position:fixed;top:0px;left:0px;height:60px;width:100%;z-index: 10;}
			.footer{position:fixed;top:97%;left:0px;height:40px;width:100%;z-index: 0;}
			

		</style>
	</head>

	<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" >
		<div class="head">
			<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
		</div>
		
		<div class="background">
			<img alt="imgf" src="graphics/main/background.jpg">
		</div>
		<button class="button">change</button>
		<div class="tableContainer">
		<!-- start header here-->
		<header>
		<div id="fdw-pricing-table">
			<div class="plan plan1">
				<div class="header">${list1[0].id}</div>
				<div class="price">￥59.23</div>
				<div class="monthly">2015/12/12</div>
				<ul>
					<li>市盈率:<b>90%</b></li>
					<li>市净率:<b>45%</b></li>
					<li>涨幅:<b>123%</b></li>
					<li><b>Unlimited</b> subdomains</li>
				</ul>
				<a class="signup" href="http://www.freshdesignweb.com">查看更多</a>
			</div>
			<div class="plan plan2 popular-plan">
				<div class="header">${list1[1].id}</div>
				<div class="price">$29</div>
				<div class="monthly">per month</div>
				<ul>
					<li><b class="ab">5GB</b> Disk Space</li>
					<li><b>50GB</b> Monthly Bandwidth</li>
					<li><b>10</b> Email Accounts</li>
					<li><b>Unlimited</b> subdomains</li>
				</ul>
				<a class="signup" href="http://www.freshdesignweb.com">Sign up</a>
			</div>
			<div class="plan plan3">
				<div class="header">Standard</div>
				<div class="price">$19</div>
				<div class="monthly">per month</div>
				<ul>
					<li><b>3GB</b> Disk Space</li>
					<li><b>25GB</b> Monthly Bandwidth</li>
					<li><b>5</b> Email Accounts</li>
					<li><b>Unlimited</b> subdomains</li>
				</ul>
				<a class="signup" href="http://www.freshdesignweb.com">Sign up</a>
			</div>
			<div class="plan plan4">
				<div class="header">Basic</div>
				<div class="price">$9</div>
				<div class="monthly">per month</div>
				<ul>
					<li><b>1GB</b> Disk Space</li>
					<li><b>10GB</b> Monthly Bandwidth</li>
					<li><b>2</b> Email Accounts</li>
					<li><b>Unlimited</b> subdomains</li>
				</ul>
				<a class="signup" href="http://www.freshdesignweb.com">Sign up</a>
			</div>
		</div>
		</header>
		<!-- end header -->
	</div>
	
		<div class="footer">
			<jsp:include page="/WEB-INF/jsp/main/foot.jsp"></jsp:include>
		</div>
		
		<script>
		$("button").click(function(){
			var number=getListNumber();
			//异步查詢
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/recommendJSON.action',
				data:'modelNumber='+number,
				success:function(data){
					$(".plan1 "+ ".header").text(data[0].id);
					$(".plan1 .price").text("99.2");
					$(".plan1 .month").text("2015/12/13");
				}
			});
			
		});
		
		function getListNumber(){
			var number=getCookie("modelNumber");
			if(number==""){
				setCookie("modelNumber", "1");
				return getCookie("modelNumber");
			}
			number++;
			if(number>1)
				number=0;
			setCookie("modelNumber", number);
			return number;
			
		}
		
		
		
		</script>
		
	</body>
</html>
