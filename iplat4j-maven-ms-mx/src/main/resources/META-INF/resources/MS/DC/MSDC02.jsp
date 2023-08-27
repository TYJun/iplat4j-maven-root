<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
    button#SUBMIT {
        position: fixed;
        left: 85%;
        top: 91%;
    }
</style>
<EF:EFPage>
    <div class="col-md-3" style="height: 200px; line-height: 50px;width: 265px">
        <EF:EFRegion id="R1" title="选择你要移动到的分组" fitHeight="true" >
            <EF:EFTree id="tree01" textField="text" valueField="label"
                       hasChildren="leaf" serviceName="MSDC03" methodName="queryTree">
            </EF:EFTree>
            <EF:EFInput ename="inqu_status-0-parentId" cname="" hidden="true" id="pid"/>
        </EF:EFRegion>
    </div>
    <EF:EFInput ename="id" cname="" width="1" hidden="true"/>
</EF:EFPage>

<EF:EFWindow id="popData" url=" " lazyload="true" width="60%"
             height="70%">
</EF:EFWindow>

<script type="text/javascript">
    $(function() {
        $("#id").css("display", "none");

    $("#SUBMIT").click(function() {
        var node = $('#MSDC02');
        var str = $("#id").val();
        var str2 = $("#inqu_status-0-parentId").val();
        console.log(str2);
        if (str == null || str == "" && str2 == null || str2 == "" || str2=='root') {
            IPLAT.alert("不可以不选择分组")
        } else {
            IPLAT.submitNode(node, 'MSDC02', 'insert2', {
                onSuccess: function (inInfo) {
                    IPLAT.alert(inInfo.getMsg());
                    NotificationUtil("修改成功", "");
                    window.parent['popData2Window'].close();
                    window.parent['resultGrid'].dataSource.query(1);
                },
                onFail: function (Msg, status, e) {
                    NotificationUtil("修改失败，原因[" + Msg + "]", "error");
                }
            });
        }
    })
    });
</script>

