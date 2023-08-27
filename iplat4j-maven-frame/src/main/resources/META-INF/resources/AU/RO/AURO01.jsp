<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
<div class="row">
	
	<div class="col-md-12">
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
			    <EF:EFInput ename="roleNum" cname="角色编码" />
				<EF:EFInput ename="roleName" cname="角色名称" />
			</div>
		</EF:EFRegion>
		<EF:EFRegion id="result" title="角色列表">
			<EF:EFGrid blockId="result" autoDraw="no"  readonly="true">
				<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" />
				<EF:EFColumn ename="roleNum" cname="角色编码" width="100" />
				<EF:EFColumn ename="roleName" cname="角色名称" width="100" />
			<%-- 	<EF:EFColumn ename="type" cname="角色类型" width="100" /> --%>
				<EF:EFColumn ename="remark" cname="备注" width="100" />
			</EF:EFGrid>
		</EF:EFRegion>
	</div>
	</div>
</EF:EFPage>

<EF:EFWindow id="addPopData" url=" " lazyload="true" width="50%" height="50%"></EF:EFWindow>
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
    		var url = IPLATUI.CONTEXT_PATH + "/web/AURO0101";
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

		
		//修改按钮
		$("#EDIT").on("click", function(e) {
			if (datagrid == null) {
				IPLAT.alert("请先选择一条记录进行修改");
			}else {
				var url = IPLATUI.CONTEXT_PATH + "/web/AURO0102?"+"id=" + datagrid.id+
						"&roleName="+datagrid.roleName+"&remark="+datagrid.remark;
				var deitPopData = $("#deitPopData");
				deitPopData.data("kendoWindow").setOptions({
					open : function() {
						deitPopData.data("kendoWindow").refresh({
							url : url
						});
					}
				});
				deitPopDataWindow.open().center();
			}
		});
		
		
		
		//删除按钮
		$("#DELETE").on("click", function(e) {
		
			var checkRows = resultGrid.getCheckedRows();
			
			if(checkRows.length<1){
				IPLAT.alert("请选择要删除的行");
				return;
			}
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var info=new EiInfo();
			info.set("list",arr);
			
			IPLAT.confirm({
				message:"确定删除选中行?",
				okFn:function(e){
					EiCommunicator.send("AURO01", "delete", info, {
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

