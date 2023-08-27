<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<title>仓库管理</title>
<EF:EFPage title="仓库管理">
	<EF:EFRegion id="inqu" title="查询条件" showClear="true">
		<div class="row">
			<EF:EFInput ename="inqu_status-0-wareHouseName" cname="仓库名称" />
			<EF:EFSelect ename="inqu_status-0-freezeFlag" cname="是否冻结"
				width="100">
				<EF:EFOption label="请选择" value="" />
				<EF:EFOption label="是" value="Y" />
				<EF:EFOption label="否" value="N" />
			</EF:EFSelect>
		</div>
	</EF:EFRegion>
	<EF:EFRegion id="result" title="仓库信息">
		<EF:EFGrid blockId="result" autoDraw="no" autoBind="true" checkMode="single,row" readonly="true">
			<EF:EFColumn ename="id" cname="id" width="100" hidden="true" />
			<EF:EFColumn ename="wareHouseNo" cname="仓库号" width="100" displayType="url" align="center" />
			<EF:EFColumn ename="wareHouseName" cname="仓库名称" width="200" align="center"/>
			<EF:EFComboColumn ename="wareHouseType" cname="仓库类型" width="100" align="center" >
				<EF:EFCodeOption codeName="wilp.ci.wareHouseType"/>
			</EF:EFComboColumn>
			<EF:EFColumn ename="freezeFlag" cname="冻结" width="100" align="center"/>
			<EF:EFColumn ename="recCreator" cname="创建人" width="100" align="center" />
			<EF:EFColumn ename="recCreateTime" cname="创建时间" width="100" align="center" />
		</EF:EFGrid>
	</EF:EFRegion>
</EF:EFPage>
<!-- 新增/编辑/保存弹窗 -->
<EF:EFWindow id="popData" title="仓库信息" url=" " lazyload="true" width="50%" height="60%"></EF:EFWindow>