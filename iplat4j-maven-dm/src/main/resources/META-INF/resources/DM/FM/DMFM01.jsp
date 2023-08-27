<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="宿舍费用管理">
	<EF:EFRegion id="inqu" title="查询区" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-buildingCode" cname="宿舍楼"  />
		<%--	<EF:EFInput ename="inqu_status-0-manName" cname="姓名" />
			<EF:EFInput ename="inqu_status-0-departmentNo" cname="科室部门" />--%>
			<%--<EF:EFSelect ename="inqu_status-0-employmentNature" cname="员工类型">
				<EF:EFOption label="请先择员工类型" value="" />
				<EF:EFOption label="本院职工" value="本院职工" />
				<EF:EFOption label="外协单位员工" value="外协单位员工" />
				<EF:EFOption label="医院返聘职工" value="医院返聘职工" />
				<EF:EFOption label="科室返聘职工" value="科室返聘职工" />
				<EF:EFOption label="进修医生" value="进修医生" />
				<EF:EFOption label="进修护士" value="进修护士" />
				<EF:EFOption label="实习医生" value="实习医生" />
				<EF:EFOption label="实习护士" value="实习护士" />
				<EF:EFOption label="研究生" value="研究生" />
				<EF:EFOption label="临时医技人员" value="临时医技人员" />
				<EF:EFOption label="规范会培训生" value="规范会培训生" />
				<EF:EFOption label="院聘临时工" value="院聘临时工" />
				<EF:EFOption label="科聘临时工" value="科聘临时工" />
			</EF:EFSelect>--%>
		<%--	<EF:EFSelect ename="inqu_status-0-ifReview" cname="审批状态" readonly="true"
				defaultValue="2">
				<EF:EFOption label="请先择审批状态" value="" />
				<EF:EFOption label="待审核" value="-2" />
				<EF:EFOption label="待分配" value="1" />
				<EF:EFOption label="不通过" value="-3" />
				<EF:EFOption label="已分配" value="2" />
			</EF:EFSelect>--%>
			<%-- <EF:EFInput ename="queryDept" cname="状态" /> --%>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="费用信息" fitHeight="true">
		<EF:EFGrid blockId="result" autoDraw="no">
			<EF:EFColumn ename="id" cname="id" readonly="true" hidden="true" />
			<EF:EFColumn ename="buildingCode" cname="宿舍楼 " readonly="true"
				width="100" />
			<EF:EFColumn ename="floorCode" cname="楼层 " readonly="true" width="40" />
			<EF:EFColumn ename="roomNo" cname="房间号 " readonly="true" width="40" />
			<EF:EFColumn ename="monthRent" cname="月租费（元）" readonly="true" width="70" />
			<%--
			<EF:EFColumn ename="lastElec" cname="上月用电量 （度）" readonly="true"
				width="70" hidden="false" />
			<EF:EFColumn ename="lastWater" cname="上月用水量（吨） " readonly="true"
				width="70" hidden="true" />
			<EF:EFColumn ename="monthElec" cname="本月用电量 （度）" readonly="true"
				width="100" />

			<EF:EFColumn ename="monthWater" cname="本月用水量 （吨）" readonly="true"
				width="70" /> --%>
			<EF:EFColumn ename="elecFee" cname="本月电费 （元）" readonly="true"
						 width="70" />
			<EF:EFColumn ename="waterFee" cname="水费（元）" readonly="true"
				width="70" />
			<EF:EFColumn ename="hospitalManageFee" cname="医院管理费（元）" readonly="true"
						 width="70" />
			<EF:EFColumn ename="propertyManageFee" cname="物业管理费（元）" readonly="true"
						 width="70" />
			<EF:EFColumn ename="networkFee" cname="网费（元）" readonly="true"
						 width="70" />
			<EF:EFColumn ename="creator" cname="创建人 " readonly="true" width="100"
				hidden="true" />
			<EF:EFColumn ename="createrTime" cname="创建时间" readonly="true"
				width="100" hidden="true" />
			<EF:EFColumn ename="roomId" cname="房间id"
				readonly="true" width="100" hidden="true" />
			<EF:EFColumn ename="dormitoryNo" cname="楼-层-房间编号" readonly="true"
				width="100" hidden="true" />
			<!-- 电费、水费、本月用电量、本月用水量、 -->
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFWindow id="popData" url=" " lazyload="true" width="60%" height="60%" title="新增信息" modal="true" />
	<EF:EFUpload ename="file3" docTag="sc_bm_file" path="sc/file" style="display:none;" hidden="true"/>
</EF:EFPage>


<script type="text/javascript">
	//弹窗 


</script>