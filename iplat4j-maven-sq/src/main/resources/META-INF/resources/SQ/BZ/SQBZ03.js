IPLATUI.EFGrid = {
	"result": {
		//页面点击事件获取选中行id
		loadComplete: function (grid) {
			//保存方法
			$("#SAV").on("click", function (e) {
				debugger
				var checkRows = resultGrid.getCheckedRows();
				if(checkRows.length<1){
					NotificationUtil("请选择需要保存的项目分项", "error");
					return;
				}
				//参数校验
				for (var i = 0; i < checkRows.length; i++) {
					//数字校验
					var orderNumber = checkRows[i].orderNumber;
					if(orderNumber != ""){
						var reg = /^[0-9]*$/;
						if(!reg.test(orderNumber)){
							NotificationUtil("调查排序请输入数字", "error");
							return;
						}
					}
					//计分方式
					var pointType = checkRows[i].pointType;
					if(pointType != "打分" && pointType != "判断"){
						NotificationUtil("打分方式请填写“打分”或者“判断”", "error");
						return;
					}
					//分数
					var point = checkRows[i].point;
					if(point != "5" && point != "10"){
						NotificationUtil("打分方式请填写“5”或者“10”", "error");
						return;
					}
					//名
					var instanceName = checkRows[i].instanceName;
					if(instanceName == "" || instanceName == null){
						NotificationUtil("请填写考核标准名称", "error");
						return;
					}
					//排序
					var orderNumber = checkRows[i].orderNumber;
					if(orderNumber == "" || orderNumber == null){
						NotificationUtil("请填写排序", "error");
						return;
					}
				}
				var info = new EiInfo();
				info.set("checkRows",checkRows);
				EiCommunicator.send("SQBZ03", "save", info, {
					onSuccess : function(ei) {
						var status = ei.getStatus();
						if(status == -1){
							IPLAT.alert(ei.getMsg());
						}else{
							NotificationUtil(ei.getMsg(), "success");
							resultGrid.dataSource.page(1);
						}
					}
				});
			});
			//删除方法
			$("#DELET").on("click", function (e) {
				var checkRows = resultGrid.getCheckedRows();
				if(checkRows.length<1){
					NotificationUtil("请选择需要删除的项目分项", "error");
					return;
				}
				//删除表单中新增的数据
				for (var i = 0; i < checkRows.length; i++) {
					var id = checkRows[i].id;
					if(id == "" || id == null){
						grid.removeRows(checkRows[i]);
						NotificationUtil("删除成功");
						return;
					}
				}
				//删除数据库中存在的数据
				var info = new EiInfo();
				info.set("checkRows",checkRows);
				EiCommunicator.send("SQBZ03", "delete", info, {
					onSuccess : function(ei) {
						var status = ei.getStatus();
						if(status == -1){
							IPLAT.alert(ei.getMsg());
						}else{
							NotificationUtil(ei.getMsg(), "success");
							resultGrid.dataSource.page(1);
						}
					}
				});
			});
		},
	}
}
