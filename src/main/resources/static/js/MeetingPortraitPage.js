function makeLink(msg, link) {
    return msg;
    // return '<a class="link" href="' + link + '">' + msg + '</a>';
}

$(document).ready(function(){
    console.log("meeting");
    var content = JSON.parse(sessionStorage.getItem('meetingInfo'));
    console.log(content);

    if (content==null) {
        alert("No matching results found");
    } else {
        $('#header-meeting').html(content.name);
        $('#paper-num').html(content.documentCount);
        $('#scholar-num').html(content.authorCount);
        $('#cites-num').html(content.referenceCount);
        $('#list-author-num').html(content.authors.length);
        $('#list-paper-num').html(content.documents.length);
        $('#list-aff-num').html(content.affiliations.length);

        getAuthors(content.authors);
        getKeywordCloud(content.directions);

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

function getKeywordCloud(map){
    let list = [];
    for (let keyword in map){
        list.push({
            name: keyword,
            value: map[keyword]
        })
    }
    var chart = echarts.init(document.getElementById('direction-detail'));
    chart.setOption({
        series: [{
            type: 'wordCloud',
            shape: 'circle',
            left: 'center',
            top: 'center',
            width: '70%',
            height: '80%',
            right: null,
            bottom: null,
            sizeRange: [12, 30],
            rotationRange: [-90, 90],
            rotationStep: 45,
            gridSize: 8,
            drawOutOfBound: true,
            textStyle: {
                normal: {
                    fontFamily: 'sans-serif',
                    fontWeight: 'bold',
                    color: function () {
                        return 'rgb(' + [
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160)
                        ].join(',') + ')';
                    }
                },
                emphasis: {
                    shadowBlur: 4,
                    shadowColor: '#333'
                }
            },
            data: list.map(item => {
                return {
                    name: item.name,
                    value: item.value,
                    url: "/searchPortrait/keyword?keyword="+item.name,
                }
            })
        }]
    });
}