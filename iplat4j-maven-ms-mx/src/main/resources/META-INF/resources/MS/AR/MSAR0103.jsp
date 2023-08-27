<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<EF:EFPage>
   <div class="col-md-3">
       <EF:EFRegion id="R1" title="分类名称" fitHeight="true">
          <EF:EFTree id="tree01" textField="text" valueField="label"
             hasChildren="leaf" serviceName="MSPL02" methodName="queryTree">

          </EF:EFTree>
           <EF:EFInput ename="inqu_status-0-relationm" cname="" value="11"/>
           <EF:EFInput ename="inqu_status-0-parentId" cname="" />
           <EF:EFInput ename="parentIds" cname="" />
           <EF:EFInput ename="id" cname="" />
       </EF:EFRegion>

   </div>
   <div class="col-md-9">
       <EF:EFRegion id="result" title="参数项信息">
           <EF:EFGrid blockId="result"  serviceName="MSPL01" methodName="query" autoDraw="no">
               <EF:EFColumn ename="id" cname="主键" width="200" hidden="true" />
               <EF:EFColumn ename="name" cname="参数项名称" width="200" />
               <EF:EFColumn ename="tag" cname="参数项Tag" width="200" />
           </EF:EFGrid>
       </EF:EFRegion>
   </div>
</EF:EFPage>

<EF:EFWindow id="popData" url=" " lazyload="true" width="60%"
             height="70%">
</EF:EFWindow>

<script type="text/javascript">
    var datagrid = null;

    //EFGrid单击事件，获取选中行数据（定义全部变量）
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
        $("#parentIds").css("display", "none");
        $("#inqu_status-0-relationm").css("display", "none");
        $("#id").css("display", "none");
        //查询
        $("#SUBMIT").on("click", function(e) {
            var node = $('#MSAR0103');
            if ( $(".check-one").is(':checked')==false) {
                IPLAT.alert("请先选择一条记录进行修改");
                return false;
            }
            IPLAT.submitNode(node, 'MSPL01', 'updateTmsar', {
                onSuccess : function(eiInfo) {
                        NotificationUtil("绑定成功", "");
                        //关闭弹窗
                   window.parent.resultGrid.dataSource.page(1);
                   window.parent['popDataWindow'].close();


                },
                onFail : function(errorMsg, status, e) {
                        NotificationUtil("查询失败，原因[" + errorMsg + "]", "error");
                }
            });
        });

    });
</script>

