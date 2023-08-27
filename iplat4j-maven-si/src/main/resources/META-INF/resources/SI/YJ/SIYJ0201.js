$(function() {
	IPLATUI.EFDatePicker = {
		"inqu_status-0-planTime": {
			start: "year",
			depth: "year"
		},
	};
	let submitFlag = true;
	IPLATUI.EFGrid = {
		"result": {
			pageable : false,
			exportGrid : true,
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false, 
				buttons:[{
					name: "SAVE",text: "保存",layout:"3",
					click: function () {
						$("#SAVE .k-grid-SAVE").attr("disabled",true);
						setTimeout(function(){$("#SAVE .k-grid-SAVE").attr("disabled",false);},5000);
						if(!submitFlag) { return;}

						let rows =resultGrid.getDataItems();
						if(!rows || rows.length  == 0) {
							NotificationUtil("需求计划明细不能为空","error");
							return;
						}
						submitFlag = false; //防止重复提交
						let eiInfo = new EiInfo();
						eiInfo.setByNode("inqu");
						eiInfo.set("rows", rows);
						EiCommunicator.send("SIYJ0201", "genPlan", eiInfo, {
							onSuccess : function(ei) {
								NotificationUtil("操作成功");
								window.parent['popDataWindow'].close();
								//刷新查询
								window.parent["resultGrid"].dataSource.page(1);
							}
						});
						submitFlag = true;
					}
				}]
			},
			dataBinding: function (e) {
				for (let i = 0, length = e.items.length; i < length; i++) {
					if(!$.isNumeric(e.items[i].num)) {
						if(parseFloat(e.items[i].maxNum) < parseFloat(e.items[i].totalNum)) {
							e.items[i].num = 0.00
						} else {
							e.items[i].num = parseFloat(e.items[i].minNum) - parseFloat(e.items[i].totalNum);
						}
					}
				}
			},
			loadComplete:function (e) {}
		},
	}
});