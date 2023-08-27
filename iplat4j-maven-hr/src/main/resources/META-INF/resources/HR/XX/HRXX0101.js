$(function (){
        IPLAT.EFPopupInput.text($("#deptNum"),__ei.deptNum);
        IPLAT.EFPopupInput.text($("#serviceDeptNum"),__ei.serviceDeptNum);
        IPLATUI.EFGrid = {
            // 表格渲染按钮
            "resultC": {
                toolbarConfig: {
                    hidden: false,// true 时，不显示功能按钮，但保留 setting 导出按钮
                    add: false, cancel: false, save: false, 'delete': false,
                    buttons: [{
                        name: "addFile", text: "上传", layout: "3",
                        click: function () {
                            fileChooseWindow.open().center()
                        }
                    }, {
                        name: "delFile", text: "删除", layout: "3",
                        click: function () {
                            var checkRows = resultCGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                resultCGrid.removeRows(checkRows);
                            } else {
                                IPLAT.NotificationUtil("请选择需要删除的文件信息", "failure")
                            }
                        }
                    }, {
                        name: "downLoadFile", text: "下载", layout: "3",
                        click: function () {
                            downLoadFile()
                        }
                    }]
                }
            },
            "resultD": {
                toolbarConfig: {
                    hidden: false,// true 时，不显示功能按钮，但保留 setting 导出按钮
                    add: false, cancel: false, save: false, 'delete': false,
                }
            },
        }


    // 文件下载
    function downLoadFile(){
        var checkRows = resultCGrid.getCheckedRows();
        if (checkRows.length > 0) {
            for(var index in checkRows){
                var docId = checkRows[index].fileId;
                var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
                window.location.href = href;
            }
        } else {
            IPLAT.NotificationUtil("请选择需要下载的文件")
        }
    }


    //文件上传
    var fileList = [];
    var s=false;
    var files = ["jpg","jpeg","png","gif","bmp","txt","doc","docx",
        "xls","xlsx","ppt","pptx","pdf","zip","rar","7z","sql"];
    IPLATUI.EFUpload = {
        "hrxxFiles":{
            upload: function(e) {
                var file = e.files[0];
                if(file.size>1073741824){
                    NotificationUtil("文件不能大于1G,请重选", "error");
                    s=true;
                }
                var fileName=getExtension(file.name);
                if(files.indexOf(fileName)==-1){
                    NotificationUtil("文件格式(后缀名)不符合,请重选", "error");
                    s=true;
                }
            },
            showFileList:false,
            loadComplete: function(e) {
                var uploader = e.sender.uploader;
                uploader.clearAllFiles();
            },
            success: function(e) {
                debugger;
                if(s){
                    s=false;
                    return;
                };
                var date = new Date();
                var fileList = [];
                var file = e.files[0];
                var docId=e.response.docId;
                var fileNum=date .getFullYear()+"-"+(date .getMonth()+1)+"-"+date .getDate()+" "
                    +date .getHours()+":"+date .getMinutes()+":"+date .getSeconds();
                if(e.operation == 'upload') {
                    fileList.push({"fileName":file.name,"fileSize":file.size,"fileId":docId,"fileNum":fileNum});
                } else if (e.operation == 'remove') {
                    for (var i = 0; i < fileList.length; i++) {
                        if (fileList[i].fileId== file.docId) {
                            fileList.splice(i, 1);
                        }
                    }
                }
                var grid = $("#ef_grid_resultC").data("kendoGrid");
                if(fileList!=null){
                    //grid.removeRows(grid.getDataItems());
                    for(var i = 0; i < fileList.length; i++){
                        var model = createModel(i);
                        var par = fileList[i];
                        model["fileId"] = par.fileId;
                        model["fileName"] = par.fileName;
                        model["fileSize"] = par.fileSize;
                        model["fileDesc"] = "";
                        model["fileNum"] = fileNum;
                        grid.addRows(model);
                    }
                }
                fileChooseWindow.close();
            },
        }
    }

    //创建kendo.data.Model
    function createModel(id){
        var gridRow = kendo.data.Model.define({
            id: "uploadId",
            fields: {
                "fileId": {type: "string"},
                "fileName": {type: "string"},
                "fileSize": {type: "string"},
                "fileDesc": {type: "string"},
                "fileNum": {type: "string"}
            }
        });
        var model = new gridRow({uploadId:id});
        return model;
    }


    //确定
    $("#RESAVE").unbind('click').on('click', function(e){
        // 防止连续提交
        $("#RESAVE").attr("disabled",true);
        setTimeout(function(){$("#RESAVE").attr("disabled",false);},5000);
        var deptNum = IPLAT.EFPopupInput.text($("#deptNum"));
        var serviceDeptNum = IPLAT.EFPopupInput.text($("#serviceDeptNum"));
        var workNo = IPLAT.EFInput.value($("#workNo"));
        var realName = IPLAT.EFInput.value($("#realName"));
        var sex = IPLAT.EFSelect.value($("#sex"));
        var maritalStatus = IPLAT.EFSelect.value($("#maritalStatus"));  //婚姻状况
        var personnelCategory = IPLAT.EFSelect.value($("#personnelCategory")); //人员类别
        var birthPlace = IPLAT.EFInput.value($("#birthPlace"));
        var kampong = IPLAT.EFInput.value($("#kampong"));
        var manCode = IPLAT.EFInput.value($("#manCode"));
        var schoolingCode = IPLAT.EFInput.value($("#schoolingCode"));
        var jobCode = IPLAT.EFInput.value($("#jobCode"));
        var politicalStatus = IPLAT.EFInput.value($("#politicalStatus"));
        var salary = IPLAT.EFInput.value($("#salary"));
        var birthDate = IPLAT.EFInput.value($("#birthDate"));
        var preInTime = IPLAT.EFInput.value($("#preInTime"));
        var phone = IPLAT.EFInput.value($("#phone"));
        var highestEducational = IPLAT.EFInput.value($("#highestEducational")); //最高学历
        var highestDegree = IPLAT.EFInput.value($("#highestDegree")); //最高学位
        var emergency = IPLAT.EFInput.value($("#emergency")); //紧急联系人
        var emergencyPhone = IPLAT.EFInput.value($("#emergencyPhone")); //紧急联系人电话
        var health = IPLAT.EFInput.value($("#health"));
        var familyAddress = IPLAT.EFInput.value($("#familyAddress"));
        var company = IPLAT.EFInput.value($("#company"));
        var memo = IPLAT.EFInput.value($("#memo"));
        var id = IPLAT.EFInput.value($("#id"));
        var type = IPLAT.EFInput.value($("#type"));
        var fileArray = resultCGrid.getDataItems();
        //参数检验
      if(validate(workNo,realName,birthPlace,deptNum,serviceDeptNum,kampong,manCode,schoolingCode,jobCode,phone,health,familyAddress,company,personnelCategory,emergency,emergencyPhone)) {
          var eiInfo = new EiInfo();
          eiInfo.set("deptNum", deptNum);
          eiInfo.set("serviceDeptNum", serviceDeptNum);
          eiInfo.set("workNo", workNo);
          eiInfo.set("realName", realName);
          eiInfo.set("sex", sex);
          eiInfo.set("birthPlace", birthPlace);
          eiInfo.set("kampong", kampong);
          eiInfo.set("manCode", manCode);
          eiInfo.set("schoolingCode", schoolingCode);
          eiInfo.set("jobCode", jobCode);
          eiInfo.set("politicalStatus", politicalStatus);
          eiInfo.set("salary", salary);
          eiInfo.set("birthDate", birthDate);
          eiInfo.set("preInTime", preInTime);
          eiInfo.set("phone", phone);
          eiInfo.set("health", health);
          eiInfo.set("familyAddress", familyAddress);
          eiInfo.set("company", company);
          eiInfo.set("memo", memo);
          eiInfo.set("id", id);
          eiInfo.set("type", type);
          eiInfo.set("fileArray", fileArray);
          eiInfo.set("maritalStatus", maritalStatus);
          eiInfo.set("personnelCategory", personnelCategory);
          eiInfo.set("highestEducational", highestEducational);
          eiInfo.set("highestDegree", highestDegree);
          eiInfo.set("emergency", emergency);
          eiInfo.set("emergencyPhone", emergencyPhone);
          EiCommunicator.send("HRXX0101", "putUserInfo", eiInfo, {
              onSuccess: function (ei) {
                  if (ei.getStatus() == -1) {
                      NotificationUtil(ei.getMsg(), "error");
                      return;
                  }
                  // NotificationUtil("新增成功");
                  // window.parent['popDataWindow'].close();
                  // window.parent["resultGrid"].dataSource.page(1);
              }
          }),
          EiCommunicator.send("HRXX0101", "insert", eiInfo, {
              onSuccess: function (ei) {
                  if (ei.getStatus() == -1) {
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

    //参数校验
    function validate(workNo,realName,birthPlace,deptNum,serviceDeptNum,kampong,manCode,schoolingCode,jobCode,phone,health,familyAddress,company,personnelCategory,emergency,emergencyPhone) {
        // if(isEmpty(workNo)){
        //     NotificationUtil("工号不能为空","error");
        //     return false;
        // }
        if(isEmpty(realName)){
            NotificationUtil("姓名不能为空","error");
            return false;
        }
        // if(isEmpty(birthPlace)){
        //     NotificationUtil("籍贯不能为空","error");
        //     return false;
        // }
        if(isEmpty(deptNum)){
            NotificationUtil("所属部门不能为空","error");
            return false;
        }
        if(isEmpty(serviceDeptNum)){
            NotificationUtil("服务部门不能为空","error");
            return false;
        }
        if(isEmpty(kampong)){
            NotificationUtil("民族不能为空","error");
            return false;
        }
        if(isEmpty(manCode)){
            NotificationUtil("身份证号码不能为空","error");
            return false;
        }else if(isCardNo(manCode)){
            NotificationUtil("身份证号码格式不正确","error");
            return false;
        }
        if(isEmpty(schoolingCode)){
            NotificationUtil("学历不能为空","error");
            return false;
        }
        if(isEmpty(jobCode)){
            NotificationUtil("岗位不能为空","error");
            return false;
        }
        if(isEmpty(phone)){
            NotificationUtil("手机号不能为空","error");
            return false;
        }else if(checkPhone(phone)){
            NotificationUtil("请输入正确的手机号","error");
            return false;
        };
        if(isEmpty(health)){
            NotificationUtil("健康状况不能为空","error");
            return false;
        }
        if(isEmpty(familyAddress)){
            NotificationUtil("现住址不能为空","error");
            return false;
        }
        if(isEmpty(company)){
            NotificationUtil("公司名称不能为空","error");
            return false;
        }
        if(isEmpty(emergency)){
            NotificationUtil("紧急联系人不能为空","error");
            return false;
        }
        if(isEmpty(emergencyPhone)){
            NotificationUtil("紧急联系人电话不能为空","error");
            return false;
        }
        if(isEmpty(personnelCategory)){
            NotificationUtil("人员类别不能为空","error");
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

    /**
     * 简单的校验一下手机号
     * @param parameter
     * @returns {boolean}
     */
    function checkPhone(parameter) {
        if (!(/^1[3456789]\d{9}$/.test(parameter))) {
           return true;
        }
        return false;
    }

    /**
     * 检验身份证号码
     * @param parameter
     * @returns {boolean}
     */
    function isCardNo(parameter){
        // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if(reg.test(parameter) === false){
            return true;
        }
        return false;
    }

    function getExtension (name) {
        return name.substring(name.lastIndexOf(".")+1)
    }
});
