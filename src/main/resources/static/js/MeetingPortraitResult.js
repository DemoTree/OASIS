function makeLink(msg, link) {
    return msg;
    // return '<a class="link" href="' + link + '">' + msg + '</a>';
}

$(document).ready(function () {

    var list = JSON.parse(sessionStorage.getItem('meetingInfo'));
    var info = "";
    if (list.length == 0) {
        info = "<td colspan='5'>对不起没有符合检索要求的作者</td>";
        document.getElementById('result-one').innerHTML = info;
        alert("No matching results found");
    } else {
        list.forEach(function (meeting) {

            info +=
                "<tr class='item'> " +
                "<td>" + makeLink(meeting.name, '/searchPortrait/meeting?meeting='+meeting.name) + "</td>" +
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
            '/searchPortrait/meeting?meeting='+keyword,
            function (res) {
                console.log(res);
                sessionStorage.clear();
                sessionStorage.setItem('meetingInfo',JSON.stringify(res.content));
                //window.location.href="/AuthorPortrait";
                window.location.href="/MeetingPortrait";
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )

        // $.ajax({
        //     type: 'POST',
        //     url: '/searchPortrait/meeting?meeting='+keyword,
        //     async: false,
        //     data: JSON.stringify(keyword),
        //     contentType: 'keyword/json',
        //     processData: false,
        //     success: function (res) {
        //         console.log(res.content);
        //         sessionStorage.clear();
        //         sessionStorage.setItem('meetingInfo',JSON.stringify(res.content));
        //         window.location.href="/MeetingPortrait";
        //     },
        //     error: function (error) {
        //         alert("failure" + error);
        //     }
        // })
    })
});