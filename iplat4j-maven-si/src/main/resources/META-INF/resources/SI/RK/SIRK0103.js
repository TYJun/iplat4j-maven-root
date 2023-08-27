$(function() {
	IPLATUI.EFGrid = {
		"result": {
			pageable : false,
			exportGrid : false,
			columns: [{field: "redRushNum", template: function (item) {
				if ($.isNumeric(item['redRushNum']) && item['redRushNum'] !=0 ) {
					return -Math.abs(item['redRushNum']) }
			}}]
		},
	}
	
	//确定
	$("#COMFIRM").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#COMFIRM").attr("disabled",true);
		setTimeout(function(){$("#COMFIRM").attr("disabled",false);},5000);
		
		//参数校验
		let rows = resultGrid.getDataItems();
		let remark = $("#inqu_status-0-remark").val();
		let newRows = new Array();
		for(let row of rows){
			if($.isNumeric(row.redRushNum) && parseFloat(row.redRushNum) != 0) {
				if(Math.abs(row.redRushNum) > parseFloat(row.enterNum) - parseFloat(row['totalRedRushNum'])){
					NotificationUtil("红冲数量不能大于入库数量减去已红冲数量","error");
					return;
				}
				//将数量转成正数
				row.redRushNum = Math.abs(row.redRushNum)+"";
				newRows.push(row);
			}
		}
		if(newRows.length == 0){
			NotificationUtil("请正确输入红冲数量或红冲数量不能全部为空","error");
			return;
		}
		//保存
		let eiInfo = new EiInfo();
		eiInfo.set("enterBillNo", $("#enterBillNo").val());
		eiInfo.set("wareHouseNo", $("#wareHouseNo").val());
		eiInfo.set("wareHouseName", $("#wareHouseName").val());
		eiInfo.set("supplierNum", $("#supplierNum").val());
		eiInfo.set("supplierName", $("#supplierName").val());
		eiInfo.set("userDeptNum", $("#userDeptNum").val());
		eiInfo.set("userDeptName", $("#userDeptName").val());
		eiInfo.set("remark", remark);
		eiInfo.set("enterType", "05");//红冲入库
		eiInfo.set("rows", newRows);
		EiCommunicator.send("SIRK0101", "enterStock", eiInfo, {
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
	let regu = /^[0-9]+(\.[0-9]{1,2})?$/;
	if(regu.test(parameter)){
		return true;
	} else {
		return false
	}
}