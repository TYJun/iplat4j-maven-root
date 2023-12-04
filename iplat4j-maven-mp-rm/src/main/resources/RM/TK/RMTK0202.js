$(function () {

    //表格初始化处理
    let formValidator = IPLAT.Validator({id: "inqu"});
    let submitFlag = true;
    IPLATUI.EFGrid = new WilpGrid({
        showPage: false,
        toolbar: {
            hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "SUBMIT", text: "退库确认", layout: "3",
                click: function () {
                    // 防止连续提交
                    $("#SUBMIT").attr("disabled", true);
                    setTimeout(function () {
                        $("#SUBMIT").attr("disabled", false);
                    }, 5000);
                    if (!submitFlag) {
                        return;
                    }
                    submitFlag = false;
                    backSure(formValidator);
                    submitFlag = true;
                }
            }]
        },
    }).buildToolBarGrid();
});

/**
 * 退库确认
 * @param formValidator
 */
function backSure(formValidator) {
    if (formValidator.validate()) {
        let eiInfo = new EiInfo();
        eiInfo.setByNode("inqu");

        let list = resultGrid.getDataItems();
        if (!list || list.length == 0) {
            NotificationUtil("明细列表不能为空", "error");
        }
        if (!validateBackNum(list)) {
            return;
        }
        eiInfo.set("list", list);

        //调用后台保存方法
        EiCommunicator.send("RMTK0202", "outStock", eiInfo, {
            onSuccess: function (ei) {
                if (ei.getStatus() == -1) {
                    NotificationUtil(ei.getMsg(), "error");
                    return;
                }

                NotificationUtil("退库成功", "success");
                window.parent['popDataWindow'].close();
                window.parent['resultGrid'].dataSource.page(1);
            },
            onFail: function (errorMsg, status, e) {
                NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
            }
        });
    }
}

/**
 * 退库数量校验
 * @param rows
 * @returns {boolean}
 */
function validateBackNum(rows) {
    for (let item of rows) {
        //退库数量
        let num = item['num'];
        //已退库数量
        let outNum = item['outNum'];
        //本次退库数量
        let curOutNum = item['curOutNum'];
        if (num - outNum - curOutNum < 0) {
            NotificationUtil(`${item.matName}的物资本次退库数量超过剩余可退库数量`, "error");
            return false;
        }
    }
    return true;
}