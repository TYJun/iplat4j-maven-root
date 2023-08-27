<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
    body{
        width: 100%;
    }
    #k-header{
        margin-top: 0px;
    }
</style>
<EF:EFPage>
    <EF:EFRegion id="result" title="参数" fitHeight="true">
        <div style="margin-top: 20px;height: 300px;" class="panel">
                <EF:EFGrid blockId="result" autoDraw="no" >
                    <EF:EFColumn ename="RetDCid" cname="关联参数id" width="200" hidden="true"/>
                    <EF:EFColumn ename="Reid" cname="主键id" width="200" hidden="true" />
                    <EF:EFColumn ename="ReName" cname="参数项名称" width="200"/>
                    <EF:EFColumn ename="ReTag" cname="参数项Tag" width="200" />
                </EF:EFGrid>
        </div>
        <EF:EFInput ename="pid" cname="" width="1" hidden="true"/>
        <EF:EFInput ename="id" cname="" width="1" hidden="true"/>
        <EF:EFInput ename="type1" cname="" width="1" hidden="true"/>
        <EF:EFInput ename="DC_id" cname="" width="1" hidden="true"/>
    </EF:EFRegion>
</EF:EFPage>

<script type="text/javascript">
    $(function() {
        window.parent.close();
        $("#type1").css("display", "none");
        $("#id").css("display", "none");
        $("#pid").css("display", "none");
        $("#DC_id").css("display", "none");

        $("#SHUT").click(function () {
            window.parent['popData3Window'].close();
        })
        $("#REMOVE").click(function () {
            if ( $(".check-one").is(':checked')==false) {
                IPLAT.alert("请先选择一条记录进行删除关联");
            } else {
                var node = $('#MSDC0102');
                var str = $("#Reid").val();
                console.log(str);
                IPLAT.submitNode(node, 'MSDC0102', 'RemoveRelevance', {
                    onSuccess: function (eiInfo) {
                        //        IPLAT.alert(eiInfo.getMsg());
                        NotificationUtil("删除成功", "");
                        window.parent['popData3Window'].close();
                        window.parent['resultGrid'].dataSource.query(1);
                    }

                })
            }
        })
    });
</script>
