<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/main/main.css" type="text/css" rel="stylesheet"/>
<link href="css/data/stock.css" type="text/css" rel="stylesheet"/>
<link href="css/industry/industry.css" type="text/css" rel="stylesheet"/>
<link href="css/table.css" type="text/css" rel="stylesheet"/>
<link href="css/main/submenu.css" type="text/css" rel="stylesheet"/>
<link href="css/compare/modelLine.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript" src='js/compare/modelLine.js'></script>
<script type="text/javascript" src="js/jquery-1.12.3.js"></script>
<script type="text/javascript" src="js/Highcharts-4.2.5/js/highcharts.js"></script>
<script type="text/javascript" src="js/tool/table.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>行业分析</title>
</head>
<body>
	<div class="head">
		<jsp:include page="/WEB-INF/jsp/main/navBar.jsp"></jsp:include>
	</div>
	<!-- 标题 -->>
	<div class="pieChartHead">
		<div class="submenu-name">
			<div class="shape"></div>
			<p class="ss-name">板块概况</p>
		
		</div>
		
	</div>
	<!-- 饼图 -->
	<div class="pieChartContainer">
		<div  class="industry corner">
			<div id='industry' class="smallChart"></div>
		</div>
		<div class="area corner">
			<div id='area' class="smallChart"></div>
		</div>
		<div id='concept' class="concept corner"></div>
	</div>
	
	
	<!-- 行业排行榜 -->
	<div class="industryRank">
		<div class="submenu-name rankHead">
			<div class="shape"></div>
			<p class="ss-name">行业推荐系数</p>
		</div>
		
		<div class="rankBar corner">
			<div class="rankContainer">
				<div class="skillbars">
					<div class="skillbar clearfix " data-percent="30%">
						<div class="skillbar-title" style="background: #d35400;"><span></span></div>
						<div class="skillbar-bar" style="background: #e67e22;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="25%">
						<div class="skillbar-title" style="background: #2980b9;"><span></span></div>
						<div class="skillbar-bar" style="background: #3498db;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="50%">
						<div class="skillbar-title" style="background: #2c3e50;"><span></span></div>
						<div class="skillbar-bar" style="background: #2c3e50;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="40%">
						<div class="skillbar-title" style="background: #46465e;"><span></span></div>
						<div class="skillbar-bar" style="background: #5a68a5;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="75%">
						<div class="skillbar-title" style="background: #333333;"><span></span></div>
						<div class="skillbar-bar" style="background: #525252;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="100%">
						<div class="skillbar-title" style="background: #27ae60;"><span></span></div>
						<div class="skillbar-bar" style="background: #2ecc71;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="70%">
						<div class="skillbar-title" style="background: #124e8c;"><span></span></div>
						<div class="skillbar-bar" style="background: #4288d0;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
				</div>	
			</div>
		</div>
	
	</div>
	
	<!-- 行业排行榜 -->
	
	
	<!-- 地域排行榜 -->
	<div class="areaRank">
		<div class="submenu-name rankHead">
			<div class="shape"></div>
			<p class="ss-name">地域推荐系数</p>
		</div>
		
		<div class="rankBar corner">
			<div class="rankContainer">
				<div class="skillbars">
					<div class="skillbar clearfix " data-percent="30%">
						<div class="skillbar-title" style="background: #d35400;"><span></span></div>
						<div class="skillbar-bar" style="background: #e67e22;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="25%">
						<div class="skillbar-title" style="background: #2980b9;"><span></span></div>
						<div class="skillbar-bar" style="background: #3498db;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="50%">
						<div class="skillbar-title" style="background: #2c3e50;"><span></span></div>
						<div class="skillbar-bar" style="background: #2c3e50;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="40%">
						<div class="skillbar-title" style="background: #46465e;"><span></span></div>
						<div class="skillbar-bar" style="background: #5a68a5;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="75%">
						<div class="skillbar-title" style="background: #333333;"><span></span></div>
						<div class="skillbar-bar" style="background: #525252;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="100%">
						<div class="skillbar-title" style="background: #27ae60;"><span></span></div>
						<div class="skillbar-bar" style="background: #2ecc71;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="70%">
						<div class="skillbar-title" style="background: #124e8c;"><span></span></div>
						<div class="skillbar-bar" style="background: #4288d0;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
				</div>	
			</div>
		</div>
	
	</div>
	<!-- 地域排行榜 -->
	
	<!-- 概念排行榜 -->
	<div class="conceptRank">
		<div class="submenu-name rankHead">
			<div class="shape"></div>
			<p class="ss-name">概念推荐系数</p>
		</div>
		
		<div class="rankBar corner">
			<div class="rankContainer">
				<div class="skillbars">
					<div class="skillbar clearfix " data-percent="30%">
						<div class="skillbar-title" style="background: #d35400;"><span></span></div>
						<div class="skillbar-bar" style="background: #e67e22;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="25%">
						<div class="skillbar-title" style="background: #2980b9;"><span></span></div>
						<div class="skillbar-bar" style="background: #3498db;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="50%">
						<div class="skillbar-title" style="background: #2c3e50;"><span></span></div>
						<div class="skillbar-bar" style="background: #2c3e50;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="40%">
						<div class="skillbar-title" style="background: #46465e;"><span></span></div>
						<div class="skillbar-bar" style="background: #5a68a5;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="75%">
						<div class="skillbar-title" style="background: #333333;"><span></span></div>
						<div class="skillbar-bar" style="background: #525252;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="100%">
						<div class="skillbar-title" style="background: #27ae60;"><span></span></div>
						<div class="skillbar-bar" style="background: #2ecc71;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
					
					<div class="skillbar clearfix " data-percent="70%">
						<div class="skillbar-title" style="background: #124e8c;"><span></span></div>
						<div class="skillbar-bar" style="background: #4288d0;"></div>
						<div class="skill-bar-percent"></div>
					</div> <!-- End Skill Bar -->
				</div>	
			</div>
		</div>
	
	</div>
	<!-- 概念排行榜 -->
	
	
	
		
	<script type="text/javascript" src="js/chart/Pie.js"></script>
	
	<script type="text/javascript">
		
		
		$(function(){
			
			rank("主要行业成交额分布", 'industry', 'getIndustryRank','.industryRank');
			rank("主要地域成交额分布", 'area', 'getAreaRank','.areaRank');
			rank("主要概念成交额分布", 'concept', 'getConceptRank','.conceptRank');
			
		});
		
		function rank(pieChartName,pieChartId,action,container){
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/'+action+'.action',
				data:'',
				success:function(data){
					pieChart(pieChartName,data,pieChartId,'cname','sumAmount');
					for(var i=0;i<7;i++){
						var percent=data[i]["index"]/30+"%";
						$(container+" .skillbars > div").eq(i).attr("data-percent",percent);
						$(container+" .skillbars > div .skill-bar-percent").eq(i).text(data[i]["index"]);
						$(container+" .skillbars > div span").eq(i).text(data[i]["cname"]);
					}
					
					$(container+' .skillbar').each(function(){
						$(this).find('.skillbar-bar').animate({
						width:$(this).attr('data-percent')
						},3000);
					});
					
				}
			});
			
		}
		
		function initTable(attrs){
			
			table(data,attrs,"increaseRank");
			selectStockListener(data,'a',"increaseRank");
			
				
		}
		
		
	</script>
	
	<div class="footer">
		<jsp:include page="/WEB-INF/jsp/main/foot.jsp"></jsp:include>
	</div>

</body>
</html>