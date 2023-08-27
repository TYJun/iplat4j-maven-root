$(function() {
	IPLATUI.EFGrid = {
		"result": {
			pageable : false,
			exportGrid : false,
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false, 
				buttons:[{
					name: "ADD",text: "新增",layout:"3",
					click: function () {
						var wareHouseNo = IPLAT.EFSelect.value($("#inqu_status-0-wareHouseNo"));
						if(wareHouseNo == "" || wareHouseNo == null){
							NotificationUtil("请先选择仓库","error");
							return;
						}
						popData(wareHouseNo);
					}
				},{
					name: "DEL",text: "删除",layout:"3",
					click: function () {
						var checkRows = resultGrid.getCheckedRows();
						if (checkRows.length > 0) {
							resultGrid.removeRows(checkRows);
						} else {
							IPLAT.NotificationUtil("请选择需要删除的物资")
						}
					}
				}]
			},
			loadComplete:function (e) {}
		},
	}
	
	//确定
	$("#COMFIRM").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#COMFIRM").attr("disabled",true);
		setTimeout(function(){$("#COMFIRM").attr("disabled",false);},5000);
		
		//获取参数
		var rows =resultGrid.getDataItems()
		//参数校验
		if(rows == null || rows.length == 0){
			NotificationUtil("请先添加物资","error");
			return;
		}
		for(var index in rows) {
			var row = rows[index];
			if(!isPositiveNumber(row.minNum) && !isPositiveNumber(row.maxNum)){
				NotificationUtil("请输入正确的预警值(最多两位小数的正数)","error");
				return;
			} else if (row.minNum == "0" && row.maxNum == "0") {
				NotificationUtil("请输入正确的预警值(最多两位小数的正数)","error");
				return;
			} else if (!isPositiveNumber(row.minNum) && row.maxNum == "0"){
				NotificationUtil("请输入正确的预警值(最多两位小数的正数)","error");
				return;
			} else if (!isPositiveNumber(row.maxNum) && row.minNum == "0"){
				NotificationUtil("请输入正确的预警值(最多两位小数的正数)","error");
				return;
			} else if (parseFloat(row.minNum) > parseFloat(row.maxNum)){
				NotificationUtil("最低库存量不能高于最高库存存量","error");
				return;
			}
		}
		//保存
		var eiInfo = new EiInfo();
		eiInfo.set("wareHouseName", IPLAT.EFSelect.text($("#inqu_status-0-wareHouseNo")));
		eiInfo.set("wareHouseNo", IPLAT.EFSelect.value($("#inqu_status-0-wareHouseNo")));
		eiInfo.set("rows", rows);
		EiCommunicator.send("CIYJ0101", "saveStockEarlyWarning", eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil("操作成功");
				window.parent['popDataWindow'].close();
				//刷新查询
				window.parent["resultGrid"].dataSource.page(1);
			}
		})
	});
	
	//关闭
	$("#CLOSE").on("click", function(e) {
		window.parent['popDataWindow'].close();
	});
})

function popData(wareHouseNo) {
	var url = IPLATUI.CONTEXT_PATH
			+ "/web/CIDB0102?initLoad&inqu_status-0-outWareHouseNo=" + wareHouseNo;
	var popData = $("#popData1");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popData1Window.open().center();
}

//添加耗材（过滤重复）
function addRows(checkRows){
	var matList = resultGrid.getDataItems();
	for (var index in checkRows) {
		var model = checkRows[index];
		var isExist = false;
		for(var i in matList) {
			var mat = matList[i];
			if(mat.matNum == model.matNum){
				isExist = true;
			} 
		}
		if(!isExist){
			model['minNum'] = '0';
			model['maxNum'] = '0';
			resultGrid.addRows(model)
		}
	}
}

function isPositiveNumber(parameter){
	var regu = /^[0-9]+(\.[0-9]{1,2})?$/;
	if(regu.test(parameter)){
		return true;
	} else {
		false
	}
}