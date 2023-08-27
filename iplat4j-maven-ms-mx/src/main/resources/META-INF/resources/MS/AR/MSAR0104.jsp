<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
    body{
        width: 100%;
    }
</style>
<EF:EFPage>
    <EF:EFRegion id="result" title="参数" fitHeight="true">
        <div style="margin-top: 20px;height: 300px;" class="panel">
                <EF:EFGrid blockId="result" autoDraw="no" >
                    <EF:EFColumn ename="id" cname="id" width="200" hidden="true"/>
                    <EF:EFColumn ename="name" cname="参数项名称" width="200"/>
                    <EF:EFColumn ename="tag" cname="参数项Tag" width="200" />
                </EF:EFGrid>
        </div>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFInput ename="id" cname="" width="1" hidden="true"/>
<script type="text/javascript">

    IPLATUI.EFGrid = {
        "result" : {
            onCheckRow : function(e) {
                if (!e.fake) {
                    datagrid = null;
                    var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
                    datagrid = model;
                    $("#id").val(datagrid.id);
                }
            }
        }
    }
    $(function() {
        $("#id").css("display", "none");
        $("#REMOVE").click(function () {
            if ( $(".check-one").is(':checked')==false) {
                IPLAT.alert("请先选择一条记录进行解除关联");
            } else {
                var node = $('#MSAR0104');
                IPLAT.submitNode(node, 'MSAR0104', 'RemoveRelevance', {
                    onSuccess: function (eiInfo) {
                        NotificationUtil("解除成功", "");
                        window.parent.resultGrid.dataSource.page(1);
                        window.parent['popData3Window'].close();
                    }

                })
            }
        })
    });
</script>
