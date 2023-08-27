var validator = IPLAT.Validator({id: "ADD"});//开启校验
$(function() {
	
	// 管理员
	var workNo = __ei.schemeMan;
	var name = __ei.managerName;
	IPLAT.EFPopupInput.value($("#schemeMan"),workNo);
	IPLAT.EFPopupInput.text($("#schemeMan"),name);
	// 管理科室
	var reqDeptNum = __ei.schemeDept;
	var reqDeptName = __ei.departName;
	IPLAT.EFPopupInput.value($("#schemeDept"),reqDeptNum);
	IPLAT.EFPopupInput.text($("#schemeDept"),reqDeptName);
	
	//根据管理员选中科室
	IPLATUI.EFPopupInput = {
			"schemeMan":{
				backFill: function (e) {
					debugger
					IPLAT.EFPopupInput.value($("#schemeDept"),e.model.deptNum);
					IPLAT.EFPopupInput.text($("#schemeDept"),e.model.deptName);
				},
			}
	}
	
	IPLATUI.EFGrid = {
			"device": {
				/* 隐藏EF:EFGrid功能按钮 */
				pageable : false,
				exportGrid : false,
				loadComplete: function (grid) {
					var operType= $("#operType").val();
					if(operType==""){
						operType="add";
					}
					$("#ADDDEVICE").on("click", function (e) {
						var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ010101";
						var popData = $("#popDataDevice");
						if(operType=="update"){
							NotificationUtil("对象信息不允许编辑！", "error");
						}else{
							popData.data("kendoWindow").setOptions({
								open : function() {
									popData.data("kendoWindow").refresh({
										url : url
									});
								}
							});
							popDataDeviceWindow.open().center();
						}

					});

					$("#DELETEDEVICE").click(function() {
						if(operType=="update"){
							NotificationUtil("对象信息不允许删除！", "error");
						}else{
							var checkRows = deviceGrid.getCheckedRows();
							deviceGrid.removeRows(checkRows);
						}

					});

					$("#ADDPACDEVICE").on("click", function (e) {
						if(operType=="update"){
							NotificationUtil("对象信息不允许编辑！", "error");
						}else{
							var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ010105";
							var popData = $("#popDataDevicePackage");
							popData.data("kendoWindow").setOptions({
								open : function() {
									popData.data("kendoWindow").refresh({
										url : url
									});
								}
							});
							popDataDevicePackageWindow.open().center();
							return;
						}
					});
				}
			},

			"cycle": {
				/* 隐藏EF:EFGrid功能按钮 */
				pageable : false,
				exportGrid : false,
				loadComplete: function (grid) {
					$("#ADDCYCLE").on("click", function (e) {
						var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ010102";
						var popData = $("#popDataCycle");

//						var popData = $("#popDataDevice");
//						var checkRows = cycleGrid.getCheckedRows();
//						var cycle = checkRows[0].id;
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataCycleWindow.open().center();
					});

					$("#DELETECYCLE").click(function() {
						var checkRows = cycleGrid.getCheckedRows();
						cycleGrid.removeRows(checkRows);
					});
				}
			},

			"item": {
				/* 隐藏EF:EFGrid功能按钮 */
				pageable : false,
				exportGrid : false,
				loadComplete: function (grid) {
					$("#ADDITEM").on("click", function (e) {
						var schemeID= $("#schemeID").val();
						var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ010103?schemeID="+schemeID;
						var popData = $("#popDataItem");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataItemWindow.open().center();
					});

					$("#DELETEITEM").click(function() {
						var checkRows = itemGrid.getCheckedRows();
						itemGrid.removeRows(checkRows);
					});

					$("#ADDCARD").click(
						function() {
							var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ010106?"
									+ "initLoad";
							var popData = $("#popDataItemCard");
							popData.data("kendoWindow").setOptions({
								open : function() {
									popData.data("kendoWindow").refresh({
										url : url
									});
								}
							});
							popDataItemCardWindow.open().center();
						});

					$("#ADDPACITEM").on("click", function (e) {
						NotificationUtil("该功能暂未实现！", "error");
						return;
					});

				}
			},

			"exman": {
				/* 隐藏EF:EFGrid功能按钮 */
				pageable : false,
				exportGrid : false,
				loadComplete: function (grid) {
					$("#ADDEXMAN").on("click", function (e) {
						var deptName=IPLAT.EFPopupInput.text($("#schemeDept"));
						var url = IPLATUI.CONTEXT_PATH + "/web/IMJZ010104?deptName="+deptName;
						var popData = $("#popDataExman");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataExmanWindow.open().center();
					});

					$("#DELETEEXMAN").click(function() {
						var checkRows = exmanGrid.getCheckedRows();
						exmanGrid.removeRows(checkRows);
					});
				}
			}

	};


	$("#SUBMIT").click(function() {
		var operType= $("#operType").val();
		if(operType==""){
			operType="add";
		}
		var info = new EiInfo();
		//列表数据
		var deviceList = deviceGrid.getDataItems();
		var cycleList = cycleGrid.getDataItems();
		var itemList = itemGrid.getDataItems();
		var exmanList = exmanGrid.getDataItems();

		//基准数据
		var schemeID = IPLAT.EFInput.value($("#schemeID"));
		var schemeCode = IPLAT.EFInput.value($("#schemeCode"));
		var schemeName = IPLAT.EFInput.value($("#schemeName"));
		var departNo = IPLAT.EFInput.value($("#schemeDept"));
		var departName = IPLAT.EFPopupInput.text($("#schemeDept"));
		var managerNo = IPLAT.EFInput.value($("#schemeMan"));
		var managerName = IPLAT.EFPopupInput.text($("#schemeMan"));
		var activeTime = IPLAT.EFInput.value($("#activeTime"));
		var smsSend = IPLAT.EFSelect.value($("#smsSend"));	
		var timeoutReminderTime = IPLAT.EFInput.value($("#timeoutReminderTime"));
		var jobContent = IPLAT.EFInput.value($("#jobContent"));

		//参数校验
		if(!validate(schemeName,departNo,managerNo,activeTime,timeoutReminderTime)){
			return;
		}
		//时间判断
		var data = getDatetime();
		for(var i=0;i<cycleList.length;i++){
			var startTime = cycleList[i].startTime;
			if(startTime<data){
				NotificationUtil("周期开始时间不能小于当前时间", "error");
				return;
			}
		}
		

		info.set("deviceList", deviceList);
		info.set("cycleList", cycleList);
		info.set("itemList", itemList);
		info.set("exmanList", exmanList);
		info.set("schemeID", schemeID);
		info.set("schemeCode", schemeCode);
		info.set("schemeName", schemeName);
		info.set("departNo", departNo);
		info.set("departName", departName);
		info.set("managerNo", managerNo);
		info.set("managerName", managerName);
		info.set("activeTime", activeTime);
		info.set("smsSend", smsSend);
		info.set("timeoutReminderTime", timeoutReminderTime);
		info.set("jobContent", jobContent);
		info.set("operType", operType);

		EiCommunicator.send("IMJZ0101", "insertScheme", info, {
			onSuccess : function(ei) {
				var status = ei.getStatus();
				if(status!=0){
					NotificationUtil(ei.getMsg(),"error");
				}else{
					NotificationUtil(ei.getMsg(), "success");
					window.parent.resultGrid.dataSource.page(1);
					window.parent['popDataWindow'].close();
					window.parent['popDataModifyWindow'].close();
				}

			}
		});
	});

	function validate(schemeName,departID,managerID,activeTime,timeoutReminderTime) {
		if(isEmpty(schemeName)){
			NotificationUtil("基准名称不能为空", "error");
			return false;
		} 
		if(isEmpty(departID) || departID=="--请选择--"){
			NotificationUtil("管理科室不能为空", "error");
			return false;
		} 
		if(isEmpty(managerID) || managerID=="--请选择--"){
			NotificationUtil("管理员不能为空", "error");
			return false;
		} 
		if(isEmpty(managerID)){
			NotificationUtil("管理员不能为空", "error");
			return false;
		} 
		if(!isNumber(activeTime) ){
			NotificationUtil("有效时间请输入正整数", "error");
			return false;
		} 
		if(!isEmpty(timeoutReminderTime) && !isNumber(timeoutReminderTime)){
			NotificationUtil("超时提前提醒时间请输入正整数", "error");
			return false;
		}  
		//校验有效时间
		if(activeTime > 8760){
			NotificationUtil("有效时间不能大于一年", "error");
			return false;
		}
		
		return true;
	}

	
	
	function isEmpty(parameter){
		if(parameter == undefined || parameter == null){
			return true;
		} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
			return true;
		} else {
			return false;
		}
	}

	function isNumber(parameter) {
		if(isEmpty(parameter)){
			return false;
		}
		var reg = /^[0-9]*[1-9][0-9]*$/;
		return reg.test(parameter);;
	}
	
	//巡检对象去重
	save = function(rows) {
		debugger
		//关闭子页面
		var grid = $("#ef_grid_device").data("kendoGrid");
		if (grid.getDataItems().length > 0) {
			grid.checkAllRows();
		}
		var data = grid.getCheckedRows();// 获取选中行数据
		if(Array.isArray(rows)){
			//去重
			data = mergeArray(data, rows)
		}else{
			//去重
			data = merge(data, rows)
		}
		/*if(data == 1){
			NotificationUtil("请勿添加重复对象", "error");
			return;
		}*/
		// 回填
		grid.addRows(data)
		grid.unCheckAllRows();
	}
	//一数组去除重复数值
	function merge(data, rows) {
		for (var i = 0; i < data.length; i++) {
			var a = data[i].machineCode;
			var b = rows.machineCode;
				if (a == b) {
					return;
				}
		}
		return rows;
	}
	//两数组去除重复数值
	function mergeArray(data, rows) {
		for (var i = 0; i < data.length; i++) {
			var a = data[i].objName;
			for (var j = 0; j < rows.length; j++) {
				var b = rows[j].objName;
				if (a == b) {
					//利用splice函数删除元素，从第i个位置，截取长度为1的元素
					rows.splice(j, 1);
				}
			}
		}
		return rows;
	}
	
	
	//巡检项目去重
	saveItem = function(rows) {
		//关闭子页面
		var grid = $("#ef_grid_item").data("kendoGrid");
		if (grid.getDataItems().length > 0) {
			grid.checkAllRows();
		}
		var data = grid.getCheckedRows();// 获取选中行数据
		//去重
		data = mergeArray2(data, rows)
		// 回填
		grid.addRows(data)
		grid.unCheckAllRows();
	}
	
	//两数组去除重复数值
	function mergeArray2(data, rows) {
		for (var i = 0; i < data.length; i++) {
			var a = data[i].content;
			for (var j = 0; j < rows.length; j++) {
				var b = rows[j].content;
				if (a == b) {
					//利用splice函数删除元素，从第i个位置，截取长度为1的元素
					rows.splice(j, 1);
				}
			}
		}
		return rows;
	}
	
	//巡检执行人去重
	saveExman = function(rows) {
		//关闭子页面
		var grid = $("#ef_grid_exman").data("kendoGrid");
		if (grid.getDataItems().length > 0) {
			grid.checkAllRows();
		}
		var data = grid.getCheckedRows();// 获取选中行数据
		//去重
		data = mergeArray3(data, rows)
		// 回填
		grid.addRows(data)
		grid.unCheckAllRows();
	}
	
	//两数组去除重复数值
	function mergeArray3(data, rows) {
		for (var i = 0; i < data.length; i++) {
			var a = data[i].workNo;
			for (var j = 0; j < rows.length; j++) {
				var b = rows[j].workNo;
				if (a == b) {
					//利用splice函数删除元素，从第i个位置，截取长度为1的元素
					rows.splice(j, 1);
				}
			}
		}
		return rows;
	}
	
	//获取当前时间
	function getDatetime(){
	    var dt = new Date();
	    return (dt.getFullYear()+'-'+(dt.getMonth()+1)+'-'+dt.getDate()+' '+dt.getHours()+':'+dt.getMinutes()+':'+dt.getSeconds()).replace(/([\-\: ])(\d{1})(?!\d)/g,'$10$2');
	}
});