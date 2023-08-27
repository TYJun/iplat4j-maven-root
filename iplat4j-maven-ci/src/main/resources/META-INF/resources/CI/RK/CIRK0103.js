$(function() {
	IPLATUI.EFGrid = {
		"result": {
			pageable : false,
			exportGrid : false,
		},
	}
	
	//确定
	$("#COMFIRM").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#COMFIRM").attr("disabled",true);
		setTimeout(function(){$("#COMFIRM").attr("disabled",false);},5000);
		
		//参数校验
		var rows = resultGrid.getDataItems();
		var newRows = new Array();
		for(var index in rows){
			if(isPositiveNumber(rows[index].redRushNum) 
					&& parseFloat(rows[index].redRushNum) > parseFloat(rows[index].enterNum)){
				NotificationUtil("红冲数量不能大于入库数量","error");
				return;
			}
			if(!isPositiveNumber(rows[index].redRushNum)){
				NotificationUtil("请正确输入红冲数量(最多两位小数的正数)","error");
				return;
			}
			if(isPositiveNumber(rows[index].redRushNum) && parseFloat(rows[index].redRushNum) > 0){
				newRows.push(rows[index]);
			}
		}
		if(newRows.length == 0){
			NotificationUtil("请正确输入红冲数量(最多两位小数的正数)","error");
			return;
		}
		//保存
		var eiInfo = new EiInfo();
		eiInfo.set("enterBillNo", $("#enterBillNo").val());
		eiInfo.set("wareHouseNo", $("#wareHouseNo").val());
		eiInfo.set("wareHouseName", $("#wareHouseName").val());
		eiInfo.set("enterType", "05");//红冲入库
		eiInfo.set("rows", newRows);
		EiCommunicator.send("CIRK0101", "enterStock", eiInfo, {
			onSuccess : function(ei) {
				if(ei.getStatus() == -1){
					NotificationUtil(ei.getMsg(), "error");
					return;
				} 
				NotificationUtil("入库成功");
				window.parent['popDataWindow'].close();
				window.parent["resultGrid"].dataSource.page(1);
			}
		})
	});
	
	//取消
	$("#CLOSE").on("click", function(e) {
		window.parent['popDataWindow'].close();
	});
});

function isPositiveNumber(parameter){
	var regu = /^[0-9]+(\.[0-9]{1,2})?$/;
	if(regu.test(parameter)){
		return true;
	} else {
		false
	}
}