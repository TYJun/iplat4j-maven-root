<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>库存明细信息</title>
<EF:EFPage>
	<EF:EFRegion id="inqu" title="查看库存明细">
		 <div class="row">
			<EF:EFInput ename="inqu_status-0-wareHouseNo" cname="仓库号"
				colWidth="5" ratio="4:8" type="text" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-wareHouseName" cname="仓库名称"
				colWidth="5" ratio="4:8" type="text" readonly="true"/>
			<EF:EFInput ename="inqu_status-0-matNum" cname="物资编码" type="hidden"/>
		</div> 
	</EF:EFRegion>
	<EF:EFRegion id="result" title="库存存量信息">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" readonly="true">
			<EF:EFColumn ename="batchNo" cname="批次号"  width="100" align="center"/>
			<EF:EFColumn ename="matNum" cname="物资编码" width="100" align="center"/>
			<EF:EFColumn ename="matName" cname="物资名称" width="100" align="center"/>
			<EF:EFColumn ename="matTypeName" cname="物资分类名称" width="100" align="center"/>
			<EF:EFColumn ename="matModel" cname="物资型号" width="100" align="center"/>
			<EF:EFColumn ename="matSpec" cname="物资规格" width="100" align="center"/>
			<EF:EFColumn ename="unit" cname="单位" width="60" hidden="true" />
			<EF:EFColumn ename="unitName" cname="计量单位" width="60" align="center"/>
			<EF:EFColumn ename="enterNum" cname="库存量" width="70" align="center"/>
			<EF:EFColumn ename="enterPrice" cname="库存单价" width="70" align="center"/>
			<EF:EFColumn ename="enterAmount" cname="库存总价" width="70" align="center"/>
			<EF:EFColumn  ename="enterTime" cname="入库时间" width="100" align="center"/>
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
	$(function() {
		//关闭
		$("#CLOSE").on("click", function(e) {
			window.parent['popDataWindow'].close();
		});
		
	})
</script>