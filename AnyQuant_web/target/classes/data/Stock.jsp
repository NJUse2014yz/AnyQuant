<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		.pageTest {
			position:relative;
			width: 600px;
			height: 50px;
			left:230px;
			top:5px;
		}
		
		.activP {
			background-color: #367fa9 !important;
			color: #fff !important;
		}
		
		.sb-icon-search:before {
			content: url(http://localhost:8081/AnyQuant_web/graphics/data/search.gif)!important;
		}
		
		.bordered tr:hover {
		    background: #fbf8e9;
		    -o-transition: all 0.1s ease-in-out;
		    -webkit-transition: all 0.1s ease-in-out;
		    -moz-transition: all 0.1s ease-in-out;
		    -ms-transition: all 0.1s ease-in-out;
		    transition: all 0.1s ease-in-out;     
		} 
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>全部股票信息</title>
	<link href="css/main/main.css" type="text/css" rel="stylesheet"/>
	<link href="css/data/stock.css" type="text/css" rel="stylesheet"/>
	<link href="css/data/newInf.css" type="text/css" rel="stylesheet"/>
	<link href="css/main/name.css" type="text/css" rel="stylesheet"/>
	<link href="css/main/submenu.css" type="text/css" rel="stylesheet"/>
	<link href="css/table.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="css/main/page.css">
	<link rel="stylesheet" type="text/css" href="css/stock/searchComponent/default.css" />
	<link rel="stylesheet" type="text/css" href="css/stock/searchComponent/component.css" />
	<link rel="stylesheet" type="text/css" href="css/user/button3d.css">
	<link rel="stylesheet" type="text/css" href="css/stock/toplist.css" />
	<link rel="stylesheet" type="text/css" href="css/stock/startend.css" />
	<script type="text/javascript" src="js/jquery-1.12.3.js"></script>
	<script src="js/searchComponent/modernizr.custom.js"></script>
  	<script type="text/javascript" src="js/tool/table.js"></script>
</head>
<body>
	
	<div class="head">
		<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
	</div>
	<div class="bodycontent">
		<div class="body-top">
			<h1 class="name">全部股票</h1>
			<h3 class="words">股票有风险，投资需谨慎！</h3>
		</div>
		
		<div class="ss-top">
			<div class="submenu-name">
				<div class="shape"></div>
				<p class="ss-name">股票龙虎榜</p>
			</div>
			<section id="ranking"> <span id="ranking_title">龙虎榜Top10</span>
				<section id="ranking_list">
					<c:forEach var="i" begin="0" end="${toplen}">   
				    	<section class="box b1">
				      		<section class="col_1" title="${i+1}">${i+1}</section>
					      	<section class="col_2">${toplist.get(i).name}</section>
					      	<section class="col_3">${toplist[i].stockId}</section>
					      	<section class="col_4">上榜理由：${toplist[i].reason}</section>
				    	</section>
				    </c:forEach>			    
				</section>
			</section>
		</div>
		
		<div class="indices">
			<div class="submenu-name">
				<div class="shape"></div>
				<p class="ss-name">指数行情</p>
			</div>
			<ul class="inf-ul">
				<li class="inf1-li">
					<div class="inf-container" onclick="requiryStock('${list1.get(0).stockId}')">
						<a href="/AnyQuant_web/stockInfo.action?id=${list1.get(0).stockId}" class="inf-name">${list1.get(0).name}</a>
						<p class="inf-close">${list1.get(0).close}</p>
						<p class="inf-updown-per">${list1.get(0).increase} ${list1.get(0).incrPer}%</p>
						<p class="inf-vol">成交量：${list1.get(0).volume}手</p>
						<p class="inf-vol-n">成交额：${list1.get(0).amount}万元</p>
						<ul class="inf-updown">
							<li class="inf-up-num">最高：${list1.get(0).high}</li>
							<li class="inf-same-num">最低：${list1.get(0).low}</li>
						</ul>
					</div>
					<ul class="inf-indice-list">
						<li class="indice-menu">
							<ul class="indice-inf">
								<li class="col-name">指数名称</li>
								<li class="col-id">指数ID</li>
								<li class="col-close">收盘价</li>
								<li class="col-per">涨跌幅</li>
							</ul>
						</li>
						
						<c:forEach var="i" begin="1" end="${len1}"> 
							<li class="indice">
								<ul class="indice-inf" onclick="requiryStock('${list1.get(i).stockId}')">
									<li class="col-name">${list1.get(i).name}</li>
									<li class="col-id">${list1.get(i).stockId}</li>
									<li class="col-close">${list1.get(i).close}</li>
									<li class="col-per">${list1.get(i).incrPer}%</li>
								</ul>
							</li>
						</c:forEach>	
					</ul>
				</li>
				<li class="inf-li-market">
					<p class="market-sh">沪<br>市</p>
					<p class="market-sz">深<br>市</p>
				</li>
				<li class="inf2-li">
					<div class="inf-container" onclick="requiryStock('${list2.get(i).stockId}')">
						<a href="/AnyQuant_web/stockInfo.action?id={list2.get(0).stockId}" class="inf-name">${list2.get(0).name}</a>
						<p class="inf-close">${list2.get(0).close}</p>
						<p class="inf-updown-per">${list2.get(0).increase} ${list1.get(0).incrPer}%</p>
						<p class="inf-vol">成交量：${list2.get(0).volume}手</p>
						<p class="inf-vol-n">成交额：${list2.get(0).amount}万元</p>
						<ul class="inf-updown">
							<li class="inf-up-num">最高：${list2.get(0).high}</li>
							<li class="inf-same-num">最低：${list2.get(0).low}</li>
						</ul>
					</div>
					<ul class="inf-indice-list">
						<li class="indice-menu">
							<ul class="indice-inf" >
								<li class="col-name">指数名称</li>
								<li class="col-id">指数ID</li>
								<li class="col-close">收盘价</li>
								<li class="col-per">涨跌幅</li>
							</ul>
						</li>
						
						<c:forEach var="i" begin="1" end="${len2}"> 
							<li class="indice">
								<ul class="indice-inf" onclick="requiryStock('${list2.get(i).stockId}')">
									<li class="col-name">${list2.get(i).name}</li>
									<li class="col-id">${list2.get(i).stockId}</li>
									<li class="col-close">${list2.get(i).close}</li>
									<li class="col-per">${list2.get(i).incrPer}%</li>
								</ul>
							</li>
						</c:forEach>	
					</ul>
				</li>
			</ul>
		</div>
		
		
		<div class="grail-industry">
			<div class="submenu-name">
				<div class="shape"></div>
				<p class="ss-name">市场更迭</p>
			</div>
			<div class="start-end">
				<div class="start">
					<div class="start-title">[新股上市]</div>
					<div class="start-cntainer">
						<c:choose>
							<c:when test="${startlist.size()>0}">	
								<ul class="start-list">
									<li class="start-menu">
										<ul class="start-inf" >
											<li class="start-name">股票名称</li>
											<li class="start-code">股票ID</li>
											<li class="start-ipo_date">上网发行日期</li>
											<li class="start-amount">发行量</li>
											<li class="start-price">发行价格</li>
											<li class="start-ballot">网上中签率</li>
										</ul>
									</li>
									<c:forEach var="i" begin="0" end="${startlist.size()-1}">
										<li class="start-li">
											<ul class="start-inf">
												<li class="start-name">${startlist.get(i).name}</li>
												<li class="start-code">${startlist.get(i).code}</li>
												<li class="start-ipo_date">${startlist.get(i).ipo_date}</li>
												<li class="start-amount">${startlist.get(i).amount}</li>
												<li class="start-price">${startlist.get(i).price}</li>
												<li class="start-ballot">${startlist.get(i).ballot}</li>
											</ul>
										</li>
									</c:forEach>						
								</ul>
							</c:when>
							<c:otherwise>
								<p class="startmessage">暂时没有新上市的股票哟!</p>
							</c:otherwise>	
						</c:choose>						
	  				</div>
	  			</div>
		
				<div class="end">
					<div class="end-title">[停牌提示]</div>
					<div class="end-container">
						<c:choose>
							<c:when test="${endlist.size()>0}">
								<ul class="end-list">
									<li class="end-menu">
										<ul class="end-inf" >
											<li class="end-name">股票名称</li>
											<li class="end-code">股票ID</li>
											<li class="end-oDate">上市日期</li>
											<li class="end-tDate">暂停上市日期</li>
										</ul>
									</li>
									<c:forEach var="i" begin="0" end="${endlist.size()-1}"> 
										<li class="end-li">
											<%-- <ul class="end-inf" onclick="requiryStock('${endlist.get(i).code}')">
												<li class="end-name">${endlist.get(i).name}</li>
												<li class="end-code">${endlist.get(i).code}</li>
												<li class="end-oDate">${endlist.get(i).oDate}</li>
												<li class="end-tDate">${endlist.get(i).tDate}</li>
											</ul> --%>
										</li>
									</c:forEach>	
								</ul>
							</c:when>
							<c:otherwise>
								<p class="endmessage">当天没有新停牌的股票哟!</p>
							</c:otherwise>
	  					</c:choose>
	  				</div>
				</div>
			</div>
		</div>
		
		<div class="stockall-table">
			<div class="submenu-name">
				<div class="shape"></div>
				<p class="ss-name">股票列表</p>
			</div>
				
			<div class="stockListTable">
				<table id="stockList" border="1" class="bordered">
					<tr>
						<th>id</th>
						<th>开盘价</th>
						<th>收盘价</th>
						<th>涨跌额</th>
						<th>涨跌幅</th>
						<th>最低价</th>
						<th>最高价</th>
						<th>成交量</th>
						<th>成交额</th>
						<th>换手率</th>
					</tr>
					
				</table>
				<div class="pageTest"></div> 
				<!-- 退出搜索 -->
				<div id="return" class="button orange" 
				style="position:relative;left:45%;top:5px;">返回</div>
				<!-- 退出搜索 -->				 
			</div>			
		</div>		  			    
	</div>
	
	<!-- 搜索框 -->	
	<div class="column" style="left:970px;top:1220px;width:300px;">
		<div id="sb-search" class="sb-search sb-search-open" style='min-width:40px'>
			<form>
				<input class="sb-search-input" placeholder="请输入股票代码" type="text" value="" name="search" id="search">
				<input class="sb-search-submit" style='width:40px' value="">
				<span class="sb-icon-search" style='width:40px'></span>
			</form>
		</div>
	</div>
	<!-- 搜索框 -->
	
	<script type="text/javascript" src="js/compo/page.js"></script>
	<script type="text/javascript">
		var tableAttrs=['stockId', 'open', 'close','increase', 'incrPer',
		    			'low', 'high', 'volume', 'amount', 'turnover'];
		//记录当前页号
		var currentPage=1;
		
		//翻页
		$('.pageTest').page({
		     leng: 290,//分页总数
		     activeClass: 'activP' , //active 类样式定义
		});
		
		
		
		
		$(function(){
			stockListTable(currentPage);
			$("#return").hide();
		});
		
		
		
		//点击选页按钮触发的事件
		function choosePageNumber(a){
			currentPage=$(a).text();
			stockListTable(currentPage);
		}
		
		function lastPage(){
			currentPage=290;
			stockListTable(currentPage);
		}
		
		//刷新列表数据
		function stockListTable(pageNumber){
			//异步查詢
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/getStockList.action',
				data:'pageNumber='+pageNumber,
				success:function(data){
					
					table(data,tableAttrs,'stockList');
					selectStockListener(data,'stockId',"stockList");
					$(".pageTest").show();
				}
			});
		} 
		
		//监听搜索框
		$('.sb-search-submit').click(function(){
			var key=$('.sb-search-input').val();//获得输入
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/searchStock.action',
				data:'key='+key,
				success:function(data){
					$(".pageTest").hide();
					$("div#return").show();
					var copy=[];
					//最多显示十个
					for(var i=0;i<10&&i<data.length;i++){
						copy.push(data[i]);
					}
					table(copy,tableAttrs,'stockList');
					selectStockListener(data,'stockId',"stockList");
				}
			});
		});
		
		//退出搜索模式
		$("div#return").click(function(){
			$("div#return").hide();
			stockListTable(currentPage);
			
		});
	
	</script>
	<div class="footer">
		<jsp:include page="/WEB-INF/jsp/main/foot.jsp"></jsp:include>
	</div>
	
 	<script src="js/searchComponent/classie.js"></script> 
 	<script src="js/searchComponent/uisearch.js"></script>
	<script>
 		new UISearch( document.getElementById('sb-search'));
 	</script>  
</body>
</html>