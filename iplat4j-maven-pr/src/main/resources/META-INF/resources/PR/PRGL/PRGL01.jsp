<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.padding20 {
	padding: 10px 0;
}
</style>
<EF:EFPage prefix="AQXC">
	<EF:EFRegion id="inqu" title="问题登记" showClear="true"
		style="width: 1000px;margin: 0 auto;">

		<div class="row padding20">
			<EF:EFSelect ename="inqu_status-problemCategory" cname="问题分类:&nbsp;&nbsp;&nbsp;&nbsp;"
						 resultId="saftyType" textField="label" valueField="value" required="true"
						 colWidth="12" ratio="2:8"
						 serviceName="PRGL03" methodName="safty" optionLabel="请选择" />
		</div>
		<div class="row padding20">
			<EF:EFInput colWidth="12" ratio="2:8"
				required="true" ename="inqu_status-problemLocation" cname="问题地点:&nbsp;&nbsp;&nbsp;&nbsp;"></EF:EFInput>
		</div>

		<div class="row padding20">
			<EF:EFDatePicker colWidth="12" ratio="2:8" format="yyyy/MM/dd"
				required="true" ename="inqu_status-0-date" cname="要求整改时间:&nbsp;&nbsp;&nbsp;&nbsp;"></EF:EFDatePicker>
		</div>

		<div class="row padding20">
			<EF:EFSelect colWidth="12" ratio="2:8"
				ename="inqu_status-problemlevel" cname="隐患等级:&nbsp;&nbsp;&nbsp;&nbsp;"
				resultId="grade" textField="typeName" valueField="typeCode" required="true"
				optionLabel="请选择">
				<EF:EFOption label="紧急" value="jj" />
				<EF:EFOption label="普通" value="pt" />
			</EF:EFSelect>
		</div>

		<div class="row padding20">
			<EF:EFInput colWidth="12" ratio="2:8" type="textarea"
				required="true" ename="inqu_status-problemDesciption" cname="描述:&nbsp;&nbsp;&nbsp;&nbsp;"></EF:EFInput>
		</div>
		
		<div class="row padding20">
			<EF:EFInput colWidth="12" ratio="2:8" type="textarea"
				required="true" ename="inqu_status-requrieDesc" cname="整改要求:&nbsp;&nbsp;&nbsp;&nbsp;"></EF:EFInput>
		</div>

		<div class="row">
			<div class="col-md-11">
				<div class="form-group">
					<label for="reqPic" class="col-xs-2 control-label" >&nbsp;问题图片:</label>
					<div class="col-xs-10">
						<span id="reqPic"></span>
						<span><EF:EFButton cname="上传图片" ename="uploadPic" layout="1" class="k-button"></EF:EFButton></span>
					</div>
				</div>
			</div>
		</div>
	</EF:EFRegion>
	<div id="outerdiv"
		 style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
		<div id="innerdiv" style="position: absolute;">
			<img id="bigimg" style="border: 5px solid #fff;" src="" />
		</div>
	</div>
	<!-- 图片上传窗口 -->
	<EF:EFWindow id="picChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="ReUpload" title="图片上传">
			<EF:EFUpload ename="prPic" docTag="pr_fs_file" path="pr/img"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>
