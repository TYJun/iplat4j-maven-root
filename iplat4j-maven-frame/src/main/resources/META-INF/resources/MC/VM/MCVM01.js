/**
 * 
 */
$(function() {

	// 查询按钮
	$("#QUERY").on("click", function (e) {
		resultVarGrid.dataSource.page(1);
    });

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu-trash").click();
		resultVarGrid.dataSource.query(1);
	});

	// 变量添加按钮
	$("#ADDVAR").on("click", function (e) {
		popData("ADDVAR");
	});


	// 变量编辑按钮
	$("#EDITVAR").on("click", function (e) {
		var checkRows = resultVarGrid.getCheckedRows();
		if(checkRows.length<1){
			NotificationUtil("请勾选要编辑的群组","error");
			return;
		}
		popData("EDITVAR");
		
    });
	

	// 变量删除按钮
	$("#DELETEVAR").on("click", function (e) {
		var checkRows = resultVarGrid.getCheckedRows();
		if(checkRows.length<1){
			NotificationUtil("请勾选要删除的群组","error");
			return;
		}
		var arr=[];
		for(var i=0;i<checkRows.length;i++){
			arr[i]=checkRows[i].id;
		}
		var info=new EiInfo();
		info.set("list",arr);
		IPLAT.confirm({
			message:"确定删除选中行?",
			okFn:function(e){
				EiCommunicator.send("MCVM01", "deleteVar", info, {
					onSuccess : function(ei) {
						if(ei.getStatus() == -1){
							NotificationUtil(ei.getMsg(),"error");
						}else{
							NotificationUtil(ei.getMsg(),"success");
							resultVarGrid.dataSource.page(1);
							resultPelGrid.dataSource.page(1);
						}
					}
				});
			}
		});
		
    });

	// 向选中的变量下添加人员
	$("#ADDPER").on("click", function (e) {
		var checkRows = resultVarGrid.getCheckedRows();
		if(checkRows.length<1){
			NotificationUtil("请先勾选群组","error");
			return;
		}
		popData("ADDPER");
	});

	
	// 人员删除按钮
	$("#DELETEPER").on("click", function (e) {
		var checkRows = resultPerGrid.getCheckedRows();
		if(checkRows.length<1){
			NotificationUtil("请勾选要删除的人员","error");
			return;
		}
		var arr=[];
		for(var i=0;i<checkRows.length;i++){
			arr[i]=checkRows[i].id;
		}
		var info=new EiInfo();
		info.set("list",arr);
		IPLAT.confirm({
			message:"确定删除选中行?",
			okFn:function(e){
				EiCommunicator.send("MCVM01", "deletePer", info, {
					onSuccess : function(ei) {
						NotificationUtil(ei.getMsg(), "success");
						resultPerGrid.dataSource.page(1);
					}
				});
			}
		});
    });


	/**
	 * 弹窗界面
	 */
	function popData(type) {
		if(type === "ADDVAR"){
			var url = IPLATUI.CONTEXT_PATH + "/web/MCVM0101";
			var popData = $("#popData");
			popData.data("kendoWindow").setOptions({
				open : function() {
					popData.data("kendoWindow").refresh({
						url : url
					});
				}
			});
			popDataWindow.open().center();
		}
		if(type === "EDITVAR"){
			var row = resultVarGrid.getCheckedRows()[0];
			var url = IPLATUI.CONTEXT_PATH + "/web/MCVM0102?id="+row.id+
				"&variableCode="+row.variableCode+
				"&variableName="+row.variableName;
			var popData = $("#popData");
			popData.data("kendoWindow").setOptions({
				open : function() {
					popData.data("kendoWindow").refresh({
						url : url,
						title:"编辑"
					});
				}
			});
			popDataWindow.open().center();
		}
		
		if(type === "ADDPER"){
			var row = resultVarGrid.getCheckedRows()[0];
			var url = IPLATUI.CONTEXT_PATH + "/web/MCVM0103?curVarId="+row.id;
			var popDataPer = $("#popDataPer");
			popDataPer.data("kendoWindow").setOptions({
				open : function() {
					popDataPer.data("kendoWindow").refresh({
						url : url
					});
				}
			});
			popDataPerWindow.open().center();
		}

	}

	IPLATUI.EFGrid = {
		"resultVar": {
			onRowClick:function(e){
				var model = e.model;
				$("#curVarId").val(model.id);
				resultPerGrid.dataSource.page(1);
			},
			onCheckRow:function(e){
				if(e.checked){
					var model = e.model;
					$("#curVarId").val(model.id);
					resultPerGrid.dataSource.page(1);
				}else{
					curVarId = null;
				}

			}

		},

	};
});