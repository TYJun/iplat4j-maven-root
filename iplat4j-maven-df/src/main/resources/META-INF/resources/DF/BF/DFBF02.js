$(function() {
	console.log(__ei);
	var id = __ei.id;
	var machineName = __ei.machineName;
	IPLAT.EFPopupInput.text($("#machineName"),machineName);
	// 设备分类
	// var classifyId = __ei.machineTypeId;
	// var classifyName = __ei.machineTypeName;
	// IPLAT.EFPopupInput.value($("#machineTypeId"),classifyId);
	// IPLAT.EFPopupInput.text($("#machineTypeId"),classifyName);
	// // 安装地方
	// var fixedPlaceId = __ei.spotId;
	// var fixedPlaceNum = __ei.spotCode;
	// var fixedPlaceName = __ei.fixedPlace;
	// IPLAT.EFPopupInput.value($("#fixedPlace"),fixedPlaceNum);
	// IPLAT.EFPopupInput.text($("#fixedPlace"),fixedPlaceName);
	// //IPLAT.EFInput.value($("#spotName"),fixedPlaceName);
	// // 管理员
	// var workNo = __ei.managerManId;
	// var name = __ei.managerMan;
	// IPLAT.EFPopupInput.value($("#managerManId"),workNo);
	// IPLAT.EFPopupInput.text($("#managerManId"),name);
	// // 管理科室
	//var machineCode= __ei.machineCode;

	// // 使用科室
	// var useDeptNum = __ei.useDeaprtId;
	// var useDeptName = __ei.useDeaprtName;
	// IPLAT.EFPopupInput.value($("#useDeaprtId"),useDeptNum);
	// IPLAT.EFPopupInput.text($("#useDeaprtId"),useDeptName);

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
				IPLAT.EFInput.value($("#building"),e.model['building']);
				IPLAT.EFInput.value($("#floor"),e.model['floor']);
				IPLAT.EFInput.value($("#spotId"),e.model['spotId']);
				IPLAT.EFInput.value($("#spotCode"),e.model['spotNum']);
				IPLAT.EFInput.value($("#spotName"),e.model['spotName']);
			}
		},
		//根据管理员选中科室
		"managerManId":{
			backFill: function (e) {
				debugger
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
					},{
						name: "downLoadFile",text: "下载",layout: "3",
						click: function () { downLoadFile() }
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
//					".xls",".xlsx",".ppt",".pptx",".pdf",".zip","rar",".7z",".sql"]
//			},
//			localization: {
//				invalidFileExtension: "文件格式不支持, 上传失败"
//			},
			success: function(e) {
				if(s){
					s=false;
					return;
				};
				var date = new Date();
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
					fileList=[];
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
	
	$("#RESAVE").click(function(){
		//判断
		var validator = IPLAT.Validator({
			id: "dfda"
		});
		if (validator.validate()) {
			var eiInfo = new EiInfo();
			eiInfo.setByNode("dfda");
			eiInfo.set("id",id);
			EiCommunicator.send("DFBF02", "compile", eiInfo, {
				onSuccess : function(ei) {
					NotificationUtil(ei.getMsg(), "success");
					window.parent.resultGrid.dataSource.page(1);
					window.parent['popDataWindow'].close();
				}
			});
		}
		
	});

});