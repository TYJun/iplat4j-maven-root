var datagrid = null;
$(function() {
	$.extend(true, IPLATUI.Config, { //全局配置
		EFGrid: {
			pageable: {
				pageSize: 100, // 每页显示的条数，优先级低于 DataSource 中的 pageSize
				pageSizes: [10, 20, 50, 100, 500, 1000]
			}
		}
	})
	IPLATUI.EFGrid = {
		"result" : {
			onCellClick : function(e) {
				var inInfo = new EiInfo();
				e.preventDefault();
				$("#mainOutBillNo").val( e.model['outBillNo']);
				inInfo.set( "mainOutBillNo",e.model['outBillNo']);
				EiCommunicator.send("CICK01","query", inInfo, {
					// 服务调用成功后的回调函数 onSuccess
					onSuccess: function(e){
						console.log(e.blocks.processsResult)
						processsResultGrid.setEiInfo(e);

					},
					// 服务调用失败后的回调函数 onFail
					onFail: function(errorMsg, status, e) { /* errorMsg 是平台格式化后的异常消息， status， e的含义和$.ajax的含义相同，请参考jQuery文档 */
						// 调用发生异常
						console.log(errorMsg);
					}
				}, {async: false});

				// if (!e.fake) {
				// 	var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
				// 	datagrid = model;
				// }
				// if (e.field === "outBillNo") {
				// 	var outBillNo = e.model['outBillNo'];
				// 	if (outBillNo != " " && outBillNo != null) {
				// 		popData(datagrid.outBillNo, datagrid.outTypeName,datagrid.wareHouseName, datagrid.userDeptName);
				// 		resultGrid.unCheckAllRows();
				// 	}
				// }
			},
			loadComplete:function (e) {
				//录入
				$("#ADD").on("click", function(e) {
					popData1();
				});
				//报废出库
				$("#BFCK").on("click", function(e) {
					popData3();
				});
				
				//红冲出库
				$("#HCCK").on("click", function(e) {
					var checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择一条记录","error");
					} else if (checkRows[0].outTypeName == "红冲出库"){
						NotificationUtil("该单据已经红冲出库，无法再操作","error");
					} else if (checkRows[0].outType != "06"){
						NotificationUtil("该单据类型不能红冲出库","error");
					} else{
						popData2(checkRows[0].outBillNo, checkRows[0].outTypeName,checkRows[0].wareHouseNo,
								checkRows[0].wareHouseName, checkRows[0].userDeptNum, checkRows[0].userDeptName);
					}
				});
			}
		},
		"processsResult":{
			pageable : true,
			exportGrid : false,
		}
	}
	
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});
})

//出库详情
function popData(outBillNo, outTypeName, wareHouseName, userDeptName) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CICK0103?initLoad&outBillNo=" + outBillNo
		+ "&outTypeName=" + outTypeName + "&wareHouseName=" + wareHouseName + "&userDeptName=" + userDeptName;
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popDataWindow.open().center();
}

//出库录入
function popData1() {
	var url = IPLATUI.CONTEXT_PATH + "/web/CICK0101";
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popDataWindow.open().center();
}

//红冲出库
function popData2(outBillNo, outTypeName, wareHouseNo, wareHouseName, userDeptNum, userDeptName) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CICK0102?initLoad&outBillNo=" + outBillNo + "&outTypeName=" + outTypeName
		 + "&wareHouseName=" + wareHouseName + "&wareHouseNo=" + wareHouseNo
		 + "&userDeptName=" + userDeptName + "&userDeptNum=" + userDeptNum;
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popDataWindow.open().center();
}

//报废出库
function popData3() {
	var url = IPLATUI.CONTEXT_PATH + "/web/CICK0104";
	var popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popDataWindow.open().center();
}
	
	