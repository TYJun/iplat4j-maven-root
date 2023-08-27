<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>人员信息新增</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="新增">
		<div class="row">
		<EF:EFInput ename="workNo" cname="工号" colWidth="3"  ratio="3:8"/>
            <EF:EFInput ename="id" cname="id" type="hidden"/>
            <EF:EFInput ename="type" cname="类型" type="hidden"/>
		<EF:EFInput ename="realName" cname="姓名" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFSelect ename="sex" cname="性别" colWidth="3" ratio="3:8"  textField="label" valueField="value">
			<EF:EFOption label="请选择" value=""/>
			<EF:EFOption label="女" value="女"/>
			<EF:EFOption label="男" value="男"/>
		</EF:EFSelect>
		<EF:EFInput ename="birthPlace" cname="籍贯" colWidth="3"  ratio="3:8"/>
		<EF:EFSelect ename="maritalStatus" cname="婚姻状况" colWidth="3" ratio="3:8"  textField="label" valueField="value">
			<EF:EFOption label="请选择" value=""/>
			<EF:EFOption label="未婚" value="未婚"/>
			<EF:EFOption label="已婚" value="已婚"/>
		</EF:EFSelect>
		<EF:EFSelect ename="personnelCategory" cname="人员类别" colWidth="3" required="true" ratio="3:8"  textField="label" valueField="value">
			<EF:EFOption label="请选择" value=""/>
			<EF:EFOption label="第三方人员" value="第三方人员"/>
			<EF:EFOption label="后勤保障中心" value="后勤保障中心"/>
		</EF:EFSelect>
		<EF:EFPopupInput ename="deptNum" cname="所属部门" colWidth="3" ratio="3:8"
						 popupType="ServiceGrid" popupTitle="科室" serviceName="HRPZ01"
						 methodName="queryDept" resultId="dept" autofit="true" readonly="true" required="true"
						 valueField="deptNum" textField="deptName" columnEnames="deptNum,deptName"
						 columnCnames="科室编码,科室名称"
		/>
		<EF:EFPopupInput ename="serviceDeptNum" cname="服务部门" colWidth="3" ratio="3:8"
						 popupType="ServiceGrid" popupTitle="科室" serviceName="HRPZ01"
						 methodName="queryDept" resultId="dept" autofit="true" readonly="true" required="true"
						 valueField="deptNum" textField="deptName" columnEnames="deptNum,deptName"
						 columnCnames="科室编码,科室名称"
		/>
		<EF:EFInput ename="kampong" cname="民族" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFInput ename="manCode" cname="身份证号码" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFInput ename="schoolingCode" cname="学历" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFInput ename="highestEducational" cname="最高学历" colWidth="3"  ratio="3:8"/>
		<EF:EFInput ename="highestDegree" cname="最高学位" colWidth="3"  ratio="3:8"/>
		<EF:EFInput ename="jobCode" cname="岗位" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFInput ename="salary" cname="基本工资" colWidth="3" ratio="3:8"/>
		<EF:EFInput ename="politicalStatus" cname="政治面貌" colWidth="3"  ratio="3:8"/>
		<EF:EFDatePicker ename="birthDate" cname="出生日期" role="date" colWidth="3" ratio="3:8" format="yyyy-MM-dd"
						 parseFormats="['yyyy-mm-dd']" readonly="true" />
		<EF:EFDatePicker ename="preInTime" cname="入职\入场日期" role="date" colWidth="3" ratio="3:8" format="yyyy-MM-dd"
						 parseFormats="['yyyy-mm-dd']" readonly="true" />
		<EF:EFInput ename="phone" cname="联系电话" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFInput ename="emergency" cname="紧急联系人" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFInput ename="emergencyPhone" cname="紧急联系人电话" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFInput ename="health" cname="健康状况" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFInput ename="familyAddress" cname="现住址" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFInput ename="company" cname="公司名称" colWidth="3" required="true" ratio="3:8"/>
		<EF:EFInput ename="memo" cname="备注" type="textarea" maxLength="400" colWidth="3" ratio="3:8"/>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="tab-tab_grid">
		<div title="证书管理">
			<EF:EFGrid blockId="resultC" needAuth="false" autoDraw="no">
				<EF:EFColumn ename="fileId" cname="文件id" width="200" hidden="true"/>
				<EF:EFColumn ename="fileName" cname="文件名称" width="200" enable="false"/>
				<EF:EFColumn ename="fileSize" cname="文件大小" width="100" enable="false"/>
				<EF:EFColumn ename="fileDesc" cname="文件说明" width="200"/>
				<EF:EFColumn ename="fileNum" cname="文件排序" width="200" hidden="true"/>
			</EF:EFGrid>
		</div>
		<div title="变更履历">
			<EF:EFGrid blockId="resultD" needAuth="false" autoDraw="no">
				<EF:EFColumn ename="id" cname="文件id" width="200" hidden="true"/>
				<EF:EFColumn ename="manId" cname="人员id" width="200" hidden="true"/>
				<EF:EFColumn ename="operatorType" cname="类型" width="100" enable="false"/>
				<EF:EFColumn ename="operatormanName" cname="操作人" width="200" enable="false"/>
				<EF:EFColumn ename="operatorTime" cname="操作时间" width="200" enable="false"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
</EF:EFPage>
<!-- 附件上传窗口 -->
<EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
	<EF:EFRegion id="upload" title="文件上传">
		<EF:EFUpload ename="hrxxFiles" docTag="hrxx_files" path="HR" showFileList="true"/>
	</EF:EFRegion>
</EF:EFWindow>