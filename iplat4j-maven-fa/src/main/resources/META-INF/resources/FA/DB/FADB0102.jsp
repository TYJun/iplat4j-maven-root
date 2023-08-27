<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<EF:EFPage title="资产调拨详情">
	<EF:EFRegion id="info" head="hidden">
		<div class="row">
			<EF:EFInput ename="type" cname="操作类型" colWidth="5" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-faTransferId" cname="uuid" colWidth="5" required="true" type="hidden"/>
			<EF:EFInput ename="info-0-archiveFlag" cname="归档标记" colWidth="5" required="true" type="hidden"/>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-turnDeptNum" cname="科室编码" colWidth="5" readonly="true" type="hidden"/>
			<EF:EFInput ename="info-0-turnDeptName" cname="科室名称" colWidth="5" readonly="true" type="hidden"/>
			<EF:EFAutoComplete ename="info-0-deptName" cname="接收科室" colWidth="5" ratio="4:8" required="true"
				noDataTemplate="没有数据" filter="contains" serviceName="FADA01" queryMethod="queryDept"
				resultId="dept" dataField="deptName"/>
<%--			<EF:EFPopupInput ename="info-0-deptNum" cname="接收科室"  colWidth="5" required="true"--%>
<%--							 popupTitle="科室选择" readOnly="true"--%>
<%--							 popupType="ServiceGrid" serviceName="FADA01" methodName="queryDept" resultId="dept"--%>
<%--							 valueField="deptNum" textField="deptName"--%>
<%--							 columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />--%>
		</div>
		<div class="row">
			<EF:EFInput ename="info-0-applyReason" cname="备注" rows="3" placeholder=""
						colWidth="10" type="textarea" ratio="2:10" maxLength="200"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="调拨" ename="save" layout="3"></EF:EFButton>
			<EF:EFButton cname="关闭" ename="close" layout="3"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<div title="资产信息">
		<EF:EFGrid blockId="resultFixedAssests" needAuth="false" autoDraw="no" autoBind="true" autoFit="true"
				   checkMode="multiple,row" readonly="true" rowNo="true" isFloat="true">
			<EF:EFColumn ename="faInfoId" cname="faInfoId"  align="center" locked="true" width="200" hidden="true"/>
			<EF:EFColumn ename="enterBillNo" cname="出库单号" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="matNum" cname="物资编码" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="model" cname="物资型号" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="spec" cname="物资规格" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="unitNum" cname="计量单位" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="unitName" cname="计量单位" align="center" width="200" hidden="true"/>
			<EF:EFColumn ename="price" cname="出库单价" align="center" width="200" hidden="true"/>

			<EF:EFColumn ename="goodsNum" cname="资产编码" align="center" width="200" enable="false"/>
			<EF:EFColumn ename="goodsName" cname="资产名称" align="center" width="200" enable="false"/>
			<EF:EFColumn ename="goodsClassifyName" cname="资产类别"  align="center" width="200" enable="false"/>
			<EF:EFColumn ename="goodsTypeName" cname="资产类别名称"  align="center" width="200" enable="false"/>
			<EF:EFColumn ename="statusCode" cname="资产状态"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="rfidCode" cname="RFID"  align="center" width="200" enable="false"/>
			<EF:EFColumn ename="manufacturer" cname="制造厂商"  align="center" width="200" enable="false"/>
			<EF:EFColumn ename="surpName" cname="供应商"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="buyDate" cname="购入日期"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="useDate" cname="使用日期"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="buyCost" cname="资产原值"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="useYears" cname="使用年限"   align="center" width="200" enable="false"/>
			<%--			<EF:EFColumn ename="deviceName" cname="设备名称"   align="center" />--%>
			<EF:EFColumn ename="recCreateName" cname="创建人"   align="center" width="200" enable="false"/>
			<EF:EFColumn ename="remark" cname="备注"  align="center" width="200" enable="false"/>
		</EF:EFGrid>
	</div>

	<EF:EFWindow id="popData" url="" lazyload="true" width="80%" height="80%" title="资产档案信息" modal="true" />

</EF:EFPage>