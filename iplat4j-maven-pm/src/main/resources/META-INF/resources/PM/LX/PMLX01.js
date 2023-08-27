$(function (){
    //查询
    $("#QUERY").on("click", function(e) {
        resultGrid.dataSource.page(1);
    });
    //重置按钮
    $("#REQUERY").on("click", function(e) {
        $("#inqu_status-0-projectName").val("");
        $("#inqu_status-0-projectPrincipal").val("");
        IPLAT.EFSelect.value($("#inqu_status-0-projectProp"),"");
        IPLAT.EFSelect.value($("#inqu_status-0-projectTypeNum"),"");
        resultGrid.dataSource.query(1);
    });
    // 必须写在框架里按钮才能生效
    IPLATUI.EFGrid = {
        "result" : {
            dataBound: function (e){

            },
            loadComplete: function (e){
                //新增
                $("#ADDPR").on("click", function(e) {
                    var url = IPLATUI.CONTEXT_PATH + "/web/PMLX0101?initLoad";
                    var popData = $("#projectNew");
                    popData.data("kendoWindow").setOptions({
                        open : function() {
                            popData.data("kendoWindow").refresh({
                                url : url,
                            });
                        }
                    });
                    // 打开弹窗
                    projectNewWindow.open().center();
                });

                //编辑
                $("#EDITPR").on("click", function(e) {

                });

                //查看
                $("#LOOKPR").on("click", function(e) {

                });

                //删除
                $("#DELETEPR").on("click", function(e) {

                });
            }
        }
    }

});