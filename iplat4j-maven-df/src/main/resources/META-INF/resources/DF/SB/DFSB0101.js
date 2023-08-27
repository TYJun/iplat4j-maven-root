


$(function() {

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
				resultGrid.dataSource.page(1);
			},
		}
	}

	IPLATUI.EFPopupInput = {
		"fixedId":{
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
		}
		/*//根据管理员选中科室
		"managerManId":{
			backFill: function (e) {
				debugger
				IPLAT.EFPopupInput.value($("#reqDeptName"),e.model.deptNum);
				IPLAT.EFPopupInput.text($("#reqDeptName"),e.model.deptName);
			},
		}*/
	}

	//文件上传
	IPLATUI.EFUpload = {
		"projects":{
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
				model["id"] = "";
				model["docId"] = e.response.docId;
				model["relateId"] = "";
				model["filePath"] = e.response.docUrl;
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
				var model = createModelPic(2);
				model["id"] = "";
				model["docId"] = e.response.docId;
				model["relateId"] = "";
				model["filePath"] = e.response.docUrl;
				model["fileName"] = file.name;
				resultDGrid.addRows(model);
				picChooseWindow.close();
			},
		}
	}

	$(function() {
		var validator = IPLAT.Validator({id: "result"});//开启校验
		//表格按钮处理
		IPLATUI.EFGrid = {
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
					},/*{
							name: "downLoadFile",text: "下载",layout: "3",
							click: function () { downLoadFile() }
						}*/]
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
					},/*{
							name: "downLoadFile",text: "下载",layout: "3",
							click: function () { downLoadFile() }
						}*/]
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
			id: "uploadFile",
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
	function createModelPic(id){
		var gridRow = kendo.data.Model.define({
			id: "uploadPic",
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

	$("#RESAVE").click(function(){
		//判断
		console.log(233);
		 validator = IPLAT.Validator({
			id: "dfsb"
		});
		if (validator.validate()) {
			//debugger
			console.log(2334);
			eiInfo = new EiInfo();
			//获取tab数据
			var fileArray = resultCGrid.getDataItems();
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

		var machineCode = IPLAT.EFInput.value($("#machineCode"));
		var machineName = IPLAT.EFInput.value($("#machineName"));
		var models = IPLAT.EFInput.value($("#models"));
		var machineTypeId = IPLAT.EFInput.value($("#machineTypeId"));
		var statusCode = IPLAT.EFSelect.value($("#statusCode"));
//		var buyTime = IPLAT.EFInput.value($("#buyTime"));
		var fixedId = IPLAT.EFPopupInput.value($("#fixedId"));
		var fixedPlace = IPLAT.EFPopupInput.text($("#fixedId"));				
		var innerMachineCode = IPLAT.EFInput.value($("#innerMachineCode"));
		var documentNo = IPLAT.EFInput.value($("#documentNo"));
		var workMedia = IPLAT.EFInput.value($("#workMedia"));			
		var useCertNo = IPLAT.EFInput.value($("#useCertNo"));
		var useArea = IPLAT.EFInput.value($("#useArea"));
		var registerCode = IPLAT.EFInput.value($("#registerCode"));
		var registerDate = IPLAT.EFInput.value($("#registerDate"));
		var outFactoryDate = IPLAT.EFInput.value($("#outFactoryDate"));
		var fixedTime = IPLAT.EFInput.value($("#fixedTime"));
		var useTime = IPLAT.EFInput.value($("#useTime"));
		var nonuseDate = IPLAT.EFInput.value($("#nonuseDate"));
		var managerDeptId = IPLAT.EFPopupInput.value($("#managerDeptId"));
		var managerDeptName = IPLAT.EFPopupInput.text($("#managerDeptId"));
		var managerManId = IPLAT.EFInput.value($("#managerManId"));
		var managerManName = IPLAT.EFPopupInput.text($("#managerManId"));
		var chargeUserId = IPLAT.EFInput.value($("#chargeUserId"));
		var chargeUserName = IPLAT.EFPopupInput.text($("#chargeUserId"));
		var useFor = IPLAT.EFInput.value($("#useFor"));
		var useDeaprtId = IPLAT.EFPopupInput.value($("#useDeaprtId"));
		var useDeaprtName = IPLAT.EFPopupInput.text($("#useDeaprtId"));
		var relatedDevice = IPLAT.EFInput.value($("#relatedDevice"));
		var needScan = IPLAT.EFSelect.value($("#needScan"));
		var manufacturerName = IPLAT.EFInput.value($("#manufacturerName"));
		var manufacturerCertno = IPLAT.EFInput.value($("#manufacturerCertno"));
		var fixedUnit = IPLAT.EFInput.value($("#fixedUnit"));
		var fixedCertno = IPLAT.EFInput.value($("#fixedCertno"));
		var maintUnit = IPLAT.EFInput.value($("#maintUnit"));
		var maintCertno = IPLAT.EFInput.value($("#maintCertno"));
		var checkUnit = IPLAT.EFInput.value($("#checkUnit"));
		var checkCertno = IPLAT.EFInput.value($("#checkCertno"));
		var thisCheckDate = IPLAT.EFInput.value($("#thisCheckDate"));
		var thisFinishDate  = IPLAT.EFInput.value($("#thisFinishDate "));
		var nextCheckDate  = IPLAT.EFInput.value($("#nextCheckDate"));
		var annualinspcycle = IPLAT.EFInput.value($("#annualinspcycle"));
		var thisExpiredDate = IPLAT.EFInput.value($("#thisExpiredDate"));
		var thisChexpiredDate = IPLAT.EFInput.value($("#thisChexpiredDate"));
		var nextExpiredDate = IPLAT.EFInput.value($("#nextExpiredDate"));
		var regularinspcycle = IPLAT.EFInput.value($("#regularinspcycle"));

			//参数校验
	   if(!validate(machineTypeId,machineName,chargeUserId,managerManId,managerDeptId,useDeaprtId,fixedPlace,thisCheckDate
   			,thisFinishDate,nextCheckDate,thisExpiredDate,thisChexpiredDate,nextExpiredDate)){
   			 return;
   		}
			 //参数校验时间
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
			eiInfo.set("fileList",fileArray);
			eiInfo.set("deviceList",deviceList);
			eiInfo.set("picList",picList);
			eiInfo.set("spotCode",spotCode);
			eiInfo.set("building",building);
			eiInfo.set("floor",floor);
			eiInfo.set("machineCode",machineCode);
			eiInfo.set("machineName",machineName);
			eiInfo.set("models",models);
			eiInfo.set("machineTypeId",machineTypeId);
			eiInfo.set("statusCode",statusCode);
//			eiInfo.set("fixedId",fixedId);
//			eiInfo.set("buyTime",buyTime);
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
			EiCommunicator.send("DFSB0101", "insert", eiInfo, {
				onSuccess : function(ei) {
					if(ei.getStatus()==-1){
						NotificationUtil(ei.getMsg(),"error");
						return;
					}{NotificationUtil("保存成功", "success");}
					window.parent.resultGrid.dataSource.page(1);
					window.parent['popDataWindow'].close();
				}
			});
		}
		
	});

		//参数校验
		function validate(machineTypeId,machineName,chargeUserId,managerManId,managerDeptId,useDeaprtId,fixedPlace,thisCheckDate
		 ,thisFinishDate,nextCheckDate,thisExpiredDate,thisChexpiredDate,nextExpiredDate){
		 if(isEmpty(machineTypeId)){
				 NotificationUtil("设备分类不能为空", "error");
		  return false;
		 }
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
		 if(isEmpty(thisCheckDate)){
				 NotificationUtil("本年检验日不能为空", "error");
		  return false;
		 }
		 if(isEmpty(thisFinishDate)){
				 NotificationUtil("本年完工日不能为空", "error");
		  return false;
		 }
		 if(isEmpty(nextCheckDate)){
		  NotificationUtil("下年检验日不能为空", "error");
		  return false;
		 }
		 if(isEmpty(thisExpiredDate)){
		  NotificationUtil("本次定检日期不能为空", "error");
		  return false;
		 }
		 if(isEmpty(thisChexpiredDate)){
		  NotificationUtil("本次定检完工日不能为空", "error");
		  return false;
		 }
		 if(isEmpty(nextExpiredDate)){

		  NotificationUtil("下年定检日不能为空", "error");
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
});