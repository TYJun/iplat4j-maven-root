<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<EF:EFRegion id="result" title="登记" fitHeight="false">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-type" cname="type"  type="hidden"/>
			<EF:EFInput ename="inqu_status-0-projectId" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billNo" cname="发起单号" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billMakeTime" cname="制单时间" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billMaker" cname="制单人" colWidth="5" ratio="4:8" readonly="true" type="hidden" />
		</div>
		<div class="row">

			<EF:EFDatePicker ename="inqu_status-0-complaintDate" cname="发生日期"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
							 bindId="complaintDate" colWidth="5" ratio="4:8"  required="true"/>

			<EF:EFInput ename="inqu_status-0-complaintName" cname="发起单号" colWidth="5"
				ratio="4:8" type="text" required="ture" />

		</div>

		<div class="row">
			<EF:EFInput ename="inqu_status-0-complaintPhone" cname="发起人电话" colWidth="5"
						ratio="4:8" type="text" required="ture" />

			<EF:EFInput ename="inqu_status-0-complaintDept" cname="发起人部门/单位" colWidth="5"
						ratio="4:8" type="text"/>

		</div>

		<div class="row">
			<EF:EFInput ename="inqu_status-0-complaintEmail" cname="发起人邮箱" colWidth="5"
						ratio="4:8" type="text"/>
<%--			<EF:EFInput ename="inqu_status-0-complaintType" cname="投诉类别" colWidth="5"--%>
<%--						ratio="4:8" type="text"/>--%>
<%--			小代码获取投诉类别信息--%>
			<EF:EFSelect ename="inqu_status-0-complaintType" cname="发起类别" colWidth="5" ratio="4:8"  type="text" >
				<EF:EFOption label="请选择" value=""/>
				<EF:EFCodeOption codeName="wilp.cp.complaintType"/>
			</EF:EFSelect>
		</div>

		<div class="row">
<%--				小代码获取投诉方式信息--%>
			<EF:EFSelect ename="inqu_status-0-complaintWay" cname="发起方式" colWidth="5" ratio="4:8"  type="text">
				<EF:EFOption label="请选择" value=""/>
				<EF:EFCodeOption codeName="wilp.cp.complaintWay"/>
			</EF:EFSelect>
<%--	<EF:EFInput ename="inqu_status-0-complaintWay" cname="投诉方式" colWidth="5"--%>
<%--				ratio="4:8" type="text"/>--%>
		</div>

		<div class="row">
			<EF:EFInput ename="inqu_status-0-complaintContent" cname="发起内容" colWidth="10"
						ratio="2:10" type="textarea" rows="4" maxLength="200" required="true" />
		</div>

		<div class="row">
			<div class="col-md-10">
				<div class="form-group">
					<label for="reqPic" class="col-xs-2 control-label">发起图片</label>
					<div class="col-xs-10">
						<span id="reqPic"></span>
						<span>
							<EF:EFButton cname="上传图片" ename="uploadPic" layout="1" class="k-button">
							</EF:EFButton>
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

		<div class="button-region" id="buttonDiv">
			<EF:EFButton cname="确定" ename="SAVEPR" layout="0"></EF:EFButton>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
		</div>
	</EF:EFRegion>


	<!-- 图片上传窗口 -->
	<EF:EFWindow id="picChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="ReUpload" title="图片上传">
			<EF:EFUpload ename="tsPic" docTag="mt_fs_file" path="mt/img"/>
		</EF:EFRegion>

	</EF:EFWindow>
</EF:EFPage>

