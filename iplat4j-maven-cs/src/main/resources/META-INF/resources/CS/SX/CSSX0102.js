$(function() {
	//数据回显
	IPLAT.EFPopupInput.value($("#serDeptName"),__ei.serviceDeptNum);	
	IPLAT.EFPopupInput.text($("#serDeptName"),__ei.serviceDeptName);
		$("#SUBMITEDIT").click(function() {
			var eiInfo = new EiInfo();
			var id = IPLAT.EFInput.value($("#itemId"));
			var moduleId = IPLAT.EFInput.value($("#moduleId"));
			var itemName = IPLAT.EFInput.value($("#itemName"));
			var serviceDeptNum = IPLAT.EFPopupInput.value($("#serDeptName"))
			var serviceDeptName = IPLAT.EFPopupInput.text($("#serDeptName"))
			var comments = IPLAT.EFInput.value($("#comments"));
			//判断
			if (itemName == null || itemName == "") {
				NotificationUtil("保养事项名称不得为空", "error");
				return;
			}
			if (serviceDeptName == null || serviceDeptName == "") {
				NotificationUtil("请绑定服务科室", "error");
				return;
			}
			eiInfo.set("id",id);
			eiInfo.set("moduleId", moduleId);
			eiInfo.set("itemName", itemName);
			eiInfo.set("serviceDeptNum", serviceDeptNum);
			eiInfo.set("serviceDeptName", serviceDeptName);
			eiInfo.set("comments", comments);
			EiCommunicator.send("CSSX0102", "update", eiInfo, {
				onSuccess : function(ei) {
					NotificationUtil(ei.getMsg(), "success");
					window.parent['popDataEditWindow'].close();
					window.parent.resultGrid.dataSource.page(1);
					
				}
			});
		});

	});