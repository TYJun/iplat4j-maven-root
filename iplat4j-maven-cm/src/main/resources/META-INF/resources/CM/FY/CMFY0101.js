$(function() {
	var validator = IPLAT.Validator({
		id: "result"
	});
	//弹窗页面保存
	$("#SAVEPR").on("click", function(ei) {
		if (validator.validate()) {
			var eiInfo = new EiInfo();
			eiInfo.setByNode("result");
			EiCommunicator.send("CMFY0101", "save", eiInfo, {
				onSuccess : function(ei) {
					closeCurrentWindow();
					IPLAT.NotificationUtil(ei.msg)
					window.parent["resultGrid"].dataSource.page(1);
				}
			})
		}else{
			NotificationUtil("请填写合同费用名称", "error")
		}
	});
	
	
	//取消
	$("#CANCEL").on("click", function() {
		closeCurrentWindow();
	});
})
//关闭窗口
function closeCurrentWindow() {
	window.parent['popDataWindow'].close();
}