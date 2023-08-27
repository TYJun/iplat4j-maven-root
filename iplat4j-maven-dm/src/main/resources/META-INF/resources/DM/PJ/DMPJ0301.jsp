<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="{pageContext.request.contextPath}" />

<EF:EFPage title="评价内容">
	<EF:EFRegion id="result">
		<div class="row">
			<EF:EFInput ename="manId" cname="manId" type="hidden"/>
		</div>
		<div class="row">
			<div class = "col-md-10">
				<div class="form-group">
					<label class="col-xs-2 control-label"><span
							style="color: red">*</span><span>学生行为</span></label>
					<div class=" col-xs-10">
						<EF:EFInput ename="manBehavior" inline="true" value="5" cname="很满意"
									type="radio" checked="checked" />
						<EF:EFInput ename="manBehavior" inline="true" value="4" cname="满意"
									type="radio" />
						<EF:EFInput ename="manBehavior" inline="true" value="3" cname="一般"
									type="radio" />
						<EF:EFInput ename="manBehavior" inline="true" value="2" cname="差"
									type="radio" />
						<EF:EFInput ename="manBehavior" inline="true" value="1" cname="很差"
									type="radio" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<EF:EFInput ename="evalContent" cname="评价备注栏" ratio="2:10"
						colWidth="10" type="textarea" rows="3" />
		</div>
	</EF:EFRegion>
</EF:EFPage>

