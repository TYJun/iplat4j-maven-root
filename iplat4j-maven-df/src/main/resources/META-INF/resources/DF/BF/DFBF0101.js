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
			eiInfo.setByNode("dfda");
			
			// var machineName = IPLAT.EFInput.value($("#machineName"));
			// var fixedPlace = IPLAT.EFInput.value($("#fixedPlace"));
			// var scrapDate = IPLAT.EFInput.value($("#scrapDate"));
			// var scrapWay = IPLAT.EFSelect.text($("#scrapWay"));
			// var scrapReason = IPLAT.EFInput.value($("#scrapReason"));
			// eiInfo.set("machineName",machineName);
			// eiInfo.set("fixedPlace",fixedPlace);
			// eiInfo.set("scrapDate",scrapDate);
			// eiInfo.set("scrapWay",scrapWay);
			// eiInfo.set("scrapReason",scrapReason);
			// eiInfo.set();
			EiCommunicator.send("DFBF0101", "insert", eiInfo,{
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