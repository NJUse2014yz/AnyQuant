<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>模块推荐</title>
    <link rel="stylesheet" type="text/css" href="css/recommend/demo.css" />
	<link rel="stylesheet" type="text/css" href="css/recommend/slicebox.css" />
	<link rel="stylesheet" type="text/css" href="css/recommend/custom.css" />
	<link rel="stylesheet" type="text/css" href="css/recommend/init.css">
	<script type="text/javascript" src="js/recommend/modernizr.custom.46884.js"></script>
</head>
<body>
<div class="recom-container">			
	<div class="wrapper">
		<ul id="sb-slider" class="sb-slider">
			<li>
				<a href="${newsList[0].url}" target="_blank"><img src="graphics/main/modelback1.jpg" alt="image1"/></a>
				<div class="sb-description">
					<h3>${newsList[0].title}</h3>
				</div>
			</li>
			<li>
				<a href="${newsList[1].url}" target="_blank"><img src="graphics/main/modelback2.jpg" alt="image2"/></a>
				<div class="sb-description">
					<h3>${newsList[1].title}</h3>
				</div>
			</li>
			<li>
				<a href="${newsList[2].url}" target="_blank"><img src="graphics/main/modelback3.jpg" alt="image1"/></a>
				<div class="sb-description">
					<h3>${newsList[2].title}</h3>
				</div>
			</li>
			<li>
				<a href="${newsList[3].url}" target="_blank"><img src="graphics/main/modelback4.jpg" alt="image1"/></a>
				<div class="sb-description">
					<h3>${newsList[3].title}</h3>
				</div>
			</li>
		</ul>

		<div id="shadow" class="shadow"></div>
			<div id="nav-arrows" class="nav-arrows">
				<a id='nextStrategy' href="#">Next</a>
				<a id='lastStrategy' href="#">Previous</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/recommend/jquery.slicebox.js"></script>
	<script type="text/javascript">
		$(function() {
			var Page = (function() {
				var $navArrows = $( '#nav-arrows' ).hide(),
				$shadow = $( '#shadow' ).hide(),
				slicebox = $( '#sb-slider' ).slicebox( {
					onReady : function() {
						$navArrows.show();
						$shadow.show();
					},
					orientation : 'r',
					cuboidsRandom : true,
					disperseFactor : 30
				} ),
				init = function() {
					initEvents();
				},
				initEvents = function() {
					// add navigation events
					$navArrows.children( ':first' ).on( 'click', function() {
						slicebox.next();
						return false;
					} );
					$navArrows.children( ':last' ).on( 'click', function() {
						slicebox.previous();
						return false;
					} );
				};
				return { init : init };
			})();
			Page.init();
			
		});
	</script>		
</body>
</html>