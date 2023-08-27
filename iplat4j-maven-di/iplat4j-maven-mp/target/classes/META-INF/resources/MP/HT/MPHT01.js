$(function(){
	/**1.表格初始化**/
	IPLATUI.EFGrid = new WilpGrid({

		editConfig: {status: ["01"]},
		extentMethod: [{
			buttonId: "EFFECT",
			call: function () {
				let checkRows = resultGrid.getCheckedRows();
				if (checkRows.length < 1) {
					NotificationUtil("请选择需要生效的合同", "error");
				} else if ("10" == checkRows[0].statusCode) {
					NotificationUtil("已生效的合同无需再次生效", "error");
				} else if ("20" == checkRows[0].statusCode) {
					NotificationUtil("已终止的合同无法生效", "error");
				} else {
					let eiInfo = new EiInfo();
					eiInfo.set("id",checkRows[0].id);
					EiCommunicator.send("MPHT01", "takeEffect",eiInfo, {
						onSuccess : function(ei) {
							if(ei.getStatus() === -1){
								NotificationUtil(ei.getMsg(), "error");
							}else{
								NotificationUtil("生效成功")
								resultGrid.dataSource.page(1);
							}
						}
					})
				}
			}
		},{
			buttonId: "STOP",
			call: function () {
				let checkRows = resultGrid.getCheckedRows();
				if (checkRows.length < 1) {
					NotificationUtil("请选择需要终止的合同", "error");
				} else if ("20" == checkRows[0].statusCode) {
					NotificationUtil("已终止的合同无需再次终止", "error");
				} else {
					IPLAT.confirm({
						message: '<b>您确定要终止合同吗？</b>',
						okFn: function (e) {
							let eiInfo = new EiInfo();
							eiInfo.set("id", checkRows[0].id);
							EiCommunicator.send("MPHT01", "stop", eiInfo, {
								onSuccess: function (ei) {
									if (ei.getStatus() == -1) {
										NotificationUtil(ei.getMsg(), "error");
										return;
									}
									NotificationUtil("终止成功");
									window["resultGrid"].dataSource.page(1);
								}
							});
						},
						cancelFn: function (e) {}
					})
				}
			}
		}]
	}).buildGrid();
	$.extend(true, IPLATUI.Config, { //全局配置
		EFGrid: {

			columns:[ {
				field: "contCost",
				readonly: true,
				template: "<span>#=contCost#元</span>",
			}]

		}
	})
	/** 查询 */
	$("#QUERY").on("click",function(e){
		resultGrid.dataSource.page(1);
	});

	/**重置**/
	$("#REQUERY").on("click",function(e){
		resetTime("inqu_status-0-recCreateTimeS", "inqu_status-0-recCreateTimeE");
		$("#inqu_status-0-contNo").val("");
		$("#inqu_status-0-contName").val("");
		$("#inqu_status-0-statusCode").val("");
		resultGrid.dataSource.page(1);
	});
})






