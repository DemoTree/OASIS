$(document).ready(function () {

    $(".iframe_link").on("click", function () { //左侧a链接直接导入右侧iframe
        var url = $(this).attr("href");
        $(".iframe-content").attr("src", url);
        return false;
    });

})