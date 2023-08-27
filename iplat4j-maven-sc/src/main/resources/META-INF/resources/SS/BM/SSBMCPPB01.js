$(function() {
	// 定义结点的各属性名
	var NODE = {
		nodeIdField:"value",
		nodeTextField:"text",
		parentIdField:"pid",
		nodeTypeField:"isLeaf"
	};
	IPLATUI.EFTree = {
		"menu" : {
			ROOT : {
				value : '0',
				text : '排班日期'
			},
			select : function(e) {
				var node = this.dataItem(e.node);
				if(node.pid != "0" && node.id != "0"){
					IPLAT.EFInput.value($("#inqu_status-0-mealDate"),node.id);
					refreshResultGrid();
				}else{
					IPLAT.EFInput.value($("#inqu_status-0-mealDate"),"");
					//$("#menu").data("kendoTreeView").expandPath(node.id);
				}
				
				/*
				 * $("#queryModuleId").val(_data.id);
				 * $("#queryClassifyName").val(_data.classifyName);
				 */
			},
			/**
			 * 树加载完成后的回调函数
			 * 
			 * @param options:
			 *            树的配置项
			 */
			loadComplete : function(options) {
				// 保持结点展开本周
				var str = {0: true, thisWeek: true};
				var expanded = Cookies.get('expanded');
				if (expanded) {
					Cookies.remove('expanded');
					$("#menu").data("kendoTreeView").expandPath(_.keys(str));
				}else{
					$("#menu").data("kendoTreeView").expandPath(_.keys(str));
				}
				
			},
			/**
			 * expand(e)：结点展开前回调函数, e.node 指被展开的结点;
			 * collapse(e)：结点收起前回调函数, e.node 指被收起的结点;
			 * saveExpanded()：结点展开和收起时，记录结点展开状态
			 */
			expand: function () {
				saveExpanded()
			},
			collapse: function () {
				saveExpanded();
			}
		}
	};
});

// Cookies 保存结点展开状态 Fn
function saveExpanded() {
	var treeview = $("#menu").data("kendoTreeView");
	var expandedItemsIds = {};
	treeview.element.find(".k-item").each(function () {
		var item = treeview.dataItem(this);
		if (item.expanded) {
			expandedItemsIds[item.id] = true;
		}
	});
	Cookies.set('expanded', kendo.stringify(expandedItemsIds));
}

function initTreeNode(){
	
}

function showdate() {
	if (new Date().getDay() == 0) {
		var now = new Date();
		var n = now.getDay();
		for (var i = 1; i <= 7; i++) {
			var start = new Date();
			start.setDate(now.getDate() - n + i - 7);
			var month = start.getMonth() + 1;
			var date = start.getDate();
			if (month < 10) {
				month = "0" + month
			}
			if (date < 10) {
				date = "0" + date
			}
			start = start.getFullYear() + "-" + month + "-" + date;
			console.log("1," + start);
		}
	} else {
		var now = new Date();
		var n = now.getDay();
		for (var i = 1; i <= 7; i++) {
			var start = new Date();
			start.setDate(now.getDate() - n + i);
			var month = start.getMonth() + 1;
			var date = start.getDate();
			if (month < 10) {
				month = "0" + month
			}
			if (date < 10) {
				date = "0" + date
			}
			start = start.getFullYear() + "-" + month + "-" + date;
			console.log("2," + start);
		}
	}
}

function showNextWeekDate() {
	if (new Date().getDay() == 0) {
		var now = new Date();
		var n = now.getDay();
		for (var i = 1; i <= 7; i++) {
			var start = new Date();
			start.setDate(now.getDate() - n + i);
			var month = start.getMonth() + 1;
			if (month < 10) {
				month = "0" + month;
			}
			var day = start.getDate();
			if (day < 10) {
				day = "0" + day;
			}
			start = start.getFullYear() + "-" + month + "-" + day;
			console.log("3," + start);
		}
	} else {
		var now = new Date();
		var n = now.getDay();
		for (var i = 1; i <= 7; i++) {
			var start = new Date();
			start.setDate(now.getDate() - n + i + 7);
			var month = start.getMonth() + 1;
			if (month < 10) {
				month = "0" + month;
			}
			var day = start.getDate();
			if (day < 10) {
				day = "0" + day;
			}
			start = start.getFullYear() + "-" + month + "-" + day;
			console.log("4," + start);
		}
	}
}