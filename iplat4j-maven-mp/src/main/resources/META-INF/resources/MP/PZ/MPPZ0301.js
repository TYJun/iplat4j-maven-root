$(function() {

    IPLATUI.EFDatePicker = {
        "inqu_status-0-purchaseYear":{
            start: "decade",
            depth: "decade"
        }
    }

    //校验器
    let  validator = IPLAT.Validator({id: "inqu"});

    //保存
    $("#SUBMIT").unbind('click').on('click', function(event){
        // 防止连续提交
        $("#SUBMIT").attr("disabled",true);
        setTimeout(function(){$("#SUBMIT").attr("disabled",false);},5000);

        //校验通过，保存数据
        if(validator.validate()) {
            //获取表单数据
            let eiInfo = new EiInfo();
            eiInfo.setByNode("inqu");

            eiInfo.set("inqu_status-0-purchaseYear", IPLAT.EFInput.value($("#inqu_status-0-purchaseYear")));
            eiInfo.set("inqu_status-0-purchaseType", IPLAT.EFSelect.value($("#inqu_status-0-purchaseType")));
            eiInfo.set("inqu_status-0-purchaseTypeName", IPLAT.EFSelect.text($("#inqu_status-0-purchaseType")));
            eiInfo.set("inqu_status-0-purchaseCost", IPLAT.EFInput.value($("#inqu_status-0-purchaseCost")));

            if(!isPositiveNumber(IPLAT.EFInput.value($("#inqu_status-0-purchaseCost")))){
                NotificationUtil("请正确输入金额(最多两位小数的正数)","error");
                return;
            }

            //调用后台保存方法
            EiCommunicator.send("MPPZ0301", "save", eiInfo, {
                onSuccess : function(ei) {
                    if(ei.getStatus() == -1){
                        NotificationUtil(ei.getMsg(), "error");
                        return;
                    }

                    NotificationUtil("保存成功", "");
                    window.parent['popDataWindow'].close();
                    window.parent["resultGrid"].dataSource.page(1);
                },
                onFail : function(errorMsg, status, e) {
                    NotificationUtil("保存失败，原因[" + errorMsg + "]", "error");
                }
            });
        } else {
            NotificationUtil("必填参数不能为空", "error");
        }
    });

    //取消
    $("#CANCEL").click(function() {
        window.parent['popDataWindow'].close();
    });

});

function isPositiveNumber(parameter){
    var regu = /^[0-9]+(\.[0-9]{1,2})?$/;
    if(regu.test(parameter)){
        return true;
    } else {
        false
    }
}