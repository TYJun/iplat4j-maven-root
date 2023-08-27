$(function () {


    IPLATUI.EFTree = {
        "tree01": {
            ROOT: {
                label: "root",
                text: "视屏监控",
                leaf: true
            },

            select: function (e) {
                var _data = this.dataItem(e.node);
                var length = _data.children._data.length;
                var labelValue = _data.label;
                var typeValue = _data.type;
                var URL = _data.url;
                if(document.getElementById('img1')) {
                    document.getElementById('img1').remove();
                } else {
                    var box = document.getElementById('box');
                    var p = document.createElement('img');
                    p.setAttribute("id","img1");
                    box.appendChild(p);
                }
                $('#inqu_status-0-parentOrgId').val(labelValue);
                $('#inqu_status-0-parentOrgType').val(typeValue);
                $('#inqu_status-0-sortIndex').val(length);
                if(URL!=null && URL != ""){
                    $("#img1").attr("src",URL);
                }
            },
        }

    };
})