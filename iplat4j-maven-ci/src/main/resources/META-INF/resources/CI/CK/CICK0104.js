//报废出库

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
		var wareHouseNo = IPLAT.EFSelect.value($("#inqu_status-0-wareHouseNo"));
		var wareHouseName = IPLAT.EFSelect.text($("#inqu_status-0-wareHouseNo"));
		var deptNum = IPLAT.EFPopupInput.value($("#inqu_status-0-userDeptNum"));
		var deptName = IPLAT.EFPopupInput.text($("#inqu_status-0-userDeptNum"));
		var rows = resultGrid.getDataItems();
		//参数检验
		if(validate(wareHouseNo,deptNum,rows)){
			var eiInfo = new EiInfo();
			eiInfo.set("wareHouseNo", wareHouseNo);
			eiInfo.set("wareHouseName", wareHouseName);
			eiInfo.set("userDeptNum", deptNum);
			eiInfo.set("userDeptName", deptName);
			eiInfo.set("outType", "09");//报废出库
			eiInfo.set("isCheck","0");//待审核
			eiInfo.set("rows", rows);
			EiCommunicator.send("CICK0101", "outStock", eiInfo, {
				onSuccess : function(ei) {
					if(ei.getStatus() == -1){
						NotificationUtil(ei.getMsg(), "error");
						return;
					} 
					NotificationUtil("报废出库申请成功");
					window.parent['popDataWindow'].close();
					window.parent["resultGrid"].dataSource.page(1);
				}
			})
		}
	});
	
	//取消
	$("#CANCEL").on("click", function(e) {
		window.parent['popDataWindow'].close();
	});
});

//物资选择
function popData(wareHouseNo) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIDB0102?initLoad&inqu_status-0-outWareHouseNo=" + wareHouseNo;
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
function validate(wareHouseNo,deptNum,rows) {
	if(wareHouseNo == null || wareHouseNo=="" || wareHouseNo==undefined){
		NotificationUtil("请选择仓库","error");
		return false;
	}
	if(deptNum == null || deptNum=="" || deptNum==undefined){
		NotificationUtil("请选择领用科室","error");
		return false;
	}
	if(rows == null || rows.length == 0){
		NotificationUtil("请选择物资","error");
		return false;
	}
	for(var index in rows){
		if(!isPositiveNumber(rows[index].outNum) || rows[index].outNum == "0"){
			NotificationUtil("请输正确的出库数量(最多两位小数的正数)","error");
			return false;
		}
		if(parseFloat(rows[index].outNum) > parseFloat(rows[index].totalNum)){
			NotificationUtil("出库数量不能大于存库总数","error");
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
			model['outNum'] = '0';
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