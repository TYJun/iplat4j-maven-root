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
						var outWareHouseNo = IPLAT.EFSelect.value($("#inqu_status-0-outWareHouseNo"));
						if(outWareHouseNo == "" || outWareHouseNo == null){
							NotificationUtil("请先选择调出仓库","error");
							return;
						}
						popData(outWareHouseNo);
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
		
		//获取数据
		var outWareHouseNo = IPLAT.EFSelect.value($("#inqu_status-0-outWareHouseNo"));
		var outWareHouseName = IPLAT.EFSelect.text($("#inqu_status-0-outWareHouseNo"));
		var inWareHouseNo = IPLAT.EFSelect.value($("#inqu_status-0-inWareHouseNo"));
		var inWareHouseName = IPLAT.EFSelect.text($("#inqu_status-0-inWareHouseNo"));
		var rows = resultGrid.getDataItems();
		
		//参数校验
		var flag = vilidate(outWareHouseNo,inWareHouseNo,rows)
		
		//保存
		if(flag){
			var eiInfo = new EiInfo();
			eiInfo.set("outWareHouseNo", outWareHouseNo);
			eiInfo.set("outWareHouseName", outWareHouseName);
			eiInfo.set("inWareHouseNo", inWareHouseNo);
			eiInfo.set("inWareHouseName", inWareHouseName);
			eiInfo.set("rows", rows);
			EiCommunicator.send("CIDB0101", "saveStockTrans",eiInfo, {
				onSuccess : function(ei) {
					if(ei.getStatus() == -1){
						NotificationUtil(ei.getMsg(), "error");
						return;
					} 
					
					NotificationUtil("操作成功");
					window.parent['popDataWindow'].close();
					window.parent["resultGrid"].dataSource.page(1);
				}
			})
		}
	});

	//关闭
	$("#CLOSE").on("click", function(e) {
		window.parent['popDataWindow'].close();
	});
	
})

//打开物资选择弹出窗口
function popData(outWareHouseNo) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIDB0102?initLoad&inqu_status-0-outWareHouseNo=" + outWareHouseNo;
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

//参数校验
function vilidate(outWareHouseNo,inWareHouseNo,rows) {
	if(outWareHouseNo == "" || outWareHouseNo == null || outWareHouseNo == undefined){
		NotificationUtil("请选择调出仓库","error");
		return false;
	}
	if(inWareHouseNo == "" || inWareHouseNo == null || inWareHouseNo == undefined){
		NotificationUtil("请选择调入仓库","error");
		return false;
	}
	if(outWareHouseNo == inWareHouseNo){
		NotificationUtil("调入仓库和调出仓库不能一致","error");
		return false;
	}
	if(rows == null || rows.length == 0){
		NotificationUtil("请添加物资","error");
		return false;
	}
	for(var index in rows) {
		if (!isPositiveNumber(rows[index].transNum) || rows[index].transNum == "0") {
			NotificationUtil("请正确输入调拨数量(最多两位小数的正数)","error");
			return false;
		}
		if(parseFloat(rows[index].transNum) > parseFloat(rows[index].totalNum)){
			NotificationUtil("调拨数量不能大于当前数量","error");
			return false;
		}
	}
	return true;
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
			model['transNum'] = '0';
			resultGrid.addRows(model)
		}
	}
}

//判断是否为正数
function isPositiveNumber(parameter){
	var regu = /^[0-9]+(\.[0-9]{1,2})?$/;
	if(regu.test(parameter)){
		return true;
	} else {
		false
	}
}