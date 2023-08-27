$(function() {
	__eiInfo.set("controlId","01");
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
						let wareHouseNo = IPLAT.EFSelect.value($("#inqu_status-0-wareHouseNo"));
						if(wareHouseNo == "" || wareHouseNo == null){
							NotificationUtil("请先选择仓库","error");
							return;
						}
						popData(wareHouseNo);
					}
				},{
					name: "DEL",text: "删除",layout:"3",
					click: function () {
						let checkRows = resultGrid.getCheckedRows();
						if (checkRows.length > 0) {
							resultGrid.removeRows(checkRows);
						} else {
							IPLAT.NotificationUtil("请选择需要删除的物资")
						}
					}
				},{
					name: "SAVE",text: "保存",layout:"3",
					click:function (){
						var rows=resultGrid.getDataItems();
						var eiInfo = new EiInfo();
						eiInfo.set("rows", rows);
						eiInfo.set("pageId","SICK0101");
						eiInfo.set("controlId","01");
						EiCommunicator.send("TSFW01", "saveTempData", eiInfo, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg());
							}
						})
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
		let wareHouseNo = IPLAT.EFSelect.value($("#inqu_status-0-wareHouseNo"));
		let wareHouseName = IPLAT.EFSelect.text($("#inqu_status-0-wareHouseNo"));
		let deptNum = IPLAT.EFPopupInput.value($("#inqu_status-0-userDeptNum"));
		let deptName = IPLAT.EFPopupInput.text($("#inqu_status-0-userDeptNum"));
		let costDeptNum = IPLAT.EFPopupInput.value($("#inqu_status-0-costDeptNum"));
		let costDeptName = IPLAT.EFPopupInput.text($("#inqu_status-0-costDeptNum"));
		let remark = $("#inqu_status-0-remark").val();
		let rows = resultGrid.getDataItems();
		//参数检验
		if(validate(wareHouseNo,deptNum,rows)){
			let eiInfo = new EiInfo();
			eiInfo.set("wareHouseNo", wareHouseNo);
			eiInfo.set("wareHouseName", wareHouseName);
			eiInfo.set("userDeptNum", deptNum);
			eiInfo.set("userDeptName", deptName);
			eiInfo.set("costDeptNum", costDeptNum);
			eiInfo.set("costDeptName", costDeptName);
			eiInfo.set("remark", remark);
			eiInfo.set("outType", "06");//领用出库
			eiInfo.set("rows", rows);
			EiCommunicator.send("SICK0101", "outStock", eiInfo, {
				onSuccess : function(ei) {
					if(ei.getStatus() == -1){
						NotificationUtil(ei.getMsg(), "error");
						return;
					} 
					NotificationUtil("出库成功");
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
	let url = IPLATUI.CONTEXT_PATH + "/web/SIDB0102?initLoad&inqu_status-0-outWareHouseNo=" + wareHouseNo;
	let popData = $("#popData1");
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
	for(let index in rows){
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
	let matList = resultGrid.getDataItems();
	for (let index in checkRows) {
		let model = checkRows[index];
		let isExist = false;
		for(let i in matList) {
			let mat = matList[i];
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
	let regu = /^[0-9]+(\.[0-9]{1,2})?$/;
	if(regu.test(parameter)){
		return true;
	} else {
		return false
	}
}