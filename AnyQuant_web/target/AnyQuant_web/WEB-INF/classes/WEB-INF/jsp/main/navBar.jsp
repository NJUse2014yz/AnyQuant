<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>导航栏</title>
	<link href="css/main/navBar.css" type="text/css" rel="stylesheet"/>
	</head>
	
	<body>
		<div class="top">
			<img alt="top" src="graphics/main/top.png">
		</div>
		<div class="container">
			<ul class="menu">
				<li><a href="${pageContext.request.contextPath}">首页</a></li>
				<li><a href="#">股票数据</a>
					<ul class="submenu">
						<li><a href="/AnyQuant_web/stock.action">全部</a></li>
						<li><a href="/AnyQuant_web/grail_sh.action">上证指数</a></li>
						<li><a href="/AnyQuant_web/grail_sz.action">深证成指</a></li>
						<li><a href="#">沪深300</a></li>
						<li><a href="#">股票对比</a></li>
						<li><a href="#">我的股票</a></li>
					</ul>
				</li>
				<li class="active"><a href="#s2">策略模块</a>
					<ul class="submenu">
						<li><a href="#">推荐策略</a></li>
						<li><a href="#">我的策略</a></li>
					</ul>
				</li>
				<li><a href="#">行业统计</a>
					<ul class="submenu">
						<li><a href="#">行业分析</a></li>
					</ul>
				</li>
				<li><a href="#">个人空间</a>
					<ul class="submenu">
						<li><a href="#">我的股票</a></li>
						<li><a href="#">我的策略</a></li>
						<li><a href="#">我的信息</a></li>
					</ul>
				</li>
			</ul>	
		</div>
		<div id="login-area">
			<ul class="unlogin">
				<li class="header-signup">
                    <a href="#">注册</a>
                </li>	
				<li class="header-signin">
                    <a href="${pageContext.request.contextPath}/askForLogin.action">登录</a>
                </li>			
			</ul>
		</div>
	</body>
</html>


