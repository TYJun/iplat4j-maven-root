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
					click: function () { popData(); }
				}, {
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
		var canteenNum = IPLAT.EFPopupInput.value($("#inqu_status-0-userDeptNum"));
		var canteenName = IPLAT.EFPopupInput.text($("#inqu_status-0-userDeptNum"));
		var rows = resultGrid.getDataItems();
		//参数检验
		if(validate(canteenNum, rows)){
			var eiInfo = new EiInfo();
			eiInfo.set("canteenNum", canteenNum);
			eiInfo.set("canteenName", canteenName);
			eiInfo.set("rows", rows);
			eiInfo.set("applySign","");
			EiCommunicator.send("CISQ0101", "insert", eiInfo, {
				onSuccess : function(ei) {
					if(ei.getStatus() == -1){
						NotificationUtil(ei.getMsg(), "error");
						return;
					}
					NotificationUtil("新增成功");
					window.parent['popDataWindow'].close();
					window.parent["resultGrid"].dataSource.page(1);
				}
			})
		}
	});

	//取消
	$("#CLOSE").on("click", function(e) {
		window.parent['popDataWindow'].close();
	});
});

//物资选择弹窗
function popData() {
	var url = IPLATUI.CONTEXT_PATH + "/web/CISQ010101?initLoad";
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
function validate(canteenNum, rows) {
	if(canteenNum == null || canteenNum=="" || canteenNum==undefined){
		NotificationUtil("请选择科室","error");
		return false;
	}
	if(rows == null || rows.length == 0){
		NotificationUtil("请选择物资","error");
		return false;
	}
	for(var index in rows){
		if(!isPositiveNumber(rows[index].applyNum) || rows[index].applyNum == "0"){
			NotificationUtil("请输正确的申请数量(最多两位小数的正数)","error");
			return false;
		}
		if(!isPositiveNumber(rows[index].enterPrice)){
			NotificationUtil("请正确填写单价(最多两位小数的正数)","error");
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
			model['applyNum'] = '0';
			model['enterPrice'] = '0';
			model['enterAmount'] = '0';
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