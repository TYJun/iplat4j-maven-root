/*********************************************************宿舍图片（开始）*************************************************/

/**
 * 加载图片（登记图片）
 * @param roomId
 */
function loadImage(roomId){
    var inInfo = new EiInfo();
    inInfo.set("roomId", roomId);
    EiCommunicator.send("DMXX0101", "showPic", inInfo, {
        onSuccess : function(ei) {
            var list = ei.get("list");
            if(E("#reqPic")){
                $("#reqPic").html("");
            }
            for (let i = 0; i < list.length; i++) {
                console.log("pic: \n " + list[i].base64);
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
    var img = $('<img style="width:100px;height:100px" class="picDormRoom">');
    img.attr('src', "data:image/jpeg;base64," + imageBase64);
    var pic = $("#reqPic");
    pic.append(img);
    $(".picDormRoom").click(function() {
        var _this = $(this);// 将当前的pimg元素作为_this传入函数
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
/*********************************************************宿舍图片（结束）*************************************************/

/**********************************************************通用方法*************************************************/

/**
 * 将base64字符串转换成数据流
 * @param base64 ： 文件base64字符串
 * @returns {Blob} ： 数据流
 */
function base64ToBlob(base64) {
    base64 = 'data:audio/amr;base64,' + base64
    let arr = base64.split(",");
    let mime = arr[0].match(/:(.*?);/)[1];
    let bytes = window.atob(arr[1]);
    let ab = new ArrayBuffer(bytes.length);
    let ia = new Uint8Array(ab);
    for(let i=0; i< bytes.length; i++) {
        ia[i] = bytes.charCodeAt(i);
    }
    return new Blob([ab],{type : mime});
}

/**
 * 获取javaScirpt元素对象
 * @param selector
 * @returns {*}
 * @constructor
 */
function E(selector) {
    return document.querySelector(selector);
}

/**
 * 判断字符串是否为空
 * @param parameter ： 字符串参数
 * @returns {boolean}
 */
function isEmpty(parameter){
    if(parameter == undefined || parameter == null){
        return true;
    } else if (parameter.replace(/(^\s*)|(\s*$)/g, "") == ""){
        return true;
    } else {
        return false;
    }
}

/**
 * 判断元素是否存在
 * @param elementId
 * @returns {boolean}
 */
function isExistElement(elementId) {
    return $("#" + elementId).length>0 ? true : false;
}