var moveGetYear = [],
	moveGetJidu = [],
	moveGetMonth = [],
	ymdArr = ["", "", ""];
var chooseYearPicker = null,
	chooseJiduPicker = null,
	chooseMonthPicker = null;
var yearValue = "",
	jiduValue = "",
	monthValue = "",
	flag = "M",
	canshuYear = "",
	canshuSeason = "",
	canshuMonth = "";
var yearValueCom = "";
//getCurrentDate();
//加载年份、季度、月份选择器
function getCurrentDate(yearValueSelf) {
	moveGetMonth = [];
	var year = '';
	var month = "";
	/*
	 * 1.如果yearValueSelf==undifined表示第一次加载，显示当前年份实际季度和月份
	 * 2.否则表示已经选择过年份
	 *    如果月份为当前月份则同样显示实际季度和月份
	 *     否则月份置位12季度变为4季度
	 * */
	console.log(yearValueSelf)
	if(yearValueSelf == "undefined" || yearValueSelf == undefined || yearValueSelf == yearValueCom) {

		var now = new Date();
		year = now.getFullYear(); //年  
		month = now.getMonth() + 1; //月 
		for(var f = year - 9; f <= year; f++) {
			moveGetYear.push(f);
		}
		yearValueCom = year;
		if(yearValueSelf == yearValueCom) {
			flag = "Y";
		} else {
			flag = "M";
			$(".keshi-month-span").css({ "color": "#2aabd2", "border-bottom": "2px solid #2aabd2" });
		}

	} else {
		flag = "Y";
		year = yearValueSelf; //年  
		month = 12; //月 
	}

	for(var a = 1; a <= month; a++) {
		var monthSelf = a;
		moveGetMonth.push(monthSelf);
	}
	if(month < 4) {
		moveGetJidu = ['一季度'];
	} else if(month < 7) {
		moveGetJidu = ['一季度', '二季度'];
	} else if(month < 10) {
		moveGetJidu = ['一季度', '二季度', '三季度'];
	} else if(month < 13) {
		moveGetJidu = ['一季度', '二季度', '三季度', '四季度'];
	}
	$(".keshi-year-span").text(year + "年");
	$(".keshi-jidu-span").text(moveGetJidu[moveGetJidu.length - 1]);
	$(".keshi-month-span").text(month + "月");

	canshuYear = year;
	canshuSeason = moveGetJidu[moveGetJidu.length - 1];
	canshuMonth = month;
	loadView();
}
//年份、季度、月份切换
mui("body").on('tap', ".keshi-ymd-span", function() {
	$(".keshi-ymd-span").css({ "color": "#333", "border-bottom": "0px" })
	$(this).css({ "color": "#2aabd2", "border-bottom": "2px solid #2aabd2" })
});

//年份选择
mui("body").on('tap', ".year-button", function() {
	chooseYMD();
	chooseYear();
});

function chooseYear() {
	ymdArr = ["year", "", ""]
	chooseYearPicker = new mui.PopPicker();
	chooseYearPicker.setData(moveGetYear);
	chooseYearPicker.show(function(rs) {
		yearValue = rs;
		$(".keshi-year-span").text(yearValue[0] + "年");
		flag = "Y";
		canshuYear = yearValue[0];
		getCurrentDate(yearValue[0]);
	});
}

function hideYear() {
	chooseYearPicker.hide();
	chooseYearPicker.dispose();
}
//季度选择
mui("body").on('tap', ".jidu-button", function() {
	chooseYMD();
	chooseJidu();
});

function chooseJidu() {
	ymdArr = ["", "jidu", ""];
	chooseJiduPicker = new mui.PopPicker();
	chooseJiduPicker.setData(moveGetJidu);
	chooseJiduPicker.show(function(rs) {
		jiduValue = rs;
		$(".keshi-jidu-span").text(jiduValue[0]);
		console.log(moveGetJidu.indexOf(jiduValue[0]))
		flag = "S";

		canshuSeason = moveGetJidu.indexOf(jiduValue[0]) + 1;
		loadView();
	});
}

function hideJidu() {
	chooseJiduPicker.hide();
	chooseJiduPicker.dispose();
}
//月份选择
mui("body").on('tap', ".month-button", function() {
	chooseYMD();
	chooseMonth();
});

function chooseMonth() {
	ymdArr = ["", "", "month"];
	chooseMonthPicker = new mui.PopPicker();
	chooseMonthPicker.setData(moveGetMonth);
	chooseMonthPicker.show(function(rs) {
		monthValue = rs;
		$(".keshi-month-span").text(monthValue[0] + "月");
		flag = "M";
		canshuMonth = monthValue[0];
		loadView();
	});
}

function hideMonth() {
	chooseMonthPicker.hide();
	chooseMonthPicker.dispose();
}

//年份、季度、月份hide
function chooseYMD() {

	if(ymdArr[0] != "") {
		hideYear();
	} else if(ymdArr[1] != "") {
		hideJidu();
	} else if(ymdArr[2] != "") {
		hideMonth();
	}
}

document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
	/**
	 * 系统返回键监听
	 */
	document.addEventListener("backbutton", onBackKeyDown, false);
}

/**
 * 系统返回键方法
 * @return {[type]} [description]
 */
function onBackKeyDown() {
	history.back(-1);
}

function goback() {
	history.back(-1);
}