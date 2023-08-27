$(function() {

	/**
	 * 数据回显
	 */
	if(__ei.type == "edit"){
		IPLAT.EFTreeInput.setAllFields( $("#parentId") , __ei.parentId, __ei.superClassifyName);
		$("#wgroupNum_textField").val(__ei.wgroupName);
	}

	/**
	 * 医院标识分类树控件
	 * @type {{parentId: {ROOT: {label: string, text: string, leaf: boolean}, backFill: IPLATUI.EFTreeInput.parentId.backFill}}}
	 */
	IPLATUI.EFTreeInput = {
		"parentId": {
			ROOT: {label: "root", text: "医院标识分类", leaf: true},
			backFill: function (e) {
				let _data = e.node;
				$("#superClassifyNum").val(_data.code);
				$("#superClassifyName").val(_data.text);
			},
		}
	};

	/**
	 * 医院标识下拉控件
	 * @type {{wgroupNum: {select: IPLATUI.EFSelect.wgroupNum.select}}}
	 */
	IPLATUI.EFSelect = {
		"wgroupNum": {
			// 点击下拉选项时触发
			select:function(e) { //获取勾选值
				let dataItem = e.dataItem;
				$("#wgroupName").val(dataItem['textField']);
			}
		}
	};

	/**
	 * 医院标识分类类保存提交
	 */
	$("#SUBMIT").unbind('click').on('click', function(event){
		// 防止连续提交
		$("#SUBMIT").attr("disabled",true);
		setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);
		//参数处理
		let node = $('#HIFL0101');
		let classifyName = $("#classifyName").val();
		let parentId = $("#parentId").val();
		if(isEmpty(classifyName)){
			NotificationUtil("医院标识名称不能为空", "error");
			return;
		} 
		if(isEmpty(parentId)){
			NotificationUtil("上级分类不能为空", "error");
			return;
		} 
		IPLAT.submitNode(node, 'HIFL0101', 'save', {
			onSuccess : function(eiInfo) {
				NotificationUtil("保存成功", "");
				window.parent['popDataWindow'].close();
				//从新加载父页面数据
				setTimeout(function() { window.parent.location.reload() }, 600);
			},
			onFail : function(errorMsg, status, e) {
				NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
			}
		});
	});
	
});

/**
 * 空校验函数
 * @param parameter
 * @returns {boolean}
 */
function isEmpty(parameter){
	if(parameter == undefined || parameter == null){
		return true;
	} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
		return true;
	} else {
		return false;
	}
}