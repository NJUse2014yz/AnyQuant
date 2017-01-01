<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<link href="css/analysis/jq22.css" type="text/css" rel="stylesheet" />

<title>添加股票</title>
</head>
<body>

<div class="demo" class="col-md-12 ">
	
	<div class="plus-tag tagbtn clearfix" id="myTags"></div>

	<div class="plus-tag-add">
		<form id="" action="" class="login">
			<ul class="Form FancyForm">
				<li>
					<span class="label">股票id</span>
					<input id="" name="" type="text" class="stext" maxlength="20" required/>
					<label>id</label>
					<span class="fff"></span>
				</li>

				<li>
					<span class="label">股票比例</span>
					<input id="" name="" type="text" class="stext" maxlength="20" required/>
					<label>比例（x%）</label>
					<span class="fff"></span>
				</li>
				<li>
					<button type="button" class="Button RedButton Button18" style="font-size:22px;">添加股票</button>
					<button type="button" class="Button RedButton Button18" style="font-size:22px;">开始分析</button>
					<a href="javascript:void(0);">推荐</a>

				</li>
			</ul>
		</form>
	</div><!--plus-tag-add end-->
	
	<div id="mycard-plus" style="display:none;">
		<div class="default-tag tagbtn">
			<div class="clearfix">
				<a value="-1" title="sh600000 20%" href="javascript:void(0);"><span>sh600000 20%</span><em></em></a>
				<a value="-1" title="sh600000 50%" href="javascript:void(0);"><span>sh600000 50%</span><em></em></a>
				<a value="-1" title="sh600000 70%" href="javascript:void(0);"><span>sh600000 70%</span><em></em></a>
				<a value="-1" title="sh600004 20%" href="javascript:void(0);"><span>sh600004 20%</span><em></em></a>
				<a value="-1" title="sh600004 50%" href="javascript:void(0);"><span>sh600004 50%</span><em></em></a>
				<a value="-1" title="sh600008 20%" href="javascript:void(0);"><span>sh600008 20%</span><em></em></a>
				<a value="-1" title="sh600008 50%" href="javascript:void(0);"><span>sh600008 50%</span><em></em></a>
				<a value="-1" title="sh600008 70%" href="javascript:void(0);"><span>sh600008 70%</span><em></em></a>
				<a value="-1" title="sz000001 20%" href="javascript:void(0);"><span>sz000001 20%</span><em></em></a>
				<a value="-1" title="sz000001 50%" href="javascript:void(0);"><span>sz000001 50%</span><em></em></a>
				<a value="-1" title="sz000001 70%" href="javascript:void(0);"><span>sz000001 70%</span><em></em></a>
				<a value="-1" title="sz000002 20%" href="javascript:void(0);"><span>sz000002 20%</span><em></em></a>
				<a value="-1" title="sz000002 50%" href="javascript:void(0);"><span>sz000002 50%</span><em></em></a>
				<a value="-1" title="sz000002 70%" href="javascript:void(0);"><span>sz000002 70%</span><em></em></a>
				<a value="-1" title="sz000002 20%" href="javascript:void(0);"><span>sz000002 20%</span><em></em></a>
				<a value="-1" title="sz000002 50%" href="javascript:void(0);"><span>sz000002 50%</span><em></em></a>
				<a value="-1" title="sz000002 70%" href="javascript:void(0);"><span>sz000002 70%</span><em></em></a>
				                  
			</div>
			
		
	</div><!--mycard-plus end-->
		
</div>
</div>
<script type="text/javascript" src="js/analysis/jq22.js"></script>


</body>
</html>