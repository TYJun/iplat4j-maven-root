<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
<div class="row">
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="角色名称" fitHeight="true">
			<EF:EFTree id="tree01" textField="groupCname" valueField="groupId"
				hasChildren="leaf" serviceName="AURR01" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	
	</div>
	<div class="col-md-9">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
				<EF:EFInput ename="resourceEname" cname="资源英文名" />
				<EF:EFInput ename="resourceName" cname="资源中文名" />
				<EF:EFInput ename="roleId" cname="" type="hidden" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="资源列表">
			<EF:EFGrid blockId="result" autoDraw="no"  readonly="true">
				<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
				<EF:EFColumn ename="resourceEname" cname="资源英文名" width="100" />
				<EF:EFColumn ename="resourceName" cname="资源中文名" width="100" />
				<EF:EFComboColumn ename="type" cname="类型" >
					<EF:EFCodeOption codeName="wilp.au.ar.type" valueField="value" textField="label"></EF:EFCodeOption>
				</EF:EFComboColumn>
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	</div>
</EF:EFPage>

<EF:EFWindow id="addPopData" url=" " lazyload="true" width="70%" height="80%"></EF:EFWindow>

<script type="text/javascript">
	var datagrid = null;
	
	//EFGrid单击事件，获取选中行数据（定义全部变量）
	IPLATUI.EFGrid = {
		"result" : {
			onCheckRow : function(e) {
				if (!e.fake) {
					datagrid = null;
					var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
					datagrid = model;
				}
			}
		}
	}
	
	

	$(function() {

		//查询
		$("#QUERY").on("click", function(e) {
			resultGrid.dataSource.query(1);
		});

		//重置按钮
		$("#REQUERY").on("click", function(e) {
			document.getElementById("inqu-trash").click();
			resultGrid.dataSource.query(1);
		});
		
		//绑定角色按钮
		$("#BINDINGRESOURCE").on("click", function (e) {
			
			if($("#roleId").val() == null || $("#roleId").val() == ""){
				IPLAT.alert("请先勾选左侧角色");
				return;
			}
			
    		var url = IPLATUI.CONTEXT_PATH + "/web/AURR0101?roleId="+$("#roleId").val();
        	var addPopData = $("#addPopData");
        	addPopData.data("kendoWindow").setOptions({
        		open : function() {
        			addPopData.data("kendoWindow").refresh({
        				url : url
        			});
        		}
        	});
        	// 打开弹窗
        	addPopDataWindow.open().center();
        });

		
		//解除角色按钮
		$("#RELIEVERESOURCE").on("click", function(e) {
			
			if($("#roleId").val() == null || $("#roleId").val() == ""){
				IPLAT.alert("请先勾选左侧角色");
				return;
			}
		
			var checkRows = resultGrid.getCheckedRows();
			console.log(checkRows);
			if(checkRows.length<1){
				IPLAT.alert("请选择要解除资源的行");
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}

			var info=new EiInfo();
			info.set("list",arr);
			info.set("roleId",$("#roleId").val());

			IPLAT.confirm({
				message:"确定解除资源?",
				okFn:function(e){
					EiCommunicator.send("AURR01", "relieveResource", info, {
						onSuccess : function(ei) {
							IPLAT.alert(ei.getMsg());
							resultGrid.dataSource.page(1);
						}
					});
				}
			});
			
		});
		
		

	});
	


	
	
</script>

