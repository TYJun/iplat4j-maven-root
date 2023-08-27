//获取上级页面传递参数
var url = window.location.href;
var str = window.location.search;

$(function() {
	$("#INS").on("click",function(e) {
		var rejectreason = $("#exceptionResult").val();
		var taskCode = $("#taskCode").val();
		if("" == rejectreason){
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
		eiInfo.set("itemID",itemID);
		EiCommunicator.send("IMYC0101", "update", eiInfo, {
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