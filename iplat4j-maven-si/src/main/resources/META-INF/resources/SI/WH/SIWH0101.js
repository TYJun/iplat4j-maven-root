$(function() {
	//保存
	$("#SUBMIT").unbind('click').on('click', function(event){
		// 防止连续提交
		$("#SUBMIT").attr("disabled",true);
		setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);

		//处理管理员
		let workNames = _.map($("#workNo").data("kendoMultiSelect").dataItems(), _.iteratee("textField"));
		if(workNames && workNames.length > 0) {
			$("#workName").val(workNames);
		}
		
		//参数处理
		let node = $('#SIWH0101');
		//参数校验
		let wareHouseNo = $("#wareHouseNo").val();
		let wareHouseName = $("#wareHouseName").val();
		if (wareHouseNo == null || wareHouseNo == "" || wareHouseNo ==undefined) {
			NotificationUtil("仓库编码不可为空", "error");
			return;
		} 
		if (wareHouseName == null || wareHouseName == "" || wareHouseName==undefined) {
			NotificationUtil("仓库名称不可为空", "error")
			return;
		}
		//调用后台方法
		IPLAT.submitNode(node, 'SIWH0101', 'insert', {
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