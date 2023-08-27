<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="被访者姓名">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="编号" colWidth="5" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-visitingDeptName" cname="被访问科室" colWidth="5" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-interviewerWorkNo" cname="被访者姓名" colWidth="5" readonly="true" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-nterviewerName" cname="被访者姓名" colWidth="5" readonly="true"/>
			<EF:EFDatePicker ename="inqu_status-0-vistingBeginDate" cname="访问日期" role="datetime" colWidth="5"
							 format="yyyy-MM-dd HH:mm:ss" parseFormats="['yyyy-MM-dd HH:mm:ss']" readonly="true" disabled="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-auditorMemo" cname="审批意见" colWidth="10" type="textarea"
						placeholder="一般由审批人在审批驳回时填写" rows="5" maxLength="200" ratio="2:10"/>
		</div>
		<div class="row">
			<div class="button-region" id="buttonDiv">
				<EF:EFButton cname="通过" ename="pass" layout="3"></EF:EFButton>
				<EF:EFButton cname="驳回" ename="reject" layout="3"></EF:EFButton>
			</div>
		</div>
		<br>
		<div class="row">
			<EF:EFRegion id="visitor" title="访客列表">
				<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true"
						   readonly="true" rowNo="true">
					<EF:EFColumn ename="visitId" cname="id" width="100" hidden="true"/>
					<EF:EFColumn ename="uplaodPic" cname="访客照片" width="100" align="center" height="100" enable="false"/>
					<EF:EFColumn ename="guestName" cname="访客姓名" width="100" align="center" required="true"/>
					<EF:EFColumn ename="cardId" cname="身份证号码" width="100" align="center" required="true"/>
					<EF:EFColumn ename="guestPhone" cname="访客电话" width="100" align="center"/>
					<EF:EFColumn ename="vehicleTypeCode" cname="车型" width="100" align="center" hidden="true"/>
					<EF:EFColumn ename="vehicleNumber" cname="车牌号" width="100" align="center"/>
					<EF:EFColumn ename="guestOrgName" cname="来访单位" width="100" align="center"/>
					<EF:EFColumn ename="ntervieweeContent" cname="来访事由" width="150" align="center"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</EF:EFRegion>
	<div id="outerdiv"
		 style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
		<div id="innerdiv" style="position: absolute;">
			<img id="bigimg" style="border: 5px solid #fff;" src="" />
		</div>
	</div>
	<!-- 图片上传窗口 -->
<%--	<EF:EFWindow id="picChoose" title="图片上传" url="" lazyload="true" refresh="true" width="40%" height="30%">--%>
<%--		<EF:EFRegion id="ReUpload" title="图片上传" head="hidden">--%>
<%--			<EF:EFUpload ename="viPic" docTag="vi_pic" path="vi/img"/>--%>
<%--		</EF:EFRegion>--%>
<%--	</EF:EFWindow>--%>
</EF:EFPage>

