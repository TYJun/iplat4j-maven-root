var datagrid = null; var enterType = null;
var moneyAll = 0.00;// 入库总金额
var pageMoneySum = 0.00;// 全选后记录本页总金额
$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					let grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "enterBillNo") {
					let enterBillNo = e.model['enterBillNo'];
					if (enterBillNo != " " && enterBillNo != null) {
						popData(datagrid.enterBillNo, datagrid.enterType, datagrid.enterTypeName,datagrid.wareHouseName,
							datagrid.remark, datagrid.supplierName, datagrid.userDeptName);
						// resultGrid.unCheckAllRows();
					}
				}
			},
			onRowClick : function (e) {
				e.preventDefault();
				$("#mainEnterBillNo").val( e.model['enterBillNo']);
				enterType = e.model['enterType']
				window["detailGrid"].dataSource.page(1);
			},
			columns:[{field: "printFlag", template: function (item) {
				return $.isNumeric(item['printFlag']) && item['printFlag'] > 0 ? '是' : '否' }
			},{field: "isCheck", template: function (item) {
					let status = parseInt(item['isCheck'])
					return status == 0 ? "待验收" : (status == 1 ? "待审核" : "已审核");
				}
			},{field: "totalAmount", template: function (item) {
					return ("05" == item['enterType'] ? "-" : "") + item['totalAmount']
				}
			}],
			pageable: {
				pageSize: 50,
				pageSizes: [10, 20, 50, 100, 500]
			},
			beforeRequest: function(e) {
				// 数据源发生改变（切换条数、切换页）时，入库总金额清零
				moneyAll = 0.00;
				$("#moneyCount").text(moneyAll);
			},
			onCheckAllRows: function (e) {
				// 全选事件会调用n次单选事件，如果先单选了几条再去全选，此时入库总金额会重复计算单选的那几条
				// 要把入库总金额清零，在每次单选事件中重新计算
				if (moneyAll != 0.00 && e.checked) {
					moneyAll = 0.00;
				} else if (!e.checked) {
					// 全选事件会调用n次单选事件，如果全选，单独取消几条勾选，再全选取消，会重复扣除单独取消的几条金额
					// 所以取消全选时，初始金额应该是本页总金额
					moneyAll = pageMoneySum;
				}
			},
			onCheckRow: function (e) {
				let model = e.model;
				let enterAmount = $.isNumeric(model["totalAmount"]) ? +model["totalAmount"] : 0;
				if (e.checked) {
					// 每次勾选把总金额加上，退货则减去
					if (model["enterType"]=="05") {
						moneyAll -= enterAmount
					} else {
						moneyAll += enterAmount
					}
					// 全选时调用n次单选，加上最后一条记录的金额时，pageMoneySum记录下本页总金额
					if (e.row == resultGrid.getDataItems().length-1) {
						pageMoneySum = moneyAll.toFixed(2);
					}
				} else {
					// 每次取消勾选把总金额减去，退货则加上
					if (model["enterType"]=="05") {
						moneyAll += enterAmount
					} else {
						moneyAll -= enterAmount
					}
				}
				$("#moneyCount").text(moneyAll.toFixed(2));
			},
			loadComplete:function (e) {
				$("#ef_grid_toolbar_result").prepend("<div style='float:left;font-size:13px;'>" +
					"入库总金额：<span id='moneyCount' style='color: red'>0.00</span>元</div>")
				//直入直出
				$("#ZRZC").on("click", function(e) {
					popData1();
				});

				//采购入库
				$("#CGRK").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if(checkRows.length > 1) {
						NotificationUtil("请不要选择多条记录","error");
					} else {
						popData2(checkRows.length > 0 ? checkRows[0].enterBillNo : undefined);
					}
				});
				
				//红冲入库
				$("#HCRK").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择一条记录","error");
					} else if (checkRows.length > 1){
						NotificationUtil("请不要选择多条记录","error");
					} else if (checkRows[0].enterType == "05"){
						NotificationUtil("该单据已经红冲入库，无法再操作","error");
					} else if (!["01", "06", "08"].includes(checkRows[0].enterType)){
						NotificationUtil("该单据类型无法红冲入库","error");
					} else if (checkRows[0]['isCheck'] == "0"){
						NotificationUtil("未验收或未审核的单据无需红冲","error");
					} else{
						popData3(checkRows[0].enterBillNo, checkRows[0].enterTypeName,checkRows[0].wareHouseNo,
							checkRows[0].wareHouseName, checkRows[0].supplierNum, checkRows[0].supplierName,
							checkRows[0].userDeptNum, checkRows[0].userDeptName);
					}
				});

				//编辑
				$("#RK_EDIT").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择一条记录","error");
					} else if (checkRows.length > 1){
						NotificationUtil("请不要选择多条记录","error");
					} else if (checkRows[0]['isCheck'] != "0"){
						NotificationUtil("只能编辑待验收的记录","error");
					} else {
						let url = IPLATUI.CONTEXT_PATH + "/web/SIRK0107?initLoad&enterBillNo="+ checkRows[0]['enterBillNo'];
						if(checkRows[0]['enterType'] == '05' ) {
							url = IPLATUI.CONTEXT_PATH + "/web/SIRK0108?initLoad&type=edit&t&enterBillNo="+ checkRows[0]['enterBillNo']
								+ "&originBillNo=" + checkRows[0]['originBillNo'];
						}
						let popData = $("#popDataNew");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url,
								});
							}
						});
						popDataNewWindow.open().center();
					}
				});

				//打印
				$("#PRINT").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择一条记录","error");
					} else {
						let enterBillNos = checkRows.map(row => row['enterBillNo'])
						let url = IPLATUI.CONTEXT_PATH + "/web/SIRK0105?initLoad&enterBillNos="+ enterBillNos;
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
				//导入
				$("#IMPORT").on("click", function(e) { excelImportWindow.open().center()});
			}
		},
		"detail": {
			columns: [{field: "enterNum", template: function (item) {
					return ("05" == enterType ? "-" : "") + item['enterNum']
				}
			},{field: "enterAmount", template: function (item) {
					return ("05" == enterType ? "-" : "") + item['enterAmount']
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
		$("#mainEnterBillNo").val( "no data" );
		window["detailGrid"].dataSource.page(1);
	});

	/**
	 * 提交数据导入
	 * @type {boolean}
	 */
	$("#IMPORT_SUBMIT").on("click", function (e) {
		// 防止连续提交
		$("#IMPORT_SUBMIT").attr("disabled",true);
		setTimeout(function(){$("#IMPORT_SUBMIT").attr("disabled",false);},5000);

		//导入文件
		siExcelImport(IPLATUI.CONTEXT_PATH+"/si/importEnter", "si_enter_error.xls", undefined);
	});

	/**
	 * 关闭数据导入窗口
	 */
	$("#IMPORT_CLOSE").on("click", function (e) {
		excelImportWindow.close();
	});

	/**
	 * 数据导入模板下载
	 */
	$("#download").click(function(){
		window.location.href =  IPLATUI.CONTEXT_PATH+"/si/downloadEnterTemplate";
	});
});

//入库详情弹窗
function popData(enterBillNo, enterType, enterTypeName, wareHouseName, remark, supplierName, deptName) {
	let url = IPLATUI.CONTEXT_PATH + "/web/SIRK0201?initLoad&enterBillNo="+ enterBillNo + "&enterType="+ enterType
		 + "&enterTypeName=" + enterTypeName + "&wareHouseName=" + wareHouseName + "&remark="+remark
		+ "&supplierName=" +supplierName + "&deptName="+deptName;
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

//直入直出弹窗
function popData1() {
	let url = IPLATUI.CONTEXT_PATH + "/web/SIRK0101?initLoad";
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

//采购入库
function popData2(enterBillNo) {
	let url = IPLATUI.CONTEXT_PATH + "/web/SIRK0106?initLoad"+ (enterBillNo ? `&enterBillNo=${enterBillNo}` : "");
	let popData = $("#popDataNew");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popDataNewWindow.open().center();
}

//红冲入库
function popData3(enterBillNo, enterTypeName, wareHouseNo, wareHouseName,supplierNum, supplierName,userDeptNum,userDeptName) {
	let url = IPLATUI.CONTEXT_PATH + "/web/SIRK0103?initLoad&enterBillNo="+ enterBillNo
	 	+ "&enterTypeName=" + enterTypeName + "&wareHouseNo=" + wareHouseNo + "&wareHouseName=" + wareHouseName
		+ "&supplierNum=" + supplierNum  + "&supplierName=" +supplierName
		+ "&userDeptNum="+ userDeptNum + "&userDeptName=" + userDeptName;
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