<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="EF" tagdir="/WEB-INF/tags/EF" %>
<style>
    .picMaintain {
        margin: 2px;
    }
    .form-group .k-input {
        padding: 0 0;
    }
</style>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<EF:EFPage>
    <EF:EFRegion id="result" title="登记" fitHeight="false">
        <div class="row">
            <EF:EFInput ename="inqu_status-0-id" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-type" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-projectId" cname="主键" type="hidden"/>
            <EF:EFInput ename="inqu_status-0-recCreateTime" cname="制单时间" colWidth="5" ratio="4:8" readonly="true"
                        type="hidden"/>
            <EF:EFInput ename="inqu_status-0-recCreator" cname="制单人" colWidth="5" ratio="4:8" readonly="true"
                        type="hidden"/>
        </div>
        <div class="row">
            <EF:EFInput ename="inqu_status-0-iconName" cname="标识名称" colWidth="5" ratio="4:8" required="true"/>
            <EF:EFInput ename="inqu_status-0-iconZhContent" cname="标识中文内容" colWidth="5"
                        ratio="4:8"  required="false"/>
        </div>
        <div class="row">
    <EF:EFInput ename="inqu_status-0-iconEnContent" cname="标识英文内容" colWidth="5"
                ratio="4:8"  required="false"/>
            <EF:EFTreeInput ename="inqu_status-0-classifyId" cname="标识分类"   colWidth="5" ratio="4:8"  readonly="true" required="true"
                            serviceName="HIBZ01" methodName="queryTree" textField="text"
                            valueField="label" hasChildren="leaf"
                            root="{label: 'root',text: '分类'}" popupTitile="上级分类" clear ="true" >
            </EF:EFTreeInput>
        </div>
        <div class="row">
<%--            <EF:EFPopupInput ename="inqu_status-0-fixedPlace" cname="安装地方" colWidth="5" ratio="4:8" readonly="true" popupWidth = "550"--%>
<%--                             popupType="ServiceGrid" popupTitle="安装地方" required="true" serviceName="HISQ0101" methodName="querySpot" resultId="spot" autofit="true"--%>
<%--                             valueField="spotNum" textField="spotName" backFillFieldIds="spotName" backFillColumnIds="spotName" columnEnames="building,floor,spotName" columnCnames="楼,层,地点"/>--%>
<%--            <EF:EFInput ename="inqu_status-0-building" colWidth="5" ratio="4:8" cname="楼"  center="true">--%>

<%--            </EF:EFInput>--%>

<%--            <EF:EFInput ename="inqu_status-0-spotId" type="hidden" cname="地点id" />--%>
<%--            <EF:EFInput ename="inqu_status-0-spotCode" type="hidden" cname="地点编码" />--%>
<%--            <EF:EFInput ename="inqu_status-0-spotName" type="hidden" cname="地点名称" />--%>
    <div class="col-md-10">
        <div class="form-group">
            <label class="col-md-2 control-label">
                <span style="color:red">*</span>安装地方
            </label>
            <div class="col-md-2" >
                <input id="building" placeholder="楼" onclick="showAll('building')">
            </div>
            <div class="col-md-2" style="padding-left:0px;margin-left:0px;" >
                <input id="floor" placeholder="层" onclick="showAll('floor')">
            </div>

        </div>
    </div>
        </div>

        <div class="row">
<%--            <EF:EFInput ename="inqu_status-0-floor" colWidth="5" ratio="4:8" cname="层" >--%>
<%--            </EF:EFInput>--%>
            <div class="col-md-10">
               <div class="form-group">
                  <label class="col-md-2 control-label">
                  </label>
             <div class="col-md-4">
                  <input id="spotNum"  placeholder="安装地方" type="hidden">
                  <input id="spotName"  placeholder="安装地方" onclick="showAll('spotName')">
             </div>
                   <EF:EFInput ename="inqu_status-0-iconAmount" cname="数量" colWidth="6"
                               type="text"  required="true"/>
               </div>
            </div>
        </div>



        <div class="row">
            <EF:EFInput ename="inqu_status-0-spotDesc" cname="安装地点说明" colWidth="5"
                        ratio="4:8" type="text" required="true"/>

            <EF:EFDatePicker ename="inqu_status-0-installDate" cname="安装日期" type="hidden"
                             format="yyyy-MM-dd" parseFormats="['yyyy-MM-dd']" readonly="true"
                             bindId="installDate" colWidth="5" ratio="4:8"/>

        </div>

        <div class="row">
            <EF:EFInput ename="inqu_status-0-applyReason" cname="申请理由" colWidth="5"
                        ratio="4:8" type="text" required="true"/>

<%--            <EF:EFPopupInput ename="inqu_status-0-managerName" cname="管理员" readonly="true"--%>
<%--                             popupTitle="员工列表" popupType="ServiceGrid" serviceName="HISQ0101"--%>
<%--                             methodName="queryAdmin" resultId="iconAdmin" valueField="workNo"--%>
<%--                             textField="name" colWidth="5" ratio="4:8"--%>
<%--                             columnEnames="workNo,name" columnCnames="员工工号,员工姓名" type="hidden"/>--%>


        </div>
        <div class="row">
            <EF:EFPopupInput ename="inqu_status-0-applyDeptName" cname="申请科室"
                             popupTitle="标识所属部门" popupType="ServiceGrid" required="true" serviceName="HISQ0101"
                             methodName="queryIconCostNum" resultId="iconDept" readonly="true"
                             valueField="iconDeptNum" textField="iconDeptName" colWidth="5"
                             ratio="4:8" columnEnames="iconDeptNum,iconDeptName"
                             columnCnames="科室编码,科室名称"/>


        </div>
        <div class="row">

        <EF:EFInput ename="inqu_status-0-remark" cname="备注" colWidth="5"
                    ratio="4:8" type="text" required="false"/>

        </div>

<%--        <div class="row">--%>

<%--            <EF:EFInput ename="tixing" cname="提醒" colWidth="5" value="xxxxxx"--%>
<%--                        ratio="4:8" type="text" required="false" readonly="true"/>--%>

<%--        </div>--%>

        <div class="button-region" id="buttonDiv">
            <EF:EFButton cname="确定" ename="SAVEPR" layout="0"></EF:EFButton>
            <EF:EFButton cname="取消" ename="CANCEL" layout="0"></EF:EFButton>
        </div>
    </EF:EFRegion>

    <!-- 页面分页 -->
        <div title="附件内容">
            <EF:EFGrid blockId="resultD" needAuth="true" autoDraw="no" autoBind="true" autoFit="true"
                       checkMode="single,row" readonly="true" rowNo="true" isFloat="true" queryMethod="queryFile">
                <EF:EFColumn ename="id" cname="id" width="100" hidden="true"/>
                <EF:EFColumn ename="relationId" cname="relationId" width="100" hidden="true"/>
                <EF:EFColumn ename="docId" cname="附件id" width="200" hidden="true"/>
                <EF:EFColumn ename="filePath" cname="附件路径" width="200" hidden="true"/>
                <EF:EFColumn ename="fileName" cname="附件名称" width="200"/>
                <EF:EFColumn ename="fileSize" cname="附件大小" width="100"/>
                <EF:EFColumn ename="remark" cname="附件说明" width="200"/>
                <EF:EFColumn ename="recCreator" cname="上传人" width="200"/>
                <EF:EFColumn ename="recCreateTime" cname="上传时间" width="200"/>
                <EF:EFColumn ename="fileStatus" cname="上传时间" width="200"/>
            </EF:EFGrid>
        </div>

    <!-- 附件上传窗口 -->
    <EF:EFWindow id="fileChoose" url="" lazyload="true" refresh="true" width="40%" height="30%" title="附件上传">
        <EF:EFRegion id="upload" title="文件上传">
            <EF:EFUpload ename="contentFile" docTag="co_file" path="Content"/>
        </EF:EFRegion>
    </EF:EFWindow>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mt_common.js">
    </script>
</EF:EFPage>

<script type="text/javascript">
    /** 楼自动补全 */
    $("#building").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/HISQ0101/selectSpotBuildingName", "building", ["building"]),
        dataTextField: "building",
        filter:"contains",
        minLength :0
    });

    /** 层自动补全 */
    $("#floor").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/HISQ0101/selectSpotFloorName", "floor",["building","floor"]),
        dataTextField: "floor",
        filter:"contains"
    });

    /** 地点自动补全 */
    $("#spotName").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/HISQ0101/selectSpotRoomName","room",["building","floor","spotNum","spotName"]),
        dataTextField: "spotName",

        filter:"contains",
        select:function(e){
            var dataItem = this.dataItem(e.item.index());
            $("#spotName").val(dataItem.spotName);
            $("#spotNum").val(dataItem.spotNum);
        }
    });

    /** kendoAutoComplete的dataSource配置*/
    function dataSourceConfig(url,blockId,param){
        return new kendo.data.DataSource({
            serverFiltering: true,//每次输入重新获取数据
            transport: {
                read: {
                    //url: IPLATUI.CONTEXT_PATH +"/service/MTRE01/selectOfficePhone",
                    url:IPLATUI.CONTEXT_PATH + url,
                    type: 'POST',
                    dataType: "json",
                    contentType: "application/json"
                },
                parameterMap: function (options, operation) {
                    var info = new EiInfo();
                    if(param != null){
                        for(var index in param){ info.set(param[index],$("#"+param[index]).val()); }
                    }
                    return  info.toJSONString();
                }
            },
            schema: {
                data: function (response) {
                    ajaxEi = EiInfo.parseJSONObject(response);
                    var block = ajaxEi.getBlock(blockId);
                    var array = new Array();
                    for(var index in  block.getRows()){
                        array.push(block.getMappedObject( block.getRows()[index]));
                    }
                    return array;
                }
            }
        })
    }

    /**
     * kendoAutoComplete查询所有数据
     * @param selector
     */
    function showAll(selector){
        // alert("1111")
        var autocomplete = $("#"+selector).data("kendoAutoComplete");
        autocomplete.search("");
    }
    /**
     * 回显指定下拉选项数据
     * @param selector
     */
    function echoOne(selector){
        var autocomplete = $("#"+selector).data("kendoAutoComplete");
        var options = autocomplete.options;
        var rows = options.dataSource._data;
        var value = autocomplete.value();
        if(rows !=null && rows.length > 0 && value.length > 0){
            var index = findIndex(rows,value,selector);
            if(index > -1){
                autocomplete.select(parseInt(index));
                var dataItem = autocomplete.dataItem(parseInt(index));
                echoSelect(selector, dataItem);
            }
        }
    }



</script>