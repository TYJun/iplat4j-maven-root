$(function() {

    $("#inqu_status-0-parentId").css("display", "none");

    IPLATUI.EFTree = {
        "tree01": {
            ROOT: {
                label: "root",
                text: "设备区域",
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
});
