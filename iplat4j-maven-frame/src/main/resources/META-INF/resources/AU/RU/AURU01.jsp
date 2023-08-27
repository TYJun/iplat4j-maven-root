<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
<div class="row">
	<div class="col-md-3">
		<EF:EFRegion id="R1" title="角色名称" fitHeight="true">
			<EF:EFTree id="tree01" textField="roleName" valueField="label"
				hasChildren="leaf" serviceName="AURU01" methodName="queryTree">
			</EF:EFTree>
		</EF:EFRegion>
	</div>
	<div class="col-md-9">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
		     	<EF:EFInput ename="workNo" cname="工号" />
				<EF:EFInput ename="name" cname="姓名" />
				<EF:EFInput ename="deptName" cname="所属科室" />
				<EF:EFInput ename="roleNameHidden" cname="" type="hidden" />
				<EF:EFInput ename="roleIdHidden" cname="" type="hidden" />
				<EF:EFInput ename="roleId" cname="" type="hidden" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="人员列表">
			<EF:EFGrid blockId="result" autoDraw="no"  readonly="true">
				<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
				<EF:EFColumn ename="workNo" cname="工号" width="70" />
				<EF:EFColumn ename="name" cname="姓名" width="100" />
				<EF:EFColumn ename="gender" cname="性别" width="50" />
				<EF:EFColumn ename="deptName" cname="所属科室" width="100" />
				<EF:EFColumn ename="contactTel" cname="联系电话" width="100" />
				<EF:EFColumn ename="staffType" cname="员工类型" width="100" />
				<EF:EFColumn ename="isService" cname="服务人员" width="100" />
				<EF:EFColumn ename="isStatus" cname="状态" width="100" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	</div>
</EF:EFPage>

<EF:EFWindow id="addPopData" url=" " lazyload="true" width="80%" height="80%"></EF:EFWindow>
<EF:EFWindow id="deitPopData" url=" " lazyload="true" width="60%" height="53%"></EF:EFWindow>

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
		
		//新增按钮
		$("#ADD").on("click", function (e) {
			
			// 判断是否选中了左侧角色
			if($("#roleIdHidden").val() == null || $("#roleIdHidden").val() == '' ){
				NotificationUtil("请先选择左侧角色", "error");
				return;
			}
			
			
    		var url = IPLATUI.CONTEXT_PATH + "/web/AURU0101?roleId="+$("#roleIdHidden").val()+
    				"&roleName="+$("#roleNameHidden").val();
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

		
		
		
		//删除按钮
		$("#DELETE").on("click", function(e) {
			// 判断是否选中了左侧角色
			if($("#roleIdHidden").val() == null || $("#roleIdHidden").val() == '' ){
				NotificationUtil("请先选择左侧角色", "error");
				return;
			}
			var checkRows = resultGrid.getCheckedRows();
			
			if(checkRows.length<1){
				NotificationUtil("请选择要删除人员", "error");
				
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);
			info.set("roleId",$("#roleIdHidden").val());
			IPLAT.confirm({
				message:"确定删除选中行?",
				okFn:function(e){
					EiCommunicator.send("AURU01", "delete", info, {
						onSuccess : function(ei) {
							NotificationUtil(ei.getMsg(), "success");
							$("#roleId").val($("#roleIdHidden").val());
							resultGrid.dataSource.page(1);
							$("#roleId").attr("value", "");
							
						}
					});
				}
			});
			
		});

		//查看按钮
		$("#SEE").on("click", function(e) {
			if (datagrid == null ) {
				NotificationUtil("请先选择一条记录进行查看", "error");
			} else {
				var url = IPLATUI.CONTEXT_PATH + "/web/AURU0102?perId="+datagrid.id+
						"&perWorkNo="+datagrid.workNo+"&perName="+datagrid.name;
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
			}
		});
	});

</script>

