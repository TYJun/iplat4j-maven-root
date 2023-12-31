<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="inquA" title="登记信息">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-type" cname="type"  type="hidden"/>
			<EF:EFInput ename="inqu_status-0-billNo" cname="发起单号" colWidth="5" ratio="4:8" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-billMakeTime" cname="制单时间" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billMaker" cname="制单人" colWidth="5" ratio="4:8" readonly="true" type="hidden" />

			<EF:EFDatePicker ename="inqu_status-0-complaintDate" cname="发生日期"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
							 bindId="complaintDate" colWidth="5" ratio="4:8"  required="true"/>

			<EF:EFInput ename="inqu_status-0-complaintName" cname="发起人" colWidth="5"
						ratio="4:8" type="text" required="ture" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-complaintPhone" cname="发起人电话" colWidth="5"
						ratio="4:8" type="text" required="ture" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-complaintDept" cname="发起人部门/单位" colWidth="5"
						ratio="4:8" type="text" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-complaintEmail" cname="发起人邮箱" colWidth="5"
						ratio="4:8" type="text" readonly="true"/>
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
	<EF:EFRegion id="inquB" title="科室处理记录">
		<EF:EFGrid blockId="resultA" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" serviceName="CPZH0101" queryMethod="queryTabA">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
			<EF:EFColumn ename="dealDept" cname="处理科室" width="100" align="center"/>
			<EF:EFColumn ename="dealWorkName" cname="处理人" width="100" align="center"/>
			<EF:EFColumn ename="dealDate" cname="处理日期" width="100" align="center"/>
			<EF:EFColumn ename="dealReason" cname="解决方式" width="100" align="center"/>
			<EF:EFColumn ename="dealDesc" cname="不予解决的情况说明" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFRegion id="inquC" title="工会回访记录">
		<EF:EFGrid blockId="resultB" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
				   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" serviceName="CPZH0101" queryMethod="queryTabB">
			<EF:EFColumn ename="returnDesc" cname="回访情况" width="100" align="center"/>
			<EF:EFColumn ename="returnWorkName" cname="回访人" width="100" align="center"/>
			<EF:EFColumn ename="returnDate" cname="回访日期" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
	<EF:EFRegion id="inquD" title="评价等级">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-teskEval" cname="评价等级" colWidth="5"
						ratio="4:8" readonly="true"/>
		</div>
		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>
</EF:EFPage>