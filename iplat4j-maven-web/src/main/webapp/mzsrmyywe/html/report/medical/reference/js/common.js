// import Menu from './Menu.js';
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

/**
 * 根据用户工号、院区编号，获取科室集合 //2021-12-15新增功能
 */
 function getDeptList() {
	$.ajax({
		url: baseUrl + 'MobileAgentService',
		type: 'post',
		headers: {
			methodName: "getUserDepts",
			serviceName: "AUFW01",
		},
		data: {
			prames: JSON.stringify({
				workNo: localStorage.getItem("workNo"),//用户工号
				datagroupCode: localStorage.getItem("dataGroupCode")//院区编号
			})

		},
		success: function (data) {
			if (data.status == "0") {
        initlist = data.attr.result;
        initSelect();
			} else {
				mui.toast("获取数据失败");
			}
		}
	});
}




