var datagrid = null; var outType = null;
$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					let grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "outBillNo") {
					let outBillNo = e.model['outBillNo'];
					if (outBillNo != " " && outBillNo != null) {
						popData(datagrid.outBillNo, datagrid.outType, datagrid.outTypeName,datagrid.wareHouseName, datagrid.userDeptName, datagrid.remark);
						// resultGrid.unCheckAllRows();
					}
				}
			},
			onRowClick : function (e) {
				e.preventDefault();
				$("#mainOutBillNo").val( e.model['outBillNo']);
				outType = e.model['outType']
				window["detailGrid"].dataSource.page(1);
			},
			columns:[{field: "printFlag", template: function (item) {
				return $.isNumeric(item['printFlag']) && item['printFlag'] > 0 ? '是' : '否' }
			},{field: "isCheck", template: function (item) {
					let status = parseInt(item['isCheck'])
					return status == 0 ? "待仓库确认" : (status == 1 ? "已签收" : (status == 2 ? "待签收" : "已结束"));
				}
				//return parseInt(item['isCheck']) > 0 ? "已签收" : "待签收";}
			},{field: "totalAmount", template: function (item) {
					return ("05" == item['outType'] ? "-" : "") + item['totalAmount']
				}
			}],
			pageable: {
				pageSize: 50,
				pageSizes: [10, 20, 50, 100, 500]
			},
			loadComplete:function (e) {
				//录入
				$("#ADD").on("click", function(e) {
					popData1();
				});
				
				//红冲出库
				$("#HCCK").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length > 0) {
						if(checkRows.length > 1) {
							NotificationUtil("请选择一条出库记录","error");
						} else if (checkRows[0].outType == "05"){
							NotificationUtil("该单据已经红冲出库，无法再操作","error");
						} else if (checkRows[0].outType != "06"){
							NotificationUtil("该单据类型不能红冲出库","error");
						} else {
							popData2(checkRows[0].outBillNo, checkRows[0].outTypeName,checkRows[0].wareHouseNo,
								checkRows[0].wareHouseName, checkRows[0].userDeptNum, checkRows[0].userDeptName,
								checkRows[0].costDeptNum, checkRows[0].costDeptName);
						}
					} else{
						/*let redPopData = $("#redPopData");
						redPopData.data("kendoWindow").setOptions({
							open : function() {
								redPopData.data("kendoWindow").refresh({
									url : IPLATUI.CONTEXT_PATH + "/web/SICK0105",
								});
							}
						});
						redPopDataWindow.open().center();*/
						NotificationUtil("请选择需要退库的记录","error");
					}
				});

				//打印
				$("#PRINT").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择需要打印的记录","error");
					} else {
						let outBillNos = checkRows.map(row => row['outBillNo']);
						let url = IPLATUI.CONTEXT_PATH + "/web/SICK0104?initLoad&outBillNos="+ outBillNos;
						let popData = $("#popData");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url,
								});
							}
						});
						popDataWindow.open().center();
					}
				});
			}
		},
		"detail": {
			columns: [{field: "outNum", template: function (item) {
					return ("05" == outType ? "-" : "") + item['outNum']
				}
			},{field: "outAmount", template: function (item) {
					return ("05" == outType ? "-" : "") + item['outAmount']
				}
			}]
		},
	}

	/**回车键查询**/
	keydown("inqu", "QUERY");
	
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resetTime(false);
		resultGrid.dataSource.page(1);
		$("#mainOutBillNo").val('no data');
		window["detailGrid"].dataSource.page(1);
	});
})

//出库详情
function popData(outBillNo, outType, outTypeName, wareHouseName, userDeptName,remark) {
	let url = IPLATUI.CONTEXT_PATH + "/web/SICK0103?initLoad&outBillNo=" + outBillNo + "&outType=" + outType
		+ "&outTypeName=" + outTypeName + "&wareHouseName=" + wareHouseName + "&userDeptName=" + userDeptName
		+ "&remark=" + remark;
	let popData = $("#popData");
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
	let url = IPLATUI.CONTEXT_PATH + "/web/SICK0101";
	let popData = $("#popData");
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
function popData2(outBillNo, outTypeName, wareHouseNo, wareHouseName, userDeptNum, userDeptName,costDeptNum, costDeptName) {
	let url = IPLATUI.CONTEXT_PATH + "/web/SICK0102?initLoad&outBillNo=" + outBillNo + "&outTypeName=" + outTypeName
		 + "&wareHouseName=" + wareHouseName + "&wareHouseNo=" + wareHouseNo
		 + "&userDeptName=" + userDeptName + "&userDeptNum=" + userDeptNum
		 + "&costDeptNum=" + costDeptNum + "&costDeptName=" + costDeptName;
	let popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popDataWindow.open().center();
}

//批量打印
function batchPrint(outBillNos) {
	$.ajax({
		async: true,
		url: IPLATUI.CONTEXT_PATH + '/si/printOutBills',
		type: 'POST',
		contentType: 'application/json;charset=utf-8',
		data: JSON.stringify(outBillNos),
		dataType: 'json',
		success: function (data) {
		},
		error: function () {
		},
	});
}
	
	