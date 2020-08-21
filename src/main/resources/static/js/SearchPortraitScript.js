$(document).ready(function () {
    //搜索功能
    function getResultListWithAuthor(keyword) {
        getRequest(
            '/searchPortrait/authorList?name='+encodeURIComponent(keyword),
            function (res) {
                console.log(res.content);
                sessionStorage.clear();
                sessionStorage.setItem('authorInfo',JSON.stringify(res.content));
                //window.location.href="/AuthorPortrait";
                window.location.href="/AuthorPortraitResult";
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )
    }
    function getResultListWithAffiliation(keyword) {
        getRequest(
            '/searchPortrait/affiliationList?affiliation='+encodeURIComponent(keyword),
            function (res) {
                console.log(res.content);
                sessionStorage.clear();
                sessionStorage.setItem('affiliationInfo',JSON.stringify(res.content));
                //window.location.href="/AffiliationPortrait";
                window.location.href="/AffiliationPortraitResult";
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )
    }
    function getResultListWithMeeting(keyword) {
        getRequest(
            '/searchPortrait/meetingList?meeting='+keyword,
            function (res) {
                console.log(res.content);
                sessionStorage.clear();
                sessionStorage.setItem('meetingInfo',JSON.stringify(res.content));
                //window.location.href="/MeetingPortrait";
                window.location.href="/MeetingPortraitResult";
            },
            function (error) {
                alert(error);
            }
        )
    }
    function getResultListWithKeyword(keyword) {
        getRequest(
            '/searchPortrait/keywordList?keyword='+encodeURIComponent(keyword),
            function (res) {
                console.log(res.content);
                sessionStorage.clear();
                sessionStorage.setItem('keywordInfo',JSON.stringify(res.content));
                //window.location.href="/KeywordPortrait";
                window.location.href="/KeywordPortraitResult";
            },
            function (error) {
                alert(error);
            }
        )
    }

    //搜索功能
    var searchBtn0=document.getElementById("search-start-btn-0");
    $("#search-start-btn-0").click(function () {
        var type0=document.getElementById("search-type-0");
        var inp0=document.getElementById("search-value-0");
        if(inp0.value=='')
            alert("输入不能为空！");
        else{
            if(type0.value=="author"){
                console.log(inp0.value);
                getResultListWithAuthor(inp0.value);
            }
            else if(type0.value=="affiliation"){
                getResultListWithAffiliation(inp0.value);
            }
            else if(type0.value=="conference"){
                getResultListWithMeeting(inp0.value);
            }
            else if(type0.value=="keyword"){
                getResultListWithKeyword(inp0.value);
            }
        }
    });
});
