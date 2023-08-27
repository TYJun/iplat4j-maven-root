

$(function() {
	$("#RESAVE").click(function(){
		//判断
		console.log(233);
		 validator = IPLAT.Validator({
			id: "inqu"
		});
		if (validator.validate()) {
			 eiInfo = new EiInfo();
			//获取tab数据
			var fileArray = FileResultGrid.getDataItems();
			eiInfo.set("fileList",fileArray);
			var taskNo = IPLAT.EFInput.value($("#taskNo"));
			var machineId= IPLAT.EFPopupInput.value($("#machineId"));
			var machineCode= IPLAT.EFPopupInput.text($("#machineId"));
			var machineName = IPLAT.EFInput.value($("#machineName"));
			var innerMachineCode = IPLAT.EFInput.value($("#innerMachineCode"));
			var checkType = IPLAT.EFSelect.value($("#checkType"));
			var statusCode = IPLAT.EFSelect.value($("#statusCode"));
			var thisCheckDate = IPLAT.EFInput.value($("#thisCheckDate"));
			 var thisFinishDate = IPLAT.EFInput.value($("#thisFinishDate"));
			 var nextCheckDate = IPLAT.EFInput.value($("#nextCheckDate"));
			 
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
});