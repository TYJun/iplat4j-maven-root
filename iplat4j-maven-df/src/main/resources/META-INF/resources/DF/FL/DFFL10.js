/**
 * 设备系统分类菜单树
 */
$(function() {
	// 用于保存左边树形结构选中和下方结果集选中信息
	var tempId,tempClassifyName,tempClassifyMemo;
	
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
			    tempClassifyCode = _data.classifyCode;
			    if(tempClassifyName == "分类信息"){
			    	tempId = "";
			    }
			    tempClassifyMemo = _data.memo;
			    $("#queryModuleId").val(tempId);
			    resultGrid.dataSource.page(1);
			    $("#queryModuleId").val("");
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
//				$("#handleMenu").kendoContextMenu({
//					target: "#" + options.treeId,
//					filter: ".k-in",
//					select: function (e) {
//						var node = e.target;
//						// 当前操作结点的 Model,包含该结点的一切属性信息
//						var model = _tree.dataItem(node);
//
//						var model2Ei = model2EiInfo(model);
//						// 当前操作结点的父结点拥有的子结点个数
//						var pNodeChildrenCount = _tree.parent(e.target).find('.k-item').length;
//						/**
//						 * 根据右击菜单选项类型进行节点操作
//						 * add：新增，delete：删除，edit：编辑
//						 */
//						var selectedType = $(e.item).data("type");
//						// 新增
//						if (selectedType === "add") {
//							$("#parentId").val(model[NODE.nodeldField]);
//							$("#parentName").val(model[NODE.nodeTextField]);
//							addWndWindow.open().center();
//							$("#addNode").unbind('click').click(function () {
//								var validator = IPLAT.Validator({
//									id: "addWnd"
//								});
//								if (validator.validate()) {
//									var eiInfo = new EiInfo();
//									// 获取父节点
//									var parentId = model[NODE.nodeldField];
//									eiInfo.set("classifyName", $("#classifyName").val());
//									eiInfo.set("parentId", parentId);
//									eiInfo.set("memo", $("#memo").val());
//									EiCommunicator.send("DFFL01", "insert", eiInfo, {
//										onSuccess: function () {
//											addWndWindow.close();
//											NotificationUtil("【" + model[NODE.nodeTextField] + "】" + "子结点添加成功");
//											
//											_tree.reload(model[NODE.parentIdField]);
//											
//											IPLAT.clearNode(document.getElementById("addNodeDiv"));
//											setTimeout(function(){window.location.reload()},10);
//										},
//										onFail: function (ei) {
//											NotificationUtil("调用失败，原因[" + ei + "]", "error");
//										}
//									});
//								}
//							});
//						}
//						if (selectedType === "delete") {
//							var deleteHTML = kendo.template($("#del-template").html())(
//								{message: "确认删除此结点和它所有的子结点?", ok: '确定', cancel: '取消'}
//							);
//							// 弹出警告框,确定是否删除结点
//							WindowUtil({
//								title: "警告",
//								content: deleteHTML,
//								ok: function () {
//									debugger
//									var id = model[NODE.nodeldField];
//									var parentId = model[NODE.parentIdField];
//									var eiInfo = new EiInfo();
//									eiInfo.set("id", id);
//									eiInfo.set("parentId", parentId);
//									EiCommunicator.send("DFFL01", "deleteBatch", eiInfo, {
//										onSuccess: function (eiInfo) {
//											NotificationUtil("【" + model[NODE.nodeTextField] + "】" + "结点删除成功");
//										} 
//									});
//									setTimeout(function(){window.location.reload()},1);
//									this.data("kendoWindow").close();
//									_tree.reload(model[NODE.parentIdField]); // 删除成功，重新加载父结点
//								},
//								cancel: function () {
//									this.data("kendoWindow").close(); // 关闭警告框
//								}
//							});
//						}
//						if (selectedType === "edit") {
//							$("#editClassifyName").val(model[NODE.nodeTextField]);
//							$("#editParentId").val(model[NODE.parentIdField]);
//							$("#editParentName").val(model.parentName);
//							$("#editMemo").val(model.memo);
//							debugger
//							var id = model[NODE.nodeldField];
//							var nodeName = model[NODE.nodeTextField];
//							var sortNo = model.sortNo;
//							editWndWindow.open().center();
//							$("#editNode").unbind('click').click(function () {
//								var validator = IPLAT.Validator({
//									id: "editWnd"
//								});
//								if (validator.validate()) {
//									model2Ei.set("classifyName", $("#editClassifyName").val());
//									model2Ei.set("sortNo", $("#editSortNo").val());
//									model2Ei.set("memo", $("#editMemo").val());
//									model2Ei.set("id", id);
//									EiCommunicator.send("DFFL10", "update", model2Ei, {
//										onSuccess: function () {
//											// 重新加载父结点信息
//											_tree.reload(model[NODE.parentIdField]);
//											NotificationUtil("【" + model[NODE.nodeTextField] + "】" + "结点更新成功");
//											// 清除编辑面板 div 信息
//											IPLAT.clearNode(document.getElementById("editNodeDiv"));
//											editWndWindow.close();
//										},
//										onFail: function (ei) {
//											// 调用异常
//											NotificationUtil("调用失败，原因[" + ei + "]", "error")
//										}
//									});
//								}
//							});
//						}
//					}
//				});
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
	        	onCheckRow:function(e){
	        		tempId = e.model.id;
	        		tempClassifyName = e.model.classifyName;
	        		tempClassifyMemo = e.model.memo;
	        	},
	            //结果集中年份字段超链接
	            loadComplete: function (grid) {
	                // 录入
	            	$("#REGISTER").on("click", function (e) {
	            		var moduleId = tempId;
	            		var classifyName = tempClassifyName;
//	            		if (moduleId == null || moduleId == "") {
//							NotificationUtil("请选择左侧分类", "error");
//							return;
//						}
	            		$("#parentId").val(moduleId);
						$("#parentName").val(classifyName);
						addWndWindow.open().center();
						$("#addNode").unbind('click').click(function () {
							var validator = IPLAT.Validator({
								id: "addWnd"
							});
							if (validator.validate()) {
								debugger
								var eiInfo = new EiInfo();
								// 获取父节点
								var parentId = moduleId;
								eiInfo.set("classifyName", $("#classifyName").val());
								eiInfo.set("parentId", $("#parentName").val());
								eiInfo.set("memo", $("#memo").val());
								EiCommunicator.send("DFFL10", "insert", eiInfo, {
									onSuccess: function () {
										addWndWindow.close();
										NotificationUtil("【" + $("#classifyName").val() + "】" + "子结点添加成功");
										window.location.reload();
										IPLAT.clearNode(document.getElementById("addNodeDiv"));
										setTimeout(function(){window.location.reload()},10);
									},
									onFail: function (ei) {
										NotificationUtil("调用失败，原因[" + ei + "]", "error");
									}
								});
							}
						});
	                });
	            	
	            	// 编辑
	            	$("#REEDIT").on("click", function (e) {
	            		var moduleId = tempId;
	            		var classifyName = tempClassifyName;
	            		var classifyMemo = tempClassifyMemo;
	            		if (moduleId == null || moduleId == "") {
							NotificationUtil("请选择左侧分类", "error");
							return;
						}
	            		$("#editClassifyName").val(classifyName);
						$("#editMemo").val(classifyMemo);
						var id = moduleId
						editWndWindow.open().center();
						$("#editNode").unbind('click').click(function () {
							var validator = IPLAT.Validator({
								id: "editWnd"
							});
							if (validator.validate()) {
								var eiInfo = new EiInfo();
								eiInfo.set("id", id);
								eiInfo.set("classifyName",  $("#editClassifyName").val());
								eiInfo.set("memo", $("#editMemo").val());
								EiCommunicator.send("DFFL10", "update", eiInfo, {
									onSuccess: function () {
										// 重新加载父结点信息
										NotificationUtil("【" + $("#editClassifyName").val() + "】" + "结点更新成功");
										// 清除编辑面板 div 信息
										IPLAT.clearNode(document.getElementById("editNodeDiv"));
										editWndWindow.close();
										window.location.reload();
									},
									onFail: function (ei) {
										// 调用异常
										NotificationUtil("调用失败，原因[" + ei + "]", "error")
									}
								});
							}
						});
	                });
	            	
	            	// 删除
	            	$("#REDELETE").on("click", function (e) {
	            		var moduleId = tempId;
	            		var classifyName = tempClassifyName;
	            		var classifyMemo = tempClassifyMemo;
	            		if (moduleId == null || moduleId == "") {
							NotificationUtil("请选择左侧分类", "error");
							return;
						}
	            		var deleteHTML = kendo.template($("#del-template").html())(
								{message: "确认删除此结点和它所有的子结点?", ok: '确定', cancel: '取消'}
							);
							// 弹出警告框,确定是否删除结点
							WindowUtil({
								title: "警告",
								content: deleteHTML,
								ok: function () {
									var id = moduleId;
									var eiInfo = new EiInfo();
									eiInfo.set("id", id);
									EiCommunicator.send("DFFL10", "deleteBatch", eiInfo, {
										onSuccess: function (eiInfo) {
											NotificationUtil("【" + classifyName + "】" + "结点删除成功");
										} 
									});
									this.data("kendoWindow").close();
									window.location.reload();
								},
								cancel: function () {
									this.data("kendoWindow").close(); // 关闭警告框
								}
							});
	                });
	            	
	            	// 查看
	            	$("#RELOOK").on("click", function (e) {
	            		var moduleId = tempId;
	            		var classifyName = tempClassifyName;
	            		var classifyMemo = tempClassifyMemo;
	            		if (moduleId == null || moduleId == "") {
							NotificationUtil("请选择左侧分类", "error");
							return;
						}
	            		$("#showClassifyName").val(classifyName);
						$("#showMemo").val(classifyMemo);
						var id = moduleId
						showWndWindow.open().center();
						$("#showNode").unbind('click').click(function () {
							showWndWindow.close();
						});
	                });
	            }
	        }
	};
});