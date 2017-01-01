<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>股票对比</title>
<link href="css/main/main.css" type="text/css" rel="stylesheet"/>
<link href="css/main/name.css" type="text/css" rel="stylesheet"/>
<link href="css/main/submenu.css" type="text/css" rel="stylesheet"/>
<link href="css/compare/compare.css" type="text/css" rel="stylesheet"/>
<link href="css/compare/modelLine.css" type="text/css" rel="stylesheet"/>
<link href="css/compare/newInf.css" type="text/css" rel="stylesheet"/>

<script src="js/compare/jquery.min.js"></script>
<script src="js/compare/cav.js"></script>
<script src="js/compare/backgroundShaking.js"></script>
<script src="js/chart/echarts.min.js"></script>
<script type="text/javascript" src='js/compare/modelLine.js'></script>
<script type="text/javascript" src='js/compare/compare.js'></script>
<script type="text/javascript" src='js/compare/obvCompare.js'></script>
<script src="js/jquery-1.12.3.js"></script>
<script src="js/compare/comboLine.js"></script>
<script src="js/Highstock-4.2.5/js/highstock.js"></script>
</head>
<body>
	<div class="head">
		<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
	</div>
	<div class="bodycontent">
		<div class="body-top">
			<h1 class="name">股票对比</h1>
			<h3 class="words">本界面展现信息仅供参考，实操有风险，看官须谨慎。</h3>
		</div>
		<div class="ss-menu-1">
			<div class="submenu-name">
				<div class="shape"></div>
				<p class="ss-name">最新信息</p>
			</div>
			<div class="index-page">
				<div class="banner">
					<div class="slide slide1">
						<div class="bg-wrapper">
							<div class="bg" id="container_left">
								<div id="canvas_left"></div>
							</div>
							<div class="mask"></div>
						</div>
						<div class="content" style="margin-left:30%">
							<div class="con-warpper-fixed">			
								<div class="con-ids">
									<p class="inf-name">${inf1.name}</p>
									<p class="inf-id">${inf1.stockId}</p>
								</div>
								<div class="inf-ocud">
									<p class="inf-open">开盘价：${inf1.open}</p>
									<p class="inf-close">收盘价：${inf1.close}</p>
									<p class="inf-up">最高价：${inf1.high}</p>
									<p class="inf-down">最低价：${inf1.low}</p>											
								</div>
								<div class="inf-updowns">
									<p class="inf-updown">涨跌额：${inf1.increase}</p>
									<p class="inf-updown-per">涨跌幅：${inf1.incrPer}%</p>
								</div>
								<div class="inf-vols">
									<p class="inf-vol">成交量：${inf1.volume}手</p>
									<p class="inf-vol-n">成交额：${inf1.amount}元</p>
									<p class="inf-turn">换手率：${inf1.turnover}%</p>
								</div>
								<p><a class="btn btn-left" id="btn-left-id" href="/AnyQuant_web/stockInfo.action?id=${inf1.stockId}">查看详情</a></p>
								<div class="inf-shadow-1"></div>
							</div>								
						</div>
					</div>
					<div class="slide slide2">
						<div class="bg-wrapper">
							<div class="bg" id="container_right">
								<div id="canvas_right"></div>
							</div>
							<div class="mask"></div>
						</div>
						<div class="content" style="margin-right:30%">
							<div class="con-warpper-fixed">			
								<div class="con-ids-2">
									<p class="inf-name-2">${inf2.name}</p>
									<p class="inf-id-2">${inf2.stockId}</p>
								</div>
								<div class="inf-ocud-2">
									<p class="inf-open-2">开盘价：${inf2.open}</p>
									<p class="inf-close-2">收盘价：${inf2.close}</p>
									<p class="inf-up-2">最高价：${inf2.high}</p>
									<p class="inf-down-2">最低价：${inf2.low}</p>											
								</div>
								<div class="inf-updowns-2">
									<p class="inf-updown-2">涨跌额：${inf2.increase}</p>
									<p class="inf-updown-per-2">涨跌幅：${inf2.incrPer}%</p>
								</div>
								<div class="inf-vols-2">
									<p class="inf-vol-2">成交量：${inf2.volume}手</p>
									<p class="inf-vol-n-2">成交额：${inf2.amount}元</p>
									<p class="inf-turn-2">换手率：${inf2.turnover}%</p>
								</div>
								<p><a class="btn btn-right" id="btn-right-id" href="/AnyQuant_web/stockInfo.action?id=${inf2.stockId}">查看详情</a></p>
								<div class="inf-shadow-2"></div>
							</div>		
						</div>
					</div>
				</div>			
			</div>		
		</div>
		<script type="text/javascript">
		</script>
		<div class="ss-menu-2">
			<div class="submenu-name">
				<div class="shape"></div>
				<p class="ss-name">图表对比</p>
			</div>
			<div class="comboLineContainer corner">
				<div class="comboLine" id="comboLine"></div>
			</div>
			<div class="chart c1 corner">
				<div id="rsi" class="smallChart"></div>
			</div>
			<div class="chart c2 corner">
				<div id="bias" class="smallChart"></div>
			</div>
			<div class="chart c3 corner">
				<div id="futurePrice" class="smallChart"></div>
			</div>
			<div class="chart c4 corner">
				<div id="buy" class="smallChart"></div>
			</div>
			<div class="chart c5 corner">
				<div id="sold" class="smallChart"></div>
			</div>
			<div class="chart c6 corner">
				<div id="futureTrade" class="smallChart"></div>
			</div>
		</div>
	</div>
	<div class="footer">
		<jsp:include page="/WEB-INF/jsp/main/foot.jsp"></jsp:include>
	</div>
	
	
	<script type="text/javascript">	
		$(function(){
			//backgroundShaking('container_left', 'canvas_left', '#ba2454', '#b73c63')
			backgroundShaking('container_left', 'canvas_left', '#0065a1', '#197bb5')
			backgroundShaking('container_right', 'canvas_right', '#ab6c11', '#cfa73a')
			paintComboLine("comboLine","${inf1.stockId}","${inf2.stockId}","${inf1.name}","${inf2.name}");
			statisticCompare();
			futureCompare();
		});
		
		
		function statisticCompare(){
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/getLineData.action',
				data:'id=${inf1.stockId}',
				success:function(data1){
					$.ajax({
						type:'post',
						url:'${pageContext.request.contextPath}/getLineData.action',
						data:'id=${inf2.stockId}',
						success:function(data2){
							lineCompare("rsi", "相对强弱指标对比",data1, data2,
									"rsi",'${inf1.name}', '${inf2.name}');
							
							lineCompare("bias", "乖离率对比",data1, data2,
									"bias10",'${inf1.name}', '${inf2.name}');
						}
					});
				}
			});
		}
		
		function futureCompare(){
		    $.ajax({
			type:'post',
			url:'${pageContext.request.contextPath}/getForecast.action',
			data:'id=${inf1.stockId}',
			success:function(data1){
			    $.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/getForecast.action',
				data:'id=${inf2.stockId}',
				success:function(data2){
				    lineCompare2("futurePrice", "未来七天股价对比",data1, data2,
						"open",'${inf1.name}', '${inf2.name}');
				    lineCompare2("futureTrade", "未来七天换手率对比",data1, data2,
						"turnover",'${inf1.name}', '${inf2.name}');
				}
			});
			}
	    });
		}
		
		$(function(){
			//空的雷达图
			var buyChart = echarts.init(document.getElementById('buy'));
			var soldChart = echarts.init(document.getElementById('sold'));
			buyChart.showLoading();
			soldChart.showLoading();
			//异步取数据
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/getAnalysisCompare.action',
				data:'id1=${inf1.stockId}&id2=${inf2.stockId}',
				success:function(data){
					buyChart.hideLoading();
					soldChart.hideLoading();
					var buyseries1=[];
					var buyseries2=[];
					var soldseries1=[];
					var soldseries2=[];
					var i=0;
					$.each(data,function(j,vo){
						if(i%2==0){
							var b=(vo["scoreIn"]);
							buyseries1.push(b);
							var s=(vo["scoreOut"]);
							soldseries1.push(s);	
						}
						else{
							var b=(vo["scoreIn"]);
							buyseries2.push(b);
							var s=(vo["scoreOut"]);
							soldseries2.push(s);	
						}
						i++;					
					});
					//图表设置
					var option={
					    title: {
					    	text: ''
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
					        x: 'center',
						    data:['${inf1.name}','${inf2.name}']
					    },
					    radar: {
					    	indicator:[
					    		{text:"kdj",max:100},
					    		{text:"macd",max:100},
					    		{text:"obv",max:100},
					    		{text:"rsi",max:100},
					    		{text:"vr",max:100}
					    	], 
					    	center: ['50%','60%'],
					    	radius:80
					    },				    	
					    series: []
					};
					buyChart.setOption(option);
					soldChart.setOption(option);
					buyChart.setOption({
						title: {
				            text: '买入评分'
				        },
						series: [{
				            type: 'radar',
				            tooltip: {
				                trigger: 'item'
				            },
				            itemStyle: {normal: {areaStyle: {type: 'default'}}},
				            data:[{
				            	name: '${inf1.name}',
				            	value: buyseries1
				        	},{
				        		name: '${inf2.name}',
				        		value: buyseries2
				        	}]
				        }]
				    });
					soldChart.setOption({
						title: {
				            text: '卖出评分'
				        },
						series: [{
				            type: 'radar',
				            tooltip: {
				                trigger: 'item'
				            },
				            itemStyle: {normal: {areaStyle: {type: 'default'}}},
				            data:[{
				            	name: '${inf1.name}',
				            	value: soldseries1
				        	},{
				        		name: '${inf2.name}',
				        		value: soldseries2
				        	}]
				        }]
				    });
				}
			});
		});
		
		$(document).ready(function(){
			$('.skillbar').each(function(){
				$(this).find('.skillbar-bar').animate({
				width:$(this).attr('data-percent')
				},3000);
			});
		});
		changeColor1(${inf1.increase});
		changeColor2(${inf2.increase});
	</script>
</body>
</html>