$(function () {
    //回显赋值
    console.log(__ei);
    //回显赋值
    IPLAT.EFPopupInput.value($("#deptName"),__ei.deptNum);
    IPLAT.EFPopupInput.text($("#deptName"),__ei.deptName);

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
        var identityCard = IPLAT.EFInput.value($("#identityCard")); /* 身份证号*/
        var deptName = IPLAT.EFPopupInput.text($("#deptName")); /* 科室名称*/
        var deptNum = IPLAT.EFPopupInput.value($("#deptName")); /* 科室编码*/
        var maritalStatus = IPLAT.EFSelect.value($("#maritalStatus")); /* 婚姻情况*/
        var spouseName = IPLAT.EFInput.value($("#spouseName")); /* 配偶姓名*/
        var educationBackground = IPLAT.EFSelect.value($("#educationBackground")); /* 学历*/
        // var academicYear = IPLAT.EFSelect.value($("#academicYear")); /* 学年*/
        var employmentNature = IPLAT.EFSelect.value($("#employmentNature")); /* 职工属性*/
        // var isNetwork = IPLAT.EFSelect.value($("#isNetwork")); /* 是否联网*/
        // var isStay = IPLAT.EFSelect.value($("#isStay")); /* 是否已办理暂住证*/
        var permanentResidence = IPLAT.EFInput.value($("#permanentResidence")); /* 户口所在地*/
        var hiredate = IPLAT.EFInput.value($("#hiredate")); /* 入职时间*/
        // var estimatedInDate = IPLAT.EFInput.value($("#estimatedInDate")); /* 预计入住时间*/
        // var estimatedOutDate = IPLAT.EFInput.value($("#estimatedOutDate")); /* 预计退宿时间*/
        var note = IPLAT.EFInput.value($("#note"));		/* 备注信息*/

        // if(!validate(manNo,manName,sex,age,phone,identityCard,deptNum,employmentNature,hiredate,estimatedInDate,estimatedOutDate)){
        //     return;
        // }
        if(!validate(manNo,manName,sex,deptNum,employmentNature)){
            return;
        }

        eiInfo.set("manId",manId);
        eiInfo.set("manNo",manNo);
        eiInfo.set("manName",manName);
        eiInfo.set("sex",sex);
        eiInfo.set("age",age);
        eiInfo.set("phone",phone);
        eiInfo.set("identityCard",identityCard);
        eiInfo.set("deptName",deptName);
        eiInfo.set("deptNum",deptNum);
        eiInfo.set("maritalStatus",maritalStatus);
        eiInfo.set("spouseName",spouseName);
        eiInfo.set("educationBackground",educationBackground);
        eiInfo.set("academicYear",academicYear);
        eiInfo.set("employmentNature",employmentNature);
        // eiInfo.set("isNetwork",isNetwork);
        // eiInfo.set("isStay",isStay);
        eiInfo.set("permanentResidence",permanentResidence);
        eiInfo.set("hiredate",hiredate);
        // eiInfo.set("estimatedInDate",estimatedInDate);
        // eiInfo.set("estimatedOutDate",estimatedOutDate);
        eiInfo.set("note",note);

        EiCommunicator.send("DMRZ0102", "update", eiInfo, {
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
// 对工号、姓名、性别、年龄、电话、身份证号、部门课时、职工属性、入职时间、预计入住时间、预计退宿时间进行参数空值验证.
// function validate(manNo,manName,sex,age,phone,identityCard,deptNum,employmentNature,hiredate,estimatedInDate,estimatedOutDate){
// 对工号、姓名、性别、部门课时、职工属性进行参数空值验证.
function validate(manNo,manName,sex,deptNum,employmentNature){
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
    // if(isEmpty(age)){
    //     NotificationUtil("年龄不能为空", "error");
    //     return false;
    // }
    // if(!isEmpty(phone)){
    //     if(!checkPhone(phone)){
    //         NotificationUtil("请输入正确的电话号码", "error");
    //         return false;
    //     }
    // }else {
    //     NotificationUtil("电话不能为空", "error");
    //     return false;
    // }
    // if(isEmpty(identityCard)){
    //     NotificationUtil("身份证号不能为空", "error");
    //     return false;
    // }
    if(isEmpty(deptNum)){
        NotificationUtil("请选择所属部门", "error");
        return false;
    }
    if(isEmpty(employmentNature)){
        NotificationUtil("请选择职工属性", "error");
        return false;
    }
    // if(isEmpty(hiredate)){
    //     NotificationUtil("请填写入职时间", "error");
    //     return false;
    // }
    // if(isEmpty(estimatedInDate)){
    //     NotificationUtil("请填写预计入住时间", "error");
    //     return false;
    // }
    // if(isEmpty(estimatedOutDate)){
    //     NotificationUtil("请填写预计退宿时间", "error");
    //     return false;
    // }
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