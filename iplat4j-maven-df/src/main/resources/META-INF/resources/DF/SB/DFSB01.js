/**
 *
 */
$(function() {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});

	$("#RESET").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	IPLATUI.EFGrid = {
			"result": {
				loadComplete: function (grid) {
					//上移
					$("#ODERUP").on("click", function (e) {
						var id ;
						eiInfo = new EiInfo();
						var rows = resultGrid.getCheckedRows();
						if(rows.length > 0){
							id = rows[0].id;
						}else {
							NotificationUtil("请选择要上移的设备", "error");
							return;
						}
						eiInfo.set("sort","UP");
						eiInfo.set("id",id);
						EiCommunicator.send("DFSB01", "sortByOrderNum", eiInfo, {
							onSuccess : function(ei) {

							}
						})
					});
					//下移
					$("#ODERDOWN").on("click", function (e) {
						var id ;
						eiInfo = new EiInfo();
						var rows = resultGrid.getCheckedRows();
						if(rows.length > 0){
							id = rows[0].id;
						}else {
							NotificationUtil("请选择要下移的设备", "error");
							return;
						}
						eiInfo.set("id",id);
						eiInfo.set("sort","DOWN");
						EiCommunicator.send("DFSB01", "sortByOrderNum", eiInfo, {
							onSuccess : function(ei) {

							}
						})
					});
					// 新增
					$("#READD").on("click", function (e) {
						var url = IPLATUI.CONTEXT_PATH + "/web/DFSB0101";
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
					// 编辑
					$("#EDIT").on("click", function (e) {
						var id;
						var rows = resultGrid.getCheckedRows();
						for (var i = 0; i < rows.length; i++) {
							if(rows[i].statusCode == "1"){
								NotificationUtil("不能编辑已启用的设备", "error");
								return;
							}
						}
						if(rows.length > 0){
							id = rows[0].id;
						}else{
							NotificationUtil("没有勾选数据", "error");
							return;
						}
						var url = IPLATUI.CONTEXT_PATH + "/web/DFSB0102?id="+id;
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
					// 删除
					$("#REDELETE").on("click",function(e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length < 1){
							NotificationUtil("请选择要删除的行", "error");
							return;
						}
						var arr=[];
						for (var i = 0; i < checkRows.length; i++) {
							if(checkRows[i].statusCode == "1"){
								NotificationUtil("设备已经启用不能删除", "error");
								return;}

							arr[i] = checkRows[i].id;
						}
						var info = new EiInfo();
						info.set("list",arr);
					/*	EiCommunicator.send("DFSB01", "deleteItem", info, {
							onSuccess : function(eiInfo) {
								/!*if(eiInfo.getStatus() == -1){
									NotificationUtil(eiInfo.getMsg(), "error");
									return;
								}*!/
								NotificationUtil("删除成功", "success");
								resultGrid.dataSource.page(1);
								window.parent['popDataWindow'].close();
								window.parent["resultGrid"].dataSource.query(1);
							}
						});*/
						IPLAT.confirm({
							message: '<b>确定删除此设备吗？</b></i>',
							okFn: function (e) {
								EiCommunicator.send("DFSB01", "deleteItem", info, {
									onSuccess : function(ei) {
										/*IPLAT.NotificationUtil(ei.msg)*/
										if(ei.getStatus()==-1){
											NotificationUtil(ei.getMsg(), "error");
											return;
										}
										NotificationUtil(ei.getMsg(), "success");
										resultGrid.dataSource.query(1);
									}
								})
							},
							cancelFn: function (e) {}
						})
					});

					//启动按钮
					$("#RESTART").on("click", function (e) {
						/*debugger;*/
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length<1){
							NotificationUtil("请选择要确认的行", "error");
							return;
						}
						var idList = [];
						for (var i = 0; i < checkRows.length; i++) {
							if(checkRows[i].statusCode == "1"){
								NotificationUtil("设备已经启用", "error");
								return;
							}
							idList[i] = checkRows[i].id;

						}
						var inInfo = new EiInfo();
						inInfo.set("idList",idList);//将id放到EiInfo后台
						EiCommunicator.send("DFSB01", "updateStart", inInfo, {

							onSuccess : function(ei) {
								NotificationUtil("启用成功", "success");
								resultGrid.dataSource.page(1);//刷新页面
							}
						});
					});

					//停用按钮
					$("#RESTOP").on("click", function (e) {

						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length<1){
							NotificationUtil("请选择要确认的行", "error");
							return;
						}

						var idList = [];
						for (var i = 0; i < checkRows.length; i++) {
							if(checkRows[i].statusCode == "-1"){
								NotificationUtil("设备已经停用", "error");
								return;}

							idList[i] = checkRows[i].id;
						}
						var inInfo = new EiInfo();
						inInfo.set("idList",idList);
						EiCommunicator.send("DFSB01", "updateStop", inInfo, {
							onSuccess : function(ei) {
								NotificationUtil("停用成功","success");
								resultGrid.dataSource.page(1);
							}
						});
					});

					//生成二维码
					$("#REEWM").on("click", function(e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length < 1){
							NotificationUtil("请选择要确认的行", "error");
							return;
						}
						var row = resultGrid.getCheckedRows()[0];
						var id = row.id;
						var fixedPlace = row.machineName;
						popData1(id,fixedPlace);
					});
					function popData1(id,fixedPlace) {
						var url = IPLATUI.CONTEXT_PATH + "/web/DFSB0103?&id="+id+"&fixedPlace="+fixedPlace;
						var popData = $("#popData1");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url,
								});
							}
						});
						// 打开生成弹窗
						popData1Window.open().center();
					}

					// 生成二维码
					$("#QRCODE").on("click", function(e) {
						var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length < 1){
							NotificationUtil("请选择要确认的行", "error");
							return;
						}
						var row = resultGrid.getCheckedRows()[0];
						var id = row.id;
						var fixedPlace = row.machineName;
						popData2(id,fixedPlace);
					});
					function popData2(id,fixedPlace) {
						var url = IPLATUI.CONTEXT_PATH + "/web/DFSB0501?&id="+id+"&fixedPlace="+fixedPlace;
						var popData = $("#popData2");

						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url,
								});
							}
						});
						// 打开生成弹窗
						popData2Window.open().center();
					}

				}
			}
	}
});
