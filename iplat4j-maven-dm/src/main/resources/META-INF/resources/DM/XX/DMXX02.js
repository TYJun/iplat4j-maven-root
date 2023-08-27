$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			loadComplete : function(grid) {
				//新增按钮
				$("#SAVEDATA").on("click", function (e) {
					var checkRows = resultGrid.getCheckedRows();
					if(checkRows.length<1){
						// NotificationUtil("请勾选一行或多行数据进行保存提交","warning");
						return;
					}else if(checkRows.length>1){
						NotificationUtil("请对数据进行一行一行的保存提交","warning");
						return;
					}
					var id = checkRows[0].id;
					var dormProperties = checkRows[0].dormProperties;
					var employmentNature = checkRows[0].employmentNature;
					var eiInfo = new EiInfo();
					eiInfo.set("id",id);
					eiInfo.set("dormProperties",dormProperties);
					eiInfo.set("employmentNature",employmentNature);
					IPLAT.confirm({
						message:"确定提交选中行?",
						okFn:function(e){
							if (id == ''){
								EiCommunicator.send("DMXX02", "insertData", eiInfo, {
									onSuccess : function(ei) {
										NotificationUtil(ei.getMsg(), "success");
										setTimeout(function() {
											window.parent.location.reload()
										}, 600);
									}
								});
							}else {
								EiCommunicator.send("DMXX02", "updateData", eiInfo, {
									onSuccess : function(ei) {
										NotificationUtil(ei.getMsg(), "success");
										setTimeout(function() {
											window.parent.location.reload()
										}, 600);
									}
								});
							}
						}
					});
				});
			}
		}
	}

});
