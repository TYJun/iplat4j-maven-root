/**
 * 设置日期初始值
 * @param isMonth : boolean :是否档期月
 */
function resetTime(isMonth) {
    let lastDate = new Date();
    if(isMonth) {
        lastDate.setDate(1);
        $("#inqu_status-0-beginTime").val(lastDate.Format("yyyy-MM-dd"));
        $("#inqu_status-0-endTime").val(new Date(lastDate.getFullYear(), lastDate.getMonth()+1, 0).Format("yyyy-MM-dd"));
    } else {
        lastDate.setMonth(lastDate.getMonth()-1);
        $("#inqu_status-0-beginTime").val(lastDate.Format("yyyy-MM-dd"));
        $("#inqu_status-0-endTime").val(new Date().Format("yyyy-MM-dd"));
    }
}

function resetTimeForOneLevel(isMonth) {
    let lastDate = new Date();
    if(isMonth) {
        lastDate.setDate(1);
        $("#beginTime").val(lastDate.Format("yyyy-MM-dd"));
        $("#endTime").val(new Date(lastDate.getFullYear(), lastDate.getMonth()+1, 0).Format("yyyy-MM-dd"));
    } else {
        lastDate.setMonth(lastDate.getMonth()-1);
        $("#beginTime").val(lastDate.Format("yyyy-MM-dd"));
        $("#endTime").val(new Date().Format("yyyy-MM-dd"));
    }
}

/**
 * 时间格式化
 * @param fmt ： 格式
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) {
    let o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}



