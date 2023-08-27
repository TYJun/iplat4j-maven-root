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
				$("#mainEnterBillNo").val( e.model['enterBillNo']);
				inInfo.set( "mainEnterBillNo",e.model['enterBillNo']);
				EiCommunicator.send("CIRK01","query", inInfo, {
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
				// if (e.field === "enterBillNo") {
				// 	var enterBillNo = e.model['enterBillNo'];
				// 	if (enterBillNo != " " && enterBillNo != null) {
				// 		popData(datagrid.enterBillNo, datagrid.enterTypeName,datagrid.wareHouseName);
				// 		resultGrid.unCheckAllRows();
				// 	}
				// }
			},
			loadComplete:function (e) {
				//直入直出
				$("#ZRZC").on("click", function(e) {
					popData1();
				});

				//采购入库
				$("#CGRK").on("click", function(e) {
					popData2();
				});
				
				//红冲入库
				$("#HCRK").on("click", function(e) {
					var checkRows = resultGrid.getCheckedRows()
					if (checkRows.length < 1) {
						NotificationUtil("请先选择一条记录","error");
					} else if (checkRows[0].enterTypeName == "红冲入库"){
						NotificationUtil("该单据已经红冲入库，无法再操作","error");
					}
					// else if (checkRows[0].enterType != "06"){
					// 	NotificationUtil("该单据类型无法红冲入库","error");
					// }
					else{
						popData3(checkRows[0].enterBillNo, checkRows[0].enterTypeName,
								 checkRows[0].wareHouseNo, checkRows[0].wareHouseName);
					}
				});
			}
		},
		"processsResult":{
			pageable : true,
			exportGrid : false,
		}
	};

	
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});
});

//入库详情弹窗
function popData(enterBillNo, enterTypeName, wareHouseName) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIRK0201?initLoad&enterBillNo="+ enterBillNo
		 + "&enterTypeName=" + enterTypeName + "&wareHouseName=" + wareHouseName;
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

//直入直出弹窗
function popData1() {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIRK0101?initLoad";
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

//采购入库 enterType=2 为采购入库
function popData2() {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIRK0102?initLoad&enterType=2";
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

//红冲入库
function popData3(enterBillNo, enterTypeName, wareHouseNo, wareHouseName) {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIRK0103?initLoad&enterBillNo="+ enterBillNo
	 	+ "&enterTypeName=" + enterTypeName + "&wareHouseNo=" + wareHouseNo
	 	+ "&wareHouseName=" + wareHouseName;
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