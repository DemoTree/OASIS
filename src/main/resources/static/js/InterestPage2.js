function makeLink(msg, link, len = 16) {
    let str = String(msg);
    // return '<a class="link" href="' + link + '">' + (str.length > len ? (str.slice(0, len) + '...') : str) + '</a>';
    return (str.length > len ? (str.slice(0, len) + '...') : str);
}

$(document).ready(function () {

    function getAuthorHeat() {
        getRequest(
            '/interest/authorHeat',
            function (res) {
                console.log("author");
                console.log(res.content);
                $(".author-heat").empty();
                var str = "";
                res.content.forEach(function (one) {
                    str +=
                        "<li><a><span class='item' value='" + one.name + "'>" + makeLink(one.name,"/author?authorName="+one.name) + "</span>" +
                        "<span class='index-num' style='float:right'>" + one.heat + "</span></a></li>";

                });
                document.getElementById("author-heat").innerHTML = str;
            }
        )

    }

    function getAffiliationHeat() {
        getRequest(
            '/interest/affiliationHeat',
            function (res) {
                console.log("affiliation");
                console.log(res.content);
                $(".affiliation-heat").empty();
                var str = "";
                res.content.forEach(function (one) {
                    str +=
                        "<li><a><span class='item' value='" + one.name + "'>" + makeLink(one.name,"/affiliation?affiliationName="+one.name) + "</span>" +
                        "<span class='index-num' style='float:right'>" + one.heat + "</span></a></li>";

                });
                document.getElementById("affiliation-heat").innerHTML = str;
            }
        )
    }

    function getResearchDirectionHeat() {
        getRequest(
            '/interest/directionHeat',
            function (res) {
                console.log("direction");
                console.log(res.content);
                $(".research-direction-heat").empty();
                var str = "";
                res.content.forEach(function (one) {
                    str +=
                        "<li><a><span class='item' value='" + one.name + "'>" + makeLink(one.name,"/keyword?keywordName="+one.name) + "</span>" +
                        "<span class='index-num' style='float:right'>" + one.heat + "</span></a></li>";
                });
                document.getElementById("research-direction-heat").innerHTML = str;
            }
        )
    }

    getAuthorHeat();
    getAffiliationHeat();
    getResearchDirectionHeat();

    // $("#author-heat").on('click','.item',function(){
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
    // $("#affiliation-heat").on('click','.item',function(){
    //     var keyword=$(this).attr('value');
    //     console.log(keyword);
    //     getRequest(
    //         '/searchPortrait/affiliation?affiliation='+keyword,
    //         function (res) {
    //             console.log(res);
    //             console.log(res.content);
    //             sessionStorage.clear();
    //             sessionStorage.setItem('affiliationInfo',JSON.stringify(res.content));
    //             //window.location.href="/AuthorPortrait";
    //             window.location.href="/AffiliationPortrait";
    //         },
    //         function (error) {
    //             console.log("错了");
    //             alert(error);
    //         }
    //     )
    // });
    //
    // $("#research-direction-heat").on('click','.item',function(){
    //     var keyword=$(this).attr('value');
    //     console.log(keyword);
    //     getRequest(
    //         '/searchPortrait/keyword?keyword='+keyword,
    //         function (res) {
    //             console.log(res);
    //             console.log(res.content);
    //             sessionStorage.clear();
    //             sessionStorage.setItem('KeywordInfo',JSON.stringify(res.content));
    //             //window.location.href="/AuthorPortrait";
    //             window.location.href="/KeywordPortrait";
    //         },
    //         function (error) {
    //             console.log("错了");
    //             alert(error);
    //         }
    //     )
    // });
});