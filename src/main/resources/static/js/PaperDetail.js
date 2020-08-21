function makeLink(msg, link) {
    // return msg;
    return '<a class="link" target="_blank" href="' + link + '">' + msg + '</a>';
}

$(document).ready(function () {
    var paper = JSON.parse(sessionStorage.getItem('paperInfo'));
    var str = "";

    //姓名组合
    var authorNames="";
    paper.authors.forEach(function(author){
        if(author!=paper.authors[0])
            authorNames+=";";
        authorNames+=author;
    });
    console.log(authorNames);

    //机构集合
    var affs="";
    paper.affiliations.forEach(function(aff){
        if(aff!=paper.affiliations[0])
            affs+=";";
        affs+=aff;
    });
    console.log(affs);

    //研究方向集合
    var keywords = "";
    paper.keywords.forEach(function(keyword){
        if(keyword!=paper.keywords[0])
            keywords+=";";
        keywords+=keyword;
    });
    console.log(keywords);

    str = "<tr><td>" + paper.title + "</td></tr>";
    document.getElementById("one").innerHTML = str;
    str = "<tr><td>" + authorNames + "</td></tr>";
    document.getElementById("two").innerHTML = str;
    str = "<tr><td>" + affs + "</td></tr>";
    document.getElementById("three").innerHTML = str;
    str = "<tr><td>" + paper.publicTitle + "</td></tr>";
    document.getElementById("four").innerHTML = str;
    str = "<tr><td>" + paper.year + "</td></tr>";
    document.getElementById("five").innerHTML = str;
    str = "<tr><td>" + makeLink(paper.pdfLink, paper.pdfLink) + "</td></tr>";
    document.getElementById("six").innerHTML = str;
    str = "<tr><td>" + keywords + "</td></tr>";
    document.getElementById("seven").innerHTML = str;
    str = "<tr><td>" + paper.referenceCount + "</td></tr>";
    document.getElementById("eight").innerHTML = str;
    str = "<tr><td>" + paper.publisher + "</td></tr>";
    document.getElementById("nine").innerHTML = str;
    // str = "<tr><td>" + paper.docID + "</td></tr>";
    // document.getElementById("ten").innerHTML = str;
    str = "<tr><td>" + paper.paperAbstract + "</td></tr>";
    document.getElementById("eleven").innerHTML = str;
});