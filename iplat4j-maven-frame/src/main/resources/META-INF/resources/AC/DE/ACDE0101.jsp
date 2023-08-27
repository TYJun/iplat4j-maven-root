<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
    <EF:EFRegion id="result" title="新增科室" fitHeight="true" >
    
		<div class="row">
			<EF:EFInput ename="parentId" cname="" type="hidden" />
			<EF:EFInput ename="parentName" cname="" type="hidden" />
		   <%--  <EF:EFInput ename="deptNum" cname="科室编码" colWidth="5"   required="true"/> --%>
			<EF:EFInput ename="deptName" cname="科室名称" colWidth="4"  required="true"/>
		   	<EF:EFTreeInput ename="parentDeptName" cname="上级科室" bindId="tree01" readonly="true"
						   serviceName="ACDE01" methodName="queryTree"
						   textField="deptName" valueField="label" hasChildren="leaf"
						   root="{label: 'root',deptName: '根节点'}" popupTitile="上级分类" clear="true"
						   colWidth="4" required="true">
		   	</EF:EFTreeInput>
		   	<EF:EFInput ename="finaCode" cname="财务代码"  />
		</div>

	    <div class="row" >
			<EF:EFInput ename="erpCode" cname="HRP代码"  />
			<EF:EFSelect ename="type" cname="服务科室"  required="true">
				<EF:EFOption label="是" value="1"/>
				<EF:EFOption label="否" value="0"/>
			</EF:EFSelect>
			<EF:EFInput ename="deptDescribe" cname="科室描述" type="textarea" placeholder="不能超过200字"  />
		</div>




	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

	$(function() {

		$("#parentDeptName").val(__ei.parentId);
		$("#parentDeptName_textField").val(__ei.parentName);

		$("#SUBMIT").on("click", function (e) {
			
			var eiInfo = new EiInfo();

			var deptName = IPLAT.EFInput.value($("#deptName"));
			var parentId = $("#parentDeptName").val();
			var finaCode = IPLAT.EFInput.value($("#finaCode"));
			var erpCode = IPLAT.EFInput.value($("#erpCode"));
			var deptDescribe = IPLAT.EFInput.value($("#deptDescribe"));
			var type =  IPLAT.EFSelect.value($("#type"));
			
			//参数校验
			if(!validate(deptName,parentId)){
				return;
			}
			
			eiInfo.set("deptName",deptName);
			eiInfo.set("parentId",parentId);
			eiInfo.set("finaCode",finaCode);
			eiInfo.set("erpCode",erpCode);
			eiInfo.set("deptDescribe",deptDescribe);
			eiInfo.set("type",type);
			
			EiCommunicator.send("ACDE0101", "insert", eiInfo, {
				onSuccess : function(ei) {
					var addPopData = $("#addPopData", parent.document);
					addPopData.kendoWindow().data("kendoWindow").close();
					NotificationUtil(ei.getMsg(), "success");
					setTimeout(function() {
						window.parent.location.reload()
					}, 600);
				}
			});
		});
	

	});
	
	//参数校验
	function validate(deptName,parentId){
		if(isEmpty(deptName)){
			NotificationUtil("科室名称不能为空", "error");
			return false;
		} 
		if(isEmpty(parentId)){
			NotificationUtil("上级科室能为空", "error");
			return false;
		} 
		
		return true;
	}

	function isEmpty(parameter){
		if(parameter == undefined || parameter == null){
			return true;
		} else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
			return true;
		} else {
			return false;
		}
	}
		
</script>
