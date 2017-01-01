/**
 * 用于定义收盘价折线图和成交量柱状图的组合图
 * @author zs
 */
/**
 * id-对应的div,id1，id2为对比股票的ID
 */
function paintComboLine(id,stockId1,stockId2,stockName1,stockName2){
	var close1 = [], close2 = [], volume1 = [], 
	volume2 = [];
	
	// set the allowed units for data grouping
	groupingUnits = [ [ 'week', // unit name
	[ 1 ] // allowed multiples
	], [ 'month', [ 1, 2, 3, 4, 6 ] ] ];
	
	// create the chart
	var chart = new Highcharts.StockChart({
		chart : {
			renderTo : id
		},rangeSelector : {
			buttons : [ {
				type : 'month',
				count : 3,
				text : '还原'
			}],
			selected : 0,
			inputEnabled : false
		},credits:{
            enabled:false // 禁用版权信息
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
		

		series : [  {
			type : 'column',
			name : stockName1+'成交量',
			data : volume1,
			yAxis : 1,
			dataGrouping : {
				units : groupingUnits
			}
		}, {
			type : 'column',
			name : stockName2+'成交量',
			data : volume2,
			yAxis : 1,
			dataGrouping : {
				units : groupingUnits
			}
		},{
			type : 'spline',
			name : stockName1+'收盘价',
			data : close1,
			dataGrouping : {
				units : groupingUnits
			}
		}, {
			type : 'spline',
			name : stockName2+'收盘价',
			data : close2,
			dataGrouping : {
				units : groupingUnits
			}

		}]
	});
	
	chart.showLoading("加载中。。。");
	
	//填充数据
	
	//第一支股票
	$.getJSON('/AnyQuant_web/getKLineData.action?id=' + stockId1,
			function(data) {

				// split the data set into ohlc and volume
				 dataLength = data.length,

				i = 0;

				for (i; i < dataLength; i += 1) {
					var dateUTC=parseInt(getDateUTC(data[i]["date"]));
					
					close1.push([ dateUTC,  parseFloat(data[i]["closePrice"]) ]);

					volume1.push([ dateUTC,  parseFloat(data[i]["volume"]) ]);
				}

				chart.series[0].setData(volume1);
				chart.series[2].setData(close1);
				getNext();
				
			});
	function getNext(){
		//第二支股票
		$.getJSON('/AnyQuant_web/getKLineData.action?id=' + stockId2,
				function(data2) {

					// split the data set into ohlc and volume
					 dataLength = data2.length,

					i = 0;

					for (i; i < dataLength; i += 1) {
						var dateUTC=parseInt(getDateUTC(data2[i]["date"]));
						
						close2.push([ dateUTC,  parseFloat(data2[i]["closePrice"]) ]);

						volume2.push([ dateUTC,  parseFloat(data2[i]["volume"]) ]);
					}

					chart.series[1].setData(volume2);
					chart.series[3].setData(close2);
					
					chart.hideLoading();
				});
	}
	}
	

/**
 * 转换日期utc格式
 * @param date 日期的原始格式 20141205
 * @returns
 */
function getDateUTC(date){
    var dArr = new Array();
    date+="";
    for(var hh=0;hh<3;hh++){
        var numb ;
        if(hh==0){
            numb = Number(date.slice(0,4));
        }
        else {
            numb= Number(date.slice((hh-1)*2+4,hh*2+4));
        };
        dArr.push(numb);
    }
    var dateUTC = Number(Date.UTC(dArr[0],dArr[1],dArr[2],0,0));//得出的UTC时间
    return dateUTC;
}

