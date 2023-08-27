$(function() {
	IPLATUI.EFGrid = {
		"result": {
			pageable : false,
			exportGrid : false,
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false, add1: false,
				buttons:[{
					name: "ADD",text: "新增",layout:"3",
					click: function () { popData(); }
				},{
					name: "ADD1",text: "食堂新增",layout:"3",
					click: function () { stpopData(); }
				},{
					name: "DEL",text: "删除",layout:"3",
					click: function () {
						var checkRows = resultGrid.getCheckedRows();
						if (checkRows.length > 0) {
							resultGrid.removeRows(checkRows);
						} else {
							IPLAT.NotificationUtil("请选择需要删除的物资")
						}
					}
				}]
			},
			loadComplete:function (e) {}
		},
	}
	
	//确定
	$("#COMFIRM").unbind('click').on('click', function(e){
		// 防止连续提交
		$("#COMFIRM").attr("disabled",true);
		setTimeout(function(){$("#COMFIRM").attr("disabled",false);},5000);

		//获取参数
		var wareHouseNo = IPLAT.EFSelect.value($("#inqu_status-0-wareHouseNo"));
		var wareHouseName = IPLAT.EFSelect.text($("#inqu_status-0-wareHouseNo"));
		//计算总价
		var grid = $("#ef_grid_result").data("kendoGrid");
		if (grid.getDataItems().length > 0) {
			grid.checkAllRows();
			var data = grid.getCheckedRows();
			for (var i = 0; i < data.length; i++) {
				var nums1 = parseFloat(data[i].enterNum);
				var price1 = parseFloat(data[i].enterPrice);
				var c = nums1*price1;
				var totalAmount ;
				totalAmount = c.toFixed(2);
				data[i].enterAmount = totalAmount;
			}

		}

		var rows = resultGrid.getDataItems();

		/*for(var index in rows){
			if(rows[index].productionDate.indexOf("-") == -1){
				rows[index].productionDate = rows[index].productionDate.toLocaleDateString().replaceAll("/","-");
			}
		}*/
		//参数检验
		if(validate(wareHouseNo,rows)){

			for(var index in rows){
				//Wed Sep 21 2022 00:00:00 GMT+0800 (中国标准时间)
				rows[index].productionDate = kendo.toString(rows[index].productionDate,"yyyy-MM-dd")
			}

			var eiInfo = new EiInfo();
			eiInfo.set("wareHouseNo", wareHouseNo);
			eiInfo.set("wareHouseName", wareHouseName);
			eiInfo.set("enterType", "06");//采购入库
			eiInfo.set("rows", rows);
			EiCommunicator.send("CIRK0101", "enterStock", eiInfo, {
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
	var enterTypeFlag=$("#enterType").val();
	//enterTypeFlag =2 表示采购入库，采购入库单独处理逻辑，获取审核通过的采购申请单
	//console.debug("enterTypeFlag="+enterTypeFlag);
	var url = IPLATUI.CONTEXT_PATH + "/web/CIRK0105?initLoad";
	if (enterTypeFlag!='2'){
		url = IPLATUI.CONTEXT_PATH + "/web/CIRK0104?initLoad";
	}

	var popData = $("#popData1");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popData1Window.open().center();
}

//食堂新增物资选择弹窗
function stpopData() {
	var url = IPLATUI.CONTEXT_PATH + "/web/CIRK0106?initLoad";
	var popData = $("#popData2");
	popData.data("kendoWindow").setOptions({
		open : function() {
			popData.data("kendoWindow").refresh({
				url : url,
			});
		}
	});
	popData2Window.open().center();
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
	for(var index in rows){
		if(!isPositiveNumber(rows[index].enterNum) || rows[index].enterNum == "0"){
			NotificationUtil("请输正确的入库数量(最多两位小数的正数)","error");
			return false;
		}
		if(!isPositiveNumber(rows[index].enterPrice)){
			NotificationUtil("请正确填写单价(最多两位小数的正数)","error");
			return false;
		}
		if(!isPositiveInteger(rows[index].shelfLife)){
			NotificationUtil("请正确填写保质期(正整数)","error");
			return false;
		}
		if(rows[index].productionDate == null || rows[index].productionDate=="" || rows[index].productionDate==undefined){
			NotificationUtil("请选择生产日期","error");
			return false;
		}
	}
	return true;
}

//添加耗材（过滤重复）
function addRows(checkRows){
	var matList = resultGrid.getDataItems();
	for (var index in checkRows) {
		var model = checkRows[index];
		var isExist = false;
		for(var i in matList) {
			var mat = matList[i];
			if(mat.matNum == model.matNum){
				isExist = true;
			} 
		}
		if(!isExist){
			//采购入库直接带入采购数量和金额
			model['enterNum'] = model['applyNum'];
			model['enterPrice'] = model['price'];
			//计算总价
			var nums1 = parseFloat(model['enterNum']);
			var price1 = parseFloat(model['enterPrice']);
			var c = nums1*price1;
			var totalAmount ;
			totalAmount = c.toFixed(2);
			model['enterAmount'] = totalAmount;
			model['shelfLife'] = '7';//保质期默认7天
			if (model instanceof kendo.data.Model){
				resultGrid.addRows(model)
			}else{
				//转换object对象为kendo.data.Model
				resultGrid.addRows(new kendo.data.Model(model))
			}

		}
	}
}

save = function(rows) {
	//关闭子页面
	popData2Window.close();
	// var grid = $("#ef_grid_result").data("kendoGrid");
	//var checkRows = resultGrid.getDataItems(), eiInfo = new EiInfo();
	var data = resultGrid.getDataItems();;// 获取选中行数据

	for (var index in rows) {
		var model = rows[index];
		var isExist = false;
		for(var i in data) {
			var mat = data[i];
			if(mat.matNum == model.matNum){
				isExist = true;
			}
		}
		if(!isExist){
			//采购入库直接带入采购数量和金额
			// model['enterNum'] = model['applyNum'];
			// alert(isExist)
			model['enterPrice'] = model['price'];
			model['shelfLife'] = '7';//保质期默认7天
			resultGrid.addRows(model);
		}
	}

	//去重
	// data = mergeArray(data, rows)
	// data['enterPrice'] = rows['price'];

	// 回填
	// grid.addRows(data)
	// grid.addRows(new kendo.data.Model(data))
	// grid.unCheckAllRows();

}

//一数组去除重复数值
function merge(data, rows) {
	for (var i = 0; i < data.length; i++) {
		var a = data[i].matNum;
		var b = rows.matNum;
		if (a == b) {
			return;
		}
	}
	return rows;
}

//两数组去除重复数值
function mergeArray(data, rows) {
	for (var i = 0; i < data.length; i++) {
		var a = data[i].idd;
		for (var j = 0; j < rows.length; j++) {
			var b = rows[j].idd;
			if (a == b) {
				//利用splice函数删除元素，从第i个位置，截取长度为1的元素
				rows.splice(j, 1);
			}
		}
	}

	return rows;
}


function isPositiveNumber(parameter){
	var regu = /^[0-9]+(\.[0-9]{1,2})?$/;
	if(regu.test(parameter)){
		return true;
	} else {
		false
	}
}
//正整数
function isPositiveInteger(parameter){
	var regu = /^[+]{0,1}(\d+)$/;
	if(regu.test(parameter)){
		return true;
	} else {
		false
	}
}