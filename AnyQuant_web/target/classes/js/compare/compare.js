/**
 * 适用于比较脚面的数据准备
 * @author zs
 */
function changeColor1(x){	
	if(x<0){
		$(".inf-updown").css("color","#80ff80");
		$(".inf-updown-per").css("color","#80ff80");
	}
	else{
		$(".inf-updown").css("color","#ee3640");
		$(".inf-updown-per").css("color","#ee3640");
	}
}
function changeColor2(x){	
	if(x<0){
		$(".inf-updown-2").css("color","#60ff60");
		$(".inf-updown-per-2").css("color","#50ff50");
	}
	else{
		$(".inf-updown-2").css("color","#ea2020");
		$(".inf-updown-per-2").css("color","#ea2020");
	}
}