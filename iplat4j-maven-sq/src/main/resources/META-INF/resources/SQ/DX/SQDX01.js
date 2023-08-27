$(function () {

    //保存
    $("#SAVE").on("click", function () {
        //参数封装
        var eiInfo = buildParam();
        //提交
        EiCommunicator.send("SQDX01", "saveSQMsg", eiInfo, {
            onSuccess: function (ei) {
                IPLAT.NotificationUtil(ei.msg)
                dataReturn();
            }
        })

    });


    //刷新
    $("#REFRESH").on("click", function (e) {
        dataReturn();
        window.location.reload();
    });


    //初始加载数据回显
    dataReturn();

    //处理回显数据
    function dataReturn() {
        //获取参数
        var eiInfo = new EiInfo();
        //提交
        EiCommunicator.send("SQDX01", "querySQMsg", eiInfo, {
            onSuccess: function (ei) {
                var dataList = ei.get("data");
                for (var index in dataList) {
                    dataReturnRow(index, dataList[index])
                }
                IPLAT.NotificationUtil(ei.msg)
            }
        })
    }

    //回显每一大项
    function dataReturnRow(index, data) {
        //回显是否启用checkBox
        if (data.isRunning == "1") {
            $("#inqu_status-" + index + "-isRunning")[0].checked = true
        }
        //回显短信模板
        $("#inqu_status-" + index + "-smsTemp").val(data.smsTemp);
        //回显短信接收人
        if (!isEmpty(data.smsRecvCode)) {
            var codes = data.smsRecvCode.split(",")
            var tags = $("input[class='person" + index + "']");
            for (var i = 0; i < tags.length; i++) {
                for (var j = 0; j < codes.length; j++) {
                    if (tags[i].value == codes[j]) {
                        tags[i].checked = true;
                    }
                }
            }
        }
        //回显超期天数
        $("#inqu_status-" + index + "-lateDays").val(data.day);
        //回显时间
        $("#inqu_status-" + index + "-time").val(data.time);
    }

    //封装参数
    function buildParam() {
        var array = new Array();
        for (var index = 0; index < 2; index++) {
            var param = {
                "id": $("#inqu_status-" + index + "-id").val(),
                "configType": $("#inqu_status-" + index + "-configType").val(),
                "configTypeName": $("#inqu_status-" + index + "-configTypeName").val(),
                "smsTemp": $("#inqu_status-" + index + "-smsTemp").val(),
                "isRunning": getCheckValue("isRunning", "", index),
                "day": $("#inqu_status-" + index + "-lateDays").val(),
                "time": $("#inqu_status-" + index + "-time").val(),
                "smsRecvCode": getCheckValue("", "person", index)
            }
            array.push(param)
        }
        var eiInfo = new EiInfo();
        eiInfo.set("list", array);
        return eiInfo;
    }

    //获取复选框的值
    function getCheckValue(checkBoxId, checkBoxClass, index) {
        var checkBox, values = [];
        if (checkBoxId != "") {
            checkBox = $("#inqu_status-" + index + "-isRunning");
            return checkBox[0].checked ? "1" : "0";
        }
        if (checkBoxClass != "") {
            checkBox = $("input[class='person" + index + "']");
            //遍历
            for (var i in checkBox) {
                if (checkBox[i].checked) {
                    values.push(checkBox[i].value);
                }
            }
            return values.join(",");
        }
    }

    function isEmpty(str) {
        if (str == undefined) {
            return true;
        }
        if (str == null) {
            return true;
        }
        if (str.replace(/(^\s*)|(\s*$)/g, "") == "") {
            return true;
        }
        return false;
    }
});
