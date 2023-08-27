$(function() {
	//保存
	$("#SUBMIT").unbind('click').on('click', function(event){
		// 防止连续提交
		$("#SUBMIT").attr("disabled",true);
		setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);
		
		//参数处理
		var node = $('#CIWH0101');
		//参数校验
		var wareHouseName = $("#wareHouseName").val();
		if (wareHouseName == null || wareHouseName == "" || wareHouseName==undefined) {
			NotificationUtil("仓库名称不可为空", "error")
			return;
		} 
		//调用后台方法
		IPLAT.submitNode(node, 'CIWH0101', 'insert', {
			onSuccess : function(eiInfo) {
				if(eiInfo.getStatus() == -1){
					NotificationUtil(eiInfo.getMsg(), "error");
					return;
				}
				
				NotificationUtil("保存成功", "");
				window.parent['popDataWindow'].close();
				window.parent["resultGrid"].dataSource.page(1);
			},
			onFail : function(errorMsg, status, e) {
				NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
			}
		});
	});
		
	//取消
	$("#CANCEL").click(function() {
		window.parent['popDataWindow'].close();
	});
});