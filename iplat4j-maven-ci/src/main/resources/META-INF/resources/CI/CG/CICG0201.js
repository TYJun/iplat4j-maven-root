$(function() {

//确定
	$("#COMFIRM").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#COMFIRM").attr("disabled",true);
		setTimeout(function(){$("#COMFIRM").attr("disabled",false);},5000);
		/*var deptNum = IPLAT.EFInput.value($("#deptNum"));
		var deptName = IPLAT.EFInput.value($("#deptName"));*/
		var rows = resultGrid.getDataItems();
		var id = IPLAT.EFInput.value($("#id"));
		var applyBillNo = IPLAT.EFInput.value($("#applyBillNo"));
		//参数检验
		if(validate(rows)){
			var inInfo = new EiInfo();
			/*inInfo.set("userDeptNum", deptNum);
			inInfo.set("userDeptName", deptName);*/
			inInfo.set("rows", rows);
			inInfo.set("id", id);
			inInfo.set("applyBillNo", applyBillNo);
			inInfo.set("outType", "10");//物资配送出库
			EiCommunicator.send("CICG0201", "updateApprove", inInfo, {
				onSuccess : function(ei) {
					if(ei.getStatus() == -1){
						NotificationUtil(ei.getMsg(), "error");
						return;
					}
					NotificationUtil("审核成功");
					window.parent['popDataModifyWindow'].close();
					window.parent["resultGrid"].dataSource.page(1);
				}
			})
		}
	});

	//驳回
	$("#REJECT").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#REJECT").attr("disabled",true);
		setTimeout(function(){$("#REJECT").attr("disabled",false);},5000);
		/*var deptNum = IPLAT.EFInput.value($("#deptNum"));;
		var deptName = IPLAT.EFInput.value($("#deptName"));*/
		var rows = resultGrid.getDataItems();
		var id = IPLAT.EFInput.value($("#id"));
		var applyBillNo = IPLAT.EFInput.value($("#applyBillNo"));
		var emo = IPLAT.EFInput.value($("#emo"));
		//参数检验
		if(validate(emo)){
			var eiInfo = new EiInfo();
			/*eiInfo.set("deptNum", deptNum);
			eiInfo.set("deptName", deptName);*/
			eiInfo.set("rows", rows);
			eiInfo.set("id", id);
			eiInfo.set("applyBillNo", applyBillNo);
			eiInfo.set("emo", emo);
			EiCommunicator.send("CICG0201", "updateReject", eiInfo, {
				onSuccess : function(ei) {
					if(ei.getStatus() == -1){
						NotificationUtil(ei.getMsg(), "error");
						return;
					}
					NotificationUtil("驳回成功");
					window.parent['popDataModifyWindow'].close();
					window.parent["resultGrid"].dataSource.page(1);
				}
			})
		}
	});



	//取消
	$("#CLOSE").on("click", function(e) {
		window.parent['popDataModifyWindow'].close();
	});
});


//参数校验
function validate(emo) {
	if(emo == null || emo=="" || emo==undefined){
		NotificationUtil("请填写驳回理由","error");
		return false;
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