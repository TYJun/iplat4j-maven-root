<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
    .two{
        margin-top: 50px;
    }
</style>
<EF:EFPage>
    <EF:EFRegion id="result" title="信息" fitHeight="true">
        <div>
            <EF:EFInput ename="monitorName" cname="视频名称：" bindId="monitorName"
                        colWidth="5" ratio="4:8" type="text" required="true"
                        placeholder="请输入视频名称" />
            <EF:EFInput ename="monitorCode" cname="视频编号：" bindId="monitorCode"
                        colWidth="5" ratio="4:8" type="text" required="true"
                        placeholder="请输入视频编号"  oninput="value=value.replace(/[^\d]/g,'')" />
            <EF:EFInput ename="url" cname="url:" bindId="url"
                        colWidth="5" ratio="4:8" type="text"
                        placeholder="请输入url地址"/>
        </div>
        <EF:EFInput ename="type" cname="" width="1" />
        <EF:EFInput ename="id" cname="" width="1" />
        <EF:EFInput ename="parentId" cname="" width="1" />
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="60%"
             height="70%">
</EF:EFWindow>
<script type="text/javascript">
    var datagrid = null;

    //EFGrid单击事件，获取选中行数据（定义全部变量）
    IPLATUI.EFGrid = {
        "result" : {
            checkMode:"single",
            onCheckRow : function(e) {
                if (!e.fake) {
                    datagrid = null;
                    var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
                    datagrid = model;
                }else{
                    datagrid = null;
                    var grid = e.sender, model = e.model, $tr = e.tr, row = e.row;
                    datagrid = model;
                }
            }
        },

    }
    //弹窗
    function popData(id) {
        var url = IPLATUI.CONTEXT_PATH + "/web/MSSP0101?initLoad&id=" + id;
        var popData = $("#popData");
        popData.data("kendoWindow").setOptions({
            open : function() {
                popData.data("kendoWindow").refresh({
                    url : url,
                });
            }
        });
        // 打开工作流节点人员选择弹窗
        popDataWindow.open().center();
    }
    $(function() {
        $("#type").css("display", "none");
        $("#id").css("display", "none");
        $("#parentId").css("display", "none");
        var s = $("#type").val();
        var parentId = $("#parentId").val();
        //通过点击查看弹窗是隐藏提交按钮
        if (s == "addType") {
            $("#VIEW").css("display", "none");
            //$("#CANCEL").css("display", "none");
        }
        if (parentId != "root") {
            $(".two").css("display", "none");
            $("#VIEW").css("display", "none");
        }
        $("#SUBMIT").on("click", function (e){
            $("#SUBMIT").attr("disabled",true);
            console.log($("#parentId").val());
            var node = $('#MSSP0101');
            var str = $("#monitorName").val().replace(/\s+/g,"");
            var str1 = $("#monitorCode").val().replace(/\s+/g,"");
            if(str == null || str ==""){
                $("#SUBMIT").attr("disabled",false);
                IPLAT.alert("视频名称不可为空")
            }else if(str1 == null || str1 ==""){
                $("#SUBMIT").attr("disabled",false);
                IPLAT.alert("视频编号不可为空")
            }else{
                IPLAT.submitNode(node, 'MSSP01', 'insert', {
                    onSuccess : function(eiInfo) {
                        if(eiInfo.getStatus()==0) {
                            IPLAT.alert(eiInfo.getMsg());
                            $("#SUBMIT").attr("disabled",false);
                        }else {
                            window.parent.resultGrid.dataSource.page(1);
                            window.parent['popDataWindow'].close();
                            window.parent.frames.reload();
                        }

                    },
                    onFail : function(errorMsg, status, e) {
                        NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
                    }
                });
            }

        });
        $("#CANCEL").click(function() {
            window.parent['popDataWindow'].close();
        })
        $("#VIEW").click(function() {
            var id = $("#id").val();
            popData(id);
        })
    });
</script>