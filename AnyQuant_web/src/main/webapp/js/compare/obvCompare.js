
/**
 * id-对应的div,id1，id2为对比股票的ID
 */
function lineCompare(id,title,data1,data2,attr,stockName1,stockName2){
	var obv1 = [], obv2 = [];
	
	// create the chart
	var chart = new Highcharts.Chart({
		chart : {
			renderTo : id
		},credits:{
            enabled:false // 禁用版权信息
	       },title:{
	    	 text:title  
	       },

	       xAxis:{
//	        	labels:{
//	        		enabled:false
//	        	}
		       type: 'datetime',
		       dateTimeLabelFormats: { // don't display the dummy year
		           month: '%e %b'
		       }
	        },
		

		series : [  {
			type : 'spline',
			name : stockName1,
		}, {
			type : 'spline',
			name : stockName2,
		}]
	});
	

	i = 0;

	for (i; i < data1.length; i += 1) {
		var dateUTC = parseInt(getDateUTC(data1[i]["date"]));

		obv1.push([ dateUTC, parseFloat(data1[i][attr]) ]);

		obv2.push([ dateUTC, parseFloat(data2[i][attr]) ]);
	}

	chart.series[0].setData(obv1);
	chart.series[1].setData(obv2);
	
}

function lineCompare2(id,title,data1,data2,attr,stockName1,stockName2){
	var obv1 = [], obv2 = [];
	
	// create the chart
	var chart = new Highcharts.Chart({
		chart : {
			renderTo : id
		},credits:{
        enabled:false // 禁用版权信息
	       },title:{
	    	 text:title  
	       },

	       xAxis:{
	         categories : [ '第一天', '第二天', '第三天', '第四天', '第五天', '第六天', '第七天']
	        },
		

		series : [  {
			type : 'spline',
			name : stockName1,
		}, {
			type : 'spline',
			name : stockName2,
		}]
	});
	

	i = 0;

	for (i; i < data1.length; i += 1) {
		

		obv1.push(parseFloat(data1[i][attr]) );

		obv2.push(parseFloat(data2[i][attr]) );
	}

	chart.series[0].setData(obv1);
	chart.series[1].setData(obv2);
	
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

