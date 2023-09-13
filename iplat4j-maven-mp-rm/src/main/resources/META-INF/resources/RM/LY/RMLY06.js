let webUrl = window.location.origin; webUrl = webUrl.substring(0,webUrl.lastIndexOf(":")); //'http://119.146.152.53'
$(function () {
    let workNo = __ei["workNo"];
    let frameSchema = __ei["frameSchema"];
    //拼接地址
    //webUrl = 'http://119.146.152.53'; frameSchema = "bona_frame_mzsrmyy";
    let url = `&frameSchema=${frameSchema}&workNo=${workNo}`;
    url = webUrl + ":8081/fr/ReportServer?reportlet=wz/科室领用物资成本表.cpt" + url;
    let reportURL = cjkEncode(url);
    document.getElementById("lyFrame").src = reportURL;
})

/**
 * url编译
 * @param url
 */
function cjkEncode(url) {
    if (url == null) {
        return "";
    }
    let newText = "";
    for (let i = 0; i < url.length; i++) {
        let code = url.charCodeAt (i);
        if (code >= 128 || code == 91 || code == 93) {  //91 is "[", 93 is "]".
            newText += "[" + code.toString(16) + "]";
        } else {
            newText += url.charAt(i);
        }
    }
    return newText;
}