$(function () {

    $("#SUBMIT").unbind('click').on('click', function(){
        // 防止连续提交
        $("#SUBMIT").attr("disabled",true);
        setTimeout(function(){$("#SUBMIT").attr("disabled",false);},3000);
        var roomSelectList = resultDormItemGrid.getDataItems();
        var eiInfo = new EiInfo();
        // 人员id
        var manId = IPLAT.EFInput.value($("#id"));
        // 原入住的宿舍id
        var roomId = IPLAT.EFInput.value($("#roomId"));
        // 原入住的宿舍剩余床位
        var remainingBedNum = IPLAT.EFInput.value($("#remainingBedNum"));
        // 宿舍人员资料表id
        var roomManId = IPLAT.EFInput.value($("#roomManId"));
        // 换宿前所在宿舍床位号
        var bedNo = IPLAT.EFInput.value($("#bedNo"));
        // 申请换宿的备注
        var changeRoomNote = IPLAT.EFInput.value($("#changeRoomNote"));
        // 申请换宿的时间
        var applyChangeDate = IPLAT.EFInput.value($("#applyChangeDate"));
        // 换宿前的宿舍实际入住时间
        var actualInDate = IPLAT.EFInput.value($("#actualInDate"));
        // 宿舍保留天数
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
        // 宿舍可选列表判断
        if(roomSelectList.length > 1){
            NotificationUtil("当前为该换宿人员分配的房间数量大于1，请删除多余的分配房间", "error");
            return;
        }
        eiInfo.set("roomManId",roomManId);
        eiInfo.set("bedNo",bedNo);
        eiInfo.set("changeRoomNote",changeRoomNote);
        eiInfo.set("applyChangeDate",applyChangeDate);
        eiInfo.set("actualInDate",actualInDate);
        eiInfo.set("manId",manId);
        eiInfo.set("roomId",roomId);
        eiInfo.set("remainingBedNum",remainingBedNum);
        eiInfo.set("keepRoomDays",keepRoomDays);
        eiInfo.set("roomSelectList",roomSelectList);
        console.log(roomSelectList);
        EiCommunicator.send("DMHS0201", "directInsert", eiInfo, {
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
                        // 获取该用户当前的宿舍roomId
                        var roomId = IPLAT.EFInput.value($("#roomId"));
                        var iframe = popDataItemWindow.element.children("iframe")[0].contentWindow;
                        // 获取所有行的数值.
                        // var rows = resultDormItemGrid.getDataItems();
                        // 构建一个数组对象，进行传值。
                        var obj = {roomId : roomId};
                        var rows = [];
                        rows.push(obj);
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

