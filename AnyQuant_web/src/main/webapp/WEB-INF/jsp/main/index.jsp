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
		
		<script src="js/jquery-1.12.3.js"></script>
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
				<div class="header">${strategyStocks[0].name}</div>
				<div class="price">￥${strategyStocks[0].predictList[0].open}</div>
				<div class="monthly">预测股价</div>
				<ul>
					<li>预测收盘价:<b>${strategyStocks.get(0).predictList.get(0).close}</b></li>
					<li>预测换手率:<b>${strategyStocks.get(0).predictList.get(0).turnover}%</b></li>
					<li>昨日收盘价:<b>${stocks[0].close}</b></li>
					<li>昨日涨幅:<b>${stocks[0].incrPer}%</b></li>
					<li><b>强烈推荐</b></li>
				</ul>
				<a class="signup" href="/AnyQuant_web/stockInfo.action?id=${strategyStocks[0].sid}">查看更多</a>
			</div>
			<div class="plan plan2">
				<div class="header">${strategyStocks[1].name}</div>
				<div class="price">￥${strategyStocks.get(1).predictList.get(0).open}</div>
				<div class="monthly">预测股价</div>
				<ul>
					<li>预测收盘价:<b>${strategyStocks.get(1).predictList.get(0).close}</b></li>
					<li>预测换手率:<b>${strategyStocks.get(1).predictList.get(0).turnover}%</b></li>
					<li>昨日收盘价:<b>${stocks[1].close}</b></li>
					<li>昨日涨幅:<b>${stocks[1].incrPer}%</b></li>
					<li><b>强烈推荐</b></li>
				</ul>
				<a class="signup" href="/AnyQuant_web/stockInfo.action?id=${strategyStocks[1].sid}">查看更多</a>
			</div>
			<div class="plan plan3">
				<div class="header">${strategyStocks[2].name}</div>
				<div class="price">￥${strategyStocks.get(2).predictList.get(0).open}</div>
				<div class="monthly">预测股价</div>
				<ul>
					<li>预测收盘价:<b>${strategyStocks.get(2).predictList.get(0).close}</b></li>
					<li>预测换手率:<b>${strategyStocks.get(2).predictList.get(0).turnover}%</b></li>
					<li>昨日收盘价:<b>${stocks[2].close}</b></li>
					<li>昨日涨幅:<b>${stocks[2].incrPer}%</b></li>
					<li><b>强烈推荐</b></li>
				</ul>
				<a class="signup" href="/AnyQuant_web/stockInfo.action?id=${strategyStocks[2].sid}">查看更多</a>
			</div>
			<div class="plan plan4">
				<div class="header">${strategyStocks[3].name}</div>
				<div class="price">￥${strategyStocks.get(3).predictList.get(0).open}</div>
				<div class="monthly">预测股价</div>
				<ul>
					<li>预测收盘价:<b>${strategyStocks.get(3).predictList.get(0).close}</b></li>
					<li>预测换手率:<b>${strategyStocks.get(3).predictList.get(0).turnover}%</b></li>
					<li>昨日收盘价:<b>${stocks[3].close}</b></li>
					<li>昨日涨幅:<b>${stocks[3].incrPer}%</b></li>
					<li><b>强烈推荐</b></li>
				</ul>
				<a class="signup" href="/AnyQuant_web/stockInfo.action?id=${strategyStocks[3].sid}">查看更多</a>
			</div>
		</div>
		</header>
		<!-- end header -->
	</div>
	
		<div class="footer">
			<jsp:include page="/WEB-INF/jsp/main/foot.jsp"></jsp:include>
		</div>
		
	<script type="text/javascript">
		
		
		
		
		
	
	</script>
	</body>
</html>
