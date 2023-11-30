$(function() {
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
	};

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
				// console.log(e.response);
				// alert("111");
				model["id"] = "";
				model["relateId"] = "";
				model["docId"] = e.response.docId;
				model["filePath"] = e.response.docUrl;

				model["fileName"] = file.name;
				resultGrid.addRows(model);
				fileChooseWindow.close();
			},
		}
	};

	let validator = IPLAT.Validator({id: "inqu"});
	$("#RESAVE").click(function(){
		if (validator.validate()) {
			let eiInfo = new EiInfo();
			let taskNo = IPLAT.EFInput.value($("#taskNo"));
			let machineId= IPLAT.EFPopupInput.value($("#machineId"));
			let machineCode= IPLAT.EFPopupInput.text($("#machineId"));
			let machineName = IPLAT.EFInput.value($("#machineName"));
			let innerMachineCode = IPLAT.EFInput.value($("#innerMachineCode"));
			let checkType = IPLAT.EFSelect.value($("#checkType"));
			let statusCode = IPLAT.EFSelect.value($("#statusCode"));
			let thisCheckDate = IPLAT.EFInput.value($("#thisCheckDate"));
			let thisFinishDate = IPLAT.EFInput.value($("#thisFinishDate"));
			let nextCheckDate = IPLAT.EFInput.value($("#nextCheckDate"));

			//参数校验
			let start= new Date(thisCheckDate);
			let end= new Date(thisFinishDate);
			let next= new Date(nextCheckDate);

			if(start>end){
				NotificationUtil("本次检查时间不能大于完工日期", "error");
				return ;
			}
			if(end>next){
				NotificationUtil("本次完工时间不能大于下次检查时间", "error");
				return ;
			}

			let fileArray = resultGrid.getDataItems();
			eiInfo.set("fileList",fileArray);
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

			EiCommunicator.send("DFSB0201", "insert", eiInfo, {
				onSuccess : function(ei) {
					NotificationUtil("保存成功", "success");
					window.parent.resultGrid.dataSource.page(1);
					window.parent['popDataWindow'].close();
				}
			});
		}

	});
})



//创建kendo.data.Model
function createModel(id){
	let gridRow = kendo.data.Model.define({
		id: "uploadId",
		fields: {
			"id": {type: "string"},
			"docId": {type: "string"},
			"relateId": {type: "string"},
			"filePath": {type: "string"},
			"fileName": {type: "string"},
		}
	});
	let model = new gridRow({uploadId:id});
	return model;
}

//文件下载
function downLoadFile(){
	let checkRows = resultGrid.getCheckedRows();
	if (checkRows.length > 0) {
		for(let index in checkRows){
			let docId = checkRows[index].docId;
			let href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
			window.location.href = href;
		}
	} else {
		IPLAT.NotificationUtil("请选择需要下载的文件")
	}
}