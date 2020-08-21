function makeLink(msg, link, len = 20) {
    let str = String(msg);
    // return '<a class="link" href="' + link + '">' + (str.length > len ? (str.slice(0, len) + '...') : str) + '</a>';
    return (str.length > len ? (str.slice(0, len) + '...') : str);
}

$(document).ready(function () {

    function getTopPublication() {
        getRequest(
            '/interest/publication',
            function (res) {
                console.log(res.content);
                $(".top-publication").empty();
                var str = "";
                res.content.forEach(function (one) {
                    str +=
                        "<li><a><span class='item' value='" + one.publicationTitle + "'>" + makeLink(one.publicationTitle ,"/author?authorName="+one.publicationTitle ) + "</span>" +
                        "<span class='index-num' style='float:right'>" + one.documentCount + "</span></a></li>";

                });
                document.getElementById("top-publication").innerHTML = str;
            }
        )

    }

    function getTopDocument() {
        getRequest(
            '/interest/document',
            function (res) {
                console.log(res.content);
                $(".top-reference").empty();
                var str = "";
                res.content.forEach(function (one) {
                    str +=
                        "<li class='item' value='" + one + "'><a>" + makeLink(one,"/PaperDetail?name="+one) + "</a></li>";
                });
                document.getElementById("top-reference").innerHTML = str;
            }
        )
    }

    getTopDocument();
    getTopPublication();

    // $("#top-publication").on('click','.item',function(){
    //     var keyword=$(this).attr('value');
    //     console.log(keyword);
    //     getRequest(
    //         '/searchPortrait/meeting?meeting='+keyword,
    //         function (res) {
    //             console.log(res);
    //             console.log(res.content);
    //             sessionStorage.clear();
    //             sessionStorage.setItem('meetingInfo',JSON.stringify(res.content));
    //             //window.location.href="/AuthorPortrait";
    //             window.location.href="/MeetingPortrait";
    //         },
    //         function (error) {
    //             console.log("错了");
    //             alert(error);
    //         }
    //     )
    // });
    //
    // $("#top-reference").on('click','.item',function(){
    //     var keyword=$(this).attr('value');
    //     console.log(keyword);
    //     getRequest(
    //         '/search/title?title='+keyword,
    //         function (res) {
    //             console.log(res);
    //             console.log(res.content);
    //             sessionStorage.clear();
    //             sessionStorage.setItem('paperInfo',JSON.stringify(res.content));
    //             //window.location.href="/AuthorPortrait";
    //             window.location.href="/PaperDetailPage";
    //         },
    //         function (error) {
    //             console.log("错了");
    //             alert(error);
    //         }
    //     )
    // });
});