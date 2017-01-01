<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/stock/card.css">
<link rel="stylesheet" type="text/css" href="css/stock/head.css">
<link rel="stylesheet" type="text/css" href="css/stock/chart.css">
<link rel="stylesheet" type="text/css" href="css/stock/StockInf.css">
<script src="js/jquery-1.12.3.js"></script>
<script src="js/chart/KLine.js"></script>
<script src="js/chart/PriceByMinute.js"></script>
<script src="js/Highstock-4.2.5/js/highstock.js"></script>

<style type="text/css">
		.head{position:fixed;top:0px;left:0px;height:60px;width:100%;z-index: 10;}
		.footer{position:fixed;top:97%;left:0px;height:40px;width:100%;z-index: 10;}
		.background{position:fixed;top:0%;left:0px;height:100%;width:100%;z-index: -1;}
	</style>
<title>单只股票信息</title>
</head>
<body>
	<h1 class="companyName">可口可乐</h1>
	<img class="headImage" src="graphics/data/like.png"></img>
	<div class="head">
			<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
	</div>
	
	<img class="background" src="graphics/main/whiteBackground.png">

	
<!-- cards -->
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
     <!-- cards -->
     <div class="footer">
			<jsp:include page="/WEB-INF/jsp/main/foot.jsp"></jsp:include>
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

</body>
</html>
