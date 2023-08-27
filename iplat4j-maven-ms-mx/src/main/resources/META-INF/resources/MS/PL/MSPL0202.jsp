<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
	<EF:EFRegion id="result" title="信息" fitHeight="true">
        <EF:EFGrid blockId="result" autoDraw="no" checkMode="single">
            <EF:EFColumn ename="groupId" cname="主键" width="200" hidden="true" />
            <EF:EFColumn ename="usergroupId" cname="分类名称" width="200" disabled="true"/>
            <EF:EFColumn ename="groupCname" cname="排序编号" width="200" disabled="true"/>
        </EF:EFGrid>
	</EF:EFRegion>
    <EF:EFInput ename="type" cname="" width="1" />
    <EF:EFInput ename="id" cname="" width="1" />
    <EF:EFInput ename="parentId" cname="" width="1" />
</EF:EFPage>
<script type="text/javascript">
    $(function() {
        $("#type").css("display", "none");
        $("#id").css("display", "none");
        $("#parentId").css("display", "none");
        $("#DELETE").click(function() {
            var node = $('#MSPL0202');
            var str = $("#classifyName").val();
            IPLAT.submitNode(node,'MSPL0202', 'delete', {
                onSuccess : function(eiInfo) {
                        window.parent['popDataWindow'].close();
                    }
                });
        });
	});
</script>

