$(function() {
    var current = 0;
    // 取消按钮
    $("#CANCEL").on("click", function() {
        window.parent["popDataWindow"].close();
    });
    setTimeout(function(){
        loadImage()
    }, 500);

    $("#innerdiv").on("click", function(e) {
        current = current + 90;
        $("#bigimg").css("transform","rotate("+current+"deg)");
        e.stopPropagation()
    });
})

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
                template: "<span class='uploadPic'></span>"
            },
        ],
        onCellClick: function (e) {
            var row = e.row;
            if (e.field === "guestName") {
                var rows = __ei.result;
                if (rows[row].base64List != undefined){
                    popDataWindow.setOptions({"title": "访客照片详情"});
                    popDataWindow.open().center();
                    $("#uploadPicA").html("")
                    var span = document.getElementsByClassName("uploadPicA")[0]
                    span.setAttribute("id","uploadPicA");
                    for (var i = 0; i < rows[row].base64List.length; i++) {
                        var imageBase64 = rows[row].base64List[i];
                        if (imageBase64 == '' || imageBase64 == null || imageBase64 == 'null') {
                            return;
                        }
                        let img = $('<img style="width:100px;height:100px" class="picMaintain">');
                        img.attr('src', "data:image/jpeg;base64," + imageBase64);
                        console.log(img)
                        var spanId = "#uploadPicA";
                        let pic = $(spanId);
                        pic.append(img);
                        $(".picMaintain").click(function() {
                            let _this = $(this);// 将当前的pimg元素作为_this传入函数
                            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
                        });
                    }
                } else {
                    IPLAT.alert("该访客未上传照片！")
                }
            }
        },
        loadComplete: function (grid) {

        }
    },
    "resultA": {
        pageable: false,
        exportGrid : false,
        columns: [
            {
                field: "uplaodPicA",
                title: "访客照片",
                lock: true,
                enable: false,
                template: "<span class='uploadPicA'></span>"
            },
        ],
        loadComplete: function (grid) {

        }
    },
    "resultFile": {
        exportGrid : false,
        toolbarConfig: {
            hidden: false,
            add: false, cancel: false, save: false, 'delete': false,
            buttons: [{
                name: "download", text: "下载", layout: "3",
                click: function () {
                    downLoadFile()
                }
            }]
        }
    }
}

/**
 * 加载图片（访客图片）
 * @param billNo
 */
function loadImage(){
    var rows = __ei.result
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
        } else {
            let img = $('<img style="width:100px;height:100px" alt="未上传图片" class="picMaintain">');
            var spanId = "#reqPic" + rows[i].id;
            let pic = $(spanId);
            pic.append(img);
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
        var scale = 0.9;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

        if (realWidth>windowH*scale){
            imgWidth = windowH*scale;
            imgHeight = imgWidth/realWidth*realHeight;
        } else if(realHeight>windowH*scale) {//判断图片高度
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

        $("#outerdiv").click(function(){//再次点击淡出消失弹出层
            $(this).fadeOut("fast");
        });
    });
}

// 文件下载
function downLoadFile(){
    var checkRows = resultFileGrid.getCheckedRows();
    console.log(checkRows)
    if (checkRows.length > 0) {
        for(var index in checkRows){
            var docId = checkRows[index].fileId;
            var href = IPLATUI.CONTEXT_PATH + "/EU/DM/EUDM06.jsp?docId=" + docId;
            window.location.href = href;
        }
    } else if (checkRows.length == 0){
        IPLAT.NotificationUtil("请选择需要下载的文件")
    }
}