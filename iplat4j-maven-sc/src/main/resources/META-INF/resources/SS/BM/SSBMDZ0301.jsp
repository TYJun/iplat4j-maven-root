<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
		<EF:EFSelect ename="groupNum" cname="分组" colWidth="4" filter="contains">
			<EF:EFOption label="第一组" value="第一组"/>
			<EF:EFOption label="第二组" value="第二组"/>
			<EF:EFOption label="第三组" value="第三组"/>
			<EF:EFOption label="第四组" value="第四组"/>
			<EF:EFOption label="第五组" value="第五组"/>
			<EF:EFOption label="第六组" value="第六组"/>
			<EF:EFOption label="第七组" value="第七组"/>
		</EF:EFSelect>
		<EF:EFSelect ename="deptNum" cname="科室"
					 resultId="dept" textField="deptName" valueField="deptNum"
					 serviceName="SSBMDZ03" methodName="queryDeptName" optionLabel="请选择"
					 colWidth="4" filter="contains">
		</EF:EFSelect>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
	$(function() {
		$("#SUMBMIT").click(function() {
			var node = $('#SSBMDZ0301');
			var groupNum = IPLAT.EFSelect.value($("#groupNum"));
			var groupName = IPLAT.EFSelect.text($("#groupNum"));
			var deptName = IPLAT.EFSelect.text($("#deptNum"));
			var deptNum = IPLAT.EFSelect.value($("#deptNum"));

			var inInfo = new EiInfo();
			inInfo.set("groupNum", groupNum);
			inInfo.set("groupName", groupName);
			inInfo.set("deptName", deptName);
			inInfo.set("deptNum", deptNum);
			console.log(groupName)
			if (groupName == null || groupName == "") {
				IPLAT.alert("请分组")
			} else if(deptName == null || deptName == ""){
				IPLAT.alert("请选科室")
			}else {
				EiCommunicator.send("SSBMDZ0301", "insert", inInfo, {
					onSuccess : function(ei) {
						NotificationUtil("新增成功", "");
						window.parent.resultGrid.dataSource.page(1);
						//关闭弹窗
						window.parent['popDataWindow'].close();
					}
				});
			}
		});
	});
</script>

