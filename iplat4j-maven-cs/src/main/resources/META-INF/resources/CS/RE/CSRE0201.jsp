<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage title="工单详情">
	<EF:EFRegion id="result2">
		<div class="row">
			<EF:EFInput ename="taskId" cname="工单id" colWidth="5" readonly="true"
				type="hidden" />
			<EF:EFInput ename="taskNo" cname="工单号" colWidth="5" readonly="true"
				type="hidden" />
		</div>
		<div class="row">
			<EF:EFInput ename="reqStaffId" cname="来电人工号" colWidth="5"
				readonly="true" />
			<EF:EFInput ename="reqStaffName" cname="来电人名称" colWidth="5"
				readonly="true" />
		</div>
		<div class="row">
			<EF:EFInput ename="reqTelNum" cname="来电号码" colWidth="5"
				readonly="true" />
			<EF:EFInput ename="reqDeptName" cname="来电科室" resultId="deptName"
				colWidth="5" readonly="true">
			</EF:EFInput>
		</div>
		<div class="row">
			<EF:EFInput ename="reqSpotName" cname="保洁地点"
				colWidth="5" readonly="true">
			</EF:EFInput>
			<EF:EFInput ename="codeName" cname="工单状态"
						colWidth="5" readonly="true">
			</EF:EFInput>
		</div>

		<div class="row">
			<div class="col-md-10">
				<div class="form-group">
					<label for="reqPic" class="col-xs-2 control-label">图片</label>
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

        <div class="row">
            <EF:EFInput ename="comments" cname="具体地址" colWidth="5" type="textarea" readonly="true"/>
        </div>
	</EF:EFRegion>

	<EF:EFRegion id="selectItem" title="保洁事项">
		<EF:EFGrid blockId="resultItem" autoDraw="no" autoBind="true"
			readonly="true" serviceName="CSRE0201" queryMethod="queryItemsRE">
			<EF:EFColumn ename="itemId" cname="保洁事项记录id" readonly="true"
				align="center" enable="false" hidden="true" />
			<EF:EFColumn ename="itemCode" cname="保洁事项编码" readonly="true"
				align="center" enable="false" hidden="true" />
			<EF:EFColumn ename="itemName" cname="保洁事项" readonly="true"
				align="center" enable="false" />
			<EF:EFColumn ename="serviceDeptNum" cname="绑定科室编码" readonly="true"
				align="center" enable="false" hidden="true" />
			<EF:EFColumn ename="serviceDeptName" cname="绑定科室" readonly="true"
				align="center" enable="false" />
			<EF:EFColumn ename="typeCode" cname="保洁事项分类编码" readonly="true"
				align="center" enable="false" hidden="true" />
			<EF:EFColumn ename="typeName" cname="保洁事项分类" readonly="true"
				align="center" enable="false" />
			<EF:EFColumn ename="comments" cname="具体保洁事项" readonly="true"
				align="center" enable="false" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>