$(function () {
	init();
})

//加载回显数据
function init() {
	var id = IPLAT.EFInput.value($("#id")); /* 宿舍满意度评价结果表主键id */
	var eiInfo = new EiInfo();
	eiInfo.set("resultId",id);
	EiCommunicator.send("DMPJ010101", "queryEvalResultByResultId", eiInfo, {
		onSuccess : function(ei) {
			NotificationUtil(ei.getMsg(), "success");
			var list = ei.get("list");
			console.log(list);
			for(var index in list){
				buildRow(list[index])
			}
		}
	});
}

function buildRow(row){
	//回显值
	if(!isEmpty(row.evalGrade)){
		$("input[name='"+row.itemCode+"']").each(function() {
			if ($(this).val() != row.evalGrade) {
				$(this).removeAttr("checked");
			} else {
				$(this).prop("checked", "checked");
			}
		});
	}
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