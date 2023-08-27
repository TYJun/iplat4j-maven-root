<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>职工心声登记处理</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="登记信息" fitHeight="false">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-type" cname="type"  type="hidden"/>
			<EF:EFInput ename="inqu_status-0-billNo" cname="发起单号" colWidth="5" ratio="4:8" readonly="true"  />
			<EF:EFInput ename="inqu_status-0-billMakeTime" cname="制单时间" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billMaker" cname="制单人" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-statusCode" cname="状态" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFDatePicker ename="inqu_status-0-complaintDate" cname="发生日期"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" disabled="true"
							 bindId="complaintDate" colWidth="5" ratio="4:8"  required="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-complaintName" cname="发起人" colWidth="5"
						ratio="4:8" type="text" required="ture" readonly = "true" />
			<EF:EFInput ename="inqu_status-0-complaintPhone" cname="发起人电话" colWidth="5"
						ratio="4:8" type="text" required="ture" readonly = "true"/>
		</div>

		<div class="row">
			<EF:EFInput ename="inqu_status-0-complaintDept" cname="发起人部门/单位" colWidth="5"
						ratio="4:8" type="text" readonly = "true" />
			<EF:EFInput ename="inqu_status-0-complaintEmail" cname="发起人邮箱" colWidth="5"
						ratio="4:8" type="text" readonly = "true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-complaintContent" cname="发起内容" colWidth="10"
						ratio="2:10" type="textarea" rows="4" maxLength="200" required="true" readonly="true"/>
		</div>
		<div class="row">
			<div class="col-md-10">
				<div class="form-group">
					<label for="reqPic" class="col-xs-2 control-label">参考图片</label>
					<div class="col-xs-10">
						<span id="reqPic"></span>
						<span id="uploadPicSpan">
						</span>
					</div>
				</div>
			</div>
		</div>
		<div id="outerdiv"
			 style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
			<div id="innerdiv" style="position: absolute;">
				<img id="bigimg" style="border: 5px solid #fff;" src="" />
			</div>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="科室处理登记" fitHeight="false">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-dealType" cname="处理类型" colWidth="5" ratio="4:8" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-dealDept" cname="处理部门" colWidth="5" ratio="4:8"/>
			<EF:EFInput ename="inqu_status-0-dealWorkNo" cname="处理人工号" colWidth="5" ratio="4:8" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-dealWorkName" cname="处理人" colWidth="5" ratio="4:8"/>
		</div>
		<div class="row">
			<EF:EFDatePicker ename="inqu_status-0-dealDate" cname="处理时间"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
							 colWidth="5" ratio="4:8"/>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-dealReason" cname="产生原因" colWidth="10"
						ratio="2:10" type="textarea" rows="4" maxLength="200" required="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="inqu_status-0-dealDesc" cname="解决方式" colWidth="10"
						ratio="2:10" type="textarea" rows="4" maxLength="200" required="true"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="SAVEPR" layout="0"></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData1" title="" url=" " lazyload="true" width="90%" height="90%"></EF:EFWindow>