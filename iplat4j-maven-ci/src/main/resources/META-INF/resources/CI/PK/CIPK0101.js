$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			pageable : false,
			exportGrid : false,
		}
	}
	
	//确定(盘库信息维护)
	$("#COMFIRM").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#COMFIRM").attr("disabled",true);
		setTimeout(function(){$("#COMFIRM").attr("disabled",false);},5000);
		
		//参数校验
		var rows =resultGrid.getDataItems();
		if(rows == null || rows.length == 0){
			NotificationUtil("请先添加物资","error");
			return;
		}
		for(var index in rows) {
			if(!isPositiveNumber(rows[index].afterInvenNum)){
				NotificationUtil("请先填盘点后数量（最多两位小数的数字）","error");
				return;
			}
			if(!isPositiveNumber(rows[index].price)){
				NotificationUtil("请填写盘点单价（最多两位小数的数字）","error");
				return;
			}
		}
		//保存
		var eiInfo = new EiInfo();
		eiInfo.set("invenBillNo", $("#invenBillNo").val());
		eiInfo.set("rows", rows);
		EiCommunicator.send("CIPK0101", "updateStockInven",eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil("操作成功");
				window.parent['popDataWindow'].close();
				//刷新查询
				setTimeout(function() {window.parent.location.reload()}, 600);
			}
		})
	});
	
	//审核通过
	$("#PASS").unbind('click').on("click", function(e) {
		// 防止连续提交
		$("#PASS").attr("disabled",true);
		setTimeout(function(){$("#PASS").attr("disabled",false);},5000);
		
		var eiInfo = new EiInfo();
		eiInfo.set("invenBillNo", $("#invenBillNo").val());
		EiCommunicator.send("CIPK0101", "approveStockInven", eiInfo, {
			onSuccess : function(ei) {
				if(ei.getStatus() == -1){
					NotificationUtil(ei.getMsg(), "error");
					return;
				} 
				window.parent['popDataWindow'].close();
				NotificationUtil(ei.getMsg());
				setTimeout(function() {window.parent.location.reload()}, 600);
			}
		})
	});
	
	//关闭
	$("#CLOSE").on("click", function(e) {
		window.parent['popDataWindow'].close();
	});
})

//判断是否是正数（小数最多只有两位）
function isPositiveNumber(parameter){
	var regu = /^[0-9]+(\.[0-9]{1,2})?$/;
	if(regu.test(parameter)){
		return true;
	} else {
		false
	}
}