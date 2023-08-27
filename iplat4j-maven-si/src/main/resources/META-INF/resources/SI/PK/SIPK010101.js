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

	IPLATUI.EFGrid = {
		"mat": {
			height:  window.innerHeight - 210,
			pageable: {pageSize: 50, pageSizes: [10, 20, 50, 100]},
			onRowDblClick: function (e) {
				let checkRows = matGrid.getCheckedRows();
				addRows(checkRows);
			},
			//onCheckRow: function (e) {if (e.checked) {}}
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
			},
		},
		"result": {
			pageable : false,
			exportGrid : false,
			height:  window.innerHeight - 210,
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
								let price2 = $.isNumeric(result[i].price) ? +result[i].price : 0;
								let num2 = $.isNumeric(result[i].num) ? +result[i].num : 0;
								if (num2 !=0) typeCount++;
								moneyCount = moneyCount + price2*num2;
								numCount = numCount + num2;
							}
							$("#typeCount").text(typeCount);
							$("#numCount").text(numCount);
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
					"新增盘库物资总数：<span id='numCount' style='color: red'>0</span>件，" +
					"新增盘库物资总金额：<span id='moneyCount' style='color: red'>0</span>元</div>")
			},
			afterEdit: function(e) {
				let grid = e.sender; let rowIndex = e.row
				let price = $.isNumeric(e.model["price"]) ? +e.model["price"] : 0;
				let num = $.isNumeric(e.model["num"]) ? +e.model["num"] : 0;
				if (addFlag){
					let result = resultGrid.getDataItems();
					let moneyCount = 0.00,numCount=0; //总金额和总数量
					typeCount = 0; // 物资种类计算前需清零
					for (let i in result) {
						let price2 = $.isNumeric(result[i].price) ? +result[i].price : 0;
						let num2 = $.isNumeric(result[i].num) ? +result[i].num : 0;
						if (num2 !=0) typeCount++;
						moneyCount = moneyCount + price2*num2;
						numCount = numCount + num2;
					}
					$("#typeCount").text(typeCount);
					$("#numCount").text(numCount);
					$("#moneyCount").text(moneyCount.toFixed(2));
					addFlag = false;
				}
				grid.setCellValue(rowIndex, "totalAmount", (price*num).toFixed(2)+"");
				addFlag = true;
			}
		},
	}

});

/**
 * 保存入库单
 */
function save() {
	//获取参数
	let invenBillNo = $("#invenBillNo").val();
	let wareHouseName = $("#wareHouseName").val();
	let rows = resultGrid.getDataItems();
	//参数检验
	if(validate(rows)){
		let eiInfo = new EiInfo();
		eiInfo.set("wareHouseName", wareHouseName);
		eiInfo.set("invenBillNo", invenBillNo);
		eiInfo.set("rows", rows);
		EiCommunicator.send("SIPK010101", "insertPKInvenDetail", eiInfo, {
			onSuccess : function(ei) {
				if(ei.getStatus() == -1){
					NotificationUtil(ei.getMsg(), "error");
					return;
				}
				NotificationUtil("新增盘点物资成功");
				window.parent['popDataAddMatWindow'].close();
				window.parent["resultGrid"].dataSource.query(1);
			}
		})
	}
}

/**
 * 参数校验
 * @param wareHouseNo
 * @param rows
 * @returns {boolean}
 */
function validate(rows) {
	if(rows == null || rows.length == 0){
		NotificationUtil("请选择物资","error");
		return false;
	}
	for(let index in rows){
		if(!isPositiveNumber(rows[index].num) || rows[index].num == "0"){
			NotificationUtil("请输正确的盘库物资数量(最多两位小数的正数)","error");
			return false;
		}
		if(!isPositiveNumber(rows[index].price)){
			NotificationUtil("请正确填写单价(最多两位小数的正数)","error");
			return false;
		}
	}
	return true;
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
			model['num'] = '0';
			model['totalAmount'] = '0';
			resultGrid.addRows(model)
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

/**
 * 判空函数
 * @param parameter
 */
function isEmpty(parameter) {
	if (parameter == undefined || parameter == null) {
		return true;
	} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == "") {
		return true;
	} else {
		return false;
	}
}