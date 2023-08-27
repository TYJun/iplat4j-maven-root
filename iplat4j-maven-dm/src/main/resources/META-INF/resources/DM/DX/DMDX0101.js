$(function() {
	init();
	// 保存按钮
	$("#saveBatchSMSTemp").unbind('click').on('click',function(event) {
		$("#saveBatchSMSTemp").attr("disabled", true);
		setTimeout(function() {
			$("#saveBatchSMSTemp").attr("disabled", false);
		}, 2000);
		// 判断选项是否有输入
		var selector = $("#batchSmsTempCode").val();
		if (isEmpty($("#"+selector+"_msg").val())){
			alert("短信模板内容为空，请按配置格式要求输入！");
			return;
		}else {
			var smsTemp = $("#"+selector+"_msg").val();
			var param = {
				"id" : $("#"+selector+"_id").val(),		/* 主键*/
				"smsCode" : selector,	/* 模板编码*/
				"smsName" : $("#"+selector+"_name").text(),		/* 模板名称*/
				"smsTemp" : smsTemp,	/* 短信模板*/
				"running" : "1",
				"smsRecvCode" : ""
			}
		}
		console.log(param);
		var eiInfo = new EiInfo();
		eiInfo.set("list",param)
		EiCommunicator.send("DMDX0101", "save", eiInfo, {
			onSuccess : function(ei) {
				var popData = $("#popData",
					parent.document);
				popData.kendoWindow().data(
					"kendoWindow").close();
				NotificationUtil(ei.getMsg(), "success");
				setTimeout(function() {
					window.parent.location.reload()
				}, 600);
			}
		});
	});
});

/**刷新**/
function fresh(){
	init();
}

//加载回显数据
function init() {
	var selector = $("#batchSmsTempCode").val();
	var eiInfo = new EiInfo();
	eiInfo.set("configType",selector);
	EiCommunicator.send("DMDX0101", "query", eiInfo, {
		onSuccess : function(ei) {
			NotificationUtil(ei.getMsg(), "success");
			var list = ei.get("list");
			if (list.length > 0){
				console.log(list);
				buildRow(list[0]);
			}
		}
	});
}

// 数据回显
function buildRow(row){
	var selector = row.configType;
	$("#"+selector+"_id").val(row.id);
	//文本框回显值
	if(isExist(selector+"_msg")){
		$("#"+selector+"_msg").val(row.smsTemp);
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

function isExist(selector){
	if($("#"+selector).length>0){
		return true;
	} else {
		return false;
	}
}
