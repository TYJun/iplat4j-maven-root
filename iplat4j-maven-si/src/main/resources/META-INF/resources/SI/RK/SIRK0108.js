let typeCount = 0; // 计算物资种类
let addFlag = true;// afterEdit会执行两次，总价会重复计算两次，用该变量控制只计算一次
$(function() {

	/**查询**/
	$("#QUERY").on("click", function(e) {
		matGrid.dataSource.page(1);
	});

	/**重置**/
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		matGrid.dataSource.page(1);
	});

	/**回车键查询**/
	keydown("inqu", "QUERY");

	IPLATUI.EFGrid = {
		"mat": {
			height:  window.innerHeight - 210,
			pageable: {pageSize: 50, pageSizes: [10, 20, 50, 100]},
			onRowDblClick: function (e) {
				let checkRows = matGrid.getCheckedRows();
				addRows(checkRows);
			},
			toolbarConfig: {
				buttons: [{
					name: "SURE",text: "确定",layout:"3",
					click: function () {
						let checkRows = matGrid.getCheckedRows();
						if (checkRows && checkRows.length > 0) {
							addRows(checkRows);
						} else {
							NotificationUtil("请选择物资", "warning");
						}
					}
				}]
			}
		},
		"result": {
			pageable : false,
			exportGrid : false,
			columns: [{field: "redRushNum", template: function (item) {
					if ($.isNumeric(item['redRushNum']) && item['redRushNum'] !=0 ) {
						return -Math.abs(item['redRushNum']) }
			}}],
			dataBinding: function (e) {
				for (let i = 0, length = e.items.length; i < length; i++) {
					if($.isNumeric(e.items[i].totalRedRushNum)){
						e.items[i].totalRedRushNum = parseFloat(e.items[i].totalRedRushNum).toFixed(2);
					}
				}
			},
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false,
				buttons:[{
					name: "DEL",text: "删除",layout:"3",
					click: function () {
						let checkRows = resultGrid.getCheckedRows();
						if (checkRows.length > 0) {
							resultGrid.removeRows(checkRows);
							// 删除表格数据后，重新计算物资种类、总金额、总数量
							let result = resultGrid.getDataItems();
							let moneyCount = 0.00,numCount=0; //总金额和总数量
							typeCount = 0; // 物资种类计算前需清零
							for (let i in result) {
								let price2 = $.isNumeric(result[i].enterPrice) ? +result[i].enterPrice : 0;
								let num2 = $.isNumeric(result[i].enterNum) ? +result[i].enterNum : 0;
								if (num2 !=0) typeCount++;
								moneyCount = moneyCount + price2*num2;
								numCount = numCount + num2;
							}
							$("#typeCount").text(typeCount);
							$("#numCount").text(numCount.toFixed(2));
							$("#moneyCount").text(moneyCount.toFixed(2));
						} else {
							IPLAT.NotificationUtil("请选择需要删除的物资")
						}
					}
				},{
					name: "SAVE",text: "保存",layout:"3",
					click:function (){
						$("#SAVE .k-grid-SAVE").attr("disabled", true);
						setTimeout(function () {$("#SAVE .k-grid-SAVE").attr("disabled", false);}, 5000);
						save();
					}
				}]
			},
			loadComplete:function (e) {
				$("#ef_grid_toolbar_result").prepend("<div style='float:left;font-size:13px;'>" +
					"物资种类：<span id='typeCount' style='color: red'>" + typeCount + "</span>类，" +
					"入库总数：<span id='numCount' style='color: red'>0</span>件，" +
					"入库总金额：<span id='moneyCount' style='color: red'>0</span>元</div>");


			},
			afterEdit: function(e) {
				/*let grid = e.sender; let rowIndex = e.row
				let price = $.isNumeric(e.model["enterPrice"]) ? +e.model["enterPrice"] : 0;
				let num = $.isNumeric(e.model["redRushNum"]) ? + Math.abs(e.model["redRushNum"]) : 0;
				grid.setCellValue(rowIndex, "enterAmount", (-1*price*num).toFixed(2)+"");*/
				if (addFlag){
					let result = resultGrid.getDataItems();
					let moneyCount = 0.00,numCount=0; //总金额和总数量
					typeCount = 0; // 物资种类计算前需清零
					for (let i in result) {
						let price2 = $.isNumeric(result[i].enterPrice) ? +result[i].enterPrice : 0;
						let num2 = $.isNumeric(result[i].enterNum) ? +result[i].enterNum : 0;
						if (num2 !=0) typeCount++;
						moneyCount = moneyCount + price2*num2;
						numCount = numCount + num2;
					}
					$("#typeCount").text(typeCount);
					$("#numCount").text(numCount.toFixed(2));
					$("#moneyCount").text(moneyCount.toFixed(2));
					addFlag = false;
				}
				addFlag = true;
			}
		},
	}

});

/**
 * 保存入库单
 */
function save() {
	/**获取参数**/
	let eiInfo = new EiInfo(); let saveInfo = new EiInfo();
	eiInfo.setByNode("redrush");
	let formData = eiInfo.getBlock("redRush").getMappedRows()[0]
	let rows = resultGrid.getDataItems();
	/**校验明细**/
	let newRows = new Array();
	for(let row of rows){
		if($.isNumeric(row.redRushNum) && parseFloat(row.redRushNum) != 0) {
			let remainNum = parseFloat(row.enterNum) - parseFloat(row['totalRedRushNum']) + parseFloat(row['tempNum']);
			if(Math.abs(row.redRushNum) > remainNum){
				NotificationUtil("红冲数量不能大于入库数量减去已红冲数量","error");
				return;
			}
			//将数量转成正数
			row.redRushNum = Math.abs(row.redRushNum)+"";
			newRows.push(row);
		}
	}
	if(newRows.length == 0){
		NotificationUtil("请正确输入红冲数量或红冲数量不能全部为空","error");
		return;
	}

	//新增
	saveInfo.setAttr(formData)
	saveInfo.set("rows", newRows);
	saveInfo.set("enterType", "05");//退库(红冲)
	if($("#type").val() == "add") {
		EiCommunicator.send("SIRK0101", "enterStock", saveInfo, {
			onSuccess : function(ei) {
				if(ei.getStatus() == -1){
					NotificationUtil(ei.getMsg(), "error");
					return;
				}
				NotificationUtil("入库成功");
				window.parent['popDataWindow'].close();
				window.parent["resultGrid"].dataSource.page(1);
			}
		})
	} else{
		//编辑
		EiCommunicator.send("SIRK0107", "editEnter", saveInfo, {
			onSuccess : function(ei) {
				if(ei.getStatus() == -1){
					NotificationUtil(ei.getMsg(), "error");
					return;
				}
				NotificationUtil("编辑成功");
				window.parent['popDataNewWindow'].close();
				window.parent["resultGrid"].dataSource.page(1);
			}
		})
	}
}

/**
 * 添加物资，过滤重复物资
 * @param checkRows
 */
function addRows(checkRows){
	let matList = resultGrid.getDataItems();
	for (let index in checkRows) {
		let model = checkRows[index];
		let isExist = false;
		for(let i in matList) {
			let mat = matList[i];
			if(mat.matNum == model.matNum){
				isExist = true;
			}
		}
		if(!isExist){
			model['redRushNum'] = '0';
			model['tempNum'] = '0';
			resultGrid.addRows(model,true, true)
		}
	}

}

/**
 * 校验数字书否是正数
 * @param parameter
 * @returns {boolean}
 */
function isPositiveNumber(parameter){
	let regu = /^[0-9]+(\.[0-9]{1,2})?$/;
	if(regu.test(parameter)){
		return true;
	} else {
		return false
	}
}