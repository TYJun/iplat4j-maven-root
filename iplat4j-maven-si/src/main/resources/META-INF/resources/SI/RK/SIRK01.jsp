<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%--<title>仓库入库管理</title>--%>
<title>物资入库及退货</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-enterBillNo" cname="入库单号" />
			<EF:EFSelect ename="inqu_status-0-wareHouseNo" cname="仓库名称"
						 resultId="result" textField="wareHouseName" valueField="wareHouseNo"
						 serviceName="SIWH01" methodName="queryWareHouse" optionLabel="请选择">
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-enterType" cname="入库类别">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.si.enterType"/>
			</EF:EFSelect>
			<EF:EFSelect ename="inqu_status-0-isCheck" cname="状态">
				<EF:EFOption label="全部" value=""/>
				<EF:EFOption label="待验收" value="0"/>
				<EF:EFOption label="待审核" value="1"/>
				<EF:EFOption label="已审核" value="2"/>
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-supplierName" cname="供应商" />
			<EF:EFDatePicker ename="inqu_status-0-beginTime" cname="制单时间起" role="date" 
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="beginTime" />
			<EF:EFDatePicker ename="inqu_status-0-endTime" cname="制单时间止" role="date" 
				format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" bindId="endTime" />
			<EF:EFInput ename="mainEnterBillNo" type="hidden" value="no data"/>
			<EF:EFInput ename="inqu_status-0-billMakerName" cname="制单人" />
			<EF:EFSelect ename="inqu_status-0-printFlag" cname="是否已打印">
				<EF:EFOption label="全部" value=""/>
				<EF:EFOption label="是" value="1"/>
				<EF:EFOption label="否" value="0"/>
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库入库信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="row" autoBind="true" readonly="true" height="250">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="enterBillNo" cname="入库单号" width="150" displayType="url" align="center"/>
			<EF:EFColumn ename="enterTypeName" cname="入库类别" width="100" align="center"/>
			<EF:EFColumn ename="originBillNo" cname="来源单据号" width="100" align="center"/>
			<EF:EFColumn ename="wareHouseName" cname="仓库" width="100" align="center"/>
			<EF:EFColumn ename="supplierName" cname="供应商" width="100" align="center"/>
			<EF:EFColumn ename="totalAmount" cname="入库总金额(元)" width="100" align="center"/>
			<EF:EFColumn ename="enterTime" cname="入库时间" width="100" align="center"/>
			<EF:EFColumn ename="billMakeTime" cname="制单日期" width="100" align="center"/>
			<EF:EFColumn ename="billMakerName" cname="制单人员" width="100" align="center"/>
			<EF:EFColumn ename="isCheck" cname="状态" width="100" align="center"/>
			<EF:EFColumn ename="printFlag" cname="是否已打印" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFRegion id="detail" title="入库单明细">
		<EF:EFGrid blockId="detail" autoDraw="no" checkMode="single,row"
				   autoBind="true" readonly="true" serviceName="SIRK04" queryMethod="queryDetail"
				   sort="single">
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="200" align="center" sort="true"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" sort="true"/>
			<%--<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center"/>--%>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" sort="false"/>
			<EF:EFColumn ename="unitName" cname="物资单位" width="60" align="center" sort="false"/>
			<EF:EFColumn ename="enterNum" cname="入库数量" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="enterPrice" cname="入库单价(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="enterAmount" cname="入库总价(元)" width="100" align="center" sort="true"/>
			<EF:EFColumn ename="matTypeNum" cname="物资分类编码" hidden="true" sort="false"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类" width="150" align="center" sort="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 入库子页面 -->
<EF:EFWindow id="popData" title="入库信息" url=" " lazyload="true" width="90%" height="88%"></EF:EFWindow>
<EF:EFWindow id="popDataNew" title="入库信息" url=" " lazyload="true" width="100%" height="100%"></EF:EFWindow>
<EF:EFWindow id="excelImport" url="" lazyload="true" refresh="true" width="45%" height="60%">
	<EF:EFRegion id="enterImport" title="数据导入">
		<h4>入库导入规则：</h4>
		<li>仓库号（必填; 且必需是系统中存在的仓库）</li>
		<li>仓库名称（必填; 且必需与仓库号对应）</li>
		<li>入库类型编码（选填; 不填时值为08,填写时值为wilp.si.enterType对应小代码项的编码）</li>
		<li>入库类型名称（选填; 当入库类型编码为空时,值为:手工入库）</li>
		<li>物资编码（必填;）</li>
		<li>物资名称（必填;）</li>
		<li>物资分类编码（必填; 且必需是系统中存在的物资分类编码）</li>
		<li>物资分类名称（必填; 且必需是系统中存在的物资分类名称）</li>
		<li>计量单位（必填; 且必需是系统中wilp.ac.gm.unit对应小代码项中文名称）</li>
		<li>入库数量（必填; 且必需是最多两位小数的正数）</li>
		<li>入库单价（必填; 且必需是最多两位小数的正数）</li>
		<li>入库日期（必填; 且格式为:yyyy-MM-dd）</li>
		<br/>
		<form id="excelForm"  enctype="multipart/form-data">
			文件上传<input id="excelFile" type="file" name="excelFile" ><br />
		</form>
		<button id="download">模板下载</button>
	</EF:EFRegion>
	<div class="button-region" id="buttonDiv" style="float: right">
		<EF:EFButton cname="提交" ename="IMPORT_SUBMIT" layout="0" ></EF:EFButton>
		<EF:EFButton cname="关闭" ename="IMPORT_CLOSE" layout="0" ></EF:EFButton>
	</div>
</EF:EFWindow>
<script type="text/javascript" src="${ctx}/SI/common/si-initTime.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-excel-import.js"></script>
<script type="text/javascript" src="${ctx}/SI/common/si-keydown.js"></script>
