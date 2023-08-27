<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="巡查项目新增">
	<EF:EFRegion id="ADD">
		<div class="row">			
			<EF:EFInput ename="moduleId" cname="巡查项目分类id" colWidth="10" type="hidden"/>
			<EF:EFInput ename="id" cname="巡查项目主键:" colWidth="10" type="hidden"/>
			<EF:EFInput ename="code" cname="巡查项目编码:" colWidth="10" type="hidden"/>
			<EF:EFInput ename="content" cname="巡查项目名称:" colWidth="10" type="text" required="true"/>
			<EF:EFSelect ename="saftyType" cname="巡查问题分类:"
			resultId="saftyType" textField="label" valueField="value"
			serviceName="IMXM0101" methodName="saftyType" optionLabel="请选择" />
			<EF:EFInput ename="projectDesc" cname="巡查项目描述:" colWidth="10" type="text"/>
			<EF:EFInput ename="referenceValue" cname="巡查项目参考值:" colWidth="10" type="text"/>
			<EF:EFInput ename="actualValueUnit" cname="实际值单位:" colWidth="10" type="text"/>
			<EF:EFInput ename="xunjianAbnormal" cname="异常描述:" colWidth="10" type="text"/>
			<EF:EFInput ename="memo" cname="备注:" colWidth="10" type="text"/>
		</div>
	</EF:EFRegion>
	
</EF:EFPage>
	<script type="text/javascript">
	$(function () {
		$("#SUBMIT").click(function(){
			var eiInfo = new EiInfo();
			var moduleId = IPLAT.EFInput.value($("#moduleId"));
			var id = IPLAT.EFInput.value($("#id"));
			var code = IPLAT.EFInput.value($("#code"));
			var content = IPLAT.EFInput.value($("#content"));
			var projectDesc = IPLAT.EFInput.value($("#projectDesc"));
			var referenceValue = IPLAT.EFInput.value($("#referenceValue"));
			var actualValueUnit = IPLAT.EFInput.value($("#actualValueUnit"));
			var xunjianAbnormal = IPLAT.EFInput.value($("#xunjianAbnormal"));
			var memo = IPLAT.EFInput.value($("#memo"));
			var saftyType = IPLAT.EFSelect.value($("#saftyType"));
			var saftyTypeName = IPLAT.EFSelect.text($("#saftyType"));
			//判断
			if (content == null || content == "") {
				NotificationUtil("巡查项目名称不得为空", "error");
				return;
			}
			
			eiInfo.set("moduleId",moduleId);
			eiInfo.set("id",id);
			eiInfo.set("code",code);
			eiInfo.set("content",content);
			eiInfo.set("projectDesc",projectDesc);
			eiInfo.set("referenceValue",referenceValue);
			eiInfo.set("actualValueUnit",actualValueUnit);
			eiInfo.set("xunjianAbnormal",xunjianAbnormal);
			eiInfo.set("memo",memo);
			eiInfo.set("saftyType",saftyType);
			eiInfo.set("saftyTypeName",saftyTypeName);
			EiCommunicator.send("IMXM0101", "insert", eiInfo, {
				onSuccess : function(ei) {
					NotificationUtil(ei.getMsg(), "success");
					window.parent.resultGrid.dataSource.page(1);
					window.parent['popDataWindow'].close();
				}
			});
		});
	});


	</script>