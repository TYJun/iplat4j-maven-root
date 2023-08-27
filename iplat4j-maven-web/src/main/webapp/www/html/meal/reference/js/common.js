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
 * 获取当前日期的前后日期
 * @param  {[type]} separation [整数表示后separation天，负数表示前separation天]
 * @return {[type]}            [description]
 */
function dateBeforeOrAfter(separation){
	var now = new Date();
	now.setDate(now.getDate() + separation);
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
function showMsg(msg){
	$("#warn small").html(msg);
	$("#warn").fadeIn(500);
	setTimeout('$("#warn").fadeOut(500)',3000);
}

/**
 * 格式化日期字符串
 * @param  {[type]} fmt [description]
 * @return {[type]}     [description]
 */
function dateFormat(fmt){
	return new Date(Date.parse(dateStr.replace(/-/g,   "/")));
}

/**
 * 字符串日期前后日期
 * @param  {[type]} dateStr    [日期字符串]
 * @param  {[type]} separation [前后日期差]
 * @return {[type]}            [description]
 */
function dateStrBeforeOrAfter(dateStr,separation){
	var now = new Date(Date.parse(dateStr.replace(/-/g,"/")));
	now.setDate(now.getDate() + separation);
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








