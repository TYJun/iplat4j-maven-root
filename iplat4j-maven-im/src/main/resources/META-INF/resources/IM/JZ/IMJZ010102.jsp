<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage title="添加周期">
	<EF:EFRegion id="addCyc">
	
		<div class="row">
		<EF:EFDatePicker ename="startTime" cname="开始时间:" role="datetime" colWidth="5" ratio="4:8" format="yyyy-MM-dd HH:mm:ss"/>
			<EF:EFInput ename="cycle" cname="间隔:" colWidth="3" type="text" placeholder="必填" required="true" value="1"/>
		</div>
		<div class="row">
			<div class="col-md-5">
				<div class="form-group">
					<label data-for="tt" class="col-xs-4 control-label">
						<span>单位</span>
					</label>
					<div class="col-md-8">
						<EF:EFInput ename="unit" cname="小时" value="h" type="radio" inline="true"/>
						<EF:EFInput ename="unit" cname="天" value="d" type="radio" inline="true" checked="checked"/>
						<EF:EFInput ename="unit" cname="月" value="m" type="radio" inline="true"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-5">
				<div class="form-group">
					<label data-for="tt" class="col-xs-4 control-label">
						<span>是否跳过节假日</span>
					</label>
					<div class="col-md-8">
						<EF:EFInput ename="holiday" cname="是" value="Y" type="radio" inline="true"/>
						<EF:EFInput ename="holiday" cname="否" value="N" type="radio" inline="true" checked="checked"/>
						
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-5">
				<div class="form-group">
					<label data-for="tt" class="col-xs-4 control-label">
						<span>是否跳过周六周日</span>
					</label>
					<div class="col-md-8">
						<EF:EFInput ename="weekend" cname="是" value="Y" type="radio" inline="true"/>
						<EF:EFInput ename="weekend" cname="否" value="N" type="radio" inline="true" checked="checked"/>
						
					</div>
				</div>
			</div>
		</div>
	</EF:EFRegion>
</EF:EFPage>