$(function(){
	/**1.表格初始化**/
	IPLATUI.EFGrid = new WilpGrid({

		add: false, edit: false, del: false,
		extentMethod: [{
			buttonId: "CHANGE",
			call: function () {
				let checkRows = resultGrid.getCheckedRows();
				if (checkRows.length < 1) {
					NotificationUtil("请选择需要变更的合同", "error");
				} else {
					popData("MPHT0201?inqu_status-0-id="+checkRows[0].id + "&type=edit")
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
		resultGrid.dataSource.page(1);
	});
})






