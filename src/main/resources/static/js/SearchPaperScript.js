$(document).ready(function () {
    //搜索模式更改
    var modeBtn=document.getElementById("inc-btn");
    var visible0=document.getElementById("line0");
    var visible1=document.getElementById("line1");
    var visible2=document.getElementById("line2");
    var visible3=document.getElementById("line3");
    var visible4=document.getElementById("line4");
    var currentNum=document.getElementById("current-num");
    modeBtn.onclick=function () {
        if(currentNum.value=="1"){
            visible0.style.display = "none";
            visible1.style.display = "";
            visible2.style.display = "";
            visible3.style.display = "";
            visible4.style.display = "";
            $("#current-num").val("4");
            modeBtn.innerHTML="back"
        }else if(currentNum.value=="4"){
            visible0.style.display = "";
            visible1.style.display = "none";
            visible2.style.display = "none";
            visible3.style.display = "none";
            visible4.style.display = "none";
            $("#current-num").val("1");
            modeBtn.innerHTML="advanced"
        }
    }
    //搜索功能
    function getResultListWithAuthor(keyword) {
        getRequest(
            '/search/author?authorName='+keyword,
            function (res) {
                console.log(res.content);
                sessionStorage.clear();
                sessionStorage.setItem('paperlist',JSON.stringify(res.content));
                window.location.href="/PaperResultPage";
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )
    }
    function getResultListWithAffiliation(keyword) {
        getRequest(
            '/search/affiliation?affiliation='+keyword,
            function (res) {
                console.log(res.content);
                sessionStorage.clear();
                sessionStorage.setItem('paperlist',JSON.stringify(res.content));
                window.location.href="/PaperResultPage";
            },
            function (error) {
                alert(error);
            }
        )
    }
    function getResultListWithMeeting(keyword) {
        getRequest(
            '/search/meeting?meeting='+keyword,
            function (res) {
                console.log(res.content);
                sessionStorage.clear();
                sessionStorage.setItem('paperlist',JSON.stringify(res.content));
                window.location.href="/PaperResultPage";
            },
            function (error) {
                alert(error);
            }
        )
    }
    function getResultListWithKeyword(keyword) {
        getRequest(
            '/search/keyword?keyword='+encodeURIComponent(keyword),
            function (res) {
                console.log(res.content);
                sessionStorage.clear();
                sessionStorage.setItem('paperlist',JSON.stringify(res.content));
                window.location.href="/PaperResultPage";
            },
            function (error) {
                alert(error);
            }
        )
    }
    function getResultListWithForm(authorName,affiliation,meeting,keyword) {
        getRequest(
            '/search?authorName='+authorName+'&affiliation='+affiliation+'&meeting='+meeting+'&keyword='+encodeURIComponent(keyword),
            function (res) {
                console.log(res.content);
                sessionStorage.clear();
                sessionStorage.setItem('paperlist',JSON.stringify(res.content));
                window.location.href="/PaperResultPage";
            },
            function (error) {
                alert(error);
            }
        )
    }
    function getDocumentDetail(title) {
        getRequest(
            '/search/title?title='+title,
            function (res) {
                console.log(res.content);
                sessionStorage.clear();
                sessionStorage.setItem('paperDetail',JSON.stringify(res.content));
                window.location.href="/PaperDetailPage";
            },
            function (error) {
                console.log("错了");
                alert(error);
            }
        )
    }

    //搜索功能
    var searchBtn0=document.getElementById("search-start-btn-0");
    var currentNum=document.getElementById("current-num");
    $("#search-start-btn-0").click(function () {
        var type0=document.getElementById("search-type-0");
        var inp0=document.getElementById("search-value-0");
        if(inp0.value=='')
            alert("输入不能为空！");
        else {
            if (type0.value == "author") {
                getResultListWithAuthor(inp0.value);
            } else if (type0.value == "affiliation") {
                getResultListWithAffiliation(inp0.value);
            } else if (type0.value == "conference") {
                getResultListWithMeeting(inp0.value);
            } else if (type0.value == "keyword") {
                getResultListWithKeyword(inp0.value);
            }
        }
    });
    var searchBtn1=document.getElementById("search-start-btn-1");
    $("#search-start-btn-1").click(function () {
        var inp1=document.getElementById("search-value-1");
        var inp2=document.getElementById("search-value-2");
        var inp3=document.getElementById("search-value-3");
        var inp4=document.getElementById("search-value-4");
        console.log(inp4.value);
        getResultListWithForm(inp1.value,inp2.value,inp3.value,inp4.value);
    });
})


