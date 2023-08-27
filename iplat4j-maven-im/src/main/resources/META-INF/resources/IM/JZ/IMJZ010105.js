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
					//console.log(datagrid);
				}
			}
		}
}
$(function(){
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});

	//重置按钮
	$("#REQUERY").on("click", function(e) {
		document.getElementById("inqu").click();
		resultGrid.dataSource.page(1);
	});
	
	//确定按钮
	$("#DETERMINE").on("click", function(e) {
		if(null == datagrid){
			IPLAT.alert("请选择需要添加的设备包");
			return; 
		}
		debugger;
		var info = new EiInfo();
		var id = datagrid.id;
		info.set("id",id);
		EiCommunicator.send("IMJZ0101", "device", info, {
			onSuccess : function(ei) {
				NotificationUtil(ei.getMsg(), "success");
				var param = ei.extAttr.param;
				
				for (var i = 0; i < param.length; i++) {
					
					var model = createModel(i);
					var par = param[i];
					
					var id = par.id;
					var packetId = par.packetId;
					var deviceId = par.deviceId;
					var machineCode = par.machineCode;
					var machineName = par.machineName;
					var models = par.models;
					var fixedPlace = par.fixedPlace;
					
					//debugger;
					model.id = id;
					model.packetId = packetId;
					model.machineId = deviceId;
					model.machineCode = machineCode;
					model.machineName = machineName;
					model.models = models;
					model.fixedPlace = fixedPlace;
					
					//遍历渲染参数，必须存model对象
					//window.parent.deviceGrid.addRows(model);
					window.parent.save(model);
				}
				window.parent['popDataDevicePackageWindow'].close();
			}
		});
	});
	
	//创建kendo.data.Model
	function createModel(id){
		var gridRow = kendo.data.Model.define({
		    id: "uploadId", 
		    fields: {
		        "id": {type: "string"},
		        "packetId": {type: "string"},
		        "machineId": {type: "string"},
		        "machineCode": {type: "string"},
		        "machineName": {type: "string"},
		        "models": {type: "string"},
		        "fixedPlace": {type: "string"}
		    }
		});
		var model = new gridRow({uploadId:id});
		return model;
	}
	
})