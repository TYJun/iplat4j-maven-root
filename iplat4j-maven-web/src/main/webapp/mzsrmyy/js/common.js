/**
 * 消息展示,需要引用messenger相关文件
 * @type {Object}
 */
Messenger.options = {
	extraClasses: 'messenger-fixed messenger-theme-air messenger-on-bottom messenger-on-right',
	theme: 'flat'
}

/**
 * 显示消息
 * @param  {[type]} msg   [消息内容]
 * @param  {[type]} type  [消息类型]
 * info:蓝色 error:红色 success:绿色 
 * @param  {[type]} delay [时间]
 * @return {[type]}       [description]
 */
function showMsg(msg, type, delay) {
	Messenger().post({
		message: msg,
		type: type,
		hideAfter: delay,
		hideOnNavigate: true
	});
}

/**
 * 获取cookies
 * @param  {[type]} name [cookie名]
 * @return {[type]}      [description]
 */
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)"); //正则匹配
	if (arr = document.cookie.match(reg)) {
		return unescape(arr[2]);
	} else {
		return null;
	}
}

/**
 * 获取当前页面名称
 * @return {[type]} [description]
 */
function getPageName() {
	var a = location.href;
	var b = a.split("/");
	var c = b.slice(b.length - 1, b.length).toString(String).split(".");
	return c.slice(0, 1).toString();
}

/**
 * 判断字符串不为空
 * @param  {[type]} str [description]
 * @return {[type]}     [description]
 */
function strNotEmpty(str) {
	if (str == "")
		return false;
	else if (str == null)
		return false;
	else if (str == "null")
		return false;
	else if (str == undefined)
		return false;
	else if (str == "undefined")
		return false;
	else
		return true;
}

/**
 * 根据参数名获取参数值
 * @param {[type]} paramKey [description]
 */
function GetQueryString(paramKey) {
	var reg = new RegExp("(^|&)" + paramKey + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	else
		return null;
}