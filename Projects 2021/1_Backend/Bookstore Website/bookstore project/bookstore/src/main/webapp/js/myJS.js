

function parseQueryString(url) {
    let str = url.split("?")[1];
    let params = str.split("&");
    let arr, Json = {};
    for (let i = 0; i < params.length; i++) {
        arr = params[i].split("=");
        Json[arr[0]] = arr[1];
        return Json;
    }
}
;

function getCategory() {
    let url = window.location.href;
    let urlData = this.parseQueryString(url);
    alert("hello!!!");
    alert(urlData);
    alert(urlData.category);
    return urlData.category;
}