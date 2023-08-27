$(function () {
    //回显赋值
    $("#manNo").val(__ei.manNo); // 登记人工号.

    $("#SUBMIT").unbind('click').on('click', function() {
        // 防止连续提交
        $("#SUBMIT").attr("disabled", true);
        setTimeout(function () {
            $("#SUBMIT").attr("disabled", false);
        }, 3000);

        var eiInfo = new EiInfo();
        var statusCode = "00";		/* 状态代码 待审核*/
        var manNo = IPLAT.EFPopupInput.value($("#manName"));		/* 工号*/
        var manName = IPLAT.EFPopupInput.text($("#manName")); /* 姓名*/
        var sex = IPLAT.EFSelect.value($("#sex")); /* 性别*/
        var age = IPLAT.EFInput.value($("#age"));   /* 年龄*/
        var phone = IPLAT.EFInput.value($("#phone")); /* 电话*/
        var identityCard = IPLAT.EFInput.value($("#identityCard")); /* 身份证号*/
        var deptName = IPLAT.EFPopupInput.text($("#deptName")); /* 科室名称*/
        var deptNum = IPLAT.EFPopupInput.value($("#deptName")); /* 科室编码*/
        var employmentNature = IPLAT.EFSelect.value($("#employmentNature")); /* 职工属性*/
        var school = IPLAT.EFInput.value($("#school")); /* 所属学校*/
        var major = IPLAT.EFInput.value($("#major")); /* 专业*/
        var roomName = IPLAT.EFSelect.text($("#roomName")); /* 入住地址*/
        var roomId = IPLAT.EFSelect.value($("#roomName")); /* 入住地址*/
        var dormitoryDirector = IPLAT.EFSelect.value($("#dormitoryDirector")); /* 入住地址*/
        var permanentResidence = IPLAT.EFInput.value($("#permanentResidence")); /* 户口所在地*/
        var note = IPLAT.EFInput.value($("#note"));		/* 备注信息*/

        if(!validate(manNo,manName,sex,deptNum,employmentNature)){
            return;
        }

        eiInfo.set("statusCode",statusCode);
        eiInfo.set("manNo",manNo);
        eiInfo.set("manName",manName);
        eiInfo.set("sex",sex);
        if (age==""){
            eiInfo.set("age",0);
        }else{
            eiInfo.set("age",age);
        }
        eiInfo.set("phone",phone);
        eiInfo.set("identityCard",identityCard);
        eiInfo.set("deptName",deptName);
        eiInfo.set("deptNum",deptNum);
        eiInfo.set("employmentNature",employmentNature);
        eiInfo.set("school",school);
        eiInfo.set("major",major);
        eiInfo.set("roomName",roomName);
        eiInfo.set("roomId",roomId);
        eiInfo.set("dormitoryDirector",dormitoryDirector);
        eiInfo.set("permanentResidence",permanentResidence);
        eiInfo.set("note",note);

        EiCommunicator.send("DMXSRZ0101", "insert", eiInfo, {
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


// -------------------自定义自动补全--------------
/** 人员工号自动补全 */
$("#manNo").kendoAutoComplete({
    // 以manNo为参数，调用DMRZ01/queryPersonnelList本地服务进行查询，将返回的person一并填写。
    dataSource: dataSourceConfig("/service/DMRZ01/queryPersonnelList", "person",["manNo"]),
    // blockId 下的属性 workNo
    dataTextField: "workNo",
    filter:"contains",
    template:'<span>#:workNo#-#:name#</span>',
    select:function(e){
        var dataItem = this.dataItem(e.item.index());
        $("[name = 'manName']").val(dataItem.workNo);		/* 人员工号*/
        $("[name = 'manName_textField']").val(dataItem.name);		/* 人员姓名*/
        $("[name = 'deptName']").val(dataItem.deptNum);		/* 科室编码*/
        $("[name = 'deptName_textField']").val(dataItem.deptName);		/* 科室名称*/
        $("[name = 'identityCard']").val(dataItem.idNo);		/* 身份证号*/
        $("[name = 'phone']").val(dataItem.contactTel);		/* 手机号*/
    }
});

// ---------------------------------------------

/** kendoAutoComplete的dataSource配置*/
function dataSourceConfig(url,blockId,param){
    return new kendo.data.DataSource({
        serverFiltering: true,//每次输入重新获取数据
        transport: {
            read: {
                url:IPLATUI.CONTEXT_PATH + url,
                type: 'POST',
                dataType: "json",
                contentType: "application/json"
            },
            parameterMap: function (options, operation) {
                var info = new EiInfo();
                if(param != null){
                    for(var index in param){ info.set(param[index],$("#"+param[index]).val()); }
                }
                return  info.toJSONString();
            }
        },
        schema: {
            data: function (response) {
                ajaxEi = EiInfo.parseJSONObject(response);
                var block = ajaxEi.getBlock(blockId);
                var array = new Array();
                for(var index in  block.getRows()){
                    array.push(block.getMappedObject( block.getRows()[index]));
                }
                return array;
            }
        }
    })
}

//参数校验
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
    if(isEmpty(identityCard)){
        NotificationUtil("身份证号不能为空", "error");
        return false;
    }
    if(isEmpty(deptNum)){
        NotificationUtil("请选择所属部门", "error");
        return false;
    }
    if(isEmpty(employmentNature)){
        NotificationUtil("请选择人员属性", "error");
        return false;
    }

    return true;
}

// 空值函数.
function isEmpty(parameter){
    if(parameter == undefined || parameter == null){
        return true;
    }
    // else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
    //     return true;
    // }
    else {
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


// 显示所有，自动补齐
function showAll(selector){
    var autocomplete = $("#"+selector).data("kendoAutoComplete");
    autocomplete.search("");
}

// 对选中的数值回显.
function echoOne(selector){
    var autocomplete = $("#"+selector).data("kendoAutoComplete");
    var options = autocomplete.options;
    var rows = options.dataSource._data;
    var value = autocomplete.value();
    if(rows !=null && rows.length > 0 && value.length > 0){
        var index = findIndex(rows,value,selector);
        if(index > -1){
            autocomplete.select(parseInt(index));
            var dataItem = autocomplete.dataItem(parseInt(index));
            echoSelect(selector, dataItem);
        }
    }
}

function findIndex(rows,value,selector){
    for(var index in rows){
        if(selector == "manNo"){
            if(rows[index]['workNo'] == value){
                return index;
            }
        }
    }
    return -1;
}

// 当manNo 有值时自动补充manName、manName_textField、deptName、deptName_textField.
function echoSelect(selector, dataItem) {
    if(selector == "manNo"){
        $("[name = 'manName']").val(dataItem.workNo);		/* 人员*/
        $("[name = 'manName_textField']").val(dataItem.name);		/* 人员*/
        $("[name = 'deptName']").val(dataItem.deptNum);		/* 科室编码*/
        $("[name = 'deptName_textField']").val(dataItem.deptName);		/* 科室*/
        $("[name = 'identityCard']").val(dataItem.idNo);		/* 身份证号*/
        $("[name = 'phone']").val(dataItem.contactTel);		/* 手机号*/
    }
}
