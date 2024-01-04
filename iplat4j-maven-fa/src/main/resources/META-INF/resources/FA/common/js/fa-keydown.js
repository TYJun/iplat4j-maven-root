function keydown(element, queryButton) {
    $("#"+element).bind("keydown", function(e){
        // 兼容FF和IE和Opera
        let theEvent = e || window.event;
        let code = theEvent.keyCode || theEvent.which || theEvent.charCode;
        if (code == '13') {
            //处理按回车键后的逻辑
            $("#"+queryButton).click();
        }
    });

}