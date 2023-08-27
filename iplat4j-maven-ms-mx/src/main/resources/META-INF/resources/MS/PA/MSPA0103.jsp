<%@ page import="com.baosight.iplat4j.core.web.threadlocal.UserSession" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<EF:EFPage>
    <div class="col-md-9">
        <EF:EFRegion id="inqu" title="查询条件" showClear="true">
            <div class="row">
                <EF:EFInput ename="inqu_status-0-login" cname="工号：" />
                <EF:EFInput ename="inqu_status-0-name" cname="姓名：" />
                <EF:EFInput ename="pid" cname="" />
            </div>
        </EF:EFRegion>
        <EF:EFRegion id="result" title="设备分类列表">
            <EF:EFGrid blockId="result" autoDraw="no"  checkMode="single" >
                <EF:EFColumn ename="id" cname="用户ID" width="100" hidden="true"/>
                <EF:EFColumn ename="workNo" cname="工号" width="100" disabled="true"/>
                <EF:EFColumn ename="name" cname="名字" width="100" disabled="true"/>
                <EF:EFColumn ename="contactTel" cname="手机号" width="100" disabled="true"/>
            </EF:EFGrid>
        </EF:EFRegion>
    </div>
</EF:EFPage>
<EF:EFWindow id="popData" url=" " lazyload="true" width="960px"
             height="700px">
</EF:EFWindow>
<script type="text/javascript">
    var datagrid = null;
    //EFGrid单击事件，获取选中行数据（定义全部变量）
    IPLATUI.EFGrid = {
        "result" : {
            onCheckRow : function(e) {
                console.log("1111111")
                console.log(e)
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
        }
    }
    $(function() {
        $("#pid").css("display", "none");
        //	QUERY
        //查询
        $("#QUERY").on("click", function(e) {
            resultGrid.dataSource.query(1);
        });
        //SUBMIT
        $("#SUBMIT").click(function() {
            if ( $(".check-one").is(':checked')==false) {
                IPLAT.alert("请先选择一条记录进行提交");
            }else {
                var pid=$("#pid").val();
                window.parent['popData2Window'].close();
                console.log(pid);
                window.parent.parent.frames.popData4(datagrid.workNo,datagrid.name,datagrid.contactTel,pid);
            }
        });
    });
</script>