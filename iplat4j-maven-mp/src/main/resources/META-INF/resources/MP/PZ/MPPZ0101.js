$(function() {

    IPLATUI.EFTreeInput = {
        "inqu_status-0-matTypeId": {
            backFill: function (e) {
                let node = e.node;
               /* let matTypeNums = [], matTypeNames = [];
                node.map(item => {matTypeNums.push(item.code); matTypeNames.push(item.text)});
                $("#inqu_status-0-matTypeNum").val(matTypeNums.join(","));
                $("#inqu_status-0-matTypeName").val(matTypeNames.join(","));*/
                $("#inqu_status-0-matTypeNum").val(node.code);
                $("#inqu_status-0-matTypeName").val(node.text);
            },
        }
    }

    /**数据回显**/
    if("add" != __ei.type) {
        IPLAT.EFTreeInput.setAllFields( $("#inqu_status-0-matTypeId") , __eiInfo.get("inqu_status-0-matTypeId") ,__eiInfo.get("inqu_status-0-matTypeName"))
        $("#deptNum").val(__eiInfo.get("inqu_status-0-deptNum"));
        $("#deptName").val(__eiInfo.get("inqu_status-0-deptName"))
    }

    /** 科室自动补全 */
    $("#deptName").kendoAutoComplete({
        dataSource: getDataSources("MPTY01/selectDept", "dept",[{name:'deptName',id:"deptName"}]),
        dataTextField: "deptName",
        filter:"contains",
        select:function(e){
            let dataItem = this.dataItem(e.item.index());
            $("#deptNum").val(dataItem.deptNum);
        }
    });

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

            //校验科室
            if(!$("#deptName").val() || $("#deptName").val()== ""){
                NotificationUtil("科室名称不能为空", "error");
                return;
            }
            eiInfo.set("inqu_status-0-deptNum", $("#deptNum").val());
            eiInfo.set("inqu_status-0-deptName", $("#deptName").val());
            //eiInfo.set("inqu_status-0-matTypeName", eiInfo.get("inqu_status-0-matTypeId_textField"));

            //调用后台保存方法
            EiCommunicator.send("MPPZ0101", "save", eiInfo, {
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