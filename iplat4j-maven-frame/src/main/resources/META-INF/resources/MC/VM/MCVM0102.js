/**
 * 新增变量信息
 */
$(function() {
	// 新增变量信息的保存按钮点击事件
	$("#SAVE").on("click", function (e) {
		var eiInfo = new EiInfo();
		var variableName = IPLAT.EFInput.value($("#variableName"));
		var id = IPLAT.EFInput.value($("#id"));

		//参数校验
		if(!validate(variableName)){
			return;
		}
		
		eiInfo.set("variableName",variableName);
		eiInfo.set("id",id);

		EiCommunicator.send("MCVM0102", "update", eiInfo, {
			onSuccess : function(ei) {
				NotificationUtil(ei.getMsg(), "success");
				window.parent.resultVarGrid.dataSource.page(1);
				window.parent['popDataWindow'].close();
			}
		});
    });
});

//参数校验
function validate(variableName){
	if(isEmpty(variableName)){
		NotificationUtil("变量名称不能为空", "error");
		return false;
	}
	return true;
}

function isEmpty(parameter){
	if(parameter == undefined || parameter == null){
		return true;
	} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
		return true;
	} else {
		return false;
	}
}