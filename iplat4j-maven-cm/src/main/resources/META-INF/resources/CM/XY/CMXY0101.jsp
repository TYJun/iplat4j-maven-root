<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="false">
	    <EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden" />
		<EF:EFInput ename="inqu_status-0-payTypeName" cname="合同协议名称"
			colWidth="7" ratio="4:8" type="text" required="true" />
		<EF:EFInput ename="inqu_status-0-payTypeNum" cname="付款协议编码" colWidth="7" ratio="4:8" type="hidden"/>
		<EF:EFInput ename="inqu_status-0-remark" cname="备注"  colWidth="7"
			ratio="4:8" type="textarea" />
			<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="SAVEPR" layout="0"></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<!--多页面显示-->
	<EF:EFTab id="tab_tab_grid" >
		<div title="付款协议明细">
			<EF:EFGrid blockId="resultA" needAuth="false" autoDraw="no" queryMethod="queryPay"
			isFloat="true" copyToAdd="false" checkMode="single,row" checkNewLine="false" rowNo="true" >
			    <EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="lastTime" cname="付款周期(月)" width="100" data-regex="/^[1-9]\d*|0$/" data-errorprompt="请输入数字" align="center"/>
				<EF:EFColumn ename="payRate" cname="付款比例(%)" width="100" data-regex="/^(?:0|[1-9][0-9]?|100)$/" data-errorprompt="请输入 0-100 之间的数字" align="center"/>
				<EF:EFColumn ename="remark" cname="备注" width="100" align="center"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
</EF:EFPage>
