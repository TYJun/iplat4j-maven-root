<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style type="text/css">
	.A{
		float:left;
		width: 600px;
		margin-left:150px;
	}
	
	.table1 {
		border:1px solid #6495ed;
		border-collapse:collapse;
		margin: 0px;
	}

	#td1 {
		text-align: center;
		color: #0000FF;
		font-size: 16px;
		height: 500px;

	}

	.table1 td {
		line-height: 35px;
		border:1px solid #6495ed;
		width: 150px;
	}
	
	#T1 td{
		background-color: #d2e8ff;
		font-weight:bold;
		font-size: 18px;
		color: #0000FF;
		width: 100%;
		text-align: center;
	}

	.text {
		width: 400px;
	    font-size: 16px;
		margin: 1px;
		height: 500px;
    	color: #000000;
    	font-family:Microsoft YaHei;
	}
	#saveBatchSMSTemp{
    	margin-top: 8px;
    	border: 1px solid #3088F4;
    	border-radius: 3px;
    	background-color: #3088F4;
    	min-width: 80px;
    	min-height: 24px;
    	margin-right:8px;
    	color: #FFFFFF;
    	line-height: 100%;
    	font-size: 14px;
    }
</style>
<title>编辑批量发送短信模板</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" head="hidden" title="编辑批量发送短信模板" showClear="false">
   		<div class="A">
			<table class="table1">
				<tr id="T1">
					<td colspan="2">编辑短息模板</td>
				</tr>
				<tr>
					<td id="td1">
						<input type="hidden" id="batchSmsTempCode" value="batchSmsTemp"/>
						<input type="hidden" id="batchSmsTemp_id" />
						<span id="batchSmsTemp_name">批量发送短信模板</span>
					</td>
					<td>
						<textarea class="text" id="batchSmsTemp_msg" name="batchSmsTemp_msg"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:right">
						<input type="button" id="saveBatchSMSTemp" value="&nbsp保&nbsp存&nbsp" style="cursor:pointer"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="B">
			<p class="Z">
				<span style="font-size:16px;color:red;">短信内容配置格式要求：</span><br/>
				姓名：<span style="color:blue" class="Z">$name$</span><br/>
				工号：<span style="color:blue" class="Z">$nameNo$</span><br/>
				电话：<span style="color:blue" class="Z">$Tel$</span><br/>
				宿舍总称(楼+层+宿舍号): <span style="color:blue" class="Z">$Dor_name$</span><br/><br/>

				例子：<span style="color:blue" class="Z">$name$($nameNo$)先生/女士，您好！您所入住的宿舍$Dor_name$即将...</span><br/>

			</p>
		</div>
	</EF:EFRegion>
</EF:EFPage>