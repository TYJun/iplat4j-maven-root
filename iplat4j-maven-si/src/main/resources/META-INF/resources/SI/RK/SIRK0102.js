let typeCount = 0; // 计算物资种类
let addFlag = true;// afterEdit会执行两次，总价会重复计算两次，用该变量控制只计算一次
$(function() {
	__eiInfo.set("controlId","01");
	if(__ei['enterBillNo']) {
		$("#inqu_status-0-supplierNum").val(__eiInfo.get("inqu_status-0-supplierNum"));
		$("#inqu_status-0-supplierName").val(__eiInfo.get("inqu_status-0-supplierName"));
		/*IPLAT.EFPopupInput.setAllFields( $("#inqu_status-0-supplierNum") ,
		__eiInfo.get("inqu_status-0-supplierNum") ,__eiInfo.get("inqu_status-0-supplierName"))*/
	}

	//根据供应商回显上一次的这个供应商的上次入库记录
	/*IPLATUI.EFPopupInput = {
		"inqu_status-0-supplierNum": {
			backFill: function (e) { reloadGrid(e.model['supplierCode']);}
		}
	};*/

	/**供应商自动补全**/
	$("#inqu_status-0-supplierName").kendoAutoComplete({
       dataSource: getDataSources("SITY02/selectSupplier", "supplier",[{name:'supplierName',id:"inqu_status-0-supplierName"}]),
       dataTextField: "supplierName",
       filter:"contains",
       select:function(e){
           let dataItem = this.dataItem(e.item.index());
           $("#inqu_status-0-supplierNum").val(dataItem.supplierCode);
		   reloadGrid(dataItem.supplierCode);
       }
   	});

	/**领用科室自动补全**/
	$("#inqu_status-0-deptName").kendoAutoComplete({
		dataSource: getDataSources("SITY02/selectBusinessDept", "b_dept",[{name:'deptName',id:"inqu_status-0-deptName"}]),
		dataTextField: "deptName",
		filter:"contains",
		select:function(e){
			let dataItem = this.dataItem(e.item.index());
			$("#inqu_status-0-deptNum").val(dataItem['deptNum']);
		}
	});

	IPLATUI.EFGrid = {
		"result": {
			pageable : false,
			exportGrid : false,
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false, 
				buttons:[{
					name: "ADD",text: "新增",layout:"3",
					click: function () { popData(); }
				},{
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
						let rows=resultGrid.getDataItems();
						let eiInfo = new EiInfo();
						eiInfo.set("rows", rows);
						eiInfo.set("pageId","SIRK0102");
						eiInfo.set("controlId","01");
						EiCommunicator.send("TSFW01", "saveTempData", eiInfo, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg());
							}
						})
					}
				}]
			},
			loadComplete:function (e) {
				$("#ef_grid_toolbar_result").prepend("<div style='float:left;font-size:13px;'>" +
					"物资种类：<span id='typeCount' style='color: red'>" + typeCount + "</span>类，" +
					"入库总数：<span id='numCount' style='color: red'>0</span>件，" +
					"入库总金额：<span id='moneyCount' style='color: red'>0</span>元</div>")
			},
			afterEdit: function(e) {
				let grid = e.sender; let rowIndex = e.row
				let price = $.isNumeric(e.model["enterPrice"]) ? +e.model["enterPrice"] : 0;
				let num = $.isNumeric(e.model["enterNum"]) ? +e.model["enterNum"] : 0;
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
				grid.setCellValue(rowIndex, "enterAmount", (price*num).toFixed(2)+"");
				addFlag = true;
			}
		},
	}
	
	//确定
	$("#COMFIRM").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#COMFIRM").attr("disabled",true);
		setTimeout(function(){$("#COMFIRM").attr("disabled",false);},5000);

		//获取参数
		let wareHouseNo = IPLAT.EFSelect.value($("#inqu_status-0-wareHouseNo"));
		let wareHouseName = IPLAT.EFSelect.text($("#inqu_status-0-wareHouseNo"));
		let supplierNum = $("#inqu_status-0-supplierNum").val();
		let supplierName = $("#inqu_status-0-supplierName").val();
		let deptNum = $("#inqu_status-0-deptNum").val();
		let deptName = $("#inqu_status-0-deptName").val();
		let remark = $("#inqu_status-0-remark").val();
		let rows = resultGrid.getDataItems();
		//参数检验
		if(validate(wareHouseNo,rows)){
			let eiInfo = new EiInfo();
			eiInfo.set("wareHouseNo", wareHouseNo);
			eiInfo.set("wareHouseName", wareHouseName);
			eiInfo.set("remark", remark);
			if(!deptNum) {
				eiInfo.set("enterType", "08");//手工入库
			} else {
				eiInfo.set("enterType", "01");//直入直出
				eiInfo.set("userDeptNum", deptNum);
				eiInfo.set("userDeptName", deptName);
			}
			eiInfo.set("supplierNum", supplierNum);
			eiInfo.set("supplierName", supplierName);
			eiInfo.set("rows", rows);
			EiCommunicator.send("SIRK0101", "enterStock", eiInfo, {
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
		}
	});
	
	//取消
	$("#CLOSE").on("click", function(e) {
		window.parent['popDataWindow'].close();
	});
});

//物资选择弹窗
function popData() {
	let url = IPLATUI.CONTEXT_PATH + "/web/SIRK0104?initLoad";
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

//参数校验
function validate(wareHouseNo,rows) {
	if(wareHouseNo == null || wareHouseNo=="" || wareHouseNo==undefined){
		NotificationUtil("请选择仓库","error");
		return false;
	}
	if(rows == null || rows.length == 0){
		NotificationUtil("请选择物资","error");
		return false;
	}
	for(let index in rows){
		if(!isPositiveNumber(rows[index].enterNum) || rows[index].enterNum == "0"){
			NotificationUtil("请输正确的入库数量(最多两位小数的正数)","error");
			return false;
		}
		if(!isPositiveNumber(rows[index].enterPrice)){
			NotificationUtil("请正确填写单价(最多两位小数的正数)","error");
			return false;
		}
	}
	return true;
}

//添加耗材（过滤重复）
function addRows(checkRows){
	let chooseRows = new Array();
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
		model['enterPrice'] = model['price'];
		if(!isExist){
			model['enterNum'] = '0';
			model['enterAmount'] = '0';
			resultGrid.addRows(model)
		}
		chooseRows.push(model);
	}
	resultGrid.setCheckedRows(chooseRows)
}

function isPositiveNumber(parameter){
	let regu = /^[0-9]+(\.[0-9]{1,2})?$/;
	if(regu.test(parameter)){
		return true;
	} else {
		return false
	}
}

function isPositiveInteger(parameter) {
	let regu = /^[0-9]+$/;
	if(regu.test(parameter)){
		return true;
	} else {
		return false
	}
}

/**
 * 根据供应商刷新表格数据
 * @param supplierNum
 */
function reloadGrid(supplierNum) {
	/**表格存在数据，跳过**/
	let rows = resultGrid.getDataItems();
	if(rows.length > 0) { return; }

	let eiInfo = new EiInfo();
	eiInfo.set("supplierNum", supplierNum)
	EiCommunicator.send("SIRK0102", "queryLastEnterBySupplier", eiInfo, {
		onSuccess : function(ei) {
			resultGrid.setEiInfo(ei);
		}
	})
}