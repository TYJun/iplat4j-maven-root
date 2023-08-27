<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>库存盘库</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称："
				resultId="result" serviceName="CIWH01" methodName="queryWareHouse"
				textField="wareHouseName" valueField="wareHouseNo" 
				optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-invenStatus" cname="状态">
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="新建" value="0" />
				<EF:EFOption label="待审核" value="1" />
				<EF:EFOption label="已审核" value="2" />
			</EF:EFSelect>
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="生成日期起:"
				role="date" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="生成日期止:"
				role="date" format="yyyy-MM-dd"
				parseFormats="['yyyy-MM-dd']" bindId="endTime" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="库存盘库信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" 
			autoBind="true" readonly="true">
			<EF:EFColumn ename="invenBillNo" cname="盘点单号" width="100" displayType="url" align="center"/>
			<EF:EFColumn ename="wareHouseNo" cname="仓库号" width="100" align="center"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="150" align="center"/>
			<EF:EFComboColumn ename="invenStatus" cname="状态" width="80" align="center">
				<EF:EFOption label="新建" value="0" />
				<EF:EFOption label="待审核" value="1" />
				<EF:EFOption label="已审核" value="2" />
			</EF:EFComboColumn>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" width="100" align="center"/>
			<EF:EFColumn ename="billMakerName" cname="制单人员" width="100" align="center"/>
			<EF:EFColumn ename="billCheckTime" cname="审核日期" width="100" align="center"/>
			<EF:EFColumn ename="billCheckerName" cname="审核人员" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<!-- 盘库明细弹窗 -->
	<EF:EFWindow id="popData" title="盘库信息" url=" " lazyload="true" width="90%" height="80%"></EF:EFWindow>
</EF:EFPage>