/**
 * 设备系统分类菜单树
 */
$(function() {
	var tempClassifyName = "";
	var classifyName = "";
	
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	
	$("#RESET").on("click", function (e) {
		document.getElementById("inqu-trash").click();
		resultGrid.dataSource.page(1);
    });
	
	// 定义结点的各属性名
	var NODE = {
		nodeldField:"id",
		nodeTextField:"classifyName",
		parentIdField:"parentId",
		nodeTypeField:"isLeaf"
	};
	IPLATUI.EFTree = {
		"menu": {
			ROOT: {
				id: "root",
				classifyName: "分类信息",
				isLeaf: true
			},
			/**
			 * 树节点内容点击触发回调函数（点击节点内容前展开收起符号不会触发）
			 */
			select: function(e) {
			    var _data = this.dataItem(e.node);
			    tempId = _data.id;
			    tempClassifyName = _data.classifyName;
			    if(tempClassifyName == "分类信息"){
			    	tempId = "";
			    	tempClassifyName = "";
			    	$("#queryModuleId").val(tempId);
				    $("#queryClassifyName").val(tempClassifyName);
			    	resultGrid.dataSource.page(1);
			    	$("#queryClassifyName").val(_data.classifyName);
			    	classifyName = _data.classifyName;
			    	return;
			    }
				$("#queryModuleId").val(_data.id);
			    $("#queryClassifyName").val(_data.classifyName);
			    resultGrid.dataSource.page(1);
			},
			/**
			 * 树加载完成后的回调函数
			 * @param options: 树的配置项
			 */
			loadComplete: function (options) {
				// 保持节点展开状态
				var expanded = Cookies.get('expanded');
				if (expanded) {
					Cookies.remove('expanded');
					expanded = JSON.parse(expanded);
					$("#menu").data("kendoTreeView").expandPath(_.keys(expanded));
				}
			
				// 树节点右击增删改
				var _tree = this;
				$("#handleMenu").kendoContextMenu({
					target: "#" + options.treeId,
					filter: ".k-in",
					select: function (e) {
						var node = e.target;
						// 当前操作结点的 Model,包含该结点的一切属性信息
						var model = _tree.dataItem(node);
						var model2Ei = model2EiInfo(model);
						// 当前操作结点的父结点拥有的子结点个数
						var pNodeChildrenCount = _tree.parent(e.target).find('.k-item').length;
					}
				});
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
		}
	};
	
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
				})
			}
		}
	};
	
	function model2EiInfo(model) {
		var ei = new EiInfo(), key;
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
	
	IPLATUI.EFGrid = {
	        "result": {
	            //结果集中年份字段超链接
	            loadComplete: function (grid) {
	                // 录入
	            	$("#REGISTER").on("click", function (e) {
	            		var moduleId = IPLAT.EFInput.value($("#queryModuleId"));
	            		var classifyName = IPLAT.EFInput.value($("#queryClassifyName"));
	            		var url = IPLATUI.CONTEXT_PATH + "/web/DFCS0101?moduleId="+moduleId+"&classifyName="+classifyName;
		            	var popData = $("#popData");
		            	popData.data("kendoWindow").setOptions({
		            		open : function() {
		            			popData.data("kendoWindow").refresh({
		            				url : url
		            			});
		            		}
		            	});
		            	popDataWindow.open().center();
	                });
	            	// 编辑
	            	$("#REEDIT").on("click", function (e) {
	            		var paramKey;
	            		var rows = resultGrid.getCheckedRows();
	            		if(rows.length > 0){
	            			paramKey = rows[0].paramKey;
	            		}else{
	            			NotificationUtil("没有勾选数据", "error");
	            			return;
	            		}
	            		var url = IPLATUI.CONTEXT_PATH + "/web/DFCS0102?paramKey="+paramKey;
		            	var popData = $("#popData");
		            	popData.data("kendoWindow").setOptions({
		            		open : function() {
		            			popData.data("kendoWindow").refresh({
		            				url : url
		            			});
		            		}
		            	});
		            	popDataWindow.open().center();
	                });
	            	// 删除
	            	$("#REDELETE").on("click", function (e) {
	            		var checkRows = resultGrid.getCheckedRows();
						if(checkRows.length < 1){
							NotificationUtil("请选择要删除的行", "error");
							return;
						}
						var arr=[];
						for(var i = 0;i < checkRows.length;i++){
							arr[i] = checkRows[i].paramKey;
						}
						var info = new EiInfo();
						info.set("list",arr);
						EiCommunicator.send("DFCS01", "delete", info, {
							onSuccess : function(ei) {
								NotificationUtil(ei.getMsg(), "success");
								if(classifyName == "分类信息"){
									tempId = "";
									tempClassifyName = "";
									$("#queryModuleId").val(tempId);
									$("#queryClassifyName").val(tempClassifyName);
									resultGrid.dataSource.page(1);
									$("#queryClassifyName").val(classifyName);
									return;
								}
								resultGrid.dataSource.page(1);
							}
						});
	                });
	            	// 查看
	            	$("#RELOOK").on("click", function (e) {
	            		var paramKey;
	            		var rows = resultGrid.getCheckedRows();
	            		if(rows.length > 0){
	            			paramKey = rows[0].paramKey;
	            		}else{
	            			NotificationUtil("没有勾选数据", "error");
	            			return;
	            		}
	            		var url = IPLATUI.CONTEXT_PATH + "/web/DFCS0103?paramKey="+paramKey;
		            	var popData = $("#popData");
		            	popData.data("kendoWindow").setOptions({
		            		open : function() {
		            			popData.data("kendoWindow").refresh({
		            				url : url
		            			});
		            		}
		            	});
		            	popDataWindow.open().center();
	                });
	            }
	        }
	};
});