$(function () {
    if (isEmptyStr($("#totalDepreciation").val())){
        if (!isEmptyStr($("#buyDate").val())){
            var totalDepreciation = (getDistanceMonth($("#buyDate").val()) * $("#monthDepreciation").val()).toFixed(2);
            if (parseInt(totalDepreciation) >= parseInt($("#buyCost").val())){
                $("#totalDepreciation").val(($("#buyCost").val() - $("#estimatedResidualValue").val()).toFixed(2))
            }else {
                $("#totalDepreciation").val(totalDepreciation)
            }
        }
    }

    $("#netAssetValue").val(($("#buyCost").val() - $("#totalDepreciation").val()).toFixed(2))

    // if(isEmptyStr($("#netAssetValue").val())){
    //
    // }
});

IPLATUI.EFTab = {
    "tab-tab_grid": {
        select: function (e) {
            var grid = $(e.contentElement).find("div.k-grid").data("kendoGrid")
            if (grid != undefined) {
                grid.dataSource.page(1);
            }
        }
    },
}

IPLATUI.EFGrid = {
    "resultPutIn":{
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
        },
    },
    "resultPutOut":{
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
        },
    },
    "resultDepreciation":{
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
        },
    },
    "resultModification": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
        },
        onCellClick:function (e){
            if (e.field === "modificationNum") {
                var modificationNum = e.model['modificationNum'];
                var type = "look";
                if (modificationNum != " " && modificationNum != null) {
                    popData(modificationNum, type);
                }
            }
        },
        loadComplete: function (e) {

        },
    },
    "resultSplit": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
        }
    },
    "resultTransfer": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
        }
    },
    "resultIdle": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
        }
    },
    "resultReimburse": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
        }
    },
    "resultScrap": {
        pageable: false,
        exportGrid: false,
        toolbarConfig: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
        }
    },
    loadComplete: function (e) {

    }
}

// 变更详情
function popData(modificationNum,type) {
    var url = IPLATUI.CONTEXT_PATH + "/web/FABG0102?initLoad&modificationNum=" + modificationNum + "&type=" + type;
    var popData = $("#popData");
    popData.data("kendoWindow").setOptions({
        open : function() {
            popData.data("kendoWindow").refresh({
                url : url,
            });
        }
    });
    popDataWindow.open().center();
}

// 计算2个日期相差多少月
function getDistanceMonth(startTime) {
    startTime = new Date(startTime)
    endTime = new Date(getNowDate())
    var dateToMonth = 0
    var startDate = startTime.getDate() + startTime.getHours() / 24 + startTime.getMinutes() / 24 / 60
    var endDate = endTime.getDate() + endTime.getHours() / 24 + endTime.getMinutes() / 24 / 60
    if (endDate >= startDate) {
        dateToMonth = 0
    } else {
        dateToMonth = -1
    }
    var yearToMonth = (endTime.getYear() - startTime.getYear()) * 12
    var monthToMonth = endTime.getMonth() - startTime.getMonth()
    return yearToMonth + monthToMonth + dateToMonth + 1
}

// 获取当前时间YYYYMMDD
function getNowDate() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (day >= 0 && day <= 9) {
        day = "0" + day;
    }
    return year + '-' + month + '-' + day
}

function isEmptyStr(s){
    if (s == null || s === '') {
        return true
    }
    return false
}