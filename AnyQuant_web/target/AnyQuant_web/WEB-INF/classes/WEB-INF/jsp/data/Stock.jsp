<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>全部股票信息</title>
	<link href="css/main/main.css" type="text/css" rel="stylesheet"/>
	<link href="css/data/stock.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/jquery-1.12.3.js"></script>
  	<script type="text/javascript" src="js/Highcharts-4.2.5/js/highcharts.js"></script>
  	<script type="text/javascript" src="js/chart/Pie.js"></script>
</head>
<body>
	<div class="head">
		<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
	</div>
	<div class="bodycontent">
		<div class="stock-top">
			<h1 class="name">全部股票</h1>
			<h3 class="words">股票有风险，投资需谨慎！</h3>
			<div class="searchCompo">
				
			</div>
		</div>
		<div class="ss-menu">
			<div class="ssm-name">
				<div class="shape"></div>
				<p class="ssmname">市场一览</p>
			</div>
			<ul class="grail-ul">
				<li class="sh-li">
					<div class="sh-container">
						<a href="#" class="sh-name">上证指数</a>
						<p class="sh-close">2300.0</p>
						<p class="sh-updown-per">-8.45 -4.3%</p>
						<p class="sh-vol">成交量：12233万手</p>
						<p class="sh-vol-n">成交额：4444亿元</p>
						<ul class="sh-updown">
							<li class="sh-up-num">涨：223</li>
							<li class="sh-same-num">平：224</li>
							<li class="sh-down-num">跌：225</li>
						</ul>
					</div>
					<div class="sh-graph">
					
					</div>
				</li>
				<li class="sz-li">
					<div class="sz-container">
						<a href="#" class="sz-name">深证成指</a>
						<p class="sz-close">2300.0</p>
						<p class="sz-updown-per">-8.45 -4.3%</p>
						<p class="sz-vol">成交量：12233万手</p>
						<p class="sz-vol-n">成交额：4444亿元</p>
						<ul class="sz-updown">
							<li class="sz-up-num">涨：223</li>
							<li class="sz-same-num">平：224</li>
							<li class="sz-down-num">跌：225</li>
						</ul>
					</div>
					<div class="sz-graph">
					
					</div>
				</li>
				<li class="hs300-li">
					<div>
					</div>
				</li>
			</ul>
		</div>
		<div class="grail-industry">
			<div class="ssm-name">
				<div class="shape"></div>
				<p class="ssmname">行业分析</p>
			</div>
			<div id="container" style="min-width:700px;height:350px"></div>
		</div>
		<div class="stock-order">
			
		</div>
		<div class="stockall-table">
			<div class="ssm-name">
				<div class="shape"></div>
				<p class="ssmname">股票列表</p>
			</div>
			<table border="1">
				<tr>
					<th>股票名称</th>
					<th>股票id</th>
				</tr>
				<tr>
					<td>1, 1</td>
					<td>1, 2</td>
				</tr>
				<tr>
					<td>2, 1</td>
					<td>2, 2</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="footer">
		<jsp:include page="/WEB-INF/jsp/main/foot.jsp"></jsp:include>
	</div>
</body>
</html>