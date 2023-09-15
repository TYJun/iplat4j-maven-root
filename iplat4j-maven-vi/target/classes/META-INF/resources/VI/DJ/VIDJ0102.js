var fileList = [];
var rowNo = 0;
$(function () {
    $("#pass").on("click", function (e) {
        var id = IPLAT.EFInput.value($("#inqu_status-0-id"));
        var interviewerWorkNo = IPLAT.EFInput.value($("#inqu_status-0-interviewerWorkNo"));
        let auditorMemo = IPLAT.EFInput.value($("#inqu_status-0-auditorMemo"));
        var eiInfo = new EiInfo();
        eiInfo.set("id", id);
        eiInfo.set("auditorMan", interviewerWorkNo);
        eiInfo.set("type", "pass");
        eiInfo.set("auditorClientType", "PC");
        eiInfo.set("auditorMemo", auditorMemo);
        EiCommunicator.send("VIDJ0102", "submit", eiInfo, {
            onSuccess: function (ei) {
                NotificationUtil(ei.getMsg(), "success");
                window.parent['popDataWindow'].close();
                window.parent.resultGrid.dataSource.page(1);
            }
        });
    })

    $("#reject").on("click", function (e) {
        var id = IPLAT.EFInput.value($("#inqu_status-0-id"));
        var eiInfo = new EiInfo();
        let auditorMemo = IPLAT.EFInput.value($("#inqu_status-0-auditorMemo"));
        if (auditorMemo == "") {
            IPLAT.alert("请填写审批意见");
            return
        } else {
            eiInfo.set("id", id);
            eiInfo.set("type", "reject");
            eiInfo.set("auditorMemo", auditorMemo);
            EiCommunicator.send("VIDJ0102", "submit", eiInfo, {
                onSuccess: function (ei) {
                    NotificationUtil(ei.getMsg(), "success");
                    window.parent['popDataWindow'].close();
                    window.parent.resultGrid.dataSource.page(1);
                }
            });
        }
    })
    setTimeout(function(){
        loadImage()
    }, 100);
});

IPLATUI.EFGrid = {
    "result": {
        pageable: false,
        exportGrid : false,
        columns: [
            {
                field: "uplaodPic",
                title: "访客照片",
                lock: true,
                enable: false,
                width: 60,
                template: "<span class='uploadPic'></span>"
            },
            // {
            //     field: "fileId",
            //     title: "操作",
            //     enable: false,
            //     width: 60,
            //     template: "<center><button class='custom i-btn-lg'>照片上传</button></center>"
            // },
        ],
        onCellClick: function (e) {
            // rowNo = e.row;
            // var visitId = e.model.visitId;
            // var span = document.getElementsByClassName("uploadPic")[rowNo]
            // var spanId = "reqPic" + visitId;
            // span.setAttribute("id",spanId)
            // if (e.field === 'fileId') {
            //     for (let i = 0; i <fileList.length; i++) {
            //         if (fileList[i].visitId == visitId){
            //             IPLAT.alert("一个访客只能上传一个照片");
            //             return
            //         }
            //     }
            //     picChooseWindow.open().center()
            // }
        },
        loadComplete: function (grid) {
            // var rows = grid.getDataItems();
            // for (let i = 0; i < rows.length; i++) {
            //     var span = document.getElementsByClassName("uploadPic")[i]
            //     var spanId = "reqPic" + rows[i].uid;
            //     span.setAttribute("id", spanId)
            //     $(spanId).html("")
            //     drawImage(rows[i].fileContent, rows[i].uid);
            // }
        },
        "resultFile": {
            exportGrid : false,
            toolbarConfig: {
                hidden: false,
                add: false, cancel: false, save: false, 'delete': false,
                buttons: [{
                    name: "downLoad", text: "下载", layout: "3",
                    click: function () {

                    }
                }]
            }
        }
    }
}

/**
 * 构建展示(图片回显)
 * @param imageBase64 ： 图片base64码
 * @param index ： 标记
 */
function drawImage(imageBase64, visitId) {
    if (imageBase64 == '' || imageBase64 == null || imageBase64 == 'null') {
        return;
    }
    let img = $('<img style="width:100px;height:100px;" class="picMaintain">');
    img.attr('src', "data:image/jpeg;base64," + imageBase64);
    // 报修
    var spanId = "#reqPic" + visitId;
    let pic = $(spanId);
    pic.append(img);
    $(".picMaintain").click(function () {
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
function imgShow(outerdiv, innerdiv, bigimg, _this) {
    var src = _this.attr("src");// 获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性

    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function () {
        var windowW = $(window).width();//获取当前窗口宽度
        var windowH = $(window).height();//获取当前窗口高度
        var realWidth = this.width;//获取图片真实宽度
        var realHeight = this.height;//获取图片真实高度
        var imgWidth, imgHeight;
        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

        if (realHeight > windowH * scale) {//判断图片高度
            imgHeight = windowH * scale;//如大于窗口高度，图片高度进行缩放
            imgWidth = imgHeight / realHeight * realWidth;//等比例缩放宽度
            if (imgWidth > windowW * scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW * scale;//再对宽度进行缩放
            }
        } else if (realWidth > windowW * scale) {//如图片高度合适，判断图片宽度
            imgWidth = windowW * scale;//如大于窗口宽度，图片宽度进行缩放
            imgHeight = imgWidth / realWidth * realHeight;//等比例缩放高度
        } else {//如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
        $(bigimg).css("width", imgWidth);//以最终的宽度对图片缩放

        var w = (windowW - imgWidth) / 2;//计算图片与窗口左边距
        var h = (windowH - imgHeight) / 2;//计算图片与窗口上边距
        $(innerdiv).css({"top": h, "left": w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
    });

    $(outerdiv).click(function () {//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });
}

/**
 * 加载图片（访客图片）
 * @param billNo
 */
function loadImage(){
    var rows = __ei.result
    if (rows != undefined){
        for (let i = 0; i < rows.length; i++) {
            var span = document.getElementsByClassName("uploadPic")[i]
            var spanId = "reqPic" + rows[i].id;
            span.setAttribute("id",spanId)
            $(spanId).html("")
            var base64List = rows[i].base64List;
            if (base64List != undefined){
                for (let j = 0; j < base64List.length; j++) {
                    drawImage(rows[i].id,base64List[j])
                }
            }
        }
    }
}
/**
 * 构建展示(图片回显)
 * @param imageBase64 ： 图片base64码
 * @param index ： 标记
 */
function drawImage(visitId,imageBase64) {
    if (imageBase64 == '' || imageBase64 == null || imageBase64 == 'null') {
        return;
    }
    let img = $('<img style="width:100px;height:100px" class="picMaintain">');
    img.attr('src', "data:image/jpeg;base64," + imageBase64);
    // 报修
    var spanId = "#reqPic" + visitId;
    let pic = $(spanId);
    pic.append(img);
    $(".picMaintain").click(function() {
        let _this = $(this);// 将当前的pimg元素作为_this传入函数
        imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
    });
}