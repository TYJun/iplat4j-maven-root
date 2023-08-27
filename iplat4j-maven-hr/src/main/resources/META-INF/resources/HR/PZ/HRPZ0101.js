$(function (){
    //确定
    $("#COMFIRM").unbind('click').on('click', function(e){
        // 防止连续提交
        $("#COMFIRM").attr("disabled",true);
        setTimeout(function(){$("#COMFIRM").attr("disabled",false);},5000);
        var serviceDeptNum = IPLAT.EFPopupInput.value($("#serviceDeptName"));
        var serviceDeptName = IPLAT.EFPopupInput.text($("#serviceDeptName"));
        var surpName = IPLAT.EFInput.value($("#surpName"));
        var position = IPLAT.EFInput.value($("#position"));
        var peopleLines = IPLAT.EFInput.value($("#peopleLines"));
        var peopleLinesStable = IPLAT.EFInput.value($("#peopleLinesStable"));
        var memo = IPLAT.EFInput.value($("#memo"));
        //参数检验
        if(validate(serviceDeptName,surpName,peopleLines,peopleLinesStable)){
            var eiInfo = new EiInfo();
            eiInfo.set("serviceDeptNum", serviceDeptNum);
            eiInfo.set("serviceDeptName", serviceDeptName);
            eiInfo.set("surpName", surpName);
            eiInfo.set("position", position);
            eiInfo.set("peopleLines", peopleLines);
            eiInfo.set("peopleLinesStable", peopleLinesStable);
            eiInfo.set("memo", memo);
            EiCommunicator.send("HRPZ0101", "insert", eiInfo, {
                onSuccess : function(ei) {
                    if(ei.getStatus() == -1){
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

    //取消
    $("#CLOSE").on("click", function(e) {
        window.parent['popDataWindow'].close();
    });

    //参数校验
    function validate(serviceDeptName,surpName,peopleLines,peopleLinesStable) {
        if(serviceDeptName == null || serviceDeptName=="" || serviceDeptName==undefined){
            NotificationUtil("服务部门不能为空","error");
            return false;
        }
        if(surpName == null || surpName=="" || surpName==undefined){
            NotificationUtil("物业公司不能为空","error");
            return false;
        }
        if(peopleLines == null || peopleLines=="" || peopleLines==undefined){
            NotificationUtil("招标配额不能为空","error");
            return false;
        }
        if(peopleLinesStable == null || peopleLinesStable=="" || peopleLinesStable==undefined){
            NotificationUtil("稳定配额不能为空","error");
            return false;
        }
        return true;
    }
});
