$(function (){
    if (__ei.status == "10"){
        $("#invalid").show()
    }else{
        $("#invalid").hide()
    }
    // 加载数据回显
    $("#impFlag").val(__ei.impFlag)

    $("#CLOSE").on("click", function (e) {
        closeParentWindow()
    });
})

function closeParentWindow() {
    window.parent['popDataWindow'].close();
}