function makeLink(msg, link) {
    return msg;
    // return '<a class="link" href="' + link + '">' + msg + '</a>';
}

$(document).ready(function () {

    var list = JSON.parse(sessionStorage.getItem('authorInfo'));
    var info = "";
    if (list.length == 0) {
        info = "<td colspan='5'>对不起没有符合检索要求的作者</td>";
        document.getElementById('result-one').innerHTML = info;
        alert("No matching results found");
    } else {
        list.forEach(function (author) {
            info +=
                "<tr class='item'> " +
                "<td>" + makeLink(author.name, "/AuthorPortrait?id="+author.name) + "</td>" +
                "<td>" + makeLink(author.affiliations, "/AuthorPortrait?id="+author.affiliations) + "</td>" +
                "</tr>";

        });
        $('#result-one').html(info);
        document.getElementById("result-one").innerHTML = info;
    }

    $("#result-one").on('click','.item',function(){
        var authorName=$(this).find('td:first').text();
        var aff=$(this).children('td').eq(1).text();
        console.log(aff);
        getRequest(
            '/searchPortrait/author?authorName='+authorName+'&aff='+encodeURIComponent(aff),
            function (res) {
                console.log(res);
                sessionStorage.clear();
                sessionStorage.setItem('authorInfo',JSON.stringify(res.content));
                //window.location.href="/AuthorPortrait";
                window.location.href="/AuthorPortrait";
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )

        // $.ajax({
        //     type: 'POST',
        //     url: '/searchPortrait/author?authorName='+keyword,
        //     async: false,
        //     data: JSON.stringify(keyword),
        //     contentType: 'keyword/json',
        //     processData: false,
        //     success: function (res) {
        //         console.log(res.content);
        //         sessionStorage.clear();
        //         sessionStorage.setItem('authorInfo',JSON.stringify(res.content));
        //         // localStorage.setItem('keywordInfo',JSON.stringify(res.content));
        //         window.location.href="/AuthorPortrait";
        //     },
        //     error: function (error) {
        //         alert("failure" + error);
        //     }
        // })
    });
});