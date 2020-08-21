function makeLink(msg, link) {
    return msg;
    // return '<a class="link" href="' + link + '">' + msg + '</a>';
}

$(document).ready(function () {
    var pub;
    var cite;

    function getPub() {
        getRequest(
            '/pub-rank',
            function (res) {
                console.log(res);
                $(".top-publication").empty();
                var str = "";
                res.forEach(function (one) {
                    str +=
                        "<li><a><span class='item' value='" + one.author + "'>" + makeLink(one.author ,"/author?authorName="+one.author ) + "</span>" +
                        "<span class='index-num' style='float:right'>" + one.pubCount + "</span></a></li>";

                });
                document.getElementById("top-publication").innerHTML = str;
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )
    }

    function getCite() {
        getRequest(
            '/cite-rank',
            function (res) {
                console.log(res);
                $(".top-reference").empty();
                var str = "";
                res.forEach(function (one) {
                    str +=
                        "<li><a><span class='item' value='" + one.author + "'>" + makeLink(one.author ,"/author?authorName="+one.author ) + "</span>" +
                        "<span class='index-num' style='float:right'>" + one.citeCount + "</span></a></li>";
                });
                document.getElementById("top-reference").innerHTML = str;
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )
    }

    getPub();
    getCite();

    // $("#top-publication").on('click','.item',function(){
    //     var keyword=$(this).attr('value');
    //     console.log(keyword);
    //     getRequest(
    //         '/searchPortrait/author?authorName='+keyword,
    //         function (res) {
    //             console.log(res);
    //             console.log(res.content);
    //             sessionStorage.clear();
    //             sessionStorage.setItem('authorInfo',JSON.stringify(res.content));
    //             //window.location.href="/AuthorPortrait";
    //             window.location.href="/AuthorPortrait";
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
    //         '/searchPortrait/author?authorName='+keyword,
    //         function (res) {
    //             console.log(res);
    //             console.log(res.content);
    //             sessionStorage.clear();
    //             sessionStorage.setItem('authorInfo',JSON.stringify(res.content));
    //             //window.location.href="/AuthorPortrait";
    //             window.location.href="/AuthorPortrait";
    //         },
    //         function (error) {
    //             console.log("错了");
    //             alert(error);
    //         }
    //     )
    // });

});
