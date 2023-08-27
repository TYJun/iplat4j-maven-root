<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
let baseUrl = "${ctx}"
</script>

<link type="text/css" rel="styleSheet"  href="${ctx}/MX/PS/MXPS01.css" />
<script src="${ctx}/MX/PS/echarts.min.js"></script>

<EF:EFPage>
    <div class="col-md-3">
        <EF:EFRegion id="R1" title="分类名称" fitHeight="true">
            <EF:EFTree id="tree01" textField="text" valueField="label"
                       hasChildren="leaf" serviceName="MSPL02" methodName="queryTree">
            </EF:EFTree>
        </EF:EFRegion>
    </div>
    <div class="col-md-9">
        <EF:EFRegion id="inqu" title="查询条件" showClear="true">
            <div class="row">
                <EF:EFInput ename="inqu_status-0-device" cname="设备"/>
                <EF:EFInput ename="inqu_status-0-name_" cname="参数"/>
                <EF:EFInput ename="inqu_status-0-tag" cname="参数TAG"/>
                <EF:EFInput ename="inqu_status-0-parentId" cname=""/>
            </div>
        </EF:EFRegion>
        <EF:EFRegion id="result" title="参数项信息">
            <EF:EFGrid blockId="result" autoDraw="no">
                <EF:EFColumn ename="id" cname="主键" width="20" hidden="true"/>
                <EF:EFColumn ename="classifyId" cname="区域编号" width="20" hidden="true"/>
                <EF:EFColumn ename="classifyName" cname="区域名称" width="150" disabled="true"/>
                <EF:EFColumn ename="name" cname="设备" width="150" disabled="true"/>
                <EF:EFColumn ename="tagName" cname="参数名称" width="150" disabled="true"/>
                <EF:EFColumn ename="tag" cname="参数TAG" width="150" disabled="true"/>
                <EF:EFColumn ename="rValue" cname="实时值" width="100" disabled="true"/>
                <EF:EFColumn ename="grade" cname="报警等级" width="80" disabled="true"/>
                <EF:EFColumn ename="unitName" cname="单位" width="50" disabled="true"/>
                <EF:EFColumn ename="dimensionName" cname="量纲" width="50" disabled="true"/>
                <EF:EFColumn ename="scope" cname="正常值范围" width="80" disabled="true"/>
                <EF:EFColumn ename="lowerLower" cname="低低报警" width="20" hidden="true" disabled="true"/>
                <EF:EFColumn ename="upperUpper" cname="高高报警" width="20" hidden="true" disabled="true"/>
            </EF:EFGrid>
        </EF:EFRegion>
    </div>
    <div id="container">
        <div id="chart"></div>
        <a class="history" onclick="changeChart('1')">查看历史</a>
        <div class="close" onclick="onClose('1')">X</div>
    </div>
    <div id="container2">
        <div>
            <input id="startDate" type="datetime-local" value="2021-09-14T14:22" /><span class="data">~</span><input id="endDate" type="datetime-local" value="2021-09-14T14:22"/><button onclick="doSearch()">查询</button>
        </div>
        <div id="chart2"></div>
        <a class="history" onclick="changeChart('2')">查看实时</a>
        <div class="close" onclick="onClose('2')">X</div>
    </div>
</EF:EFPage>

<EF:EFWindow id="popData" url=" " lazyload="true" width="60%"
             height="70%">
</EF:EFWindow>

<script type="text/javascript">
    var datagrid = null;

    //EFGrid单击事件，获取选中行数据（定义全部变量）
    IPLATUI.EFGrid = {
        "result": {
            onCheckRow: function (e) {
                if (!e.fake) {
                    datagrid = null;
                    var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
                    datagrid = model;
                }else{
                    datagrid = null;
                    var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
                    datagrid = model;
                }
            },
            onSuccess: handleSuccess,
            onRowDblClick: handleRowDblClick
        }
    }

    //弹窗
    function popData(id, type) {
        var url = IPLATUI.CONTEXT_PATH + "/web/BMBD0101?initLoad&id=" + id
            + "&type=" + type;
        var popData = $("#popData");
        popData.data("kendoWindow").setOptions({
            open: function () {
                popData.data("kendoWindow").refresh({
                    url: url,
                });
            }
        });
        // 打开工作流节点人员选择弹窗
        popDataWindow.open().center();
    }

    $(function () {

        //一键处理科室名称简拼按钮
        $("#DEPTNAMEJP").on("click", function (e) {
            EiCommunicator.send("BMBD01", "toJpDeptName", new EiInfo(), {
                onSuccess: function (ei) {
                    IPLAT.alert("操作成功");
                }
            })
        });

        //查询
        $("#QUERY").on("click", function (e) {
            resultGrid.dataSource.query(1);
        });

        //重置按钮
        $("#REQUERY").on("click", function (e) {
            var parentId = $("#inqu_status-0-parentId").val();
            document.getElementById("inqu-trash").click();
            $("#inqu_status-0-parentId").val(parentId);
            resultGrid.dataSource.query(1);
        });

        // 处理默认时间
        let now = new Date();
        let startTime = now.getFullYear() + "-"
            + ((now.getMonth() + 1) < 10 ? "0" + (now.getMonth() + 1) : (now.getMonth() + 1)) + "-"
            + (now.getDate() < 10 ? "0" + now.getDate() : now.getDate()) + "T00:00";
        let endTime = now.getFullYear() + "-"
            + ((now.getMonth() + 1) < 10 ? "0" + (now.getMonth() + 1) : (now.getMonth() + 1)) + "-"
            + ((now.getDate() + 1) < 10 ? "0" + (now.getDate() + 1) : (now.getDate() + 1)) + "T00:00";
        $("#startDate").val(startTime);
        $("#endDate").val(endTime);
    });
</script>

