$(function () {
    IPLATUI.EFGrid = {
        "result": {
            columns: [{field: "enterNum", template: function (item) {
                    return ("05" == __ei.enterType ? "-" : "") + item['enterNum']
                }
            },{field: "enterAmount", template: function (item) {
                    return ("05" == __ei.enterType ? "-" : "") + item['enterAmount']
                }
            }]
        },
    }
})