
$(function() {
	$("#QUERY").on("click", function (e) {
		resultGrid.dataSource.page(1);
    });
	
	$("#RESET").on("click", function (e) {
		//$("#typeId").val("");
		$("#typeName").val("");
		$("#content").val("");
		resultGrid.dataSource.page(1);
    });
	
	// 定义结点的各属性名
	var NODE = {
		nodeldField:"id",
		nodeTextField:"classifyName",
		parentIdField:"parentId",
		nodeTypeField:"hasChildren",
		nodeMemo:"memo"
	};
	IPLATUI.EFTree = {
		"menu": {
			ROOT: {
				id: "root",
				classifyName: "根节点",
				hasChildren: true
			},
			/**
			 * 树节点内容点击触发回调函数（点击节点内容前展开收起符号不会触发）
			 */
			select: function(e) {
			    var _data = this.dataItem(e.node);
			    $("#typeId").val(_data[NODE.nodeldField]);
			    if($("#typeId").val()=="root"){
			    	$("#typeId").val("");
			    }
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
			
				//树节点右键增删改
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
						/**
						 * 根据右击菜单选项类型进行节点操作
						 * add：新增，delete：删除，edit：编辑
						 */
						var selectedType = $(e.item).data("type");
						// 新增
						if (selectedType === "add") {
							addWndWindow.open().center();
							$("#addNode").unbind('click').click(function () {
								var validator = IPLAT.Validator({
									id: "addWnd"
								});
								if (validator.validate()) {
									var eiInfo = new EiInfo();
									// 获取父节点
									var parentId = model[NODE.nodeldField];
									var pId = model[NODE.parentIdField];
									
									//判断
									var classifyName = IPLAT.EFInput.value($("#classifyName"));
									if (classifyName == null || classifyName == "") {
										NotificationUtil("巡检分类名称不得为空", "error");
										return;
									}
									
									eiInfo.set("classifyName", $("#classifyName").val());
									eiInfo.set("memo", $("#memo").val());
									eiInfo.set("parentId", parentId);
									EiCommunicator.send("IMXM01", "insert", eiInfo, {
										onSuccess: function () {
											NotificationUtil("【" + model[NODE.nodeTextField] + "】" + "子结点添加成功");
											_tree.reload(model[NODE.parentIdField]);
											IPLAT.clearNode(document.getElementById("addNodeDiv"));
											addWndWindow.close();
											setTimeout(function(){window.parent.location.reload()},10);
//											if(parentId === "root"){
//												// 局部刷新
//												setTimeout(function(){window.parent.location.reload()},10);
//											}
										},
										onFail: function (ei) {
											NotificationUtil("调用失败，原因[" + ei + "]", "error");
										}
									});
								}
							});
						}
						if (selectedType === "delete") {
							var deleteHTML = kendo.template($("#del-template").html())(
								{message: "确认删除此结点和它所有的子结点下的所有数?", ok: '确定', cancel: '取消'}
							);
							// 弹出警告框,确定是否删除结点
							WindowUtil({
								title: "警告",
								content: deleteHTML,
								ok: function () {
									var id = model[NODE.nodeldField];
									var parentId = model[NODE.parentIdField];
									var eiInfo = new EiInfo();
									eiInfo.set("id", id);
									eiInfo.set("parentId",parentId);
									EiCommunicator.send("IMXM01", "deleteBatch", eiInfo, {
										onSuccess: function (eiInfo) {
											NotificationUtil("【" + model[NODE.nodeTextField] + "】" + "结点删除成功");
											setTimeout(function(){window.parent.location.reload()},10);
										} , onFail: function (eiInfo) {
											NotificationUtil("调用失败，原因[" + eiInfo + "]", "error");
										}
									});
									this.data("kendoWindow").close();
									//_tree.reload(model[NODE.parentIdField]); // 删除成功，重新加载父结点
								},
								cancel: function () {
									this.data("kendoWindow").close(); // 关闭警告框
								}
							});
						}
						if (selectedType === "edit") {
							$("#EclassifyName").val(model[NODE.nodeTextField]);
							$("#Ememo").val(model[NODE.nodeMemo]);
							var id = model[NODE.nodeldField];
							var nodeName = model[NODE.nodeTextField];
							editWndWindow.open().center();
							$("#editNode").unbind('click').click(function () {
								var validator = IPLAT.Validator({
									id: "editWnd"
								});
								if (validator.validate()) {
									//判断
									var EclassifyName = IPLAT.EFInput.value($("#EclassifyName"));
									if (EclassifyName == null || EclassifyName == "") {
										NotificationUtil("巡检项目分类名称不得为空", "error");
										return;
									}
									
									model2Ei.set("classifyName", $("#EclassifyName").val());
									model2Ei.set("memo", $("#Ememo").val());
									model2Ei.set("id", id);
									EiCommunicator.send("IMXM01", "update", model2Ei, {
										onSuccess: function () {
											// 重新加载父结点信息
											_tree.reload(model[NODE.parentIdField]);
											NotificationUtil("【" + model[NODE.nodeTextField] + "】" + "结点更新成功");
											// 清除编辑面板 div 信息
											IPLAT.clearNode(document.getElementById("editNodeDiv"));
											editWndWindow.close();
										},
										onFail: function (ei) {
											// 调用异常
											NotificationUtil("调用失败，原因[" + ei + "]", "error")
										}
									});
								}
							});
						}
					}
				});
				
			},
			
			//数据增删改之后的回调
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
				$("#ADD").on("click", function (e) {
					var moduleId = IPLAT.EFInput.value($("#typeId"));
					if (moduleId == null || moduleId == "") {
						NotificationUtil("请选择项目分类添加巡检项目,分类信息节点不能新增项目", "error");
						return;
					}
					var url = IPLATUI.CONTEXT_PATH + "/web/IMXM0101?moduleId="+moduleId;
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
				$("#EDIT").on("click", function (e) {
					var checkRows = resultGrid.getCheckedRows();
					if(checkRows.length!=1){
						NotificationUtil("请选中一条数据", "error");
						return;
					}
					var id = checkRows[0].id;
					var url = IPLATUI.CONTEXT_PATH + "/web/IMXM0102?id="+id;
					var popData = $("#popDataEdit");
					popData.data("kendoWindow").setOptions({
						open : function() {
							popData.data("kendoWindow").refresh({
								url : url
							});
						}
					});
					popDataEditWindow.open().center();
			    });
				$("#DELETEROW").on("click", function (e) {
					var checkRows = resultGrid.getCheckedRows();
					if(checkRows.length<1){
						NotificationUtil("请选择要删除的行", "error");
						return;
					}
					var arr=[];
					for(var i=0;i<checkRows.length;i++){
						arr[i]=checkRows[i].id;
					}
					var info=new EiInfo();
					info.set("list",arr);
					EiCommunicator.send("IMXM01", "deleteItem", info, {
						onSuccess : function(ei) {
							NotificationUtil(ei.getMsg(), "success");
							resultGrid.dataSource.page(1);
						}
					});
			    });
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
	
	
});