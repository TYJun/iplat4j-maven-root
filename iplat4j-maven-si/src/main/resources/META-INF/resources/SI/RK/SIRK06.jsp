<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<%@ page import="com.baosight.iplat4j.core.web.threadlocal.UserSession" %>
<script>
	let loginName = '<%=UserSession.getLoginCName()%>'
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--<title>入库组合页面</title>--%>
<title>入库组合查询</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		 <div class="row">
             <EF:EFSelect ename="wareHouseName" cname="仓库名称" optionLabel="请选择"
						  filter="contains" ratio="3:8">
                 <EF:EFOptions blockId="wareHouse" textField="wareHouseName" valueField="wareHouseName"/>
             </EF:EFSelect>
			 <EF:EFSelect ename="supplierName" cname="供应商" filter="contains"
						  optionLabel="请选择" ratio="3:8">
				 <EF:EFOptions blockId="supplier" textField="supplierName" valueField="supplierName" />
			 </EF:EFSelect>
			 <div class="col-md-4">
				 <div class="form-group">
					 <label class="col-xs-3 control-label">
						 物资分类名称
					 </label>
					 <div class="col-xs-8" >
						 <input id="matTypeName" name="matTypeName" placeholder="物资分类" onclick="showAll('matTypeName')">
					 </div>
				 </div>
			 </div>
			<EF:EFDatePicker ename="beginTime" cname="开始时间" role="date" ratio="3:8"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="endTime" cname="截止时间" role="date" ratio="3:8"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" />
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="入库明细">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="multiple,row"
			autoBind="true" readonly="true" sort="single">
			<EF:EFColumn ename="enterBillDetailNo" cname="入库明细号" hidden="true" />
			<EF:EFColumn ename="matNum" cname="物资代码" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="200" align="center" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
			<EF:EFColumn ename="unitName" cname="物资单位" width="60" align="center" sort="false"/>
			<EF:EFColumn ename="enterNum" cname="入库数量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="enterPrice" cname="入库单价(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="enterAmount" cname="入库总价(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="recCreateTime" cname="入库时间" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="150" align="center" sort="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>