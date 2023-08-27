var datagrid = null;
$(function() {
	console.log(__ei);
	console.log(__ei.role);
	if (__ei.role == 'DMSPR_XSSS' || __ei.role == 'DMZSR'){
		$("#hiddenDiv").hide();
	}
	//查询
	$("#QUERY").on("click", function(e) {
		resultGrid.dataSource.query(1);
	});

	// 重置按钮.
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
	});

	IPLATUI.EFDatePicker = {
		"currentMonth":{
			start:"year",
			depth:"year"
		}
	};

	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
			loadComplete: function (grid) {
				//费用录入按钮
				$("#ADDCHARGES").on("click", function(e) {
					console.log("本月费用录入");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						var id = checkRows[0]["drmId"];
						console.log(id);
						var url = IPLATUI.CONTEXT_PATH + "/web/DMFY0102?initLoad&id=" + id;
						var popData = $("#popDataADD");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataADDWindow.open().center();
					} else {
						NotificationUtil("请选择想要录入本月费用的人员信息", "warning")
					}
				});


				//查看个人详情按钮
				$("#SHOWCHARGES").on("click", function(e) {
					console.log("查看个人详情按钮");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						var manId = checkRows[0]["manId"];
						var url = IPLATUI.CONTEXT_PATH + "/web/DMFY0103?initLoad&manId=" + manId;
						var popData = $("#popDataShow");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataShowWindow.open().center();
					} else {
						NotificationUtil("请选择想要查看人员个人详情信息", "warning")
					}
				});

				//额外费用增加按钮
				$("#EXTRACHARGES").on("click", function(e) {
					console.log("额外费用增加");
					// 获取选中的行数据.
					var checkRows = resultGrid.getCheckedRows();
					// 判断是否有选中行.
					if (checkRows.length > 0) {
						var roomId = checkRows[0]["roomId"];
						var manId = checkRows[0]["manId"];
						var currentMonth = checkRows[0]["currentMonth"];
						var url = IPLATUI.CONTEXT_PATH + "/web/DMFY0101?initLoad&roomId=" + roomId + "&manId=" + manId + "&currentMonth=" + currentMonth;
						var popData = $("#popDataExtraCharges");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh({
									url : url
								});
							}
						});
						popDataExtraChargesWindow.open().center();
					} else {
						NotificationUtil("请选择想要额外增加费用的信息", "warning")
					}
				});


				//数据导入
				$("#IMPORT").on("click", function(e) {
					execlImportWindow.open().center()
				});

			}
		}
	}

	//提交数据导入
	var uploading = false;
	$("#IMPORTSUBMIT").on("click", function (e) {
		// 防止连续提交
		$("#IMPORTSUBMIT").attr("disabled",true);
		setTimeout(function(){$("#IMPORTSUBMIT").attr("disabled",false);},5000);
		//参数处理
		var form =  new FormData();
		form.append("fileUpload",$('#excelFile')[0].files[0]);
		//数据校验
		if ($('#excelFile')[0].files[0] == null){
			NotificationUtil("请上传文件","error");
			return;
		}
		if(uploading){
			NotificationUtil("数据正在提交中，请不要重复点击提交","warning");
			return;
		}
		//数据提交
		$.ajax({
			url: IPLATUI.CONTEXT_PATH+"/dmfyImport",
			type: 'POST',
			cache: false,
			processData: false,
			contentType: false,
			dataType : 'json',
			data:form,
			beforeSend: function(){ uploading = true; },
			success : function(data) {
				uploading = false;
				if(data.msg == "all"){
					NotificationUtil("数据导入成功","success");
					window['execlImportWindow'].close();
					setTimeout(function() {
						window.parent.location.reload()
					}, 1000);
				}else if (data.msg == "part"){
					NotificationUtil("导入数据存在问题，请查看返回文件","warning");
					downloadFileByBase64('data:application/xls;base64,'+data.base64, "dormitory_fee_error.xls");
					window['execlImportWindow'].close();
					setTimeout(function() {
						window.parent.location.reload()
					}, 600);
				}else if (data.msg == "error"){
					NotificationUtil("数据导入失败","error");
					window['execlImportWindow'].close();
				}
			}
		});
	});

	//关闭数据导入窗口
	$("#IMPORTCLOSE").on("click", function (e) {
		execlImportWindow.close();
	});

	//数据导入模板下载
	$("#download").click(function(){
		window.location.href =  IPLATUI.CONTEXT_PATH+"/dmfyImport";
	});

});

function downloadFileByBase64(base64,name){
	var myBlob = dataURLtoBlob(base64)
	var myUrl = URL.createObjectURL(myBlob)
	var failFile = document.createElement("a")//创建a标签
	failFile.setAttribute("href",myUrl)
	failFile.setAttribute("download",name)
	failFile.setAttribute("target","_blank")
	var clickEvent = document.createEvent("MouseEvents");
	clickEvent.initEvent("click", true, true);
	failFile.dispatchEvent(clickEvent);
}

function dataURLtoBlob(dataurl) {
	var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
		bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
	while (n--) {
		u8arr[n] = bstr.charCodeAt(n);
	}
	return new Blob([u8arr], { type: mime });
}