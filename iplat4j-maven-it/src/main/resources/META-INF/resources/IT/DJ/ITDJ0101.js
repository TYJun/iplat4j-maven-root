$(function () {
    alert(__ei.type)
    if (__ei.type == "cancellation"){
        $("#reqStaffId").attr("disabled",true)
        $("#reqStaffName").attr("disabled",true)
        $("#reqTelNum").attr("disabled",true)
        $("#reqDeptName").attr("disabled",true)
        $("#serveDeptName").attr("disabled",true)
        $("#reqSpotName").attr("disabled",true)
        $("#serveContent").attr("disabled",true)
        $("#building").attr("disabled",true)
        $("#remark").attr("disabled",true)
        $("#floor").attr("disabled",true)
        $("#invalid").show()
    }else if(__ei.type == "submit"){
        $("#reqStaffId").attr("disabled",true)
        $("#reqStaffName").attr("disabled",true)
        $("#reqTelNum").attr("disabled",true)
        $("#reqDeptName").attr("disabled",true)
        $("#serveDeptName").attr("disabled",true)
        $("#reqSpotName").attr("disabled",true)
        $("#serveContent").attr("disabled",true)
        $("#building").attr("disabled",true)
        $("#remark").attr("disabled",true)
        $("#floor").attr("disabled",true)
        $("#invalid").hide()
    } else {
        $("#invalid").hide()
    }

    // 树结构初始化赋值
    IPLAT.EFTreeInput.setAllFields( $("#parentId") , __ei.classifyId,__ei.classifyName);
    // 保存方法
    $("#SAVE").on("click", function (e) {
        $("#SAVE").attr("disabled",true);
        setTimeout(function(){$("#SAVE").attr("disabled",false);},5000);
        // 参数校验
        if ($("#type").val() == "cancellation"){
            if ($("#invalidReason").val() == ""){
                NotificationUtil("请填写作废理由", "error")
                return
            }
        }
        if ($("#reqDeptName").val() == ""){
            NotificationUtil("请填写需求科室", "error")
            return
        }
        if ($("#reqDeptNum").val() == ""){
            NotificationUtil("请选择需求科室，不要手填", "error")
            return
        }
        if ($("#parentId").val() == ""){
            NotificationUtil("请填写任务分类", "error")
            return
        }
        if ($("#serveDeptName").val() == ""){
            NotificationUtil("请填写服务科室", "error")
            return
        }
        if ($("#serveDeptNum").val() == ""){
            NotificationUtil("请选择填服务科室，不要手填", "error")
            return
        }
        if ($("#reqSpotName").val() == ""){
            NotificationUtil("请填写服务地点", "error")
            return
        }
        if ($("#reqSpotNum").val() == ""){
            NotificationUtil("请选择服务地点，不要手填", "error")
            return
        }
        if ($("#serveContent").val() == ""){
            NotificationUtil("请填写服务内容", "error")
            return
        }
        // 获取参数
        var eiInfo = new EiInfo();
        eiInfo.setByNode("info");
        EiCommunicator.send("ITDJ0101", "saveIndependentTask", eiInfo, {
            onSuccess : function(ei) {
                closeParentWindow()
                window.parent.location.reload()
            }
        })
    });

    $("#CLOSE").on("click", function (e) {
        closeParentWindow()
    });

    //数据加载(回显)
    console.log(__ei)
    $("#impFlag").val(__ei.impFlag)
    $("#reqStaffId").val(__ei.reqStaffNo);
    $("#reqStaffName").val(__ei.reqStaffName);
    $("#reqDeptNum").val(__ei.reqDeptNum);
    $("#reqDeptName").val(__ei.reqDeptName);
    $("#reqTelNum").val(__ei.reqTelNum);
    $("#serveDeptNum").val(__ei.serveDeptNum);
    $("#serveDeptName").val(__ei.serveDeptName);
    $("#building").val(__ei.building);
    $("#floor").val(__ei.floor);
    $("#reqSpotNum").val(__ei.spotNum);
    $("#reqSpotName").val(__ei.spotName);


    // -------------------自定义自动补全--------------
    /** 报修人工号自动补全 */
    $("#reqStaffId").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/ITDJ0101/queryPersonnelList", "person",["reqStaffId"]),
        dataTextField: "workNo",
        filter:"contains",
        template:'<span>#:workNo#-#:name#</span>',
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#reqStaffName").val(dataItem.name);		/* 报修人*/
            $("#reqDeptNum").val(dataItem.deptNum);		/* 报修科室*/
            $("#reqDeptName").val(dataItem.deptName);		/* 报修科室*/
            document.getElementById("reqStaffId").setAttribute('readonly',true);
            document.getElementById("reqStaffName").setAttribute('readonly',true);
        }
    });

    /** 报修人自动补全 */
    $("#reqStaffName").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/ITDJ0101/queryPersonnelList", "person",["reqStaffName"]),
        dataTextField: "name",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#reqStaffId").val(dataItem.workNo);		/* 报修人*/
            $("#reqDeptNum").val(dataItem.deptNum);		/* 报修科室*/
            $("#reqDeptName").val(dataItem.deptName);		/* 报修科室*/
            document.getElementById("reqStaffId").setAttribute('readonly',true);
            document.getElementById("reqStaffName").setAttribute('readonly',true);
        }
    });

    /** 报修电话自动补全 */
    $("#reqTelNum").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/ITDJ0101/selectOfficePhone", "phone",["reqTelNum"]),
        dataTextField: "telNum",
        filter:"contains",
        template:'<span>#:telNum#-#:deptName#</span>',
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#reqDeptNum").val(dataItem.deptNum);		/* 报修科室*/
            $("#reqDeptName").val(dataItem.deptName);		/* 报修科室*/
            $("#building").val(dataItem.building);
            $("#floor").val(dataItem.floor);
        }
    });

    /** 报修科室自动补全 */
    $("#reqDeptName").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/ITDJ0101/queryDeptList", "dept",["reqDeptName"]),
        dataTextField: "deptName",
        filter:"contains",
        /*template:'<span>#:deptNum#-#:deptName#</span>',*/
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#reqDeptNum").val(dataItem.deptNum);		/* 报修科室*/
            document.getElementById("reqDeptName").setAttribute('readonly',true);
        }
    });

    $("#serveDeptName").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/ITDJ0101/queryDeptList", "dept",["serveDeptName"]),
        dataTextField: "deptName",
        filter:"contains",
        /*template:'<span>#:deptNum#-#:deptName#</span>',*/
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#serveDeptNum").val(dataItem.deptNum);		/* 报修科室*/
            document.getElementById("serveDeptName").setAttribute('readonly',true);
        }
    });

    /** 楼自动补全 */
    $("#building").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/ITDJ0101/selectSpotBuildingName", "building", ["building"]),
        dataTextField: "building",
        filter:"contains",
        minLength :0
    });

    /** 层自动补全 */
    $("#floor").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/ITDJ0101/selectSpotFloorName", "floor",["building","floor"]),
        dataTextField: "floor",
        filter:"contains"
    });

    /** 地点自动补全 */
    $("#reqSpotName").kendoAutoComplete({
        dataSource: dataSourceConfig("/service/ITDJ0101/selectSpotRoomName", "room",["building","floor","reqSpotName"]),
        dataTextField: "spotName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#reqSpotNum").val(dataItem.spotNum);
            document.getElementById("building").setAttribute('readonly',true);
            document.getElementById("floor").setAttribute('readonly',true);
            document.getElementById("reqSpotName").setAttribute('readonly',true);
        }
    });

});

function closeParentWindow() {
    window.parent['popDataWindow'].close();
}

