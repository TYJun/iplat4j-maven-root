/**
 * 获取当前时间
 * @return {[string]} [YYYY-MM-DD hh:mm]
 */
function date_YMDHM() {
	var now = new Date();
	var year = now.getFullYear(); //年  
	var month = now.getMonth() + 1; //月  
	var day = now.getDate(); //日  
	var hh = now.getHours(); //时  
	var mm = now.getMinutes(); //分  
	var clock = year + "-";
	if(month < 10)
		clock += "0";
	clock += month + "-";
	if(day < 10)
		clock += "0";
	clock += day + " ";
	if(hh < 10)
		clock += "0";
	clock += hh + ":";
	if(mm < 10) clock += '0';
	clock += mm;
	return(clock);
}

/**
 * 获取当前日期
 * @return {[string]} [YYYY-MM-DD]
 */
function date_YMD() {
	var now = new Date();
	var year = now.getFullYear(); //年  
	var month = now.getMonth() + 1; //月  
	var day = now.getDate(); //日  
	var clock = year + "-";
	if(month < 10)
		clock += "0";
	clock += month + "-";
	if(day < 10)
		clock += "0";
	clock += day;
	return(clock);
}

/**
 * 获取当前月份
 * @return {[string]} [YYYY-MM]
 */
function date_YM() {
	var now = new Date();
	var year = now.getFullYear(); //年  
	var month = now.getMonth() + 1; //月  
	var clock = year + "-";
	if(month < 10)
		clock += "0";
	clock += month;
	return(clock);
}

/**
 * 获取当前日期的前后日期
 * @param  {[type]} separation [整数表示后separation天，负数表示前separation天]
 * @return {[type]}            [description]
 */
function dateBeforeOrAfter(separation) {
	var now = new Date();
	now.setDate(now.getDate() + separation);
	var year = now.getFullYear(); //年  
	var month = now.getMonth() + 1; //月  
	var day = now.getDate(); //日  
	var clock = year + "-";
	if(month < 10)
		clock += "0";
	clock += month + "-";
	if(day < 10)
		clock += "0";
	clock += day;
	return(clock);
}

/**
 * 根据日期获取星期几
 * @param  {[type]} dateString [时间格式YYYY-MM-DD]
 * @return {[type]}            [description]
 */
function getWeek(dateString) {
	var dateArray = dateString.split("-");
	var date = new Date(dateArray[0], parseInt(dateArray[1] - 1), dateArray[2]);
	return "星期" + "日一二三四五六".charAt(date.getDay());
};

/**
 * 消息显示
 * @param  {[string]} msg [需要显示的消息]
 */
function showMsg(msg) {
	$("#warn small").html(msg);
	$("#warn").fadeIn(500);
	setTimeout('$("#warn").fadeOut(500)', 3000);
}

/*
 * 根据毫秒值获取当前时间格式(2017-12-12 12:12:12)
 * */

function secondToTime(timeString) {
	var newTime = new Date(timeString);
	var year = newTime.getFullYear();
	var month = newTime.getMonth() + 1;
	var day = newTime.getDate(); //日  
	var hh = newTime.getHours(); //时  
	var mm = newTime.getMinutes(); //分  
	var ss = newTime.getSeconds(); //秒
	if(month < 10)
		month = "0" + month;
	if(day < 10)
		day = "0" + day;

	if(hh < 10)
		hh = "0" + hh;
	if(ss < 10)
		ss = "0" + ss;
	if(mm < 10)
		mm = "0" + mm;
	return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
}