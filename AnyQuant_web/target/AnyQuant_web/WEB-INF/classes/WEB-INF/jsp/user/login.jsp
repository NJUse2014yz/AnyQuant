<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <title>Login</title>
        <meta name="description" content="Custom Login Form Styling with CSS3" />
        <meta name="keywords" content="css3, login, form, custom, input, submit, button, html5, placeholder" />
        <!-- 标题图标，必须放到网站的根目录下 -->
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="css/user/loginStyle.css" />
		<link rel="stylesheet" type="text/css" href="css/user/button3d.css" />
		<!--[if lte IE 7]><style>.main{display:none;} .support-note .note-ie{display:block;}</style><![endif]-->
		<style>
			
			body {
				background: #563c55 url(graphics/user/loginBackground.jpg) no-repeat center top;
				-webkit-background-size: cover;
				-moz-background-size: cover;
				background-size: cover;
			}
			.container > header h1,
			.container > header h2 {
				color: #fff;
				text-shadow: 0 1px 1px rgba(0,0,0,0.7);
			}
			
			a{
				 /* Width and position */
			    width: 270px;
			    margin: 5px;
			}
			
		</style>
    </head>
    <body>
        <div class="container">
		
			<header>
			
				<h1><strong>A</strong>ny <strong>Q</strong>uant</h1>
				<h2>Creative and modern system  with intelligent idea</h2>
				
				
			</header>
			
			<section class="main">
				<form class="form-3" action="/AnyQuant_web/login.action">
				    <p class="clearfix">
				        <label for="login">用户名</label>
				        
				        <c:if test="${userName==null}">
				        <input type="text" name="userName" id="name" placeholder="Username" required>
				        </c:if>
				        <!-- 用户名不为空时说明已经保存密码 -->
				        <c:if test="${userName!=null}">
				        <input type="text" name="userName" id="name" value="${userName}" required>
				        </c:if>
				    </p>
				    <p class="clearfix">
				        <label for="password">密码</label>
				        <c:if test="${password==null}">
				        	<input type="password" name="password" id="password" placeholder="Password" required> 
				    	</c:if>
				    	
				    	<c:if test="${password!=null}">
				    		<input type="password" name="password" id="password" value="${password}" required> 
				  
				    	</c:if>
				    	
				
				    	
				    </p>
				    

				    
				    <p class="clearfix">
				    	<c:if test="${userName==null}">
				    		<input type="checkbox" name="rememberMe" id="rememberMe">
				  
				    	</c:if>
				        
				        <c:if test="${userName!=null}">
				        	<input type="checkbox" name="rememberMe" id="rememberMe" checked="checked">
				        </c:if>
				        <label for="remember">记住我</label>
				    </p>
				    <p class="clearfix">
				        <input type="submit" value="登录">
				    </p>
				    <p></p>
				    <p class="clearfix"> 
				     	<a class="orange button" href="/AnyQuant_web/login.action">注册</a>
				    </p>
				    
				    <p class="clearfix"> 
				     	<a class="orange button" href="#">忘记密码</a>
				    </p>
				    
				    <c:if test="${loginResult=='fail'}">
				    	<p class="wrongPassword clearfix">密码错误</p>
				    </c:if>
				</form>
				​
			</section>
			
        </div>
        
        
    </body>
</html>