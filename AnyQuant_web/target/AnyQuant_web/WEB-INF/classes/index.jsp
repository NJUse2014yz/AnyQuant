<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>主界面</title>
		<link href="css/main/reset.css" type="text/css" rel="stylesheet"/>
		<link href="css/main/main.css" type="text/css" rel="stylesheet"/>
		<link href="css/barTable/styles.css" rel="stylesheet" type="text/css"
			media="all" />
		<link href="css/barTable/demo.css" rel="stylesheet" type="text/css"
			media="all" />
		
		
		<style type="text/css">
			.recommend{position:relative;top:60px;left:0px;height:40%;width:100%;z-index: 1}			
		</style>
	</head>

	<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
		<div class="head">
			<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
		</div>
		<div class="recommend">
			<jsp:include page="/WEB-INF/jsp/main/recommend.jsp"></jsp:include>
		</div>
		
		<div class="tableContainer">
		<!-- start header here-->
		<header>
		<div id="fdw-pricing-table">
			<div class="plan plan1">
				<div class="header">可口可乐</div>
				<div class="price">￥59.23</div>
				<div class="monthly">2015、</div>
				<ul>
					<li>市盈率:<b>90%</b></li>
					<li>市净率:<b>45%</b></li>
					<li>涨幅:<b>123%</b></li>
					<li><b>Unlimited</b> subdomains</li>
				</ul>
				<a class="signup" href="http://www.freshdesignweb.com">查看更多</a>
			</div>
			<div class="plan plan2">
				<div class="header">Professional</div>
				<div class="price">$29</div>
				<div class="monthly">per month</div>
				<ul>
					<li><b>5GB</b> Disk Space</li>
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
	</body>
</html>
