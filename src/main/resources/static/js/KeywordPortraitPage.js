function makeLink(msg, link) {
    return msg;
    // return '<a class="link" href="' + link + '">' + msg + '</a>';
}

$(document).ready(function(){
    console.log("keyword");
    var content = JSON.parse(sessionStorage.getItem('keywordInfo'));
    console.log(content);
    if (content==null) {
        alert("No matching results found");
    } else {
        $('#header-keyword').html(content.name);
        $('#heat-num').html(content.heat);
        $('#paper-num').html(content.documentCount);
        $('#scholar-num').html(content.authorCount);
        $('#cites-num').html(content.referenceCount);
        $('#list-author-num').html(content.authors.length);
        $('#list-paper-num').html(content.documents.length);
        $('#list-aff-num').html(content.affiliations.length);
        $('#list-meeting-num').html(content.meetings.length);

        getAuthors(content.authors);

        $('#tab-authors').on("click",function(){
            $('.page-tab').removeClass("page-tab-active");
            $('#tab-authors').addClass("page-tab-active");
            getAuthors(content.authors);
        });
        $('#tab-papers').on("click",function(){
            $('.page-tab').removeClass("page-tab-active");
            $('#tab-papers').addClass("page-tab-active");
            getPapers(content.documents);
        });
        $('#tab-affs').on("click",function(){
            $('.page-tab').removeClass("page-tab-active");
            $('#tab-affs').addClass("page-tab-active");
            getAffiliations(content.affiliations);
        });
        $('#tab-meetings').on("click",function(){
            $('.page-tab').removeClass("page-tab-active");
            $('#tab-meetings').addClass("page-tab-active");
            getMeetings(content.meetings);
        });
    }
});

function getAuthors(list){
    var info="";
    console.log(list);

    for(let i=0;i<list.length;i++){
        var j=parseInt(i)+1;
        var author=list[i];

        info +=
            "<tr> " +
            "<td>" +  j + "</td>" +
            "<td>" + makeLink(author, "/author?authorName="+author) + "</td>" +
            "</tr>";
    }

    $('#result-one').html(info);
}

function getPapers(list){
    var info="";
    console.log(list);

    for(let i=0;i<list.length;i++){
        var j=parseInt(i)+1;
        var paper=list[i];

        info +=
            "<tr> " +
            "<td>" +  j + "</td>" +
            "<td>" + makeLink(paper, "/PaperDetail?name="+paper) + "</td>" +
            "</tr>";
    }

    $('#result-one').html(info);
}

function getAffiliations(list){
    var info="";
    console.log(list);

    for(let i=0;i<list.length;i++){
        var j=parseInt(i)+1;
        var aff=list[i];

        info +=
            "<tr> " +
            "<td>" +  j + "</td>" +
            "<td>" + makeLink(aff, "/affiliation?affiliationName="+aff) + "</td>" +
            "</tr>";
    }

    $('#result-one').html(info);
}

function getMeetings(list){
    var info="";
    console.log(list);

    for(let i=0;i<list.length;i++){
        var j=parseInt(i)+1;
        var paper=list[i];

        info +=
            "<tr> " +
            "<td>" +  j + "</td>" +
            "<td>" + makeLink(paper, "/meeting?meetingName="+paper) + "</td>" +
            "</tr>";
    }

    $('#result-one').html(info);
}