$(function () {
    var onSelect = function (e) {
        var color = $(e.item).data("color");
        // var selector = $("#flatpicker");
        // var picker = selector.data("kendoFlatColorPicker");
        if (color) {
            // picker.value(color);
            // selector.css("display","block");
            colorpickerwindow.open();
        }
    }

    // expand不起作用是因为click事件被绑定了两次
    // 因为加载了两个EFPage
    window.panelbar = $("#panelbar").kendoPanelBar({
        expandMode: "single",
        animation: false,
        select: onSelect
    }).data("kendoPanelBar");

    // var colorpicker = $("#flatpicker").kendoFlatColorPicker({
    //     opacity: true,
    //     buttons: true
    // }).data("kendoFlatColorPicker");

    // $("#flatpicker .apply").on("click",function () {
    //     var selected = $(panelbar.select()[0]);
    //     var data = selected.data();
    //     var gradient = data.gradient;
    //     var clazz = data.class;
    //     var clazznot = data.classnot;
    //     var chooseColor = colorpicker.value();
    //     var elements = clazznot ? $(clazz + ":not(" + clazznot + ")") : $(clazz);
    //     var property = data.property;
    //     selected.data("color",chooseColor);
    //     if (gradient) {
    //         if (gradient === "left") elements.css("background", "linear-gradient(to right, " + chooseColor + ", " + selected.next().data("color") + ")");
    //         else elements.css("background", "linear-gradient(to right, " + selected.prev().data("color") + ", " + chooseColor + ")");
    //     } else {
    //         elements.css(property, chooseColor);
    //     }
    // })
    //
    // $("#flatpicker .cancel").on("click", function () {
    //     var selector = $("#flatpicker");
    //     selector.css("display","none");
    // });

    window.colorpickerwindow = $("#colorpicker").kendoWindow({
        title: "标准色盘",
        actions: ["close"],
        autoFocus: false,
        resizable: true,
        width: "80%",
        height: "80%",
        visible: false,
        position: {top: "10%", left: "10%"}
    }).data("kendoWindow");

    var template = kendo.template($("#colorTemplate").html());
    colorpickerwindow.content(template(COLORS))

    IPLATUI.EFUpload = {
        file1: {
            validation: {
                allowedExtensions: [".null"], //文件格式过滤
                maxFileSize: 200,
                minFileSize: 50
            }
        }
    };

    IPLATUI.EFGrid = {
        "result": {
            toolbarConfig: {
                add:true,
                buttons: [{
                    text: "按钮",
                    name: "button"
                }]
            }
        }
    }

    var notifyTemplate = kendo.template($("#notification-template").html())({
        isCopy: true,
        isLock: true,
        isState: true,
        isAPM: true
    });

    var msgTemplate = kendo.template($("#msg-template").html());

    var notificationWidget = $("#notification-util").kendoNotification({
        // 动画
        // animation: {
        //     open: {
        //         effects: "slideIn:down",
        //         duration: 700
        //     },
        //     close: {
        //         effects: "slideIn:down",
        //         duration: 700,
        //         reverse: true
        //     }
        // },
        position: {
            top: 0,
            left: IPLATUI.Config.Notification.LEFT
        },
        allowHideAfter: 0,
        // 设置默认不关闭
        autoHideAfter: 0,
        button: true,
        stacking: "up",
        width: IPLATUI.Config.Notification.WIDTH,
        templates: [
            {
                type: 'success',
                template: notifyTemplate
            },
            {
                type: 'info',
                template: notifyTemplate
            },
            {
                type: 'error',
                template: notifyTemplate
            },
            {
                type: 'warning',
                template: notifyTemplate
            }
        ],
        hideOnClick: false
    }).data("kendoNotification");

    $("#notification").kendoNotification({
        position: {
            top: 20,
            right: 20
        },
        stacking: "down"
    });

    $("#notification1").click(function (){
        notificationWidget.hide();
        notificationWidget.show(msgTemplate({
            msg: "这是一条信息",
            detailMsg: "",
            msgKey: "",
            showMsg: false
        }),"info");
    })

    $("#notification2").click(function (){
        notificationWidget.hide();
        notificationWidget.show(msgTemplate({
            msg: "这是一条警告",
            detailMsg: "",
            msgKey: "",
            showMsg: false
        }),"warning");
    })

    $("#notification3").click(function (){
        notificationWidget.hide();
        notificationWidget.show(msgTemplate({
            msg: "这是一条错误",
            detailMsg: "",
            msgKey: "",
            showMsg: false
        }),"error");
    })

    $("#notification4").click(function (){
        notificationWidget.hide();
        notificationWidget.show(msgTemplate({
            msg: "这是一条成功提示",
            detailMsg: "",
            msgKey: "",
            showMsg: false
        }),"success");
    })

    $("#prompt1").click(function (){
        IPLAT.prompt("询问");
    })

    $("#alert1").click(function (){
        IPLAT.alert("提示");
    })

    $("#GENERATE").click(function (){
        IPLAT.prompt({
            message:'输入主题名（不填默认ant）：',
            okFn:function (theme) {
                if (IPLAT.isBlankString(theme)) {
                    theme = "ant";
                }
                const re = new RegExp(".i-theme-ant", "g");

                var cssJson = window.cssJson;
                var progressElement = $("body");
                IPLAT.progress(progressElement, true);
                var nodeList = document.querySelectorAll("li[data-dirty=true]");
                for (var i = 0; i < nodeList.length; i++) {
                    var dataset = nodeList[i].dataset;
                    var prefix = dataset.extraValue;
                    var suffix = dataset.suffix;
                    var property = dataset.property.split(",");
                    var addition = dataset.addition;
                    var group = dataset.group ? cssGroup[dataset.group] : [dataset.class];
                    for (var j = 0; j < group.length; j++) {
                        var val = (prefix ? (prefix + " " + dataset.color) : dataset.color) + (suffix ? (" " + suffix) : "")
                        if (Object.prototype.hasOwnProperty.call(cssJson, group[j])) {
                            var classObj = cssJson[group[j]];
                            for (var k = 0; k < property.length; k++) {
                                console.log(group[j] + "类的" + property[k] + "属性更新前", classObj[property[k]]);
                                // if (Object.prototype.hasOwnProperty.call(classObj, property[k])) {
                                //     classObj[property[k]] = val;
                                // }
                                classObj[property[k]] = val;
                                console.log(group[j] + "类的" + property[k] + "属性更新后", classObj[property[k]]);
                            }
                        } else {
                            for (var g = 0; g < property.length; g++) {
                                console.log("添加新类：" + group[j] + ",新属性：" + property[g] + ",值：" + val);
                                cssJson[group[j]] = {
                                    [property[g]]: val
                                }
                                console.log(cssJson[group[j]])
                            }
                        }
                        if (addition){
                            // 额外的属性
                            var temp = addition.split("@");
                            var css = cssJson[temp[0]];
                            console.log(temp[0] + "类的" + temp[1] + "属性更新前", css[temp[1]]);
                            css[temp[1]] = val;
                            console.log(temp[0] + "类的" + temp[1] + "属性更新后", css[temp[1]]);
                        }
                    }
                }

                window.newCssStr = theme === "ant" ? json2css(cssJson) : json2css(cssJson).replace(re, ".i-theme-" + theme);

                var element = document.createElement('a');
                element.setAttribute('href', 'data:text/css;charset=utf-8,' + encodeURIComponent(window.newCssStr));
                element.setAttribute('download', "iplat.ui." + theme + ".min.css");
                element.style.display = 'none';
                document.body.appendChild(element);
                element.click();
                document.body.removeChild(element);
                IPLAT.progress(progressElement, false);
            }
        })

    })
});

window.onload = function () {
    // var ts = $("#c-layout-tab").data("kendoTabStrip")
    // ts.disable(ts.tabGroup.children())

    $(".kendotooltip").kendoTooltip({autoHide: true});

    $("#notification1").trigger("click");

    getCss();
}

function getCss() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = () => {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            window.cssStr = xmlhttp.responseText;
            window.cssJson = css2json(xmlhttp.responseText);
        }
    }
    // 向服务器发送请求
    xmlhttp.open('GET', IPLATUI.CONTEXT_PATH + "/iplatui/css/iplat.ui.ant.min.css", true);
    xmlhttp.send();
}

function hexToRgb(hex) {
    if (isNaN(parseInt("0x" + hex.slice(1, 3)))) return ""
    return "rgb(" + parseInt("0x" + hex.slice(1, 3)) + "," + parseInt("0x" + hex.slice(3, 5)) + "," + parseInt("0x" + hex.slice(5, 7)) + ")";
}

function setColor(colorName, hex, element) {
    // var rgb = hexToRgb(hex);
    var selected = element ? element : $(panelbar.select()[0]);
    var repairCall = !!element;
    var data = selected.data();
    var group = data.group;
    var clazz = data.class;
    var addition = data.addition;
    var repair = data.repair;
    var chooseColor = hex;
    var property = data.property;
    var propertyList = property.split(",");
    var extraValue = data.prefix;
    var suffix = data.suffix;
    if (data.color.toLowerCase() !== chooseColor.toLowerCase() || repairCall) {
        var style = document.createElement("style");

        var tempClazz = group ? false : clazz;
        var loopLen = (group ? cssGroup[group].length : 1);
        for (var i = 0; i < loopLen; i++) {
            style.innerHTML += (tempClazz ? tempClazz : cssGroup[group][i]) + "{";
            for (var j = 0; j < propertyList.length; j++) {
                style.innerHTML += propertyList[j] + ":" + (extraValue ? (extraValue + " " + chooseColor) : chooseColor) + (suffix ? (" " + suffix) : "") + ";";
            }
            style.innerHTML += "}";
        }
        if (addition) {
            var temp = addition.split("@");
            style.innerHTML += temp[0] + "{" + temp[1] + ":" + chooseColor + ";}";
        }
        document.head.appendChild(style);
        selected.attr("data-color", chooseColor);
        selected.attr("data-dirty", true);
        selected.attr("data-colorOld", data.color);
        $("#notification").getKendoNotification().show("样式已更新");
        colorpickerwindow.close();
        if (repair) {
            var ele = $(repair);
            setColor("", ele.attr("data-color"), ele);
        }
    }
}

function css2json(css) {

    // 移除CSS所有注释
    while ((open = css.indexOf("/*")) !== -1 && (close = css.indexOf("*/")) !== -1) {
        css = css.substring(0, open) + css.substring(close + 2);
    }
    // 初始化返回值
    var json = {};

    while (css.length > 0) {
        // 存储第一个左/右花括号的下标
        var lbracket = css.indexOf('{'); //第一个匹配到的 { 符号索引
        var childBracket = css.indexOf('{', lbracket + 1); //第二个 { 符号的索引
        var rbracket = css.indexOf('}'); //第一个匹配到的 } 符号索引
        var hasChild = false;  //flag  是否有嵌套关系存在
        //判断是否有嵌套  如果第一个匹配到的 } 在 第二个匹配到的 { 之后，说明有嵌套关系
        if (rbracket > childBracket) {
            hasChild = true;
            var startSearchIndex = lbracket;

            for (var i = 0; i < css.length; i++) {
                var firstRight = css.indexOf("}", startSearchIndex + 1);  //从起始位开始 第一个匹配到的 }
                var secondRight = css.indexOf("}", firstRight + 1); //从起始位开始 第二个匹配到的 }
                var firstLeft = css.indexOf("{", firstRight + 1); //第一个匹配到的 } 之后，下一个 {
                //如果 firstLeft>secondRight 说明 两个 } 结束符中间，没有{，也就说嵌套结束
                if (firstLeft > secondRight) {
                    rbracket = secondRight;
                    break;
                }
                startSearchIndex = firstLeft;
            }

        }

        // 第一步：将声明转换为Object，如：
        // `font: 'Times New Roman' 1em; color: #ff0000; margin-top: 1em;`
        //  ==>
        // `{"font": "'Times New Roman' 1em", "color": "#ff0000", "margin-top": "1em"}`

        // 辅助方法：将array转为object
        function toObject(array) {
            var ret = {};
            array.forEach(e => {
                var index = e.indexOf(':');
                var property = e.substring(0, index).trim();
                var value = e.substring(index + 1).trim();
                ret[property] = value;
            });
            return ret;
        }

        // 切割声明块并移除空白符，然后放入数组中
        var declarations = css.substring(lbracket + 1, rbracket)
            .split(/;(?![^()]*\))/g)
            .map(e => e.trim())
            .filter(e => e.length > 0); // 移除所有""空值
        // 转为Object对象
        declarations = toObject(declarations);


        // 第二步：选择器处理，每个选择器会与它对应的声明相关联，如：
        // `h1, p#bar {color: red}`
        // ==>
        // {"h1": {color: red}, "p#bar": {color: red}}

        /**
         * 该方法会把","分割的class拆分成两个class      .jxx,.jxx1{color:#fff;}   ==>  .jxx{color:#fff;} .jxx1{color:#fff;}
         var selectors = css.substring(0, lbracket)
         // 以,切割，并移除空格：`"h1, p#bar, span.foo"` => ["h1", "p#bar", "span.foo"]
         .split(",")
         .map(selector => selector.trim());
         */

            //该方法 不切割拆分class    .jxx,.jxx1{color:#fff;}   ==>  .jxx,.jxx1{color:#fff;}
            // var selectors = [css.substring(0, lbracket).replace(/\r\n/g, "").replace(/\n/g, "")];  //替换掉class的换行符
        var selectors = [css.substring(0, lbracket)];
        // 迭代赋值
        selectors.forEach(selector => {
            // 若不存在，则先初始化
            if (!json[selector]) json[selector] = {};
            // 赋值到JSON
            Object.keys(declarations).forEach(key => {
                json[selector][key] = declarations[key];
            });
        });

        // 继续下个声明块
        css = css.slice(rbracket + 1).trim();
    }

    // 返回JSON形式的结果串
    return json;
}

function json2css(json) {
    var cssStr = "";
    for (const className in json) {
        cssStr += className + "{";
        const attr = json[className];
        for (const attrName in attr) {
            cssStr += attrName + ":" + attr[attrName] + ";";
        }
        cssStr += "}";
    }
    cssStr = cssStr.replace(/: };/g, "}").replace(/:};/g, "}");
    return cssStr;
}

var COLORS = [
    {
        groupName: "彩色系",
        groupDesc: "",
        child: [
            {
                itemName: "Heaven Blue / 天空蓝",
                itemDesc: "包容、博大、科技、理智",
                colors: [
                    {
                        colorName: "Blue+5",
                        hex: "#EFF8FF"
                    },
                    {
                        colorName: "Blue+4",
                        hex: "#DBEFFF"
                    },
                    {
                        colorName: "Blue+3",
                        hex: "#C0DCFF"
                    },
                    {
                        colorName: "Blue+2",
                        hex: "#99D2FF"
                    },
                    {
                        colorName: "Blue+1",
                        hex: "#599FF6"
                    },
                    {
                        colorName: "Blue-Base",
                        hex: "#3088F4"
                    },
                    {
                        colorName: "Blue-1",
                        hex: "#2B76DB"
                    },
                    {
                        colorName: "Blue-2",
                        hex: "#335CA1"
                    },
                    {
                        colorName: "Blue-3",
                        hex: "#2C539D"
                    },
                    {
                        colorName: "Blue-4",
                        hex: "#204E9B"
                    },
                ]
            },
            {
                itemName: "Aurora Cyan / 极光青",
                itemDesc: "神秘、变化、智慧、希望",
                colors: [
                    {
                        colorName: "Cyan+5",
                        hex: "#D7F3EF"
                    },
                    {
                        colorName: "Cyan+4",
                        hex: "#B9EAE2"
                    },
                    {
                        colorName: "Cyan+3",
                        hex: "#99DFD6"
                    },
                    {
                        colorName: "Cyan+2",
                        hex: "#7CD7CA"
                    },
                    {
                        colorName: "Cyan+1",
                        hex: "#5ECEBD"
                    },
                    {
                        colorName: "Cyan-Base",
                        hex: "#36C2AD"
                    },
                    {
                        colorName: "Cyan-1",
                        hex: "#2EA593"
                    },
                    {
                        colorName: "Cyan-2",
                        hex: "#289182"
                    },
                    {
                        colorName: "Cyan-3",
                        hex: "#237E71"
                    },
                    {
                        colorName: "Cyan-4",
                        hex: "#1E6B5F"
                    },
                ]
            },
            {
                itemName: "Meadow Green / 野原绿",
                itemDesc: "创新、生机、自然、坚韧",
                colors: [
                    {
                        colorName: "Green+5",
                        hex: "#DEF0D4"
                    },
                    {
                        colorName: "Green+4",
                        hex: "#C5E5B4"
                    },
                    {
                        colorName: "Green+3",
                        hex: "#ACDA93"
                    },
                    {
                        colorName: "Green+2",
                        hex: "#93CF73"
                    },
                    {
                        colorName: "Green+1",
                        hex: "#7AC453"
                    },
                    {
                        colorName: "Green-Base",
                        hex: "#59B528"
                    },
                    {
                        colorName: "Green-1",
                        hex: "#4C9A22"
                    },
                    {
                        colorName: "Green-2",
                        hex: "#43881E"
                    },
                    {
                        colorName: "Green-3",
                        hex: "#3A761A"
                    },
                    {
                        colorName: "Green-4",
                        hex: "#316316"
                    },
                ]
            },
            {
                itemName: "Autumn Gold / 秋叶金",
                itemDesc: "高贵、活力、温暖、愉悦",
                colors: [
                    {
                        colorName: "Gold+5",
                        hex: "#FCEBD8"
                    },
                    {
                        colorName: "Gold+4",
                        hex: "#FADBBA"
                    },
                    {
                        colorName: "Gold+3",
                        hex: "#F8CC9C"
                    },
                    {
                        colorName: "Gold+2",
                        hex: "#F6BD7F"
                    },
                    {
                        colorName: "Gold+1",
                        hex: "#F4AD61"
                    },
                    {
                        colorName: "Gold-Base",
                        hex: "#F1993A"
                    },
                    {
                        colorName: "Gold-1",
                        hex: "#CD8231"
                    },
                    {
                        colorName: "Gold-2",
                        hex: "#B5732B"
                    },
                    {
                        colorName: "Gold-3",
                        hex: "#9D6426"
                    },
                    {
                        colorName: "Gold-4",
                        hex: "#845420"
                    },
                ]
            },
            {
                itemName: "Sunset Rouge / 落日红",
                itemDesc: "奋斗、奔放、澎湃、吉祥",
                colors: [
                    {
                        colorName: "Rouge+5",
                        hex: "#FADCDE"
                    },
                    {
                        colorName: "Rouge+4",
                        hex: "#F7C2C5"
                    },
                    {
                        colorName: "Rouge+3",
                        hex: "#F3A7AC"
                    },
                    {
                        colorName: "Rouge+2",
                        hex: "#F08D94"
                    },
                    {
                        colorName: "Rouge+1",
                        hex: "#ED737B"
                    },
                    {
                        colorName: "Rouge-Base",
                        hex: "#E8505A"
                    },
                    {
                        colorName: "Rouge-1",
                        hex: "#C5444D"
                    },
                    {
                        colorName: "Rouge-2",
                        hex: "#AE3C43"
                    },
                    {
                        colorName: "Rouge-3",
                        hex: "#97343B"
                    },
                    {
                        colorName: "Rouge-4",
                        hex: "#7F2C31"
                    },
                ]
            },
            {
                itemName: "Fuscia Pink / 阳春粉",
                itemDesc: "明快、感性、浪漫、青春",
                colors: [
                    {
                        colorName: "Pink+5",
                        hex: "#FDE1F3"
                    },
                    {
                        colorName: "Pink+4",
                        hex: "#FCCAE9"
                    },
                    {
                        colorName: "Pink+3",
                        hex: "#FAB3E0"
                    },
                    {
                        colorName: "Pink+2",
                        hex: "#F89CD7"
                    },
                    {
                        colorName: "Pink+1",
                        hex: "#F785CD"
                    },
                    {
                        colorName: "Pink-Base",
                        hex: "#F567C1"
                    },
                    {
                        colorName: "Pink-1",
                        hex: "#D058A4"
                    },
                    {
                        colorName: "Pink-2",
                        hex: "#B84D91"
                    },
                    {
                        colorName: "Pink-3",
                        hex: "#9F437E"
                    },
                    {
                        colorName: "Pink-4",
                        hex: "#87396A"
                    },
                ]
            },
            {
                itemName: "Dusk Purple / 日暮紫",
                itemDesc: "高雅、梦幻、变换、内涵",
                colors: [
                    {
                        colorName: "Purple+5",
                        hex: "#E9DFFA"
                    },
                    {
                        colorName: "Purple+4",
                        hex: "#D9C8F6"
                    },
                    {
                        colorName: "Purple+3",
                        hex: "#C8AFF2"
                    },
                    {
                        colorName: "Purple+2",
                        hex: "#B797EF"
                    },
                    {
                        colorName: "Purple+1",
                        hex: "#A780EB"
                    },
                    {
                        colorName: "Purple-Base",
                        hex: "#9160E6"
                    },
                    {
                        colorName: "Purple-1",
                        hex: "#7B52C4"
                    },
                    {
                        colorName: "Purple-2",
                        hex: "#6D48AC"
                    },
                    {
                        colorName: "Purple-3",
                        hex: "#5E3E96"
                    },
                    {
                        colorName: "Purple-4",
                        hex: "#50357E"
                    },
                ]
            },
            {
                itemName: "Ocean Azure / 沧海碧",
                itemDesc: "高雅、梦幻、变换、内涵",
                colors: [
                    {
                        colorName: "Azure+5",
                        hex: "#CCE7E9"
                    },
                    {
                        colorName: "Azure+4",
                        hex: "#A6D5D8"
                    },
                    {
                        colorName: "Azure+3",
                        hex: "#7FC3C7"
                    },
                    {
                        colorName: "Azure+2",
                        hex: "#59B2B7"
                    },
                    {
                        colorName: "Azure+1",
                        hex: "#33A0A6"
                    },
                    {
                        colorName: "Azure-Base",
                        hex: "#008890"
                    },
                    {
                        colorName: "Azure-1",
                        hex: "#00747B"
                    },
                    {
                        colorName: "Azure-2",
                        hex: "#00666C"
                    },
                    {
                        colorName: "Azure-3",
                        hex: "#00595E"
                    },
                    {
                        colorName: "Azure-4",
                        hex: "#004B4F"
                    },
                ]
            }
        ]
    },
    {
        groupName: "中性色",
        groupDesc: "",
        child: [
            {
                itemName: "Mono / 黑白灰",
                itemDesc: "平衡了可读性、美感以及可用性",
                colors: [
                    {
                        colorName: "White",
                        hex: "#FFFFFF"
                    },
                    {
                        colorName: "Mono-1",
                        hex: "#F7F7F7"
                    },
                    {
                        colorName: "Mono-2",
                        hex: "#F0F0F0"
                    },
                    {
                        colorName: "Mono-3",
                        hex: "#E8E8E8"
                    },
                    {
                        colorName: "Mono-4",
                        hex: "#D9D9D9"
                    },
                    {
                        colorName: "Mono-5",
                        hex: "#BFBFBF"
                    },
                    {
                        colorName: "Mono-6",
                        hex: "#A6A6A6"
                    },
                    {
                        colorName: "Mono-7",
                        hex: "#8C8C8C"
                    },
                    {
                        colorName: "Mono-8",
                        hex: "#737373"
                    },
                    {
                        colorName: "Mono-9",
                        hex: "#595959"
                    },
                    {
                        colorName: "Mono-10",
                        hex: "#404040"
                    },
                    {
                        colorName: "Mono-11",
                        hex: "#363636"
                    },
                    {
                        colorName: "Mono-12",
                        hex: "#2E2E2E"
                    },
                    {
                        colorName: "Mono-13",
                        hex: "#262626"
                    },
                    {
                        colorName: "Mono-14",
                        hex: "#1F1F1F"
                    },
                    {
                        colorName: "Black",
                        hex: "#000000"
                    },
                ]
            }
        ]
    },
    {
        groupName: "渐变色",
        groupDesc: "",
        child: [
            {
                itemName: "同色系渐变",
                itemDesc: "同色系颜色变化较为平和，但可以增加界面颜色的丰富度，提升视觉效果的立体感",
                colors: [
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #599FF6, #2B76DB)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #5ECEBD, #2EA593)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #7AC453, #4C9A22)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #F4AD61, #CD8231)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #ED737B, #C5444D)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #F785CD, #D058A4)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #A780EB, #7B52C4)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #33A0A6, #00747B)"
                    }
                ]
            },
            {
                itemName: "明暗深浅渐变",
                itemDesc: "在同色系渐变中，也可以加入中性色对应的明暗变化，带来色环中主色对应的明暗深浅渐变效果",
                colors: [
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #FFFFFF, #EFF8FF)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #FFFFFF, #D7F3EF)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #FFFFFF, #DEF0D4)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #FFFFFF, #FCEBD8)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #FFFFFF, #FADCDE)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #FFFFFF, #FDE1F3)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #FFFFFF, #E9DFFA)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #FFFFFF, #CCE7E9)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #112FF5, #071424)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #13443C, #081D1A)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #1F3F0E, #0D1B06)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #543514, #241709)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #3A1417, #230C0D)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #562443, #250F1D)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #332250, #160E22)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #002F32, #001415)"
                    },
                ]
            },
            {
                itemName: "相邻色渐变",
                itemDesc: "与同色系界面相同，这种渐变没有太过强烈的颜色对比，但相较于前者，其有着更加丰富的视觉变化，能够带来更加活泼多变的视觉效果",
                colors: [
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #5ECEBD, #3088F4)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #7AC453, #289182)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #F1993A, #E8505A)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #F567C1, #9160E6)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #599FF6, #9160E6)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #F567C1, #E8505A)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #ED737B, #7B52C4)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #36C2AD, #008890)"
                    }
                ]
            },
            {
                itemName: "对色、补色渐变",
                itemDesc: "对色、补色渐变方案，建议应用于需要强调的信息图配色、特殊功能控件中",
                colors: [
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #E8505A, #3088F4)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #F1993A, #36C2AD)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #E8505A, #59B528)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #F1993A, #9160E6)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #F1993A, #3088F4)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #F567C1, #36C2AD)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #F1993A, #59B528)"
                    },
                    {
                        colorName: "",
                        hex: "linear-gradient(135deg, #36C2AD, #9160E6)"
                    }
                ]
            },
        ]
    }
]

var cssGroup = {
    "formBorderColorGroup": [
        ".i-theme-ant .k-autocomplete,.i-theme-ant .k-multiselect,.i-theme-ant .k-textarea,.i-theme-ant .k-textbox,.i-theme-ant input[type=password],.i-theme-ant span.k-dropdown-wrap",
        ".i-theme-ant .k-datepicker .k-state-default,.i-theme-ant .k-datetimepicker .k-state-default,.i-theme-ant .k-timepicker .k-state-default"
    ],
    "formBorderColorActiveGroup": [
        ".i-theme-ant .k-autocomplete:active,.i-theme-ant .k-autocomplete:focus,.i-theme-ant .k-autocomplete:hover,.i-theme-ant .k-multiselect:active,.i-theme-ant .k-multiselect:focus,.i-theme-ant .k-multiselect:hover,.i-theme-ant .k-textarea:active,.i-theme-ant .k-textarea:focus,.i-theme-ant .k-textarea:hover,.i-theme-ant .k-textbox:active,.i-theme-ant .k-textbox:focus,.i-theme-ant .k-textbox:hover,.i-theme-ant input[type=password]:active,.i-theme-ant input[type=password]:focus,.i-theme-ant input[type=password]:hover,.i-theme-ant span.k-dropdown-wrap:active,.i-theme-ant span.k-dropdown-wrap:focus,.i-theme-ant span.k-dropdown-wrap:hover",
        ".i-theme-ant .k-datepicker .k-state-focused,.i-theme-ant .k-datepicker .k-state-hover,.i-theme-ant .k-datetimepicker .k-state-focused,.i-theme-ant .k-datetimepicker .k-state-hover,.i-theme-ant .k-timepicker .k-state-focused,.i-theme-ant .k-timepicker .k-state-hover",
        ".i-theme-ant .k-dropdown-wrap.k-state-focused,.i-theme-ant .k-dropdown-wrap.k-state-hover"
    ],
    "datepickerPanelActive": [
        ".i-theme-ant .k-animation-container .k-calendar-container .k-calendar .k-content .k-state-focused .k-link",
    ],
    "datepickerPanelFont": [
        ".i-theme-ant .k-animation-container .k-calendar-container .k-calendar .k-header .k-link",
        ".i-theme-ant .k-animation-container .k-calendar-container .k-calendar .k-content"
    ],
    "buttonBgc": [
        ".i-theme-ant .k-grid .k-header.k-grid-toolbar .grid_toolbar .k-overflow-anchor",
        ".i-theme-ant .i-btn,.i-theme-ant .i-btn-drop,.i-theme-ant .i-btn-lg,.i-theme-ant .i-btn-lg-no-ripple,.i-theme-ant .i-btn-ripple-bounce,.i-theme-ant .i-btn-sm,.i-theme-ant .k-button,.i-theme-ant .k-overflow-anchor"
    ],
    "treeNodeActive": [
        ".i-theme-ant .k-treeview .k-state-focused,.i-theme-ant .k-treeview .k-state-selected",
        ".i-theme-ant .k-treeview .k-state-hover,.i-theme-ant .k-treeview .k-state-hover:hover"
    ],
    "pagerButtonGroup": [
        ".i-theme-ant .k-grid .k-pager-wrap.k-grid-pager .k-link:not(.k-state-disabled):hover",
        ".i-theme-ant .k-grid .k-pager-wrap.k-grid-pager a.i-grid-count:not(.k-state-disabled):hover"
    ],
    "datepickerPanelBtnText":[
        ".i-theme-ant .i-btn-text",
        ".i-theme-ant .i-btn-text:hover"
    ],
    "headerColor":[
        ".i-theme-ant .k-grid th.k-header>.k-link",
        ".i-theme-ant .k-grid th.k-header"
    ],
    "gridSelectedRowBgc":[
        ".i-theme-ant .k-grid .k-grid-content tr.i-state-selected,.i-theme-ant .k-grid .k-grid-content-locked tr.i-state-selected,.i-theme-ant .k-grid .k-selectable tr.i-state-selected",
        ".i-theme-ant .k-grid .k-grid-content tr.k-state-selected,.i-theme-ant .k-grid .k-grid-content-locked tr.k-state-selected,.i-theme-ant .k-grid .k-selectable tr.k-state-selected"
    ],
    "gridSelectedRowColor":[
        ".i-theme-ant .k-grid .k-grid-content tr.i-state-selected,.i-theme-ant .k-grid .k-grid-content-locked tr.i-state-selected,.i-theme-ant .k-grid .k-selectable tr.i-state-selected",
        ".i-theme-ant .k-grid .k-grid-content tr.k-state-selected,.i-theme-ant .k-grid .k-grid-content-locked tr.k-state-selected,.i-theme-ant .k-grid .k-selectable tr.k-state-selected"
    ]
}