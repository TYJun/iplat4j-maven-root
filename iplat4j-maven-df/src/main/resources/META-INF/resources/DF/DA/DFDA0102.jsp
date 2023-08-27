<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="">
	<EF:EFRegion id="dfda" title="建档信息">
		<div class="row">

			<EF:EFInput ename="machineName" cname="设备名称" colWidth="4" ratio="3:8" required="true"/>

			<EF:EFTreeInput ename="machineTypeId" cname="设备分类"
							serviceName="DFFL10" methodName="queryDFFLTree" textField="classifyName"
							valueField="id" hasChildren="isLeaf" popupTitle="设备分类"
							root="{id: 'root',classifyName: '目录结构'}" clear="true" readonly="true"
							colWidth="4" ratio="3:8" required="true">
			</EF:EFTreeInput>
			<EF:EFInput ename="manufacturerName" cname="生产单位" colWidth="4" ratio="3:8" required="true"/>
				<%-- <EF:EFPopupInput ename="machineName" cname="设备名称：" colWidth="4" ratio="3:8"
                    popupTitle="设备名称" required="true" placeholder="请选择设备"
                    popupType="ServiceGrid" serviceName="DFFL10" methodName="queryDevice" resultId="device"
                    valueField="paramKey" textField="paramName"
                    columnEnames="classifyName,paramKey,paramName" columnCnames="设备系统分类,设备参数编号,设备参数名称" /> --%>

		</div>
		<div class="row">
			<EF:EFPopupInput ename="fixedPlace" cname="安装地方" colWidth="4" ratio="3:8" readonly="true" popupWidth = "550"
							 popupType="ServiceGrid" popupTitle="安装地方" required="true" serviceName="DFDA01" methodName="querySpot" resultId="spot" autofit="true"
							 valueField="spotNum" textField="spotName" backFillFieldIds="spotName" backFillColumnIds="spotName" columnEnames="building,floor,spotName" columnCnames="楼,层,地点"/>
			<EF:EFInput ename="building" colWidth="4" ratio="3:8" cname="楼"  center="true">
				<%-- <EF:EFCodeOption codeName="yyhq.df.dl.building" textField="label" valueField="value"/> --%>
			</EF:EFInput>
			<EF:EFInput ename="floor" colWidth="4" ratio="3:8" cname="层" >
				<%-- <EF:EFCodeOption codeName="yyhq.df.dl.floor" textField="label" valueField="value"/> --%>
			</EF:EFInput>
			<EF:EFInput ename="spotId" type="hidden" cname="地点id" />
			<EF:EFInput ename="spotCode" type="hidden" cname="地点编码" />
			<EF:EFInput ename="spotName" type="hidden" cname="地点名称" />
		</div>

		<div class="row">
			<EF:EFInput ename="supplierName" cname="供应商名称" colWidth="4" ratio="3:8" required="true"/>
			<EF:EFInput ename="supplierId" cname="供应商id" colWidth="4" ratio="3:8" type="hidden"/>
			<EF:EFInput ename="maintUnit" cname="保养单位" colWidth="4" ratio="3:8" required="true"/>
			<EF:EFPopupInput ename="reqDeptName" cname="管理科室"  colWidth="4" ratio="3:8"
							 popupTitle="科室选择" required="true" readonly="true"
							 popupType="ServiceGrid" serviceName="DFDA01" methodName="queryDept" resultId="dept"
							 valueField="deptNum" textField="deptName"
							 columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />

		</div>
		<div class="row">
			<EF:EFPopupInput ename="useDeaprtId" cname="使用科室"  colWidth="4" ratio="3:8"
							 popupTitle="科室选择" required="true"
							 popupType="ServiceGrid" serviceName="DFDA01" methodName="queryDept" resultId="dept"
							 valueField="deptNum" textField="deptName" readonly="true"
							 columnEnames="deptNum,deptName" columnCnames="科室编码,科室名称" />
			<EF:EFDatePicker ename="fixedTime" cname="安装日期" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" readonly="true"/>
			<EF:EFDatePicker ename="useTime" cname="使用日期" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="machineFolderId" cname="档案盒号" colWidth="4" ratio="3:8"/>
			<EF:EFSelect ename="status" colWidth="4" ratio="3:8" cname="设备状态" >
				<EF:EFOption label="正常" value="正常"/>
				<EF:EFOption label="启用" value="启用"/>
				<EF:EFOption label="停用" value="停用"/>
				<EF:EFOption label="报废" value="报废"/>
				<EF:EFOption label="异常" value="异常"/>
			</EF:EFSelect>
			<EF:EFSelect ename="needScan" cname="是否扫二维码" colWidth="4"
						 ratio="3:8" textField="label" valueField="value">
				<EF:EFOption label="否" value="否"/>
				<EF:EFOption label="是" value="是"/>
			</EF:EFSelect>
		</div>
		<div class="row">
				<%--			<EF:EFDatePicker ename="lastMaintainTime" cname="上次保养日期" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"--%>
				<%--			parseFormats="['yyyy-mm-dd']" readonly="true"/>--%>
		</div>
		<div class="row">
				<%-- <EF:EFInput ename="managerManId" cname="管理员：" colWidth="4" ratio="3:8" readonly="true"/> --%>
			<EF:EFPopupInput ename="managerManId" cname="管理员"   colWidth="4" ratio="3:8"
							 popupTitle="管理员选择" required="false" readonly="true"
							 popupType="ServiceGrid" serviceName="DFDA01" methodName="queryPerson" resultId="person"
							 valueField="workNo" textField="name"
							 columnEnames="workNo,name" columnCnames="人员工号,人员姓名" />

			<EF:EFInput ename="memo" cname="备注"  type="textarea" colWidth="4" ratio="3:8"/>
				<%-- <EF:EFInput ename="managerDepartId" cname="管理科室：" colWidth="4" ratio="3:8" readonly="true"/> --%>
		</div>
		<div class="row">
				<%--			<EF:EFInput ename="matNum" cname="物资编码" colWidth="4" ratio="3:8" />--%>
				<%--			<EF:EFInput ename="matName" cname="物资名称" colWidth="4" ratio="3:8" />--%>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id = "ibin" title="入库信息">
		<div class="row">
			<EF:EFInput ename="machineCode" cname="设备编码" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="models" cname="规格型号" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="energyForm" cname="能源形式" colWidth="4" ratio="3:8"/>
		</div>
		<div class="row">
			<EF:EFInput ename="makerBrand" cname="品牌" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="outFactoryNo" cname="出厂编号" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="factoryTel" cname="出厂联系方式" colWidth="4" ratio="3:8"/>
		</div>
		<div class="row">
			<EF:EFInput ename="buyMode" cname="购置方式" colWidth="4" ratio="3:8"/>
			<EF:EFDatePicker ename="buyTime" cname="购买日期" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" readonly="true"/>
			<EF:EFInput ename="assetPrice" cname="资产价格(元)" data-regex="/^\d*\.?\d+$/" data-errorprompt="请输入正数字" colWidth="4" ratio="3:8"/>
		</div>
		<div class="row">
			<EF:EFInput ename="useFor" cname="用途" colWidth="4" ratio="3:8"/>
			<EF:EFDatePicker ename="inStorageTime" cname="入库时间" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" readonly="true"/>
			<EF:EFInput ename="useLimit" cname="使用年限" colWidth="4" ratio="3:8"/>
		</div>
		<div class="row">
			<EF:EFSelect ename="maintainRound" colWidth="4" ratio="3:8" cname="保养周期" >
				<EF:EFOption label="每天" value="每天"/>
				<EF:EFOption label="每周" value="每周"/>
				<EF:EFOption label="每半月" value="每半月"/>
				<EF:EFOption label="每月" value="每月"/>
				<EF:EFOption label="每季" value="每季"/>
				<EF:EFOption label="每半年" value="每半年"/>
				<EF:EFOption label="每年" value="每年"/>
			</EF:EFSelect>
			<EF:EFDatePicker ename="warrantyDate" cname="质保到期日" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" readonly="true"/>
		</div>

	</EF:EFRegion>
	<EF:EFRegion id = "obin" title="出库信息">
		<div class="row">
			<EF:EFInput ename="goodsCode" cname="固定资产编码" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="goodsName" cname="固定资产名称" colWidth="4" ratio="3:8"/>
			<EF:EFInput ename="goodsNo" cname="固定资产条码" colWidth="4" ratio="3:8"/>
		</div>
		<div class="row">
			<EF:EFInput ename="assetBelongs" cname="资产所属" colWidth="4" ratio="3:8"/>
			<EF:EFDatePicker ename="outStorageTime" cname="出库时间" role="date" colWidth="4" ratio="3:8" format="yyyy-MM-dd"
							 parseFormats="['yyyy-mm-dd']" readonly="true"/>
		</div>
	</EF:EFRegion>
	<EF:EFRegion title="参数信息">
		<EF:EFGrid blockId="result" fitHeight="true" autoDraw="no" serviceName="DFDA0102" queryMethod="edit">
			<EF:EFColumn ename="id" cname="主键" hidden="true"/>
			<EF:EFColumn ename="paramKey" cname="参数编码" enable="false" align="center"/>
			<EF:EFColumn ename="paramName" cname="参数名称" enable="false" align="center"/>
			<EF:EFColumn ename="paramValue" cname="参数值" enable="false" align="center"/>
	 		<EF:EFColumn ename="paramUnit" cname="参数单位" enable="false" align="center"/>
			<EF:EFColumn ename="memo" cname="备注" enable="false" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>