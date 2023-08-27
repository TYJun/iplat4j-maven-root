//接受id的变量
var datagrid = null;
var eiInfo = new EiInfo();
IPLATUI.EFGrid = {
  "result" : {
	  pageable : false,
	  exportGrid : false,
	  loadComplete: function (grid) {
		//新增按钮
			$("#INSERT0201").on("click", function() {
				popData();
			});
			
			// 数据提交
			$("#SAVEINFO201").on("click", function() {
				
				 eiInfo.setByNode("sumbit");
				 var content = resultGrid.getDataItems();
				 eiInfo.set("content", content);
				 if(!validatePR(eiInfo)){
					 return ;
				} 
				 EiCommunicator.send("DIKP0201","updateResult", eiInfo,{
						onSuccess : function(ei) {
							IPLAT.NotificationUtil(ei.msg);
							window.parent["resultGrid"].dataSource.page(1);
							 window.parent['popDataWindow'].close();
						}
					}) 
			});
			
	  },
	  onCheckRow : function(e) {
	    if (!e.fake) {
	     datagrid = null;
	     var grid = e.sender,
	      model = e.model, 
	      $tr = e.tr,
	      row = e.row;
	     console.log("ssss++"+row);
	      datagrid = model; 
	      console.log("mmm++"+datagrid);
	    }
	  }
  }
 } 
 
save = function(rows) {
	//关闭子页面
	var grid = $("#ef_grid_result").data("kendoGrid");
	if (grid.getDataItems().length > 0) {
		grid.checkAllRows();
	}
	var data = grid.getCheckedRows();// 获取选中行数据
	if(Array.isArray(rows)){
		//去重
		data = mergeArray(data, rows)
	}else{
		//去重
		data = merge(data, rows)
	}
	/*if(data == 1){
		NotificationUtil("请勿添加重复设备", "error");
		return;
	}*/
	// 回填
	grid.addRows(data)
	grid.unCheckAllRows();
}
//一数组去除重复数值
function merge(data, rows) {
	for (var i = 0; i < data.length; i++) {
		var a = data[i].idd;
		var b = rows.idd;
			if (a == b) {
				return;
			}
	}
	return rows;
}
//两数组去除重复数值
function mergeArray(data, rows) {
	for (var i = 0; i < data.length; i++) {
		var a = data[i].idd;
		for (var j = 0; j < rows.length; j++) {
			var b = rows[j].idd;
			if (a == b) {
				//利用splice函数删除元素，从第i个位置，截取长度为1的元素
				rows.splice(j, 1);
			}
		}
	}
	return rows;
}

	// 弹窗  项目信息展示
	function popData() {
		var url = IPLATUI.CONTEXT_PATH + "/web/DIKP03";
		var popData = $("#popData");
		
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});	
			}
		});
		// 打开生成弹窗
		popDataWindow.open().center();
	}
	
	// 数据删除操作
/* 	$("#DELETE0201").on("click", function(e) {
		
		 eiInfo.setByNode("sumbit");
		 var content = resultGrid.getDataItems();
		 eiInfo.set("content", content);
		 if(!validatePR(eiInfo)){
			 return ;
		} 
		 EiCommunicator.send("DIKP0201","deleteResult", eiInfo,{
				onSuccess : function(ei) {
					IPLAT.NotificationUtil(ei.msg);
					window.parent["resultGrid"].dataSource.query(1);
				}
			}) 
	}); */
	
	
	//提交时判断数据是否存在
	function validatePR(eiInfo) {
		  if (isEmpty(eiInfo.get("inqu_status-0-cardname"))) {
			  IPLAT.NotificationUtil("卡片名称不能为空","error");
		   return false;
		  }
		  if (isEmpty(eiInfo.get("inqu_status-0-memo"))) {
			  IPLAT.NotificationUtil("备注不能为空","error");
		   return false;
		  }

		  return true;
		 }
	
	// 判空
	function isEmpty(str) { 
		  if(str == undefined){
		   return true;
		  }
		  if(str == null){
		   return true;
		  }
		  if(str.replace(/(^\s*)|(\s*$)/g, "") == ""){
		   return true;
		  }
		  return false;
		 }
