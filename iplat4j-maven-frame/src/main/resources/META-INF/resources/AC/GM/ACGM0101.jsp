<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage >
    <EF:EFRegion id="result" title="新增物资分类" fitHeight="true" >
    
		<div class="row">
			<EF:EFInput ename="matClassName" cname="物资分类名称" colWidth="4"  required="true" maxLength="100"/>
		   	<EF:EFTreeInput ename="parentMatClassName" cname="上级物资分类" bindId="tree01" readonly="true"
						   serviceName="ACGM01" methodName="queryTree"
						   textField="matClassName" valueField="label" hasChildren="leaf"
						   root="{label: 'root',matClassName: '根节点'}" popupTitile="上级物资分类" clear="true"
						   colWidth="4" required="true">
		   	</EF:EFTreeInput>
		</div>

	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">

	$(function() {
		// 物资分类回显
		$("#parentMatClassName").val(__ei.matClassId);
		$("#parentMatClassName_textField").val(__ei.matClassName);
		var validator = IPLAT.Validator({
			id: "result"
		});

		$("#SUBMIT").on("click", function (e) {
			//参数校验
			if(!validator.validate()){
				return;
			}
			var eiInfo = new EiInfo();
			eiInfo.setByNode("result");

			EiCommunicator.send("ACGM0101", "insert", eiInfo, {
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
</script>
