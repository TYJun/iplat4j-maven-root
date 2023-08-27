const tags = [];
let myChart, myChart2, particle;
const option = {
    xAxis: {
        type: 'category',
        data: ['1', '2', '3', '4', '5', '6', '7']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        type: 'line',
        data: [1, 20, 100, 150, 10, 50, 260]
    }]
};
const option2 = {
    color: ['#ca8622', '#c23531', '#749f83', '#bda29a'],
    title: {
        text: '',
        textStyle: {
            color: '#ffffff'
        }
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        textStyle: {
            color: '#ffffff'
        },
        data: ['']
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
            axisLabel: {
                //interval: 0,
                rotate:"45",
                formatter: value => {
                    switch (particle) {
                        case '1d':
                            return value.substring(5,7) + '月 ' + value.substring(8,10) + '日'
                        case '1h':
                            return value.substring(8,10) + '日 ' + value.substring(11,13) + '时'
                        case '5m':
                            return value.substring(11,13) + '时 ' + value.substring(14,16) + '分'
                        case '1s':
                            return value.substring(14,16) + '分 ' + value.substring(17,19) + '秒'
                    }
                }
            }
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: '',
            type: 'line',
            stack: '总量',
            areaStyle: {},
            emphasis: {
                focus: 'series'
            },
            data: [220, 182, 191, 234, 290, 330, 310]
        }
    ]
};
const cacheDatas = {
    defaultDataXY: {dataX: ['8', '9', '10', '11', '12', '13', '14'], dataY: [150, 230, 224, 218, 135, 147, 260]}
}
let container, container2;

let currentTag;

function handleSuccess(e) {
    tags.length = 0;
    let eiInfo = e.eiInfo;
    let rows = eiInfo.blocks.result.rows;
    rows.forEach(function (columns) {
        let scope = columns[columns.length - 6];
        let lower,upper;
        if(scope) {
            let scopeArr = scope.split("~");
            lower = scopeArr[0]
            upper = scopeArr[1]
        }
        tags.push({
            name: columns[2]
        })
    })
    window.websocket.send(JSON.stringify(tags));
}

$(function () {

    $("#inqu_status-0-parentId").css("display", "none");

    IPLATUI.EFTree = {
        "tree01": {
            ROOT: {
                label: "root",
                text: "系统分类",
                leaf: true
            },

            select: function (e) {
                var _data = this.dataItem(e.node);
                var length = _data.children._data.length;
                var labelValue = _data.label;
                var typeValue = _data.type;
                $('#inqu_status-0-parentOrgId').val(labelValue);
                $('#inqu_status-0-parentOrgType').val(typeValue);
                $('#inqu_status-0-sortIndex').val(length);
                $("#inqu_status-0-parentId").val(labelValue);
                var s = $("#inqu_status-0-parentId").val();
                resultGrid.dataSource.page(1);
                $("#inqu_status-0-parentId").attr("value", "");
            },

        }

    };

    ///////////////////////// - WebSocket
    //生成uuid
    function guid() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
    }

    let url = window.location.href;
    url = url.substring(7,url.lastIndexOf("/web"));
    let uuid = guid();
    window.websocket = null;
    if ('WebSocket' in window) {
        window.websocket = new WebSocket("ws://" + url + "/ws/" + uuid);
    } else if ('MozWebSocket' in window) {
        window.websocket = new MozWebSocket("ws://" + url + "/ws/" + uuid);
    } else {
        window.websocket = new SockJS(url + "/ws/" + uuid);
    }

    //连接成功建立的回调方法
    window.websocket.onopen = function () {
        window.websocket.send("连接成功");
        console.log("连接成功建立的回调方法");
    }

    //接收到消息的回调方法
    window.websocket.onmessage = function (event) {
        let rows = JSON.parse(event.data);
        handlerChartData(rows);
        tags.forEach(function (tag) {
            let $tag = $("div.i-validate-helper:contains(" + tag.name + ")");
            $tag.each(function () {
                let $rValueParent = $(this).parent().next();
                let $rValue = $rValueParent.children(":first");
                let row = rows[tag.name];
                if(row) {
                    $rValue.html(row.valText);
                    let $gradeParent = $rValueParent.next();
                    let $grade = $gradeParent.children(":first")
                    $grade.html(row.grade);
                }
            });
        })
        renderChart();
    }

    //连接关闭的回调方法
    window.websocket.onclose = function () {
        console.log("WebSocket连接关闭");
    }

    //连接发生错误的回调方法
    window.websocket.onerror = function () {
        console.log("WebSocket连接发生错误");
    };

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        window.websocket.close();
    }

    container = document.getElementById("container");
    container2 = document.getElementById("container2");

    //////////////////// - 拖拽

    var drag = container, drag2 = container2;
    drag.onmousedown = function(event){
        var event = event || window.event;  //兼容IE浏览器
        var diffX = event.clientX - drag.offsetLeft;
        var diffY = event.clientY - drag.offsetTop;
        if(typeof drag.setCapture !== 'undefined'){
            drag.setCapture();
        }
        document.onmousemove = function(event){
            var event = event || window.event;
            var moveX = event.clientX - diffX;
            var moveY = event.clientY - diffY;
            if(moveX < 0){
                moveX = 0
            }else if(moveX > window.innerWidth - drag.offsetWidth){
                moveX = window.innerWidth - drag.offsetWidth
            }
            if(moveY < 0){
                moveY = 0
            }else if(moveY > window.innerHeight - drag.offsetHeight){
                moveY =  window.innerHeight - drag.offsetHeight
            }
            drag.style.left = moveX + 'px';
            drag.style.top = moveY + 'px'
        }
        document.onmouseup = function(event){
            this.onmousemove = null;
            this.onmouseup = null;
            //修复低版本ie bug
            if(typeof drag.releaseCapture!='undefined'){
                drag.releaseCapture();
            }
        }
    }

    drag2.onmousedown = function(event){
        var event = event || window.event;  //兼容IE浏览器
        var diffX = event.clientX - drag2.offsetLeft;
        var diffY = event.clientY - drag2.offsetTop;
        if(typeof drag2.setCapture !== 'undefined'){
            drag2.setCapture();
        }
        document.onmousemove = function(event){
            var event = event || window.event;
            var moveX = event.clientX - diffX;
            var moveY = event.clientY - diffY;
            if(moveX < 0){
                moveX = 0
            }else if(moveX > window.innerWidth - drag2.offsetWidth){
                moveX = window.innerWidth - drag2.offsetWidth
            }
            if(moveY < 0){
                moveY = 0
            }else if(moveY > window.innerHeight - drag2.offsetHeight){
                moveY =  window.innerHeight - drag2.offsetHeight
            }
            drag2.style.left = moveX + 'px';
            drag2.style.top = moveY + 'px'
        }
        document.onmouseup = function(event){
            this.onmousemove = null;
            this.onmouseup = null;
            //修复低版本ie bug
            if(typeof drag2.releaseCapture!='undefined'){
                drag2.releaseCapture();
            }
        }
    }

    ///////////////////// - echarts
    let chart = document.getElementById("chart");
    myChart = echarts.init(chart);
    if (option && typeof option === 'object') {
        myChart.setOption(option);
    }

    let chart2 = document.getElementById("chart2");
    myChart2 = echarts.init(chart2);
    if (option2 && typeof option2 === 'object') {
        myChart2.setOption(option2);
    }

});

function handlerChartData(rows) {
    Object.keys(rows).forEach(function(key){
        let { val, tss, tsm } = rows[key];
        if(val != null && typeof val != "undefined") {
            let date = new Date(Number(tss.concat(tsm)));
            let hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            let minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            let second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
            if (cacheDatas[key]) {
                //更新缓存
                let dataXY = cacheDatas[key];
                dataXY.dataX.push(hour + ":" + minute + ":" + second);
                dataXY.dataY.push(Number(val));
                if(dataXY.dataX && dataXY.dataX.length > 18) {
                    dataXY.dataX.shift();
                    dataXY.dataY.shift();
                }
            } else {
                cacheDatas[key] = {
                    dataX: [hour + ":" + minute + ":" + second],
                    dataY: [Number(val)]
                }
            }
        }
    });
}

function handleRowDblClick(e) {
    currentTag = e.model.tag;
    container.style.display = "block";
    renderChart();
}

function renderChart() {
    var dataXY = cacheDatas[currentTag];
    if(!dataXY) {
        dataXY = cacheDatas['defaultDataXY'];
    }
    myChart.setOption({
        title: {
            left: 'center',
            text: currentTag,
        },
        xAxis: {
            type: 'category',
            data: dataXY.dataX
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            type: 'line',
            data: dataXY.dataY
        }]
    }, true);
}

function onClose(target) {
    if ('1' === target) {
        container.style.display = "none";
    } else {
        container2.style.display = "none";
    }
}

function changeChart(target) {
    if ('1' === target) {
        container.style.display = "none";
        container2.style.display = "block";
    } else {
        container.style.display = "block";
        container2.style.display = "none";
    }
}

function doSearch() {
    const params = {
        tagName: currentTag,
        start: $("#startDate").val().replace("T"," "),
        end: $("#endDate").val().replace("T"," ")
    }
    $.get(baseUrl + "/sym/query/stats", params, function(res) {
        console.log('res', res)
        let flag = false
        const {code, data: {results}, msg} = res
        particle = msg
        if(code === 0) {
            const {series} = results[0]
            if(series && series.length > 0) {
                const {values} = series[0]
                if(values && values.length > 0) {
                    let dataX=[], dataY=[]
                    values.forEach(entity => {
                        const [time, mean] = entity
                        dataX.push(calcTime(time))
                        if(mean) {
                            dataY.push(mean)
                        }else {
                            dataY.push(0)
                        }
                    })
                    option2.title.text = '时间颗粒：' + particle
                    option2.xAxis[0].data = dataX
                    option2.series[0].data = dataY
                    myChart2.setOption(option2)
                }else {
                    flag = true
                }
            } else {
                flag = true
            }
        }
        if(flag || code !== 0) {
            option2.title.text = ''
            option2.xAxis[0].data = []
            option2.series[0].data = []
            myChart2.setOption(option2)
        }
    })

    // 转化时间戳，加上8个小时
    function calcTime(time) {
        let date = new Date(time);
        let year = date.getFullYear();
        let month = (date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
        let day = date.getDate() > 10 ? date.getDate() : "0" + date.getDate();
        let hour = date.getHours() > 10 ? date.getHours() : "0" + date.getHours();
        let minute = date.getMinutes() > 10 ? date.getMinutes() : "0" + date.getMinutes();
        let second = date.getSeconds() > 10 ? date.getSeconds() : "0" + date.getSeconds();
        return year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second + "Z";
    }
}
