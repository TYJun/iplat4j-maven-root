
$(function() {
	var validator = IPLAT.Validator({id: "result"});//开启校验
	//表格按钮处理
	IPLATUI.EFGrid = {
		"result": {
			pageable : false,
			exportGrid : false,
			toolbarConfig: {
				hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
				add: false, cancel: false, save: false, 'delete': false,
				buttons: [{
					name: "addFile", text: "上传", layout: "3",
					click: function () {
						fileChooseWindow.open().center()
					}
				}, {
					name: "delFile", text: "删除", layout: "3",
					click: function () {
						var checkRows = resultGrid.getCheckedRows();
						if (checkRows.length > 0) {
							resultGrid.removeRows(checkRows);
						} else {
							IPLAT.NotificationUtil("请选择需要删除的项目附件信息", "failure")
						}
					}
				}, {
					name: "downLoadFile", text: "下载", layout: "3",
					click: function () {
						downLoadFile()
					}
				}]
			}
		},
	}
})

//文件上传
IPLATUI.EFUpload = {
	"projectFile":{
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
			resultGrid.addRows(model);
			fileChooseWindow.close();
		},
	}
}

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
//文件下载
function downLoadFile(){
	var checkRows = resultGrid.getCheckedRows();
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

$(function() {
	//回显赋值
	IPLAT.EFPopupInput.text($("#machineId"),__ei.machineCode);
	
	
	$("#RESAVE").click(function(){
		//判断
		console.log(233);
		 validator = IPLAT.Validator({
			id: "inqu1"
		});
		if (validator.validate()) {
			 eiInfo = new EiInfo();
			 id = IPLAT.EFInput.value($("#id"));
			 taskNo = IPLAT.EFInput.value($("#taskNo"));
			 machineId= IPLAT.EFPopupInput.value($("#machineId"));
			 machineCode= IPLAT.EFPopupInput.text($("#machineId"));
			 machineName = IPLAT.EFInput.value($("#machineName"));
			 innerMachineCode = IPLAT.EFInput.value($("#innerMachineCode"));
			 checkType = IPLAT.EFSelect.value($("#checkType"));
			 statusCode = IPLAT.EFSelect.value($("#statusCode"));
			 thisCheckDate = IPLAT.EFInput.value($("#thisCheckDate"));
			 thisFinishDate = IPLAT.EFInput.value($("#thisFinishDate"));
			 nextCheckDate = IPLAT.EFInput.value($("#nextCheckDate"));
			 
			 //参数校验
			 var start= new Date(thisCheckDate); 
			 var end= new Date(thisFinishDate);
			 var next= new Date(nextCheckDate);
			
					if(start>end){
						NotificationUtil("本次检查时间不能大于完工日期", "error");
						return ;
					} 
					if(end>next){
						NotificationUtil("本次完工时间不能大于下次检查时间", "error");
						return ;
					}
			var fileArray = resultGrid.getDataItems();
			eiInfo.set("fileList",fileArray);
			 eiInfo.set("id",id);
			 eiInfo.set("taskNo",taskNo);
			 eiInfo.set("machineId",machineId);
			 eiInfo.set("machineCode",machineCode);
			 eiInfo.set("machineName",machineName);
			 eiInfo.set("innerMachineCode",innerMachineCode);
			 eiInfo.set("statusCode",statusCode);
			 eiInfo.set("checkType",checkType);
			 eiInfo.set("thisCheckDate",thisCheckDate);
			 eiInfo.set("thisFinishDate",thisFinishDate);
			 eiInfo.set("nextCheckDate",nextCheckDate);
			 
			 EiCommunicator.send("DFSB0202", "update", eiInfo, {
				onSuccess : function(ei) {
		 			NotificationUtil(ei.getMsg(), "success");
		 			window.parent.resultGrid.dataSource.page(1);
		 			window.parent['popDataWindow'].close();
			 		}	
			 });
		}

	});
});