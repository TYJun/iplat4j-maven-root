$(function (){
    var managementDeptNum= $("#inqu_status-0-managementDeptNum").val();
    if(isEmpty(managementDeptNum)){
        $("#inqu_status-0-managementDeptNum").attr("readonly",false)
    }

    // 查询
    $("#QUERY").on("click", function (e) {
        resultGrid.dataSource.page(1);
    });
    // 重置按钮
    $("#RESET").on("click", function (e) {
        $("#inqu_status-0-realName").val("");
        IPLAT.EFSelect.value($("#inqu_status-0-sex"), "");
      /*  $("#inqu_status-0-jobCode").val("");*/
        $("#inqu_status-0-company").val("");
        $("#inqu_status-0-workNo").val("");
        IPLAT.EFSelect.value($("#inqu_status-0-statusCode"), "");
        $("#inqu_status-0-deptNum").val("");
        $("#inqu_status-0-serviceDeptNum").val("");
        $("#inqu_status-0-managementDeptNum").val("");
        resultGrid.dataSource.page(1);
    });

    IPLATUI.EFGrid = {
        "result": {
            pageable: {
                pageSize: 50,
                pageSizes: [50, 100, 200, 500]
            },
            loadComplete: function (grid) {
                // 新增
                $("#SEE").on("click", function (e) {
                    var  rows=resultGrid.getCheckedRows();
                    var id =rows[0].id;
                    var url = IPLATUI.CONTEXT_PATH + "/web/HRXX0201?id=" + id;
                    var popData = $("#popData");
                    popData.data("kendoWindow").setOptions({
                        open: function () {
                            popData.data("kendoWindow").refresh({
                                url: url
                            });
                        }
                    });
                    popDataWindow.open().center();
                });
            }
        },

    }
})
function isEmpty(parameter){
    if(parameter == undefined || parameter == null){
        return true;
    } // 除去参数俩端的空格
    else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
        return true;
    } else {
        return false;
    }
}
