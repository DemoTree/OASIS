function makeLink(msg, link) {
    return msg;
    // return '<a class="link" href="' + link + '">' + msg + '</a>';
}

$(document).ready(function () {

    var list = JSON.parse(sessionStorage.getItem('affiliationInfo'));
    var info = "";
    if (list.length == 0) {
        info = "<td colspan='5'>对不起没有符合检索要求的机构</td>";
        document.getElementById('result-one').innerHTML = info;
        alert("No matching results found");
    } else {
        list.forEach(function (affiliation) {

            info +=
                "<tr class='item'> " +
                "<td>" + makeLink(affiliation.name, "/AffiliationPortrait?id="+affiliation.name) + "</td>" +
                "<td>" + affiliation.heat + "</td>" +
                "</tr>";

        });
        console.log(info);
        $('#result-one').html(info);
        document.getElementById("result-one").innerHTML = info;
    }

    $("#result-one").on('click','.item',function(){
        var keyword=$(this).find('td:first').text();
        console.log(keyword);
        getRequest(
            '/searchPortrait/affiliation?affiliation='+keyword,
            function (res) {
                console.log(res);
                console.log(JSON.stringify(res.content));
                sessionStorage.clear();
                sessionStorage.setItem('affiliationInfo',JSON.stringify(res.content));
                console.log(1);
                //window.location.href="/AuthorPortrait";
                window.location.href="/AffiliationPortrait";
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )

        // $.ajax({
        //     type: 'POST',
        //     url: '/searchPortrait/affiliation?affiliation='+keyword,
        //     async: false,
        //     data: JSON.stringify(keyword),
        //     contentType: 'keyword/json',
        //     processData: false,
        //     success: function (res) {
        //         console.log(res.content);
        //         sessionStorage.clear();
        //         sessionStorage.setItem('affiliationInfo',JSON.stringify(res.content));
        //         // localStorage.setItem('keywordInfo',JSON.stringify(res.content));
        //         window.location.href="/AffiliationPortrait";
        //     },
        //     error: function (error) {
        //         alert("failure" + error);
        //     }
        // })
    });
});