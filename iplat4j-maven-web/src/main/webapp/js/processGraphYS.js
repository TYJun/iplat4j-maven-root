
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
                datalist.push(' <li class="time-li">');
                datalist.push(point);
                datalist.push(' <span></span>');
                datalist.push(' </div>');
                datalist.push(' <div class="timeline-content">');
                datalist.push(' <div class="timeline-top">');
                datalist.push('  <span>' + data[i].content + '</span>');
                datalist.push('</div> ');
                datalist.push(line);
                datalist.push(' <span></span>');
                datalist.push('</div> ');
                datalist.push(content);
                datalist.push(' <div class="timeline-content-child">');
                datalist.push(jobhtml);
                datalist.push(' </div>');
                datalist.push(' <div class="timeline-content-child">');
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
