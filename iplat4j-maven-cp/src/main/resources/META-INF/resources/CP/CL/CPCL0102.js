$(function (){
    if("add" != __ei.type){
        loadImage(__ei.billNo)
    }

    IPLATUI.EFGrid = {
        "result": {
            toolbarConfig: {
                pageable: false,
                hidden: false,//true 时，不显示功能按钮，但保留 setting 导出按钮
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [
                    {
                        name: "ADDCED", text: "新增", layout: "3",
                        click: function () {
                            resultGrid.addRow()
                        }
                    },
                    {
                        name: "DELCED", text: "删除", layout: "3",
                        click: function () {
                            var checkRows = resultGrid.getCheckedRows();
                            if (checkRows.length > 0) {
                                resultGrid.removeRows(checkRows);
                            } else {
                                IPLAT.NotificationUtil("请选择需要删除的内容")
                            }
                        }
                    }

                ]
            },
            dataBinding: function (e) {
                for (var i = 0, length = e.items.length; i < length; i++){
                    // if(isAvailable(e.items[i].returnDate)){
                    //     //e.items[i].returnDate = parseFloat(e.items[i].returnDate);
                    // }
                    // if(isAvailable(e.items[i].returnDesc)){
                    //     //e.items[i].returnDesc = parseFloat(e.items[i].returnDesc);
                    // }
                }
            },
        },
        "resultA":{
            pageable : false,
            exportGrid : false,
        }
    }



    $("#SAVEPR").on("click", function () {
        //获取参数*/
        var billNo =IPLAT.EFInput.value($("#inqu_status-0-billNo"));
        var isDeal = IPLAT.EFSelect.value($("#isDeal"));
        var eiInfo = new EiInfo();
        let validatorInqu = IPLAT.Validator({id : "inqu"});
        if(isDeal == ""){
            NotificationUtil("是否需要继续处理","error");
        }
        if (validatorInqu.validate()) {
            let validator = IPLAT.Validator({id : "result"});
            if (validator.validate()) {
                eiInfo.setByNode("result");
                eiInfo.set("billNo", billNo);
                eiInfo.set("isDeal", isDeal);
                //提交
                EiCommunicator.send("CPCL0102", "saveComplaintReturn", eiInfo, {
                    onSuccess: function (ei) {
                        closeCurrentWindow();
                        IPLAT.NotificationUtil(ei.msg)
                        window.parent["resultGrid"].dataSource.page(1);
                    }
                })
            }
        }
    });

    $("#CANCEL").on("click", function () {
        closeCurrentWindow();
    })

    //关闭窗口
    function closeCurrentWindow() {
        window.parent['popData2Window'].close();
    }
})


/**
 * 加载图片（投诉图片）
 * @param billNo
 */
function loadImage(billNo){
    var inInfo = new EiInfo();
    inInfo.set("billNo", billNo);
    EiCommunicator.send("CPDJ01", "showPic", inInfo, {
        onSuccess : function(ei) {
            let list = ei.get("list");
            $("#reqPic").html("");
            for (let i = 0; i < list.length; i++) {
                drawImage(list[i].base64);
            }
        }
    });
}
/**
 * 构建展示(图片回显)
 * @param imageBase64 ： 图片base64码
 * @param index ： 标记
 */
function drawImage(imageBase64) {
    if (imageBase64 == '' || imageBase64 == null || imageBase64 == 'null') {
        return;
    }
    let img = $('<img style="width:100px;height:100px" class="picMaintain">');
    img.attr('src', "data:image/jpeg;base64," + imageBase64);
    // 报修
    let pic = $("#reqPic");
    pic.append(img);

    $(".picMaintain").click(function() {
        let _this = $(this);// 将当前的pimg元素作为_this传入函数
        imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
    });
}


/**
 * 查看大图
 * @param outerdiv
 * @param innerdiv
 * @param bigimg
 * @param _this
 */
function imgShow(outerdiv, innerdiv, bigimg, _this){
    var src = _this.attr("src");// 获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性

    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function(){
        var windowW = $(window).width();//获取当前窗口宽度
        var windowH = $(window).height();//获取当前窗口高度
        var realWidth = this.width;//获取图片真实宽度
        var realHeight = this.height;//获取图片真实高度
        var imgWidth, imgHeight;
        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

        if(realHeight>windowH*scale) {//判断图片高度
            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW*scale;//再对宽度进行缩放
            }
        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
            imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
        } else {//如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
        $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放

        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
    });

    $(outerdiv).click(function(){//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
}
IPLATUI.EFDatePicker = {
    "inqu_status-0-returnDate": {
        min: new Date(__ei.dealDate) // 设置最小日期
    }
    // "inqu_status-0-m2": {
    //     max: new Date("2018-07-23") // 设置最大日期
    // }
};