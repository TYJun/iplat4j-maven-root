<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<title>宿舍费用管理</title>
<EF:EFPage title="宿舍费用管理">
	<EF:EFRegion id="inqu" title="查询区" showClear="true">
		<div class="row">
			<EF:EFInput ename="manNo" cname="工号" />
			<EF:EFInput ename="manName" cname="姓名" />
			<EF:EFSelect ename="employmentNature" cname="员工类型" >
				<EF:EFOption label="请选择员工类型" value=""/>
				<EF:EFOption label="本院职工" value="本院职工"/>
				<EF:EFOption label="全日制学生" value="全日制学生"/>
				<EF:EFOption label="政策类研究生" value="政策类研究生"/>
				<EF:EFOption label="外协单位员工" value="外协单位员工"/>
				<EF:EFOption label="医院返聘职工" value="医院返聘职工"/>
				<EF:EFOption label="科室返聘职工" value="科室返聘职工"/>
				<EF:EFOption label="进修医生" value="进修医生"/>
				<EF:EFOption label="进修护士" value="进修护士"/>
				<EF:EFOption label="实习医生" value="实习医生"/>
				<EF:EFOption label="实习护士" value="实习护士"/>
				<EF:EFOption label="临时医技人员" value="临时医技人员"/>
				<EF:EFOption label="规范会培训生" value="规范会培训生"/>
				<EF:EFOption label="院聘临时工" value="院聘临时工"/>
				<EF:EFOption label="科聘临时工" value="科聘临时工"/>
			</EF:EFSelect>
		</div>
		<div class="row">
			<EF:EFInput ename="deptName" cname="科室部门" />
			<EF:EFInput ename="roomName" cname="宿舍总称" />
			<EF:EFDatePicker ename="currentMonth" cname="年月份"
							 type="text"  role="date" format="yyyy-MM" parseFormats="['yyyy-mm']"
							 placeholder="请选择要搜索的年月份" />
		</div>
		<div class="row">
			<div id="hiddenDiv">
				<EF:EFSelect ename="manNature" cname="人员大类" defaultValue="职工">
					<EF:EFOption label="全部" value=""/>
					<EF:EFOption label="学生" value="学生"/>
					<EF:EFOption label="职工" value="职工"/>
				</EF:EFSelect>
			</div>
		</div>

	</EF:EFRegion>
	<EF:EFRegion id="result" title="相关信息" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no" serviceName="DMFY01" checkMode="single,row" rowNo="true">
			<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true"/>
			<EF:EFColumn ename="drmId" cname="drmId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="manId" cname="manId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="roomId" cname="roomId" readonly="true" hidden="true"/>
			<EF:EFColumn ename="manNo" cname="工号" readonly="true" width="70" align="center" locked="true"/>
			<EF:EFColumn ename="manName" cname="姓名" readonly="true" width="70" align="center" locked="true"/>
			<EF:EFColumn ename="deptName" cname="科室部门" readonly="true" width="70" align="center" locked="true"/>
			<EF:EFColumn ename="roomName" cname="宿舍总称" readonly="true" width="120" align="center" locked="true"/>
			<EF:EFColumn ename="currentMonth" cname="年月份" readonly="true" width="80" align="center" locked="true"/>
			<EF:EFColumn ename="waterPriece" cname="水费(元)" readonly="true"  align="center" />
			<EF:EFColumn ename="elecPriece" cname="电费(元)" readonly="true" align="center" />
			<EF:EFColumn ename="currentRent" cname="房租(元)" readonly="true"  align="center" />
			<EF:EFColumn ename="currentManageFee" cname="管理费(元)" readonly="true"  align="center" />
			<EF:EFColumn ename="returnRent" cname="退房租(元)" readonly="true" align="center" />
			<EF:EFColumn ename="returnManageFee" cname="退管理费(元)" readonly="true"  align="center" />
			<EF:EFColumn ename="returnWaterPriece" cname="退水费(元)" readonly="true"  align="center" />
			<EF:EFColumn ename="returnElecPriece" cname="退电费(元)" readonly="true"  align="center" />
			<EF:EFColumn ename="replenishRent" cname="补房租(元)" readonly="true"  align="center" />
			<EF:EFColumn ename="replenishManageFee" cname="补管理费(元)" readonly="true"  align="center" />
			<EF:EFColumn ename="replenishWaterPriece" cname="补水费(元)" readonly="true"  align="center" />
			<EF:EFColumn ename="replenishElecPriece" cname="补电费(元)" readonly="true"  align="center" />
			<EF:EFColumn ename="extraCharges" cname="额外费用(元)" readonly="true" width="80" align="center" />
			<EF:EFColumn ename="remark" cname="备注" readonly="true" width="80" align="center" />
			<EF:EFColumn ename="currentMonth" cname="年月份" readonly="true" width="80" align="center" />
			<EF:EFColumn ename="archiveFlagMean" cname="归档状态" readonly="true"  width="80"  align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popDataADD" url=" " lazyload="true" width="40%" height="90%" title="本月费用录入" modal="true" />
	<EF:EFWindow id="popDataExtraCharges" url=" " lazyload="true" width="40%" height="40%" title="添加额外费用" modal="true" />
	<EF:EFWindow id="popDataShow" url="" lazyload="true" width="90%" height="80%" title="查看详情信息" modal="true" />
	<EF:EFWindow id="execlImport" url="" lazyload="true" refresh="true" width="40%" height="80%">
		<EF:EFRegion id="fyUpload" title="宿舍费用导入">
			<h4>宿舍费用导入规则：</h4>
			<li>工号（必填; 取值为系统中已存在有住宿信息的工号）</li>
			<li>姓名（非必填; 取值为系统中已存在有住宿信息的工号对应的姓名）</li>
			<li>水费(元)（必填; 仅需要填非负数的数值）</li>
			<li>电费(元)（必填; 仅需要填非负数的数值）</li>
			<li>是否已退宿（非必填; 是/否）</li>
			<li>备注（非必填; 对该员工费用内容进行备注）</li>
			<li>退房租(元)（非必填; 有填写时仅需要填非负数的数值）</li>
			<li>退管理费(元)（非必填; 有填写时仅需要填非负数的数值）</li>
			<li>退水费(元)（非必填; 有填写时仅需要填非负数的数值）</li>
			<li>退电费(元)（非必填; 有填写时仅需要填非负数的数值）</li>
			<li>补房租(元)（非必填; 有填写时仅需要填非负数的数值）</li>
			<li>补管理费(元)（非必填; 有填写时仅需要填非负数的数值）</li>
			<li>补水费(元)（非必填; 有填写时仅需要填非负数的数值）</li>
			<li>补电费(元)（非必填; 有填写时仅需要填非负数的数值）</li>
			<li>其他费用(元)（非必填; 有填写时仅需要填非负数的数值）</li>
			<br/>
			<form id="excelForm"  enctype="multipart/form-data">
				文件上传<input id="excelFile" type="file" name="file" ><br />
			</form>
			<button id="download">模板下载</button>
		</EF:EFRegion>
		<div class="button-region" id="buttonDiv" style="float: right">
			<EF:EFButton cname="提交" ename="IMPORTSUBMIT" layout="0" ></EF:EFButton>
			<EF:EFButton cname="关闭" ename="IMPORTCLOSE" layout="0" ></EF:EFButton>
		</div>
	</EF:EFWindow>
</EF:EFPage>