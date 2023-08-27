<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="R1" style="width:100%" title="分类名称" fitHeight="true">
		<EF:EFTree id="tree01" textField="text" valueField="label"
				   hasChildren="leaf" serviceName="MSPL02" methodName="queryTree">
		</EF:EFTree>
	</EF:EFRegion>
	<EF:EFInput ename="id" cname="" width="1" />
	<EF:EFInput ename="parentId" cname="" width="1" />
</EF:EFPage>

<script type="text/javascript">

	$(function() {
		$("#id").css("display", "none");
		$("#SUBMIT").on("click",function (e) {
			var node = $('#MSPL0103');
			var id = $("#id").val();
			var parentId = $("#parentId").val();

			if(parentId=='' || parentId=='root') {
				IPLAT.alert("请选择树状菜单并且不能点位参数后再提交");
				return false;
			}
			IPLAT.submitNode(node, 'MSPL01', 'updateParameClassifyId', {
				onSuccess : function(eiInfo) {
					NotificationUtil("移动成功", "");
					//关闭弹窗
					window.parent.resultGrid.dataSource.page(1);
					window.parent['popDataWindow'].close();


				},
				onFail : function(errorMsg, status, e) {
					NotificationUtil("移动失败，原因[" + errorMsg + "]", "error");
				}
			});
		})
	});
</script>

