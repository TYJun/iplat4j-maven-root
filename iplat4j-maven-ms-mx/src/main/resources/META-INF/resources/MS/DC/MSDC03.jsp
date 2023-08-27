<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="区域名称" fitHeight="true">
			<EF:EFTree id="tree01" textField="text" valueField="label"
					   hasChildren="leaf" serviceName="MSDC03" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
        <EF:EFRegion id="inqu" title="查询条件" showClear="true">
            <div class="row">
                <EF:EFInput ename="inqu_status-0-classifyname" cname="下属分类名称" />
				<EF:EFInput ename="inqu_status-0-parentId" cname="" />
            </div>
        </EF:EFRegion>
		<br/>
		<EF:EFRegion id="result" title="下属分类">
			<EF:EFGrid blockId="result" autoDraw="no"   checkMode="single">
				<EF:EFColumn ename="id" cname="主键" width="200" hidden="true" />
                <EF:EFColumn ename="parentId" cname="主键" width="200" hidden="true" />
				<EF:EFColumn ename="classifyName" cname="分类名称" width="200" disabled="true"/>
				<EF:EFColumn ename="classifyCode" cname="排序编号" width="200" disabled="true"/>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="60%"
			 height="50%">
</EF:EFWindow>

<script type="text/javascript">
	var datagrid = null;
    var treestate=true;
	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}else{
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			},
		}
	}
	//刷新树
    function reload() {
            var tree = $("#tree01").data("kendoTreeView");
            var inInfo = new EiInfo();
        //    inInfo.set("label", e.model['ename']);//grid
            EiCommunicator.send("MSDC03", "queryTree",inInfo ,{//
                onSuccess: function (inInfo) {
                    tree.reload("root");//
                }
            });
    }
	//弹窗
	function popData(id, parentId, type) {
		var url = IPLATUI.CONTEXT_PATH + "/web/MSDC0301?initLoad&id=" + id
            + "&parentId=" + parentId+ "&type=" + type;
		var popData = $("#popData");
		popData.data("kendoWindow").setOptions({
			open : function() {
				popData.data("kendoWindow").refresh({
					url : url,
				});
			}
		});
		// 打开工作流节点人员选择弹窗
		popDataWindow.open().center();
	}

	$(function() {
		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.query(1);
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			var parentId = $("#inqu_status-0-parentId").val();
			document.getElementById("inqu-trash").click();
			$("#inqu_status-0-parentId").val(parentId);
			resultGrid.dataSource.query(1);
		});

		//新增按钮
		$("#ADD").on("click", function(e) {
			var parentId = $("#inqu_status-0-parentId").val();
			if(parentId=='') {
				parentId = 'root';
			}
			popData("", parentId, "addType");
		});

		//编辑按钮
		$("#EDIT").on("click", function(e) {
            var parentId = $("#inqu_status-0-parentId").val();
            if ( $(".check-one").is(':checked')==false){
                IPLAT.alert("请先选择一条记录进行修改");
            } else {
                if (parentId.length==null || parentId.length==0){
                    parentId=datagrid.parentId;
                }
				popData(datagrid.id, parentId, "edit");
		//		datagrid = null;
			}
		});

		//查看按钮
		$("#SEE").on("click", function(e) {
			if (datagrid == null) {
				IPLAT.alert("请先选择一条记录进行查看");
			} else {
				popData(datagrid.id, "see");
				//datagrid = null;
			}
		});
	});
</script>

