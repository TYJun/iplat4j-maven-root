<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>人员信息管理</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查询条件">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-realName" cname="姓名" colWidth="3" ratio="3:8"/>
			<EF:EFSelect ename="inqu_status-0-sex" cname="性别" colWidth="3" ratio="3:8">
				<EF:EFOption label="全部" value=""/>
				<EF:EFOption label="男" value="男"/>
				<EF:EFOption label="女" value="女"/>
			</EF:EFSelect>
			<EF:EFInput ename="inqu_status-0-workNo" cname="工号" colWidth="3" ratio="3:8"/>
			<EF:EFInput ename="inqu_status-0-company" cname="公司名称" colWidth="3" ratio="3:8"/>
			<EF:EFInput ename="inqu_status-0-deptNum" cname="所属部门" colWidth="3" ratio="3:8" />
			<EF:EFInput ename="inqu_status-0-serviceDeptNum" cname="服务部门" colWidth="3" ratio="3:8" />
			<EF:EFInput ename="inqu_status-0-managementDeptNum" cname="管理部门" colWidth="3" ratio="3:8"  readonly="true"/>
			<EF:EFSelect ename="inqu_status-0-statusCode" cname="状态" colWidth="3" ratio="3:8">
				<EF:EFOption label="全部" value=""/>
				<EF:EFCodeOption codeName="wilp.hr.personCode"/>
			</EF:EFSelect>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="查询" ename="QUERY" layout="0"></EF:EFButton>
			<EF:EFButton cname="重置" ename="RESET" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="人员配置信息">
		<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="false" readonly="true" rowNo="true">
			<EF:EFColumn ename="id" cname="主键" width="100" hidden="true" align="center" enable="false"/>
			<EF:EFColumn ename="realName" cname="姓名" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="workNo" cname="工号" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="sex" cname="性别" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="company" cname="公司名称" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="deptNum" cname="所属部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="serviceDeptNum" cname="服务部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="managementDeptNum" cname="管理部门" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="schoolingCode" cname="学历" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="politicalStatus" cname="政治面貌" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="jobCode" cname="岗位" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="preInTime" cname="预计入职时间" width="150" align="center" enable="false"/>
			<EF:EFColumn ename="inTime" cname="入职时间" width="150" align="center"  enable="false"/>
			<EF:EFComboColumn ename="statusCode" cname="状态" align="center" enable="false">
				<EF:EFCodeOption codeName="wilp.hr.personCode"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="creatorName" cname="登记人" width="100" align="center" enable="false"/>
			<EF:EFColumn ename="createDate" cname="登记时间" width="150" align="center" enable="false"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" title="新增信息" url=" " lazyload="true" width="97%" height="80%"></EF:EFWindow>
<EF:EFWindow id="popDataModify" title="确认入职" url=" " lazyload="true" width="30%" height="45%"></EF:EFWindow>
<EF:EFWindow id="excelImport" url="" lazyload="true" refresh="true" width="45%" height="60%">
	<EF:EFRegion id="enterImport" title="数据导入">
		<h4>登记导入规则：</h4>
		<li>姓名（必填;）</li>
		<li>手机号（必填;）</li>
		<li>性别（选填; ）</li>
		<li>出生日期（选填; 且格式为:yyyy-MM-dd）</li>
		<li>身份证号码（必填;且必须遵循正确的身份证号码格式）</li>
		<li>民族（必填; ）</li>
		<li>籍贯（选填; ）</li>
		<li>学历（必填;）</li>
		<li>岗位（必填; ）</li>
		<li>预计入职时间（选填;且格式为:yyyy-MM-dd ）</li>
		<li>健康状况（必填; ）</li>
		<li>现住址（必填; ）</li>
		<li>婚姻状况（选填; ）</li>
		<li>最高学历（选填;）</li>
		<li>最高学位（选填;）</li>
		<li>公司名称（必填;）</li>
		<li>基本工资（选填; ）</li>
		<li>政治面貌（选填; ）</li>
		<li>人员类别（必填;第三方人员/后勤保障中心）</li>
		<li>所属部门（必填;）</li>
		<li>服务部门（必填;）</li>
		<li>紧急联系人（必填;）</li>
		<li>紧急联系人电话（必填;）</li>
		<li>管理部门（必填;数据中心/后勤保障中心/消防科/物业管理科/输送科/动力运维科）</li>
		<li>备注（选填;）</li>

		<li></li>

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
<script type="text/javascript" src="${ctx}/HR/common/js/hr-excel-import.js"></script>