function makeLink(msg, link) {
    return msg;
    // return '<a class="link" href="' + link + '">' + msg + '</a>';
}

$(document).ready(function () {

    var list = JSON.parse(sessionStorage.getItem('paperlist'));
    var info = "";
    if (list.length == 0) {
        info = "<td colspan='5'>对不起没有符合检索要求的论文</td>";
        document.getElementById('result-one').innerHTML = info;
        alert("No matching results found");
    } else {
        list.forEach(function (paper) {
            var authorNames="";
            paper.authors.forEach(function(author){
                if(author.name!=paper.authors[0].name)
                    authorNames+=";";
                authorNames+=makeLink(author.name,"/AuthorPortrait?id="+author.name);
            });

            info +=
                "<tr class='item'> " +
                "<td>" + makeLink(paper.documentTitle, "/search/document?id="+paper.documentTitle) + "</td>" +
                "<td>" + authorNames + "</td>" +
                "<td>" + makeLink(paper.publicationTitle, "/MeetingPortrait?id="+paper.publicationTitle) + "</td>" +
                "<td>" + paper.publicationYear + "</td>" +
                "<td>" + paper.referenceCount + "</td>" +
                "</tr>";
        });
        $('#result-one').html(info);
        document.getElementById("result-one").innerHTML = info;
    }

    $("#result-one").on('click','.item',function(){
        var keyword=$(this).find('td:first').text();
        console.log(keyword);
        getRequest(
            '/search/title?title='+keyword,
            function (res) {
                console.log(res);
                console.log(JSON.stringify(res.content));
                sessionStorage.clear();
                sessionStorage.setItem('paperInfo',JSON.stringify(res.content));
                //window.location.href="/AuthorPortrait";
                window.location.href="/PaperDetailPage";
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )
    });
});