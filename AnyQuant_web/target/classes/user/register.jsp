<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<style type="text/css">
#wizard {border:5px solid #789;font-size:12px;height:450px;margin:20px auto; 
width:570px;overflow:hidden;position:relative;} 
#wizard .items{width:20000px; clear:both; position:absolute;} 
#wizard .right{float:right;} 
#wizard #status{height:35px;background:#123;padding-left:25px !important;} 
#status li{float:left;color:#fff;padding:10px 30px;} 
#status li.active{background-color:#369;font-weight:normal;} 
.input{width:240px; height:18px; margin:10px auto; line-height:20px;  
border:1px solid #d3d3d3; padding:2px} 
.page{padding:20px 30px;width:500px;float:left;} 
.page h3{height:42px; font-size:16px; border-bottom:1px dotted #ccc; margin-bottom:20px; 
 padding-bottom:5px} 
.page h3 em{font-size:12px; font-weight:500; font-style:normal} 
.page p{line-height:24px;} 
.page p label{font-size:14px; display:block;} 
.btn_nav{height:36px; line-height:36px; margin:20px auto;} 
.prev,.next{width:100px; height:32px; line-height:32px; background:url(btn_bg.gif)  
repeat-x bottom; border:1px solid #d3d3d3; cursor:pointer}
</style>
<script type="text/javascript" src="js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/user/scrollable.js"></script>
</head>
</head>
<body>



<div id="main">
  
   <form action="${pageContext.request.contextPath}/register.action" method="post">
	<div id="wizard">
		<ul id="status">
			<li class="active"><strong>1.</strong>创建账户</li>
			<li><strong>2.</strong>填写联系信息</li>
			<li><strong>3.</strong>完成</li>
		</ul>

		<div class="items">
			<div class="page">
               <h3>创建一个账户<br/><em>请填写您的注册账户信息，用于登录。</em></h3>
               <p><label>用户名：</label><input type="text" class="input" id="user" name="userName" /></p>
               <p><label>密码：</label><input type="password" class="input" id="pass" name="password" /></p>
               <p><label>确认密码：</label><input type="password" class="input" id="pass1" name="password1" /></p>
               <div class="btn_nav">
                  <input type="button" class="next right" value="下一步&raquo;" />
               </div>
            </div>
			<div class="page">
               <h3>填写联系信息<br/><em>请告诉我们您的联系方式。</em></h3>
               <p><label>E-mail：</label><input type="text" class="input" name="email" /></p>
               <p><label>QQ：</label><input type="text" class="input" name="qq" /></p>
               <p><label>手机号码：</label><input type="text" class="input" name="mobile" /></p>
               <div class="btn_nav">
                  <input type="button" class="prev" style="float:left" value="&laquo;上一步" />
                  <input type="button" class="next right" value="下一步&raquo;" />
               </div>
            </div>
			<div class="page">
               <h3>完成注册<br/><em>点击确定完成注册。</em></h3>
               <h4>AnyQuant欢迎您！</h4>
               <p>请点击“确定”按钮完成注册。</p>
               <br/>
               <br/>
               <br/>
               <div class="btn_nav">
                  <input type="button" class="prev" style="float:left" value="&laquo;上一步" />
                  <input type="button" class="next right" id="sub" value="确定" />
               </div>
            </div>
		</div>
	</div>
</form><br />
<br />
<br />

</div>

<script type="text/javascript">
$(function(){
	$("#wizard").scrollable({
		onSeek: function(event,i){
			$("#status li").removeClass("active").eq(i).addClass("active");
		},
		onBeforeSeek:function(event,i){
			if(i==1){
				var user = $("#user").val();
				if(user==""){
					alert("请输入用户名！");
					return false;
				}
				var pass = $("#pass").val();
				var pass1 = $("#pass1").val();
				if(pass==""){
				    alert("请输入密码！");
					return false;
				}
				if(pass1 != pass){
				    alert("两次密码不一致！");
					return false;
				}
			}
		}
	});
	$("#sub").click(function(){
	    var data = $("form").serialize();
		window.location.href="${pageContext.request.contextPath}/register.action?"+data;
	});
});
</script>

<p id="stat"><script type="text/javascript" src="http://js.tongji.linezing.com/1870888/tongji.js"></script></p>

</body>
</html>