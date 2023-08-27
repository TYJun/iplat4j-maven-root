<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="inqu" title="项目科室选择" showClear="true">
		<div class="row">
			<EF:EFPopupInput ename="inqu_status-0-projectObjectDeptNum" cname="项目科室" 
				popupTitle="科室选择" ratio="2:10" required="true" 
				popupType="ServiceGrid" serviceName="PM0101" methodName="queryDept" resultId="dept"
				valueField="deptNum" textField="deptName"
				columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
		</div>
	</EF:EFRegion>
	
	<!-- 新增弹出窗口 -->
	<EF:EFWindow id="popDataAdd" url="" lazyload="true" width="120%" height="120%"></EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
	$(function() {
		//确认
		$("#SURE").on("click", function() {
			var projectObjectDeptNum = $("[name = 'inqu_status-0-projectObjectDeptNum']").val();
			var deptName = $("#inqu_status-0-projectObjectDeptNum_textField").val()
			if(projectObjectDeptNum == ''){
				IPLAT.NotificationUtil("请选择项目科室","error")
			} else {
				var data = {'projectObjectDeptNum':projectObjectDeptNum,'projectObjectDeptName':deptName};
				window.parent.windowReturn("PM0101",data);
				closeCurrentWindow();
			}
		});
		
		//取消
		$("#CANCEL").on("click", function() {
			closeCurrentWindow()
		});
	})
	
	//关闭窗口
	function closeCurrentWindow() {
		window.parent['projectObjectDeptWindow'].close();
	}
	
</script>

