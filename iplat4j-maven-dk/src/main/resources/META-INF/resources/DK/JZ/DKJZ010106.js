//声明一个控制点击的变量
var datagrid = null;

IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender,
					model = e.model, 
					$tr = e.tr,
					row = e.row;
					datagrid = model;
				}
			}
		}
}

$(function(){
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.query(1);
	});
	
	//确定按钮
	$("#SAVE").on("click", function(e) {
		if(null == datagrid){
			IPLAT.alert("请选择需要添加的卡片");
			return; 
		}
		debugger;
		var info = new EiInfo();
		var id = datagrid.cardid;
		info.set("cardid",id);
		EiCommunicator.send("DKJZ010106", "queryProjectInfo", info, {
			onSuccess : function(ei) {
				debugger;
				NotificationUtil(ei.getMsg(), "success");
				var param = ei.extAttr.param;
				
				for (var i = 0; i < param.length; i++) {
					
					var model = createModel(i);
					var par = param[i];
					
					var id = par.itemidXmid;
					var itemId = par.itemidXmid;
					var content = par.content;
					var referenceValue = par.referencevalue;
					var projectDesc = par.itemdesc;
					var actualValueUnit = par.actualvalueunit;
					
					//debugger;
					model.id = id;
					model.itemId = itemId;
					model.content = content;
					model.referenceValue = referenceValue;
					model.projectDesc = projectDesc;
					model.actualValueUnit = actualValueUnit;
					model.code = "";
					
					//遍历渲染参数，必须存model对象
					//window.parent.deviceGrid.addRows(model);
					window.parent.saveItem(model);
				}
				window.parent['popDataItemCardWindow'].close();
			}
		});
	});
	
	//创建kendo.data.Model
	function createModel(id){
		var gridRow = kendo.data.Model.define({
		    id: "uploadId", 
		    fields: {
		        "id": {type: "string"},
		        "itemId": {type: "string"},
		        "content": {type: "string"},
		        "referenceValue": {type: "string"},
		        "projectDesc": {type: "string"},
		        "actualValueUnit": {type: "string"},
		        "code": {type: "string"}
		    }
		});
		var model = new gridRow({uploadId:id});
		return model;
	}
	
})