;(function ($, _) {
    var trim = $.trim,
        extend = $.extend;

    var isObject = IPLAT.isObject || function (obj) {
        return null !== obj && typeof obj === 'object';
    };

    // 为首页定制的新增Tab接口
    kendo.ui.TabStrip.fn.addTab = function (item) {
        if (!isObject(item)) return;

        // newWindow模式: 重新打开window窗口展示新页面
        // tabView模式: 在首页的tab项中添加新页面
        // 默认是newWindow模式
        var defaultMode = 'newWindow';

        var mode = $('#toggle-view-mode').data('mode') || defaultMode;

        addBusinessShorCutRecords(item, mode);

        if (mode === 'newWindow') {
            if (item.urlFlag) {
                window.open(item.url)
            } else {
                if(item.menuOpen){ // 从菜单打开页面
                    IPLAT.openForm(item.tabEname);
                } else {
                    IPLAT.openForm(item.title);
                }
            }
            return;
        }

        if (mode === 'tabView') {
            this.append({
                text: item.title,
                content: "&#12"
            });

            var last = this.tabGroup.children().length - 1;
            var lastItem = this.tabGroup.children().eq(last);
            lastItem.append('<span data-type="remove" class="k-link"><span class="k-icon k-i-close"></span></span>');
            var lastContentElement = this.contentElement(last);
            var iframe = $('<iframe>');
            iframe.appendTo($(lastContentElement));
            $(lastContentElement).css('height', $(window).height() - $('#page-list').offset().top - 31); // 当前tab页k-content的高度
            iframe.attr('width', '100%');
            iframe.attr('frameborder', '0');
            iframe.attr('height', $(window).height() - 120); // iframe的高度
            iframe.attr('src', item.url);
            this.activateTab(lastItem);

        }
    };

    var _defaultOptions = {
        slick: true, // 首页轮播
        menu: true,  // 左侧菜单
        pageSearch: true, // 页面号查询
        favorite: true, // 收藏页面
        panelBar: true, // 消息主看板
        unload: true, // 首页刷新或者关闭时，关闭所有的子窗口
        apm: false,  // APM指标默认不展示
        tabs: true,  // 页面切换
        calendar: true //日历区域
    };

    var uiLayout = function (isTabs) {
        var offsetTop = !!isTabs ? $('#page-list').offset().top + 10 * 2 + 31 : 80;
        var $hWindow = $(window).height();
        var offsetHeight = $hWindow - offsetTop;

        // $(".main-content").css('height', $hWindow - 80);
        $("#iplat-menu").css('height', $hWindow - 90);
        $("#iplat-menu").getNiceScroll().resize();
        var topH = offsetHeight * 0.55,
            bottomH = offsetHeight * 0.45;

        $(".i-index-top").css('height', topH);

        $(".i-index-tab").css('height', topH - 80); // 最外层tab的高度固定，防止切换tab时的抖动

        $(".i-index-bottom").css('height', bottomH - 10);
        $(".index-favorite").css('height', bottomH - 56); // 10 + 10 + 36

        $(".i-index-panelbar").css('height', topH - 120); // 只允许panelbar 出现滚动条

        //$(".i-region-content").css('height', $(".index-favorite").height() - 36);
        /*$(".index-favorite .i-region-content").css('height', $(".index-charts").height() - 46);

        $(".index-links ").css('height', $(".index-charts").height() - 46);*/
        $("#info").css('height', $(".index-charts").height());

        $(".index-charts").find(".i-region-content").css('height', $(".index-charts").height() - 36);

        $(".index-charts").find(".i-region-content.notice-region").css('height', $(".index-charts").height());

        // $(".index-apm").find(".i-region-content").css('height', $(".index-apm").height() - 36);

        $(".index-links .dropdown-menu").css("left", 47 - $(".index-links").width());

        /*
        if (echarts.getInstanceByDom($("#qps")[0])) {
            echarts.getInstanceByDom($("#qps")[0]).resize();
            echarts.getInstanceByDom($("#servicepv")[0]).resize();
            echarts.getInstanceByDom($("#pagepv")[0]).resize();
        }
        */

    };

    var V6Index = function (options) {
        if (this instanceof V6Index) {
            this._options = extend({}, _defaultOptions, options);
            this.init();
        } else {
            return new V6Index(options);
        }
    };

    // 调用各个模块
    V6Index.prototype.init = function () {
        var _options = this._options,
            that = this,
            key;
        var keys = _.keys(_options);

        for (var i = 0, length = keys.length; i < length; i++) {
            key = keys[i];
            if (key && _options[key] && _.isFunction(that[key])) {
                // 防止页面JS报错，导致页面无法继续执行
                try {
                    that[key].call(that);
                } catch (e) {
                    console.error(e);
                }
            }
        }
        // 样式重置
        uiLayout(true);
        // APM echarts
        // drawCharts();

        // reisze 重新布局
        $(window).resize(kendo.throttle(function () {
            uiLayout(true);
        }, 200));

        $(".fa-file-o").on("click", function () {
            $("#new-window-view").css('display', 'block');
            $("#tab-view").css('display', 'none');
            $('#toggle-view-mode').data('mode', 'newWindow');
        });
        $(".fa-files-o").on("click", function () {
            $("#tab-view").css('display', 'block');
            $("#new-window-view").css('display', 'none');
            $('#toggle-view-mode').data('mode', 'tabView');
        });

        if (IPLAT.Browser.isIE8) {
            $("#new-window-view").css("display", "none");
            $("#tab-view").css("display", "none");
        }
    };


    V6Index.prototype.meet = function () {
        var meetInfo = new EiInfo();
        // EiCommunicator.send("KB00M", "meetingToThisWeek", meetInfo, {
        //     onSuccess: function (eiInfo) {
        //         var status = eiInfo.getStatus();
        //         var fileUrl = eiInfo.get("fileUrl");
        //         var title = eiInfo.get("title");
        //         if (status != -1) {
        //             $("#meetingAnnouncement").html('<a href="' + fileUrl + '">' + title + '公司会议安排</a>')
        //         } else {
        //             $("#meetingAnnouncement").html(' ');
        //             console.log("调用会议接口" + eiInfo.getMsg());
        //         }
        //     },
        //     onFail: function (eMsg) {
        //         $("#meetingAnnouncement").html(' ');
        //         console.log("调用会议接口：" + eMsg);
        //     }
        // });
    };
    // APM指标
    /*
    V6Index.prototype.apm = function () {
        // 绘制图表
        var apmData = "";
        var draw = function (elementList) {
            for (var k = 0; k < elementList.length; k++) {
                var elementId = elementList[k]['elementId'],
                    text = elementList[k]['text'],
                    themeColor = elementList[k]['themeColor'],
                    _index = elementList[k]['_index'],
                    type = elementList[k]['type'];
                var thisChart = echarts.init(document.getElementById(elementId));
                var _option = {
                    title: {
                        show: true,
                        text: text,
                        top: '2%',
                        left: '0',
                        textStyle: {
                            color: '#25A4D1',
                            fontStyle: 'normal',
                            fontWeight: 'normal',
                            fontSize: 13
                        }
                    },
                    grid: {
                        top: '30%',
                        height: '65%',
                        width: '95%',
                        left: '3%',
                        right: '10%'
                    },
                    textStyle: {
                        color: '#f7f7f7',
                        fontStyle: 'normal',
                        fontSize: 13
                    },
                    tooltip: {
                        show: true,
                        trigger: 'axis',
                        // formatter: "{c}?11:{c}</br>" + "<span style='font-size: 5px;'>{b}</span>",
                        formatter: function (params) {
                            if (!_.isUndefined(params[0].value)) {
                                var res = '<span>时间：' + params[0].name + '</span>';
                                for (var i = 0; i < params.length; i++) {
                                    res += '<p>' + params[i].seriesName + ': ' + params[i].data + '</p>'
                                }
                                return res;
                            } else {
                                return "无数据";
                            }
                        },
                        backgroundColor: "rgba(255, 255, 255, 0.8)",
                        confine: true, // 将tooltip限制在图表区域内
                        textStyle: {
                            color: themeColor,
                            fontWeight: 'bold',
                            fontSize: 10
                        },
                        axisPointer: {
                            type: 'line',
                            lineStyle: {
                                color: themeColor,
                                width: 1,
                                type: 'solid'
                            }
                        }
                    },
                    xAxis: {
                        data: [],
                        type: 'category',
                        // interval: 9,
                        splitNumber: 6,
                        axisLine: {
                            show: true        //显示坐标轴轴线
                        },
                        axisLabel: {
                            show: false,
                            textStyle: {
                                fontSize: 9
                            },
                            interval: 9
                        },
                        axisTick: {
                            show: false,
                            alignWithLabel: true,
                            interval: 9
                        },
                        splitLine: {          //grid 区域中的分隔线。
                            show: false,
                            interval: 9,
                            lineStyle: {
                                type: 'solid',
                                color: '#65686A'
                            }
                        },
                        boundaryGap: type == "bar"   //柱状图留白
                    },
                    yAxis: {
                        gridIndex: 0,
                        type: 'value',
                        nameGap: 20,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                fontSize: 9,
                                fontWeight: 'bold'
                            }
                        },
                        axisTick: {          //显示坐标轴刻度
                            show: false
                        },
                        axisLine: {
                            show: true       //显示坐标轴轴线
                        },
                        splitLine: {         //grid 区域中的分隔线。
                            show: true,
                            interval: 3,
                            lineStyle: {
                                type: 'dotted',
                                color: '#65686A'
                            }
                        }
                    },
                    dataZoom: [{
                        type: 'inside',     //鼠标缩放
                        start: 0,           //开始缩放百分比(default:0)
                        end: 100            //最大缩放百分比(default:100)
                    }]
                };
                thisChart.setOption(_option);

                drawThisChart(thisChart, k);

                // 绘制单个chart
                function drawThisChart(thisChart, k) {
                    apmData = k === 0 ? queryAPMData() : apmData;

                    var esData = apmData.get("result");
                    var date = [];
                    var value = [];
                    var _node, _now;

                    if (!_.isEmpty(esData) && esData['hits']['hits'].length > 0) {
                        var nodes = esData['hits']['hits'];
                        for (var i = 0; i < nodes.length; i++) {
                            _node = nodes[i];
                            var nodeSource = _node['_source'];
                            _now = new Date(nodeSource['@timestamp']);
                            var timeStr = [_now.getHours(), Math.floor(_now.getMinutes() / 10) == 0 ? '0' + _now.getMinutes() : _now.getMinutes()].join(":");
                            date.push(timeStr);
                            value.push(nodeSource[elementList[k]['_index']]);
                        }

                        // // 在线用户数
                        // var onlineUserNumStr = nodes[nodes.length - 1]['_source']['onlineuser'].toString();
                        // $(".index-charts-layout-0-span2").html("");
                        // for (var j = 0; j < onlineUserNumStr.length; j++) {
                        //     $("<span class='index-charts-layout-0-span3'>" + onlineUserNumStr[j] + "</span>").appendTo(".index-charts-layout-0-span2");
                        // }
                    }

                    // 在线用户数
                    var onlineUserStr = apmData.get("onlineUser");
                    if (!_.isEmpty(onlineUserStr)) {
                        $(".index-charts-layout-0-span2").html("");
                        for (var j = 0; j < onlineUserStr.length; j++) {
                            $("<span class='index-charts-layout-0-span3'>" + onlineUserStr[j] + "</span>").appendTo(".index-charts-layout-0-span2");
                        }
                    }

                    thisChart.setOption({
                        xAxis: {data: date},
                        series: [
                            {
                                // type: 'line',
                                type: elementList[k]['type'],
                                symbol: 'circle',
                                symbolSize: 8,
                                itemStyle: {
                                    normal: {
                                        color: elementList[k]['themeColor']
                                    },
                                    emphasis: {
                                        color: elementList[k]['themeColor']
                                    }
                                },
                                connectNulls: true,
                                name: elementList[k]['text'],
                                data: value
                            }
                        ]
                    });

                    setTimeout(function () {
                        drawThisChart(thisChart, k);
                    }, APM_REFRESH * 60 * 1000);
                }

                // 获取APM数据
                function queryAPMData() {
                    var result = "";
                    var nowTimestamp = new Date().getTime();
                    var ei = new EiInfo();
                    ei.set("request_body", {
                        "size": IPLATUI.ES_SPAN, //数据条数
                        "sort": [
                            {
                                "@timestamp": {
                                    "order": "asc"
                                }
                            }
                        ],
                        "query": {
                            "bool": {
                                "must": [
                                    {
                                        "range": {
                                            "@timestamp": {
                                                "gte": nowTimestamp - IPLATUI.ES_SPAN * 60 * 1000,//开始时间（Unix时间戳 ，毫秒）
                                                "lte": nowTimestamp  //结束时间（毫秒）
                                            }
                                        }
                                    }
                                ]
                            }
                        }
                    });
                    EiCommunicator.send("EF0001", "queryAPM", ei, {
                        onSuccess: function (ei) {
                            result = ei;
                        },
                        onFail: function (ei) {

                        }
                    }, {async: false});
                    return result;
                }
            }
        };
        var drawCharts = function () {
            var elementList = [];
            elementList.push({
                'elementId': 'pagepv',
                'text': '页面访问量',
                'themeColor': '#FF9100',
                "_index": 'pagepv',
                "type": "bar"
            });
            elementList.push({
                'elementId': 'servicepv',
                'text': '服务访问量',
                'themeColor': '#0067E9',
                "_index": 'servicepv',
                "type": "bar"
            });
            elementList.push({
                'elementId': 'qps',
                'text': '每秒查询率',
                'themeColor': '#F25753',
                "_index": 'QPS',
                "type": "line"
            });
            draw(elementList);
        };

        // 显示APM区内容
        $(".index-apm .i-region-content").css("display", "block");
        drawCharts();

        // APM部分查看更多
        $("#moreAPM").on("click", function () {
            window.open("http://pscstest.baogang.info/iPlatAPM/web/XPSJ03");
        });
    };
    */
    // 轮播图
    V6Index.prototype.slick = function () {
        $('.slick').slick({
            slidesToShow: 4,
            slidesToScroll: 4,
            dots: true,
            autoplay: true,
            autoplaySpeed: 5000,
            appendDots: $("#i-region-dots-append"),
            dotsClass: 'slick-dots i-slick-dots'
        });
    };


    /**
     * 菜单在底部展开时，自动定位
     * @param node
     * @return {number}
     */
    function getOffsetTop(node) {
        var t = 0;
        var p = $(node);
        while (!p.parent().hasClass('iplat-menu-content')) {
            if (p.is('li')) {
                console.debug(p);
                t += p.position().top;
            }
            p = p.parent();
        }
        return t;
    }

    // 菜单
    V6Index.prototype.menu = function () {
        var v6Index = this;
        // 首页菜单
        var iplatMenu = function (root, menuId) {
            var ei = new EiInfo();
            var $menus = $("#" + menuId);
            $menus.css("visibility", "hidden");

            var openMenu = function ($li) {
                var content = $li.find(".iplat-menu-content");
                var id = content.attr("id");
                var $menu = $("#" + id);
                var first = content.data("first");

                $li.addClass("open");

                // 内容首次加载时候，加载生成树
                // 后面再点击不再重新生成树
                if (first) {
                    IPLAT.TreeView({
                        treeId: id,
                        ROOT: id.replace("tree", ""),
                        url: IPLATUI.CONTEXT_PATH,
                        serviceName: "EF0000",
                        methodName: "query",
                        textField: "text",
                        valueField: "label",
                        hasChildren: "leaf",
                        dataSpriteCssClassField: "imagePath",
                        messages: {
                            loading: "加载中......"
                        },
                        // 菜单展开时候，分为两种情况下，一种是需要ajax调用后端数据，然后展开。 另外一种是子结点折叠起来，然后展开
                        // dataBound是处理ajax调用后端数据，展开子结点展开的回调
                        dataBound: function () {
                            $("#iplat-menu").getNiceScroll().resize();
                        },
                        // expand是处理是子结点折叠起来，然后展开
                        // 可以通过aria-expanded存在这个属性表明是子结点已经获取，不需要再从后端获取结点信息
                        expand: function (e) {
                            var node = e.node;
                            // var expanded = $(node).attr('aria-expanded');

                            // setTimeout: 400是异步执行，防止子结点没有展开完
                            setTimeout(function () {
                                $("#iplat-menu").getNiceScroll().resize();

                                // 自动定位菜单滚动条的位置
                                //var t = getOffsetTop(node);
                                //$("#iplat-menu").getNiceScroll(0).doScrollTop(t, 200);
                            }, 400);

                        },
                        select: function (e) {
                            var tree = e.sender;
                            var model = tree.dataItem(e.node) || {};
                            var formCname = model['text'];
                            var formEname = model['label'];
                            if (!model['leaf']) {
                                var url = trim(model.nodeUrl),
                                    param = trim(model.nodeParam);
                                if (v6Index._options.tabs && v6Index.tabs) {
                                    if (url) {
                                        v6Index.tabs.addTab({
                                            title: formCname,
                                            url: url,
                                            urlFlag:true,
                                            tabEname: formEname,
                                            menuOpen: true
                                        });
                                    } else {
                                        v6Index.tabs.addTab({
                                            title: formCname,
                                            url: IPLAT.createUrl(formEname, param),
                                            urlFlag:false,
                                            tabEname: formEname,
                                            menuOpen: true
                                        });
                                    }
                                } else {
                                    if (url) {
                                        window.open(url)
                                    } else {
                                        IPLAT.openForm(formEname, param);
                                    }
                                }
                            }
                        },
                        loadComplete: function () {
                            $menu.on("click", "li .k-state-selected", function () {
                                var treeview = $("#" + id).data("kendoTreeView"),
                                    node = $(this).closest("li")[0];
                                treeview.trigger("select", {node: node});
                            });
                        }
                    });
                    content.data("first", false);

                    var kendoTreeView = $menu.data("kendoTreeView");

                    $menu.on("click", ".k-in", function (e) {
                        kendoTreeView.toggle($(e.target).closest(".k-item"));
                    });
                }

                $menu.show();
            };

            var init = function (menusHtml) {
                $menus.html(menusHtml);
                $menus.on("click", ".iplat-menu", function (e) {

                    // 获取li的结点
                    var $li = $(this),
                        leaf = $li.data("leaf"),
                        url = trim($li.data('url')),
                        param = trim($li.data('param')),
                        pageSerial = $li.data("page");

                    if (leaf == 1) {
                        if (v6Index._options.tabs && v6Index.tabs) {
                            if (url) {
                                v6Index.tabs.addTab({
                                    title: pageSerial,
                                    url: url
                                });
                            } else {
                                v6Index.tabs.addTab({
                                    title: pageSerial,
                                    url: IPLAT.createUrl(pageSerial, param)
                                });
                            }
                        } else {
                            if (url) {
                                window.open(url);
                            } else {
                                IPLAT.openForm(pageSerial, param);
                            }
                        }
                    } else {
                        var $iplatMenu = $("#iplat-menu");
                        if ($(e.target).closest(".iplat-menu-content").length < 1) {
                            var opened = $li.hasClass("open");
                            // 关闭已经打开menu
                            $menus.find(".iplat-menu-content").hide();

                            $iplatMenu.find("li.iplat-menu").removeClass("open");

                            if (!opened) { // 菜单之前是打开的，不再打开
                                openMenu($li);
                            }
                        }

                        $iplatMenu.getNiceScroll().resize();
                    }

                });
            };

            ei.set("inqu_status-0-node", root);
            // 构建menu
            EiCommunicator.send("EF0000", "query", ei, {
                onSuccess: function (ei) {
                    var nodeList = ei.getBlock(root).getMappedRows() || [];
                    var menusHtml = kendo.template($("#menu-template").html())({
                        menus: nodeList
                    });
                    init(menusHtml);
                    $menus.css("visibility", "visible");

                    $("#iplat-menu").niceScroll({
                        // cursorwidth: "7px",
                        // cursorborder: "none",
                        // height: $menus.height()
                    });
                },
                onFail: function (ei) {
                    // alert("菜单服务调用失败");
                }
            });
        };

        var iplatMenuName = trim($("input[name='iplatMenuName']").val());

        if (trim(iplatMenuName)) {
            iplatMenu(iplatMenuName, "iplat-menu")
        }

        // 菜单的收缩展开
        $("#side-toggle").on("click", function () {
            App.layout("sidebar_mini_toggle");
        });
    };

    // 收藏功能
    V6Index.prototype.favorite = function () {
        // 收藏页面
        var favoriteTmpl = kendo.template($("#favorite-template").html());
        EiCommunicator.send("EDFA10", "query", new EiInfo(), {
            onSuccess: function (e) {
                var favData = [],
                    item = e.getBlock("result").getMappedRows(),
                    favList = $("#fav-list");
                if (item.length > 0) {
                    for (var i = 0; i < item.length; i++) {
                        favData.push({
                            encoded: false,
                            text: favoriteTmpl({
                                form_ename: item[i]['form_ename'],
                                form_cname: item[i]['form_cname']
                            })
                        })
                    }
                } else {
                    favData.push({text: "暂无收藏页面！"});
                    favList.next(".for-more").css("display", "none");
                }
                favList.kendoPanelBar({
                    dataSource: favData,
                    select: onSelect
                });
            }, onFail: function (e) {
            }
        });

        var onSelect = function (e) {
            var ename = $(e.item).find("div").attr("ename");
            IPLAT.openForm(ename);
        };

        $(".moreCollection").on("click", function () {
            IPLAT.openForm("EDFA10");
        });

        /*        $("#meetingAnnouncement").on("click", function () {
                    var meetInfo = new EiInfo();
                    EiCommunicator.send("KB00M", "meetingToThisWeek", meetInfo, {
                        onSuccess : function(eiInfo) {
                            var status = eiInfo.getStatus();
                            var fileUrl = eiInfo.get("fileUrl");
                            if (status != -1) {
                                $("#meetingAnnouncement").html('<a href="http://sp.baogang.info:9082/NMfiledownloadservlet?fileguid=ebc227e9-fe91-48b4-b8c5-475a15b22e31">本周会议</a>')
                            }
                        },
                        onFail : function(eMsg) {
                            IPLAT.NotificationUtil("调用会议接口服务失败.");
                        }
                    });
                });*/
    };

    // 页面号查询功能
    V6Index.prototype.pageSearch = function () {
        var v6Index = this;
        // 防止抖动
        var filterChinese = _.debounce(function (td) {
            td.value = td.value.replace(/[\u4e00-\u9fa5]/g, '');
        }, 60);

        var $formEname = $("#inqu_status-0-form_ename");

        // 过滤中文
        $formEname.on("input", function () {
            filterChinese(this)
        });
        var defaultPageSize = 100;

        // 页面号的查询
        var dataSource = new kendo.data.DataSource({
            transport: {
                read: {
                    url: IPLATUI.CONTEXT_PATH + "/service/EF0001/query",
                    type: 'POST',
                    dataType: "json",
                    contentType: "application/json;charset=utf-8"
                },
                parameterMap: function () {
                    var info = new EiInfo();
                    info.set("inqu_status-0-form_ename", $("#inqu_status-0-form_ename").val());
                    info.set("result-limit", defaultPageSize);
                    info.set("result-offset", 0);
                    return info.toJSONString(true);
                }
            },
            schema: {
                model: {
                    id: "form_ename"
                },
                data: function (response) {
                    // 处理异常
                    var ajaxEi = EiInfo.parseJSONObject(response);
                    if (ajaxEi.getStatus() < 0) {
                        NotificationUtil(ajaxEi);
                        return [];
                    }
                    return ajaxEi.getBlock("result").getMappedRows();
                }
            },
            error: function (e) {
                NotificationUtil('网络发生异常, 请稍后再试', 'error');

            },
            pageSize: defaultPageSize,
            serverFiltering: true
        });

        // 设置下拉列的宽度
        var width = $formEname.width() * 1.4;
        var template = "<div class='text-overflow' style='width:" + width + "px'>" + '#: form_ename #-#: form_cname#' + "</div>";
        // 按下Enter键后触发change事件
        var enterFunc = function (e) {
            if (kendo.keys.ENTER === e.keyCode) {
                $formEname.unbind("keyup.iplat", enterFunc); // 解绑keyup事件，防止单页展示时出现两个相同tab
                var autoComplete = $("#inqu_status-0-form_ename").data("kendoAutoComplete");
                autoComplete.trigger("change", {sender: autoComplete, open: true});
            }
        };

        $formEname.kendoAutoComplete({
            autoWidth: true,
            dataSource: dataSource,
            dataTextField: "form_ename",
            minLength: 2,
            enforceMinLength: true,
            height: 200,
            template: template,
            suggest: false,
            select: function (e) {
                var param = "",
                    form_ename = e.dataItem.form_ename;
                if (v6Index._options.tabs && v6Index.tabs) {
                    v6Index.tabs.addTab({
                        title: form_ename,
                        url: IPLAT.createUrl(form_ename.toUpperCase(), param)
                    });
                } else {
                    IPLAT.openNewForm(form_ename.toUpperCase(), param);
                }
            },
            change: function (e) {
                // 支持重新打开页面
                $formEname.unbind("keydown.iplat");
                $formEname.on("keydown.iplat", enterFunc);

                // 支持Enter时候触发，其他时候触发change不打开页面
                if (e.open) {
                    var dataSource = e.sender.dataSource,
                        form_ename = trim(e.sender.element.val()),
                        param = "",
                        item = dataSource.get(form_ename);
                    if (!!item) {
                        param = trim(item['form_param']);
                    }

                    if (v6Index._options.tabs && v6Index.tabs) {
                        v6Index.tabs.addTab({
                            title: form_ename,
                            url: IPLAT.createUrl(form_ename.toUpperCase(), param)
                        });
                    } else {
                        IPLAT.openNewForm(form_ename.toUpperCase(), param);
                    }
                }
            }
        });
        // 页面第一次加载时，用keyup事件弹出新窗口
        $formEname.on("keyup.iplat", enterFunc);
    };

    var addBusinessShorCutRecords = function(item, mode) { // 添加点击tab的记录

        var nodeEname;

        if(item.menuOpen){
            nodeEname = item.tabEname;
        } else {
            nodeEname = item.title;
        }

        var info  = new EiInfo();
        info.set("nodeEname", nodeEname);

        if(item.urlFlag){ // 新标签打开的页面，需要记录一下当前页面的url
            info.set("url", item.url);
            info.set("title", item.title);
        }

        EiCommunicator.send("IRSY01", "addLists", info, {
            onSuccess: function (ei) {
                $("#businessShortCut div ul").find("li").remove();
                initBusinessShortCut();
            },
            onFail: function (errorMsg, status, e) {
                NotificationUtil(errorMsg, "error");
            }
        });
    }

    var initBusinessShortCut = function() { // 初始化业务快捷列表

        var icons = [ // 暂时写死，没有图标的来源
            'iplatui/img/index/工时填报.png',
            'iplatui/img/index/报销录入.png',
            'iplatui/img/index/我的报销.png',
            'iplatui/img/index/报销额度.png',
            'iplatui/img/index/项目查询.png',
            'iplatui/img/index/项目变更.png',
            'iplatui/img/index/项目状态报告.png',
            'iplatui/img/index/完工办理.png',
            'iplatui/img/index/销售合同评审.png',
            'iplatui/img/index/合同执行跟踪.png',
            'iplatui/img/index/采购申请.png',
            'iplatui/img/index/外协审签.png',
            'iplatui/img/index/预算送审.png',
            'iplatui/img/index/预算履历.png',
            'iplatui/img/index/划转审批.png',
            'iplatui/img/index/划转录入.png'
        ];

        var info = new EiInfo();

        EiCommunicator.send("IRSY01", "queryLists", info, {
            onSuccess: function (ei) {
                if (ei.getStatus() != 1) {
                    IPLAT.alert('初始化业务快捷列表失败: ' + ei.getMsg());
                    return false;
                }
                var bsLists = ei.get("result");
                for(var i =0; i< bsLists.length; i++){
                    var lastUlElement = $("#businessShortCut div ul").eq(parseInt(i/4));
                    var eName = bsLists[i].nodeEname;
                    var url = bsLists[i].url;
                    var clsName = 'onclickOpenFromBS' + eName;
                    if(url){
                        lastUlElement.append('<li><img class=' + clsName + ' src=' + icons[i] +' alt='+ bsLists[i].nodeCname +' name ='+ eName +' url='+ url +'>' +
                            '<p class=' + clsName + ' name= '+ eName +' url= '+ url +'>' + bsLists[i].nodeCname +'</p></li>');
                    }else {
                        lastUlElement.append('<li><img class=' + clsName + ' src=' + icons[i] +' alt='+ bsLists[i].nodeCname +' name ='+ eName +'>' +
                            '<p class=' + clsName + ' name= '+ eName +'>' + bsLists[i].nodeCname +'</p></li>');
                    }

                    $("." + clsName).on("click", function () {
                        if($(this).attr("url")){
                            window.open($(this).attr("url"));
                        } else {
                            IPLAT.openForm(this['name']);
                        }
                    });
                }

            },
            onFail: function (errorMsg, status, e) {
                NotificationUtil(errorMsg, "error");
            }
        });

    }

    // index刷新 关闭时，关闭所有的子窗口
    V6Index.prototype.unload = function () {
        window.onunload = function () {
            __iplat__closeWindows(true);
        };
    };

    // 首页消息主看板模块
    V6Index.prototype.panelBar = function () {
        /*
        var rows = [
            [
                "质量模块",
                "PQ",
                "disputeAppr",
                "质量异议审批",
                "Manual4",
                "备件二级审批人",
                "a3ce7050-728d-11e7-a9a1-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-27 13:50:37",
                "open",
                "PQYY02A",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "PM_LEV1",
                "一级审核",
                "107c4d9f-710b-11e7-ba7f-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-25 15:30:24",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "PM_LEV2",
                "二级审核",
                "d6123e71-710e-11e7-ba7f-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-25 18:08:36",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "PM_LEV1",
                "一级审核",
                "c009262f-70eb-11e7-92a2-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-25 14:00:23",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "AUDIT1",
                "一级审核",
                "276476a4-715a-11e7-a9a1-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-26 00:57:19",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "AUDIT1",
                "一级审核",
                "f323d809-7159-11e7-a9a1-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-26 00:55:44",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "AUDIT1",
                "一级审核",
                "dd40fcf8-7152-11e7-a9a1-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-26 00:33:36",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "AUDIT1",
                "一级审核",
                "c1fe1945-715a-11e7-a9a1-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-26 01:02:49",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "AUDIT1",
                "一级审核",
                "8d5bfaba-715a-11e7-a9a1-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-26 01:00:14",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "AUDIT1",
                "一级审核",
                "6e33c01f-715a-11e7-a9a1-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-26 00:58:47",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "AUDIT1",
                "一级审核",
                "8ed7ec1e-76ab-11e7-b0b4-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-08-01 19:22:05",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "基础模块",
                "PSCS-PM",
                "PmTestAuditDC",
                "基础工作流审核测试1",
                "AUDIT3",
                "三级审核",
                "d799ef4c-7296-11e7-a9a1-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-27 18:13:13",
                "open",
                "PMJPA303",
                "01",
                "0"
            ],
            [
                "寻源模版",
                "PSCS-PH",
                "PhContract1001",
                "合同审核",
                "AUDIT2",
                "二级审核",
                "616227f8-75c1-11e7-a9a1-005056b604c3",
                "创建人同时提交工作流",
                "019328",
                "2017-07-31 15:26:17",
                "open",
                "PHCTTASK",
                "01",
                "0"
            ]
        ];

        var columns = ["categoryName", "categoryKey", "processKey", "processName", "taskDefKey", "taskDefName", "procInstId",
            "subject", "assignee", "startTime", "state", "form", "taskType"];

        var block = EiBlock.build("result", columns);
        block.setRows(rows);

        var jsonRows = block.getMappedRows();
        */

        /**
         * 刷新服务.
         *
         * @param service 调用的本地service
         * @param method 调用本地service的方法
         * @param drawfunc 渲染区域的function
         * @param info 提交的数据
         * @param acceptFail 即使服务调用失败，也调用处理函数
         */
        var refreshService = function (service, method, drawfunc, info, acceptFail) {
            if (!service || !method) {
                return;
            }

            if (!info) {
                info = new EiInfo();
            }
            EiCommunicator.send(service, method, info, {
                onSuccess: function (ei) {
                    if (!acceptFail && ei.getStatus() === -1) {
                        // IPLAT.alert('<b>' + ei.getMsg() + '</b>', function (e) {
                        // }, '警告');
                        return false;
                    }
                    if (drawfunc && typeof drawfunc === 'function') {
                        drawfunc(ei);
                    }
                },
                onFail: function (errorMsg, status, e) {
                    // console.error(e);
                    NotificationUtil(errorMsg, "error");
                }
            });

        };
        // 看板id和顺序
        var panelIndex = {
            "todo": 0, // 待办
            "information": 1, // 提醒
            "trace": 2, // 跟踪
            "record": 3, // 已办
            "notification": 4, // 通知
            "notice": 5, // 公告
        };

        // 清除看板和数量
        var clearPanel = function (id) {
            if (!id || panelIndex[id] === undefined) {
                return null;
            }
            $("#" + id).html('');
            var tabElements = $("#info-board>ul>li[role='tab']");
            var tab = tabElements.eq(panelIndex[id]);
            // 如果原来有通知数量，先清除
            var bg = tab.find('.badge.badge-danger');
            if (bg) {
                bg.remove();
            }
            return tab;
        };

        // 绑定刷新链接
        var bandForRefresh = function (id, refreshfunc) {
            if (!id || !refreshfunc || typeof refreshfunc !== 'function') {
                return false;
            }
            $("#" + id + " ~ .for-refresh").off("click");
            $("#" + id + " ~ .for-refresh").on("click", function () {
                refreshfunc();
            });
        };

        // 绑定查看更多链接
        var bandForMore = function (id, form, param) {
            if (!id || !form) {
                return false;
            }
            $("#" + id + " ~ .for-more").off("click");
            $("#" + id + " ~ .for-more").on("click", function () {
                IPLAT.openForm(form, param);
            });

        };

        // 待办
        var tempData;
        var drawTodo = function (ei) {

            //CHG: remove all first 20200217.yanzj
            if ($('#todo').length == 0) {//未构造

            } else if ($('#todo').hasClass('todo-empty-node')) {//前一次是空 tabStrip，为新构造 待办构造一个新dom
                $('#todo').data('kendoPanelBar').destroy();
                var joTd = $('#todo');
                joTd.after('<ul id="todo"></ul>');
                joTd.remove();
            } else if ($('#todo').parent().data('kendoTabStrip')) {//已经存在 tabStrip，为新构造 待办构造一个新dom
                var joTd = $('#todo');
                joTd.closest('.k-tabstrip-wrapper').after('<div><ul id="todo"></ul></div>');
                joTd.parent().data('kendoTabStrip').destroy();
                joTd.parent().remove();
            }
            var tab = clearPanel('todo');

            //刷新按钮绑定事件提前 20200110.yanzj
            $("#info-board-1").off("click", ".for-refresh");
            $("#info-board-1").on("click", ".for-refresh", function () {
                refreshTodo();
            });

            var jsonRows = ei.get('result') || [];
            var taskCount = parseInt(jsonRows.length);
            if (taskCount === 0) {
                var kendoPanelBar = $("#todo").kendoPanelBar({
                    dataSource: {
                        text: '暂无待办任务！'
                    }
                });
                $(kendoPanelBar).addClass('todo-empty-node');//空数据时，标识一下 20200217.yanzj
                return false;
            }

            var modules = _.groupBy(jsonRows, "categoryKey");

            var processTmpl = kendo.template($("#process-template").html());
            var taskTmpl = kendo.template($("#task-template").html());

            for (var module in modules) {

                // 生成子tab
                // var $module = $("<li id='" + module + "' >" + modules[module][0]["categoryName"] +
                //     "[" + modules[module].length + "]</li>");
                var $panel = $('.todo-panel-items.module-' + module);
                $panel = $panel.length > 0 ? $panel : $("<div id='" + module + "_panelBar' class='i-index-panelbar todo-panel-items module-" + module + "' />");

                $("#todo").parent().append($panel);
                // $("#todo").append($module);
                // $("#todo").append($panel);

                var groups = _.groupBy(modules[module], function (value) { // 按照流程定义和活动Id分组//20200121.CHG: 任务不按角色分组
                    return value["processKey"];
                });

                var dataSource = _.map(groups, function (group, key) {
                    var process = {encoded: false};

                    // context/web/form
                    // process["text"] = "您有" + group.length + "个" + group[0]["processName"] + group[0]["taskDefName"];
                    process["text"] = processTmpl({
                        count: group.length,
                        processDefName: group[0]["processDefName"],
                        taskName: group[0]["taskName"],
                        form: group[0]["form"],
                        categoryName: modules[module][0]["categoryName"]
                    });

                    process["items"] = _.map(group, function (value) {
                        // context/web/form?pid=pid
                        // return {text: value["subject"]};
                        return {
                            text: taskTmpl({
                                instSubject: value["instSubject"],
                                processInstanceId: value["processInstanceId"],
                                form: value["form"]
                            }),
                            encoded: false
                        };
                    });
                    return process;
                });

                $panel.kendoPanelBar({
                    dataSource: dataSource
                });

                setTimeout(function () {//刷新父节点，计算panel高度
                    $panel.parent().resize();
                }, 500);
                // tempData = dataSource;
            }

            $("#todo").parent().kendoTabStrip({
                scrollable: {
                    distance: 125
                }
            }).data("kendoTabStrip").select(0);

            $(".i-index-process").off("click");
            $(".i-index-process").on("click", function (e) {
                $(e.currentTarget).parent().click();
                return false;
            });
            // 渲染待办数量
            if (tab && taskCount > 0) {
                // 显示通知数量
                tab.append('<span class="badge badge-danger">' + taskCount + '</span>');
            }
        };

        var refreshTodo = function () {
            // refreshService('KB00WF', 'getTask', drawTodo);
        };

        //获取提醒中的数据信息 add@20200224.yanzj
        var getInformationSum = function (ei) {
            var info = {sum: 0};
            var rows = ei.getBlock('result').getMappedRows();
            for (var i = 0; i < rows.length; i++) {
                var count = (rows[i].notifyContent || '[0]').replace(new RegExp('.*\\[([\\d]{1,})\\].*'), '$1');
                console.log('count :', count);
                info.sum += parseInt(count);
            }
            return info;
        };
        //显示提醒总数 add@20200224.yanzj
        var showInformationSum = function (tab, ei) {
            if (tab && ei) {
                try {
                    var info = getInformationSum(ei);
                    if (info) {
                        tab.append('<span class="badge badge-danger">' + info.sum + '</span>');
                    }
                } catch (ex) {
                }
            }
        };

        //给eiInfo 追加工时内容
        var appendWorkHours = function (oldInfo) {
            var defer = $.Deferred();
            var info = new EiInfo();
            // EiCommunicator.send("KPPE1700", "getManHour", info, {
            /*onSuccess : function(ei) {
                try {
                    if (ei.getStatus() === -1) {// 调用失败
                        console.error('获取工时信息失败 : ' + ei.getMsg(), ei.getDetailMsg());
                        defer.resolve(oldInfo);
                        return;
                    }
                    // 添加工时数据
                    var count = parseInt(oldInfo.get('result-count') || '0');
                    var resultBlock = oldInfo.getBlock('result');
                    if (!resultBlock) {
                        resultBlock = new EiBlock('result');
                        oldInfo.addBlock(resultBlock);
                    }
                    // add rows
                    var rows = ei.getBlock('result').getMappedRows();
                    for (var i = 0; i < rows.length; i++) {
                        if (parseInt(rows[i].notFillManhour) != 0 || parseInt(rows[i].notCommTime) != 0) {//有未填报 或 未提交工时，则认为本周未处理
                            count++;
                        }
                    }
                    // 添加一行工时新数据
                    var ridx = resultBlock.getRows().length;
                    oldInfo.set('result', ridx, 'notifyTitle', 'KPPE1700');
                    oldInfo.set('result', ridx, 'notifyContent', '您有工时需要填报，共[' + rows.length + ']周');
                    oldInfo.set('result', ridx, 'eiMetadata', '{}');
                    oldInfo.set('result-count', count);// 总数修改成新的

                    // 移动到最前面
                    var rows = resultBlock.getRows();
                    var row = rows.splice(rows.length - 1)[0];
                    rows.splice(0, 0, row);
                    // 将明细，置于最终的eiInfo中
                    ei.getBlock('result').getBlockMeta().blockId = 'manhour';
                    oldInfo.addBlock(ei.getBlock('result'));
                } catch (ex) {
                    console.error('处理工时出错：', ex);
                }
                defer.resolve(oldInfo);
            },
            onFail : function(errorMsg, status, e) {
                console.error('获取工时出错：', e);
                defer.resolve(oldInfo);
            }*/
            // });
            return defer.promise();
        };

        // 显示工时明细
        var showManHoursItems = function (tab, ei) {
            try {
                var joPanel = $('#information');
                var joHours = null;
                joPanel.find('a').each(function () {// 找工时项目
                    if ($(this).attr('href').indexOf('KPPE1700') > 0) {// 工时填报页面
                        joHours = $(this).closest('li.k-item');
                    }
                });
                // console.debug(' joHours :', joHours);
                if (joHours) {// 找到，则处理
                    joHours.addClass('man-hour-li');// 标识出自己这一项
                    var manHoursTmpl = kendo.template($("#manhours-template").html());
                    var txt = manHoursTmpl({
                        rows: ei.getBlock('manhour').getMappedRows()
                    });
                    joHours.append(txt);// 添加模板
                    joHours.closest('li').on('click', function () {// 添加展开折叠事件
                        joHours.find('.man-hour-items').toggleClass('hide');
                    });
                    joHours.find('.k-header:first').append('<span class="man-hour-open">展开</span>');//添加可展开提示
                }
            } catch (ex) {
                console.error('显示工时明细 出错', ex);
            }
        };

        // 提醒
        var drawInformation = function (ei) {
            appendWorkHours(ei).then(function (ei) {//添加工时信息
                var tab = clearPanel('information');
                // 提醒数量
                var infoCount = parseInt(ei.get('result-count'));
                if (infoCount === 0) {
                    $("#information").kendoPanelBar({
                        dataSource: {
                            text: '暂无提醒信息！'
                        }
                    });
                } else {
                    var result = ei.getBlock("result").getMappedRows();
                    // 提醒数据
                    var informationData = [];
                    // 提醒模板
                    var informationTmpl = kendo.template($("#information-template").html());
                    // 构造提醒显示内容
                    $.each(result, function (i, n) {
                        informationData.push({
                            text: informationTmpl(n),
                            encoded: false
                        });
                    });
                    // 渲染提醒
                    $("#information").kendoPanelBar({
                        dataSource: informationData
                    });
                    showInformationSum(tab, ei);//显示提醒总数 add@20200224.yanzj
                    showManHoursItems(tab, ei);//显示工时明细
                }
                bandForRefresh('information', refreshInformation);
            });
        };

        var refreshInformation = function () {
            // refreshService('KB00WF', 'getBusinessTask', drawInformation, null, true);
//            refreshService('KPPE1700', 'getManHour', drawInformation, null, true);
        };

        // 跟踪
        drawTrace = function (ei) {

            var tab = clearPanel('trace');

            // 跟踪数量
            var traceCount = parseInt(ei.get('result-count'));
            if (traceCount === 0) {
                $("#trace").kendoPanelBar({
                    dataSource: {
                        text: '暂无跟踪信息！'
                    }
                });
            } else {
                /* TODO 待实现
                var result = ei.getBlock("result").getMappedRows();
                // 跟踪数据
                var traceData = [];
                // 跟踪模板
                var traceTmpl = kendo.template($("#trace-template").html());
                // 构造跟踪显示内容
                $.each(result, function(i,n) {
                    traceData.push({
                        text: infoTmpl(n),
                        encoded: false
                    });
                });
                // 渲染跟踪
                $("#trace").kendoPanelBar({
                    dataSource: traceData
                });
                // 渲染跟踪数量
                if (traceCount > 0) {
                    // 显示跟踪数量
                    tab.append('<span class="badge badge-danger">'+traceCount+'</span>');
                }
                */
            }

            bandForMore('trace', '', '');
            bandForRefresh('trace', null);
        };

        var refreshTrace = function () {
            var info = new EiInfo();
            info.set('result-count', 0);
            drawTrace(info);
            //refreshService('', '', drawTrace);
        };

        // 已办
        var drawRecord = function (ei) {
            var tab = clearPanel('record');

            // 已办数量
            var recordCount = parseInt(ei.get('result-count'));
            if (recordCount === 0) {
                $("#record").kendoPanelBar({
                    dataSource: {
                        text: '暂无已办任务！'
                    }
                });
            } else {
                var result = ei.getBlock("result").getMappedRows();
                // 已办数据
                var recordData = [];
                // 已办模板
                var recordTmpl = kendo.template($("#record-template").html());
                // 构造已办显示内容
                $.each(result, function (i, n) {
                    var ar = n.approvalResult || ' ';
                    var op = n.opinion || '无';
                    op = op.trim() || '无';
                    switch (ar) {
                        case 'grant':
                            ar = '同意';
                            break;
                        case 'deny':
                            ar = '拒绝';
                            break;
                        case 'reject':
                            ar = '驳回';
                            break;
                        default:
                            ar = '提交';
                    }
                    n.ar = ar;
                    n.op = op;
                    n.endTime = kendo.toString(kendo.parseDate(n.endTime, 'yyyyMMddHHmmss'), 'yyyy-MM-dd HH:mm:ss');
                    recordData.push({
                        text: recordTmpl(n),
                        encoded: false
                    });
                });
                // 渲染已办
                $("#record").kendoPanelBar({
                    dataSource: recordData
                });
                // 渲染已办数量
                //if (recordCount > 0) {
                // 显示已办数量
                //	tab.append('<span class="badge badge-danger">'+recordCount+'</span>');
                //}
            }
            // bandForMore('record', 'KB00WF', 'methodName=getHistoryTask');
            bandForRefresh('record', refreshRecord);
        };

        var refreshRecord = function () {
            // refreshService('KB00WF', 'getHistoryTask', drawRecord);
        };

        // 通知
        var drawNotification = function (ei) {
            var tab = clearPanel('notification');

            // 通知数量
            var notifyCount = parseInt(ei.get('count'));
            if (notifyCount === 0) {
                $("#notification").kendoPanelBar({
                    dataSource: {
                        text: '暂无未阅通知！'
                    }
                });
            } else {
                var result = ei.getBlock("result").getMappedRows();
                // 通知数据
                var notifyData = [];
                // 通知模板
                var notifyTmpl = kendo.template($("#notify-template").html());
                // 构造通知显示内容
                $.each(result, function (i, n) {
                    notifyData.push({
                        text: notifyTmpl(n),
                        encoded: false
                    });
                });
                // 渲染通知
                $("#notification").kendoPanelBar({
                    dataSource: notifyData
                });
                // 渲染通知数量
                if (notifyCount > 0) {
                    // 显示通知数量
                    tab.append('<span class="badge badge-danger">' + notifyCount + '</span>');
                }
                // 清除原来的事件
                $("#notification").off("click", "[data-notify-guid]");
                // 重新绑定关闭事件
                $("#notification").on("click", "[data-notify-guid]", function (e) {
                    var guid = $(e.target).data('notifyGuid');
                    var rows = [{
                        guid: guid
                    }];
                    var info = new EiInfo();
                    info.set('data', rows);
//                     EiCommunicator.send('KBCT00', 'confirm', info, {
//                         onSuccess: function (ei) {
//                             if (ei.getStatus() === -1) {
//                                 IPLAT.alert('<b>' + ei.getMsg() + '</b>', function (e) {
//                                 }, '警告');
//                                 return false;
//                             }
// //                            IPLAT.alert('通知已读');//20200326.yanzj 领导不想看删除后的提示
//                             refreshNotification();
//                         },
//                         onFail: function (errorMsg, status, e) {
//                             console.error(e);
//                             NotificationUtil(errorMsg, "error");
//                         }
//                     });

                });
            }
            bandForMore('notification', 'KBCT00', 'methodName=query');
            bandForRefresh('notification', refreshNotification);

        };
        var refreshNotification = function () {
            // refreshService('KBCTNotify', 'queryNotification', drawNotification);
        };

        // 公告
        var drawNotice = function (ei) {
            var tab = clearPanel('notice');

            var MAX_NOTICE_DISPLAY = 10;
            var result = ei.get("result");
            // console.debug(JSON.stringify(result, null,2));
            // 公告数量
            var noticeCount = result.length;
            if (noticeCount === 0) {
                $("#notice").kendoPanelBar({
                    dataSource: {
                        text: '暂无未阅公告！'
                    }
                });
            } else {
                // 公告数据
                var noticeData = [];
                // 公告模板

                var noticeHtml = kendo.template($("#notice-template").html())({
                    nodes: result,
                    encode: false
                });
                $("#notice").append(noticeHtml).resize();
                $('.slick-notice').css({
                    height: "100%",
                    overflow: "hidden"
                });
                $('.slick-notice').slick({
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    dots: true,
                    autoplay: false,
                    autoplaySpeed: 5000,
                    infinite: true,
                    appendDots: $("#i-region-dots-append-notice"),
                    dotsClass: 'slick-dots i-slick-dots'
                });
                $('#info-board').data('kendoTabStrip').setOptions({
                    show: function (e) {
                        if (e.contentElement.id === 'info-board-6') {
                            $('.slick-notice')[0].slick.refresh();
                        }
                    }
                });

                // 渲染公告数量
                if (noticeCount > 0) {
                    // 显示通知数量
                    tab.append('<span class="badge badge-danger">' + noticeCount + '</span>');
                }

                $("#notice").off('click', '.notice-close[data-notice-guid]');
                $("#notice").on('click', '.notice-close[data-notice-guid]', function (e) {
                    var guid = $(e.target).data('noticeGuid');
                    console.debug('notice.guid', guid);
                    var info = new EiInfo();
                    info.set('guid', guid);

                    // EiCommunicator.send('KBCTNotify', 'closeNotice', info, {
                    //     onSuccess: function (ei) {
                    //         if (ei.getStatus() === -1) {
                    //             IPLAT.alert('<b>' + ei.getMsg() + '</b>', function (e) {
                    //             }, '警告');
                    //             return false;
                    //         }
                    //         refreshNotice();
                    //     },
                    //     onFail: function (errorMsg, status, e) {
                    //         console.error(e);
                    //         NotificationUtil(errorMsg, "error");
                    //     }
                    // });

                });
            }
            bandForRefresh('notice', refreshNotice);
        };

        var refreshNotice = function () {
            var info = new EiInfo();
            info.set('unread', 'true');
            // refreshService('KBCTNotify', 'queryNotice', drawNotice, info);
        };
        // 首页消息主看板的Tab的图标的添加
        window.onload = function () {

            // 准备条数
            var tabElements = $("#info-board>ul>li[role='tab']");
            var tabElementsTwo = $("#info>ul>li[role='tab']");
            // 待办
            tabElements.eq(0).prepend('<span class="k-link k-distance"><span class="index-todo"></span></span>');
            // 提醒
            tabElements.eq(1).prepend('<span class="k-link k-distance"><span class="index-warn"></span></span>');
            // 跟踪
            //tabElements.eq(2).prepend('<span class="k-link"><span class="index-icon index-follow"></span></span>');
            // 已办
            tabElements.eq(3).prepend('<span class="k-link k-distance"><span class="index-record"></span></span>');
            // 通知
            tabElements.eq(4).prepend('<span class="k-link k-distance"><span class="index-notification"></span></span>');
            // 公告
            tabElements.eq(5).prepend('<span class="k-link k-distance"><span class="index-announcement"></span></span>');


            tabElementsTwo.eq(0).prepend('<span class="k-link k-distance"><span class="index-icon-fav index-fav"></span></span>');
            tabElementsTwo.eq(1).prepend('<span class="k-link k-distance"><span class="index-icon-link index-link"></span></span>');

            var tabPanel = $('#info-board').data('kendoTabStrip');
            // 刷新看板数据
            // 待办
            refreshTodo();
            (function () {//定时刷新
                var refreshLimits = null;
                if (needRefresh) {//指定刷新
                    $('.i-index-todo .refresh-timer').removeClass('hide');
                    try {
                        taskRefeshTime = IPLAT.trimString(taskRefeshTime);
                        taskRefeshTime = taskRefeshTime == '' ? 5 * 60 * 1000 : (taskRefeshTime * 1);
                        refreshLimits = refreshLimits || taskRefeshTime;
                        var refreshTimmer = new RefreshTimer().setLimits(refreshLimits).setSlice(1000);
                        $('.i-index-todo .refresh-timer .refresh-limits').text(refreshTimmer.seconds2Minutes(refreshLimits / 1000));

                        var ticker = function (refresher) {
                            var text = refreshTimmer.seconds2Minutes(refresher.getSpareSeconds());
                            $('.i-index-todo .refresh-timer .spare-seconds').text(text);
                        };
                        var trigger = function (refresher) {
                            refreshTodo();
                        };
                        refreshTimmer.setTicker(ticker).setTrigger(trigger).start();
                    } catch (ex) {
                        console.error(ex);
                    }
                }
            })();
            // 提醒
            refreshInformation();
            // tabPanel.hide(1);
            // 跟踪
            //refreshTrace();
//            tabPanel.hide(1);
//            tabPanel.hide(2);
//            tabPanel.hide(4);
//            tabPanel.hide(5);
            // 已办
            refreshRecord();
            // 通知
            refreshNotification();
            // 公告
            refreshNotice();

        };
    };

    V6Index.prototype.tabs = function () {
        var page_list = $('#page-list').data('kendoTabStrip');
        if (page_list) {
            page_list.tabGroup.on("click", "[data-type='remove']", function (e) {
                e.preventDefault();
                e.stopPropagation();
                var item = $(e.target).closest(".k-item");
                page_list.remove(item.index());
                if (page_list.items().length > 0 && item.hasClass('k-state-active')) {
                    page_list.select(0);
                }
            });
            this.tabs = page_list;
        }
    };


    window.V6Index = V6Index;

    V6Index.uiLayout = uiLayout;

    // 设置人员下拉框中明细信息
    var setUserInfoValue = function (name, obj, key, value) {
        var node = $("#user-info-dropdown").find('.detail-info.' + name);
        if (!node || !obj || !obj[key] || !obj[value]) {
            return false;
        }
        var display = obj[key] + ' - ' + obj[value];
        node.text(display);
    };

    var setUserInfoValueByOrg = function (name, obj, key, value) {
        var node = $("#user-info-dropdown").find('.detail-info.' + name);
        if (!node || !obj || !obj[key] || !obj[value]) {
            return false;
        }
        if (name.indexOf("-oth") === -1) {
            var display = obj[key];
            node.text(display);
        } else {
            var display = obj[value];
            node.text(display);
        }
    };

    // 设置人员组织机构信息
    var setUserOrg = function (obj) {
        setUserInfoValue('user-org', obj, 'orgEname', 'orgCname');
        // 附加设置代理委托用户
        var node = $("#user-info-dropdown").find('.delegate-user');
        if (!node) {
            return false;
        } else if (!obj || !obj.delegateLoginName || !obj.delegateLoginName.trim()) {
            node.text('');
            return false;
        }

        node.text('[' + obj.delegateLoginName + '-' + obj.delegateUserName + ']');
        $('.change-password').remove();
    };

    // 设置人员公司别账套信息
    var selectAccountSet = function (accountSetObj) {
        setUserInfoValue('user-company', accountSetObj, 'companyCode', 'companyName');
        setUserInfoValue('user-accountset', accountSetObj, 'accountSet', 'accountSetName');
        $('#header-navbar>ul.pull-right>li[data-accountset]>a>span').text(accountSetObj.accountSetName);
        setTimeout(function () {
            V6Index.prototype.meet();
        }, 200);
    };

    // 设置人员业务组织机构信息
    var selectOrg = function (data, blockId) {
        // console.debug(blockId, data);
        if (blockId) {
            setUserInfoValueByOrg('user-' + blockId + '-org', data, 'orgEname', 'orgCname');
            setUserInfoValueByOrg('user-' + blockId + '-org-oth', data, 'orgEname', 'orgCname');
        }
    };

    // 打开公司别、账套选择弹框
    var openAccountSet = function (type) {
        wChangeUserInfoWindow.content('');
        wChangeUserInfoWindow.setOptions({
            // 打开是刷新
            open: function () {
                wChangeUserInfoWindow.refresh({
                    url: IPLATUI.CONTEXT_PATH + '/web/KB00?methodName=query'
                });
                if (type === 'auto') {
                    wChangeUserInfoWindow.element.siblings().find('div.k-window-actions').hide();
                } else {
                    wChangeUserInfoWindow.element.siblings().find('div.k-window-actions').show();
                }
            },
            height: "50%"
        });
        wChangeUserInfoWindow.open().center();
    };

    // 打开业务组织机构选择弹框
    var openOrg = function (type) {
        wChangeUserInfoWindow.content('');
        wChangeUserInfoWindow.setOptions({
            // 打开是刷新
            open: function () {
                wChangeUserInfoWindow.refresh({
                    url: IPLATUI.CONTEXT_PATH + '/web/KB01?methodName=query&inqu_status-0-type=' + type
                });
            },
            height: "80%"
        });
        wChangeUserInfoWindow.open().center();
    };


    $.extend(window, {
        setUserOrg: setUserOrg,
        selectAccountSet: selectAccountSet,
        selectOrg: selectOrg,
        openAccountSet: openAccountSet,
        openOrg: openOrg
        //refreshNotify: refreshNotify
    });

    $(window).load(function () {

        initBusinessShortCut();

        $("#user-info-dropdown").on("click", "[data-stopPropagation]", function (e) {
            e.stopPropagation();
        });

        // 切换账套按钮
        $("#header-navbar").on("click", "[data-accountset]", function (e) {
            openAccountSet('manual');
        });
        $("#user-info-dropdown").on("click", "[data-accountset]", function (e) {
            openAccountSet('manual');
        });

        // 切换组织按钮
        $("#user-info-dropdown").on("click", "[data-org]", function (e) {
            openOrg($(this).data('org'));
        });
        // refreshNotify();
        $("#user-info-dropdown").on("click", ".k-link.user-name", function (e) {
            IPLAT.confirm({
                message: '<b>切换代理将<span style="color: #ff0000">关闭</span>所有已打开的画面<br>确定执行该操作吗？</b>',
                okFn: function (e) {
                    window.location.href = IPLATUI.CONTEXT_PATH + '/web/KB00DL?methodName=query';
                },
                cancelFn: function (e) {
                },
                title: '确认对话框'
            });
        });

        //项目模块信息管理
        $(".onclickOpenFrom1").on("click", function () {
            IPLAT.openForm("EDPI01");
        });
        //页面信息管理
        $(".onclickOpenFrom2").on("click", function () {
            IPLAT.openForm("EDFA00");
        });
        //按钮信息管理
        $(".onclickOpenFrom3").on("click", function () {
            IPLAT.openForm("EDFA01");
        });
        //菜单信息管理
        $(".onclickOpenFrom4").on("click", function () {
            IPLAT.openForm("EDPI10");
        });


        //微服务信息管理
        $(".onclickOpenFrom5").on("click", function () {
            IPLAT.openForm("EDXM01");
        });
        //微服务事件管理
        $(".onclickOpenFrom6").on("click", function () {
            IPLAT.openForm("EDXM00");
        });
        //配置环境
        $(".onclickOpenFrom7").on("click", function () {
            IPLAT.openForm("EDCC02");
        });
        //配置信息
        $(".onclickOpenFrom8").on("click", function () {
            IPLAT.openForm("EDCC03");
        });


        //用户信息管理
        $(".onclickOpenFrom9").on("click", function () {
            IPLAT.openForm("XS01");
        });
        //用户群组管理
        $(".onclickOpenFrom10").on("click", function () {
            IPLAT.openForm("XS02");
        });
        //代码类别管理
        $(".onclickOpenFrom11").on("click", function () {
            IPLAT.openForm("EDCM00");
        });
        //序列号管理
        $(".onclickOpenFrom12").on("click", function () {
            IPLAT.openForm("EDMDM2");
        });


        //资源信息管理
        $(".onclickOpenFrom13").on("click", function () {
            IPLAT.openForm("XS04");
        });
        //资源组信息管理
        $(".onclickOpenFrom14").on("click", function () {
            IPLAT.openForm("XS05");
        });
        //资源群组成员信息管理
        $(".onclickOpenFrom15").on("click", function () {
            IPLAT.openForm("XS06");
        });
        //授权管理
        $(".onclickOpenFrom16").on("click", function () {
            IPLAT.openForm("XS08");
        });
        //维修
        $("#maintain").on("click", function () {
            IPLAT.openForm("MTRG01");
        });
        //运送
        $("#transport").on("click", function () {
            IPLAT.openForm("YSDJ01");
        });
        //陪检
        $("#peijian").on("click", function () {
            IPLAT.openForm("PJDJ01");
        });
        // 对于超长的 菜单项，显示叠层
        (function () {
            // 构造
            var MENU_ITEM_TITLE = 'cls-menu-item-title';
            var joParent = null;
            var joTitle = $('.' + MENU_ITEM_TITLE);// 添加动态显示条
            if (joTitle.length == 0) {
                joTitle = $('<span class="' + MENU_ITEM_TITLE + ' left-hide"></span>');
                joParent = joParent || $('#page-container');
                joParent.append(joTitle);
            }

            // 检查，项目是否被覆盖
            var checkItemCovered = function (joItem) {
                var maxWidth = $('#iplat-menu').width();
                var titleWith = $('.' + MENU_ITEM_TITLE).width() + 28 + 2;// 文本内容 + padding + 线宽
                var titleLeft = joItem.find('.k-in:last').offset().left;// 真实项目的左偏移
                if (titleLeft + titleWith > maxWidth) {
                    return true;
                }
                return false;
            };
            // 显示
            var showItemTitle = function (joItem) {
                var offset = joItem.find('.k-in:first').offset();
                joTitle.text(joItem.find('.k-in:first').text());
                if (checkItemCovered(joItem)) {
                    joTitle.css({
                        left: offset.left - 1, // 减边框宽度
                        top: offset.top
                    });
                    joTitle.removeClass('left-hide');
                }
            };
            // 隐藏
            var hideItemTitle = function (joItem) {
                joTitle.addClass('left-hide');
            };

            $('#iplat-menu').on('mouseover', 'ul.k-group > li.k-item', function (e) {
                var joItem = $(e.target).closest('.k-item');
                // 显示
                try {
                    showItemTitle(joItem);
                } catch (ex) {
                }
            });
            $('#iplat-menu').on('mouseout', 'ul.k-group > li.k-item', function (e) {
                var joItem = $(e.target).closest('.k-item');
                try {
                    hideItemTitle(joItem);
                } catch (ex) {
                }
            });
        })();

    });

    /**
     * 定时刷新器.
     */
    var RefreshTimer = function () {
        this._start = null;
        this._slicer = null;
        this._slice = null;
        this._limits = null;
        this._trigger = null;
    };
    RefreshTimer.prototype = {
        setTicker: function (ticker) {
            this._ticker = ticker;
            return this;
        },
        setSlice: function (slice) {
            this._slice = slice;
            return this;
        },
        getSpareSeconds: function () {
            return parseInt((this._start + this._limits - new Date().getTime()) / 1000) + 1;
        },
        setLimits: function (limits) {
            this._limits = limits;
            return this;
        },
        setTrigger: function (trigger) {
            this._trigger = trigger;
            return this;
        },
        seconds2Minutes: function (seconds) {
            var min = parseInt(seconds / 60);
            var sec = parseInt(seconds % 60);
            var text = (min < 10 ? '0' : '') + min + ':' + (sec < 10 ? '0' : '') + sec;
            return text;
        },
        startTicker: function () {
            var that = this;
            var loop = function () {
                if ($.isFunction(that._ticker)) {
                    try {
                        that._ticker(that);
                    } catch (ex) {
                    }
                }
            };
            this._sliceTimer = setInterval(loop, this._slice);
        },
        start: function () {
            var that = this;
            this._start = new Date().getTime();
            var loop = function () {
                that._start = new Date().getTime();
                if ($.isFunction(that._trigger)) {
                    try {
                        that._trigger(that);
                    } catch (ex) {
                    }
                }
                setTimeout(loop, that._limits);
            };
            setTimeout(loop, this._limits);
            this.startTicker();
        }
    };

})(jQuery, _);


$(function () {
    var taskCount = 0;
    if (taskCount === 0) {
        var kendoPanelBar = $("#todo").kendoPanelBar({
            dataSource: {
                text: '暂无待办任务！'
            }
        });
    }

    var recordCount = 0;
    if (recordCount === 0) {
        $("#record").kendoPanelBar({
            dataSource: {
                text: '暂无已办任务！'
            }
        });
    }

    var notifyCount = 0;
    if (notifyCount === 0) {
        $("#notification").kendoPanelBar({
            dataSource: {
                text: '暂无未阅通知！'
            }
        });
    }

    var noticeCount = 0;
    if (noticeCount === 0) {
        $("#notice").kendoPanelBar({
            dataSource: {
                text: '暂无未阅公告！'
            }
        });
    }

//    var seasons = changePictures();
//    // var imageLocation = $("#solarTermsPic")[0].src;
//
//    switch (seasons) {
//        case "小寒":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/winter.png";//该节气暂无图片
//            break;
//        case "大寒":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/winter.png";//该节气暂无图片
//            break;
//        case "立春":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/spring.png";//该节气暂无图片
//            break;
//        case "雨水":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/spring.png";//该节气暂无图片
//            break;
//        case "惊蛰":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/spring.png";//该节气暂无图片
//            break;
//        case "春分":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/spring.png";
//            break;
//        case "清明":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/spring.png";//该节气暂无图片
//            break;
//        case "谷雨":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/grain-rain.jpg";
//            break;
//        case "立夏":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/summer.png";
//            break;
//        case "小满":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/grain-buds.jpg";
//            break;
//        case "芒种":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/grain-buds.jpg";//该节气暂无图片
//            break;
//        case "夏至":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/grain-buds.jpg";//该节气暂无图片
//            break;
//        case "小暑":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/grain-buds.jpg";//该节气暂无图片
//            break;
//        case "大暑":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/grain-buds.jpg";//该节气暂无图片
//            break;
//        case "立秋":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/autumn.png";
//            break;
//        case "处暑":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/autumn.png";//该节气暂无图片
//            break;
//        case "白露":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/autumn.png";//该节气暂无图片
//            break;
//        case "秋分":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/autumn.png";//该节气暂无图片
//            break;
//        case "寒露":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/autumn.png";//该节气暂无图片
//            break;
//        case "霜降":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/autumn.png";//该节气暂无图片
//            break;
//        case "立冬":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/autumn.png";//该节气暂无图片
//            break;
//        case "小雪":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/autumn.png";//该节气暂无图片
//            break;
//        case "大雪":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/winter.png";
//            break;
//        case "冬至":
//            $("#solarTermsPic")[0].src = "iplatui/img/index/season/winter.png";//该节气暂无图片
//            break;
//    }

    function changePictures(yyyy, mm, dd) {
        if (!yyyy || !mm || !dd) {
            var now = new Date();
            yyyy = now.getFullYear(); //年
            mm = now.getMonth() + 1; //月
            dd = now.getDate(); //日
        }

        mm = mm - 1;

        var sTermInfo = [0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072,
            240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758];
        var solarTerm = ["小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满",
            "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"];
        var solarTerms = "";

        while (solarTerms == "") {
            var tmp1 = new Date((31556925974.7 * (yyyy - 1900) + sTermInfo[mm * 2 + 1] * 60000) + Date.UTC(1900, 0, 6, 2, 5));
            var tmp2 = tmp1.getUTCDate();

            if (tmp2 == dd) solarTerms = solarTerm[mm * 2 + 1];

            tmp1 = new Date((31556925974.7 * (yyyy - 1900) + sTermInfo[mm * 2] * 60000) + Date.UTC(1900, 0, 6, 2, 5));
            tmp2 = tmp1.getUTCDate();
            if (tmp2 == dd) solarTerms = solarTerm[mm * 2];

            if (dd > 1) {
                dd = dd - 1;
            } else {
                mm = mm - 1;

                if (mm < 0) {
                    yyyy = yyyy - 1;
                    mm = 11;
                }
                dd = 31;
            }
        }
        return solarTerms;
    }
})


