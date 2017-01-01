<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的股票</title>

<link href="css/main/main.css" type="text/css" rel="stylesheet"/>
<link href="css/table.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="css/main/page.css">
<link rel="stylesheet" type="text/css" href="css/user/personalStock.css">

<script src="js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/tool/table.js"></script>
</head>
<body>
	<div class="head">
		<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
	</div>

	<div id="myStockList">
		<table id="myStockList" border="1" class="bordered">
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
	
	</div>
	
	
	<script type="text/javascript">
		$(function(){
			
		});
	</script>
	
	<div class="footer">
		<jsp:include page="/WEB-INF/jsp/main/foot.jsp"></jsp:include>
	</div>
</body>
</html>