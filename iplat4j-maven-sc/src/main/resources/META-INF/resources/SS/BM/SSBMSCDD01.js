var menuTree = null;

//提交类型
var submitType = "insert";
var editValidator = null;
IPLATUI.EFGrid = {
	    "result": {
	        beforeRequest: function (e) {
	        	/* 查询之前添加参数，避免点击清除按钮后条件失效 */
	        	IPLAT.EFInput.value( $("#inqu_status-0-userId") ,IPLATUI.USER_ID);
	        }
	    }
	}

IPLATUI.EFGrid = {
	"result": {
		loadComplete: function (grid) {
			grid.dataSource.bind("requestEnd", function (e) {
				var response = e.response;
				var type = e.type; // 事件类型
				if ("create" === type || "update" === type || "destroy" === type) {
					// 获取 kendo 的树
					var tree = $("#menu").data("kendoTreeView");
					var ajaxEi = EiInfo.parseJSONObject(response);
					var result = ajaxEi.getBlock("result");
					var rows = result.getMappedRows();
					// rows 表示修改的结点信息
					for (var i = 0; i < rows.length; i++) {
						var node = rows[i];
						tree.reload(node[NODE.parentIdField]); // 刷新树结点信息
					}
				}
			});
		},
		//按钮绑定事件
		loadComplete: function(grid){

        }
	}
};

//关闭事件
IPLATUI.EFWindow = {
"shEdit": {
    close: function (e) {
        //EFRegion的id-trash
        $("#edit-trash").click();
    	}
	}	
}


//按钮绑定事件
$(function(){
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
	});
	var options = {
			url :  ctx,
			serviceName : "SSBMSCDD01",
			methodName : "queryTree",
			textField : "name",
			valueField : "id",
			hasChildren : "hasChildren",
			pid : "topcode"
		};
	IPLATUI.EFSelect={
		"inqu_status-0-canteenName" : {
			change : function(e){
				if(e.sender._old){
					// 清空以前的 DIV 块
					$("#tree .block-content").empty();

					// 获取新的 DIV
					var div = document.createElement("div");
					//var id = "tree" + kendo.guid();
					var id = "menu" + "_" + e.sender._old;
					div.id = id;
					$("#tree .block-content").append(div);
					rootId = e.sender._old;
					menutreeId = id;
					// 构建一个新的 Tree
					var initOptions = $.extend({}, options, {
						treeId : id
					}, {
						ROOT : { 
							name: "送餐地点菜单树", 
							id: e.sender._old
						}
					});
					IPLAT.TreeView(initOptions);
					bindContextMenu(menutreeId);
					selectedNode = null;
				}
			}
		}
	}
})

$(function(){
	//页面加载时查询一次
	//refreshResultGrid();
	//启用校验
	editValidator=IPLAT.Validator({id:"edit"});
})

$(function () {
	//$("#addWnd").css("display", "none");
	//登录用户
	IPLAT.EFInput.value($("#inqu_status-0-userId"), IPLATUI.USER_ID);
	//隐藏控件
	$("#inqu_status-0-userId").closest(".col-md-4").attr("style", "display:none;");
});

var rootId = null;
var menutreeId = null;
var classTreeSelect = {classId : "",className : ""};
// 定义结点的各属性名
var NODE = {
		nodeIdField: "id",
		nodeTextField: "name",
		parentIdField: "topCode",
		nodeTypeField: "hasChildren"
};
$(function () {
	rootId = getBlocksMappedRows("canteenData")[0]["typeCode"];
	//初始化默认树
	menutreeId = "menu";
	IPLATUI.EFTree["menu"]= treeInit(rootId);
	bindContextMenu(menutreeId);
	var canteens = getBlocksMappedRows('canteenData');
	for (var i = 0; i < canteens.length; i++) {
		IPLATUI.EFTree["menu"+ "_" + canteens[i].typeCode] = treeInit(canteens[i].typeCode);
	}
});
var selectedNode = null;
function treeInit(rootId){
	return {
		ROOT: {
			id: rootId,
			name: "送餐地点菜单树"
		},
		/**
		 * 树节点内容点击触发回调函数（点击节点内容前展开收起符号不会触发）
		 */
		select: function (e) {
			var _data = this.dataItem(e.node);
			selectedNode = _data;
			if (_data.parentId != "root" && _data.id != "root") {
				classTreeSelect.classId = _data.id;
				classTreeSelect.className = _data.nodeName;
			}
			$("#inqu_status-0-canteenNum").val("option:selected").text();
			//判断是不是层topCode开头位ST的是楼，id开头为ST的是根节点
			if(_data["topCode"].substring(0,2) != "ST" && _data["id"].substring(0,2) != "ST" ){
				//找父节点
				var parentCode,parentName;
				var items = menuTree.dataItems()[0]["children"]["_view"];
				for (var i = 0; i < items.length; i++) {
					if(items[i]["id"] == _data["topCode"]){
						parentCode = items[i]["code"];
						parentName = items[i]["name"];
						break;
					}
				}
				IPLAT.EFInput.value( $("#inqu_status-0-building") ,parentCode);
				IPLAT.EFInput.value( $("#inqu_status-0-floor") ,_data["code"]);
				resultGrid.dataSource.page(1);
			}
		},
		/**
		 * 树加载完成后的回调函数
		 * @param options:树的配置项
		 */
		loadComplete: function (options) {
			// 保持节点展开状态
			var expanded = Cookies.get('expanded');
			if (expanded) {
				Cookies.remove('expanded');
				expanded = JSON.parse(expanded);
				//$("#menu").data("kendoTreeView").expandPath(_.keys(expanded));
				if($("#" + options.treeId).data("kendoTreeView")){
					$("#" + options.treeId).data("kendoTreeView").expandPath(_.keys(expanded));
				}
			}
			// 树节点右击增删改
			menuTree = this;
		},
		/**
		 * 数据源改变后回调，增删改查节点信息都会触发
		 * @param e
		 * e.node：被操作结点的父结点
		 */
		dataBound: function (e) {
			console.log("DataBound", e.node);
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
	};
}
//Cookies 保存结点展开状态 Fn
function saveExpanded() {
	var treeview = $("#menu").data("kendoTreeView");
	if(treeview){
		var expandedItemsIds = {};
		treeview.element.find(".k-item").each(function () {
			var item = treeview.dataItem(this);
			if (item.expanded) {
				expandedItemsIds[item.id] = true;
			}
		});
		Cookies.set('expanded', kendo.stringify(expandedItemsIds));
	}
}

// 用于删除结点时获取其所有子结点 id 的方法
function getChildrenIds(data, idField, parentId, rootId, itemName) {
	var mapList = IPLAT.flat2Map(data, idField, parentId, rootId);
	var list = [];
	list.push(rootId);
	var _map2Array = function (arrayList) {
		$.each(arrayList, function (i, array) {
			if (array[itemName] && array[itemName].length > 0) {
				_map2Array(array[itemName]);
			}
			list.push(array[idField]);
			delete array[itemName];
		})
	};
	_map2Array(mapList);
	return list;
}

function model2EiInfo(model) {
	var ei = new EiInfo(),
	key;
	var json = model.toJSON();
	for (key in json) {
		if (typeof json[key] === "string") {
			ei.set("result", "0", key, json[key]);
		}
		if (typeof json[key] === "boolean") {
			ei.set("result", "0", key, json[key] ? "2" : "1");
		}
	}
	return ei;
}

function getNodeByTextUid(text,uid){
	var nodes = menuTree.findByText(text);
	var flag = false;
	for (var i = 0; i < nodes.length; i++) {
		var datauid = $(nodes[i]).attr('data-uid');
		if(datauid == uid){
			flag = true;
			return nodes[i];
		}
	}
	if(!flag){
		return null;
	}
}