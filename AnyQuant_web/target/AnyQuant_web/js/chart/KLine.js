
function paintKLine() {
	$
			.getJSON(
					'http://www.hcharts.cn/datas/jsonp.php?filename=aapl-ohlcv.json&callback=?',
					function(data) {

						// split the data set into ohlc and volume
						var ohlc = [], 
						volume = [],
						open=[],//todo
						close=[],
						high=[],
						dataLength = data.length,
						
						// set the allowed units for data grouping
						groupingUnits = [ [ 'week', // unit name
						[ 1 ] // allowed multiples
						], [ 'month', [ 1, 2, 3, 4, 6 ] ] ],

						i = 0;

						for (i; i < dataLength; i += 1) {
							ohlc.push([ data[i][0], // the date
							data[i][1], // open
							data[i][2], // high
							data[i][3], // low
							data[i][4] // close
							]);
							
							open.push([
							    data[i][0],
							    data[i][1]
							]);
							
							close.push([
							    data[i][0],
							    data[i][4]
							]);
							
							high.push([
							    data[i][0],
							    data[i][2]
							]);

							volume.push([ data[i][0], // the date
							data[i][5] // the volume
							]);
						}

						// create the chart
						var chart = new Highcharts.StockChart({
							chart : {
								renderTo : 'chart'
							},
							rangeSelector : {
								selected : 1
							},

							title : {
								text : 'k线图'
							},

							yAxis : [ {
								labels : {
									align : 'left',
									x : 1
								},
								height : '60%',
								lineWidth : 2
							}, {
								labels : {
									align : 'left',
									x : 1
								},

								top : '65%',
								height : '35%',
								offset : 0,
								lineWidth : 2
							} ],

							series : [ {
								type : 'candlestick',
								name : 'AAPL',
								data : ohlc,
								dataGrouping : {
									units : groupingUnits
								}
							}, {
								type : 'column',
								name : 'Volume',
								data : volume,
								yAxis : 1,
								dataGrouping : {
									units : groupingUnits
								}
							} ,{
								type:'spline',
								name:'open',
								data:open,
								dataGrouping : {
									units : groupingUnits
								}
							},{
								type:'spline',
								name:'close',
								data:close,
								dataGrouping : {
									units : groupingUnits
								}
				
							},{
								type:'spline',
								name:'high',
								data:high,
								dataGrouping : {
									units : groupingUnits
								}
							}]
						});
					});
}
