$(function(){

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
				// 新增
				$("#ADD").on("click", function (e) {
					var url = IPLATUI.CONTEXT_PATH + "/web/CICG0101";
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
				$("#REEDIT").on("click", function (e) {
					var id;
					var rows = resultGrid.getCheckedRows();
					for (var i = 0; i < rows.length; i++) {
						if(rows[i].applySign == "已审批"){
							NotificationUtil("不能编辑已审批的工单", "error");
							return;
						}
						if(rows[i].applySign == "待审批"){
							NotificationUtil("不能编辑待审批的工单", "error");
							return;
						}
					}
					if(rows.length > 0){
						id = rows[0].id;
						deptName=rows[0].applyDeptName;
						applyBillNo=rows[0].applyBillNo;
						emo=rows[0].emo;
					}else{
						NotificationUtil("没有勾选数据", "error");
						return;
					}
					var url = IPLATUI.CONTEXT_PATH + "/web/CICG0102?id="+id+"&deptName=" +deptName+"&applyBillNo=" + applyBillNo +"&emo=" + emo;
					var popDataModify = $("#popDataModify");
					popDataModify.data("kendoWindow").setOptions({
						open : function() {
							popDataModify.data("kendoWindow").refresh({
								url : url
							});
						}
					});
					popDataModifyWindow.open().center();
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
						if(checkRows[i].applySign == "待审批"  ){
							NotificationUtil("待审批的工单不能删除", "error");
							return;
						}
						if(checkRows[i].applySign == "已审批" ){
							NotificationUtil("已审批的工单不能删除", "error");
							return;
						}
						arr[i] = checkRows[i].id;
					}
					var info = new EiInfo();
					info.set("list",arr);

					IPLAT.confirm({
						message: '<b>确定删除此工单吗？</b></i>',
						okFn: function (e) {
							EiCommunicator.send("CICG01", "delete", info, {
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

				//提交按钮
				$("#SUBMIT").on("click", function (e) {
					/*debugger;*/
					var checkRows = resultGrid.getCheckedRows();
					if(checkRows.length<1){
						NotificationUtil("请选择要确认的行", "error");
						return;
					}
					var idList = [];
					for (var i = 0; i < checkRows.length; i++) {
						if(checkRows[i].applySign == "已审批"){
							NotificationUtil("工单已审批", "error");
							return;
						}
                        if(checkRows[i].applySign == "待审批"){
                            NotificationUtil("待审批工单不能提交", "error");
                            return;
                        }
						idList[i] = checkRows[i].id;
					}
					var inInfo = new EiInfo();
					inInfo.set("idList",idList);//将id放到EiInfo后台
					EiCommunicator.send("CICG01", "updateSubmit", inInfo, {
						onSuccess : function(ei) {
							NotificationUtil("提交成功", "success");
							resultGrid.dataSource.page(1);//刷新页面
						}
					});
				});
			}
		}
	}
});