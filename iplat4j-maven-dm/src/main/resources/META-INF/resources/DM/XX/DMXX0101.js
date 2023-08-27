var picList = [];
$(function () {
    IPLATUI.EFGrid = {
        "results": {
            toolbarConfig: {
                pageable: false,
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "ADDCED", text: "新增", layout: "3",
                        click: function () {
                            resultsGrid.addRow()
                        }
                    },
                    {
                        name: "DELCED", text: "删除", layout: "3",
                        click: function () {
                            var checkRows = resultsGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                resultsGrid.removeRows(checkRows);
                            } else {
                                IPLAT.NotificationUtil("请选择需要删除的内容")
                            }
                        }
                    }

                ]
            },
        }
    }
    // 月租、管理费、年费默认为0
    IPLAT.EFInput.value($("#rent"),"0");
    IPLAT.EFInput.value($("#manageFee"),"0");
    IPLAT.EFInput.value($("#annualFee"),"0");
    $("#REGISTERDROM").unbind('click').on('click', function(){
        // 防止连续提交
        $("#REGISTERDROM").attr("disabled",true);
        setTimeout(function(){
            $("#REGISTERDROM").attr("disabled",false);
        },3000);

        var eiInfo = new EiInfo();
        var building = IPLAT.EFInput.value($("#building"));
        var floor = IPLAT.EFInput.value($("#floor"));
        var roomNo = IPLAT.EFInput.value($("#roomNo"));
        var roomName = building+floor+roomNo;
        var bedNum = IPLAT.EFInput.value($("#bedNum"));
        var dormProperties = IPLAT.EFSelect.value($("#dormProperties"));
        var typeCode = IPLAT.EFSelect.value($("#typeCode"));
        var openRoom = IPLAT.EFSelect.value($("#openRoom"));
        var elevatorRoom = IPLAT.EFSelect.value($("#elevatorRoom"));
        var priBathroom = IPLAT.EFSelect.value($("#priBathroom"));
        var dormPosition = IPLAT.EFSelect.value($("#dormPosition"));
        var dormArea = IPLAT.EFSelect.value($("#dormArea"));
        var rent = IPLAT.EFInput.value($("#rent"));
        var manageFee = IPLAT.EFInput.value($("#manageFee"));
        var annualFee = IPLAT.EFInput.value($("#annualFee"));
        var materials = IPLAT.EFInput.value($("#materials"));
        var note = IPLAT.EFInput.value($("#note"));
        //获取tab数据
        var htb = resultsGrid.getDataItems();

        //参数校验
        if(!validate(building,floor,roomNo,bedNum,dormProperties,typeCode,openRoom)){
            return;
        }
        eiInfo.set("building",building);
        eiInfo.set("floor",floor);
        eiInfo.set("roomNo",roomNo);
        eiInfo.set("roomName",roomName);
        eiInfo.set("bedNum",bedNum);
        eiInfo.set("typeCode",typeCode);
        eiInfo.set("openRoom",openRoom);
        eiInfo.set("dormProperties",dormProperties);
        eiInfo.set("elevatorRoom",elevatorRoom);
        eiInfo.set("priBathroom",priBathroom);
        eiInfo.set("dormPosition",dormPosition);
        eiInfo.set("dormArea",dormArea);
        eiInfo.set("rent",rent);
        eiInfo.set("manageFee",manageFee);
        eiInfo.set("annualFee",annualFee);
        eiInfo.set("note",note);
        eiInfo.set("materials",materials);
        eiInfo.set("picList",picList);
        eiInfo.set("htb", htb);
        // 执行后台DMXX0101.insert接口方法.
        EiCommunicator.send("DMXX0101", "insert", eiInfo, {
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

    /**
     * 打开上传图片窗口
     */
    $("#uploadPic").click(function(){
        picChooseWindow.open().center()
    });

});

/**
 * 文件上传（上传完工图片）
 * @type {{dmrePic: {localization: {invalidFileExtension: string}, loadComplete: IPLATUI.EFUpload.dmrePic.loadComplete, success: IPLATUI.EFUpload.dmrePic.success, validation: {allowedExtensions: string[]}}}}
 */
IPLATUI.EFUpload = {
    "dmrePic":{
        loadComplete: function(e) {
            var uploader = e.sender.uploader;
            uploader.clearAllFiles();
        },
        validation: {
            allowedExtensions: [".jpg","jpeg",".png",".gif",".bmp"]
        },
        localization: {
            invalidFileExtension: "文件格式不支持, 上传失败"
        },
        success: function(e) {
            var file = e.files[0];
            if(e.operation == 'upload') {
                picList.push({"fileName":file.name,"docId":e.response.docId,"base64":"","type":"RE","uid":file.uid});
            } else if (e.operation == 'remove') {
                for (let i = 0; i < picList.length; i++) {
                    if (picList[i].uid== file.uid) {
                        picList.splice(i, 1); i--;//i需要自减，否则每次删除都会讲原数组索引发生变化
                    }
                }
            }
            picChooseWindow.close();
            loadREPic();
        },
    }
}

/**
 * 加载宿舍图片
 */
function loadREPic(){
    var inInfo = new EiInfo();
    inInfo.set("picList",picList);
    EiCommunicator.send("DMXX0101", "showTempPic", inInfo, {
        onSuccess : function(ei) {
            var list = ei.get("list");
            $("#reqPic").html("")
            for (var i = 0; i < list.length; i++) {
                drawImage(list[i].base64);
            }
        }
    });
}

//参数校验
function validate(building,floor,roomNo,bedNum,dormProperties,typeCode,openRoom){
    if(isEmpty(building)){
        NotificationUtil("所填楼不能为空", "error");
        return false;
    }
    if(isEmpty(floor)){
        NotificationUtil("所填层不能为空", "error");
        return false;
    }
    if(isEmpty(roomNo)){
        NotificationUtil("所填宿舍号不能为空", "error");
        return false;
    }
    if(isEmpty(bedNum)){
        NotificationUtil("所填床位数不能为空", "error");
        return false;
    }
    if(isEmpty(dormProperties)){
        NotificationUtil("请选择宿舍属性", "error");
        return false;
    }
    if(isEmpty(typeCode)){
        NotificationUtil("请选择宿舍类型", "error");
        return false;
    }
    if(isEmpty(openRoom)){
        NotificationUtil("请选择是否开放选房", "error");
        return false;
    }
    return true;
}

// 判空函数.
function isEmpty(parameter){
    if(parameter == undefined || parameter == null){
        return true;
    } else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
        return true;
    } else {
        return false;
    }
}