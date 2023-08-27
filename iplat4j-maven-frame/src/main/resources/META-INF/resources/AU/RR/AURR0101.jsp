<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
   
		<div class="row">
			<div class="col-md-12">
				<EF:EFRegion id="inqu" title="查询条件" showClear="true">
					<div class="row">
					    <EF:EFInput ename="resourceEname" cname="资源英文名" />
						<EF:EFInput ename="resourceName" cname="资源中文名" />
						<EF:EFInput ename="roleId" cname="角色ID"  type="hidden" />
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

<script type="text/javascript">

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
		
		$("#SUBMIT").on("click", function (e) {
			
			var checkRows = resultGrid.getCheckedRows();
			
			if(checkRows.length<1){
				IPLAT.alert("请选择要绑定的资源");
				return;
			}
			
			var arr=[];
			for(var i=0;i<checkRows.length;i++){
				arr[i]=checkRows[i].id;
			}
			var eiInfo=new EiInfo();
			eiInfo.set("list",arr);
			eiInfo.set("roleId",$("#roleId").val());
			
			EiCommunicator.send("AURR0101", "bindingResource", eiInfo, {
				onSuccess : function(ei) {
					var addPopData = $("#addPopData", parent.document);
					addPopData.kendoWindow().data("kendoWindow").close();
					NotificationUtil(ei.getMsg(), "success");
					window.parent['addPopDataWindow'].close();
					setTimeout(function() {
						window.parent.resultGrid.dataSource.page(1);
					}, 600);
					
				}
			});
		});
		
		
		
	

	});
	

</script>
