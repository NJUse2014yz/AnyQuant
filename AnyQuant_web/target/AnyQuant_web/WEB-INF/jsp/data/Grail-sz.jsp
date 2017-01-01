<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>深证成指</title>
<link href="css/main/main.css" type="text/css" rel="stylesheet"/>
<link href="css/data/grail.css" type="text/css" rel="stylesheet"/>
<link href="css/stock/card.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="head">
		<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
	</div>
	<div class="bodycontent">
		<div class="grail-top">
			<h1 class="name">深证成指</h1>
			<h3 class="words">从深圳证券交易所挂牌上市的所有股票中抽取具有市场代表性的500家上市公司的股票为样本，以自由流通股本为权数，采用派氏加权法编制而成的股价指标。</h3>
			<div class="searchCompo">				
			</div>
		</div>
		<!-- cards -->
		<div class="cards">	
			<div class="ssm-name">
				<div class="shape"></div>
				<p class="ssmname">最新市情</p>
			</div>		
	    	<div class="card pinkCard">
	     		<!--  <img class="card" alt="user" src="graphics/data/user.png">
	     		<small>员工数</small>
	     		<h2>3678</h2>-->
	     	</div>
	     
		    <div class="card blueCard">
		     
		     
		    </div>
		     
		    <div class="card greenCard">
		     
		     
		    </div>
		     
		    <div class="card grayCard">
		     
		     
		    </div>
		     
		    <div class="card orangeCard">
	     
	     	</div>
		</div>
	     <!-- cards -->
		<ul class="grail-ul">
			<li class="sh-li">
				<div>
				</div>
			</li>
			<li class="sz-li">
				<div>
				</div>
			</li>
			<li class="hs300-li">
				<div>
				</div>
			</li>
		</ul>
		<div class="grail-industry">
			
		</div>
		<div class="stock-order">
			
		</div>
		<dir class="stockall-table">
			
		</dir>
	</div>
	<div class="footer">
		<jsp:include page="/WEB-INF/jsp/main/foot.jsp"></jsp:include>
	</div>
</body>
</html>