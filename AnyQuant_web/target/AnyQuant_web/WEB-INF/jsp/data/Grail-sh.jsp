<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上证指数</title>
<script src="js/jquery-1.12.3.js"></script>
<script src="js/chart/KLine.js"></script>
<script src="js/chart/PriceByMinute.js"></script>
<script src="js/Highstock-4.2.5/js/highstock.js"></script>
<link href="css/main/main.css" type="text/css" rel="stylesheet"/>
<link href="css/stock/card.css" type="text/css" rel="stylesheet">
<link href="css/data/grail.css" type="text/css" rel="stylesheet"/>

</head>
<body>
<div class="head">
		<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
	</div>
	<div class="bodycontent">
		<div class="grail-top">
			<h1 class="name">上证指数</h1>
			<h3 class="words">以上证所挂牌上市的全部股票为计算范围，以发行量为权数的加权综合股价指数。</h3>
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
	     		<p>昨   收:2827.11</p>
	     		<p>今   开:2816.78</p>
	     	</div>
	     
		    <div class="card blueCard">
		     	<p>最 高:2851.23</p>
	     		<p>最 低:2804.99</p>
		    </div>
		     
		    <div class="card greenCard">
		     	<p>000001.SH</p>
	     		<p>价格:2816.78</p>
		    </div>
		     
		    <div class="card grayCard">
		     	<p>成交量:1.15亿手</p>
	     		<p>成交额:1268亿</p>
		    </div>
		     
		    <div class="card orangeCard">
	     		<p>涨跌幅:+0.84%</p>
	     		<p>涨跌额：+23.75</p>
	     	</div>
		</div>
	     <!-- cards -->
	    
		<div class="grail-k">
			<div class="ssm-name">
				<div class="shape"></div>
				<p class="ssmname">图表展现</p>
			</div>
			<div id="chart" class="chart kLine"></div>
     		<div id="price" class="chart price"></div>
     		<script>
				var chartData=[];
				$(function () {
				    paintKLine();
				    priceByMinute();
				});
				
				
				function refresh(){
					//异步查詢
					$.ajax({
						type:'post',
						url:'${pageContext.request.contextPath}/getData.action',
						data:'',
						success:function(data){
							chart.series[0].setData(data);
						}
					});
				}
			</script>     		
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