<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="保养卡片">
	<EF:EFRegion id="AUL" title="基本情况" >
		<div class="row">
		    <EF:EFInput ename="kpType" cname="类型" colWidth="2" type="hidden"/>
			<EF:EFInput ename="cardID" cname="卡片id" colWidth="3" type="hidden" readonly="true"/>
			<EF:EFInput ename="cardCode" cname="卡片代码" colWidth="4" type="text" />
			<EF:EFInput ename="cardName" cname="卡片名称" colWidth="4" type="text" required="true"/>
		</div>
		<div class="row" style="margin-top: 10px;">
		<EF:EFRegion title="备注说明">
			<EF:EFInput  colWidth="14" ratio="0:12" type="textarea"
				 ename="memo" cname=""></EF:EFInput>
		</EF:EFRegion>
		</div>
	</EF:EFRegion>
		
	<EF:EFRegion title="项目信息">
		<EF:EFGrid blockId="item" autoDraw="no" serviceName="DKKP0101">
			<EF:EFColumn ename="itemID_xmID" cname="项目ID" enable="false"  hidden="true"/>
			<EF:EFColumn ename="jitemName" align="center" cname="保养项目" enable="false" />
			<EF:EFColumn ename="itemDesc" align="center" cname="项目描述" enable="false" />
			<EF:EFColumn ename="actualValueUnit" align="center" cname="保养实际值单位" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popDataItem" url="" lazyload="true" width="50%" height="80%" title="添加项目" modal="true" />
</EF:EFPage>
<script type="text/javascript">
$(function() {
	if($("#kpType").val()==1){
	$("button").hide();
	$("input[id='cardCode']").attr("readonly","readonly");
	$("input[id='cardName']").attr("readonly","readonly")
	}else{
		$("input[id='cardCode']").attr("readonly","readonly");
		}
});
</script>