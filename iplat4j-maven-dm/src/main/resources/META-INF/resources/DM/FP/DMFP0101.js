$(function () {

    $("#SUBMIT").unbind('click').on('click', function(){
        // 防止连续提交
        $("#SUBMIT").attr("disabled",true);
        setTimeout(function(){$("#SUBMIT").attr("disabled",false);},3000);
        var roomSelectList = resultDormItemGrid.getDataItems();
        var eiInfo = new EiInfo();
        var manId = IPLAT.EFInput.value($("#id"));
        var keepRoomDays = IPLAT.EFInput.value($("#keepRoomDays"));
        //参数校验
        if(!validate(keepRoomDays)){
            return;
        }
        // 宿舍可选列表判断
        if(roomSelectList == null || roomSelectList.length < 1){
            NotificationUtil("请为当前对象分配房间", "error");
            return;
        }
        eiInfo.set("manId",manId);
        eiInfo.set("keepRoomDays",keepRoomDays);
        eiInfo.set("roomSelectList",roomSelectList);
        console.log(roomSelectList);
        EiCommunicator.send("DMFP0101", "insert", eiInfo, {
            onSuccess : function(ei) {
                var popData = $("#popData", parent.document);
                popData.kendoWindow().data("kendoWindow").close();
                NotificationUtil(ei.getMsg(), "success");
                setTimeout(function() {
                    window.parent.location.reload()
                }, 600);
            }
        });
    });

    IPLATUI.EFGrid = {
        "resultDormItem" : {
            /* 隐藏EF:EFGrid功能按钮 */
            pageable : false,
            exportGrid : false,
            loadComplete : function(e) {
                $("#DELETECHOOSE").click(function() {
                    var checkRows = resultDormItemGrid.getCheckedRows();
                    if(checkRows.length<1){
                        NotificationUtil("请选择要删除的行", "error");
                        return;
                    }
                    resultDormItemGrid.removeRows(checkRows);
                });
                $("#ADDDORM").on("click", function (e) {
                    var url = IPLATUI.CONTEXT_PATH + "/web/DMFP010101";
                    var popData = $("#popDataItem");
                    popData.data("kendoWindow").setOptions({
                        open : function() {
                            popData.data("kendoWindow").refresh({
                                url : url
                            });
                        }
                    });
                    popDataItemWindow.open().center();
                    // 向其子窗口中的 iframe 对象传值
                    setTimeout(function(){
                        var iframe = popDataItemWindow.element.children("iframe")[0].contentWindow;
                        // 获取所有行的数值.
                        var rows = resultDormItemGrid.getDataItems();
                        console.log(iframe);
                        // 将所有行的数值传给其子窗口.
                        if(iframe){
                            iframe.setData(rows);
                        }
                    }, 400 );
                });
            }
        }
    }
});

function validate(keepRoomDays){
    if(isEmpty(keepRoomDays)){
        NotificationUtil("宿舍保留天数不能为空", "warning");
        return false;
    }else{
        var reg = /^[1-9]\d*$/;
        if (reg.test(keepRoomDays)) {
            return true;
        } else {
            NotificationUtil("请正确填写天数,格式为非零的正整数", "warning");
            return false;
        }
    }
}

// 判空函数.
function isEmpty(parameter){
    if(parameter == undefined || parameter == null){
        return true;
    } else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
        return true;
    } else {
        return false;
    }
}

