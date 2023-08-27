$(function () {
	IPLATUI.EFSelect = {
		"inqu_status-0-deptName": {
			// 点击下拉选项时触发
			select:function(e) { //获取勾选值
				let dataItem = e.dataItem;
				$("#inqu_status-0-deptNum").val(dataItem['valueField']);
			}
		},
		"inqu_status-0-worker":{
			select:function(e) { //获取勾选值
				let dataItem = e.dataItem;
				$("#inqu_status-0-workNo").val(dataItem['valueField']);
			}
		}
	};

	// 保存方法
	$("#SAVE").on("click", function (e) {
		if ($("#inqu_status-0-deptNum").val() == ""){
			NotificationUtil("请选择所属部门", "warning")
			return
		}
		if ($("#inqu_status-0-workNo").val() == ""){
			NotificationUtil("请选择员工", "warning")
			return
		}
		// 获取参数
		var eiInfo = new EiInfo();
		eiInfo.set("deptNum",$("#inqu_status-0-deptNum").val());
		eiInfo.set("deptName",IPLAT.EFSelect.text($("#inqu_status-0-deptName")));
		eiInfo.set("workNo",$("#inqu_status-0-workNo").val());
		eiInfo.set("worker",IPLAT.EFSelect.text($("#inqu_status-0-worker")));
		// 固资类别录入提交
		EiCommunicator.send("CPPZ0101", "saveDeptInfo", eiInfo, {
			onSuccess : function(ei) {
				closeParentWindow()
				window.parent.location.reload()
			}
		})
	});

	$("#CLOSE").on("click", function (e) {
		closeParentWindow()
	});
})

function closeParentWindow() {
	window.parent['popDataWindow'].close();
}