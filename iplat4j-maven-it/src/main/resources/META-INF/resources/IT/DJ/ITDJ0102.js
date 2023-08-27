$(function (){
    // 加载数据回显
    $("#impFlag").val(__ei.impFlag)

    $("#CLOSE").on("click", function (e) {
        closeParentWindow()
    });
})

function closeParentWindow() {
    window.parent['popDataWindow'].close();
}