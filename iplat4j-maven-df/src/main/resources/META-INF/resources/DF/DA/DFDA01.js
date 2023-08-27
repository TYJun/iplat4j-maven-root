/**
 * 
 */
$(function() {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	
	$("#RESET").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });
	
	IPLATUI.EFGrid = {
			"result": {
	            loadComplete: function (grid) {
					//生成二维码
					$("#QRCODE").on("click", function(e) {
						var row = resultGrid.getCheckedRows()[0];
						var id = row.id;
						var machineCode = row.machineCode;
						popData(id,machineCode);
					});
					function popData(id,machineCode) {
						var url = IPLATUI.CONTEXT_PATH + "/web/DFDA0104?&id="+id+"&machineCode="+machineCode;
						var popData = $("#popData");
						popData.data("kendoWindow").setOptions({
							open : function() {
								popData.data("kendoWindow").refresh( {
									url : url,
								});
							}
						});
						// 打开生成弹窗
						popDataWindow.open().center();
					};
	                // 新增
	            	$("#READD").on("click", function (e) {
	            		var url = IPLATUI.CONTEXT_PATH + "/web/DFDA0101";
		            	var popData = $("#popData");
		            	popData.data("kendoWindow").setOptions({
		            		open : function() {
		            			popData.data("kendoWindow").refresh({
		            				url : url
		            			});
		            		}
		            	});
		            	popDataWindow.open().center();
	                });
	            	// 编辑
	            	$("#REEDIT").on("click", function (e) {
	            		var id;
	            		var rows = resultGrid.getCheckedRows();
	            		if(rows.length > 0){
	            			id = rows[0].id;
	            		}else{
	            			NotificationUtil("没有勾选数据", "error");
	            			return;
	            		}
	            		var url = IPLATUI.CONTEXT_PATH + "/web/DFDA02?id="+id;
		            	var popData = $("#popData");
		            	popData.data("kendoWindow").setOptions({
		            		open : function() {
		            			popData.data("kendoWindow").refresh({
		            				url : url
		            			});
		            		}
		            	});
		            	popDataWindow.open().center();
	                });
	            	
	            	// 查看
	            	$("#RELOOK").on("click", function (e) {
	            		var id;
	            		var rows = resultGrid.getCheckedRows();
	            		if(rows.length > 0){
	            			id = rows[0].id;
	            		}else{
	            			NotificationUtil("没有勾选数据", "error");
	            			return;
	            		}
	            		var url = IPLATUI.CONTEXT_PATH + "/web/DFDA0102?id="+id;
		            	var popData = $("#popData");
		            	popData.data("kendoWindow").setOptions({
		            		open : function() {
		            			popData.data("kendoWindow").refresh({
		            				url : url
		            			});
		            		}
		            	});
		            	popDataWindow.open().center();
	                });
	            	
	            	// 删除
	            	$("#REDELETE").on("click",function(e) {
	            		var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length < 1){
							NotificationUtil("请选择要删除的行", "error");
							return;
						}
						var arr=[];
						for(var i = 0;i < checkRows.length;i++){
							arr[i] = checkRows[i].id;
						}
						var info = new EiInfo();
						info.set("list",arr);
						EiCommunicator.send("DFDA01", "deleteItem", info, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg(), "success");
								resultGrid.dataSource.page(1);
							}
						});
	            	});
	            	
	            	// 启用
	            	$("#RESTART").on("click",function(e) {
	            		var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length < 1){
							NotificationUtil("请选择要启用的行", "error");
							return;
						}
						var arr=[];
						for(var i = 0;i < checkRows.length;i++){
							arr[i] = checkRows[i].id;
						}
						var info = new EiInfo();
						info.set("list",arr);
						EiCommunicator.send("DFDA01", "startRow", info, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg(), "success");
								resultGrid.dataSource.page(1);
							}
						});
	            	});
	            	
	            	// 停用
	            	$("#RESTOP").on("click",function(e) {
	            		var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length < 1){
							NotificationUtil("请选择要停用的行", "error");
							return;
						}
						var arr=[];
						for(var i = 0;i < checkRows.length;i++){
							arr[i] = checkRows[i].id;
						}
						var info = new EiInfo();
						info.set("list",arr);
						EiCommunicator.send("DFDA01", "stopRow", info, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg(), "success");
								resultGrid.dataSource.page(1);
							}
						});
	            	});
	            	
	            	//数据导入 
					$("#IMPORT").on("click", function(e) {
						execlImportWindow.open().center()
					});
	            }
	        }
	};
	
	//提交数据导入
	var uploading = false;
	$("#IMPORTSUBMIT").on("click", function (e) {
		// 防止连续提交
		$("#SUBMIT").attr("disabled",true);
		setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);
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
			url: IPLATUI.CONTEXT_PATH+"/dfdaImport",
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
					}, 600);
				}else if (data.msg == "part"){
					NotificationUtil("导入数据存在问题，请查看返回文件","warning");
					downloadFileByBase64('data:application/xls;base64,'+data.base64, "df_da_error.xls");
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
		window.location.href =  IPLATUI.CONTEXT_PATH+"/dfdaImport";
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