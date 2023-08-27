<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage prefix="AQXC">
	<EF:EFRegion id="result" title="基本内容">
		<div class="rowId" id="idName">
			<EF:EFInput ename="id" cname="id" colWidth="6" ratio="4:6" readonly="true" />
			<EF:EFInput ename="localtypecode" cname="localtypecode" colWidth="6" ratio="4:6" readonly="true" />
		</div>
		<div class="col-md-12">
			<div class="row" style="height: 10px;">
				<EF:EFInput ename="dangerwhere" cname="问题地点:" colWidth="6"
					ratio="4:6" readonly="true" />
				<EF:EFInput ename="localTypeName" cname="问题分类:" colWidth="6"
					ratio="2:6" readonly="true" />
				<EF:EFInput ename="requiredtime" cname="要求整改时间:" colWidth="6"
					ratio="4:6" readonly="true" />
			</div>
			<div class="row" style="height: 10px;">
				<EF:EFInput colWidth="12" ratio="2:8" type="textarea"
					ename="contentdesc" cname="描述:" readonly="true"></EF:EFInput>
			</div>
			<div class="row" style="height: 10px;">
				<EF:EFInput colWidth="12" ratio="2:8" type="textarea"
					ename="requiredesc" cname="整改要求:" readonly="true"></EF:EFInput>
			</div>
		</div>
		<div class="row">
			<div class="col-md-11">
				<div class="form-group">
					<label for="reqPic" class="col-xs-2 control-label">&nbsp;问题图片:</label>
					<div class="col-xs-10">
						<span id="reqPic"></span>
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


 	<EF:EFRegion id="inqu" title="整改实绩详情">
		<div class="row" style="height: 10px;">
			<EF:EFInput ename="writeman" cname="整改执行人:" colWidth="6" ratio="4:6"/>
			<EF:EFDatePicker ename="finishtime" cname="完成时间"
			role="date" colWidth="6" ratio="2:6" format="yyyy-MM-dd"/>
			<EF:EFInput colWidth="12" ratio="2:8" type="textarea"
				ename="contentdesc2" cname="整改实绩:" ></EF:EFInput>
		</div>
	<div class="row2">
		<div class="col-md-10">
			<div class="form-group2">
				<label for="reqPic2" class="col-xs-2 control-label">整改图片:</label>
				<div class="col-xs-10">
					<span id="reqPic2"></span>
					<span><EF:EFButton cname="上传图片" ename="uploadPic" layout="1" class="k-button"></EF:EFButton></span>
				</div>
			</div>
		</div>
	</div>
	</EF:EFRegion>
	<!-- 图片上传窗口 -->
	<EF:EFWindow id="picChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="ReUpload" title="图片上传">
			<EF:EFUpload ename="saftyPic" docTag="pr_fs_file" path="pr/img"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>
<script>
//隐藏id输入框
$("#idName").hide();
</script>
