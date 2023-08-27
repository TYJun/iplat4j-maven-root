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
	if (month < 10)
		clock += "0";
	clock += month + "-";
	if (day < 10)
		clock += "0";
	clock += day + " ";
	if (hh < 10)
		clock += "0";
	clock += hh + ":";
	if (mm < 10) clock += '0';
	clock += mm;
	return (clock);
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
	if (month < 10)
		clock += "0";
	clock += month + "-";
	if (day < 10)
		clock += "0";
	clock += day;
	return (clock);
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
	if (month < 10)
		clock += "0";
	clock += month;
	return (clock);
}

/**
 * 消息显示
 * @param  {[string]} msg [需要显示的消息]
 */
function showMsg(msg){
	$("#warn small").html(msg);
	$("#warn").fadeIn(500);
	setTimeout('$("#warn").fadeOut(500)',3000);
}








