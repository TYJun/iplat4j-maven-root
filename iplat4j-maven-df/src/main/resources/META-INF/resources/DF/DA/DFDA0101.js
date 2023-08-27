$(function() {

	/**
	 * Tree 中数据回填触发事件
	 * e.tree Tree 对象
	 * e.node 待回填的树结点数据对象（勾选回填时是集合）
	 */
	IPLATUI.EFTreeInput = {
		"machineTypeId":{
			backFill: function (e) {
				$("#machineTypeId").val(e.node.id);
				resultGrid.dataSource.page(1);
			},
		}
	}
	
	IPLATUI.EFPopupInput = {
		"fixedPlace":{
			backFill: function (e) {
				debugger
				IPLAT.EFInput.value($("#building"),e.model['building']);
				IPLAT.EFInput.value($("#floor"),e.model['floor']);
				IPLAT.EFInput.value($("#spotId"),e.model['spotId']);
				IPLAT.EFInput.value($("#spotCode"),e.model['spotNum']);
				IPLAT.EFInput.value($("#spotName"),e.model['spotName']);
			},
		},
		//根据管理员选中科室
	   "managerManId":{
		  backFill: function (e) {
			  IPLAT.EFPopupInput.value($("#reqDeptName"),e.model.deptNum);
			  IPLAT.EFPopupInput.text($("#reqDeptName"),e.model.deptName);
		  },
	   }
	}

	IPLATUI.EFGrid = {
		//表格渲染按钮
		"resultC":{
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
							IPLAT.NotificationUtil("请选择需要删除的文件信息","failure")
						}
					}
				}]
			}
		},
	}
	
	//文件下载
	function downLoadFile(){
		var checkRows = resultCGrid.getCheckedRows();
		if (checkRows.length > 0) {
			for(var index in checkRows){
				var docId = checkRows[index].fileId;
	            var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
	            window.location.href = href;
			}
		} else {
			IPLAT.NotificationUtil("请选择需要下载的文件")
		}
	}
	
	
	//文件上传
	var fileList = [];
	var s=false;
	var files = ["jpg","jpeg","png","gif","bmp","txt","doc","docx",
		"xls","xlsx","ppt","pptx","pdf","zip","rar","7z","sql"];
	IPLATUI.EFUpload = {
		"dfdaFiles":{
			upload: function(e) {
			var file = e.files[0];
			if(file.size>1048576){
				NotificationUtil("文件不能大于1G,请重选", "error");
				s=true;
			}
			var fileName=file.name.split('.')[1];
			if(files.indexOf(fileName)==-1){
				NotificationUtil("文件格式(后缀名)不符合,请重选", "error");
				s=true;
			}
			},
			showFileList:false,
			loadComplete: function(e) {
				var uploader = e.sender.uploader;
				uploader.clearAllFiles();
			},
//			validation: {
//				allowedExtensions: [".jpg","jpeg",".png",".gif",".bmp",".txt",".doc","docx",
//					".xls",".xlsx",".ppt",".pptx",".pdf",".zip","rar",".7z",".sql"],
//				maxFileSize: 1048576
//			},
//			localization: {
//				invalidFileExtension: "文件格式不支持, 上传失败"
//			},
			success: function(e) {
				debugger;
				if(s){
					s=false;
					return;
				};
				var date = new Date();
				var fileList = [];
				var file = e.files[0];
				var docId=e.response.docId;
				var fileNum=date .getFullYear()+"-"+(date .getMonth()+1)+"-"+date .getDate()+" "
				+date .getHours()+":"+date .getMinutes()+":"+date .getSeconds();
				if(e.operation == 'upload') {
					fileList.push({"fileName":file.name,"fileSize":file.size,"fileId":docId,"fileNum":fileNum});
				} else if (e.operation == 'remove') {
					 for (var i = 0; i < fileList.length; i++) {
			            if (fileList[i].fileId== file.docId) {
			            	fileList.splice(i, 1);
			            }
			        }
				}
				var grid = $("#ef_grid_resultC").data("kendoGrid");
				if(fileList!=null){
					//grid.removeRows(grid.getDataItems());
					for(var i = 0; i < fileList.length; i++){
						var model = createModel(i);
						var par = fileList[i];
						model["fileId"] = par.fileId;
						model["fileName"] = par.fileName;
						model["fileSize"] = par.fileSize;
						model["fileDesc"] = "";
						model["fileNum"] = fileNum;
						grid.addRows(model);
					}
				}
				fileChooseWindow.close();
			},
		}
	}
	
	//创建kendo.data.Model
	function createModel(id){
		var gridRow = kendo.data.Model.define({
		    id: "uploadId", 
		    fields: {
		        "fileId": {type: "string"},
		        "fileName": {type: "string"},
		        "fileSize": {type: "string"},
		        "fileDesc": {type: "string"},
		        "fileNum": {type: "string"}
		    }
		});
		var model = new gridRow({uploadId:id});
		return model;
	}

	/**
	 * 设备保存提交
	 */
	$("#RESAVE").click(function(){
		//判断
		var validator = IPLAT.Validator({
			id: "dfda"
		});
		if (validator.validate()) {
			var eiInfo = new EiInfo();
			//建档信息
			var machineName = IPLAT.EFInput.value($("#machineName"));
			var machineTypeId = IPLAT.EFInput.value($("#machineTypeId"));
			var manufacturerName = IPLAT.EFInput.value($("#manufacturerName"));
			var fixedPlace = IPLAT.EFInput.value($("#fixedPlace"));
			var building = IPLAT.EFInput.value($("#building"));
			var floor = IPLAT.EFInput.value($("#floor"));
			var spotId = IPLAT.EFInput.value($("#spotId"));
			var spotCode = IPLAT.EFInput.value($("#spotCode"));
			var spotName = IPLAT.EFInput.value($("#spotName"));
			var supplierName = IPLAT.EFInput.value($("#supplierName"));
			var supplierId = IPLAT.EFInput.value($("#supplierId"));
			var maintUnit = IPLAT.EFInput.value($("#maintUnit"));
			var managerDepartId = IPLAT.EFInput.value($("#reqDeptName"));
			var useDeaprtId = IPLAT.EFInput.value($("#useDeaprtId"));
			var fixedTime = IPLAT.EFInput.value($("#fixedTime"));
			var useTime = IPLAT.EFInput.value($("#useTime"));
			var managerManId = IPLAT.EFInput.value($("#managerManId"));
			var needScan = IPLAT.EFSelect.value($("#needScan"));
			var status = IPLAT.EFSelect.text($("#status"));
			var managerMan = IPLAT.EFPopupInput.text($("#managerManId"));
			var memo = IPLAT.EFInput.value($("#memo"));
			//var lastMaintainTime = IPLAT.EFInput.value($("#lastMaintainTime"));
			// var matNum = IPLAT.EFInput.value($("#matNum"));
			// var matName = IPLAT.EFInput.value($("#matName"));
			var machineFolderId = IPLAT.EFInput.value($("#machineFolderId"));
			var assetPrice = IPLAT.EFInput.value($("#assetPrice"));
			var managerDepartName = IPLAT.EFPopupInput.text($("#reqDeptName"));
			var useDeaprtName = IPLAT.EFPopupInput.text($("#useDeaprtId"));
		
            //入库信息
			var machineCode = IPLAT.EFInput.value($("#machineCode"));
			var models = IPLAT.EFInput.value($("#models"));
			var energyForm = IPLAT.EFInput.value($("#energyForm"));
			var makerBrand = IPLAT.EFInput.value($("#makerBrand"));
			var outFactoryNo = IPLAT.EFInput.value($("#outFactoryNo"));
			var factoryTel = IPLAT.EFInput.value($("#factoryTel"));
			var buyMode = IPLAT.EFInput.value($("#buyMode"));
			var buyTime = IPLAT.EFInput.value($("#buyTime"));
			var useFor = IPLAT.EFInput.value($("#useFor"));
			var useLimit = IPLAT.EFInput.value($("#useLimit"));
			var maintainRound = IPLAT.EFSelect.text($("#maintainRound"));
			var warrantyDate = IPLAT.EFInput.value($("#warrantyDate"));
			var inStorageTime = IPLAT.EFInput.value($("#inStorageTime"));
			//出库信息
			var goodsCode = IPLAT.EFInput.value($("#goodsCode"));
			var goodsName = IPLAT.EFInput.value($("#goodsName"));
			var goodsNo = IPLAT.EFInput.value($("#goodsNo"));
			var assetBelongs = IPLAT.EFInput.value($("#assetBelongs"));
			var outStorageTime = IPLAT.EFInput.value($("#outStorageTime"));
			var resultCList = resultCGrid.getDataItems();
			//建档信息
			eiInfo.set("machineName",machineName);
			eiInfo.set("machineTypeId",machineTypeId);
			eiInfo.set("manufacturerName",manufacturerName);
			eiInfo.set("fixedPlace",fixedPlace);
			eiInfo.set("building",building);
			eiInfo.set("floor",floor);
			eiInfo.set("spotId",spotId);
			eiInfo.set("spotCode",spotCode);
			eiInfo.set("spotName",spotName);
			eiInfo.set("supplierName",supplierName);
			eiInfo.set("supplierId",supplierId);
			eiInfo.set("maintUnit",maintUnit);
			eiInfo.set("managerDepartName",managerDepartName);
			eiInfo.set("useDeaprtName",useDeaprtName)
			eiInfo.set("fixedTime",fixedTime);
			eiInfo.set("useTime",useTime);
			eiInfo.set("needScan",needScan);
			eiInfo.set("managerManId",managerManId);
			eiInfo.set("status",status);
			eiInfo.set("managerMan",managerMan);
			eiInfo.set("managerDepartId",managerDepartId);
			eiInfo.set("useDeaprtId",useDeaprtId);
			eiInfo.set("machineFolderId",machineFolderId);
			eiInfo.set("memo",memo);
			//入库信息
			eiInfo.set("machineCode",machineCode);
			eiInfo.set("models",models);
			eiInfo.set("energyForm",energyForm);
			eiInfo.set("makerBrand",makerBrand);
			eiInfo.set("outFactoryNo",outFactoryNo);
			eiInfo.set("buyMode",buyMode);
			eiInfo.set("buyTime",buyTime);
			eiInfo.set("assetPrice",assetPrice);
			eiInfo.set("useFor",useFor);
			eiInfo.set("useLimit",useLimit);
			eiInfo.set("warrantyDate",warrantyDate);
			//eiInfo.set("lastMaintainTime",lastMaintainTime);
			eiInfo.set("maintainRound",maintainRound);
			eiInfo.set("factoryTel",factoryTel);
			eiInfo.set("inStorageTime",inStorageTime);
			//eiInfo.set("matNum",matNum);
			//eiInfo.set("matName",matName);
			//出库信息
			eiInfo.set("goodsCode",goodsCode);
			eiInfo.set("goodsName",goodsName);
			eiInfo.set("goodsNo",goodsNo);
			eiInfo.set("assetBelongs",assetBelongs);
			eiInfo.set("outStorageTime",outStorageTime);
			//文件上传
			eiInfo.set("resultC",resultCList);
			eiInfo.set();
			EiCommunicator.send("DFDA0101", "insert", eiInfo,{
				onSuccess : function(ei) {
					if(ei.getStatus() == -1){
						NotificationUtil(ei.getMsg(), "error");
					} else {
						NotificationUtil(ei.getMsg(), "success");
						window.parent.resultGrid.dataSource.page(1);
						window.parent['popDataWindow'].close();
					}
				},
				onFail : function (ei) {
					NotificationUtil(ei.getMsg(), "error");
				}
			});
		}
		
	});

});