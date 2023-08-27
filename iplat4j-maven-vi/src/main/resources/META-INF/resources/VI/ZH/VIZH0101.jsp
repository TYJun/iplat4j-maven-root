<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="被访客信息">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="编号" colWidth="5" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-visitingDeptName" cname="访问科室" colWidth="5" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-nterviewerName" cname="被访者姓名" colWidth="5" readonly="true"/>
			<EF:EFDatePicker ename="inqu_status-0-vistingBeginDate" cname="访问时间" role="datetime" colWidth="5"
							 format="yyyy-MM-dd HH:mm:ss" parseFormats="['yyyy-MM-dd HH:mm:ss']" readonly="true" disabled="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-auditorMemo" cname="审批意见" colWidth="10" type="textarea"
						placeholder="一般由审批人在审批驳回时填写" rows="5" maxLength="200" ratio="2:10" readonly="true"/>
		</div>
		<div class="row">
			<div class="button-region" id="buttonDiv">
				<EF:EFButton cname="取消" ename="CANCEL" layout="3"></EF:EFButton>
			</div>
		</div>
		<br>
		<div class="row">
<%--			<EF:EFRegion id="visitor" title="访客列表">--%>
<%--				<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true"--%>
<%--						   readonly="true" rowNo="true">--%>
<%--					<EF:EFColumn ename="visitId" cname="id" width="100" hidden="true"/>--%>
<%--					<EF:EFColumn ename="uplaodPic" cname="访客照片" width="50" align="center" height="100" enable="false"/>--%>
<%--					<EF:EFColumn ename="guestName" cname="访客姓名" width="110" align="center" displayType="url" enable="false"/>--%>
<%--					<EF:EFColumn ename="cardId" cname="身份证号码" width="100" align="center" enable="false"/>--%>
<%--					<EF:EFColumn ename="guestPhone" cname="访客电话" width="110" align="center" enable="false"/>--%>
<%--					<EF:EFColumn ename="vehicleTypeCode" cname="车型" width="110" align="center" enable="false" hidden="true"/>--%>
<%--					<EF:EFColumn ename="vehicleNumber" cname="车牌号" width="110" align="center" enable="false"/>--%>
<%--					<EF:EFColumn ename="guestOrgName" cname="来访单位" width="110" align="center" enable="false"/>--%>
<%--					<EF:EFColumn ename="ntervieweeContent" cname="来访事由" width="150" align="center" enable="false"/>--%>
<%--				</EF:EFGrid>--%>
<%--			</EF:EFRegion>--%>
			<EF:EFTab id="ViTab">
				<div title="访客列表">
					<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true" rowNo="true" height="370px">
						<EF:EFColumn ename="visitId" cname="id" width="100" hidden="true"/>
						<EF:EFColumn ename="uplaodPic" cname="访客照片" width="50" align="center" height="100" enable="false"/>
						<EF:EFColumn ename="guestName" cname="访客姓名" width="110" align="center" displayType="url" enable="false"/>
						<EF:EFColumn ename="cardId" cname="身份证号码" width="100" align="center" enable="false"/>
						<EF:EFColumn ename="guestPhone" cname="访客电话" width="110" align="center" enable="false"/>
						<EF:EFColumn ename="vehicleTypeCode" cname="车型" width="110" align="center" enable="false" hidden="true"/>
						<EF:EFColumn ename="vehicleNumber" cname="车牌号" width="110" align="center" enable="false"/>
						<EF:EFColumn ename="guestOrgName" cname="来访单位" width="110" align="center" enable="false"/>
						<EF:EFColumn ename="ntervieweeContent" cname="来访事由" width="150" align="center" enable="false"/>
					</EF:EFGrid>
				</div>
				<div title="附件上传">
					<EF:EFGrid blockId="resultFile" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true" rowNo="true" height="370px">
						<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
						<EF:EFColumn ename="fileName" cname="附件名称" width="40" align="center" height="100" enable="false"/>
						<EF:EFColumn ename="fileSize" cname="附件大小(MB)" width="40" align="center" height="100" enable="false"/>
						<EF:EFColumn ename="fileDesc" cname="附件说明" width="40" align="center" height="100" enable="false"/>
						<EF:EFColumn ename="uploadTime" cname="上传时间" width="40" align="center" height="100" enable="false"/>
					</EF:EFGrid>
				</div>
			</EF:EFTab>
		</div>
	</EF:EFRegion>
	<EF:EFWindow id="popData" title="访客照片" url="" modal="true" width="100%" height="30%" style="display:none;">
		<EF:EFRegion id="pic" head="hidden">
			<div class="row">
				<EF:EFGrid blockId="resultA" autoDraw="no" checkMode="single,row" autoBind="true"
						   readonly="true">
					<EF:EFColumn ename="uplaodPicA" cname="访客照片" width="50" align="center" height="100" enable="false"/>
				</EF:EFGrid>
			</div>
		</EF:EFRegion>
	</EF:EFWindow>
	<div id="outerdiv"
		 style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 999999; width: 100%; height: 100%; display: none;">
		<div id="innerdiv" style="position: absolute;">
			<img id="bigimg" style="border: 5px solid #fff;" src="" />
		</div>
	</div>
</EF:EFPage>

