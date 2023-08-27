<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<body>
<!-- 标识申请选择弹出框  -->
<EF:EFPage>
		<EF:EFRegion id="inqu" title="查询条件" showClear="true">
			<div class="row">
				<EF:EFInput ename="applyNo" cname="申请单号："  class="col-md-8 col-sm-8"/>
				<EF:EFInput ename="iconName" cname="标识名称："  class="col-md-8 col-sm-8"/>
				<EF:EFInput ename="parentId" cname="" type="hidden" />
				<EF:EFButton cname="查询" ename="queryItem" layout="1" class="k-button"></EF:EFButton>
			</div>
		</EF:EFRegion>

		<EF:EFRegion id="result" title="标识申请列表" >
			<EF:EFGrid blockId="result" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
					   checkMode="single,row" readonly="true" rowNo="true" isFloat="true" serviceName="HIXX0102" methodName="query">
				<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
				<EF:EFColumn ename="applyNo" cname="申请单号" width="200" />
				<EF:EFColumn ename="iconName" cname="标识名称" width="200" />
				<EF:EFColumn ename="applyDeptName" cname="申请科室" width="200" />
				<EF:EFColumn ename="iconZhContent" cname="标识中文内容" width="100" />
				<EF:EFColumn ename="iconEnContent" cname="标识英文内容" width="100" />
				<EF:EFComboColumn ename="status" cname="状态" width="100" align="center">
					<EF:EFCodeOption codeName="wilp.hi.applyStatus"/>
				</EF:EFComboColumn>
			</EF:EFGrid>
		</EF:EFRegion>
</EF:EFPage>
<script type="text/javascript">
	$(function (){
		//调整查询条件的css样式
		let rowDiv = $('#inqu_status-0-itemName').parent().parent().parent();
		rowDiv.removeClass('col-md-4');
		rowDiv.attr('class','col-md-8 col-sm-8');
	})
</script>
</body>
