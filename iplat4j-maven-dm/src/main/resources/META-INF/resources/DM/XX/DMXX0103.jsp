<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
	<script type="text/javascript" src="${ctx}/DM/common/js/DMCommon.js"></script>
<%--	<script type="text/javascript" src="../../DM/common/js/DMCommon.js"></script>--%>
<%--	<style>--%>
<%--		.picDormRoom {--%>
<%--			margin: 2px;--%>
<%--		}--%>
<%--	</style>--%>
	<EF:EFRegion id="result" title="宿舍信息" fitHeight="false">
		<div class="row">
			<EF:EFInput ename="id" cname="主键" colWidth="5" type="hidden" readonly="true" />
		</div>
		<div class="row">
			<EF:EFInput ename="building" cname="宿舍楼" colWidth="5"  type="text" readonly="true"/>
			<EF:EFInput ename="floor" cname="宿舍层" colWidth="5"  type="text" readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="roomNo" cname="宿舍号" colWidth="5"  type="text" readonly="true"/>
			<EF:EFInput ename="bedNum" cname="床位数量" colWidth="5"  type="text" readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="dormProperties" cname="宿舍属性" colWidth="5"  type="text"
						readonly="true"/>
			<EF:EFInput ename="typeCodeName" cname="宿舍类型" colWidth="5"  type="text"
						readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="openRoomMean" cname="是否开放选房" colWidth="5"  type="text"
						readonly="true"/>
			<EF:EFInput ename="dormArea" cname="宿舍面积" colWidth="5"  type="text"
						readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="elevatorRoom" cname="是否为电梯房" colWidth="5"  type="text"
						readonly="true"/>
			<EF:EFInput ename="priBathroom" cname="独立卫生间" colWidth="5"  type="text"
						readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="dormPosition" cname="宿舍位置" colWidth="5"  type="text"
						readonly="true"/>
			<EF:EFInput ename="rent" cname="房租" colWidth="5"  type="text"
						readonly="true"/>
		</div>
		<div class="row">
			<EF:EFInput ename="manageFee" cname="管理费" colWidth="5"  type="text"
						readonly="true"/>
			<EF:EFInput ename="annualFee" cname="年费" colWidth="5"  type="text"
						readonly="true"/>
		</div>
		<div class="row">
    		<EF:EFInput ename="note" type="textarea" colWidth="10" ratio="2:10" rows="3" cname="宿舍备注" readonly="true" />
		</div>
		<div class="row">
			<div class="col-md-10">
				<div class="form-group">
					<label for="reqPic" class="col-xs-2 control-label">宿舍图片</label>
					<div class="col-xs-10">
						<span id="reqPic"></span>
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

	<EF:EFTab id="tab-tab_grid" active="0">
		<div title="宿舍物资信息">
			<EF:EFRegion id="results" title="宿舍物资信息">
				<EF:EFGrid blockId="results"  autoDraw="no" checkMode="single,row" readonly="true" rowNo="true" >
					<EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
					<EF:EFColumn ename="dormId" cname="跟房间关联的id" width="100" align="center" hidden="true"/>
					<EF:EFColumn ename="matCode" cname="物资编码" width="100" align="center" enable="true"/>
					<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center" enable="true"/>
					<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center" enable="true"/>
					<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center" enable="true"/>
					<EF:EFColumn ename="num" cname="数量" width="100" align="center" enable="true"/>
					<EF:EFColumn ename="recCreateTime" cname="创建时间" width="100" align="center" enable="true" hidden="true"/>
					<EF:EFColumn ename="recCreator" cname="创建人" width="100" align="center" enable="true" hidden="true"/>
				</EF:EFGrid>
			</EF:EFRegion>
		</div>
	</EF:EFTab>

	<!-- 图片上传窗口 -->
	<EF:EFWindow id="picChoose" url="" lazyload="true" refresh="true" width="40%" height="30%">
		<EF:EFRegion id="reUpload" title="图片上传">
			<EF:EFUpload ename="dmrePic" docTag="dm_re_file" path="dm/img"/>
		</EF:EFRegion>
	</EF:EFWindow>
</EF:EFPage>
<script type="text/javascript">
	////////////////////////////////////////////////////////////////////
	$(function() {
		let roomId = IPLAT.EFInput.value($("#id"));
		/**
		 * 加载图片
		 */
		loadImage(roomId);
	});
</script>