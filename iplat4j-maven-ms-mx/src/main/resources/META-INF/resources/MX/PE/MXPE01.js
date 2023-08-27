let bjslEcharts, bjlxEcharts, bjslOption, bjlxOption, bjwzEcharts, bjwzOption, lsbjEcharts, lsbjOption;
$(function() {

    $("#inqu_status-0-parentId").css("display", "none");

    IPLATUI.EFTree = {
        "tree01": {
            ROOT: {
                label: "root",
                text: "智能监控",
                leaf: true
            },

            select: function(e) {
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

    // 报警数量统计
    bjslEcharts = echarts.init(document.getElementById("bjslChart"));
    bjslOption = {
        title: {
            text: '报警数量统计',
            textStyle:{
                color: '#1F65DA',
                fontSize: 18,
                fontWeight: 100
            },
            left: 'center',
            top: '5%'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        xAxis: {
            type: 'value',
            axisLabel: {
                color: '#000'
            },
            splitLine: {
                lineStyle: {
                    type: 'dashed',
                    color: '#000'
                }
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#000'
                }
            },
        },
        yAxis: {
            data: ['实时报警数量','今日解除报警数量','已确认的报警数量','未确认的报警数量'],
            axisLabel: {
                color: '#000',
                fontSize: 14,
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#000'
                }
            },
            splitLine:{
                show:true,
                lineStyle: {
                    type: 'solid',
                    color: '#000',
                }
            }
        },
        grid: [{
            bottom: '15%',
            top: '20%',
            left: '15%',
            right: '5%',
        }],
        series:
            [{
                type: 'bar',
                barWidth: '40',
                color:'#ffac3e',
                data: []
            }]
    };
    bjslEcharts.setOption(bjslOption);
    // 报警类型占比分析
    bjlxEcharts = echarts.init(document.getElementById("bjlbEchart"));
    bjlxOption = {
        color: ['#1F65DA', '#00bcd4', '#368FFF', '#03a9f4'],
        title: {
            text: '报警类型占比分析',
            textStyle:{
                color: '#1F65DA',
                fontSize: 18,
                fontWeight: 100
            },
            left: 'center',
            top: '5%'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: '5%',
            top: 'center',
            textStyle:{
                color: '#000',
                fontSize: 14
            }
        },
        series: [
            {
                type: 'pie',
                radius: '60%',
                center: ["60%", "55%"],
                data: [],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    bjlxEcharts.setOption(bjlxOption);
    // 报警位置占比分析
    bjwzEcharts = echarts.init(document.getElementById("bjwzEchart"));
    bjwzOption = {
        color: ['#4caf50', '#009688', '#368FFF', '#00bcd4'],
        title: {
            text: '报警位置占比分析',
            textStyle:{
                color: '#1F65DA',
                fontSize: 18,
                fontWeight: 100
            },
            left: 'center',
            top: '5%'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: '5%',
            top: 'center',
            textStyle:{
                color: '#000',
                fontSize: 14
            }
        },
        series: [
            {
                type: 'pie',
                radius: '60%',
                center: ["60%", "55%"],
                data: [],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    bjwzEcharts.setOption(bjwzOption);
    // 历史报警情况
    lsbjEcharts = echarts.init(document.getElementById("lsbjEchart"));
    lsbjOption = {
        title: {
            text: '历史报警情况',
            textStyle:{
                color: '#fff',
                fontSize: 20,
                fontWeight: 100
            },
            left: 'center',
            top: '5%'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: '2%',
            top: 'center',
            textStyle:{
                color: '#fff',
                fontSize: 14
            },
            data:['新增报警数量','剩余报警数量']
        },
        xAxis: {
            type: 'category',
            data: ['7', '8', '9', '10', '11', '12', '13','14', '15', '16', '17', '18', '19', '20','21', '22', '23', '24', '25', '26', '27','28', '29', '30', '31', '1', '2', '3','4', '5', '6'],
            axisLabel: {
                color: '#fff'
            },
            // splitLine: {
            //     show: true,
            //     lineStyle: {
            //         type: 'solid',
            //         color: '#fff'
            //     }
            // },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#000'
                }
            },
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                color: '#fff'
            },
            splitLine: {
                lineStyle: {
                    type: 'dashed',
                    color: '#000'
                }
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#000'
                }
            },
            axisTick: {
                show: false
            },
            min:0,
            max:8,
        },
        series: [
            {
                name: '新增报警数量',
                color: '#ff4d4d',
                type: 'line',
                data: ['0', '2', '1', '3', '4', '0', '5','6', '7', '7', '8', '8', '8', '8','6', '6', '5', '5', '5', '0', '0','0', '0', '0', '0', '0', '3', '8','5', '3', '6']
            },
            // {
            //     name: '解除报警数量',
            //     type: 'line',
            //     color: '#D4851E',
            //     data: ['0', '0', '0', '0', '0', '0', '0','0', '0', '0', '0', '0', '0', '0','0', '0', '0', '0', '0', '0', '0','0', '0', '1', '1', '1', '0', '0','0', '0', '0']
            // },
            // {
            //     name: '确认报警数量',
            //     type: 'line',
            //     color: '#308263',
            //     data: ['0', '0', '0', '0', '0', '0', '0','0', '0', '0', '0', '0', '0', '0','0', '0', '0', '0', '0', '0', '0','0', '0', '1', '2', '1', '0', '0','0', '0', '0']
            // },
            {
                name: '剩余报警数量',
                type: 'line',
                color: '#fbd879',
                data: ['7', '7', '5', '7', '6', '8', '7','0', '3', '7', '7', '7', '5', '7','7', '7', '7', '7', '7', '0', '0','0', '0', '0', '0', '7', '6', '6','6', '7', '7']
            },
        ]
    };
    lsbjEcharts.setOption(lsbjOption);
});