IPLATUI.EFGrid = {
    "result": {
        columns: [{field: "outNum", template: function (item) {
                return ("05" == __ei.outType ? "-" : "") + item['outNum']
            }
        },{field: "outAmount", template: function (item) {
                return ("05" == __ei.outType ? "-" : "") + item['outAmount']
            }
        }]
    },
}