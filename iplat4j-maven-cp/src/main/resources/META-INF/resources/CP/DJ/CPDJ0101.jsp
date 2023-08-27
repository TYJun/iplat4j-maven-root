<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	.control-label{
		font-size: 18px;
	}
	span{
		font-size: 18px;
		margin-top: -3px;
	}
	label{
		font-size: 18px !important;
		line-height:20px;
	}
	.i-theme-ant label{
		font-weight: 400;
		padding-top: 4px;
		padding-bottom: 15px;
	}
	.i-theme-ant .i-region .i-region-header {
		font-size: 14px;
		padding: 4px 12px 4px 16px;
		line-height: 20px;
		color: #2F80ED;
		letter-spacing: 0;
		background: #DBEFFF;
		font-weight: 700;
		overflow: hidden;
	}
	button{
		width:100px;
		height: 100px;

	}
	i-theme-ant input .k-textbox {
	height: 80px;}
	inqu_status-0-complaintDate_clear-button{
		font-size: 20px;
	}


</style>
<EF:EFPage>

	<div id="title">
		<EF:EFRegion id="resultTitle" title="职工心声邮箱" fitHeight="false" style="font-size: 20px;hight:30px;">
			<center></center>
			<div id="content" style="position: relative; left: 50%; transform: translateX(-50%);width: 1160px">
				<p style="font-size: 20px;margin:10px 0px 5px 0px">
					亲爱的同事们:
				</p>
				<p style="font-size: 20px;text-indent: 2em;">
					为持续改善医院管理和医疗服务质量，提高职工的获得感和幸福感，提升医院整体满意度，工会开通“职工心声邮箱"听您的心声，把您在生活、工作中的困难或对医院各项工作的意见和建议告诉我们，工会将与医院各部门协同解决您的合理诉求，不断提升员工幸福指数，以更饱满的热情投入到医院高质量发展建设中去。
				</p>
			</div>
		</EF:EFRegion>
	</div>
	<div id="deal" style="display: none">
		<EF:EFRegion id="resultA" title="分配处理科室" fitHeight="false" style="font-size: 30px;">
			<EF:EFSelect ename="inqu_status-0-deptName" cname="处理部门/单位" filter="contains"
						 resultId="dept" textField="deptName" valueField="deptNo"
						 serviceName="CPDJ01" methodName="queryDeptByRole" optionLabel="请选择"
						 colWidth="5" ratio="4:8" required="true" style="font-size: 18px;">
			</EF:EFSelect>
<%--			<div class="button-region">--%>
<%--				<EF:EFButton cname="完结" ename="COMPLETED" layout="0"></EF:EFButton>--%>
<%--			</div>--%>
		</EF:EFRegion>
	</div>
	<EF:EFRegion id="result" title="个人信息" fitHeight="false" style="font-size: 18px;">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-type" cname="type" type="hidden"/>
			<EF:EFInput ename="type" cname="type" type="hidden"/>
			<EF:EFInput ename="inqu_status-0-projectId" cname="主键" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billNo" cname="发起单号" colWidth="7" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billMakeTime" cname="制单时间" colWidth="7" ratio="4:8" readonly="true" type="hidden" />
			<EF:EFInput ename="inqu_status-0-billMaker" cname="制单人" colWidth="7" ratio="4:8" readonly="true" type="hidden" />
		</div>
		<div class="row" style="font-size: 18px;">
			<EF:EFDatePicker style="font-size: 18px;importance!;" ename="inqu_status-0-complaintDate" cname="发生日期"
							 format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
							 bindId="complaintDate" colWidth="5" ratio="4:8"  required="true"/>

			<EF:EFInput ename="inqu_status-0-recCreator" cname="登记人工号" colWidth="5"
						ratio="4:8" type="hidden"/>

			<EF:EFInput ename="inqu_status-0-recCreateName" cname="登记人" colWidth="5"
						ratio="4:8" type="hidden"/>

			<EF:EFInput ename="inqu_status-0-complaintName" cname="发起人" colWidth="5"
						ratio="4:8" required="true" readonly="true"/>

			<EF:EFInput ename="inqu_status-0-complaintPhone" cname="发起人电话" colWidth="5"
						ratio="4:8" required="true"/>

			<EF:EFInput ename="inqu_status-0-complaintEmail" cname="发起人邮箱" colWidth="5"
						ratio="4:8" />

			<EF:EFInput ename="inqu_status-0-complaintDept" cname="发起人部门/单位" colWidth="5"
						ratio="4:8" />
			<EF:EFInput ename="inqu_status-0-complaintContent" cname="发起内容" colWidth="10"
						ratio="2:10" type="hidden" rows="8" maxLength="400" />
		</div>
	</EF:EFRegion>
	<EF:EFRegion style="font-size: 18px;" id="resultContent" title="职工诉求" fitHeight="false">
		<div class="row" style="font-size: 18px;">
			<EF:EFInput ename="complaintContent" cname="发起内容" colWidth="10"
						ratio="2:10" type="textarea" rows="8" maxLength="400" required="true"/>
		</div>
		<div class="row" style="font-size: 18px;" length="100">
			<div class="col-md-10">
				<div class="form-group">
					<label for="reqPic" class="col-xs-2 control-label" style="font-size: 18px;">参考图片</label>
					<div class="col-xs-10">
						<span id="reqPic"></span>
						<span id="uploadPicSpan">
							<EF:EFButton cname="上传图片" ename="uploadPic" layout="1" class="k-button">
							</EF:EFButton>
							<EF:EFButton cname="清除图片" ename="cleanPic" layout="1" class="k-button">
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
		<div class="button-region" id="buttonDiv" >
			<span id="SAVEPRSPAN">
				<EF:EFButton cname="提交" ename="SAVEPR" layout="0" style="font-size:18px;"></EF:EFButton>
			</span>
			<EF:EFButton cname="取消" ename="CANCEL" layout="0" style="font-size:18px;"></EF:EFButton>
		</div>
	</EF:EFRegion>

	<!-- 图片上传窗口 -->
	<EF:EFWindow id="picChoose" title="图片上传" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="ReUpload" title="图片上传" head="hidden">
			<EF:EFUpload ename="tsPic" docTag="mt_fs_file" path="cp/img"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>

