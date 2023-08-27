$(function() {
		$("#SUBMIT").click(function() {
			var eiInfo = new EiInfo();
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
			eiInfo.set("moduleId", moduleId);
			eiInfo.set("itemName", itemName);
			eiInfo.set("serviceDeptNum", serviceDeptNum);
			eiInfo.set("serviceDeptName", serviceDeptName);
			eiInfo.set("comments", comments);
			EiCommunicator.send("CSSX0101", "insert", eiInfo, {
				onSuccess : function(ei) {
					NotificationUtil(ei.getMsg(), "success");
					window.parent['popDataWindow'].close();
					window.parent.resultGrid.dataSource.page(1);
				}
			});
		});

	});