var picList = [];
$(function(){
	//回显赋值
	IPLAT.EFPopupInput.text($("#managerDeptId"),__ei.managerDeptName);
	IPLAT.EFPopupInput.text($("#chargeUserId"),__ei.chargeUserName);
	IPLAT.EFPopupInput.text($("#managerManId"),__ei.managerManName);
	IPLAT.EFPopupInput.text($("#fixedId"),__ei.fixedPlace);
	IPLAT.EFPopupInput.text($("#machineTypeId"),__ei.classifyName);
	IPLAT.EFPopupInput.text($("#useDeaprtId"),__ei.useDeaprtName);

	var spotId = "";
	var spotCode = "";
	var spotName = "";
	var building = "";
	var floor = "";
	IPLATUI.EFTreeInput = {
		"machineTypeId":{
			/**
			 * Tree 中数据回填触发事件
			 * e.tree Tree 对象
			 * e.node 待回填的树结点数据对象（勾选回填时是集合）
			 */
			backFill: function (e) {
				console.log("33");
				$("#machineTypeId").val(e.node.id);
				resultAGrid.dataSource.page(1);
			},
		}
	}

	IPLATUI.EFPopupInput = {
		"fixedId": {
			backFill: function (e) {
				debugger
//					console.log(e.model['building'])
//					console.log(e.model['floor'])
// 				IPLAT.EFInput.value($("#building"),e.model['building']);
// 				IPLAT.EFInput.value($("#floor"),e.model['floor']);
				spotId = e.model['spotId'];
				spotCode = e.model['spotNum'];
				spotName = e.model['spotName'];
				building = e.model['building'];
				floor = e.model['floor'];
			},
		},
	}


	$("#RESAVE").click(function(){
		//判断
	 validator = IPLAT.Validator({
		id: "dfsb1"
	});
	if (validator.validate()) {
//		debugger
		 eiInfo = new EiInfo();
		var fileList = resultCGrid.getDataItems();
		var deviceList = resultBGrid.getDataItems();
		var picList = resultDGrid.getDataItems();
		//周期判断
		if(deviceList==null){
			NotificationUtil("周期不能为空", "error");
			return;
		}
		//循环校验周期
		//var data = new Date();
		for(var i=0;i<deviceList.length;i++){
			var thisCheckDate = kendo.toString(deviceList[i].thisCheckDate, "yyyy-MM-dd HH:mm:ss");
			var nextCheckDate = kendo.toString(deviceList[i].nextCheckDate, "yyyy-MM-dd HH:mm:ss");
			//校验时间
			if(thisCheckDate=="" || thisCheckDate==null){
				NotificationUtil("第"+(i+1)+"行的开始时间不能为空", "error");
				return;
			}
			//校验时间
			if(nextCheckDate=="" || nextCheckDate==null){
				NotificationUtil("第"+(i+1)+"行的开始时间不能为空", "error");
				return;
			}
			if(thisCheckDate>nextCheckDate){
				NotificationUtil("第"+(i+1)+"行的本次检验时间不能大于下次检验时间", "error");
				return;
			}
			deviceList[i].thisCheckDate=thisCheckDate;
			deviceList[i].nextCheckDate=nextCheckDate;
		}
		 id = IPLAT.EFInput.value($("#id"));
		 machineCode = IPLAT.EFInput.value($("#machineCode"));
		 machineName = IPLAT.EFInput.value($("#machineName"));
		 models = IPLAT.EFInput.value($("#models"));
		 machineTypeId = IPLAT.EFInput.value($("#machineTypeId"));
		 statusCode = IPLAT.EFSelect.value($("#statusCode"));
		 fixedId = IPLAT.EFPopupInput.value($("#fixedId"));
		 fixedPlace = IPLAT.EFPopupInput.text($("#fixedId"));
		 innerMachineCode = IPLAT.EFInput.value($("#innerMachineCode"));
		 documentNo = IPLAT.EFInput.value($("#documentNo"));
		 workMedia = IPLAT.EFInput.value($("#workMedia"));
		 useCertNo = IPLAT.EFInput.value($("#useCertNo"));
		 useArea = IPLAT.EFInput.value($("#useArea"));
		 registerCode = IPLAT.EFInput.value($("#registerCode"));
		 registerDate = IPLAT.EFInput.value($("#registerDate"));
		 outFactoryDate = IPLAT.EFInput.value($("#outFactoryDate"));
		 fixedTime = IPLAT.EFInput.value($("#fixedTime"));
		 useTime = IPLAT.EFInput.value($("#useTime"));
		 nonuseDate = IPLAT.EFInput.value($("#nonuseDate"));
		 managerDeptId = IPLAT.EFPopupInput.value($("#managerDeptId"));
		 managerDeptName = IPLAT.EFPopupInput.text($("#managerDeptId"));
		 managerManId = IPLAT.EFInput.value($("#managerManId"));
		 managerManName = IPLAT.EFPopupInput.text($("#managerManId"));
		 chargeUserId = IPLAT.EFInput.value($("#chargeUserId"));
		 chargeUserName = IPLAT.EFPopupInput.text($("#chargeUserId"));
		 useFor = IPLAT.EFInput.value($("#useFor"));
		 useDeaprtId = IPLAT.EFPopupInput.value($("#useDeaprtId"));
		 useDeaprtName = IPLAT.EFPopupInput.text($("#useDeaprtId"));
		 relatedDevice = IPLAT.EFInput.value($("#relatedDevice"));
		 needScan = IPLAT.EFSelect.value($("#needScan"));
		 manufacturerName = IPLAT.EFInput.value($("#manufacturerName"));
		 manufacturerCertno = IPLAT.EFInput.value($("#manufacturerCertno"));
		 fixedUnit = IPLAT.EFInput.value($("#fixedUnit"));
		 fixedCertno = IPLAT.EFInput.value($("#fixedCertno"));
		 maintUnit = IPLAT.EFInput.value($("#maintUnit"));
		 maintCertno = IPLAT.EFInput.value($("#maintCertno"));
		 checkUnit = IPLAT.EFInput.value($("#checkUnit"));
		 checkCertno = IPLAT.EFInput.value($("#checkCertno"));
		 thisCheckDate = IPLAT.EFInput.value($("#thisCheckDate"));
		 thisFinishDate  = IPLAT.EFInput.value($("#thisFinishDate "));
		 nextCheckDate  = IPLAT.EFInput.value($("#nextCheckDate"));
		 annualinspcycle = IPLAT.EFInput.value($("#annualinspcycle"));
		 thisExpiredDate = IPLAT.EFInput.value($("#thisExpiredDate"));
		 thisChexpiredDate = IPLAT.EFInput.value($("#thisChexpiredDate"));
		 nextExpiredDate = IPLAT.EFInput.value($("#nextExpiredDate"));
		 regularinspcycle = IPLAT.EFInput.value($("#regularinspcycle"));

		//参数校验
		if(!validate(machineName,chargeUserId,managerManId,managerDeptId,useDeaprtId,fixedPlace)){
			return;
		}

		//参数校验
		 var start= new Date(thisCheckDate);
		 var end= new Date(thisFinishDate);
		 var next= new Date(nextCheckDate);

		 var start1= new Date(thisExpiredDate);
		 var end1= new Date(thisChexpiredDate);
		 var next1= new Date(nextExpiredDate);
				if(start>=end){
					NotificationUtil("本次年度检验日不能大于本次年度检验完工日期", "error");
					return ;
				}
				if(end>=next){
					NotificationUtil("本次年度检验完工日不能大于下次年度检验日", "error");
					return ;
				}
				if(start1>=end1){
					NotificationUtil("本次到期检验时间不能大于本次到期检验完工日期", "error");
					return ;
				}
				if(end1>=next1){
					NotificationUtil("本次到期检验完工日期不能大于下次检查时间", "error");
					return ;
				}
		eiInfo.set("deviceList",deviceList);
		eiInfo.set("fileList",fileList);
		eiInfo.set("picList",picList);
		eiInfo.set("spotCode",spotCode);
		eiInfo.set("building",building);
		eiInfo.set("floor",floor);
		eiInfo.set("id",id);
		eiInfo.set("machineCode",machineCode);
		eiInfo.set("machineName",machineName);
		eiInfo.set("models",models);
		eiInfo.set("machineTypeId",machineTypeId);
		eiInfo.set("statusCode",statusCode);
		eiInfo.set("fixedId",fixedId);
		eiInfo.set("fixedPlace",fixedPlace);
		eiInfo.set("innerMachineCode",innerMachineCode);
		eiInfo.set("documentNo",documentNo);
		eiInfo.set("workMedia",workMedia);
		eiInfo.set("useCertNo",useCertNo);
		eiInfo.set("useArea",useCertNo);
		eiInfo.set("registerCode",registerCode);
		eiInfo.set("registerDate",registerDate);
		eiInfo.set("outFactoryDate",outFactoryDate);
		eiInfo.set("fixedTime",fixedTime);
		eiInfo.set("useTime",useTime);
		eiInfo.set("nonuseDate",nonuseDate);
		eiInfo.set("managerDeptId",managerDeptId);
		eiInfo.set("managerDeptName",managerDeptName);
		eiInfo.set("managerManId",managerManId);
		eiInfo.set("managerManName",managerManName);
		eiInfo.set("chargeUserId",chargeUserId);
		eiInfo.set("chargeUserName",chargeUserName);
		eiInfo.set("useFor",useFor);
		eiInfo.set("useDeaprtId",useDeaprtId);
		eiInfo.set("useDeaprtName",useDeaprtName);
		eiInfo.set("relatedDevice",relatedDevice);
		eiInfo.set("needScan",needScan);
		eiInfo.set("manufacturerName",manufacturerName);
		eiInfo.set("manufacturerCertno",manufacturerCertno);
		eiInfo.set("fixedUnit",fixedUnit);
		eiInfo.set("fixedCertno",fixedCertno);
		eiInfo.set("maintUnit",maintUnit);
		eiInfo.set("maintCertno",maintCertno);
		eiInfo.set("checkUnit",checkUnit);
		eiInfo.set("checkCertno",checkCertno);
		eiInfo.set("thisCheckDate",thisCheckDate);
		eiInfo.set("thisFinishDate",thisFinishDate);
		eiInfo.set("nextCheckDate",nextCheckDate);
		eiInfo.set("annualinspcycle",annualinspcycle);
		eiInfo.set("thisExpiredDate",thisExpiredDate);
		eiInfo.set("thisChexpiredDate",thisChexpiredDate);
		eiInfo.set("nextExpiredDate",nextExpiredDate);
		eiInfo.set("regularinspcycle",regularinspcycle);
		EiCommunicator.send("DFSB0102", "update", eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil("保存成功", "success");
				window.parent.resultGrid.dataSource.page(1);
				window.parent['popDataWindow'].close();
			}
		});
	}
});

	//参数校验
	function validate(machineName,chargeUserId,managerManId,managerDeptId,useDeaprtId,fixedPlace){
		// if(isEmpty(machineTypeId)){
		// 	NotificationUtil("设备分类不能为空", "error");
		// 	return false;
		// }
		if(isEmpty(machineName)){
			NotificationUtil("设备名称不能为空", "error");
			return false;
		}
		if(isEmpty(chargeUserId)){
			NotificationUtil("负责人不能为空", "error");
			return false;
		}
		if(isEmpty(managerManId)){
			NotificationUtil("管理员不能为空", "error");
			return false;
		}
		if(isEmpty(managerDeptId)){
			NotificationUtil("管理科室不能为空", "error");
			return false;
		}
		if(isEmpty(useDeaprtId)){
			NotificationUtil("使用科室不能为空", "error");
			return false;
		}
		if(isEmpty(fixedPlace)){
			NotificationUtil("安装地点不能为空", "error");
			return false;
		}
		// if(isEmpty(thisCheckDate)){
		// 	NotificationUtil("本年检验日不能为空", "error");
		// 	return false;
		// }
		// if(isEmpty(thisFinishDate)){
		// 	NotificationUtil("本年完工日不能为空", "error");
		// 	return false;
		// }
		// if(isEmpty(nextCheckDate)){
		// 	NotificationUtil("下年检验日不能为空", "error");
		// 	return false;
		// }
		// if(isEmpty(thisExpiredDate)){
		// 	NotificationUtil("本次定检日期不能为空", "error");
		// 	return false;
		// }
		// if(isEmpty(thisChexpiredDate)){
		// 	NotificationUtil("本次定检完工日不能为空", "error");
		// 	return false;
		// }
		// if(isEmpty(nextExpiredDate)){
		// 	NotificationUtil("下年定检日不能为空", "error");
		// 	return false;
		// }
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

})
$(function() {
	var validator = IPLAT.Validator({id: "result"});//开启校验
	//表格按钮处理
	IPLATUI.EFGrid = {
		"resultB":{
			toolbarConfig: {
				buttons:[{
					name: "delete", text: "删除", layout: "3",
					click: function () {
						var checkRows = resultBGrid.getCheckedRows();
						if (checkRows.length < 1) {
							NotificationUtil("请选择要删除的行", "error");
							return;
						}
						var id = checkRows[0].id;
						info = new EiInfo();
						info.set("id", id);
						EiCommunicator.send("DFSB0102", "delete", info, {
							onSuccess: function (ei) {
								var status = ei.getStatus();
								if (status == -1) {
									IPLAT.alert(ei.getMsg());
								} else {
									setTimeout(function () {
										NotificationUtil(ei.getMsg(), "success");
									}, 300);
								}
								resultBGrid.dataSource.page(1);
							}
						});
					}
				}]
			},
			page: function (grid) {
				eiInfo = new EiInfo();
				var machineTypeId = IPLAT.EFInput.value($("#machineTypeId"));
				var id = IPLAT.EFInput.value($("#id"));
				eiInfo.set("machineTypeId",machineTypeId);
				eiInfo.set("id",id);
				// EiCommunicator.send("DFSB0102", "query", eiInfo, {
				// 	onSuccess : function(ei) {
				//
				// 	}
				// });
			}
		},
		"resultC":{
			pageable : false,
			exportGrid : false,
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false,
				buttons:[{
					name: "addFile",text: "上传",layout: "3",
					click: function () { fileChooseWindow.open().center() }
				},{
					name: "delFile",text: "删除",layout: "3",
					click: function () {
						var checkRows = resultCGrid.getCheckedRows();
						if (checkRows.length > 0) {
							resultCGrid.removeRows(checkRows);
						} else {
							IPLAT.NotificationUtil("请选择需要删除的项目附件信息","failure")
						}
					}
				},{
							name: "downLoadFile",text: "下载",layout: "3",
							click: function () { downLoadFile() }
						}]
			}
		},
		"resultD":{
			pageable : false,
			exportGrid : false,
			toolbarConfig:{
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false,cancel: false,save: false,'delete': false,
				buttons:[{
					name: "addFile",text: "上传",layout: "3",
					click: function () { picChooseWindow.open().center() }
				},{
					name: "delFile",text: "删除",layout: "3",
					click: function () {
						var checkRows = resultDGrid.getCheckedRows();
						if (checkRows.length > 0) {
							resultDGrid.removeRows(checkRows);
						} else {
							IPLAT.NotificationUtil("请选择需要删除的项目附件信息","failure")
						}
					}
				},{
							name: "downLoadFile",text: "下载",layout: "3",
							click: function () { downLoadFile1() }
						}]
			}
		},
	}

	//使用 grid 的 refresh 方法来解决渲染异常
	IPLATUI.EFTab = {
		"tab-tab_grid": {
			show: function (e) {
				$(e.contentElement).find("div[data-role='grid']").data("kendoGrid").refresh()
			}
		}
	}
})


//创建kendo.data.Model
function createModel(id){
	var gridRow = kendo.data.Model.define({
		id: "uploadId",
		fields: {
			"id": {type: "string"},
			"docId": {type: "string"},
			"relateId": {type: "string"},
			"filePath": {type: "string"},
			"fileName": {type: "string"},
		}
	});
	var model = new gridRow({uploadId:id});
	return model;
}

//创建kendo.data.Model
function createModel1(id){
	var gridRow = kendo.data.Model.define({
		id: "uploadId",
		fields: {
			"id": {type: "string"},
			"docId": {type: "string"},
			"relateId": {type: "string"},
			"filePath": {type: "string"},
			"fileName": {type: "string"},
		}
	});
	var model = new gridRow({uploadId:id});
	return model;
}
//文件下载
function downLoadFile(){
	var checkRows = resultCGrid.getCheckedRows();
	if (checkRows.length > 0) {
		for(var index in checkRows){
			var docId = checkRows[index].docId;
			var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
			window.location.href = href;
		}
	} else {
		IPLAT.NotificationUtil("请选择需要下载的文件")
	}
}
//文件下载
function downLoadFile1(){
	var checkRows = resultDGrid.getCheckedRows();
	if (checkRows.length > 0) {
		for(var index in checkRows){
			var docId = checkRows[index].docId;
			var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
			window.location.href = href;
		}
	} else {
		IPLAT.NotificationUtil("请选择需要下载的文件")
	}
}
//文件上传
IPLATUI.EFUpload = {
	"projectm":{
		loadComplete: function(e) {
			var uploader = e.sender.uploader;
			uploader.clearAllFiles();
		},
		validation: {
			allowedExtensions: [".jpg","jpeg",".png",".gif",".bmp",".txt",".doc","docx",".xls",".xlsx",".ppt",".pptx",".pdf",".zip","rar",".7z"]
		},
		localization: {
			invalidFileExtension: "文件格式不支持, 上传失败"
		},
		success: function(e) {
			var file = e.files[0];
			var model = createModel(1);
			// console.log(e.response)
			model["id"] = "";
			model["docId"] = e.response.docId;
			model["relateId"] = "";
			model["filePath"] = 'webapp/'+e.response.docUrl;
			model["fileName"] = file.name;
			resultCGrid.addRows(model);
			fileChooseWindow.close();
		},
	},
	"projectPic":{
		loadComplete: function(e) {
			var uploader = e.sender.uploader;
			uploader.clearAllFiles();
		},
		validation: {
			allowedExtensions: [".jpg","jpeg",".png",".gif",".bmp",".txt",".doc","docx",".xls",".xlsx",".ppt",".pptx",".pdf",".zip","rar",".7z"]
		},
		localization: {
			invalidFileExtension: "文件格式不支持, 上传失败"
		},
		success: function(e) {
			var file = e.files[0];
			var model = createModel1(2);
			// console.log(e.response)
			model["id"] = "";
			model["docId"] = e.response.docId;
			model["relateId"] = "";
			model["filePath"] = 'webapp/'+e.response.docUrl;
			model["fileName"] = file.name;
			resultDGrid.addRows(model);
			picChooseWindow.close();
		},
	}
}
