<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单股策略分析</title>
<link href="css/main/main.css" type="text/css" rel="stylesheet"/>
<link href="css/main/name.css" type="text/css" rel="stylesheet"/>
<link href="css/analysis/analysis.css" type="text/css" rel="stylesheet"/>
<link href="css/analysis/strategy.css" type="text/css" rel="stylesheet"/>
<link href="css/analysis/labelauty.css" type="text/css" rel="stylesheet"/>
<link href="css/tool/loading.css" type="text/css" rel="stylesheet"/>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/chart/echarts.min.js"></script>
<script src="js/analysis/labelauty.js"></script>
<script>
	$(function(){
		$(':input').labelauty();
	});
	
	$(document).ready(function(){
		$(".change-container").hide();
		$(".loader").hide();
	  	$("#change-stock-id").click(function(){
	    	$(".change-container").fadeToggle();
	  	});
	});	
	$(function(){
		//监听更换股票确定按钮
		$('.change-confirm').click(function(){
			var input=$('.change-input').val();//获得输入	
			if(input.length==0)
				return;
			//验证id是否存在，id存在则跳转到对应股票页面
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/searchStock.action',
				data:'key='+input,
				success:function(data){
					//找不到唯一的股票则返回
					if(data.length!=1){
						alert("找不到股票");
						return;
					}					
					//跳转到对应股票页面
					window.location.href="/AnyQuant_web/strategy.action?id="+input;
				}
			});
		});
		var status=0;
		//监听指标选择
		$("input[type=checkbox]").click(function(){
			//提示信息隐藏
			$("li.result").hide();
			$("p.result2").hide();
			//空的雷达图
			var myChart = echarts.init(document.getElementById('analysis-graph'));
			var lineChart=echarts.init(document.getElementById('backtest-line'));
			//处理复选框
			var str="";
			$("input[type=checkbox]:checked").each(function(){ 
				//由于复选框一般选中的是多个,所以可以循环输出选中的值  
		        str=str+$(this).attr("id")+",";  				
		    });
			str=str+"end";
			$(".result-li").remove();
			if(str=="end"){
				$("li.result").show();
				$("p.result2").show();
				myChart.setOption({
			        xAxis: {
			            data: []
			        },
			        series: []
			    });
				return;
			}
			//回归测试折线图异步更新区
			lineChart.showLoading();
			//异步取折线图数据
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/getStrategy.action',
				data:'id=${inf.stockId}&'+'quotas='+str,
				success:function(data){
					lineChart.hideLoading();
					//右边数据处理
					$(".annualizedReturns").text("策略年化收益率:  "+(data["annualizedReturns"])+"%");
					$(".benchmarkReturns").text("基准年化收益率:  "+(data["benchmarkReturns"])+"%");
					$(".alpha").text("Alpha:  "+(data["alpha"])+"%");
					$(".beta").text("Beta:  "+(data["beta"])+"%");
					$(".sharpeRatio").text("夏普比率:  "+(data["sharpeRatio"])+"%");
					$(".volatility").text("收益波动率:  "+(data["volatility"])+"%");
					$(".informationRatio").text("信息比率:  "+(data["informationRatio"])+"%");
					$(".maxDrawdown").text("最大回撤:  "+(data["maxDrawdown"])+"%");
					$(".turnoverRate").text("换手率:  "+(data["turnoverRate"])+"%");
					//折线图数据处理
					
					var date = [];
					var stra = [];
					var base = [];
					
					$.each((data["cumlist"]),function(j,vo){
						var b=(vo["date"]);
						date.push(b);
						var s=(vo["value"]);
						stra.push(s);
					});
					$.each((data["bCumlist"]),function(j,vo){
						var s=(vo["value"]);
						base.push(s);
					});

					//图表更新
					lineChart.setOption({
					    tooltip: {
					        trigger: 'axis',
					        position: function (pt) {
					            return [pt[0], '10%'];
					        }
					    },
					    title: {
					        text: '累计收益折线图',
					    },
					    legend: {
					        data:['策略累计收益','基准累计收益']
					    },
					    xAxis: {
					        type: 'category',
					        boundaryGap: false,
					        data: date
					    },
					    yAxis: {
					        type: 'value',
					        boundaryGap: [0, '100%'],
					    	name:'%'
					    },
					    
					    series: [{
					    	name:'策略累计收益',
					        type:'line',
					        smooth:true,
					        symbol: 'none',
					        sampling: 'average',
				            data: stra
				        },{
				        	name:'基准累计收益',
					        type:'line',
					        smooth:true,
					        symbol: 'none',
					        sampling: 'average',
				            data: base
				        }]
					});
				}
			});
			//雷达图异步更新区
			myChart.showLoading();
			$(".loader").show();
			//异步取雷达图数据
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/getAnalysis.action',
				data:'id=${inf.stockId}&'+'quotas='+str,
				success:function(data){
					myChart.hideLoading();
					$(".loader").hide();
					var buyseries=[];
					var soldseries=[];
					$.each(data,function(j,vo){
						var b=(vo["scoreIn"]);
						buyseries.push(b);
						var s=(vo["scoreOut"]);
						soldseries.push(s);
						//结果展现
						var txt=document.createElement("li");
						txt.innerHTML=(vo["message"]); 
						var txtC=document.createAttribute("class");
						txtC.nodeValue = "result-li";
						txt.setAttributeNode(txtC);
						$(".result").after(txt);
					});
		
					//图表更新
					myChart.setOption({
				        title: {
				            text: ''
				        },
				        tooltip: {
				        	trigger: 'axis'
				        },
				        legend: {
				        	x: 'center',
					        data:['买入评分','卖出评分']
				        },
				        radar: {
				    		indicator: (function (){
				    			var res=[];
				    			var strs = str.split(',',10);
				                for (var i = 0; i < strs.length-1; i++) {
				                    res.push({text:strs[i],max:100});
				                }
				                return res;
				            })(), 
				    	     center: ['50%','52%'],
				    	     radius:150
				    	},				    	
				        series: [{
				        	name: '',
				            type: 'radar',
				            tooltip: {
				                trigger: 'item'
				            },
				            itemStyle: {normal: {areaStyle: {type: 'default'}}},
				            data : [
				                {
				                    value : buyseries,
				                    name : '买入评分'
				                },
				                 {
				                    value : soldseries,
				                    name : '卖出评分'
				                }
				            ]
				        }]
				    });
				}
			});
		});
	});
</script>
</head>
<body>
	<div class="head">
		<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
	</div>
	
	<div class="bodycontent">
		<div class="body-top">
			<h1 class="name">单股策略</h1>
			<h3 class="words">本页面默认以浦发银行（sh600000）作为分析股票，默认投资金额为10万，点击自定义按钮可针对股票分配，指标选择，投资金额等进行自定义策略分析！</h3>
		</div>
		<div class="backtest">
			<p class="title backtest-t1">回测</p>
			<p class="title backtest-t2">分析</p>
			<div class="backtest-result">
				<div id="backtest-line"></div>
				<p class="result2 r1">没有选择指标是看不到结果的哟！</p>
				<div class=backtest-data>
					<div class="back-datas annualizedReturns">策略年化收益率:</div>
					<div class="back-datas benchmarkReturns">基准年化收益率:</div>
					<div class="back-datas alpha">Alpha:</div>
					<div class="back-datas beta">Beta:</div>
					<div class="back-datas sharpeRatio">夏普比率:</div>
					<div class="back-datas volatility">收益波动率:</div>
					<div class="back-datas informationRatio">信息比率:</div>
					<div class="back-datas maxDrawdown">最大回撤:</div>
					<div class="back-datas turnoverRate">换手率:</div>
				</div>
			</div>
		</div>
		<div class="checkbox-quitas">
			<p class="title checkbox-title">指标选择</p>
			<div class="checkbox-container">
				<ul class="dowebok">
					<li><input type="checkbox" name="checkbox" id="bias" data-labelauty="BIAS"></li>
					<li><input type="checkbox" name="checkbox" id="boll" data-labelauty="BOLL"></li>
					<li><input type="checkbox" name="checkbox" id="dmi" data-labelauty="DMI"></li>
					<li><input type="checkbox" name="checkbox" id="kdj" data-labelauty="KDJ"></li>
					<li><input type="checkbox" name="checkbox" id="macd" data-labelauty="MACD"></li>
					<li><input type="checkbox" name="checkbox" id="obv" data-labelauty="OBV"></li>
					<li><input type="checkbox" name="checkbox" id="roc" data-labelauty="ROC"></li>
					<li><input type="checkbox" name="checkbox" id="rsi" data-labelauty="RSI"></li>
					<li><input type="checkbox" name="checkbox" id="vr" data-labelauty="VR"></li>
				</ul>
			</div>
			<p class="title result-title">分析结果</p>
			<div class="result-border"></div>
			<ul class="result-container">
				<li class="result">没有选择指标是看不到结果的哟！</li>	
			</ul>
			<div class="loader">
		        <div class="loader-inner pacman">
		          	<div></div>
		          	<div></div>
		          	<div></div>
		          	<div></div>
		          	<div></div>
		        </div>
      		</div>
		</div>
		<div class="stock-inf">
			<div class="con-ids">
				<p class="title inf-title">[${inf.name}]</p>
				<div class="change-stock-bt" id="change-stock-id">更换股票</div>				
			</div>
			<div class="change-container">
				<form class="change-stock">
		  			<span class="input">
	    				<input class="change-input" type="text" id="input-1"/>
	    				<label class="input__label" for="input-1">
	        				<span class="input__label-content">请输入股票ID</span>
	    				</label>
					</span>
					<div class="change-confirm">确定</div>
				</form>
				<p class="change-message">更换股票</p>
			</div>
			<p class="inf-id">(${inf.stockId})</p>
			<div class="inf-ocud">
				<p class="inf-open">开盘价：${inf.open}</p>
				<p class="inf-close">收盘价：${inf.close}</p>
				<p class="inf-high">最高价：${inf.high}</p>
				<p class="inf-low">最低价：${inf.low}</p>											
			</div>
			<div class="inf-others">
				<p class="inf-updown">涨跌额：${inf.increase}</p>
				<p class="inf-updown-per">涨跌幅：${inf.incrPer}%</p>
				<p class="inf-vol">成交量：${inf.volume}手</p>
				<p class="inf-vol-n">成交额：${inf.amount}万元</p>				
			</div>			
		</div>
										
		<div class="analysis-container">
			<p class="title graph-title">图表展现</p>
			<p class="result2 r2">没有选择指标是看不到结果的哟！</p>	
			<div id="analysis-graph"></div>			
		</div>
	
	</div>
	
	
	<div class="footer">
		<jsp:include page="/WEB-INF/jsp/main/foot.jsp"></jsp:include>
	</div>
	<script src="js/analysis/classie.js"></script>
	<script>
		(function() {
			if (!String.prototype.trim) {
				(function() {
					// Make sure we trim BOM and NBSP
					var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
					String.prototype.trim = function() {
						return this.replace(rtrim, '');
					};
				})();
			}

			[].slice.call( document.querySelectorAll( 'input.change-input' ) ).forEach( function( inputEl ) {
			// in case the input is already filled..
				if( inputEl.value.trim() !== '' ) {
					classie.add( inputEl.parentNode, 'input--filled' );
				}

				// events:
				inputEl.addEventListener( 'focus', onInputFocus );
				inputEl.addEventListener( 'blur', onInputBlur );
			} );

			function onInputFocus( ev ) {
				classie.add( ev.target.parentNode, 'input--filled' );
			}

			function onInputBlur( ev ) {
				if( ev.target.value.trim() === '' ) {
					classie.remove( ev.target.parentNode, 'input--filled' );
				}
			}
		})();
	</script>
</body>
</html>