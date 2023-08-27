$(function() {

	/**
	 * 数据回显
	 */
	if(__ei.type == "edit"){
		IPLAT.EFTreeInput.setAllFields( $("#classifyId") , __ei.classifyId, __ei.classifyName);
		$("#classifyId_textField").val(__ei.classifyName);
	}


	/**
	 * 数据回显
	 */

	if(__ei.type == "see"){
		IPLAT.EFTreeInput.setAllFields( $("#classifyId") , __ei.classifyId, __ei.classifyName);
		$("#classifyId_textField").val(__ei.classifyName);
	}

	/**
	 * 医院标识分类树控件
	 * @type {{parentId: {ROOT: {label: string, text: string, leaf: boolean}, backFill: IPLATUI.EFTreeInput.parentId.backFill}}}
	 */
	IPLATUI.EFTreeInput = {
		"classifyId": {
			ROOT: {label: "root", text: "医院标识分类", leaf: true},
			backFill: function (e) {
				let _data = e.node;
				$("#classifyId").val(_data.code);
				$("#classifyName").val(_data.text);
			},
		}
	};

	/**
	 * 科室下拉控件
	 * @type {{wgroupNum: {select: IPLATUI.EFSelect.wgroupNum.select}}}
	 */
	IPLATUI.EFSelect = {
		"wgroupNum": {
			// 点击下拉选项时触发
			select:function(e) { //获取勾选值
				let dataItem = e.dataItem;
				$("#wgroupName").val(dataItem['textField']);
			}
		}
	};



	/* 附件上传 */
	IPLATUI.EFUpload = {
		"contentFile": {
			loadComplete: function (e) {
				var uploader = e.sender.uploader;
				uploader.clearAllFiles();
			},
			validation: {
				allowedExtensions: [".jpg", "jpeg", ".png", ".gif", ".bmp",
					".txt", ".doc", "docx", ".xls", ".xlsx", ".ppt",
					".pptx", ".pdf", ".zip", "rar", ".7z"]
			},
			localization: {
				invalidFileExtension: "文件格式不支持, 上传失败"
			},
			success: function (e) {
				var file = e.files[0];
				var model = createModel(1);
				model["id"] = "";
				model["relationId"] = "";
				model["docId"] = e.response.docId;
				model["filePath"] = e.response.docUrl;
				model["fileName"] = file.name;
				model["fileSize"] = file.size;
				var uploadTime = e.response.uploadTime;
				var uploadTimeNice = timeSet(uploadTime);
				model["recCreateTime"] = uploadTimeNice;
				model["remark"] = "";
				resultBGrid.addRows(model);
				fileChooseWindow.close();
			},
		}
	}

	/* 分页处理 */
	$(function () {
		var buttonList = [];
		if ($("#type").val() == "add") {
			var addFile = {
				name: "addFile",
				text: "上传",
				layout: "3",
				click: function () {
					fileChooseWindow.open().center()
				}
			}
			buttonList.push(addFile);
			var deleteFile = {
				name: "delFile",
				text: "删除",
				layout: "3",
				click: function () {
					var checkRows = resultBGrid.getCheckedRows();
					if (checkRows.length > 0) {
						resultBGrid.removeRows(checkRows);
						fileList.push(checkRows[0]);
						console.log(fileList);
					} else {
						IPLAT.NotificationUtil("请选择需要删除的附件信息")
					}
				}
			}
			buttonList.push(deleteFile);
			var loadFile = {
				name: "downLoadFile",
				text: "下载",
				layout: "3",
				click: function () {
					downLoadFile()
				}
			}
			buttonList.push(loadFile);
		} else if ($("#type").val() == "edit") {
			var addFile = {
				name: "addFile",
				text: "上传",
				layout: "3",
				click: function () {
					fileChooseWindow.open().center()
				}
			}
			buttonList.push(addFile);
			var deleteFile = {
				name: "delFile",
				text: "删除",
				layout: "3",
				click: function () {
					var checkRows = resultBGrid.getCheckedRows();
					if (checkRows.length > 0) {
						resultBGrid.removeRows(checkRows);
						fileList.push(checkRows[0]);
						console.log(fileList);
					} else {
						IPLAT.NotificationUtil("请选择需要删除的附件信息")
					}
				}
			}
			buttonList.push(deleteFile);
			var loadFile = {
				name: "downLoadFile",
				text: "下载",
				layout: "3",
				click: function () {
					downLoadFile()
				}
			}
			buttonList.push(loadFile);
			var lookFile = {
				name: "lookFile",
				text: "预览",
				layout: "3",
				click: function () {
					var checkRows = resultBGrid.getCheckedRows();
					if (checkRows.length > 0) {
						var docPath = checkRows[0].attachPath;
						var last = docPath.lastIndexOf("/");
						var first = docPath.indexOf("/");
						var fileName = docPath.substring(last + 1, docPath.length);
						var filePath = docPath.substring(0, first);
						if (docPath.indexOf(".pdf") >= 0) {
							window.open(IPLATUI.CONTEXT_PATH + "/pdf?fileName=" + fileName + "&filePath=" + filePath);
						} else {
							IPLAT.NotificationUtil("只能查看pdf格式的文档", "error")
							return;
						}
					} else {
						IPLAT.NotificationUtil("请选择需要预览的附件信息")
					}
				}
			}
			buttonList.push(lookFile);
		} else if ($("#type").val() == "see") {
			var loadFile = {
				name: "downLoadFile",
				text: "下载",
				layout: "3",
				click: function () {
					downLoadFile()
				}
			}
			buttonList.push(loadFile);
			var lookFile = {
				name: "lookFile",
				text: "预览",
				layout: "3",
				click: function () {
					var checkRows = resultBGrid.getCheckedRows();
					if (checkRows.length > 0) {
						var docPath = checkRows[0].attachPath;
						var last = docPath.lastIndexOf("/");
						var first = docPath.indexOf("/");
						var fileName = docPath.substring(last + 1, docPath.length);
						var filePath = docPath.substring(0, first);
						if (docPath.indexOf(".pdf") >= 0) {
							window.open(IPLATUI.CONTEXT_PATH + "/pdf?fileName=" + fileName + "&filePath=" + filePath);
						} else {
							IPLAT.NotificationUtil("只能查看pdf格式的文档", "error")
							return;
						}
					} else {
						IPLAT.NotificationUtil("请选择需要预览的附件信息")
					}
				}
			}
			buttonList.push(lookFile);
		}

		var fileList = [];
		//开启校验
		IPLATUI.EFGrid = {
			"resultB": {
				//pageable : false,
				toolbarConfig: {
					buttons: buttonList,
					hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
					add: false,
					cancel: false,
					save: false,
					'delete': false

				}
			}
		}





		/**
		 * 维修事项分类保存提交
		 */
		$("#SUBMIT").unbind('click').on('click', function(event){
			// 防止连续提交
			$("#SUBMIT").attr("disabled",true);
			setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);
			//参数处理
			let node = $('#HIBZ0101');
			let standardName = $("#standardName").val();
			let classifyId = $("#classifyId").val();
			var eiInfo = new EiInfo();
			var file = resultBGrid.getDataItems();
			var deleteFile = fileList
			eiInfo.setByNode("result");
			eiInfo.set("standardName",standardName)
			eiInfo.set("classifyId",classifyId)
			eiInfo.set("file", file)
			eiInfo.set("deleteFile",deleteFile)
			eiInfo.set("node",node)
			if(isEmpty(standardName)){
				NotificationUtil("标准名称不能为空", "error");
				return;
			}
			if(isEmpty(classifyId)){
				NotificationUtil("标识分类不能为空", "error");
				return;
			}
			EiCommunicator.send("HIBZ0101", "save", eiInfo,{
				onSuccess : function(eiInfo) {
					NotificationUtil("保存成功", "");
					window.parent['popDataWindow'].close();
					//从新加载父页面数据
					setTimeout(function() { window.parent.location.reload() }, 600);
				},
				onFail : function(errorMsg, status, e) {
					NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
				}
			});
		});


		/* 渲染分页 */
		IPLATUI.EFTab = {
			"tab-tab_grid": {
				select: function (e) {
					var grid = $(e.contentElement).find("div[data-role='grid']").data("kendoGrid")
					if (grid.getDataItems().length === 0) {
						grid.dataSource.page(1);
					}
				}
			}
		}

		$("#scheduleQuery").on("click", function () {
			resultGrid.dataSource.page(1);
		})

		$("#scheduleReset").on("click", function () {
			$("#inqu_status-0-scheduleAutoNo").val("");
			$("#inqu_status-0-scheduleName").val("");
			resultGrid.dataSource.page(1);
		})

		var s = [];
		var l;
		$("#scheduleSave").on("click", function () {
			s = [];
			var info = new EiInfo();
			var gridResultH = $("#ef_grid_resultH").data("kendoGrid");
			var dataItems = gridResultH.getDataItems();
			gridResultH.removeRows(dataItems);
			var row = resultGrid.getCheckedRows();
			var model = row[0];
			var scheduleName = model.scheduleName;
			var scheduleAutoNo = model.scheduleAutoNo;
			var nodeAutoNo = model.nodeAutoNo;
			const nodeAutoNoStr = nodeAutoNo.split(";");
			var nodeName = model.nodeName;
			const nodeNameStr = nodeName.split(";");
			var nodeRemark = model.nodeRemark;
			const nodeRemarkStr = nodeRemark.split(";");
			for (var i = nodeAutoNoStr.length; i > 0; i--) {
				var model = createNodeModel(i);
				var nodeAutoNo = nodeAutoNoStr[i - 1];
				var nodeName = nodeNameStr[i - 1];
				var nodeRemark = nodeRemarkStr[i - 1];
				model.nodeAutoNo = nodeAutoNo;
				model.nodeName = nodeName;
				model.nodeRemark = nodeRemark;
				l = {
					textField: nodeNameStr[nodeAutoNoStr.length - i],
					valueField: nodeAutoNoStr[nodeAutoNoStr.length - i]
				}
				s.push(l);
				gridResultH.addRows(model);
			}
			$(".k-window").hide();
			IPLAT.EFPopupInput.text($("#inqu_status-0-schedule"), scheduleName);
			IPLAT.EFPopupInput.value($("#inqu_status-0-schedule"), scheduleAutoNo);
			var dataSource = new kendo.data.DataSource({
				data: s
			});
			//IPLAT.EFSelect.setDataSource($("#inqu_status-0-projectProgress"), dataSource);
		})



	})

//关闭窗口
	function closeCurrentWindow() {
		window.parent['popDataWindow'].close();
	}


//文件下载
	function downLoadFile() {
		var checkRows = resultBGrid.getCheckedRows();
		if (checkRows.length > 0) {
			for (var index in checkRows) {
				var docId = checkRows[index].docId;
				var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
				window.location.href = href;
			}
		} else {
			IPLAT.NotificationUtil("请选择需要下载的文件")
		}
	}

//子参数
	function addRows(checkRows, type) {
		for (var index in checkRows) {
			var model = checkRows[index];
			if (type == "cont") {
				model["contTermName"] = model.contTermName;
				model["cotent"] = model.contTermContent;
				model["remark"] = model.remark;
				//resultAGrid.addRows(model)
				clearRepeat(model, resultAGrid, "contTermName");
			} else {
				model["workNo"] = model.workNo;
				model["name"] = model.name;
				model["phone"] = model.number;
				//resultEGrid.addRows(model)
				clearRepeat(model, resultEGrid, "workNo");
			}
		}
	}

	function clearRepeat(model, grid, compareField) {
		var list = grid.getDataItems();
		var isExist = false;
		for (var i in list) {
			var row = list[i];
			if (row[compareField] == model[compareField]) {
				isExist = true;
			}
		}
		if (!isExist) {
			grid.addRows(model)
		}
	}

//创建kendo.data.Model
	function createModel(id) {
		var gridRow = kendo.data.Model.define({
			id: "uploadId",
			fields: {
				"id": {
					type: "string"
				},
				"relationId": {
					type: "string"
				},
				"docId": {
					type: "string"
				},
				"filePath": {
					type: "string"
				},
				"fileName": {
					type: "string"
				},
				"fileSize": {
					type: "number"
				},
				"remark": {
					type: "string"
				},
				"recCreateTime":{
					type:"string"
				}
			}
		});
		var model = new gridRow({uploadId: id});
		return model;
	}

//创建kendo.data.Model
// function createModelProjectFile(id) {
// 	var gridRow = kendo.data.Model.define({
// 		id: "uploadId",
// 		fields: {
// 			"id": {
// 				type: "string"
// 			},
// 			"projectPk": {
// 				type: "string"
// 			},
// 			"attachId": {
// 				type: "string"
// 			},
// 			"attachPath": {
// 				type: "string"
// 			},
// 			"attachName": {
// 				type: "string"
// 			},
// 			"attachSize": {
// 				type: "number"
// 			},
// 			"attachDesc": {
// 				type: "string"
// 			},
// 			"recCreator": {
// 				type: "string"
// 			},
// 			"recCreateTime": {
// 				type: "string"
// 			}
// 		}
// 	});
// 	var model = new gridRow({uploadId: id});
// 	return model;
// }




//创建kendo.data.Model
	function createNodeModel(id) {
		var gridRow = kendo.data.Model.define({
			id: "nodeId",
			fields: {
				"nodeCode": {type: "string"},
				"nodeName": {type: "string"},
				"nodeRemark": {type: "string"}
			}
		});
		var model = new gridRow({nodeId: id});
		return model;
	}




});

/**
 * 空校验函数
 * @param parameter
 * @returns {boolean}
 */
function isEmpty(parameter){
	if(parameter == undefined || parameter == null){
		return true;
	} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
		return true;
	} else {
		return false;
	}
}

/**
 * 时间格式化
 */


function timeSet(uploadTime) {
	var uploadTimew = uploadTime.replace(/(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/g, '$1-$2-$3 $4:$5:$6');
	return  uploadTimew;
}



