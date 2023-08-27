
// 流程时间图绘制方法
function FnTimeline(id, data) {
    if(data != null) {
        var datalist = [];
        var count = data.length;
        if(count > 0) {
            for(var i = 0; i < count; i++) {

                var point = ' <div class="timeline-point point-stateed">';
                var line = ' <div class="my-line line-stateed">';
                var content = ' <div class="timeline-bottom">';
                var jobhtml = ' <span>' + data[i].job + '</span>';

                if(data[i].state == 0) {
                    point = ' <div class="timeline-point point-stateing">';
                    line = ' <div class="my-line line-stateing">';
                    content = ' <div class="timeline-bottom linebottom-stateing">';
                    jobhtml = ' <span class="linetop-stateing">' + data[i].job + '</span>';
                }

                datalist.push(' <li class="time-li">');
                datalist.push(point);
                datalist.push(' <span></span>');
                datalist.push(' </div>');
                datalist.push(' <div class="timeline-content">');
                datalist.push(' <div class="timeline-top">');
                if(data[i].timelimit == "") {
                    datalist.push(' <label></label>');
                } else {
                    datalist.push(' <label>时限' + data[i].timelimit + '天</label>');
                }

                datalist.push(jobhtml);
                datalist.push('</div> ');
                datalist.push(line);
                datalist.push(' <span></span>');
                datalist.push('</div> ');
                datalist.push(content);
                datalist.push(' <div class="timeline-content-child">');
                datalist.push('<span>' + data[i].name + '</span> ');
                datalist.push(' <span>' + data[i].time + '</span>');
                datalist.push(' </div>');
                datalist.push(' <div class="timeline-content-child">');
                datalist.push(' <label>备注:</label>');
                datalist.push(' <span>' + data[i].remark + '</span>');
                datalist.push(' </div>');
                datalist.push('</div> ');
                datalist.push(' </div>');
                datalist.push(' </li>');

            }

        }

        var datahtml = datalist.join("");
        $("#" + id).append(datahtml);
    }
}
