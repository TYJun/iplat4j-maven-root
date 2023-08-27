var datagrid = null;
$(function() {
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.query(1);
	});

	// 重置按钮.
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
			loadComplete: function (grid) {
				//编辑短信模板按钮
				$("#EDITSMSTEMP").on("click", function(e) {
					console.log("编辑短信模板");
					var url = IPLATUI.CONTEXT_PATH + "/web/DMDX0101";
					var popData = $("#popData");
					popData.data("kendoWindow").setOptions({
						open : function() {
							popData.data("kendoWindow").refresh({
								url : url
							});
						}
					});
					popDataWindow.open().center();
				});

				//发送短信按钮
				$("#SENDMSG").on("click", function(e) {
					console.log("发送短信");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					var manIdList = "";
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						for (var i = 0;i<checkRows.length; i++){
							if (checkRows[i]["statusCode"] == '00' || checkRows[i]["statusCode"] == '01' ||
								checkRows[i]["statusCode"] == '02' || checkRows[i]["roomName"] == null){
								alert("所勾选的第"+ (i+1) +"行不满足发送短信要求，请确认所勾选的人员是否都有入住信息！");
								return;
							}else {
								// 当遍历最后一个值时，省略最后的"，"
								if (i == checkRows.length - 1) {
									manIdList += checkRows[i]["id"];
								} else {
									manIdList += checkRows[i]["id"] + ',';
								}
							}
						}
						//提交数据
						var eiInfo = new EiInfo();
						eiInfo.set("manIdList", manIdList);
						console.log(manIdList);
						EiCommunicator.send("DMDX01", "batchSendMsg", eiInfo, {
							onSuccess : function(ei) {
								console.log(ei);
								alert(ei.getMsg());
								setTimeout(function() {
									window.parent.location.reload()
								}, 600);
							}
						});
					} else {
						NotificationUtil("请选择想要发短信的用户", "warning")
					}
				});
			}
		}
	}
});