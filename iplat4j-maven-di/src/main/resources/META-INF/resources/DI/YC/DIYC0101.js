//获取上级页面传递参数
var url = window.location.href;
var str = window.location.search;


$(function() {


	Date.prototype.Format = function (fmt) {
		var o = {
			"M+": this.getMonth() + 1, //月份
			"d+": this.getDate(), //日
			"H+": this.getHours(), //小时
			"m+": this.getMinutes(), //分
			"s+": this.getSeconds(), //秒
			"q+": Math.floor((this.getMonth() + 3) / 3), //季度
			"S": this.getMilliseconds() //毫秒
		};
		if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (var k in o)
			if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}

	var dealDate = new Date().Format("yyyy-MM-dd");
	$("#finishTime").val(dealDate);




	$("#INS").on("click",function(e) {
		var rejectreason = $("#exceptionResult").val();
		var solutionTypeValue = IPLAT.EFSelect.text($("#solutionType"));
		var dealMan = IPLAT.EFPopupInput.text($("#dealMan"));
		var finishTime = IPLAT.EFInput.value($("#finishTime"));
		var taskCode = $("#taskCode").val();
		if("" == rejectreason){
			IPLAT.alert("请填写异常处理信息");
			return;
		}
		if(""==solutionTypeValue){
			IPLAT.alert("请填写异常处理信息");
			return;
		}
		var eiInfo = new EiInfo();
		var itemID = taskCode;
		if(null == itemID){
			IPLAT.alert("系统错误，请重新选择并填写");
			return;
		}
		eiInfo.set("rejectreason",rejectreason);
		eiInfo.set("solutionType",solutionTypeValue);
		eiInfo.set("itemID",itemID);
		eiInfo.set("dealMan",dealMan);
		eiInfo.set("finishTime",finishTime);
		EiCommunicator.send("DIYC0101", "update", eiInfo, {
			onSuccess : function(ei) {
				IPLAT.alert("保存成功");
				NotificationUtil(ei.getMsg(), "success");
				setTimeout(function() {
					window.parent.location.reload()
				}, 500);
			}
		})
	})
})