$(function (){
    IPLATUI.EFGrid = {
        "result": {
            pageable : false,
            exportGrid : false,
            toolbarConfig:{
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false,cancel: false,save: false,'delete': false,
                buttons:[{
                    name: "ADD",text: "新增",layout:"3",
                    click: function () { popData(); }
                },{
                    name: "DEL",text: "删除",layout:"3",
                    click: function () {
                        var checkRows = resultGrid.getCheckedRows();
                        if (checkRows.length > 0) {
                            resultGrid.removeRows(checkRows);
                        } else {
                            IPLAT.NotificationUtil("请选择需要删除的人员")
                        }
                    }
                }]
            },
            loadComplete:function (e) {}
        },
    }



    //确定
    $("#RESAVE").unbind('click').on('click', function(e){
        // 防止连续提交
        $("#RESAVE").attr("disabled",true);
        setTimeout(function(){$("#RESAVE").attr("disabled",false);},5000);
        var rows = resultGrid.getDataItems();
        for(var i=0;i<rows.length;i++){
            var preOutTime = kendo.toString(rows[i].preOutTime, "yyyy-MM-dd");
            rows[i].preOutTime=preOutTime;
            var inTime = kendo.toString(rows[i].inTime, "yyyy-MM-dd");
            rows[i].inTime = inTime;
        }
        //参数检验
        if(validate(rows)) {
            var eiInfo = new EiInfo();
            eiInfo.set("rows", rows);
            EiCommunicator.send("HRLZ0101", "update", eiInfo, {
                onSuccess: function (ei) {
                    if (ei.getStatus() == -1) {
                        NotificationUtil(ei.getMsg(), "error");
                        return;
                    }
                    NotificationUtil("新增成功");
                    window.parent['popDataWindow'].close();
                    window.parent["resultGrid"].dataSource.page(1);
                }
            })
        }
    });

    //物资选择弹窗
    function popData() {
        var url = IPLATUI.CONTEXT_PATH + "/web/HRLZ010101?initLoad";
        var popData = $("#popDataPerson");
        popData.data("kendoWindow").setOptions({
            open : function() {
                popData.data("kendoWindow").refresh({
                    url : url,
                });
            }
        });
        popDataPersonWindow.open().center();
    }

    //参数校验
    function validate(rows) {
        if(rows == null || rows.length == 0){
            NotificationUtil("请选择人员","error");
            return false;
        }
        for(var index in rows){
            if(rows[index].preOutTime==null || rows[index].preOutTime === ""){
                NotificationUtil("请输预离职时间","error");
                return false;
            }
            if(rows[index].preOutTime < rows[index].inTime){
                NotificationUtil("预离职时间不可早于入职时间","error");
                return false;
            }
        }
        return true;
    }

});
//添加人员(过滤重复数据)
function addRows(checkRows){
    // 获取当前页的
    var peopleList = resultGrid.getDataItems();
    for (var index in checkRows) {
        var model = checkRows[index];
        var isExist = false;
        for(var i in peopleList) {
            var man = peopleList[i];
            if(man.workNo == model.workNo){
                isExist = true;
            }
        }
        if(!isExist){
            resultGrid.addRows(model)
        }
    }
}
