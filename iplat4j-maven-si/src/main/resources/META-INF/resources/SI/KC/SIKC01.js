var datagrid = null;

$(function() {
	IPLATUI.EFGrid = {
		"result" : {
			dataBound: function (e) {
				let grid = e.sender;
				let trs = grid.table.find("tr");
				$.each(trs, function (i, tr) {
					let totalNum = e.sender.dataItems()[i].totalNum;
					let minNum = e.sender.dataItems()[i].minNum;
					let maxNum = e.sender.dataItems()[i].maxNum;
					if ((minNum > 0 && minNum - totalNum > 0) || (maxNum > 0 && totalNum - maxNum > 0)) {
						tr.style.background = "#FF6347"
					}
				});
			},
			pageable: {
				pageSize: 50,
				pageSizes: [10, 20, 50, 100, 500, 1000]
			},
			onCellClick : function(e) {
				e.preventDefault();
				if (!e.fake) {
					let grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
				if (e.field === "matNum") {
					let matNum = e.model['matNum'];
					if (matNum != " " && matNum != null) {
						popData(datagrid.wareHouseNo, datagrid.matNum,datagrid.wareHouseName);
						// resultGrid.unCheckAllRows();
					}
				}
			},
			loadComplete:function (e) {
				//生成盘库单
				$("#SCPKD").on("click", function(e) {
					popData1();
				});
				$("#XZYJ").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows();
					let url = "/web/SIYJ0101"; let ids = new Array();
					if(checkRows.length > 0 && checkWareHouse(checkRows, ids)) {
						url = url + "?inqu_status-0-wareHouseNo=" + checkRows[0]['wareHouseNo'] + "&ids=" + ids.join(",");
					}
					commonPopData(url, '设置物资预警值');
				});
				//生成需求计划
				$("#GEN").on("click", function(e) {
					commonPopData("/web/SIYJ0201", "生成需求计划");
				});
				//新增调拨
				$("#XZDB").on("click", function(e) {
					let checkRows = resultGrid.getCheckedRows();
					let url = "/web/SIDB0101"; let ids = new Array();
					if(checkRows.length > 0 && checkWareHouse(checkRows, ids)) {
						url = url + "?inqu_status-0-outWareHouseNo=" + checkRows[0]['wareHouseNo'] + "&ids=" + ids.join(",");
					}
					commonPopData(url, "物资调拨");
				});
				$("#XZBF").on("click", function(e) {
					commonPopData('/web/SIBF0101?type=add', "物资报废");
				});
				$("#DC").on("click", function(e) {
					let eiInfo = new EiInfo(); let url = IPLATUI.CONTEXT_PATH+"/si/exportStorage?foo=";
					eiInfo.setByNode("inqu");
					let params = eiInfo.getBlock("inqu_status").getMappedRows()[0];
					for (let field in params) {
						if(params[field]) { url = url + "&"+field+"="+params[field]}
					}
					window.location.href = url;
				});
				// 显示库存总价和库存总量
				$("#ef_grid_toolbar_result").prepend("<div style='float:left;font-size:13px;'>库存总量：<span id='sumAll' style='color: red'>0</span>件，库存总价：<span id='moneyAll' style='color: red'>0</span>元</div>");
				// 查询更新库存总价和库存总量
				var eiInfo = new EiInfo();
				EiCommunicator.send("SIKC03", "countNumAndSum", eiInfo, {
					onSuccess : function(ei) {
						$("#sumAll").text(ei.extAttr.numAll);
						$("#moneyAll").text(ei.extAttr.moneyAll);
					}
				});
			}
		}
	}

	//选中数据处理
	IPLATUI.EFSelect = {
		"inqu_status-0-warningStock": {
			select: function (e) { //获取勾选值
				let dataItem = e.dataItem;
				if("Y" == dataItem['valueField']) {
					IPLAT.EFSelect.value($("#inqu_status-0-earlyWarning"),"Y", true);
				}
			}
		}
	}

	/**回车键查询**/
	keydown("inqu", "QUERY");

	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.page(1);
		// 查询更新库存总价和库存总量
		var eiInfo = new EiInfo();
		eiInfo.set("inqu_status-0-wareHouseNo",IPLAT.EFSelect.value($("#inqu_status-0-wareHouseNo")));
		eiInfo.set("inqu_status-0-matTypeNum",IPLAT.EFInput.value($("#inqu_status-0-matTypeNum")));
		eiInfo.set("inqu_status-0-matNum",IPLAT.EFInput.value($("#inqu_status-0-matNum")));
		eiInfo.set("inqu_status-0-matName",IPLAT.EFInput.value($("#inqu_status-0-matName")));
		eiInfo.set("inqu_status-0-isNot0",IPLAT.EFSelect.value($("#inqu_status-0-isNot0")));
		eiInfo.set("inqu_status-0-earlyWarning",IPLAT.EFSelect.value($("#inqu_status-0-earlyWarning")));
		eiInfo.set("inqu_status-0-warningStock",IPLAT.EFSelect.value($("#inqu_status-0-warningStock")));
		EiCommunicator.send("SIKC01", "countNumAndSum", eiInfo, {
			onSuccess : function(ei) {
				$("#sumAll").text(ei.extAttr.numAll);
				$("#moneyAll").text(ei.extAttr.moneyAll);
			}
		});
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
		// 更新库存总价和库存总量
		var eiInfo = new EiInfo();
		eiInfo.set("inqu_status-0-wareHouseNo",IPLAT.EFSelect.value($("#inqu_status-0-wareHouseNo")));
		eiInfo.set("inqu_status-0-matTypeNum",IPLAT.EFInput.value($("#inqu_status-0-matTypeNum")));
		eiInfo.set("inqu_status-0-matNum",IPLAT.EFInput.value($("#inqu_status-0-matNum")));
		eiInfo.set("inqu_status-0-matName",IPLAT.EFInput.value($("#inqu_status-0-matName")));
		eiInfo.set("inqu_status-0-isNot0",IPLAT.EFSelect.value($("#inqu_status-0-isNot0")));
		eiInfo.set("inqu_status-0-earlyWarning",IPLAT.EFSelect.value($("#inqu_status-0-earlyWarning")));
		eiInfo.set("inqu_status-0-warningStock",IPLAT.EFSelect.value($("#inqu_status-0-warningStock")));
		EiCommunicator.send("SIKC01", "countNumAndSum", eiInfo, {
			onSuccess : function(ei) {
				$("#sumAll").text(ei.extAttr.numAll);
				$("#moneyAll").text(ei.extAttr.moneyAll);
			}
		});
	});

})

//打开查看物资详情弹窗
function popData(wareHouseNo, matNum,wareHouseName) {
	let url = IPLATUI.CONTEXT_PATH + "/web/SIKC0101?initLoad&inqu_status-0-wareHouseNo="+wareHouseNo
		+ "&inqu_status-0-wareHouseName="+wareHouseName
		+ "&inqu_status-0-matNum="+matNum;
	let popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
				title: '物资批次明细'
			});
		}
	});
	popDataWindow.open().center();
}

//打开生成盘库单弹窗
function popData1() {
	let url = IPLATUI.CONTEXT_PATH + "/web/SIKC0102?initLoad";
	let popData = $("#popData1");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popData1Window.open().center();
}

function commonPopData(url, title) {
	url = IPLATUI.CONTEXT_PATH + url
	let popData = $("#popData");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
				title: title
			});
		}
	});
	popDataWindow.open().center();
}

function checkWareHouse(rows, ids) {
	let wareHouseArray = new Array();
	for(let row of rows) {
		wareHouseArray.push(row['wareHouseNo']);
		ids.push(row['id']);
	}
	let wareHouseSet = new Set(wareHouseArray);
	return wareHouseSet.size > 1 ? false : true;
}