<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="登记" fitHeight="false">
		<div class="row">
			<EF:EFInput ename="type" cname="操作类型" colWidth="5" ratio="4:8" type="hidden"/>
			<EF:EFInput ename="bidId" cname="招标主键" colWidth="5" ratio="4:8" type="hidden"/>
			<EF:EFInput ename="bidNo" cname="招标号" colWidth="5" ratio="4:8"/>
			<EF:EFInput ename="bidName" cname="招标名称" colWidth="5" ratio="4:8"/>
			<EF:EFPopupInput ename="tenderee" cname="招标负责人" readonly="true"
				popupTitle="员工列表" popupType="ServiceGrid" serviceName="CMDJ0101"
				methodName="queryAdmin" resultId="contAdmin" valueField="workNo"
				textField="name" colWidth="5" ratio="4:8"
				columnEnames="workNo,name" columnCnames="员工工号,员工姓名"/>
			<EF:EFInput ename="budget" cname="预算费用(元)" colWidth="5" ratio="4:8" maxLength="10"
						data-regex="/^[1-9]\d*|0$/" data-errorprompt="请输入数字" format="{0:c2}"/>
			<EF:EFPopupInput ename="undertakeDeptName" cname="相关科室"
				popupTitle="相关科室" popupType="ServiceGrid" serviceName="CMDJ0101"
				methodName="queryContCostNum" resultId="contDept" readonly="true"
				valueField="contDeptNum" textField="contDeptName" colWidth="5"
				ratio="4:8" columnEnames="contDeptNum,contDeptName"
				columnCnames="科室编码,科室名称"/>
			<EF:EFInput ename="bidPrice" cname="中标价(元)" colWidth="5" ratio="4:8" maxLength="10"
						data-regex="/^[1-9]\d*|0$/" data-errorprompt="请输入数字" format="{0:c2}"/>
			<EF:EFPopupInput ename="bidWinner" cname="中标单位"
				popupTitle="中标单位选择" popupType="ServiceGrid" serviceName="CMDJ0101"
				methodName="querySupplier" resultId="supplier" readonly="true"
				valueField="surpNum" textField="surpName" colWidth="5"
				ratio="4:8" columnEnames="surpNum,supplierName"
				columnCnames="供应商编码,供应商名称"/>
			<EF:EFInput ename="bidContent" cname="招标内容" colWidth="5" ratio="4:8"/>
			<EF:EFInput ename="bidRemark" cname="招标备注" colWidth="5" ratio="4:8"/>
		</div>
		<div class="button-region" id="buttonDiv" style="float: right">
			<EF:EFButton cname="确认" ename="CONFIRM" layout="0" ></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0" ></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="tab-tab_grid" active="0">
		<div title="参标单位">
			<EF:EFGrid blockId="resultA" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
					   checkMode="row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryResultA">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" enable="false" align="center"/>
				<EF:EFColumn ename="surpNum" cname="参标单位编码" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="surpName" cname="参标单位名称" width="100" enable="false" align="center"/>
			</EF:EFGrid>
		</div>
		<div title="项目附件">
			<EF:EFGrid blockId="resultB" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
					   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryResultB">
				<EF:EFColumn ename="uploadId " cname="id" width="100"  enable="false" align="center" hidden="true"/>
				<EF:EFColumn ename="filePath " cname="文件路径" width="100"  enable="false" align="center" hidden="true"/>
				<EF:EFColumn ename="fileName" cname="文件名" width="100" enable="false" align="center"/>
				<EF:EFColumn ename="fileRemark" cname="说明" width="100" enable="true" align="center"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
	<EF:EFWindow id="supplierChoose" url="" lazyload="true" width="80%" height="90%" title="供应商选择"/>
	<EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%" title="附件上传">
		<EF:EFRegion id="upload" title="文件上传">
			<EF:EFUpload ename="bidFile" docTag="bid" path="contract/bid" readonly="false"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>

