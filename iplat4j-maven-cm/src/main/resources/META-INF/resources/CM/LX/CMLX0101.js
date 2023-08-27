$(function() {
	//弹窗页面保存
	$("#SAVEPR").on("click", function(ei) {
		var validator = IPLAT.Validator({
			id: "result"
		});
		if (validator.validate()) {
			var eiInfo = new EiInfo();
			eiInfo.setByNode("result");
			EiCommunicator.send("CMLX0101", "save", eiInfo, {
				onSuccess : function(ei) {
					closeCurrentWindow();
					IPLAT.NotificationUtil(ei.msg)
					window.parent["resultGrid"].dataSource.page(1);
					window.parent.datagrid = null;
				}
			})
		}else{
			NotificationUtil("请填写带*的输入框", "error")
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