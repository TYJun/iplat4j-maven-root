<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">
    body{
        width: 100%;
    }
    #SUBMIT{
        position:fixed;
        right: 17%;
        bottom: 10px;
    }
    #SHUT{
        position:fixed;
        right: 10%;
        bottom: 10px;
    }
    #VIEW{
        position:fixed;
        right: 5%;
        bottom: 10px;
    }
    #REMOVE{
        position:fixed;
        right: 10%;
        bottom: 10px;
    }
    div#inqu {
        margin-top: 70px;
    }
</style>
<EF:EFPage>
    <EF:EFRegion id="result" title="信息" fitHeight="true">
        <div style="margin-top: 50px;height: 110px;" class="panel1" >
            <div style="height: 50px; line-height: 50px">
                <EF:EFInput ename="code" cname="设备代码："
                        bindId="code" colWidth="5" ratio="4:8" type="text" required="true" placeholder="请输入设备代码："/>
                <EF:EFInput ename="name" cname="设备名称："
                            bindId="name" colWidth="5" ratio="4:8" type="text" required="true" placeholder="请输入设备名称："/>
                <EF:EFInput ename="weight" cname="权重："
                            bindId="weight" colWidth="5" ratio="4:8" type="text" required="true" placeholder="请输入权重："
                            oninput="value=value.replace(/[^\d]/g,'')"/>
            </div>
            <div style="margin-top: 20px;height: 300px;" class="panel2">
                <EF:EFRegion id="inqu" title="查询条件" showClear="true">
                        <EF:EFInput ename="inqu_status-0-param" cname="参数类型Tag：" />
                        <EF:EFInput ename="inqu_status-0-parentId" cname="" hidden="true"/>
                </EF:EFRegion>
                <EF:EFGrid blockId="result" autoDraw="no" >
                    <EF:EFColumn ename="RetDCid" cname="关联参数id" width="200" hidden="true"/>
                    <EF:EFColumn ename="Reid" cname="主键id" width="200" hidden="true" />
                    <EF:EFColumn ename="ReName" cname="参数项名称" width="200" disabled="true"/>
                    <EF:EFColumn ename="Retag" cname="参数项Tag" width="200" disabled="true"/>
                </EF:EFGrid>
            </div>
        </div>
        <EF:EFInput ename="pid" cname="" width="1" hidden="true"/>
        <EF:EFInput ename="id" cname="" width="1" hidden="true"/>
        <EF:EFInput ename="type1" cname="" width="1" hidden="true"/>
        <EF:EFInput ename="DC_id" cname="" width="1" hidden="true"/>
    </EF:EFRegion>
</EF:EFPage>
<EF:EFWindow id="popData3" url=" " lazyload="true" width="60%"
             height="80%">
</EF:EFWindow>

<script type="text/javascript">
    function popData(DC_id, type1,pid) {
        var url = IPLATUI.CONTEXT_PATH + "/web/MSDC0101?initLoad&id=" + DC_id
            + "&type1=" + type1+"&pid=" + pid;
        var popData = $("#popData");
        popData.data("kendoWindow").setOptions({
            open : function() {
                popData.data("kendoWindow").refresh({
                    url : url,
                });
            }
        });
    }
    function popData3(DC_id, type1,pid) {
        console.log("11111111111111111111111111111111");
        var url = IPLATUI.CONTEXT_PATH + "/web/MSDC0102?initLoad&id=" + DC_id
            + "&type1=" + type1+"&pid=" + pid;
        var popData3 = $("#popData3");
        popData3.data("kendoWindow").setOptions({
            open : function() {
                popData3.data("kendoWindow").refresh({
                    url : url,
                });
            }
        });
        // 打开工作流节点人员选择弹窗
        popData3Window.open().center();
    }
    $(function() {
   //      $(".panel1").css("display", "block");
        //-------------------------------------暂时不使用
         $("#REMOVE").css("display", "none");
       // $(".panel3").css("display", "none");//block
        //-------------------------------------
       // control-label
       //   $(".form-group").css("display", "none");
       //   $(".col-xs-8").css("display", "none");
       //   $(".control-label").css("display", "none");
        $("#type1").css("display", "none");
        $("#id").css("display", "none");
        $("#pid").css("display", "none");
        $("#DC_id").css("display", "none");
        $("#superItemTypeNum").css("display", "none");
        $("#parentId").css("display", "none");
        $("#tree01_textField").attr("value", $("#superItemTypeNum").val());
        var s = $("#type1").val();
        if (s === "see") {
            $("#SUBMIT").css("display", "none");
        }
        $("#SUBMIT").click(function() {
            $("#SUBMIT").attr("disabled",true);
            var node = $('#MSDC0101');
            var str = $("#code").val();
            var str1 = $("#name").val();
            var str2 = $("#type").val();
            var str3 = $("#weight").val();
            var str4 = $("#t_area_classify_id").val();
            if (str == null || str == "") {
                $("#SUBMIT").attr("disabled",false);
                IPLAT.alert("设备代码不可为空")
            }else if (str1 == null || str1 == "") {
                $("#SUBMIT").attr("disabled",false);
                IPLAT.alert("设备名称不可为空")
            }else if (str3 == null || str3 == "") {
                $("#SUBMIT").attr("disabled",false);
                IPLAT.alert("权重不可为空")
            }else{
                IPLAT.submitNode(node, 'MSDC0101', 'insert', {
                    onSuccess : function(inInfo) {
                        if (inInfo.getMsg()=="根节点不能新建增加") {
                            inInfo.setMsg("增加失败设备代码不可重复");
                            IPLAT.alert(inInfo.getMsg());
                        }else if (inInfo.getMsg()=="增加失败") {
                            inInfo.setMsg("增加失败设备代码不可重复");
                            IPLAT.alert(inInfo.getMsg());
                        }else {
                            NotificationUtil("增加成功", "");
                            window.parent['popDataWindow'].close();
                            window.parent['resultGrid'].dataSource.query(1);
                        }
                    },
                    onFail : function(errorMsg, status, e) {
                        NotificationUtil("修改失败，原因[" + errorMsg + "]", "error");
                    }
                });
            }
        });
        $("#SHUT").click(function() {
            window.parent['popDataWindow'].close();
        })

        //查询
        $("#QUERY").on("click", function(e) {
            resultGrid.dataSource.query(1);
        });
        //----------------------------------------------------暂时不用
        // var VIEWState=true;
        // //参数窗口
        // $("#VIEW").on("click", function(e) {
        //     if (VIEWState==true){
        //         $("#REMOVE").css("display", "block");
        //         $(".panel3").css("display", "block");
        //         $(".panel2").css("display", "none");
        //         $("#SUBMIT").css("display", "none");
        //         $("#SHUT").css("display", "none");
        //         return VIEWState=false;
        //     }
        //     if (VIEWState==false){
        //         $("#REMOVE").css("display", "none");
        //         $(".panel3").css("display", "none");
        //         $(".panel2").css("display", "block");
        //         $("#SUBMIT").css("display", "block");
        //         $("#SHUT").css("display", "block");
        //         return VIEWState=true;
        //     }
        // });
        //----------------------------------------------------------------------------
        $("#VIEW").on("click", function(e) {
            var id = $("#DC_id").val();
            popData3(id,"edit","");
        });
    });
</script>
