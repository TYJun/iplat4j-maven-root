$(function () {
    //回显赋值
    console.log(__ei);
    //回显赋值
    IPLAT.EFPopupInput.value($("#note"),__ei.nowAddress);

    $("#SUBMIT").unbind('click').on('click', function() {
        // 防止连续提交
        $("#SUBMIT").attr("disabled", true);
        setTimeout(function () {
            $("#SUBMIT").attr("disabled", false);
        }, 3000);

        var eiInfo = new EiInfo();
        var manId = IPLAT.EFInput.value($("#id"));
        var manNo = IPLAT.EFInput.value($("#manNo"));		/* 工号*/
        var manName = IPLAT.EFInput.value($("#manName")); /* 姓名*/
        var sex = IPLAT.EFSelect.value($("#sex")); /* 性别*/
        var age = IPLAT.EFInput.value($("#age"));   /* 年龄*/
        var phone = IPLAT.EFInput.value($("#phone")); /* 电话*/
        var note = IPLAT.EFInput.value($("#note"));		/* 当前居住地*/

        if(!validate(manNo,manName,sex,age,phone)){
            return;
        }

        eiInfo.set("manId",manId);
        eiInfo.set("manNo",manNo);
        eiInfo.set("manName",manName);
        eiInfo.set("sex",sex);
        eiInfo.set("age",age);
        eiInfo.set("phone",phone);
        eiInfo.set("note",note);

        EiCommunicator.send("DMXW0102", "update", eiInfo, {
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
});

//参数校验
// 对工号、姓名、性别、年龄、电话、身份证号进行参数空值验证.
function validate(manNo,manName,sex,age,phone){
    if(isEmpty(manNo)){
        NotificationUtil("工号不能为空", "error");
        return false;
    }
    if(isEmpty(manName)){
        NotificationUtil("姓名不能为空", "error");
        return false;
    }
    if(isEmpty(sex)){
        NotificationUtil("性别不能为空", "error");
        return false;
    }
    if(isEmpty(age)){
        NotificationUtil("年龄不能为空", "error");
        return false;
    }
    if(!isEmpty(phone)){
        if(!checkPhone(phone)){
            NotificationUtil("请输入正确的电话号码", "error");
            return false;
        }
    }else {
        NotificationUtil("电话不能为空", "error");
        return false;
    }
    return true;
}

// 空值函数.
function isEmpty(parameter){
    if(parameter == undefined || parameter == null){
        return true;
    } else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
        return true;
    } else {
        return false;
    }
}

// 检查号码规则.
function checkPhone(mobile) {
    var tel = /^([0-9]{3,4}-)?[0-9]{4,8}$/;
    var phone = /^((\+?86)|(\(\+86\)))?(1[3-9][0-9]{9})$/;
    if(tel.test(mobile) || phone.test(mobile) ){ //手机号码
        return true;
    }
    return false;
}