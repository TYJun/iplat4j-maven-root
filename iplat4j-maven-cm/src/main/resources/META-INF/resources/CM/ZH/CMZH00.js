$(function () {
    var info = new EiInfo();
    var splitDate = "";
    var normal = "";
    var oneMonth = "";
    var twoMonth = "";
    var threeMonth = "";
    var overdue = "";
    var total = "";
    var line = "";

    IPLATUI.EFSelect = {
        "splitDate": {
            select: function (e) {
                var dataItem = e.dataItem;
                splitDate = dataItem.valueField;
                info.set("splitDate", splitDate);
            }, change: function (e) {
                EiCommunicator.send("CMZH00", "querySplitDate", info, {
                    onSuccess: function (ei) {
                        //console.log(ei.blocks.result.meta.metas.normal.pos);
                        //console.log(ei.blocks.result.rows[0][ei.blocks.result.meta.metas.normal.pos]);
                        var dataItems = resultGrid.getDataItems();
                        resultGrid.removeRows(dataItems);
                        var model = createModel(1);
                        model["normal"] = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.normal.pos];
                        normal = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.normal.pos];
                        model["oneMonth"] = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.oneMonth.pos];
                        oneMonth = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.oneMonth.pos];
                        model["twoMonth"] = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.twoMonth.pos];
                        twoMonth = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.twoMonth.pos];
                        model["threeMonth"] = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.threeMonth.pos];
                        threeMonth = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.threeMonth.pos];
                        model["overdue"] = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.overdue.pos];
                        overdue = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.overdue.pos];
                        model["total"] = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.total.pos];
                        total = ei.blocks.result.rows[0][ei.blocks.result.meta.metas.total.pos];
                        resultGrid.addRows(model);
                    }
                });

                EiCommunicator.send("CMZH00", "queryLineChart", info, {
                    onSuccess: function (ei) {
                        line = ei.blocks.result.rows;
                        setTimeout(function () {
                            echart(normal, oneMonth, twoMonth, threeMonth, overdue, line);
                        }, 300);
                    }
                });
            }
        }
    }
});

IPLATUI.EFGrid = {
    "result": {
        pageable: false,
        exportGrid: false,
    }


}

function createModel(id) {
    var gridRow = kendo.data.Model.define({
        id: "uploadId",
        fields: {
            "normal": {
                type: "string"
            },
            "oneMonth": {
                type: "string"
            },
            "twoMonth": {
                type: "string"
            },
            "threeMonth": {
                type: "string"
            },
            "overdue": {
                type: "string"
            }, "total": {
                type: "string"
            },
        }
    });
    var model = new gridRow({uploadId: id});
    return model;
}

function echart(normal, oneMonth, twoMonth, threeMonth, overdue, line) {
    var date = [];
    var count = [];
    for(j = 0; j < line.length; j++) {
        date.push(line[j][0]);
        count.push(line[j][1]);
    }
    // 基于准备好的dom，初始化echarts实例
    var myChartA = echarts.init(document.getElementById('mainA'));
    var myChartB = echarts.init(document.getElementById('mainB'));
    // 指定图表的配置项和数据
    var optionA = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {type: 'cross'}
        },
        xAxis: [
            {
                type: 'category',
                name: '登记时间',
                axisTick: {
                    alignWithLabel: true
                },
                data: date
            }
        ],
        yAxis: [
            {},
            {
                type: 'value',
                name: '数量',
                min: 0,
                max: 50,
                position: 'left',
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name: '合同登记数量',
                type: 'line',
                smooth: false,
                yAxisIndex: 1,
                data: count
            }
        ]
    };

    var optionB = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: '',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '40',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    {value: overdue, name: '超期'},
                    {value: threeMonth, name: '临期三个月'},
                    {value: twoMonth, name: '临期两个月'},
                    {value: oneMonth, name: '临期一个月'},
                    {value: normal, name: '正常'}
                ]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChartA.setOption(optionA);
    myChartB.setOption(optionB);
}
