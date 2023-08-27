$(function() {
	init();
	// 保存按钮
	$("#saveCheckList").unbind('click').on('click',function(event) {
		$("#saveCheckList").attr("disabled", true);
		setTimeout(function() {
			$("#saveCheckList").attr("disabled", false);
		}, 2000);
		// 判断选项是否有选择
		var array = [];
		// var reg = /^[0-9]*$/;
		// 非负数正则
		var reg = /^(\+)?\d+(\.\d+)?$/;
		for(var i=1; i<23; i++) {
			var selector = $("#itemCode"+ i).val();
			if (typeof($("input[name='"+selector+"_exits']:checked").val()) == "undefined"){
				alert("请选择第"+i+"行的项目是否存在");
				return;
			}else if (typeof($("input[name='"+selector+"_intact']:checked").val()) == "undefined"){
				alert("请选择第"+i+"行的项目是否完好");
				return;
			}else if (!reg.test(Number($("#"+selector+"_text").val()))){
				alert("第"+i+"行的其他费用仅需要输入数值(非负数)，不需要额外补充中文");
				return;
			}else {
				array.push(getParamter(i));//参数获取
			}
		}
		console.log(array);
		var eiInfo = new EiInfo();
		var id = IPLAT.EFInput
			.value($("#id")); /* 宿舍人员绑定关系表id */
		console.log(id);
		eiInfo.set("roomManId",id);
		eiInfo.set("list",array);
		EiCommunicator.send("DMQD01", "save", eiInfo, {
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

function getParamter (index) {
	var selector = $("#itemCode"+index).val();
	var exits = $("input[name='"+selector+"_exits']:checked").val();
	var intact = $("input[name='"+selector+"_intact']:checked").val();
	var extraCharges = Number($("#"+selector+"_text").val());
	var note = $("#"+selector+"_note").val();
	var param = {
			"id" : $("#"+selector+"_id").val(),		/* 主键*/
			"serialNumber" : $("#"+selector+"_num").text(), /* 序号*/
		    "itemCode" : selector,	/* 项目编码*/
		    "itemName" : $("#"+selector+"_name").text(),		/* 项目名称*/
			"exits" : exits,		/*有/否（单选框的值） */
		    "intact" : intact,		/*完好/损坏（单选框的值） */
		    "extraCharges" : extraCharges,	/* 其他费用（输入框的值）*/
			"note" : note,	/* 备注信息（输入框的值）*/
	}
	return param;
}

/**刷新**/
function fresh(){
	init();
}

//加载回显数据
function init() {
	var id = IPLAT.EFInput
		.value($("#id")); /* 宿舍人员绑定关系表id */
	console.log(id);
	var eiInfo = new EiInfo();
	eiInfo.set("roomManId",id);
	EiCommunicator.send("DMQD01", "queryByRoomManId", eiInfo, {
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
	var selector = "item" + row.serialNumber;
	$("#"+selector+"_id").val(row.id);
	//单选框回显值
	if(!isEmpty(row.existence)){
		$("input[name='"+selector+"_exits']").each(function() {
			if ($(this).val() != row.existence) {
				$(this).removeAttr("checked");
			} else {
				$(this).prop("checked", "checked");
			}
		});
	}
	if(!isEmpty(row.isIntact)){
		$("input[name='"+selector+"_intact']").each(function() {
			if ($(this).val() != row.isIntact) {
				$(this).removeAttr("checked");
			} else {
				$(this).prop("checked", "checked");
			}
		});
	}

	//文本框回显值
	if(isExist(selector+"_text")){
		$("#"+selector+"_text").val(row.extraCharges);
	}
	if(isExist(selector+"_note")){
		$("#"+selector+"_note").val(row.note);
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
