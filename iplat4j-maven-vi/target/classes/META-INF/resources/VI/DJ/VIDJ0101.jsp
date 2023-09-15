<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="被访者姓名">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-visitingDeptId" cname="被访问科室" colWidth="5" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-visitingDeptName" cname="被访问科室" colWidth="5"/>
			<EF:EFInput ename="inqu_status-0-interviewerWorkNo" cname="被访者姓名" colWidth="5" readonly="true" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-nterviewerName" cname="被访者姓名" colWidth="5" readonly="true"/>
			<EF:EFDatePicker ename="inqu_status-0-vistingBeginDate" cname="访问日期" role="datetime" colWidth="5"
							 format="yyyy-MM-dd HH:mm:ss" parseFormats="['yyyy-MM-dd HH:mm:ss']" interval="60"/>
			<div class="button-region" id="buttonDiv">
				<EF:EFButton cname="确认" ename="CONFIRM" layout="3"></EF:EFButton>
				<EF:EFButton cname="取消" ename="CANCEL" layout="3"></EF:EFButton>
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFTab id="ViTab">
		<div title="访客列表">
			<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true" rowNo="true" height="550px">
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
		</div>
		<div title="附件上传">
			<EF:EFGrid blockId="resultFile" autoDraw="no" checkMode="single,row" autoBind="true" readonly="true" rowNo="true" height="550px">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
				<EF:EFColumn ename="fileName" cname="附件名称" width="50" align="center" height="100" enable="false"/>
				<EF:EFColumn ename="fileSize" cname="附件大小(MB)" width="50" align="center" height="100" enable="false"/>
				<EF:EFColumn ename="fileDesc" cname="附件说明" width="50" align="center" height="100"/>
				<EF:EFColumn ename="uploadTime" cname="上传时间" width="50" align="center" height="100" enable="false"/>
			</EF:EFGrid>
		</div>
	</EF:EFTab>
<%--	<div class="row">--%>
<%--		<EF:EFRegion id="visitor" title="访客列表">--%>
<%--			<EF:EFGrid blockId="result" autoDraw="no" checkMode="single,row" autoBind="true"--%>
<%--					   readonly="true" rowNo="true">--%>
<%--				<EF:EFColumn ename="visitId" cname="id" width="100" hidden="true"/>--%>
<%--				<EF:EFColumn ename="uplaodPic" cname="访客照片" width="100" align="center" height="100" enable="false"/>--%>
<%--				<EF:EFColumn ename="guestName" cname="访客姓名" width="100" align="center" required="true"/>--%>
<%--				<EF:EFColumn ename="cardId" cname="身份证号码" width="100" align="center" required="true"/>--%>
<%--				<EF:EFColumn ename="guestPhone" cname="访客电话" width="100" align="center"/>--%>
<%--				<EF:EFColumn ename="vehicleTypeCode" cname="车型" width="100" align="center"/>--%>
<%--				<EF:EFColumn ename="vehicleNumber" cname="车牌号" width="100" align="center"/>--%>
<%--				<EF:EFColumn ename="guestOrgName" cname="来访单位" width="100" align="center"/>--%>
<%--				<EF:EFColumn ename="ntervieweeContent" cname="来访事由" width="150" align="center"/>--%>
<%--			</EF:EFGrid>--%>
<%--		</EF:EFRegion>--%>
<%--	</div>--%>
	<div id="outerdiv"
		 style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
		<div id="innerdiv" style="position: absolute;">
			<img id="bigimg" style="border: 5px solid #fff;" src="" />
		</div>
	</div>
	<!-- 图片上传窗口 -->
	<EF:EFWindow id="picChoose" title="照片上传" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="ReUpload" title="照片上传" head="hidden">
			<EF:EFUpload ename="viPic" docTag="vi_pic" path="vi/img"/>
		</EF:EFRegion>
	</EF:EFWindow>
	<!-- 附件上传窗口 -->
	<EF:EFWindow id="fileChoose" title="附件上传" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="upload" title="文件上传"  head="hidden">
			<EF:EFUpload ename="viFile" docTag="vi_file" path="vi/file"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>

