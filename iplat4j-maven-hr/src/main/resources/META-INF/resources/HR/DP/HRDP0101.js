$(function (){
    IPLAT.EFPopupInput.text($("#deptNum"),__ei.deptNum);

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
        var type=IPLAT.EFInput.value($("#type"));
        var id=IPLAT.EFInput.value($("#id"));
        var billTime=IPLAT.EFInput.value($("#billTime"));
        var arriveTime=IPLAT.EFInput.value($("#arriveTime"));
        var leaveTime=IPLAT.EFInput.value($("#leaveTime"));
        var deptNum= IPLAT.EFPopupInput.text($("#deptNum"));
        var shiftTimeSection=IPLAT.EFInput.value($("#shiftTimeSection"));
        var changeCode=IPLAT.EFSelect.text($("#changeCode"));
        var numbers=IPLAT.EFInput.value($("#numbers"));
        var shiftFirstTime=IPLAT.EFInput.value($("#shiftFirstTime"));
        var supportStation=IPLAT.EFInput.value($("#supportStation"));
        var becauseMemo=IPLAT.EFInput.value($("#becauseMemo"));

        //参数检验
            var eiInfo = new EiInfo();
            eiInfo.set("rows", rows);
            eiInfo.set("type", type);
            eiInfo.set("id", id);
            eiInfo.set("billTime", billTime);
            eiInfo.set("arriveTime", arriveTime);
            eiInfo.set("leaveTime", leaveTime);
            eiInfo.set("deptNum", deptNum);
            eiInfo.set("shiftTimeSection", shiftTimeSection);
            eiInfo.set("shiftFirstTime", shiftFirstTime);
            eiInfo.set("changeCode", changeCode);
            eiInfo.set("supportStation", supportStation);
            eiInfo.set("numbers", numbers);
            eiInfo.set("becauseMemo", becauseMemo);
            if(validate(rows,billTime,arriveTime,leaveTime,deptNum,shiftTimeSection,shiftFirstTime,numbers)) {
                EiCommunicator.send("HRDP0101", "insert", eiInfo, {
                    onSuccess: function (ei) {
                        if (ei.getStatus() == -1) {
                            NotificationUtil(ei.getMsg(), "error");
                            return;
                        }
                        NotificationUtil("新增成功");
                        window.parent['popDataWindow'].close();
                        window.parent['popDataModifyWindow'].close();
                        window.parent["resultGrid"].dataSource.page(1);
                    }
                })
            }
    });

    //人员选择弹窗
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
    function validate(rows,billTime,arriveTime,leaveTime,deptNum,shiftTimeSection,shiftFirstTime,numbers){
        if(rows == null || rows.length == 0){
            NotificationUtil("请选择人员","error");
            return false;
        }
        if (isEmpty(arriveTime)){
            NotificationUtil("到岗时间不能为空","error");
            return false;
        }
        if (isEmpty(leaveTime)){
            NotificationUtil("离岗时间不能为空","error");
            return false;
        }
        if (isEmpty(deptNum)){
            NotificationUtil("申请科室不能为空","error");
            return false;
        }
        if (isEmpty(shiftTimeSection)){
            NotificationUtil("支援时段不能为空","error");
            return false;
        }
        if (isEmpty(shiftFirstTime)){
            NotificationUtil("上班时间不能为空","error");
            return false;
        }
        if (isEmpty(numbers)){
            NotificationUtil("支援人数不能为空","error");
            return false;
        }
        if (rows.length!=parseInt(numbers)){
            NotificationUtil("支援人数要与选择人员数相同","error");
            return false;
        }
        if(isValid(numbers)){
            NotificationUtil("支援人数请输入正整数","error");
            return false;
        }
        return true;
    }

});
//添加人员(过滤重复数据)
function addRows(checkRows){
    // 获取当前页的数据
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
function isValid(numbers){
    let m= /^[0-9]*[1-9][0-9]*$/
    if (m.test(numbers)){
        return false;
    }
        return true;
}

/**
 * 判断是否为空
 * @param parameter
 * @returns {boolean}
 */
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
