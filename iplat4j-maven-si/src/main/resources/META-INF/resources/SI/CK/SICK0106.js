var datagrid = null;
let dataUidList;
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
					let dataUid = e.model.uid;
					dataUidList = $('[' + "data-uid=" + dataUid + ']' )
					let outBillNo = e.model['outBillNo'];
					if (outBillNo != " " && outBillNo != null) {
						popData(datagrid.outBillNo, datagrid.outType, datagrid.outTypeName,datagrid.wareHouseName, datagrid.userDeptName, datagrid.remark);
					}
				}
			},
			columns:[{field: "printFlag", template: function (item) {
				return $.isNumeric(item['printFlag']) && item['printFlag'] > 0 ? '是' : '否' }
			},{field: "isCheck", template: function (item) {
					let status = parseInt(item['isCheck'])
					return status == 0 ? "待仓库确认" : (status == 1 ? "已签收" : "待签收");
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
			}
		}
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
		},
		close:function (e) {
			if(dataUidList!= undefined && dataUidList!= null){
				dataUidList[0].scrollIntoView()
			}
		}
	});
	popDataWindow.open().center();
}




	
	