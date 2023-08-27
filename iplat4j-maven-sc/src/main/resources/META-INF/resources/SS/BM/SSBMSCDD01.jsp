<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<!-- SSBMSCDD01 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 引用/smp_meal/WebContent/WEB-INF/fragments/MEAL-head.jsp -->
<EF:EFPage prefix="MEAL">
	<EF:EFRegion id="inqu" title="送餐地点管理" showClear="true">
	<!--查询  -->
		<div class="row">
			<div style="margin-left: 0px;">
				<EF:EFSelect ename="inqu_status-0-canteenName" cname="食堂名称"
					colWidth="4" ratio="3:8">
					<EF:EFOptions blockId="canteenData" textField="typeName" valueField="typeCode" />
				</EF:EFSelect>
				<EF:EFInput ename="inqu_status-0-userId" disabled="true" style="display:none;" hidden="true" />
				<EF:EFInput ename="canteen" disabled="true" style="display:none;" hidden="true" />
				<EF:EFInput ename="address" disabled="true" style="display:none;" hidden="true" />
				<EF:EFInput ename="inqu_status-0-building" style="display:none;" hidden="true" />
				<EF:EFInput ename="inqu_status-0-floor" style="display:none;" hidden="true" />
			</div>
		</div>
	</EF:EFRegion>
	
	<!--树  -->
	<div class="col-md-3" style="padding-left: 0px;height:560px;">
		<EF:EFRegion id="tree" title="送餐地点菜单树" style="height:100%;">
			<EF:EFTree id="menu" textField="name" valueField="id" pid="topcode"
				hasChildren="hasChildren" serviceName="SSBMSCDD01" methodName="queryTree" >
			</EF:EFTree>
			<ul id="handleMenu" style="display: none">
				<li data-type="add"><span class="fa fa-plus"></span>添加</li>
				<li data-type="edit"><span class="fa fa-pencil"></span>编辑</li>
				<li data-type="delete"><span class="fa fa-trash"></span>删除</li>
			</ul>
			<!-- 弹窗，在树上直接添加节点 -->
			<EF:EFWindow id="addWnd" width="60%" height="30%" title="在所选结点下添加子结点" style="display: none">
				<EF:EFRegion id="add_node_region" title="修改结点" style="margin-bottom: 0;">
					<div id="addNodeDiv">
						<div class="row">
							<EF:EFSelect ename="addNodeName1" cname="楼" optionLabel="请选择" colWidth="10" ratio="3:8">
								<EF:EFOptions blockId="building" textField="typeName" valueField="typeCode" />
							</EF:EFSelect>
							<EF:EFSelect ename="addNodeName2" cname="层" type="hidden" optionLabel="请选择" colWidth="10" ratio="3:8">
								<EF:EFOptions blockId="floor" textField="typeName" valueField="typeCode" />
							</EF:EFSelect>
						</div>
					</div>
					<div class="k-window-save k-popup-save">
						<EF:EFButton ename="addNode" cname="确定" layout="1" class="i-btn-wide" />
					</div>
				</EF:EFRegion>
			</EF:EFWindow>
		</EF:EFRegion>
	</div>
	
	<!--结果集  -->
	<div class="col-md-9" style="padding-left: 0px;">
		<EF:EFRegion id="result" title="地点信息">
			<EF:EFGrid blockId="result" autoDraw="no" rowNo="true">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<%-- <EF:EFColumn ename="building" cname="楼" width="50" align="center" readonly="true" />
				<EF:EFColumn ename="floor" cname="层" width="50" align="center" readonly="true" /> --%>
				<EF:EFColumn ename="spotNum" cname="地点编码" width="50" align="center" readonly="true" />
				<EF:EFColumn ename="spotName" cname="地点名称" width="50" align="center" readonly="true" />
				<EF:EFColumn ename="room" cname="房间" width="50" align="center" readonly="true" />
				<EF:EFColumn ename="deptNum" cname="科室编码" width="50" align="center" readonly="true" />
				<EF:EFColumn ename="deptName" cname="科室名称" width="50" align="center" readonly="true" />
				<%-- <EF:EFColumn ename="canteenName" cname="食堂名称" width="50" align="center"
					readonly="true" /> --%>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
		<!-- 弹窗，在result进行增删改 -->
		<EF:EFWindow id="shEdit" width="550px" modal="true" url="" title="录入">
			<EF:EFRegion id="edit" title="" showClear="true">
				<div class="row">
					<EF:EFInput ename="deptNum" cname="科室编码"  align="center" colWidth="10"/>				
				</div>
				<div class="row">
					<EF:EFInput ename="deptName" cname="科室名称"  align="center" colWidth="10" />				
				</div>
				<div class="row">
					<EF:EFInput ename="spotNum" cname="地点编码"  align="center" colWidth="10"/>				
				</div>
				<div class="row">
					<EF:EFInput ename="spotName" cname="地点名称"  align="center"  colWidth="10"/>				
				</div>
				
			</EF:EFRegion>
			<EF:EFButton ename="cancel" cname="取消"  class="k-button window-btn" style="float:left;"/>
			<EF:EFButton ename="submit" cname="提交"  class="k-button window-btn" style="float:right;"/>
			<EF:EFInput ename="canteen" disabled="true" style="display:none;" hidden="true" />
			<EF:EFInput ename="address" disabled="true" style="display:none;" hidden="true" />
		</EF:EFWindow>

	<EF:EFWindow id="popData" url=" " lazyload="true" width="680px" height="625px">
	</EF:EFWindow>
</EF:EFPage>
<script>
    var ctx = "${ctx}";
    
    function bindContextMenu(value){
    	$("#handleMenu").kendoContextMenu({
            target: "#" + value,
            filter: ".k-in",
            select: function (e) {
                //var node = e.target;
                var uid = $("#"+menutreeId+"_tv_active").attr('data-uid');
                var node = $("#" + menutreeId).data("kendoTreeView").findByUid(uid);
                // 当前操作结点的 Model,包含该结点的一切属性信息
                var model = menuTree.dataItem(node);
                var model2Ei = model2EiInfo(model);
                // 当前操作结点的父结点拥有的子结点个数
                //var pNodeChildrenCount = menuTree.parent(e.target).find('.k-item').length;
                /**
                 * 根据右击菜单选项类型进行节点操作
                 * add：新增，delete：删除，edit：编辑
                 */
                var selectedType = $(e.item).data("type");
                // 新增
                if (selectedType === "add") {

                    /*判断：
                     * if(当前选中节点是根节点,添加楼信息)
                     * if(当前选中节点的父节点是根,添加层信息)
                     * if(当前选中节点层次为层,不弹出弹窗)
                     */
                    if (model.id.substring(0,2) == "ST") {//在根节点添加
                        //添加楼
                        $("#addNodeName1").closest(".col-md-10").css("display", "");
                        $("#addNodeName2").closest(".col-md-10").css("display", "none");
                    } else if (model["topCode"].substring(0,2) == "ST") {//在楼层次添加
                        //添加层
                        $("#addNodeName1").closest(".col-md-10").css("display", "none");
                        $("#addNodeName2").closest(".col-md-10").css("display", "");
                    } else {
                        return;
                    }
                    addWndWindow.open().center();
                    $("#addNode").unbind('click').click(function () {
                        var validator = IPLAT.Validator({
                                id: "addWnd"
                            });
                        if (validator.validate()) {
                            var eiInfo = new EiInfo();
                            // 获取父节点
                            var parentId = model[NODE.nodeIdField];
                            var pid = model[NODE.parentIdField];
                            var map = [];
                            if (model.id.substring(0,2) == "ST") {
                                //添加楼
                                eiInfo.set("code", IPLAT.EFSelect.value($("#addNodeName1")));
                                eiInfo.set("name", IPLAT.EFSelect.text($("#addNodeName1")));
                                eiInfo.set("topCode", parentId);
								//"hasChildren": 0有子节点
                                map.push( {"code" : IPLAT.EFSelect.value($("#addNodeName1")),
                                        "name": IPLAT.EFSelect.text($("#addNodeName1")),
                                        "topCode": parentId,
                                        "type": "building",
									"hasChildren": 0});
                            } else if (model["topCode"].substring(0,2) == "ST") {
                                //添加层
                                eiInfo.set("code", IPLAT.EFSelect.value($("#addNodeName2")));
                                eiInfo.set("name", IPLAT.EFSelect.text($("#addNodeName2")));
                                eiInfo.set("topCode", parentId);
								//"hasChildren": 1没有子节点
                                map.push( {"code" : IPLAT.EFSelect.value($("#addNodeName2")),
                                    "name": IPLAT.EFSelect.text($("#addNodeName2")),
                                    "topCode": parentId,
									"type": "floor",
                                    "hasChildren": 1});
                            } else {
                                return;
                            }
                            map[0]["canteenNum"] = IPLAT.EFSelect.value($("#inqu_status-0-canteenName"));
                            map[0]["canteenName"] = IPLAT.EFSelect.text($("#inqu_status-0-canteenName"));
                            eiInfo.set("submit",map);
                            eiInfo.set("type","tree1");
                            EiCommunicator.send("SSBMSCDD01", "insert", eiInfo, {
								onSuccess : function(ei) {
									if(ei.status < 1) {
										NotificationUtil(ei.getMsg(), "warning");
									}else{
										addWndWindow.close();
										NotificationUtil("【" + model[NODE.nodeTextField] + "】" + "子结点添加成功");
										menuTree.reload(model[NODE.parentIdField]);
										IPLAT.clearNode(document.getElementById("addNodeDiv"));
										setTimeout(function () {
											window.location.reload()
										}, 10);
									}
                                },
                                onFail: function (ei) {
                                    NotificationUtil("调用失败，原因[" + ei + "]", "error");
                                }
                            });
                        }
                    });
                }
                if (selectedType === "delete") {
                    var deleteHTML = kendo.template($("#del-template").html())({
                            message: "确认删除此结点?",
                            ok: '确定',
                            cancel: '取消'
                        });
                    // 弹出警告框,确定是否删除结点
                    WindowUtil({
                        title: "提示",
                        content: deleteHTML,
                        ok: function () {
                            var id = model[NODE.nodeIdField];
                            var nodeId = model[NODE.nodeIdField];
                            var eiInfo = new EiInfo();
                            eiInfo.set("id", id);
                            //获取当前节点
                            eiInfo.set("nodeId", nodeId);
                            EiCommunicator.send("SSBMSCDD01", "deleteBatch", eiInfo, {
                                onSuccess: function (eiInfo) {
                                    NotificationUtil("【" + model[NODE.nodeTextField] + "】" + "结点删除成功");
                                },
                                onFail: function (eiInfo) {
                                    NotificationUtil("调用失败，原因[" + eiInfo + "]", "error");
                                }
                            });
                            
                            // setTimeout(function () {
                            //     window.location.reload()
                            // }, 1);
                            this.data("kendoWindow").close();
                            menuTree.reload(model[NODE.parentIdField]); // 删除成功，重新加载父结点
                        },
                        cancel: function () {
                            this.data("kendoWindow").close(); // 关闭警告框
                        }
                    });
                }
                if (selectedType === "edit") {
                    //if(楼节点:节点的父节点为根节点)
                    //if(层节点:没有孩子节点)
                    //else 不能编辑
                    if (model["topCode"].substring(0, 2) == "ST") {
                        //编辑楼
                        $("#addNodeName1").closest(".col-md-10").css("display", "");
                        $("#addNodeName2").closest(".col-md-10").css("display", "none");
                        IPLAT.EFSelect.value($("#addNodeName1"),model.code);
                    } else if(model.id.substring(0, 2) == "ST"){
                        return;
                    }else {
                        //编辑层
                        $("#addNodeName1").closest(".col-md-10").css("display", "none");
                        $("#addNodeName2").closest(".col-md-10").css("display", "");
                        IPLAT.EFSelect.value($("#addNodeName2"),model.code);
                    }
                    $("#addNodeName1").val(model[NODE.nodeTextField]);
                    //$("#addNodeName2").val(model[NODE.nodeTextField]);

                    var id = model[NODE.nodeIdField];
                    //var nodeName1 = model[NODE.nodeTextField];

                    addWndWindow.open().center();
                    $("#addNode").unbind('click').click(function () {
                        var validator = IPLAT.Validator({
                                id: "addWnd"
                            });
                        if (validator.validate()) {
                            var map = [];
                            if (model["topCode"].substring(0, 2) == "ST") {
                                map.push( {"code" : IPLAT.EFSelect.value($("#addNodeName1")),
                                    "name": IPLAT.EFSelect.text($("#addNodeName1")),
                                    "id": id});
                            } else if (model.id.substring(0, 2) == "ST"){
                                return;
                            }else {
                                map.push( {"code" : IPLAT.EFSelect.value($("#addNodeName2")),
                                    "name": IPLAT.EFSelect.text($("#addNodeName2")),
                                    "id": id});
                            }
                            model2Ei.set("submit",map);
                            model2Ei.set("type","tree1");
                            EiCommunicator.send("SSBMSCDD01", "update", model2Ei, {
                                onSuccess: function () {
                                    addWndWindow.close();
                                    NotificationUtil("【" + model[NODE.nodeTextField] + "】" + "结点更新成功");
                                    // 重新加载父结点信息
                                    menuTree.reload(model[NODE.parentIdField]);
                                    // 清除编辑面板div信息
                                    IPLAT.clearNode(document.getElementById("addNodeDiv"));
                                    setTimeout(function(){
                                        window.location.reload()
                                    },10);
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
    }
	//关闭弹窗，供iframe调用
	function closeWindow(type,rows){
		submitType = type;
		if(submitType == "insert" && rows.length < 1){
			NotificationUtil("请选择科室", "warning");
			return ;
		}
		popDataWindow.close();
	}
	//弹窗
	function popData() {
		var paramInfo = getWindowQueryInfo();
		if(!paramInfo.canteenNum){
			NotificationUtil("请选择食堂", "warning");
			return;
		}else{
			if(!paramInfo.building || !paramInfo.floor){
				NotificationUtil("请选择楼和层", "warning");
				return;
			}
		}
		//为弹窗绑定属性
		var url = IPLATUI.CONTEXT_PATH + "/web/SSBMSCDD02";
		$("#popData").data("kendoWindow").setOptions({
			title : "科室信息",
			open : function(e) {
				$("#popData").data("kendoWindow").refresh({
					url : url
				});
			},
			close: function (e) {
				//关闭弹窗获取iframe传值
				var iframe = popDataWindow.element.children("iframe")[0].contentWindow;
				if(iframe){
					var data = iframe.getData();
					if(submitType == "insert" && data.length > 0){

						for (var i = 0; i < data.length; i++) {
							$.extend(data[i], paramInfo);
						}
						var eiInfo = new EiInfo();
						eiInfo.set("submit",data);
						eiInfo.set("type","result1");
						console.log(eiInfo);
						//提交数据
						EiCommunicator.send("SSBMSCDD01", submitType, eiInfo, {
							onSuccess : function(ei) {
								if(ei.status < 1) {
									NotificationUtil(ei.getMsg(), "warning");
								}else {
									NotificationUtil(ei.getMsg(), "success");
									//刷新grid
									refreshResultGrid();
								}
							}
						});
					}
				}
			}
		});
		// 打开弹窗
		popDataWindow.open().center();
		// 向子窗口中的 iframe 对象传值
		setTimeout(function(){
			var iframe = popDataWindow.element.children("iframe")[0].contentWindow;
			if(iframe){
				iframe.setData(getWindowQueryInfo());
			}
		}, 1000 );
	}
	/**获取弹窗查询条件*/
	function getWindowQueryInfo(){
		var building = IPLAT.EFInput.value($("#inqu_status-0-building"));
		var canteenNum = IPLAT.EFSelect.value($("#inqu_status-0-canteenName"));
		var floor = IPLAT.EFInput.value($("#inqu_status-0-floor"));
		//加载descriptionGrid菜品组成数据
		return params = {
			'building' : building,
			'canteenNum' : canteenNum,
			'floor' : floor
		};
	}
	$(function(){
		//录入
		$("#ADD").on("click", function(e) {
			//判断当前选择的是不是层
			if(selectedNode && selectedNode["topCode"].substring(0,2) != "ST" && selectedNode["id"].substring(0,2) != "ST" ){
				//打开弹窗的方法
				popData();
			}else{
				NotificationUtil("请选择对应的楼和层","warning");
			}
		});
	});
</script>

